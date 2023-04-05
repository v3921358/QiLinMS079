package database;

import com.alibaba.druid.pool.DruidDataSource;
import server.ServerProperties;

/**
 *
 * @author XiaoMaDengDengWo
 */
public class DBConPool {

    private static DruidDataSource dataSource = null;
    public static final int RETURN_GENERATED_KEYS = 1;
    public static String dbUser = "", dbPass = "root", dbIp = "localhost", dbName = "v079";
    public static int dbport = 3306;

    static {
        InitDB();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("[数据库信息] 找不到JDBC驱动.");
            System.exit(0);
        }

    }

    public static void InitDB() {
        dbUser = ServerProperties.getProperty("qilin.db.user", dbUser);
        dbport = ServerProperties.getProperty("qilin.db.port", dbport);
        dbPass = ServerProperties.getProperty("qilin.db.password", dbPass);
        dbIp = ServerProperties.getProperty("qilin.db.ip", dbIp);
        dbName = ServerProperties.getProperty("qilin.db.name", dbName);
    }

    private static class InstanceHolder {

        public static final DBConPool instance = new DBConPool();
    }

    public static DBConPool getInstance() {
        return InstanceHolder.instance;
    }

    private DBConPool() {
    }

    public DruidDataSource getDataSource() {
        if (dataSource == null) {
            InitDBConPool();
        }
        return dataSource;
    }

    private void InitDBConPool() {
        dataSource = new DruidDataSource();
        dataSource.setName("mysql_pool");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + "127.0.0.1" + ":" + dbport + "/" + dbName + "?useUnicode=true&characterEncoding=UTF8");
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPass);
        dataSource.setInitialSize(300);//150
        dataSource.setMinIdle(500);//250
        dataSource.setMaxActive(3000);//1000
        //dataSource.setInitialSize(750);
        //dataSource.setMinIdle(1250);
        //dataSource.setMaxActive(5000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setMaxWait(60000);
        dataSource.setUseUnfairLock(true);
    }
}
