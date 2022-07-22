/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：月妙
 */
var 最小等级 = 1;
var 最高等级 = 255;
var 最少人数 = 1;
var 最多人数 = 6;

var status = -1;
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.对话结束();
            return;
        }
        status--;
    }
    if (cm.getMapId() == 910010100) {
        cm.warp(100000200);
        cm.对话结束();
	 } else if (status == 0) {	
	 cm.sendYesNo("\r\n     你愿意和你的组队完成一个任务吗？在这里，你会发现障碍和问题，你将无法击败它，除非与伟大的团队合作。如果你想试试，请告诉我 #b组队队长#k 跟我说话.\r\n\r\n#r要求:  " + 最少人数 + " - " + 最多人数 + " 队员 所有级别 " + 最小等级 + " - " + 最高等级 + ".\r\n   #k总计完成: #r"+cm.getBossRank("月妙",2)+"\r\n#k   今日完成: #r"+cm.getBossLog("月妙")+"\r\n\r\n副本奖励:\r\n "+cm.显示物品(4001101)+"\r\n "+cm.显示物品(1002798)+" \r\n "+cm.显示物品(4001126)+" \r\n "+cm.显示物品(4000313)+"");
    } else if (status == 1) {
        if (cm.getParty() == null) {
            cm.sendSimple("请组队后再找我把。");
        } else if (!cm.isLeader()) {
            cm.sendSimple("如果你想尝试，请告诉 #b组队队长#k 跟我说话.#b#l");
        } else {
            var party = cm.getParty().getMembers();
            var mapId = cm.getMapId();
            var next = true;
            var levelValid = 0;
            var inMap = 0;
            var it = party.iterator();

            while (it.hasNext()) {
                var cPlayer = it.next();
                if ((cPlayer.getLevel() >= 最小等级) && (cPlayer.getLevel() <= 最高等级)) {
                    levelValid += 1;
                } else {
                    next = false;
                }
                if (cPlayer.getMapid() == mapId) {
                    inMap += (cPlayer.getJobId() == 900 ? 6 : 1);
                }
            }
            if (party.size() > 最多人数 || inMap < 最少人数) {
                next = false;
            }
            if (next) {
                var em = cm.getEventManager("HenesysPQ");
                if (em == null) {
                    cm.sendSimple("PQ遇到了一个错误。请联系GM，与截图.#b#l");
                } else {
                    var prop = em.getProperty("state");
                    if (prop.equals("0") || prop == null) {
                        for (var i = 4001095; i < 4001099; i++) {
                            cm.givePartyItems(i, 0, true);
                        }
                        for (var i = 4001100; i < 4001101; i++) {
                            cm.givePartyItems(i, 0, true);
                        }
                        em.startInstance(cm.getParty(), cm.getMap());
                        cm.对话结束();
                        return;
                    } else {
                        cm.sendSimple("另一方已进入 #r月秒任务#k 在这里。请尝试另一个频道，或者等待当前的任务完成.#b#");
                    }
                }
            } else {
                cm.sendOk("副本: #b月妙#k\r\n人数: #b" + 最少人数 + " - " + 最多人数 + "#k\r\n等级: #b" + 最小等级 + " - " + 最高等级 + "#k");
                cm.对话结束();
            }
        }
    }
}