var head = "#fUI/UIWindow2.img/Quest/quest_info/summary_icon/summary#\r\n";
var icon = "#fUI/UIWindow/Minigame/Common/mark#";
var sl1 = 0;//兑换数量

var status = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == 1) {
		status++;
	} else {
		if (status >= 0) {
			cm.dispose();
			return;
		}
		status--;
	}
          if (status == 0) {

			//var text = "#h0#e#d 欢迎来到" + cm.getServerName() + "#k,先大概了解一下本服萌新特色\r\n";
			    var text = "#r萌新礼包介绍：确认点下一页立即领取#k#n\r\n";
				text += "#v1332066##z1332066# 1把 属性+2\r\n";
				text += "#v1052550##z1052550# 1件 属性+2\r\n";
				text += "#v1003713##z1003713# 1件 属性+2\r\n";
				text += "#v1082493##z1082493# 1件 属性+2\r\n";
				text += "#v1142358##z1142358# 1个 属性+1\r\n";
				text += "#v1112171##z1112171# 1个 \r\n";
				text += "#v2000005##z2000005#  10瓶\r\n";
				text += "冒险金币:10万 抵用卷：5000点#k#n\r\n";
			cm.sendSimple(text);
		cm.sendNextS(text, 1);
		} else if (status == 1) {

		if (cm.getOneTimeLog("新手驾到") < 1 && cm.getPlayerStat("LVL") < 30) {
			cm.setOneTimeLog("新手驾到");	
			cm.gainItem(2000005, 10);//超级药水
			cm.gainItem(1332066,2,2,2,2,0,0,10,10,0,0,0,0,0,0,72);//新手刮胡刀
			cm.gainItem(1052550,2,2,2,2,0,0,10,10,0,0,0,0,0,0,72); //海豹上衣
			cm.gainItem(1003713,2,2,2,2,0,0,10,10,0,0,0,0,0,0,72); //海豹手套
			cm.gainItem(1082493,2,2,2,2,0,0,10,10,0,0,0,0,0,0,72); //海豹帽子
			cm.gainItem(1142358,1,1,1,1,10,10,1,1,0,0,0,0,0,0); //新手勋章我最可爱
			cm.gainItem(1112171,1); //狗子名片
			//cm.给属性装备(1332066, 1, 0, 20, 20, 20, 20, 50, 50, 30, 30,0, 0, 0, 0, 0, 0, 24);//新手刮胡刀时间单位/小时
			//cm.给属性装备(1142358, 1, 0, 1, 1, 1, 1, 50, 50, 1, 1,0, 0, 0, 0, 0, 0, 0);//新手勋章我最可爱
			//cm.给属性装备(1142099, 1, 0, 20, 20, 20, 20, 500, 500, 35, 35,0, 0, 0, 0, 40, 40, 24);//时间单位/小时
			//cm.给属性装备(1142101, 1, 0, 20, 20, 20, 20, 500, 500, 15, 15,0, 0, 0, 0, 40, 40, 0);
			cm.gainMeso(100000);//金币10万
			cm.gainDY(5000);//给予抵用卷5000点               
			cm.sendOk("领取成功！");
			cm.全服黄色喇叭("[公告事项] : 恭喜玩家 "+cm.getPlayer().getName()+" 成功领取了萌新大礼包。")
            cm.dispose();
				
		} else {
            cm.sendOk("您已领取过新手礼包了或者您的等级超过30级不属于新手了。");
            cm.dispose();



            }
        }

}
