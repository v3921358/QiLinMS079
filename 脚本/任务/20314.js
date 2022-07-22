/*
 * Cygnus 3rd Job advancement - Night Walker
 */

var status = -1;

function start(mode, type, selection) {
    if (mode == 0 && status == 1) {
	qm.sendNext("我猜你還沒準備好。");
	qm.dispose();
	return;
    } else if (mode == 0) {
	status--;
    } else {
	status++;
    }

    if (status == 0) {
	qm.sendNext("你所帶回來的寶石是神獸的眼淚，它擁有非常強大的力量。如果被黑磨法師給得手了，那我們全部都可能要倒大楣了....");
    } else if (status == 1) {
	qm.sendYesNo("女皇為了報答你的努力，將任命你為皇家騎士團的上級騎士，你準備好了嘛？");
    } else if (status == 2) {
	if (qm.getPlayerStat("RSP") > (qm.getPlayerStat("LVL") - 70) * 3) {
	    qm.sendNext("請確認你的技能點數點完沒。");
	} else {
	    if (qm.canHold(1142068)) {
		qm.gainItem(1142068, 1);
		qm.changeJob(1411);
		qm.gainAp(5);
		qm.sendOk("因為這一刻，你現在的騎士警長。從這一刻起，你應隨身攜帶自己以尊嚴和尊重你的相稱新標題天鵝騎士的騎士警長。");
	    } else {
		qm.sendOk("請先把道具欄空出一些空間哦。");
	    }
	}
	qm.dispose();
    }
}

function end(mode, type, selection) {
}