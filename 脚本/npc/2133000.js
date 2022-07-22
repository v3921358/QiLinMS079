/*
冒险岛(079)游戏服务端
 脚本：毒雾森林
 */
var 最少人数 = 2;
var 最多人数 = 6;
var 最低等级 = 91;
var 最高等级 = 255;
var 毒物通关经验 = 8000;
//毒雾森林奖励预览
/*
 物品，概率
 */
var 奖励预览 = [
    [4002000, 50],
	[4002001, 50],
	[4002002, 50],
	[4002003, 50]
];
var status = 0;

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.dispose();
        }
        status--;
    }
    if (status == 1) {
		var 文本信息 = "";
        for (var i = 0; i < 奖励预览.length; i++) {
            文本信息 += "   " + cm.显示物品(奖励预览[i][0]) + "  " + 奖励预览[i][1] + " % #k\r\n";
        }
        cm.sendYesNo("\r\n     毒物森林。副本的要求的人数是#b" + 最少人数 + " - " + 最多人数 + "#k，等级要求#b" + 最低等级 + " - " + 最高等级 + "#k，你要参加副本#b毒雾森林#k吗？在这里盛产各种#r邮票#k哦。\r\n\r\n#d\t最后通关奖励经验 #r" + cm.getLevel()*毒物通关经验 + "#k\r\n\r\n   总计完成: #r" + cm.getBossRank("毒雾森林", 2) + "\r\n#k   今日完成: #r" + cm.getBossLog("毒雾森林") + "\r\n\r\n#k#e副本奖励预览:#n\r\n\r\n" + 文本信息 + "");

    } else if (status == 2) {
        if (cm.getPlayer().getParty() == null || !cm.isLeader()) {
            cm.sendOk("找您的队长来和我谈话。");
        } else {
            var party = cm.getPlayer().getParty().getMembers();
            var mapId = cm.getPlayer().getMapId();
            var next = true;
            var size = 0;
            var it = party.iterator();
            while (it.hasNext()) {
                var cPlayer = it.next();
                var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
                if (ccPlayer == null || ccPlayer.getLevel() > 最高等级 || ccPlayer.getLevel() < 最低等级) {
                    next = false;
                    break;
                }

            }
            if (party.size() > 最多人数 || party.size() < 最少人数) {
                next = false;
            }
			
            if (next) {
                var em = cm.getEventManager("Ellin");
                if (em == null) {
                    cm.sendOk("当前副本有问题，请联络管理员....");
                } else {
                    var prop = em.getProperty("state");
                    if (prop.equals("0") || prop == null) {
                        em.startInstance(cm.getParty(), cm.getMap());
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("里面已经有人了,请你稍后在进入看看,或者是换频");
                    }
                }
            } else {
                cm.sendOk("副本: #b毒雾森林#k\r\n人数: #b" + 最少人数 + " - " + 最多人数 + "#k\r\n等级: #b" + 最低等级 + " - " + 最高等级 + "#k");
            }
        }

        cm.dispose();
    }
}