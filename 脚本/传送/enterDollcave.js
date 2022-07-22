function enter(pi) {
    if (pi.getQuestStatus(21731) == 1) {
        pi.openNpc(1063011);
	} else if (pi.getQuestStatus(21728) == 1) {
		pi.forceCompleteQuest(21728);
		pi.gainExp(200);
		pi.playerMessage(5, "任務完成。");
    } else {
        pi.playerMessage(5, "因不明的力量，而無法進入此洞穴。");
        return false;
    }
	return true;

}
