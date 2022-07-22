var status = -1;
var gailv = 50;//输入百分之几不要输入百分号只要数字在里面就行了!!

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
			cm.sendOk("感谢使用~!");
            cm.dispose();
        }
        status--;
    }
    if (status == 0) {
        if (cm.getInventory(1).getItem(1) == null) {
            cm.sendOk("装备栏第一格必须有物品才行哦~!");
            cm.dispose();
            return;
        }
		
		
				
		
		//if (cm.getInventory(1).getItem(1).getLevel() >= 5) {
         //   cm.sendOk("强化的装备等级不能大于#r120#k级哦~!\r\n当前#v"+Id+"##t"+Id+"# #b 已强化次数:#r"+cm.getInventory(1).getItem(1).getLevel()+"");
         //   cm.dispose();
        //    return;
        //}
		/*
		if (cm.getInventory(1).getItem(1).getUpgradeSlots() >= 5) {
			var Id = cm.getInventory(1).getItem(1).getItemId();
            cm.sendOk("可砸卷次数不能超过#r 5 #k次哦~!\r\n当前#v"+Id+"##t"+Id+"# #b 可砸卷次数为:#r"+cm.getInventory(1).getItem(1).getUpgradeSlots()+"");
            cm.dispose();
            return;
        }
		*/
		/*
		if ((cm.getInventory(1).getItem(1).getUpgradeSlots() + cm.getInventory(1).getItem(1).getLevel()) >= 12) {
			var Id = cm.getInventory(1).getItem(1).getItemId();
            cm.sendOk("可升级次数不能超过#r 12 #k次哦~!\r\n当前#v"+Id+"##t"+Id+"# #b 可强化次数为:#r"+(cm.getInventory(1).getItem(1).getUpgradeSlots() + cm.getInventory(1).getItem(1).getLevel())+"");

            cm.dispose();
            return;
        }
		*/
		
		
		if (!cm.haveItem(4000463,5) && cm.getMeso() < 500) {
            cm.sendOk("每次强化需要消耗#b5#k个#i4000463:#和500万金币");
            cm.dispose();
            return;
        }
		var Id = cm.getInventory(1).getItem(1).getItemId();
        var selStr = "您好欢迎来到#r强化中心#k (成功概率为50%)\r\n\r\n";
            selStr += "您要强化的道具是 #v"+Id+"##t"+Id+"# \r\n#b 目前可砸卷次数为:#r"+cm.getInventory(1).getItem(1).getUpgradeSlots()+"#b\r\n每次强化增加#r1#b次可砸卷次数,消耗#b5#k个#i4000463:#和500万金币.\r\n\r\n#r强化失败概率==50%，你确定要强化吗？";//当前已强化次数:#r"+cm.getInventory(1).getItem(1).getLevel()+"
        cm.sendYesNo(selStr);
    } else if (status == 1) {
		
		if (cm.getBossLog("强化次数4") == 5){
				cm.sendOk("你今天已经强化过5次了,请明天在来吧!");
				cm.dispose();
			} else if (cm.getInventory(1).getItem(1) == null)  {
				cm.sendOk("请把要强化的装备放在第一格才能进行.");
				cm.dispose();
			} else if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("限时装备不能进行强化.");
				cm.dispose();
			} else {
		
		
		s1 = Math.floor(Math.random() * (100 - 1) + 1);
		if(s1 <= gailv){
			cm.gainMeso(-50);
			cm.gainItem(4000463,-5);
			cm.getInventory(1).getItem(1).setUpgradeSlots(cm.getInventory(1).getItem(1).getUpgradeSlots()+1);
			cm.setBossLog('强化次数4');
			cm.刷新状态();
			cm.喇叭(2,"恭喜[" + cm.getPlayer().getName() + "]在土豪强化中升级了一次可强化次数！"); 
			//cm.全服红色喇叭("[恭喜] : " + " : " + "玩家 [" + cm.getChar().getName() + "]在土豪强化中升级了一次可强化次数！")
			cm.dispose();
		} else {
			cm.setBossLog('强化次数4');
			cm.gainMeso(-50);
			cm.gainItem(4000463,-5);
			cm.sendOk("强化失败~!");
            cm.dispose();
		}
    }
}
}