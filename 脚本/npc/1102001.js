/* 
 *  NPC   : Kiriko
 *  Maps  : Training Hall 2
 *  FUNC  : Second job Advancement
 */

function start() {
    cm.askAcceptDecline("你想離開？？");
}

function action(mode, type, selection) {
    if (mode == 0) {
	cm.sendNext("你可能需要一些時間。.");
    } else {
	cm.warp(130020000, 0);
    }
    cm.dispose();
}