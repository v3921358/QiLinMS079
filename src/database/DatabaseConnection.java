package database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 疯神冒险岛
 */
public class DatabaseConnection {

    private static final HashMap<Integer, ConWrapper> connections = new HashMap();
    private static final ReentrantLock lock = new ReentrantLock();
    private static String dbDriver, dbUrl, dbUser, dbPass;
    private static boolean propsInited = false;
    private static Properties dbProps = new Properties();
    private static long connectionTimeOut = 5 * 60 * 1000; // 5 minutes 300000毫秒
    public static final int CLOSE_CURRENT_RESULT = 1;

    private DatabaseConnection() {
    }

    public static final int KEEP_CURRENT_RESULT = 2;

    public static final int CLOSE_ALL_RESULTS = 3;
 
    public static final int SUCCESS_NO_INFO = -2;
  
    public static final int EXECUTE_FAILED = -3;

    public static final int RETURN_GENERATED_KEYS = 1;

    public static final int NO_GENERATED_KEYS = 2;

    public static Connection getConnection() {
        Thread cThread = Thread.currentThread();
        int threadID = (int) cThread.getId();
        ConWrapper ret = connections.get(threadID);
        if (ret == null) {
            Connection retCon = connectToDB();
            ret = new ConWrapper(retCon);
            ret.id = threadID;
            connections.put(threadID, ret);
            //System.out.println("[DB信息] 线程ID " + threadID + " 创建了一个新的数据库连接.");
        }
        return ret.getConnection();
    }
    private static long getWaitTimeout(Connection con) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = (Statement) con.createStatement();
            rs = stmt.executeQuery("SHOW VARIABLES LIKE 'wait_timeout'");
            if (rs.next()) {
                return Math.max(1000, rs.getInt(2) * 1000 - 1000);
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            return -1;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException ex1) {
                        }
                    }
                }
            }
        }
    }

    private static Connection connectToDB() {
        if (!propsInited) {
            try {
                //FileReader fR = new FileReader("settings.ini");
                FileReader fR = new FileReader("配置.ini");
                dbProps.load(fR);
                fR.close();
            } catch (IOException ex) {
                System.err.println("加载数据库配置出错，请检查" + ex);
              //  throw new DatabaseException(ex);
            }
            dbDriver = "com.mysql.jdbc.Driver";
            dbUrl = "jdbc:mysql://" + "127.0.0.1" + ":" +  "3306" + "/" +  "v079" + "?useUnicode=true&characterEncoding=UTF8";
            dbUser = dbProps.getProperty("QhMs.db.user");
            dbPass = dbProps.getProperty("QhMs.db.password");
            try {
                connectionTimeOut = Long.parseLong(dbProps.getProperty("timeout"));
            } catch (Exception e) {
                //System.out.println("[DB信息] Cannot read Timeout Information, using default: " + connectionTimeOut + " ");
                System.out.println("○ 【正在加载】 -> 数据库最大连接数 " + connectionTimeOut + " ");
            }
        }
        try {
            Class.forName(dbDriver); // touch the MySQL driver
        } catch (ClassNotFoundException e) {
            //System.out.println("[DB信息] Could not locate the JDBC mysql driver.");
        }
        try {
            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            if (!propsInited) {
                long timeout = getWaitTimeout(con);
                if (timeout == -1) {
                    //System.out.println("[DB信息] Failed to read Wait_Timeout, using " + connectionTimeOut + " instead.");
                } else {
                    connectionTimeOut = timeout;
      //              System.out.println("[DB信息] Database Timeout is: " + connectionTimeOut + " milliseconds.");
                }
                propsInited = true;
            }
            return con;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public static class ConWrapper {

        private long lastAccessTime = 0;
        private Connection connection;
        private int id;

        public ConWrapper(Connection con) {
            this.connection = con;
        }

        public Connection getConnection() {
            if (expiredConnection()) {
                //System.out.println("[DB信息] 线程ID " + id + " 的SQL连接已经超时.重新连接...");
                try { // Assume that the connection is stale
                    connection.close();
                } catch (Throwable err) {
                    // Who cares
                }
                this.connection = connectToDB();
            }
            lastAccessTime = System.currentTimeMillis(); // Record Access
            return this.connection;
        }

        /**
         * Returns whether this connection has expired
         *
         * @return
         */
        public boolean expiredConnection() {
            if (lastAccessTime == 0) {
                return false;
            }
            try {
                return System.currentTimeMillis() - lastAccessTime >= connectionTimeOut || connection.isClosed();
            } catch (Throwable ex) {
                return true;
            }
        }

        private boolean close() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public static void closeAll() throws SQLException {
        for (ConWrapper con : connections.values()) {
            con.connection.close();
        }
        connections.clear();
    }
    //新增清除SQL连线
    public static void closeTimeout() {
        int i = 0;
        lock.lock();
        List<Integer> keys = new ArrayList(connections.keySet());
        try {
            for (Integer tid : keys) {
                ConWrapper con = connections.get(tid);
                if (con.close()) {
                    i++;
                }
            }
        } finally {
            lock.unlock();
        }
    }
    
    
    
      public final static Runnable CloseSQLConnections = new Runnable() {

        @Override
        public void run() {
            DatabaseConnection.closeTimeout();
        }
    };
    
}
