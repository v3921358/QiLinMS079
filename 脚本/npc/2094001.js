var status = -1;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
	cm.removeAll(4001117);
	cm.removeAll(4001120);
	cm.removeAll(4001121);
	cm.removeAll(4001122);
	//cm.sendSimple("#b#L0#我要離開#l\r\n#L1#我要兌換海賊王帽子#l#k");
	cm.sendSimple("#b#L0#我要離開#l\r\n#k");
    } else if (status == 1) {
	if (selection == 0) {
	    cm.warp(251010404,0);
	} else {
	    var cmp = cm.getPlayer().getOneInfo(1204, "cmp");
	    var have0 = cm.getPlayer().getOneInfo(1204, "have0");
	    var have1 = cm.getPlayer().getOneInfo(1204, "have1");
	    var have2 = cm.getPlayer().getOneInfo(1204, "have2");
	    var have3 = cm.getPlayer().getOneInfo(1204, "have3");
	    if (cmp == null) {
		cm.sendOk("30 場海盜PQ = #t1002571#\r\n80 場海盜PQ = #t1002572#\r\n200 場海盜PQ = #t1002573#\r\n350 場海盜PQ = #t1002574#");
	    } else {
		var cmp_i = parseInt(cmp);
		var have0_i = parseInt(have0);
		var have1_i = parseInt(have1);
		var have2_i = parseInt(have2);
		var have3_i = parseInt(have3);
		if (have3_i > 0) {
		    if (cm.canHold(1002574,1)) {
		    	cm.gainItem(1002574,1);
			cm.sendOk("我已經給你帽子了。");
		    } else {
			cm.sendOk("我已經給你帽子了但如果你想要其它的，請清出裝備欄空間。");
		    }
		} else if (have2_i > 0) {
		    if (cmp_i >= 350) {	
			if (cm.canHold(1002574,1)) {
		    	    cm.gainItem(1002574,1);
			    cm.sendOk("我已經給你帽子了。");
		    	} else {
			    cm.sendOk("請清出裝備欄空間。");
		        } 
		    } else {
			cm.sendOk("你需要有玩30場 目前總共 : " + cmp_i);
		    }
		} else if (have1_i > 0) {
		    if (cmp_i >= 200) {	
			if (cm.canHold(1002573,1)) {
		    	    cm.gainItem(1002573,1);
			    cm.sendOk("我已經給你帽子了。");
		    	} else {
			    cm.sendOk("請清出裝備欄空間。");
		        } 
		    } else {
			cm.sendOk("你需要有玩200場 目前總共 : " + cmp_i);
		    }
		} else if (have0_i > 0) {
		    if (cmp_i >= 80) {	
			if (cm.canHold(1002572,1)) {
		    	    cm.gainItem(1002572,1);
			    cm.sendOk("我已經給你帽子了。");
		    	} else {
			    cm.sendOk("請清出裝備欄空間。");
		        } 
		    } else {
			cm.sendOk("你需要有玩80場 目前總共 : " + cmp_i);
		    }
		} else {
		    if (cmp_i >= 30) {	
			if (cm.canHold(1002571,1)) {
		    	    cm.gainItem(1002571,1);
			    cm.sendOk("我已經給你帽子了。");
		    	} else {
			    cm.sendOk("請清出裝備欄空間。");
		        } 
		    } else {
			cm.sendOk("你需要有玩30場 目前總共 : " + cmp_i);
		    }
		}
	    }
	}
	cm.dispose();
    }
}