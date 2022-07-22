/* 
 * NPC :      Mihai
 * Map :      Timu's Forest
 */

function start() {
    cm.sendNext("呵呵...難道我發現了什麼東西嗎？那麼只有一個辦法了！出來吧！!");
}

function action(mode, type, selection) {
    if (mode == 1) {
	cm.removeNpc(cm.getMapId(), cm.getNpc());
	cm.spawnMonster(9001009,1); // Transforming
    }
    cm.dispose();
}