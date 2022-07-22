package server;

import client.MapleCharacter;
import client.SkillFactory;
import client.inventory.OnlyID;
import constants.GameConstants;
import constants.PiPiConfig;
import constants.ServerConfig;
import constants.WorldConstants;
import database.DBConPool;
import database.DatabaseConnection;
import gui.Qhms;
import gui.活动倍率活动;
import gui.活动神秘商人;
//import gui.活动喜从天降;
import gui.活动魔族入侵;
import gui.活动魔族攻城;
import static gui.活动野外通缉.随机通缉;
import handling.channel.ChannelServer;
import handling.channel.MapleGuildRanking;
import handling.login.LoginServer;
import handling.cashshop.CashShopServer;
import handling.login.LoginInformationProvider;
import handling.world.MapleParty;
import handling.world.World;
import java.sql.SQLException;
import handling.world.family.MapleFamilyBuff;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import javax.swing.JOptionPane;
import static server.MapleCarnivalChallenge.getJobNameById;
import server.Timer.*;
import server.events.MapleOxQuizFactory;
import server.life.MapleLifeFactory;
import server.life.PlayerNPC;
import server.maps.MapleMap;
import server.maps.MapleMapFactory;
import server.quest.MapleQuest;
import tools.FileoutputUtil;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;
import tools.StringUtil;
import tools.packet.UIPacket;

public class Start {
    
    private static ServerSocket srvSocket = null; //服务线程，用以控制服务器只启动一个实例
    private static int srvPort = 6350;     //控制启动唯一实例的端口号，这个端口如果保存在配置文件中会更灵活
    public static long startTime = System.currentTimeMillis();
    public static final Start instance = new Start();
    private static int maxUsers = 0;
    private int rankTime;
    private boolean ivCheck;
    public static boolean  是否控制台启动 = false;
    public static Map<String, Integer> ConfigValuesMap = new HashMap<>();
    public static Map<String, Integer> 地图吸怪检测 = new HashMap<>();

    //private static void resetAllLoginState() {
    public final static void main(final String args[]) {
        if(是否控制台启动 == true){
        String name = null;
        int id = 0, vip = 0, size = 0;

        try (Connection con = DBConPool.getInstance().getDataSource().getConnection(); PreparedStatement ps = con.prepareStatement("UPDATE accounts SET loggedin = 0")) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", ex);
            throw new RuntimeException("【錯誤】 請確認資料庫是否正確連接");
        }
   // }

    
            GetConfigValues();
        System.out.println("○ 【正在加载】 -> 【冒险岛模擬器】");
        System.out.println("○ 【正在加载】 -> 【版本】 v079.VIP.13");
        
        System.out.println("○ 【正在加载】 -> 正在读取授权码请稍后...");
       /* if (!AuthenticationEncryption.getAuthorizedFeedBack()) {
        JOptionPane.showMessageDialog(null, "此服务器未授权，请联系作者授权！");
          System.exit(0);
         }*/
        
        long startQuestTime = System.currentTimeMillis();
        //resetAllLoginState();
        
        //加载配置 
        System.out.println("\r\n○ 【正在加载】 -> 开始加载各项游戏数据");

        if (WorldConstants.ADMIN_ONLY) {
            System.out.println("○ 【正在加载】 -> 只允许管理员登录模式开关: 【开启】");
        } else {
            System.out.println("○ 【正在加载】 -> 只允许管理员登录模式开关: 【关闭】");
        }

        if (ServerConfig.AUTO_REGISTER) {
            System.out.println("○ 【正在加载】 -> 账号自动注册模式开关: 【开启】");
        } else {
            System.out.println("○ 【正在加载】 -> 账号自动注册模式开关: 【关闭】");
        }

        if (!WorldConstants.GMITEMS) {
            System.out.println("○ 【正在加载】 -> 允许玩家使用管理员物品开关: 【开启】");
        } else {
            System.out.println("○ 【正在加载】 -> 允许玩家使用管理员物品开关: 【关闭】");
        }
        

        //加载游戏设定 
        ServerConfig.loadSetting();
        World.init();
        //加载计时系统
        WorldTimer.getInstance().start();
        EtcTimer.getInstance().start();
        MapTimer.getInstance().start();
        MobTimer.getInstance().start();
        CloneTimer.getInstance().start();
        EventTimer.getInstance().start();
        BuffTimer.getInstance().start();
        PingTimer.getInstance().start();
        // 加载WZ內禁止使用的名稱 
        LoginInformationProvider.getInstance();
        //加载钓鱼系统 
        FishingRewardFactory.getInstance();
       // System.out.println("○ 加载钓鱼物品:  完成");
        //加载任務
        MapleQuest.initQuests();
        MapleLifeFactory.loadQuestCounts();
        MapleOxQuizFactory.getInstance().initialize();
        //加载物品信息 
        MapleItemInformationProvider.getInstance().load();
        //MapleItemInformationProvider.loadFaceHair(); //载入脸型发型信息
        PredictCardFactory.getInstance().initialize();
        CashItemFactory.getInstance().initialize();
        //加载随机奖励 
        RandomRewards.getInstance();
        //加载技能信息
        SkillFactory.LoadSkillInformaion();
        //加载怪物技能 
        MapleCarnivalFactory.getInstance();
        
        System.out.println("○ 【正在加载】 -> 游戏商品数量: " + 服务器游戏商品() + " 个");
        System.out.println("○ 【正在加载】 -> 商城商品数量: " + 服务器商城商品() + " 个");
        System.out.println("○ 【正在加载】 -> 玩家账号数量: " + 服务器账号() + " 个");
        System.out.println("○ 【正在加载】 -> 玩家角色数量: " + 服务器角色() + " 个");
        System.out.println("○ 【正在加载】 -> 玩家道具数量: " + 服务器道具() + " 个");
        System.out.println("○ 【正在加载】 -> 玩家技能数量: " + 服务器技能() + " 个");
        System.out.println("○ 【正在加载】 -> 自动存档线程");
        System.out.println("○ 【正在加载】 -> 启动记录在线时长线程");
        System.out.println("○ 【正在加载】 -> 启动服务端内存回收线程");
        System.out.println("○ 【正在加载】 -> 启动服务端地图回收线程");
        System.out.println("○ 【正在加载】 -> 处理怪物重生、CD、宠物、坐骑");
        System.out.println("○ 【正在加载】 -> 自定义玩家NPC");
        System.out.println("○ 【正在加载】 -> 检测游戏复制道具系统");
        
        //加载排行 
        MapleGuildRanking.getInstance().getGuildRank();
        MapleGuildRanking.getInstance().getJobRank(1);
        MapleGuildRanking.getInstance().getJobRank(2);
        MapleGuildRanking.getInstance().getJobRank(3);
        MapleGuildRanking.getInstance().getJobRank(4);
        MapleGuildRanking.getInstance().getJobRank(5);
        MapleGuildRanking.getInstance().getJobRank(6);
        //加载家族Buff 
        MapleFamilyBuff.getBuffEntry();
        
         System.out.println("\r\n○ 【正在加载】 -> 游戏倍率信息");
        //显示经验倍率，不能超过100倍
        if (Integer.parseInt(ServerProperties.getProperty("QhMs.expRate")) > 100) {
            System.out.println("○ 游戏经验倍率: 100 倍 (上限)");
        } else {
            System.out.println("○  游戏经验倍率: " + Integer.parseInt(ServerProperties.getProperty("QhMs.expRate")) + " 倍 ");
        }
        //显示物品倍率，不能超过100倍
        if (Integer.parseInt(ServerProperties.getProperty("QhMs.dropRate")) > 100) {
            System.out.println("○ 游戏物品倍率：100 倍 (上限)");
        } else {
            System.out.println("○  游戏物品倍率：" + Integer.parseInt(ServerProperties.getProperty("QhMs.dropRate")) + " 倍 ");
        }
        //显示金币倍率，不能超过100倍
        if (Integer.parseInt(ServerProperties.getProperty("QhMs.mesoRate")) > 100) {
            System.out.println("○ 游戏金币倍率：100 倍 (上限)");
        } else {
            System.out.println("○  游戏金币倍率：" + Integer.parseInt(ServerProperties.getProperty("QhMs.mesoRate")) + " 倍 ");
        }
        
        System.out.println("\r\n○ 【开始加载】 -> 游戏端口配置");
        
        //加载游戏登录端口
        LoginServer.setup();
        ///加载频道端口
        ChannelServer.startAllChannels();
        //加载商城端口
        CashShopServer.setup();
        //加载封号系统
        CheatTimer.getInstance().register(AutobanManager.getInstance(), 60000);
        //加载关闭服务器线程 
        Runtime.getRuntime().addShutdownHook(new Thread(ShutdownServer.getInstance()));
        // 載入速度排行 
        SpeedRunner.getInstance().loadSpeedRuns();
        //加载怪物重生、CD、宠物、坐骑 
        World.registerRespawn();
        //加载玩家NPC 
        PlayerNPC.loadAll();// touch - so we see database problems early...
        /* 設定finishedShutdown為false */
        LoginServer.setOn();
        //加载自定义 NPC、怪物
        MapleMapFactory.loadCustomLife();
        
   
        
        //读取技能范围检测();
        System.out.println("○ -> 开始加载地图吸怪检测");
        读取地图吸怪检测();
        System.out.println("○ -> 启动角色福利泡点线程");
        福利泡点(2);//泡点时间3分钟一次
        自动存档(3);
        在线时间(1);
        回收内存(360);
        回收地图(480);
        在线统计(30);
        记录在线时间(1);
        
        
        World.isShutDown = false;
        OnlyID.getInstance(); 
        System.out.println("○ ->  【所有游戏数据加载完毕】 ");
        System.out.println("○ ->  【服务端已启动完毕，耗时 " + ((System.currentTimeMillis() - startQuestTime) / 1000) + " 秒】");
        System.out.println("○ ->  【贴心提示】运行中请勿直接关闭本控制台，使用下方关闭服务器按钮来关闭服务端，否则回档自负\r\n" );        
        //System.out.println("○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○ " );
        
    }
    }

 public static void GetConfigValues() {
        //动态数据库连接
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement("SELECT name, val FROM ConfigValues")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    int val = rs.getInt("val");
                    ConfigValuesMap.put(name, val);
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("读取动态数据库出错：" + ex.getMessage());
        }
    } 
 
 
  /**
     * * <1分钟执行一次>
     */
    private static int 记录在线时间 = 0;
    //private static int 重置数据库 = 0;
   // private static Boolean 每日送货 = false;
    private static Boolean 喜从天降 = false;
    //private static Boolean 鱼来鱼往 = false;
    private static int 初始通缉令 = 0;
    private static Boolean 倍率活动 = false;
    private static Boolean 幸运职业 = false;
    //private static Boolean 启动OX答题线程 = false;
    private static Boolean 魔族入侵 = false;
    private static Boolean isClearBossLog = false;
    private static Boolean 魔族攻城 = false;
    private static int Z = 0;
    
     public static void 记录在线时间(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (记录在线时间 > 0) {
                    MapleParty.服务端运行时长 += 1;
                    Calendar calendar = Calendar.getInstance();
                    int 时 = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                    int 分 = Calendar.getInstance().get(Calendar.MINUTE);
                    int 星期 = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                    /**
                     * <凌晨清理每日信息>
                     */
                    if (时 == 0 && isClearBossLog == false) {
                        System.err.println("[服务端]" + CurrentReadable_Time() + " : ------------------------------");
                        System.err.println("[服务端]" + CurrentReadable_Time() + " : 服务端开始清理每日信息 √");
                        //通信("服务端开始清理每日信息");
                        try {
                            //重置今日在线时间
                            try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characters SET todayOnlineTime = 0")) {
                                ps.executeUpdate();
                                System.err.println("[服务端]" + CurrentReadable_Time() + " : 清理今日在线时间完成 √");
                            }
                            //重置每日bosslog信息
                            try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE bosslog SET characterid = 0")) {
                                ps.executeUpdate();
                                System.err.println("[服务端]" + CurrentReadable_Time() + " : 清理今日log信息完成 √");
                            }
                            //重置每日bosslog信息
                           // try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE accounts SET df_tired_point = 0")) {
                           //     ps.executeUpdate();
                           //     System.err.println("[服务端]" + CurrentReadable_Time() + " : 清理今日疲劳完成 √");
                          //  }
                            System.err.println("[服务端]" + CurrentReadable_Time() + " : 服务端清理每日信息完成 √");
                            //通信("服务端清理每日信息完成");
                            System.err.println("[服务端]" + CurrentReadable_Time() + " : ------------------------------");
                        } catch (SQLException ex) {
                            System.err.println("[服务端]" + CurrentReadable_Time() + " : 服务端处理每日数据出错 × " + ex.getMessage());
                            //通信("服务端处理每日数据出错，你可能要手动清理一下。");
                            System.err.println("[服务端]" + CurrentReadable_Time() + " : ------------------------------");
                        }
                        isClearBossLog = true;
                        //每日送货 = false;
                        //启动OX答题线程 = false;
                        魔族入侵 = false;
                        魔族攻城 = false;
                        喜从天降 = false;
                        //鱼来鱼往 = false;
                        //MapleParty.OX答题活动 = 0;
                        /*if (重置数据库 == 1) {
                            重置数据库();
                        } else {
                            重置数据库++;
                        }*/
                    } else if (时 == 23) {
                        isClearBossLog = false;
                    }
               
                    
                  
                   
                     // <魔族入侵>
                     
                    if (Start.ConfigValuesMap.get("魔族突袭开关") == 0) {
                        if (calendar.get(Calendar.HOUR_OF_DAY) == 22 && 魔族入侵 == false) {
                            活动魔族入侵.魔族入侵线程();
                            魔族入侵 = true;
                        }
                    }
                    
                    //  <魔族攻城>
                     
                    if (Start.ConfigValuesMap.get("魔族攻城开关") == 0) {
                        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 1) {//判断星期
                            if (时 == 21 && 分 <= 10 && 魔族攻城 == false) {
                                活动魔族攻城.魔族攻城线程();
                                魔族攻城 = true;
                            }
                        }
                    }
                    
                    // <幸运职业，天选之人-狩猎>
                    
                    if (Start.ConfigValuesMap.get("幸运职业开关") == 0) {
                        if (时 == 11 && 幸运职业 == false) {
                            幸运职业();
                            幸运职业 = true;
                        } else if (时 == 23 && 幸运职业 == true) {
                            幸运职业();
                            幸运职业 = false;
                        } else if (MapleParty.幸运职业 == 0) {
                            幸运职业();
                        }
                    }
                    
                     // <周末随机倍率活动>
                    
                    if (Start.ConfigValuesMap.get("周末倍率开关") == 0) {
                        switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
                            case 7:
                                if (时 == 0 && 倍率活动 == false) {
                                    活动倍率活动.倍率活动线程();
                                    倍率活动 = true;
                                } else if (时 == 23) {
                                    倍率活动 = false;
                                }
                                break;
                            case 1:
                                if (时 == 0 && 倍率活动 == false) {
                                    活动倍率活动.倍率活动线程();
                                    倍率活动 = true;
                                } else if (时 == 23) {
                                    倍率活动 = false;
                                }
                                break;
                            case 6:
                                if (倍率活动 == true) {
                                    倍率活动 = false;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                 
                  
                    //  <启动神秘商人>

                    if (Start.ConfigValuesMap.get("神秘商人开关") == 0) {
                        //第一次启动神秘商人
                        if (MapleParty.神秘商人线程 == 0) {
                            活动神秘商人.启动神秘商人();
                            MapleParty.神秘商人线程++;
                        }
                        //召唤神秘商人
                        if (MapleParty.神秘商人线程 > 0) {
                            if (时 == MapleParty.神秘商人时间 && MapleParty.神秘商人 == 0) {
                                活动神秘商人.召唤神秘商人();
                            }
                        }

                    }

                   //   <初始化通缉令>
                    
                    if (Start.ConfigValuesMap.get("野外通缉开关") == 0) {
                        if (初始通缉令 == 30) {
                            随机通缉();
                            初始通缉令++;
                        } else {
                            初始通缉令++;
                        }
                    }

                   
                   //   <记录在线时间>
                    
                   Z = 0;
                    for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                        for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                            if (chr == null) {
                                continue;
                            }
                            //chr.增加角色疲劳值(1);
                            Connection con = DatabaseConnection.getConnection();
                            try {
                                
                                 // <加在线>
                                 
                                try (PreparedStatement psu = con.prepareStatement("UPDATE characters SET todayOnlineTime = todayOnlineTime + ?, totalOnlineTime = totalOnlineTime + ? WHERE id = ?")) {
                                    psu.setInt(1, time);
                                    psu.setInt(2, time);
                                    psu.setInt(3, chr.getId());
                                    psu.executeUpdate();
                                    psu.close();
                                }
                                chr.getClient().sendPacket(MaplePacketCreator.enableActions());
                                if (Z == 0) {
                                    Z++;
                                    //System.err.println("[服务端]" + CurrentReadable_Time() + " : 记录在线 √");
                                }
                            } catch (SQLException ex) {
                               // System.err.println("[服务端]" + CurrentReadable_Time() + " : 记录在线 × (" + chr.getId() + ")");//+ ex.getMessage()
                                记录在线时间补救(chr.getId());
                            }

                        }
                    }
                } else {
                    记录在线时间++;
                }
            }
        }, 60 * 1000 * time
                   
                
        );
       
    }
    
       public static void 记录在线时间补救(int a) {
        Connection con = DatabaseConnection.getConnection();
        try {
            try (PreparedStatement psu = con.prepareStatement("UPDATE characters SET todayOnlineTime = todayOnlineTime + ?, totalOnlineTime = totalOnlineTime + ? WHERE id = ?")) {
                psu.setInt(1, 1);
                psu.setInt(2, 1);
                psu.setInt(3, a);
                psu.executeUpdate();
                psu.close();
            }
           // System.err.println("[服务端]" + CurrentReadable_Time() + " : 记录在线第一次补救成功 √ (" + a + ")");
        } catch (SQLException ex) {
            //System.err.println("[服务端]" + CurrentReadable_Time() + " : 记录在线第一次补救失败 × (" + a + ")" + ex.getMessage());
            记录在线时间补救2(a);
        }
    }

    public static void 记录在线时间补救2(int a) {
        Connection con = DatabaseConnection.getConnection();
        try {
            try (PreparedStatement psu = con.prepareStatement("UPDATE characters SET todayOnlineTime = todayOnlineTime + ?, totalOnlineTime = totalOnlineTime + ? WHERE id = ?")) {
                psu.setInt(1, 1);
                psu.setInt(2, 1);
                psu.setInt(3, a);
                psu.executeUpdate();
                psu.close();
            }
           // System.err.println("[服务端]" + CurrentReadable_Time() + " : 记录在线第二次补救成功 √ (" + a + ")");
        } catch (SQLException ex) {
           // System.err.println("[服务端]" + CurrentReadable_Time() + " : 记录在线第二次补救失败 × (" + a + ")" + ex.getMessage());
            记录在线时间补救3(a);
        }
    }

    public static void 记录在线时间补救3(int a) {
        Connection con = DatabaseConnection.getConnection();
        try {
            try (PreparedStatement psu = con.prepareStatement("UPDATE characters SET todayOnlineTime = todayOnlineTime + ?, totalOnlineTime = totalOnlineTime + ? WHERE id = ?")) {
                psu.setInt(1, 1);
                psu.setInt(2, 1);
                psu.setInt(3, a);
                psu.executeUpdate();
                psu.close();
            }
           // System.err.println("[服务端]" + CurrentReadable_Time() + " : 记录在线第三次补救成功 √ (" + a + ")");
        } catch (SQLException ex) {
           // System.err.println("[服务端]" + CurrentReadable_Time() + " : 记录在线第三次补救失败 × (" + a + ")" + ex.getMessage());

        }
    }
    
    public static void 幸运职业() {
        int 随机 = (int) Math.ceil(Math.random() * 18);
        if (随机 == 0) {
            随机 += 1;
        }
        switch (随机) {
            case 1:
                MapleParty.幸运职业 = 111;
                break;
            case 2:
                MapleParty.幸运职业 = 121;
                break;
            case 3:
                MapleParty.幸运职业 = 131;
                break;
            case 4:
                MapleParty.幸运职业 = 211;
                break;
            case 5:
                MapleParty.幸运职业 = 221;
                break;
            case 6:
                MapleParty.幸运职业 = 231;
                break;
            case 7:
                MapleParty.幸运职业 = 311;
                break;
            case 8:
                MapleParty.幸运职业 = 321;
                break;
            case 9:
                MapleParty.幸运职业 = 411;
                break;
            case 10:
                MapleParty.幸运职业 = 421;
                break;
            case 11:
                MapleParty.幸运职业 = 511;
                break;
            case 12:
                MapleParty.幸运职业 = 521;
                break;
            case 13:
                MapleParty.幸运职业 = 1111;
                break;

            case 14:
                MapleParty.幸运职业 = 1211;
                break;
            case 15:
                MapleParty.幸运职业 = 1311;
                break;
            case 16:
                MapleParty.幸运职业 = 1411;
                break;
            case 17:
                MapleParty.幸运职业 = 1511;
                break;
            case 18:
                MapleParty.幸运职业 = 2111;
                break;
            default:

                break;
        }
        String 信息 = "恭喜 " + getJobNameById((MapleParty.幸运职业 - 1)) + " " + getJobNameById(MapleParty.幸运职业) + " " + getJobNameById((MapleParty.幸运职业 + 1)) + " 幸运成为幸运职业，增加50%基础狩猎经验";
        System.err.println("[服务端]" + CurrentReadable_Time() + " : [幸运职业] : " + 信息);
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[幸运职业] : " + 信息));

    }
 
 

 
 public static int 福利泡点 = 0;

    public static void 福利泡点(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (福利泡点 > 0) {
                    try {
                        for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                            for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                                if (chr == null) {
                                    continue;
                                }
                                //if (MapleParty.OX答题活动 == 0) {
                                    if (chr.level > 10) {
                                        int 点券 = 0;
                                        int 经验 = 0;
                                        int 金币 = 0;
                                        int 抵用 = 0;
                                        int 豆豆 = 0;
                                        int 泡点豆豆开关 = gui.Qhms.ConfigValuesMap.get("泡点豆豆开关");
                        if (泡点豆豆开关 <= 0) {
                            int 泡点豆豆 = gui.Qhms.ConfigValuesMap.get("泡点豆豆");
                            豆豆 += 泡点豆豆;
                           //chr.gainBeans(泡点豆豆, true);//给固定豆豆
                            chr.gainBeans(豆豆);//给固定豆豆
                        }
                                        
                                        int 泡点金币开关 = gui.Qhms.ConfigValuesMap.get("泡点金币开关");
                                        if (泡点金币开关 <= 0) {
                                            int 泡点金币 = gui.Qhms.ConfigValuesMap.get("泡点金币");
                                            金币 += (chr.getLevel() * 泡点金币);
                                            chr.gainMeso((chr.getLevel() *泡点金币), true);//给金币乘当前等级
                                        }
                                        int 泡点点券开关 = gui.Qhms.ConfigValuesMap.get("泡点点券开关");
                                        if (泡点点券开关 <= 0) {
                                            int 泡点点券 = gui.Qhms.ConfigValuesMap.get("泡点点券");
                                            chr.modifyCSPoints(1, 泡点点券, true);
                                            点券 += 泡点点券;
                                        }
                                        int 泡点抵用开关 = gui.Qhms.ConfigValuesMap.get("泡点抵用开关");
                                        if (泡点抵用开关 <= 0) {
                                            int 泡点抵用 = gui.Qhms.ConfigValuesMap.get("泡点抵用");
                                            chr.modifyCSPoints(2, 泡点抵用, true);
                                            抵用 += 泡点抵用;
                                        }
                                        int 泡点经验开关 = gui.Qhms.ConfigValuesMap.get("泡点经验开关");
                                        if (泡点经验开关 <= 0) {
                                            int 泡点经验 = gui.Qhms.ConfigValuesMap.get("泡点经验");
                                            经验 += (chr.getLevel() * 泡点经验);
                                           
                                           chr.gainExp((chr.getLevel() * 经验), false, false, false);//给经验乘当前等级
                                         //chr.gainExp(经验, false, false, false);//给固定经验
                                        }
                                        //喇叭
                                        chr.getClient().getSession().write(MaplePacketCreator.serverNotice(5, "[世界泡点] ：获得 ["+点券+"] 点卷 / ["+抵用+"] 抵用卷 / [" + 经验 + "] 经验  [" +金币 + "] 金币  !"));                                        
                                        chr.getClient().sendPacket(UIPacket.getTopMsg("[" + GameConstants.冒险岛名字 + "世界泡点]:获得 " + 点券 + " 点券 / " + 抵用 + " 抵用 / " + 经验 + " 经验 / " + 金币 + " 金币 !"));//，泡点经验 + 1
                                        chr.getClient().sendPacket(MaplePacketCreator.enableActions());
                                    }
                                }
                            }
                      //  }
                       // System.err.println("[服务端]" + CurrentReadable_Time() + " : 系统正在发放世界泡点 √ ");
                    } catch (Exception e) {
                        //System.err.println("[服务端]" + CurrentReadable_Time() + " : 系统正在启用备用发放世界泡点 √ ");
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000 * 10);
                                    福利泡点();
                                } catch (InterruptedException e) {
                                }
                            }
                        }.start();
                    }
                } else {
                    福利泡点++;
                }
            }
        }, 60 * 1000 * time);
    }

    /**
     * * <备用发送福利泡点>
     */
    public static void 福利泡点() {
        try {
            for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                    if (chr == null) {
                        continue;
                    }
                    if (chr.level > 10) {
                        int 点券 = 0;
                        int 经验 = 0;
                        int 金币 = 0;
                        int 抵用 = 0;
                        int 豆豆 = 0;
                        
                        int 泡点豆豆开关 = gui.Qhms.ConfigValuesMap.get("泡点豆豆开关");
                        if (泡点豆豆开关 <= 0) {
                            int 泡点豆豆 = gui.Qhms.ConfigValuesMap.get("泡点豆豆");
                            豆豆 += 泡点豆豆;
                            //if (chr.getEquippedFuMoMap().get(34) != null) {
                            //    金币 += 泡点金币 / 100 * chr.getEquippedFuMoMap().get(34);
                           // }
                           //chr.gainMeso((chr.getLevel() *泡点豆豆), true);//给金币乘当前等级
                            //chr.gainBeans(泡点豆豆, true);//给固定豆豆
                            chr.gainBeans(豆豆);//给固定豆豆
                        }
                        
                        int 泡点金币开关 = gui.Qhms.ConfigValuesMap.get("泡点金币开关");
                        if (泡点金币开关 <= 0) {
                            int 泡点金币 = gui.Qhms.ConfigValuesMap.get("泡点金币");
                            金币 += (chr.getLevel() * 泡点金币);
                            //if (chr.getEquippedFuMoMap().get(34) != null) {
                            //    金币 += 泡点金币 / 100 * chr.getEquippedFuMoMap().get(34);
                           // }
                           chr.gainMeso((chr.getLevel() *泡点金币), true);//给金币乘当前等级
                            //chr.gainMeso(泡点金币, true);给固定金币
                        }
                        int 泡点点券开关 = gui.Qhms.ConfigValuesMap.get("泡点点券开关");
                        if (泡点点券开关 <= 0) {
                            int 泡点点券 = gui.Qhms.ConfigValuesMap.get("泡点点券");
                            chr.modifyCSPoints(1, 泡点点券, true);
                            点券 += 泡点点券;
                        }
                        int 泡点抵用开关 = gui.Qhms.ConfigValuesMap.get("泡点抵用开关");
                        if (泡点抵用开关 <= 0) {
                            int 泡点抵用 = gui.Qhms.ConfigValuesMap.get("泡点抵用");
                            chr.modifyCSPoints(2, 泡点抵用, true);
                            抵用 += 泡点抵用;
                        }
                        int 泡点经验开关 = gui.Qhms.ConfigValuesMap.get("泡点经验开关");
                        if (泡点经验开关 <= 0) {
                            int 泡点经验 = gui.Qhms.ConfigValuesMap.get("泡点经验");
                            经验 += (chr.getLevel() * 泡点经验);
                            //if (chr.getEquippedFuMoMap().get(33) != null) {
                            //    经验 += 泡点经验 / 100 * chr.getEquippedFuMoMap().get(33);
                           // }
                           chr.gainExp((chr.getLevel() * 泡点经验), true, true, false);//给经验乘当前等级
                            //chr.gainExp(泡点经验, true, true, false);//给固定经验
                        }
                        //BossRankManager3.getInstance().setLog(chr.getId(), chr.getName(), "泡点经验", (byte) 2, (byte) 1);
                        //chr.getClient().sendPacket(UIPacket.getTopMsg("[" + MapleParty.开服名字 + "泡点]:" + 点券 + " 点券 / " + 抵用 + " 抵用 / " + 经验 + " 经验 / " + 金币 + " 金币，泡点经验 + 1"));
                        //喇叭
                        chr.getClient().getSession().write(MaplePacketCreator.serverNotice(5, "[世界泡点] ：获得 ["+点券+"] 点卷 / ["+抵用+"] 抵用卷 / [" + 经验 + "] 经验  [" +金币 + "] 金币  !"));                                        
                        chr.getClient().sendPacket(UIPacket.getTopMsg("[" + GameConstants.冒险岛名字 + "世界泡点]:获得 " + 点券 + " 点券 / " + 抵用 + " 抵用 / " + 经验 + " 经验 / " + 金币 + " 金币 ! "));//，泡点经验 + 1
 
                        chr.getClient().sendPacket(MaplePacketCreator.enableActions());
                    }
                }
            }
            //System.err.println("[服务端]" + CurrentReadable_Time() + " : 系统正在发放世界泡点 ↑√ ");
        } catch (Exception e) {
            //System.err.println("[服务端]" + CurrentReadable_Time() + " : 系统正在发放世界泡点 ↑× ");
        }
    }



 
    
     public static void 自动存档(int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                int ppl = 0;
                try {
                    for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                        for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                            if (chr == null) {
                                continue;
                            }
                            ppl++;
                            chr.saveToDB(false, false);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("自动存档出错：" + e);
                }
                System.out.println("○ 【自动存档】 " + CurrentReadable_Time() + " 已经将 " + ppl + " 个玩家保存到数据中.");
            }
        }, 60 * 1000 * time);
    }
    
     
      /**
     * * <30分钟强制回收一次内存>
     */
    private static int 回收内存 = 0;

    public static void 回收内存(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                if (回收内存 > 0) {
                    System.gc();
                    System.err.println("○【内存回收】 " + CurrentReadable_Time() + " : 回收服务端内存 √");
                } else {
                    回收内存++;
                }
            }
        }, 60 * 1000 * time);
       }
    /**
     * * <30分钟强制回收一次地图>
     */
    
        public static void 回收地图(int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {

            public void run() {
                for (ChannelServer cserv : ChannelServer.getAllInstances()) {
                    for (MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                        for (int i = 0; i < 6; i++) {
                            int mapidA = 100000000 + (i + 1000000 - 2000000);
                            MapleCharacter player = chr;
                            if (i == 6) {
                                mapidA = 910000000;
                            }
                            int mapid = mapidA;
                            MapleMap map = player.getClient().getChannelServer().getMapFactory().getMap(mapid);
                            if (player.getClient().getChannelServer().getMapFactory().destroyMap(mapid)) {
                                MapleMap newMap = player.getClient().getChannelServer().getMapFactory().getMap(mapid);
                                MaplePortal newPor = newMap.getPortal(0);
                                LinkedHashSet<MapleCharacter> mcs = new LinkedHashSet<MapleCharacter>(map.getCharacters()); // do NOT remove, fixing ConcurrentModificationEx.
                                outerLoop:
                                for (MapleCharacter m : mcs) {
                                    for (int x = 0; x < 5; x++) {
                                        try {
                                            m.changeMap(newMap, newPor);
                                            continue outerLoop;
                                        } catch (Throwable t) {
                                        System.err.println("○【地图回收】 " + CurrentReadable_Time() + " : 系统正在回收地图 √");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }, 60000 * time);
    }
        
         public static void 读取地图吸怪检测() {
        //动态数据库连接
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement("SELECT name, val FROM 地图吸怪检测")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    int val = rs.getInt("val");
                    地图吸怪检测.put(name, val);
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("读取吸怪检测错误：" + ex.getMessage());
        }
        
        
    }
     
      public static int 服务器角色() {
        int p = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id as DATA FROM characters WHERE id >=0");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    p += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("服务器角色？");
        }
        return p;
    }

    public static int 服务器账号() {
        int p = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id as DATA FROM accounts WHERE id >=0");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    p += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("服务器账号？");
        }
        return p;
    }

    public static int 服务器技能() {
        int p = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id as DATA FROM skills ");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    p += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("服务器技能？");
        }
        return p;
    }

    public static int 服务器道具() {
        int p = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT inventoryitemid as DATA FROM inventoryitems WHERE inventoryitemid >=0");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    p += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("服务器道具？");
        }
        return p;
    }

    public static int 服务器商城商品() {
        int p = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT serial as DATA FROM cashshop_modified_items WHERE serial >=0");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    p += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("服务器商城商品？");
        }
        return p;
    }  
   
        public static int 服务器游戏商品() {
        int p = 0;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT shopitemid as DATA FROM shopitems WHERE shopitemid >=0");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    p += 1;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("服务器道具游戏商品？");
        }
        return p;
    }
        
        
         /*
     * 统计在线人数
     */
    public static void 在线统计(int time) {
        System.out.println("服务端启用在线统计." + time + "分钟统计一次在线的人数信息.");
        WorldTimer.getInstance().register(new Runnable() {

            @Override
            public void run() {
                Map<Integer, Integer> connected = World.getConnected();
                StringBuilder conStr = new StringBuilder(FileoutputUtil.CurrentReadable_Time() + " 在线人数: ");
                for (int i : connected.keySet()) {
                    if (i == 0) {
                        int users = connected.get(i);
                        conStr.append(StringUtil.getRightPaddedStr(String.valueOf(users), ' ', 3));
                        if (users > maxUsers) {
                            maxUsers = users;
                        }
                        conStr.append(" 最高在线: ");
                        conStr.append(maxUsers);
                        break;
                    }
                }
                System.out.println("在线统计" + conStr.toString());
                if (Start.maxUsers > 0) {
                    FileoutputUtil.log("在线统计.txt", conStr.toString() + "\r\n");
                }
            }
        }, 1000 * 60 * time);
    }

    public static void 在线时间(int time) {
        WorldTimer.getInstance().register(new Runnable() {

            @Override
            public void run() {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                if (hour == 0 && minute == 0) {
                    try {
                        Connection con = DatabaseConnection.getConnection();
                        try (PreparedStatement ps = con.prepareStatement("UPDATE accounts_info SET gamePoints = ?, updateTime = CURRENT_TIMESTAMP()")) {
                            ps.setInt(1, 0);
                            ps.executeUpdate();
                            ps.close();
                        }
                    } catch (SQLException Ex) {
                        System.err.println("更新角色帐号的在线时间出现错误 - 数据库更新失败." + Ex);
                    }
                }
                try {
                    for (ChannelServer chan : ChannelServer.getAllInstances()) {
                        for (MapleCharacter chr : chan.getPlayerStorage().getAllCharacters()) {
                            if (chr == null) {
                                continue;
                            }
                            if (hour == 0 && minute == 0) {
                                chr.set在线时间(0);
                                continue;
                            }
                            chr.gainGamePoints(1);
                            if (chr.get在线时间() < 5) {//chr.getGamePoints() < 5
                                chr.resetGamePointsPS();
                                chr.resetGamePointsPD();
                            }
                        }
                    }
                } catch (Exception e) {
                    System.err.println("在线时间出错:" + e);
                }
            }
        }, 60000 * time);
    }
        
        
        
         public static void startCheck() {
        System.out.println("服务端启用检测.30秒检测一次角色是否与登录器断开连接.");
        WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                for (ChannelServer cserv_ : ChannelServer.getAllInstances()) {
                    for (MapleCharacter chr : cserv_.getPlayerStorage().getAllCharacters()) {
                        if (chr != null) {
                            chr.startCheck();
                        }
                    }
                }
            }
        }, 30 * 1000);
    }

    public static class Shutdown implements Runnable {

        @Override
        public void run() {
            new Thread(ShutdownServer.getInstance()).start();
        }
    }

    //设置服务端单一实例.启动一个ServerSocket，用以控制只启动一个实例  禁止服务端多开
    //设置srv端口 = 6350//用于控制启动唯一实例的端口号，这个端口如果保存在配置文件中会更灵活
    protected static void checkSingleInstance() {
        try {
            srvSocket = new ServerSocket(srvPort);
        } catch (IOException ex) {
            if (ex.getMessage().contains("地址已经在使用:JVM_Bind")) {
                System.out.println("在一台主机上同时只能启动一个进程(Only one instance allowed)。");
            }
            System.exit(0);
        }
    } 
        
    
}
