var status = -1;

function start(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status == 0) {
	    qm.sendNext("您還沒準備好獵殺 #o0100132#嗎？ 最好把該準備的都準備好再去狩獵比較好。如果不好好準備，在途中一命嗚呼了，那只會讓人遺淺罷了！");
	    qm.dispose();
	    return;
	}
	status--;
    }
    if (status == 0) {
	qm.askAcceptDecline("那麼要繼續基礎體力鍛鍊嗎？準備好了嗎？請您在確認劍是否裝備好了，技能和藥是否已經放入快捷欄內，然後就開始吧！");
    } else if (status == 1) {
	qm.forceStartQuest();
	qm.AranTutInstructionalBubble("Effect/OnUserEff.img/guideEffect/aranTutorial/tutorialArrow3");
	qm.dispose();
    }
}

function end(mode, type, selection) {
    qm.dispose();
}
