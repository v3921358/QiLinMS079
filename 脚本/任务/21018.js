var status = -1;

function start(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status == 1) {
	    qm.sendNext("什麼，該不會是嫌5隻太少了吧？如果是為了修煉多擊退一些的話也是沒關係。為了英雄，我雖然心痛，但是也會睜一隻眼閉一隻眼");
	    qm.dispose();
	    return;
	}
	status--;
    }
    if (status == 0) {
	qm.sendNext("好！那麼現在要進行基礎體力的測試。方法很簡單。只要擊敗島上最強最兇惡的怪物， #o0100134# 就可以了！如果可以擊敗 #r50隻#k 那就太好了，可是...");
    } else if (status == 1) {
	qm.askAcceptDecline("如果將僅剩沒幾隻的 #o0100134#全部擊退，對生態界似乎會造成不好的影響，那麼請擊敗5隻。考慮到自然和環境的鍛煉！啊！真太美了...");
    } else if (status == 2) {
	qm.forceStartQuest();
	qm.AranTutInstructionalBubble("Effect/OnUserEff.img/guideEffect/aranTutorial/tutorialArrow1");
	qm.dispose();
    }
}

function end(mode, type, selection) {
    qm.dispose();
}
