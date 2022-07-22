var status = -1;

function start(mode, type, selection) {
    if (mode == -1) {
        qm.dispose();
    } else {
        if (mode == 1) {
            status++;
        } else {
            status--;
        }
        if (status == 0) {
            qm.sendNext("很不錯啊，已經成功獲取了狼神。但是乘騎的話還是需要特製的鞍子才能騎上去的。如果由於材料不夠，我無發給你`你所需要的東西。");
        } else if (status == 1) {
            qm.sendAcceptDecline("別著急，雖然我這裡沒有足夠的材料。但是也不是沒辦法了。這樣吧，你幫我去搜索#b小白雪人的皮50個#k來吧！我就可以給你做了！");
        } else if (status == 2) {
            qm.forceStartQuest();
            qm.sendNext("快去快回啊，好冷！記好是 #b50個小白雪人的皮#k。");
        } else if (status == 3) {
            qm.dispose();
        }
    }
}