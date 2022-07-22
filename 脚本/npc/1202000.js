/*
 * Tutorial Lirin
 */

var status = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	status--;
    }
if (cm.getPlayer().getMapId() != 140090000) {
if (status == 0) {
	cm.sendSimple("您有什麼想知道的呢？有的話我會再度說明。  \n\r #b#L1#該如何使用小地圖？#l \n\r #b#L2#該如何使用任務欄位？#l \n\r #b#L3#該如何使用道具？#l \n\r #b#L4#如何使用普通攻擊？#l \n\r #b#L5#如何撿取東西？#l \n\r #b#L6#如何穿裝備？#l \n\r #b#L7#如何打開技能視窗？#l \n\r #b#L8#如何把技能放到快捷鍵上？#l \n\r #b#L9#如何打破一個箱子？#l \n\r #b#L10#如何坐椅子？#l \n\r #b#L11#如何查看地圖資訊？#l");
} else {
    cm.summonMsg(selection);
    cm.dispose();
}
} else {
    if (cm.getInfoQuest(21019).equals("")) {
	if (status == 0) {
	    cm.sendNext("您....終於醒了!");
	} else if (status == 1) {
	    cm.sendNextPrevS("...你是誰?", 2);
	} else if (status == 2) {
	    cm.sendNextPrev("我已經在這等你好久了. 等待那個與黑磨法師對抗的英雄甦醒...!");
	} else if (status == 3) {
	    cm.sendNextPrevS("等等, 你說什麼..? 你是誰...?", 2);
	} else if (status == 4) {
	    cm.sendNextPrevS("等等... 我是誰...? 我既不起以前的事情了... 啊...我頭好痛啊..", 2);
	} else if (status == 5) {
	    cm.updateInfoQuest(21019, "helper=clear");
	    cm.showWZEffect("Effect/Direction1.img/aranTutorial/face");
	    cm.showWZEffect("Effect/Direction1.img/aranTutorial/ClickLirin");
	    cm.playerSummonHint(true);
	    cm.dispose();
	}
    } else {
	if (status == 0) {
	    cm.sendNext("你還好嗎？");
	} else if (status == 1) {
	    cm.sendNextPrevS("我... 什麼都不記得了...這裡是哪裡？還有你是誰？", 2);
	} else if (status == 2) {
	    cm.sendNextPrev("放輕鬆. 因為黑磨法師的詛咒，讓你想不起以前的了. 以前的事情已經不重要了. 我會幫助你想起所有事情的.");
	} else if (status == 3) {
	    cm.sendNextPrev("你曾經是這裡的英雄. 幾百年以前, 你與你的朋友們對抗黑魔法師，拯救了楓之谷的世界. 但那個時候，黑磨法師對你下了詛咒，將你冰凍起來，直到抹去你所有的記憶為止.");
	} else if (status == 4) {
	    cm.sendNextPrev("這裡是瑞恩島。黑魔法師將您囚禁在此地。詛咒的氣候混亂，經年覆蓋冰霜和雪。您在冰之窟的深處被發現的。");
	} else if (status == 5) {
	    cm.sendNextPrev("我的名字是#p1202000#。 是瑞恩島的成員。瑞恩族根據古老的預言從很久以前就等待英雄回來。還有...終於找到您了。現在。就是這裡....");
	} else if (status == 6) {
	    cm.sendNextPrev("好像一下說太多了。就算您不能馬上了解也沒有關係。您會慢慢知道所有事....#b我們先去村莊吧#k。在抵達村莊之前，如果還有什麼想知道，我會逐一向您說明。");
	} else if (status == 7) {
	    cm.playerSummonHint(true);
	    cm.warp(140090100, 1);
	    cm.dispose();
	}
    }
}
}