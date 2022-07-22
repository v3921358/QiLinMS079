/*
 * Cygnus 2nd Job advancement - Proof of test
 * Flame Wizard
 */

var status = -1;

function start(mode, type, selection) {
}

function end(mode, type, selection) {
    if (mode == 0) {
	if (status == 0) {
	    qm.sendNext("我猜你還沒準備好。");
	    qm.dispose();
	    return;
	} else if (status >= 2) {
	    status--;
	} else {
	    qm.dispose();
	    return;
	}
    } else {
	status++;
    }
    if (status == 0) {
	if (qm.getQuestStatus(20202) == 0) {
	    qm.forceStartQuest();
	    qm.dispose();
	} else {
	    if (qm.haveItem(4032097, 30)) {
		qm.sendYesNo("所以，你準備好二轉了？");
	    } else {
		qm.dispose(); // Hack
	    }
	}
    } else if (status == 1) {
	if (!qm.canHold(1142067)) {
	    qm.sendOk("請確認裝備欄是否足夠。");
	    qm.dispose();
	} else {
	    qm.forceCompleteQuest();
	    if (qm.getJob() != 1210) {
		qm.changeJob(1210); // Flame Wizard
		qm.gainItem(4032097, -30);
		qm.gainItem(1142067, 1);
	    }
	    qm.sendNext("訓練已經結束。你現在皇家騎士團的騎士官員。");
	}
    } else if (status == 2) {
	qm.sendPrev("好運！");
	qm.dispose();
    }
}