package abc;


import database.DatabaseConnection;
//import gui.Qhms;

import handling.world.MapleParty;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import server.ServerProperties;
import tools.FileoutputUtil;
import static tools.FileoutputUtil.CurrentReadable_Date;
import static tools.FileoutputUtil.CurrentReadable_Time;

/*

 * 《控制源》 方便的控制源码内部一些数据，功能等等。 
 */
public class Game {

    //服务端名称
    public static String 服务端名称 = "芒果冒险岛(079)游戏服务端";
    //服务端名称
    public static String 安全系统 = "<安全防护>\r\n\r\nVer.1.0\r\n\r\n";
    //服务端名称
    public static String 官方网站 = "www.55ax.cn";
    //服务端名称
    public static String 更新程序 = "更新程序.exe";
    //服务端版本号
    public static int 版本 = 27;
    //禁止未认证脚本使用*
    public static String 窗口标题 = "mGms(079)Ver." + 版本 + " 官网:" + 官方网站 + " ";
    //客户端版本号ZEVMS更新程序
    public static final short 检测客户端版本1 = 79;
    //客户端版本号2
    public static final String 检测客户端版本2 = "1";
    //频道端口
    public static int 商城端口 = 8600;
    //作者QQ
    public static String 作者QQ = "czmyongyuankx";
    //作者
    public static String 作者昵称 = "芒果冒险岛V079";
    //官方群
    public static String 官方群号 = "774775139";
    //人数限制
    public static int 人数限制 = 500;
    //官方群链接
    public static String 官方群链接 = "http://www.baidu.com";
    //服务端网速检测指定IP 服务器IP
    public static String IP地址 = "127.0.0.1";
    //单机体验过期时间
    public static String 过期时间 = "2020-05-01 00:00";
    //从网络获取时间
    public static String 获取网络时间 = "http://baidu.com";
    //当前网络时间
    public static String 当前时间 = "" + 获取网络时间(获取网络时间) + "";
    //每日清理在线时间（准备）
    public static int 清理每日在线时间 = 1;
    //每日清理在线时间（开始清理）
    public static int 准备清理每日在线时间 = 0;//在线时间清理默认23
    //服务端自动定时重启（准备）
    public static int 准备服务端自动重启 = 15;
    //服务端自动定时重启（开始重启）
    public static int 服务端自动重启 = 16;
    //服务端网速检测指定网址
    public static String 测速网速开关 = "关";
    public static String 测速网速 = "www.baidu.com";
    //服务端配置要求*低于该配置无法启动
    public static String 服务端配置需要开关 = "关";

    public static int 服务端配置需要 = 16 * 1024 * 1024;//KB
    //服务端IP地址*引用配置文件
    public static String 服务端IP地址 = MapleParty.IP地址;
    //服务端开服名字*引用配置文件
    public static String 开服名字 = MapleParty.开服名字;

    //服务端调试
    public static String 调试 = "关";
    public static String 调试2 = "关";
    public static String 调试3 = "开";
    public static String OX猜题 = "关";
    public static String 宠物调试 = "关";
    public static String 调试输出 = "关";
    public static String 外挂调试 = "关";
    public static String 地图脚本报错 = "关";
    //技能定义选项
    public static String 原始技能 = "开";
    public static String 技改1 = "关";
    public static String 技改2 = "关";
    public static String 技改3 = "关";
    public static String 技改4 = "关";
    //指令开关集
    public static String 等级 = "开";
    public static String 人气设置 = "开";
    public static String 无敌 = "开";
    public static String 刷技能点 = "开";
    public static String 刷能力点 = "开";
    public static String 刷 = "开";
    public static String 吸怪 = "开";
    public static String 刷新 = "开";
    public static String 清物 = "开";
    public static String 清怪 = "开";
    public static String 清怪2 = "开";
    public static String 我的位置 = "开";
    public static String 召唤怪物 = "开";
    public static String 传送 = "开";
    //活动时间设置
    public static int 向高地活动时间 = 20 * 60 * 1000;
    public static int 上楼活动时间 = 20 * 60 * 1000;
    //禁止未认证脚本使用*
    public static String 禁止未认证脚本使用 = "开";
    //单机说明文字
    public static int 单机人数 = 500;
    public static String 单机说明文字 = "服务端目前模式:单机版，限制 " + 单机人数 + " 人";
    public static String 单机滚动公告 = "目前服务端为单机版，仅限 " + 单机人数 + " 人登陆游戏，仅供单人娱乐体验，如需体验联机，请租凭服务器然后切换为联机模式";
    //联机说明文字
    public static int 联机人数 = 300;
    public static String 联机说明文字 = "服务端目前模式:联机版，限制 " + 联机人数 + " 人";
    public static String 联机滚动公告 = "目前服务端为联机版，仅限 " + 联机人数 + " 人登陆游戏，如需体验更多人数，请购买私服版本";
    //私服说明文字
    public static String 私服说明文字 = "服务端目前模式:私服版，无限制人数";
    public static String 私服滚动公告 = ServerProperties.getProperty("ZEV.ServerMessage");
    //NPC错误文本提示
    public static String NPC错误文本提示 = "我是不是能为你做些什么呢？";
    //脚本NPC引用前缀
    public static String NPC前缀 = "cm";
    public static String NPC前缀2 = "MMP";
    public static int 封包异常输出;
    public static int 封包异常;

    public static boolean 屏蔽文字(String a) {
        switch (a) {
            case "擦":
                return true;
            default:
                return false;
        }
    }

    public static boolean 两小时限时道具(int a) {
        switch (a) {
            case 5211060:
                return true;
            default:
                return false;
        }
    }

    public static boolean 三小时限时道具(int a) {
        switch (a) {
            //双倍经验值卡（三小时）
            case 5211047:
            //双倍爆率值卡（三小时）
            case 5360014:
            //三倍经验值卡（三小时）
            case 5211060:
                return true;
            default:
                return false;
        }
    }

    public static boolean 一天时限时道具(int a) {
        switch (a) {
            //黑板1日
            case 5370001:
            //雇佣商店
            case 5030001:
            case 5030009:
            case 5030011:
            case 5030012:
            //双倍经验值卡（一天权）
            case 5210000:
            //双倍经验值卡（白天一天权）
            case 5210002:
            //双倍经验值卡（晚上一天权）
            case 5210004:
            //双倍爆率卡（一天权）
            case 5360000:
            //双倍爆率卡（一天权）
            case 5360015:
                return true;
            default:
                return false;
        }
    }

    public static boolean 七天时限时道具(int a) {
        switch (a) {
            //黑板7日
            case 5370000:
            //装备许可证
            case 5590000:
            case 5590001:
            //雇佣商店
            case 5030000:
            case 5030008:
            case 5030010:
            case 5030018:
            //双倍经验值卡（七天权）
            case 5210001:
            //双倍经验值卡（白天七天权）
            case 5210003:
            //双倍经验值卡（晚上七天权）
            case 5210005:
            //双倍爆率卡（一周权）
            case 5360016:
                return true;
            default:
                return false;
        }
    }

    public static boolean 三十天时限时道具(int a) {
        switch (a) {
            //雇佣
            case 5030016:
                return true;
            default:
                return false;
        }
    }

    public static boolean 主城(int a) {
        switch (a) {
            //彩虹村
            case 1000000:
            //彩虹村武器店
            case 1000001:
            //彩虹村村民家
            case 1000002:
            //彩虹村杂货店
            case 1000003:
            //南港
            case 2000000:
            //射手村
            case 100000000:
            //射手村民宅
            case 100000001:
            //射手村集市
            case 100000100:
            //射手村武器店
            case 100000101:
            //射手村杂货店
            case 100000102:
            //射手村整容院
            case 100000103:
            //射手村美发店
            case 100000104:
            //射手村护肤中心
            case 100000105:
            //射手村公园
            case 100000200:
            //宠物公园 
            case 100000202:
            //射手村游戏中心
            case 100000203:
            //弓箭手的殿堂
            case 100000204:
            //魔法密林
            case 101000000:
            //魔法密林武器店
            case 101000001:
            //魔法密林杂货店
            case 101000002:
            //魔法密林图书馆
            case 101000003:
            //魔法师的殿堂
            case 101000004:
            //生命之林
            case 101000200:
            //魔法密林码头
            case 101000300:
            //候船室
            case 101000301:
            //勇士部落
            case 102000000:
            case 102000001:
            case 102000002:
            case 102000003:
            case 102000004:
            case 103000000:
            case 103000001:
            case 103000002:
            case 103000003:
            case 103000004:
            case 103000005:
            case 103000006:
            case 103000008:
            case 103000100:
            case 104000000:
            case 104000001:
            case 104000002:
            case 104000003:
            case 104000004:
            case 105040400:
            case 105040401:
            case 105040402:
            case 105040300:
            case 106020000:
            case 140000000:
            case 140000001:
            case 140000010:
            case 140000011:
            case 140000012:
            case 140010110:
            case 200000000:
            case 200000001:
            case 200000002:
            case 200000100:
            case 200000110:
            case 200000111:
            case 200000112:
            case 200000120:
            case 200000121:
            case 200000122:
            case 200000130:
            case 200000131:
            case 200000132:
            case 200000140:
            case 200000141:
            case 200000150:
            case 200000151:
            case 200000152:
            case 200000160:
            case 200000161:
            case 200000200:
            case 200000201:
            case 200000202:
            case 200000203:
            case 200000300:
            case 200000301:
            case 209000000:
            case 211000001:
            case 209080000:
            case 209080100:
            case 211000000:
            case 211000100:
            case 211000101:
            case 211000102:
            case 220000000:
            case 220000001:
            case 220000002:
            case 220000003:
            case 220000004:
            case 220000005:
            case 220000006:
            case 220000100:
            case 220000110:
            case 220000111:
            case 220000300:
            case 220000301:
            case 220000302:
            case 220000303:
            case 220000304:
            case 220000305:
            case 220000306:
            case 220000307:
            case 220000400:
            case 220000500:
            case 221000000:
            case 221000001:
            case 221000100:
            case 221000200:
            case 221000300:
            case 222000000:
            case 222020000:
            case 230000000:
            case 230000001:
            case 230000002:
            case 230000003:
            case 240000000:
            case 240000001:
            case 240000002:
            case 240000003:
            case 240000004:
            case 240000005:
            case 240000006:
            case 240000100:
            case 240000110:
            case 240000111:
            case 250000000:
            case 250000001:
            case 250000002:
            case 250000003:
            case 250000100:
            case 251000000:
            case 260000000:
            case 260000100:
            case 260000110:
            case 260000200:
            case 260000201:
            case 260000202:
            case 260000203:
            case 260000204:
            case 260000205:
            case 260000206:
            case 260000207:
            case 260000300:
            case 260000301:
            case 260000302:
            case 260000303:
            case 261000000:
            case 261000001:
            case 261000002:
            case 261000010:
            case 261000011:
            case 261000020:
            case 261000021:
            case 270010000:
            case 270000000:
            case 300000000:
            case 300000001:
            case 300000002:
            case 300000010:
            case 300000011:
            case 300000012:
            case 500000000:
            case 540000000:
            case 541000000:
            case 550000000:
            case 551000000:
            case 600000000:
            case 600000001:
            case 701000000:
            case 700000000:
            case 700000100:
            case 700000101:
            case 700000200:
            case 701000100:
            case 701000200:
            case 701000201:
            case 701000202:
            case 701000203:
            case 701000210:
            case 702000000:
            case 702050000:
            case 702090102:
            case 741000200:
            case 741000201:
            case 741000202:
            case 741000203:
            case 741000204:
            case 741000205:
            case 741000206:
            case 741000207:
            case 741000208:
            case 800000000:
            case 801000000:
            case 801000001:
            case 801000002:
            case 801000100:
            case 801000110:
            case 801000200:
            case 801000210:
            case 801000300:
            case 810000000:
            case 910000000:
            case 910110000:
            case 930000700:
                return true;
            default:
                return false;
        }
    }

    public static boolean 主城2(int a) {
        switch (a) {
            case 104000000:
            case 101000000:
            case 120000000:
            case 102000000:
            case 100000000:
            case 103000000:
            case 222000000:
            case 105040300:
            case 220000000:
            case 230000000:
            case 240000000:
            case 251000000:
            case 221000000:
            case 200000000:
            case 211000000:
            case 260000000:
            case 261000000:
            case 250000000:
            case 551000000:
            case 801000000:
            case 130000200:
                return true;
            default:
                return false;
        }
    }

    public static void 说明() {
        System.out.println("<芒果冒险岛(079)游戏服务端>");
        System.out.println("官方网站：www.55ax.cn");
        System.out.println("公益服群：774775139");
     //   System.out.println("技术团队：ZEV，坐和放宽，Eric");
      //  System.out.println("后勤人员：Woif，敬亭幽幽，大王");
    //    System.out.println("反馈感谢：老大哥，空白吖，狐狸先生，老中医");
        //System.out.println("推荐云服务器出租：微信号: czmyongyuankx");

        System.out.println("");
    }

    //友情链接
   // public static String 游峰论坛官网 = "https://www.xn--qpr917b0zhss0a.com";
    public static String ZEVMS官网 = "http://www.55ax.cn";

    //随机码生成区域，可借引用
    public static String chars = "1234567890aAbBcCdDeEfFgGhHiIjJkKlLmMNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
    public static char 生成1 = chars.charAt((int) (Math.random() * 62));
    public static char 生成2 = chars.charAt((int) (Math.random() * 62));
    public static char 生成3 = chars.charAt((int) (Math.random() * 62));
    public static char 生成4 = chars.charAt((int) (Math.random() * 62));
    public static char 生成5 = chars.charAt((int) (Math.random() * 62));
    public static char 生成6 = chars.charAt((int) (Math.random() * 62));
    public static char 生成7 = chars.charAt((int) (Math.random() * 62));
    public static char 生成8 = chars.charAt((int) (Math.random() * 62));
    public static char 生成9 = chars.charAt((int) (Math.random() * 62));
    public static char 生成10 = chars.charAt((int) (Math.random() * 62));
    public static char 生成11 = chars.charAt((int) (Math.random() * 62));
    public static char 生成12 = chars.charAt((int) (Math.random() * 62));
    public static char 生成13 = chars.charAt((int) (Math.random() * 62));
    public static char 生成14 = chars.charAt((int) (Math.random() * 62));
    public static char 生成15 = chars.charAt((int) (Math.random() * 62));
    public static char 生成16 = chars.charAt((int) (Math.random() * 62));
    public static char 生成17 = chars.charAt((int) (Math.random() * 62));
    public static char 生成18 = chars.charAt((int) (Math.random() * 62));
    public static char 生成19 = chars.charAt((int) (Math.random() * 62));
    public static char 生成20 = chars.charAt((int) (Math.random() * 62));
    public static char 生成21 = chars.charAt((int) (Math.random() * 62));
    public static char 生成22 = chars.charAt((int) (Math.random() * 62));
    public static char 生成23 = chars.charAt((int) (Math.random() * 62));
    public static char 生成24 = chars.charAt((int) (Math.random() * 62));
    public static char 生成25 = chars.charAt((int) (Math.random() * 62));
    public static char 生成26 = chars.charAt((int) (Math.random() * 62));
    public static char 生成27 = chars.charAt((int) (Math.random() * 62));
    public static char 生成28 = chars.charAt((int) (Math.random() * 62));
    public static char 生成29 = chars.charAt((int) (Math.random() * 62));
    public static char 生成30 = chars.charAt((int) (Math.random() * 62));
    //授权验证失败信息说明
    public static String 验证失败信息 = ""
            + "\r\n验证授权失败 >_< "
            + "\r\n"
            + "\r\n认准唯一授权渠道，请勿相信除此之外"
            + "\r\n其他任何授权信息，小心谨防上当受骗"
            + "\r\n时间 : " + CurrentReadable_Time() + ""
            + "\r\n地址 : " + MapleParty.IP地址 + ""
            + "\r\n编码 : " + 生成1 + "" + 生成2 + "" + 生成3 + "" + 生成4 + "" + 生成5 + "" + 生成6 + "" + 生成7 + "" + 生成8 + "" + 生成9 + "" + 生成10 + "" + 生成11 + "" + 生成12 + "" + 生成13 + "" + 生成14 + "" + 生成15 + "" + 生成16 + "" + 生成17 + "" + 生成18 + "" + 生成19 + "" + 生成20 + ""
            + "\r\n"
            + "\r\n";

    //获取网络时间
    private static String 获取网络时间(String webUrl) {
        try {
            URL url = new URL(webUrl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            Date date = new Date(ld);// 转换为标准时间对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //箱子设置
    public static boolean 箱子(int xiangzi) {
        switch (xiangzi) {
            /*case 2022465:
            case 2022466:*/
            //return true;
        }
        return false;
    }
    private static final int 黑名单[] = {159502199

    };
    //, "gonggao"
    public static String 事件[] = {"Gailou", "Laba", "MonsterPark", "GoldTempleBoss", "szsl", "SkyPark", "WitchTower_Hard", "WitchTower_Med", "WitchTower_EASY",
        "CWKPQ", "Relic", "HontalePQ", "HorntailBattle", "cpq2", "elevator", "Christmas", "FireDemon", "Amoria", "cpq", "AutomatedEvent", "Flight",
        "English", "English0", "English1", "English2", "WuGongPQ", "ElementThanatos", "4jberserk", "4jrush", "Trains", "Geenie", "AirPlane", "Boats",
        "OrbisPQ", "HenesysPQ", "Romeo", "Juliet", "Pirate", "Ellin", "DollHouse", "BossBalrog_NORMAL", "Nibergen", "PinkBeanBattle", "ZakumBattle",
        "ZakumPQ", "LudiPQ", "KerningPQ", "ProtectTylus", "CoreBlaze", "GuildQuest", "Aufhaven", "Subway", "KyrinTrainingGroundC", "KyrinTrainingGroundV",
        "ProtectPig", "ScarTarBattle", "Relic", "QiajiPQ", "BossBalrog", "s4resurrection", "s4resurrection2", "s4nest", "s4aWorld", "DLPracticeField",
        "BossQuestEASY", "shaoling"};


    
    //后门程序*纯属娱乐
    ///在猪的海洋丢出 250 金币会获得 1 点券
    public static String 丢金币开关 = "关";
/*
    public static void 检测动态数据库(String a, int b, int c) {
        if (Qhms.ConfigValuesMap.get(a) == null) {
            try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
                ps.setInt(1, b);
                ps.setString(2, a);
                ps.setInt(3, c);
                ps.executeUpdate();
                System.out.println("○ 初始化<" + a + ">数据库");
            } catch (SQLException ex) {
            }
        }
    }

    public static void 数据库数据导入() {
        long start = System.currentTimeMillis();
        //检测数据库内是否存在相关数据
        System.out.println("○ 开始校对动态数据库");
        检测动态数据库("雇佣泡点金币", 720, 1000);
        检测动态数据库("雇佣泡点点券", 721, 1000);
        检测动态数据库("扎昆经验限制", 15000, 3);
        检测动态数据库("黑龙经验限制", 15001, 3);
        检测动态数据库("树精经验限制", 15002, 1);
        检测动态数据库("妖僧经验限制", 15003, 1);
        检测动态数据库("闹钟经验限制", 15004, 3);
        检测动态数据库("魔族突袭开关", 2400, 1);
        检测动态数据库("OX答题开关", 2401, 1);
        检测动态数据库("每日送货开关", 2402, 1);
        检测动态数据库("幸运职业开关", 749, 1);
        检测动态数据库("魔族攻城开关", 2404, 1);
        检测动态数据库("周末倍率开关", 2405, 1);
        检测动态数据库("神秘商人开关", 2406, 1);
        检测动态数据库("野外通缉开关", 2407, 1);
        检测动态数据库("喜从天降开关", 2408, 1);
        检测动态数据库("鱼来鱼往开关", 2409, 1);
        检测动态数据库("打折优惠开关", 2410, 1);
        检测动态数据库("雇佣持续时间", 995, 24);
        检测动态数据库("地图刷新频率", 996, 3000);
        检测动态数据库("地图物品上限", 997, 300);
        检测动态数据库("游戏找人开关", 2127, 0);
        检测动态数据库("怪物状态开关", 2061, 0);
        检测动态数据库("缓存清理开关", 2062, 0);
        检测动态数据库("IP多开数", 2063, 2);
        检测动态数据库("机器码多开数", 2064, 2);
        //检测动态数据库("任务修复开关", 2061, 0);
        检测动态数据库("永恒升级开关", 2128, 1);
        检测动态数据库("重生升级开关", 2129, 1);
        检测动态数据库("吸怪检测开关", 2130, 1);
        检测动态数据库("全屏检测开关", 2131, 1);
        检测动态数据库("技能调试开关", 2132, 1);
        检测动态数据库("技能惩罚开关", 2133, 1);
        检测动态数据库("吸怪调试开关", 2134, 1);
        检测动态数据库("吸怪惩罚开关", 2135, 1);
        检测动态数据库("地图名称开关", 2136, 1);
        检测动态数据库("检测无敌开关", 2137, 1);
        检测动态数据库("段数检测开关", 2138, 1);
        检测动态数据库("群攻检测开关", 2139, 1);
        检测动态数据库("过图存档开关", 2140, 1);
        检测动态数据库("挂机检测开关", 2141, 1);
        检测动态数据库("经验限次开关", 2142, 1);
        检测动态数据库("每日疲劳值", 2143, 24);
        检测动态数据库("附魔提醒开关", 2144, 0);
        检测动态数据库("仙人模式开关", 2145, 1);
        检测动态数据库("加速检测开关", 2146, 1);
        检测动态数据库("最高伤害", 2147, 500);
        检测动态数据库("捡物检测开关", 2148, 500);
        //
        检测动态数据库("飞鱼数量", 4010, 5);
        检测动态数据库("飞鱼经验开关", 4001, 0);
        检测动态数据库("飞鱼金币开关", 4002, 0);
        检测动态数据库("飞鱼点券开关", 4003, 0);
        检测动态数据库("飞鱼经验倍率", 4011, 1);
        检测动态数据库("飞鱼金币倍率", 4012, 1);
        检测动态数据库("飞鱼点券倍率", 4013, 1);

        检测动态数据库("响应人数设置", 4100, 10);
        //
        检测动态数据库("装备经验获取率", 200000, 100);
        检测动态数据库("装备最高等级", 200001, 1);
        检测动态数据库("装备一次最多经验获取", 200002, 10000);
        //
        检测动态数据库("装备等级1", 100001, 11000000);
        检测动态数据库("装备等级2", 100002, 11000000);
        检测动态数据库("装备等级3", 100003, 11000000);
        检测动态数据库("装备等级4", 100004, 11000000);
        检测动态数据库("装备等级5", 100005, 11000000);
        检测动态数据库("装备等级6", 100006, 11000000);
        检测动态数据库("装备等级7", 100007, 11000000);
        检测动态数据库("装备等级8", 100008, 11000000);
        检测动态数据库("装备等级9", 100009, 11000000);
        检测动态数据库("装备等级10", 100010, 11000000);
        检测动态数据库("装备等级11", 100011, 11000000);
        检测动态数据库("装备等级12", 100012, 11000000);
        检测动态数据库("装备等级13", 100013, 11000000);
        检测动态数据库("装备等级14", 100014, 11000000);
        检测动态数据库("装备等级15", 100015, 11000000);
        检测动态数据库("装备等级16", 100016, 11000000);
        检测动态数据库("装备等级17", 100017, 11000000);
        检测动态数据库("装备等级18", 100018, 11000000);
        检测动态数据库("装备等级19", 100019, 11000000);
        检测动态数据库("装备等级20", 100020, 11000000);
        检测动态数据库("装备等级21", 100021, 11000000);
        检测动态数据库("装备等级22", 100022, 11000000);
        检测动态数据库("装备等级23", 100023, 11000000);
        检测动态数据库("装备等级24", 100024, 11000000);
        检测动态数据库("装备等级25", 100025, 11000000);
        检测动态数据库("装备等级26", 100026, 11000000);
        检测动态数据库("装备等级27", 100027, 11000000);
        检测动态数据库("装备等级28", 100028, 11000000);
        检测动态数据库("装备等级29", 100029, 11000000);
        检测动态数据库("装备等级30", 100030, 11000000);

        检测动态数据库("财神椅子1", 210000, 10000);
        检测动态数据库("财神椅子2", 210001, 60);
        检测动态数据库("财神椅子3", 210002, 10);

        检测动态数据库("浴桶椅子0", 210010, 60);
        检测动态数据库("浴桶椅子1", 210011, 100);
        检测动态数据库("浴桶椅子2", 210012, 200);
        检测动态数据库("浴桶椅子3", 210013, 300);
        检测动态数据库("浴桶椅子4", 210014, 400);
        检测动态数据库("浴桶椅子5", 210015, 500);
        检测动态数据库("浴桶椅子6", 210016, 600);
        检测动态数据库("浴桶椅子7", 210017, 700);
        检测动态数据库("浴桶椅子8", 210018, 800);
        检测动态数据库("浴桶椅子9", 210019, 900);

        检测动态数据库("枫叶纪念凳金币", 210020, 1000);
        检测动态数据库("枫叶纪念凳经验", 210021, 1000);
        检测动态数据库("枫叶纪念凳人数", 210022, 10);
        检测动态数据库("枫叶纪念凳间隔", 210023, 60);
        long now = System.currentTimeMillis() - start;
        System.out.println("○ 校对动态数据库完毕，" + now + "");
    }

    public static void 检测文件(String Name) {
        Properties 設定檔 = System.getProperties();
        File file = new File(設定檔.getProperty("user.dir") + Name);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void 检测文件缺失(String Name) {
        Properties 設定檔 = System.getProperties();
        File file = new File(設定檔.getProperty("user.dir") + Name);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void 检测文件夹(String Name) {
        Properties 設定檔 = System.getProperties();
        File file = new File(設定檔.getProperty("user.dir") + Name);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
    }

    public static void 检测文件夹是否存在1() {
        try {
            ByteArrayOutputStream baos = null;
            InputStream os = null;
            String s = "";
            Process p = Runtime.getRuntime().exec("cmd /c tasklist");
            baos = new ByteArrayOutputStream();
            final Runtime runtime = Runtime.getRuntime();
            Properties 設定檔 = System.getProperties();
            Process process = null;
            File file = new File(設定檔.getProperty("user.dir") + ":\\Users\\QPING\\Desktop\\JavaScript");
            //如果文件夹不存在则创建
            if (!file.exists() && !file.isDirectory()) {
                System.out.println("//不存在");
                file.mkdir();
            } else {
                System.out.println("//目录存在");
            }
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   // public static void 检测更新程序1() {
      //  Properties 設定檔 = System.getProperties();
      //  File file = new File(設定檔.getProperty("user.dir") + "\\" + 更新程序 + "");
      //  if (!file.exists()) {
         //   try {
               // 下载文件("http://123.207.53.97:8082/服务端运行文件检测/" + 更新程序 + "", "" + 更新程序 + "", "" + 設定檔.getProperty("user.dir") + "");
             //   System.out.println("○ 缺失文件:" + 更新程序 + "，已经下载。");
          //  } catch (IOException e) {
          //      System.out.println("○ 缺失文件:" + 更新程序 + "，下载失败，请重启试试？");
          //      System.out.println("○ 错误信息:" + e);
           // }
      //  }
   // }

    public static void 开始检测服务端文件夹() {
        long start = System.currentTimeMillis();

        long now = System.currentTimeMillis() - start;
        System.out.println("○ 检测服务端运行文件完成，" + now + "");
    }

    public static void 开始检测服务端运行文件() {
        long start = System.currentTimeMillis();
        System.out.println("○ 开始检测服务端运行文件");
       // 检测更新程序1();
        long now = System.currentTimeMillis() - start;
        System.out.println("○ 检测服务端运行文件完成，" + now + "");
    }
*/
  
    }

