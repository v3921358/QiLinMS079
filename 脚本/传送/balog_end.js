function enter(pi) {
	if (!pi.canHold(4001261,1)) {
		pi.playerMessage(5, "包包滿了,請先空出空間.");
		return false;
	}
	pi.gainExpR(pi.getPlayer().getMapId() == 105100301 ? 260000 : 520000);
	//pi.gainNX(pi.getPlayer().getMapId() == 105100301 ? 2000 : 3000);
	pi.gainItem(4001261,1);
	pi.warp(105100100,0);
	pi.playPortalSE();
}