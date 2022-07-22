/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：女神塔
 */

var 最低等级 = 51;
var 最高等级 = 255;
var 最少人数 = 2;
var 最多人数 = 6;
var 第一关经验 = 2000;
var 最后通关 = 5000;
//女神塔奖励预览
/*
 物品，概率
 */
var 奖励预览 = [
    [4000463, 40],//国庆币
	[4002002, 40],//木妖邮票
	[4007000, 30],
	[4007001, 30],
	[4007002, 30],
	[4007003, 30],
	[4007004, 30],
	[4007005, 30],
	[4007006, 30],
	[4007007, 30],
	[4010000, 30],
	[4010001, 30],
	[4010002, 30],
	[4010003, 30],
	[4010004, 30],
	[4010005, 30],
	[4010006, 30],
	[4010007, 30],
	[4001158, 100] //女神的羽毛
];
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
	if(cm.getPlayer().getMapId() == 920010000 && cm.isLeader()){
        if (cm.getPlayer().haveItem(4001063, 20)) {
            cm.givePartyExp(cm.getLevel()*第一关经验);
            cm.gainItem(4001063, -20);
			cm.warpParty(920010100); 
			return;
        } else {
            cm.sendNext("我被远古精灵困在这座塔，快收集材料让我出去。");
            cm.dispose();
            return;
        }
    }
    if (status == 0) {
		var 文本信息 = "";
        for (var i = 0; i < 奖励预览.length; i++) {
            文本信息 += "   " + cm.显示物品(奖励预览[i][0]) + "  " + 奖励预览[i][1] + " % #k\r\n";
        }
        cm.sendYesNo("\r\n     快来拯救女神吧。副本的要求的人数是#b" + 最少人数 + " - " + 最多人数 + "#k，等级要求#b" + 最低等级 + " - " + 最高等级 + "#k，你要参加副本#b女神塔#k吗？在这里盛产各种#r魔法粉末，金属母矿石#k哦。\r\n\r\n#d\t最后通关奖励经验 #r" + cm.getLevel()*最后通关 + "#k\r\n\r\n  总计完成: #r" + cm.getBossRank("女神塔", 2) + "\r\n#k   今日完成: #r" + cm.getBossLog("女神塔") + "\r\n\r\n#k#e副本奖励预览:#n\r\n\r\n" + 文本信息 + "");
    } else if (status == 1) {
        if (cm.getParty() == null) {
            cm.sendSimple("请组队再来找我，或者让队长找我。");
        } else {
            var party = cm.getParty().getMembers();
            var mapId = cm.getMapId();
            var next = true;
            var levelValid = 0;
            var inMap = 0;
            var it = party.iterator();
            while (it.hasNext()) {
                var cPlayer = it.next();
                if ((cPlayer.getLevel() >= 最低等级) && (cPlayer.getLevel() <= 最高等级)) {
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
                var em = cm.getEventManager("OrbisPQ");
                if (em == null) {
                    cm.sendSimple("找不到脚本请联络GM");
                } else {
                    var prop = em.getProperty("state");
                    if (prop.equals("0") || prop == null) {
                        em.startInstance(cm.getParty(), cm.getMap());
                        cm.对话结束();
                        return;
                    } else {
                        cm.sendSimple("其他队伍已经在里面做 #r组队任务了#k 请尝试换频道或者等其他队伍完成。");
                    }
                }
            } else {
                cm.sendSimple("你的队伍貌似没有达到要求...:\r\n\r\n#r要求: " + 最少人数 + " 玩家成员, 每个人的等级必须在 " + 最低等级 + " 到 等级 " + 最高等级 + ".");
            }
        }
    } else {
        cm.对话结束();
    }
}