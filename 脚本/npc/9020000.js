/*
冒险岛(079)游戏服务端
 脚本：废弃都市
 */

var 最小人数 = 3;
var 最大人数 = 6;
var 最低等级 = 15;
var 最高等级 = 256;
//以下只是展示，需要修改9020001
var 第一关经验 = 200;
var 第二关经验 = 300;
var 第三关经验 = 400;
var 第四关经验 = 500;
//以下只是展示，需要修改9020002
var 最后通关 = 3000;
var 神秘粉末获取次数 = 10;
//废弃奖励预览
/*
 物品，概率
 */
var 奖励预览 = [
	[4002000, 40],//绿蜗牛邮票
	[4006000, 50],
	[4006001, 50],
//    [4310113, 70],
//    [4310022, 100],
    [4170002, 50],
 //   [4310044, 10],
    [4032135, 70],
    [4001009, 50],
    [4004000, 30],
    [4004001, 30],
    [4004002, 30],
    [4004003, 30],
    [4004004, 10],
    [4020000, 10],
    [4020001, 10],
    [4020002, 10],
    [4020003, 10],
    [4020004, 10],
    [4020005, 10],
    [4020006, 10],
    [4020007, 10],
    [4020008, 10]

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
        cm.对话结束();
        return;
    }
    var 总完成 = cm.getBossRank("废弃都市", 2);
    var 角色 = cm.getPlayer().id;
    if (总完成 < 0) {
        var 总完成 = 0;
    }
    if (status == 1) {
        var 文本信息 = "";
        for (var i = 0; i < 奖励预览.length; i++) {
            文本信息 += "    #d#z" + 奖励预览[i][0] + "##k  #r" + 奖励预览[i][1] + " % #k\r\n";
        }
        var 成就 = "\r\n";
        if (总完成 >= 10) {
            成就 += "    #r[成就]#d最后通关额外获取 #b50%#k #d经验#k\r\n";
        }
        if (总完成 >= 50) {
            成就 += "    #r[成就]#d最后通关额外获取 #b#z4032135##k x #d10#k\r\n";
        }
        if (总完成 >= 150) {
            成就 += "    #r[成就]#d最后通关 #b5%#d 概率获取 #b1#d 点AP(2次/天)#k\r\n";
        }
        var 说明 = "#e#d[副本]:#k#n#r废弃都市#k  \r\n   #kHi~#b#h ##k，位于废弃底下水道的副本任务，可以通过完成该副本获取超多收益。副本的要求的人数是#b" + 最小人数 + " - " + 最大人数 + "#k，等级要求#b" + 最低等级 + " - " + 最高等级 + "#k，你要参加副本#b废弃都市#k吗？神秘粉末每日只有前 #r" + 神秘粉末获取次数 + "#k 次才会计算有机率获取。当你完成足够多的通关次数，就会解锁成就，获取额外的奖励。";
        cm.sendYesNo("" + 说明 + "\r\n" + 成就 + "\r\n    挑战次数: #b" + 总完成 + "#k / #r" + cm.getBossLog("废弃都市") + "  \r\n\r\n    #k#b#e副本经验预览:#n\r\n#d\t第一关 #r" + cm.getLevel()*第一关经验 + "#k\r\n#d\t第二关 #r" + cm.getLevel()*第二关经验 + "#k\r\n#d\t第三关 #r" + cm.getLevel()*第三关经验 + "#k\r\n#d\t第四关 #r" + cm.getLevel()*第四关经验 + "#k\r\n#d\t最后通关 #r" + cm.getLevel()*最后通关 + "#k\r\n\r\n    #k#e#b副本奖励预览:#n\r\n" + 文本信息 + "");
    } else if (status == 2) {
        if (cm.getParty() == null) {
            cm.说明文字("请组队再来找我，或者让队长找我。");
        } else if (!cm.isLeader()) {
            cm.说明文字("请叫你的队长来找我!");
        } else {
            var party = cm.getParty().getMembers();
            var mapId = cm.getMapId();
            var next = true;
            var levelValid = 0;
            var inMap = 0;

            var it = party.iterator();
            while (it.hasNext()) {
                var cPlayer = it.next();
                if (cPlayer.getLevel() >= 最低等级 && cPlayer.getLevel() <= 最高等级) {
                    levelValid += 1;
                } else {
                    next = false;
                }
                if (cPlayer.getMapid() == mapId) {
                    inMap += (cPlayer.getJobId() == 900 ? 4 : 1);
                }
            }
            if (party.size() > 最大人数 || inMap < 最小人数) {
                next = false;
            }
            if (团队判断() == 1 || 团队判断() == 2) {
                cm.说明文字("请保证队友都在场。");
                cm.对话结束();
                return;
            }
            if (next) {
                var em = cm.getEventManager("KerningPQ");
                if (em == null) {
                    cm.说明文字("找不到脚本，请联系管理员！");
                    cm.对话结束();
                    return;
                } else {
                    var prop = em.getProperty("state");
                    if (prop == null || prop.equals("0")) {
                        em.startInstance(cm.getParty(), cm.getMap());
                    } else {
                        cm.说明文字("已经有队伍在里面挑战了。");
                        cm.对话结束();
                        return;
                    }
                }
            } else {
                cm.说明文字("副本: #b废弃都市#k\r\n人数: #b" + 最小人数 + " - " + 最大人数 + "#k\r\n等级: #b" + 最低等级 + " - " + 最高等级 + "#k");
                cm.对话结束();
                return;
            }
        }
        cm.对话结束();
    }
}

function 团队判断() {
    var 队伍 = cm.getParty().getMembers();
    var mapId = cm.判断地图();
    var Channel = cm.getPlayer().getClient().getChannel();
    var valid = 0;
    var it = 队伍.iterator();
    while (it.hasNext()) {
        var cPlayer = it.next();
		//不在同地图
        if (cPlayer.getMapid() != mapId) {
            valid = 1;
        }
		//不在同频道
        if (cPlayer.getChannel() != Channel) {
            valid = 2;
        }
    }
    return valid;
}

function 时间(value) {
    var theTime = parseInt(value);
    var theTime1 = 0;
    var theTime2 = 0;
    if (theTime > 60) {
        theTime1 = parseInt(theTime / 60);
        theTime = parseInt(theTime % 60);
        if (theTime1 > 60) {
            theTime2 = parseInt(theTime1 / 60);
            theTime1 = parseInt(theTime1 % 60);
        }
    }
    var result = "" + parseInt(theTime) + " 秒 ";
    if (theTime1 > 0) {
        result = "" + parseInt(theTime1) + " 分 " + result;
    }
    if (theTime2 > 0) {
        result = "" + parseInt(theTime2) + " : " + result;
    }
    return result;
} 