/*
*   麒麟服务端
*   By: QQ:327321366 
*/
var status = 0;
var 金币 = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var 礼包物品 = "#v2022256#";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            cm.sendSimple ("\t\t\t\t  #e"+礼包物品+" 技能学院 "+礼包物品+"#k#n\r\n您好,尊敬的#k #h ##k 你是想学习技能？①个技能费用:100万\r\n#r"+金币+"当前金币："+cm.判断金币() +"#k#n\r\n#L2##s1004#骑兽学习\r\n#L1##s8#群宠学习\r\n#L0##s1007#锻造学习\r\n#L3##s1013#皇家骑宠\r\n");
        } else if (status == 1) {
            switch(selection) {
        case 2:
		if (cm.getBossLog('骑兽技能') == 1) {
		    cm.sendOk("每个账号只能学习一次技能哦！");
		    cm.dispose();
		 } else {
		if (cm.getMeso() <= 1000000) {
		cm.sendOk("请检查是否有所需的冒险金币。");
	    } else {
			cm.gainMeso(-1000000);
            cm.teachSkill(1004,1,1);
			cm.setBossLog('骑兽技能');
		    cm.sendOk("成功学习了骑兽技能，赶紧去看看吧！");
		    cm.dispose();
		}
		} 
            break;
        case 1: 
		if (cm.getBossLog('群宠技能') == 1) {
		    cm.sendOk("每个账号只能学习一次技能哦！");
		    cm.dispose();
		 } else {
		if (cm.getMeso() <= 1000000) {
		cm.sendOk("请检查是否有所需的冒险金币。");
	    } else {
			cm.gainMeso(-1000000);
            cm.teachSkill(8,1,1);
			cm.setBossLog('群宠技能');
		    cm.sendOk("成功学习了群宠技能，赶紧去看看吧！");
		    cm.dispose();
		}
		} 
            break;
         case 0: 
		if (cm.getBossLog('锻造技能') == 1) {
		    cm.sendOk("每个账号只能学习一次技能哦！");
		    cm.dispose();
		 } else {
		if (cm.getMeso() <= 1000000) {
		cm.sendOk("请检查是否有所需的冒险金币。");
	    } else {
			cm.gainMeso(-1000000);
            cm.teachSkill(1007,3,1);
			cm.setBossLog('锻造技能');
		    cm.sendOk("成功学习了锻造技能，赶紧去看看吧！");
		    cm.dispose();
		}
		}
            break;
            case 3: 
        if (cm.getBossLog('皇家骑宠技能') == 1) {
		    cm.sendOk("每个账号只能学习一次技能哦！");
		    cm.dispose();
		 } else {
		if (cm.getMeso() <= 1000000) {
		cm.sendOk("请检查是否有所需的冒险金币。");
	    } else {
			cm.gainMeso(-1000000);
            cm.teachSkill(1013,1,1);
			cm.setBossLog('皇家骑宠技能');
		    cm.sendOk("成功学习了皇家骑宠技能，赶紧去看看吧！");
		    cm.dispose();
		}
		}
            break;
            case 4: 
            if(cm.haveItem(4000313,100)){
                //cm.gainDY(100);
                cm.gainMeso(15000000);
				cm.gainItem(4000313,-100);
				cm.sendOk("恭喜你，你获得了 15000000 金币! .");
			        cm.worldMessage(6,"【兑换系统】["+cm.getName()+"]100张黄金枫叶兑换15000000金币成功！");
				cm.dispose();
            }else{
                cm.sendOk("你没有 足够的 黄金枫叶，我不能给你换购~.");
                cm.dispose();
            }
            break;
            case 5: 
            if(cm.haveItem(4032226,100)){
                //cm.gainDY(100);
                cm.gainvip(+10);
				cm.gainItem(4032226,-100);
				cm.sendOk("恭喜你，你提升了10级VIP等级! .");
			        cm.worldMessage(6,"【兑换系统】["+cm.getName()+"]100只黄金猪猪提升10级VIP等级!成功！");
				cm.dispose();
            }else{
                cm.sendOk("你没有 足够的 黄金猪猪，我不能给你换购~.");
                cm.dispose();
            }
            break;
           case 6: 
            if(cm.haveItem(4001126,100)){
                cm.gainItem(4000313,10);
				cm.gainItem(4001126,-100);
				cm.sendOk("恭喜你，你获得了 10张黄金枫叶! .");
			        cm.worldMessage(6,"【兑换系统】["+cm.getName()+"]100张枫叶兑换10张黄金枫叶成功！");
				cm.dispose();
            }else{
                cm.sendOk("你没有 足够的 枫叶，我不能给你换购~.");
                cm.dispose();
            }
            break;
            case 7: 
            if(cm.getMeso() >= 10000000){
                cm.sendOk("恭喜你，你获得了 2000000 经验值! .");
                cm.gainMeso(-10000000);
                cm.gainExp(2000000);
                cm.dispose();
            }else{
                cm.sendOk("你没有 10000000 金币，我不能给你换购~.");
                cm.dispose();
            }
            break;
            case 8: 
            if(cm.getMeso() >= 100000000){
                cm.sendOk("恭喜你，你获得了 50000000 经验值! .");
                cm.gainMeso(-100000000);
                cm.gainExp(50000000);
                cm.dispose();
            }else{
                cm.sendOk("你没有 1亿 金币，我不能给你换购~.");
                cm.dispose();
            }
            break;
            }
        }
    }
}