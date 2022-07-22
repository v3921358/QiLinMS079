/*
 This file is part of the OdinMS Maple Story Server
 Copyright (C) 2008 ~ 2010 Patrick Huy <patrick.huy@frz.cc> 
 Matthias Butz <matze@odinms.de>
 Jan Christian Meyer <vimes@odinms.de>

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License version 3
 as published by the Free Software Foundation. You may not use, modify
 or distribute this program under any other version of the
 GNU Affero General Public License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package constants;

import server.ServerProperties;

public class ServerConstants {

    /*
     * Specifics which job gives an additional EXP to party
     * returns the percentage of EXP to increase
     */
    // Start of Poll
    public static boolean TESPIA = false;
    public static final boolean PollEnabled = false;
    public static final String Poll_Question = "Are you mudkiz?";
    public static final String[] Poll_Answers = {"test1", "test2", "test3"};
    // End of Poll
    public static final short MAPLE_VERSION = 79;
    public static final String MAPLE_PATCH = "1";
    public static MapleType MAPLE_TYPE = MapleType.中国;
    public static boolean Use_Fixed_IV = false;
    public static final int MIN_MTS = 110;
    public static final int MTS_BASE = 100; //+1000 to everything in MSEA but cash is costly here
    public static final int MTS_TAX = 10; //+% to everything
    public static final int MTS_MESO = 5000; //mesos needed
    public static final int CHANNEL_COUNT = 200;
    public static final String CashShop_Key = "a;!%dfb_=*-a123d9{P~";
    public static final String Login_Key = "pWv]xq:SPTCtk^LGnU9F";
    public static int[] hot_sell = {10000007, 10000008, 10000009, 10000010, 10000011};
    public static boolean MobDropMPoint = true;
    public static int MobDropMPointRate = 30;
    public static int MobDropMPointLimit = 2500;
    public static int MobDropMPointMin = 1;
    public static int MopDropMPointMax = 2;

    public static enum PlayerGMRank {

        普通玩家(0),
        新实习生(1),
        老實習生(2),
        巡逻者(3),
        领导者(4),
        超级管理员(5),
        神(100);
        private final char commandPrefix;
        private final int level;

        PlayerGMRank(int level) {
            this.commandPrefix = level > 0 ? '!' : '@';
            this.level = level;
        }

        public char getCommandPrefix() {
            return commandPrefix;
        }

        public int getLevel() {
            return level;
        }

        public static class 玩家指令 {

            public static int getLevel() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            public static char getCommandPrefix() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            public 玩家指令() {
            }
        }

        public static class 巡查管理 {

            public static char getCommandPrefix() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            public 巡查管理() {
            }
        }

        public static class 游戏管理 {

            public static char getCommandPrefix() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            public 游戏管理() {
            }
        }
    }

    /*public static enum VIPRank {

        普通VIP(1),
        進階VIP(2),
        高級VIP(3),
        尊貴VIP(4),
        至尊VIP(5);
        private final char commandPrefix;
        private final int level;

        VIPRank(int level) {
            this.commandPrefix = '#';
            this.level = level;
        }

        public char getCommandPrefix() {
            return commandPrefix;
        }

        public int getLevel() {
            return level;
        }
    }*/
    public static enum CommandType {

        NORMAL(0),
        TRADE(1);
        private final int level;

        CommandType(int level) {
            this.level = level;
        }

        public int getType() {
            return level;
        }
    }

    public static enum MapleType {

        UNKNOWN(-1, "UTF-8"),
        한국(1, "EUC_KR"),
        한국_TEST(1, "EUC_KR"),
        日本(3, "Shift_JIS"),
        中国(4, "GBK"),
        TESPIA(5, "UTF-8"),
        台灣(6, "BIG5"),
        SEA(7, "UTF-8"),
        GLOBAL(8, "UTF-8"),
        BRAZIL(9, "UTF-8");
        byte type;
        final String ANSI;

        private MapleType(int type, String ANSI) {
            this.type = (byte) type;
            this.ANSI = ANSI;
        }

        public byte getType() {
            if (!ServerConstants.TESPIA) {
                return type;
            }
            if (this == 한국 || this == 한국_TEST) {
                return 2;//KMS測試機
            } else {
                return 5;//測試機
            }
        }

        public String getANSI() {
            return ANSI;
        }

        public void setType(int type) {
            this.type = (byte) type;
        }

        public static MapleType getByType(byte type) {
            for (MapleType l : MapleType.values()) {
                if (l.getType() == type) {
                    return l;
                }
            }
            return UNKNOWN;
        }
    }

    public static void loadSetting() {
        String[] x = ServerProperties.getProperty("QhMs.cashshop.HotSell", "10000007, 10000008, 10000009, 10000010, 10000011").split(",");
        int[] y = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] = Integer.parseInt(x[i].replace(" ", ""));
        }
        hot_sell = y;
        TESPIA = ServerProperties.getProperty("QhMs.tespia", TESPIA);
        Use_Fixed_IV = ServerProperties.getProperty("server.crypt", Use_Fixed_IV);
    }

    static {
        loadSetting();
    }
}
