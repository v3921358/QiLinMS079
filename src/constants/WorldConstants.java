package constants;

import server.ServerProperties;

public class WorldConstants {

    public static Option WORLD = WorldOption.绿水灵;
    public static boolean ADMIN_ONLY = true;
    public static boolean JZSD = false;
    public static boolean WUYANCHI = true;
    public static boolean LieDetector = false;
    public static boolean DropItem = true;
    public static int USER_LIMIT = 10000;
    public static int MAX_CHAR_VIEW = 20;
    public static boolean GMITEMS = false;
    public static boolean CS_ENABLE = true;
    public static int EXP_RATE = 1;
    public static int MESO_RATE = 1;
    public static int DROP_RATE = 1;
    public static byte FLAG = 3;
    public static int CHANNEL_COUNT = 2;
    public static String WORLD_TIP = "請享受楓之谷的冒險之旅吧!";
    public static String SCROLL_MESSAGE = "";
    public static boolean AVAILABLE = true;
    public static final int gmserver = -1; // -1 = no gm server
    public static final byte recommended = (byte) -1; //-1 = no recommended
    public static final String recommendedmsg = recommended < 0 ? "" : "        Join " + getById(recommended).name() + ",       the newest world! (If youhave friends who play, consider joining their worldinstead. Characters can`t move between worlds.)";

    public static interface Option {

        public int getWorld();

        public String name();
    }

    /**
     *
     * @Warning: World will be duplicated if it's the same as the gm server
     */
    public static enum WorldOption implements Option {
        蓝蜗牛(0),
        蘑菇仔(1),
        绿水灵(2),
        漂漂猪(3),
        小青蛇(4),
        红螃蟹(5),
        大海龟(6),
        章鱼怪(7),
        顽皮猴(8),
        星精灵(9),
        胖企鹅(10),
        童话村(121);
        private final int world;

        WorldOption(int world) {
            this.world = world;
        }

        @Override
        public int getWorld() {
            return world;
        }
    }

    public static enum TespiaWorldOption implements Option {

        測試機("t0");
        private final int world;
        private final String worldName;

        TespiaWorldOption(String world) {
            this.world = Integer.parseInt(world.replaceAll("t", ""));
            this.worldName = world;
        }

        @Override
        public int getWorld() {
            return world;
        }
    }

    public static Option[] values() {
        return ServerConstants.TESPIA ? TespiaWorldOption.values() : WorldOption.values();
    }

    public static Option valueOf(String name) {
        return ServerConstants.TESPIA ? TespiaWorldOption.valueOf(name) : WorldOption.valueOf(name);
    }

    public static Option getById(int g) {
        for (Option e : values()) {
            if (e.getWorld() == g) {
                return e;
            }
        }
        return null;
    }

    public static boolean isExists(int id) {
        return getById(id) != null;
    }

    public static String getNameById(int serverid) {
        if (getById(serverid) == null) {
            System.err.println("World doesn't exists exception. ID: " + serverid);
            return "";
        }
        return getById(serverid).name();
    }

    public static void loadSetting() {
        ADMIN_ONLY = ServerProperties.getProperty("qilin.admin", ADMIN_ONLY);
        FLAG = ServerProperties.getProperty("qilin.flag", FLAG);
        EXP_RATE = ServerProperties.getProperty("qilin.expRate", EXP_RATE);
        MESO_RATE = ServerProperties.getProperty("qilin.mesoRate", MESO_RATE);
        DROP_RATE = ServerProperties.getProperty("qilin.dropRat", DROP_RATE);
        WORLD_TIP = ServerProperties.getProperty("qilin.eventMessage", WORLD_TIP);
        SCROLL_MESSAGE = ServerProperties.getProperty("qilin.serverMessage", SCROLL_MESSAGE);
        CHANNEL_COUNT = ServerProperties.getProperty("qilin.channel.count", CHANNEL_COUNT);
        //USER_LIMIT = ServerProperties.getProperty("qilin.userlimit", USER_LIMIT);//服务端最大人数
        USER_LIMIT = gui.Qhms.ConfigValuesMap.get("服务端最大人数");
        MAX_CHAR_VIEW = ServerProperties.getProperty("qilin.maxCharView", MAX_CHAR_VIEW);//频道容纳人数
        GMITEMS = ServerProperties.getProperty("qilin.gmitems", GMITEMS);
        CS_ENABLE = ServerProperties.getProperty("qilin.cashshop.enable", CS_ENABLE);
    }

    static {
        loadSetting();
    }
}
