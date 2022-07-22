/*
    Zakum Entrance
*/

function enter(pi) {
    if (pi.getQuestStatus(100200) != 2) {
	pi.playerMessage(5, "您好像還沒準備好面對BOSS。");
	return false;

    } else if (!pi.haveItem(4001017)) {
	pi.playerMessage(5, "由於你沒有火眼之眼，所以不能挑戰殘暴炎魔。");
	return false;
    }
    
    pi.playPortalSE();
    pi.warp(pi.getPlayer().getMapId() + 100, "west00");
    return true;
}