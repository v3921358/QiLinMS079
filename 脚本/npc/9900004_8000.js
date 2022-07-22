
var 正在进行中 = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var 完成 = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var 正在进行中蓝 = "#fUI/UIWindow/MonsterCarnival/icon1#";
var 完成红 = "#fUI/UIWindow/MonsterCarnival/icon0#";
function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("感谢你的光临！");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			text += "\t\t\t  #e#d欢迎来到在线奖励系统\r\n\r\n"
			text += "\t\t\t#r您的当前在线时间："+cm.getGamePoints()+" 分钟#k!#n\r\n"
			if(cm.getGamePoints() >= 60 && cm.getBossLog("zxsj") == 0){
					text += "#L2##r"+完成红+"在线时间超过60分钟！"+完成+"#l\r\n\r\n\r\n"
				} else if(cm.getGamePoints() >= 60 && cm.getBossLog("zxsj") > 0){
					text += ""+完成红+"#r在线时间超过60分钟！#l"+完成+"\r\n\r\n"//3
				} else {
					text += ""+正在进行中蓝+"#r在线时间超过60分钟！奖品缩地石2个！#l"+正在进行中+"\r\n\r\n"
			}
			
			if(cm.getGamePoints() >= 120 && cm.getBossLog("zxsj") == 1){
					text += "#L3##r"+完成红+"在线时间超过120分钟！"+完成+"#l\r\n\r\n\r\n"
				} else if(cm.getGamePoints() >= 120 && cm.getBossLog("zxsj") > 1){
					text += ""+完成红+"#r在线时间超过120分钟！#l"+完成+"\r\n\r\n"
				} else {
					text += ""+正在进行中蓝+"#r在线时间超过120分钟！奖品黄金枫叶20个！#l"+正在进行中+"\r\n\r\n"
			}
			
			if(cm.getGamePoints() >= 180 && cm.getBossLog("zxsj") == 2){
					text += "#L4##r"+完成红+"在线时间超过180分钟！"+完成+"#l\r\n\r\n\r\n"
				} else if(cm.getGamePoints() >= 180 && cm.getBossLog("zxsj") > 2){
					text += ""+完成红+"#r在线时间超过180分钟！#l"+完成+"\r\n\r\n"
				} else {
					text += ""+正在进行中蓝+"#r在线时间超过180分钟！奖品皇家发型2张！#l"+正在进行中+"\r\n\r\n"
			}
			
			if(cm.getGamePoints() >= 240 && cm.getBossLog("zxsj") == 3){
					text += "#L5##r"+完成红+"在线时间超过240分钟！"+完成+"#l\r\n\r\n\r\n"
				} else if(cm.getGamePoints() >= 240 && cm.getBossLog("zxsj") > 3){
					text += ""+完成红+"#r在线时间超过240分钟！#l"+完成+"\r\n\r\n"
				} else {
					text += ""+正在进行中蓝+"#r在线时间超过240分钟！奖品枫叶100个 喇叭3个！#l"+正在进行中+"\r\n\r\n"
			}
			
			if(cm.getGamePoints() >= 300 && cm.getBossLog("zxsj") == 4){
					text += "#L6##r"+完成红+"在线时间超过300分钟！"+完成+"#l\r\n\r\n\r\n"
				} else if(cm.getGamePoints() >= 300 && cm.getBossLog("zxsj") > 4){
					text += ""+完成红+"#r在线时间超过300分钟！#l"+完成+"\r\n\r\n"
				} else {
					text += ""+正在进行中蓝+"#r在线时间超过300分钟！奖品1000抵用券 1000点券#l"+正在进行中+"\r\n\r\n"
			}
			if(cm.getGamePoints() >= 360 && cm.getBossLog("zxsj") == 5){
					text += "#L7##r"+完成红+"在线时间超过360分钟！"+完成+"#l\r\n\r\n\r\n"
				} else if(cm.getGamePoints() >= 360 && cm.getBossLog("zxsj") > 5){
					text += ""+完成红+"#r在线时间超过360分钟！#l"+完成+"\r\n\r\n"
				} else {
					text += ""+正在进行中蓝+"#r在线时间超过360分钟！奖品劳动奖章+签到图章+大量经验#l"+正在进行中+"\r\n\r\n"
			}
            cm.sendSimple(text);
        } else if (selection == 2) {
			cm.gainItem(5040000, 2);
			cm.setBossLog('zxsj');
            cm.sendOk("领取奖励成功！");
			cm.worldMessage(6,"玩家：["+cm.getName()+"]领取了60分钟在线奖励！.");
            cm.dispose();
        } else if (selection == 3) {
			//cm.gainExpR(20000);
			cm.gainItem(4000313, 20);
			cm.setBossLog('zxsj');
            cm.sendOk("领取奖励成功！");
			cm.worldMessage(6,"玩家：["+cm.getName()+"]领取了120分钟在线奖励！");
            cm.dispose();
        } else if (selection == 4) {
			//cm.gainMeso(50000);
			cm.gainItem(5150040, 2);
			//cm.gainItem(5121015,3,3);
			cm.setBossLog('zxsj');
            cm.sendOk("领取奖励成功！");
			cm.worldMessage(6,"玩家：["+cm.getName()+"]领取了180分钟在线奖励！");
            cm.dispose();
        } else if (selection == 5) {
			cm.gainItem(5390002, 3);//喇叭
			cm.gainItem(4001126, 100);//枫叶
			cm.setBossLog('zxsj');
            cm.sendOk("领取奖励成功！");
			cm.worldMessage(6,"玩家：["+cm.getName()+"]领取了240分钟在线奖励！");
            cm.dispose();
        } else if (selection == 6) {
			cm.gainDY(1000);//抵用券
			//cm.gainItem(5050000, 1, 7);//护身符
			cm.gainNX(1000);
			cm.setBossLog('zxsj');
            cm.sendOk("领取奖励成功！");
			cm.worldMessage(6,"玩家：["+cm.getName()+"]领取了300分钟在线奖励！");
            cm.dispose();
		} else if (selection == 7) {
			//cm.gainDY(200);//抵用券
			//cm.gainItem(5040000, 5, 7);//缩地石
			//cm.gainItem(5150040, 1, 15);//皇家
			//cm.gainDY(666);
			cm.gainItem(4001266, 1);//劳动奖章
			cm.gainItem(4032398, 1);//图章
			cm.gainExpR(666666);
			cm.setBossLog('zxsj');
            cm.sendOk("领取奖励成功！");
			cm.worldMessage(6,"玩家：["+cm.getName()+"]领取了360分钟在线奖励！");
            cm.dispose();
		}
    }
}


