/*
冒险岛(079)游戏服务端
 脚本：海盗船
 */

var 最低等级 = 110;
var 最高等级 = 255;
var 最少人数 = 2;
var 最多人数 = 6;
var 海盗通关经验 = 10000;
var 奖励预览 = [
    [4001009, 30],
	[4001010, 30],
	[4001011, 30],
	[4001012, 30],
	[4001013, 30],
	[4001014, 30],
	[4001021, 30]
];
var status = 0;

function start() {
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }
    if (status == 1) {
		var 文本信息 = "";
        for (var i = 0; i < 奖励预览.length; i++) {
            文本信息 += "   " + cm.显示物品(奖励预览[i][0]) + "  " + 奖励预览[i][1] + " % #k\r\n";
        }
        cm.sendYesNo("\r\n     海盗船被一群外来的海盗占领了，你会帮我教训教训他们吗？副本的要求的人数是 #b" + 最少人数 + " - " + 最多人数 + "#k，等级要求#b" + 最低等级 + " - " + 最高等级 + "#k，你要参加副本#b海盗船#k吗？在这里盛产各种#r橡皮擦#k哦。\r\n\r\n#d\t最后通关奖励经验 #r" + cm.getLevel()*海盗通关经验 + "#k\r\n\r\n   总计完成: #r" + cm.getBossRank("海盗船", 2) + "\r\n#k   今日完成: #r" + cm.getBossLog("海盗船") + "\r\n\r\n#k#e副本奖励预览:#n\r\n\r\n" + 文本信息 + "");
    } else if (status == 2) {
        cm.removeAll(4001117);
        cm.removeAll(4001120);
        cm.removeAll(4001121);
        cm.removeAll(4001122);
        if (cm.getPlayer().getParty() == null || !cm.isLeader()) {
            cm.sendOk("请找队长来找我。");
        } else {
            var party = cm.getPlayer().getParty().getMembers();
            var mapId = cm.getPlayer().getMapId();
            var next = true;
            var size = 0;
            var it = party.iterator();
            while (it.hasNext()) {
                var cPlayer = it.next();
                var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
                if (ccPlayer == null || ccPlayer.getLevel() < 最低等级 || ccPlayer.getLevel() > 最高等级) {
                    next = false;
                    break;
                }
                size += (ccPlayer.isGM() ? 2 : 1);
            }
            if (next && size >= 最少人数) {
                if (checkMap()) {
                    var em = cm.getEventManager("Pirate");
                    if (em == null) {
                        cm.sendOk("找不到脚本，请联系GM！！");
                    } else {
                        em.startInstance(cm.getPlayer().getParty(), cm.getPlayer().getMap());
                    }
                } else {
                    cm.sendOk("目前有人在挑战了。");
                }
            } else {
                cm.sendOk("副本: #b海盗船#k\r\n人数: #b" + 最小人数 + " - " + 最大人数 + "#k\r\n等级: #b" + 最低等级 + " - " + 最高等级 + "#k");
            }
        }
        cm.对话结束();
    }
}

function checkMap() {
    var map = [925100000, 925100100, 925100200, 925100201, 925100202, 925100300, 925100301, 925100302, 925100400, 925100400, 925100500];
    for (var i = 0; i < map.length; i++) {
        if (cm.getPlayerCount(map[i]))
            return false;
    }
    return true;
}