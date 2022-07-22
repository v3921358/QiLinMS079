var status = -1;

function start(mode, type, selection) {
}

function end(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
		if (status == 2) {
            qm.sendNext("#b等你考慮好在來找我談談吧！");
            qm.dispose();
            return;
        }
        status--;
    }
    if (status == 0) {
		qm.sendNext("喔~資格的象徵全部都拿來了嗎？你...比我想像中的還要強。可是我最欣賞的事你毫不畏懼可能會刺傷你的危險武器，爽快的說要帶走的態度...很好。#b巨大的斧#k送給你吧！");
	} else if (status == 1) {
        qm.sendNextPrev("#b(不久後 大長翁拿出來用布包裏的 巨大的矛。)");
	} else if (status == 2) {
		qm.askAcceptDecline("這就是為你製作的矛， 叫做 瑪哈...以後就請多關照。");
    } else if (status == 3) {
        qm.gainItem(4032311, -30);
		qm.warp(140030000,0);
        qm.forceCompleteQuest();
		qm.dispose();
    }
}