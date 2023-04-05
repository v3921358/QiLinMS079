package constants;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import server.ServerProperties;

public class ServerConfig {

     public static boolean pvp = false;//pvp开启
    public static int pvpch = 1;//pvp频道
    public static boolean LOG_MRECHANT = true;//精灵商人出售记录
    public static boolean LOG_CSBUY = true;//商城购买记录
    public static boolean LOG_DAMAGE = false;//伤害检测记录
    public static boolean LOG_CHAT = true;//聊天记录
    public static boolean LOG_MEGA = true;//广播记录
    public static boolean LOG_PACKETS = false;
    public static boolean CHRLOG_PACKETS = false;//角色封包输出
    public static boolean AUTO_REGISTER = true;//账号注册
    public static boolean LOCALHOST = false;//废弃的
    public static boolean Encoder = false;//废弃的
    public static boolean TESPIA = false;//测试服
    public static boolean shieldWardAll = false;//卷轴对装备防爆
    public static boolean DISCOUNTED = false;//栏位扩充
    public static boolean 泡点系统 = false;//泡点系统开关
    public static int 泡点地图 = 910000000;//泡点地图
    public static int 点卷数量 = 5;//泡点地图
    public static int 抵用卷数量 = 5;//泡点地图
    public static int 豆豆数量 = 5;//泡点地图
    public static int 等级经验倍率 = 100;//泡点地图
    public static String SERVERNAME = "冒险岛";//游戏名字
    public static String version = "Ver.079.13 [ VIP版 ]";
    public static String TOUDING = "冒险岛欢迎您！";
    public static String IP = "127.0.0.1";
    public static String wzpath = "WZ";
    private static String EVENTS = null;
    public static boolean DEBUG_MODE = false;//debug模式开关
    public static boolean NMGB = true;//匿名广播开关
    public static boolean PDCS = false;//频道掉线测试
    public static int RSGS = 0;//人物灌水百分比
    
    public static int maxlevel = 250;
    
    public static int kocmaxlevel = 200;

    public static boolean isPvPChannel(int ch) {
        return pvp && ch == pvpch;
    }

    //public static final byte[] Gateway_IP = new byte[]{(byte) 114, (byte) 67, (byte) 126, (byte) 65};//114.67.126.65
    //public static final byte[] Gateway_IP2 = new byte[]{(byte) 114, (byte) 67, (byte) 126, (byte) 65};
    public static final byte[] Gateway_IP = new byte[]{(byte) 127, (byte) 0, (byte) 0, (byte) 1};
    public static final byte[] Gateway_IP2 = new byte[]{(byte) 127, (byte) 0, (byte) 0, (byte) 1};


    public static String[] getEvents(boolean reLoad) {
        return getEventList(reLoad).split(",");
    }

    public static String getEventList(boolean reLoad) {
        if (EVENTS == null || reLoad) {
            File root = new File("脚本/事件");
            File[] files = root.listFiles();
            EVENTS = "";
            for (File file : files) {
                if (!file.isDirectory()) {
                    String[] fileName = file.getName().split("\\.");
                    if (fileName.length > 1 && "js".equals(fileName[fileName.length - 1])) {
                        for (int i = 0; i < fileName.length - 1; i++) {
                            EVENTS += fileName[i];
                        }
                        EVENTS += ",";
                    }
                }
            }
        }
        return EVENTS;
    }

    public static boolean isAutoRegister() {
        return AUTO_REGISTER;
    }

    public static String getVipMedalName(int lv) {
        String medal = "";
        if (SERVERNAME.equals("冒险岛")) {
            switch (lv) {
                case 1:
                    medal = " <普通VIP>";
                    break;
                case 2:
                    medal = " <進階VIP>";
                    break;
                case 3:
                    medal = " <高級VIP>";
                    break;
                case 4:
                    medal = " <尊貴VIP>";
                    break;
                case 5:
                    medal = " <至尊VIP>";
                    break;
                default:
                    medal = " <VIP" + medal + ">";
                    break;
            }
        } else if (SERVERNAME.equals("冒险岛")) {
            switch (lv) {
                case 1:
                    medal = "☆";
                    break;
                case 2:
                    medal = "☆★";
                    break;
                case 3:
                    medal = "☆★☆";
                    break;
                case 4:
                    medal = "☆★☆★";
                    break;
                case 5:
                    medal = "☆★☆★☆";
                    break;
                case 6:
                    medal = "☆★☆★☆★";
                    break;
                case 7:
                    medal = "☆★☆★☆★☆";
                    break;
                case 8:
                    medal = "☆★☆★☆★☆★";
                    break;
                case 9:
                    medal = "☆★☆★☆★☆★☆";
                    break;
                case 10:
                    medal = "☆★☆★☆★☆★☆★";
                    break;
                case 11:
                    medal = "楓之谷第一土豪";
                    break;
                default:
                    medal = "<VIP" + medal + ">";
                    break;
            }
        }
        return medal;
    }

    public static void loadSetting() {
        LOG_MRECHANT = ServerProperties.getProperty("qilin.merchantLog", LOG_MRECHANT);
        LOG_MEGA = ServerProperties.getProperty("qilin.megaLog", LOG_MEGA);
        LOG_CSBUY = ServerProperties.getProperty("qilin.csLog", LOG_CSBUY);
        LOG_DAMAGE = ServerProperties.getProperty("qilin.damLog", LOG_DAMAGE);
        LOG_CHAT = ServerProperties.getProperty("qilin.chatLog", LOG_CHAT);
        LOG_PACKETS = ServerProperties.getProperty("qilin.packetLog", LOG_PACKETS);
        AUTO_REGISTER = ServerProperties.getProperty("qilin.autoRegister", AUTO_REGISTER);
        SERVERNAME = ServerProperties.getProperty("qilin.serverName", SERVERNAME);
        DEBUG_MODE = ServerProperties.getProperty("qilin.debug", DEBUG_MODE);
    }

    static {
        loadSetting();
    }

    //v113.cizaojdk.top 自己用
    /*public static String getIP() {
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName("rose1234.ddns.net");
            //ip = InetAddress.getByName("v113.cizaojdk.top");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("getIP_null_null");
        }
        System.out.println(ip.getHostAddress());
        return ip.getHostAddress();
    }*/
    //taiwangf.ddns.net
    /*public static String getIP2() {
        InetAddress ip = null;
        try {
            //ip = InetAddress.getByName(TESPIA ? "yuchan0516.no-ip.org" : "paopaoms.win");
            //ip = InetAddress.getByName(TESPIA ? "77520.ddns.net" : "www.paopaoms.win");
            ip = InetAddress.getByName(TESPIA ? "77520.ddns.net" : "paopaoms.cizaojdk.top");
            //ip = InetAddress.getByName("taiwangf.ddns.net");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("getIP2_null_null");
        }
        return ip.getHostAddress().toString();
    }*/
 /*public static byte[] getIP3() {
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName("rose1234.ddns.net");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("获取失败");
        }
        System.out.println(ip.getAddress());
        return ip.getAddress();
    }*/
}
