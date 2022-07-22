function action(mode, type, selection) {
    var em = cm.getEventManager("Juliet");
    if (em == null) {
	cm.sendOk("找不到腳本，請聯繫GM！");
	cm.dispose();
	return;
    }
    switch(cm.getPlayer().getMapId()) {
	case 261000021:
	    cm.removeAll(4001130);
	    cm.removeAll(4001131);
	    cm.removeAll(4001132);
	    cm.removeAll(4001133);
	    cm.removeAll(4001134);
	    cm.removeAll(4001135);
	    if (cm.getPlayer().getParty() == null || !cm.isLeader()) {
		cm.sendOk("請找隊長來和我談。");
	    } else {
		var party = cm.getPlayer().getParty().getMembers();
		var mapId = cm.getPlayer().getMapId();
		var next = true;
		var size = 0;
		var it = party.iterator();
		while (it.hasNext()) {
			var cPlayer = it.next();
			var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
			if (ccPlayer == null || ccPlayer.getLevel() < 70 || ccPlayer.getLevel() > 200) {
				next = false;
				break;
			}
			size += (ccPlayer.isGM() ? 4 : 1);
		}	
		if (next && (cm.getPlayer().isGM() || size >= 4)) {
			em.startInstance(cm.getPlayer().getParty(), cm.getPlayer().getMap());
		} else {
			cm.sendOk("請你的隊員大於4個人(含)都要70等以上(含)200等以下(200也可)都要在這張地圖再來找我");
		}
	    }
	    break;
	case 926110000:
	    cm.sendOk("你應該嘗試在這裡調查各地。看看庫中的文件，直到你可以找到入口實驗室.");
	    break;
	case 926110001:
	    cm.sendOk("請消除所有的怪物。");
	    break;
	case 926110100:
	    cm.sendOk("請把燒杯裡的溢體裝滿。");
	    break;
	case 926110200:
	    if (cm.haveItem(4001131,1)) {
		cm.sendOk("哦，我的信找到了，謝謝！");
		cm.gainItem(4001131,-1);
		em.setProperty("stage", "1");
	    } else if (cm.haveItem(4001134,1)) {
		cm.gainItem(4001134,-1);
		cm.sendOk("謝謝你，現在幫我找#t4001135#.");
		em.setProperty("stage4", "1");
	    } else if (cm.haveItem(4001135,1) && em.getProperty("stage4").equals("1")) {
		cm.gainItem(4001135,-1);
		cm.sendOk("謝謝你，已經過關了。.");
		em.setProperty("stage4", "2");
		cm.getMap().getReactorByName("jnr3_out3").hitReactor(cm.getClient());
	    } else {
	    	cm.sendOk("現在我們必須停止衝突，請幫我找出#t4001134# 和 #t4001135#。");
	    }
	    break;
	case 926100300:
	    cm.sendOk("我們一定要到實驗室的頂部.");
	    break;
	case 926100400:
	    cm.sendOk("當你準備好了，我們要快去救救我的愛人.");
	    break;
	case 926110401:
	    cm.warpParty(926110500); //urete
	    break;
    }
    cm.dispose();
}