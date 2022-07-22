/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：废弃都市奖励
 */
importPackage(java.awt);
importPackage(Packages.tools);
importPackage(Packages.server);
importPackage(Packages.handling.world);
var status;

function start() {
    status = -1;
    action(1, 0, 0);
}
var time = 0;
function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.对话结束();
        return;
    } else {
        if (mode == 1){
            status++;
        }else{
            status--;
		}
        var mapId = cm.getMapId();
        if (mapId == 103000890) {
            cm.warp(103000000, "mid00");
            cm.removeAll(4001007);
            cm.removeAll(4001008);
            cm.对话结束();
        } else {
            var outText;
            if (mapId == 103000805) {
                outText = "你确定要离开地图？？";
                time = 1;
            } else {
                outText = "一旦你离开地图，你将不得不重新启动整个任务，如果你想再次尝试。你还是要离开这个地图？";
                time = 0;
            }
            if (status == 0) {
                cm.sendYesNo(outText);
            } else if (mode == 1) {
                if (time == 1) {
                    getPrize();
                }
				cm.warp(103000890, "st00");
                cm.对话结束();
            }
        }
    }
}
//副本通关奖励区域
/*
cm.概率给物品(物品代码，固定数量，概率,备注)；
cm.概率给物品2(物品代码，随机数量，概率,备注)；
*/
function getPrize() {
	var eim = cm.getEventInstance();
	var 废弃通关经验 = 3000;
	ver 积分 = 1;
	var 角色 = cm.getPlayer().id;
	var 总完成 = cm.getBossRank("废弃都市", 2);
	var 神秘粉末获取次数 = 10;
	//废弃盛产母矿，恩。
	if(cm.判断每日值("废弃都市神秘粉末")<=神秘粉末获取次数){
		cm.概率给物品2(4032135,20,70,"神秘粉末");
		cm.增加每日值("废弃都市神秘粉末");
		if(总完成>=50){
		cm.概率给物品(4032135,10,100,"神秘粉末");
		}
	}else{
		cm.个人公告("今日无法获取神秘粉末。");
	}
	cm.概率给物品2(4002000,3,80,"绿蜗牛邮票");
	cm.概率给物品2(4001009,2,50,"橡皮擦");
	cm.概率给物品(4006000,10,60,"魔法石");
	//cm.概率给物品2(4310113,2,7,"胡萝卜币");
	//cm.概率给物品2(4310022,2,100,"樱花节纪念币");
	//cm.概率给物品2(4310044,2,10,"猫郎诅咒币");
	cm.概率给物品2(4006000,20,50,"魔法石");
	cm.概率给物品2(4006001,10,50,"召回石");
	cm.概率给物品2(4004000,2,30,"力量母矿");
	cm.概率给物品2(4004001,2,30,"智慧母矿");
	cm.概率给物品2(4004002,2,30,"敏捷母矿");
	cm.概率给物品2(4004003,2,30,"幸运母矿");
	cm.概率给物品2(4170002,1,50,"飞天猪的蛋");
	cm.概率给物品2(4004004,3,1,"黑暗水晶母矿");
	cm.概率给物品2(4020000,3,50,"石榴石母矿");
	cm.概率给物品2(4020001,3,50,"紫水晶母矿");
	cm.概率给物品2(4020002,3,50,"海蓝石母矿");
	cm.概率给物品2(4020003,3,50,"祖母绿母矿");
	cm.概率给物品2(4020004,3,50,"蛋白石母矿");
	cm.概率给物品2(4020005,3,50,"蓝宝石母矿");
	cm.概率给物品2(4020006,3,50,"黄晶母矿");
	cm.概率给物品2(4020007,3,50,"钻石母矿");
	cm.概率给物品2(4020008,3,50,"黑水晶母矿");
	cm.给经验(cm.getLevel()*废弃通关经验);
	//解锁成就后获取额外的经验。
	cm.worldMessage(2, "[副本-废弃都市] : 恭喜 " + cm.getPlayer().getName() + " 完成废弃都市副本。");
	if(总完成>=10){
		cm.给经验(cm.getLevel()*废弃通关经验/2);
	}
	//解锁成就后一定机率获取AP点。
	if(总完成>=150){
		if(cm.判断每日值("废弃给AP点")<=2){
			if(cm.百分率(5)){
				cm.给能力点(1);
				cm.增加每日值("废弃给AP点");
				cm.个人公告("获取 1 AP");
			}
		}
	}
	//记录通关信息
		cm.gainzdjf(积分);//组队任务积分
		cm.getPlayer().endPartyQuest(1201);
		cm.setBossRankCount("废弃都市",1);
		cm.setBossLog("废弃都市");
		cm.dispose();
		}
		



function formatSeconds(value) {
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