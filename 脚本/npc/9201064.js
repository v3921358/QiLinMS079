﻿/* Mani
	NLC VIP Hair/Hair Color Change.
*/
var status = -1;
var beauty = 0;
var hair_Colo_new;

function start() {
    action(1, 0, 0);
}

//function action(mode, type, selection) {
    if (mode == 0) {
	cm.dispose();
	return;
    } else {
	status++;
    }

    if (status == 0) {
	cm.sendSimple("嗨，我是#p9201064# 如果您有 #b#t5150031##k 或者有 #b#t5151026##k 我就可以幫助您~ 選擇您想要的服務 \r\n#L0#使用: #i5150031##t5150031##l\r\n#L1#使用: #i5151026##t5151026##l");
    } else if (status == 1) {
	if (selection == 0) {
	    var hair = cm.getPlayerStat("HAIR");
	    hair_Colo_new = [];
	    beauty = 1;

	    if (cm.getPlayerStat("GENDER") == 0) {
		hair_Colo_new = [30250, 30110, 30230, 30050, 30280, 30410, 30730, 30160, 30200];
	    } else {
		hair_Colo_new = [31150, 31310, 31220, 31300, 31260, 31160, 31730, 31410, 31410];
	    }
	    for (var i = 0; i < hair_Colo_new.length; i++) {
		hair_Colo_new[i] = hair_Colo_new[i] + (hair % 10);
	    }
	    cm.askAvatar("選擇一個喜歡的", hair_Colo_new);
	} else if (selection == 1) {
	    var currenthaircolo = Math.floor((cm.getPlayerStat("HAIR") / 10)) * 10;
	    hair_Colo_new = [];
	    beauty = 2;

	    for (var i = 0; i < 8; i++) {
		hair_Colo_new[i] = currenthaircolo + i;
	    }
	    cm.askAvatar("選擇一個喜歡的", hair_Colo_new);
	}
    } else if (status == 2){
	if (beauty == 1) {
	    if (cm.setAvatar(5150031, hair_Colo_new[selection]) == 1) {
		cm.sendOk("享受！");
	    } else {
		cm.sendOk("痾...貌似沒有#b#t5150031##k");
	    }
	} else {
	    if (cm.setAvatar(5151026, hair_Colo_new[selection]) == 1) {
		cm.sendOk("享受！");
	    } else {
		cm.sendOk("痾...貌似沒有#b#t5151026##k");
	    }
	}
	cm.dispose();
    }
}