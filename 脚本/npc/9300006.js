var status = 0
var chair = 3012003

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
	if(status == 1){
		cm.sendYesNo("请问是否要领取#v"+chair+"#新婚大礼包");
	} else if(status == 2){
		if(cm.getPlayer().getMarriageId() >= 0) {
			if(!cm.canHold(chair)){
				cm.sendNext("背包空间不足");
				cm.dispose();
				return;
			} else if(cm.getPlayer().getOneTimeLog("LoveChair1") > 0){
				cm.sendNext("你已经领取过结婚礼包");
				cm.dispose();
				return;
			}
			var equip = cm.getEquip(1012057);
            equip.setStr(15);//力量
            equip.setDex(15);//敏捷
            equip.setInt(15);//智力
            equip.setLuk(15);//运气
			equip.setWatk(10);//攻击
            equip.setMatk(10);//魔力
            equip.setHp(520);//hp
            equip.setMp(520);//mp
            equip.setWdef(100);//物防
            equip.setMdef(100);//魔防
            cm.addbyItem(equip);	
		    //cm.gainItem(5211060,1,10);
			cm.getPlayer().setOneTimeLog("LoveChair1");
			cm.gainItem(chair, 1);
			cm.sendNext("#v"+chair+"#已经放到了你的背包");
		cm.dispose();
		}
else {
cm.sendNext("你还没有结婚哦!单身狗无法领取!");		
cm.dispose();
}
	} else {
		cm.dispose();
	}
}