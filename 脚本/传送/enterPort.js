function enter(pi) {
    if (pi.getQuestStatus(21301) == 1) {
        var em = pi.getEventManager("aran3rd");
        if (em == null) {
            pi.playerMessage("找不到腳本，請聯絡管理員。");
        } else {
            em.startInstance(pi.getPlayer());
        }
    } else {
        pi.warp(140020300, 1);
    }
    return true;
}