var status = -1;

function start(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            qm.sendNext("有很緊急的事情。要是拒絕的話，肯定會後悔的哦？#b有關你長矛的事情#k，也就是有關你的過去。誰知道呢？……說不定這個長矛能夠喚醒你的能力？");
            qm.dispose();
            return;
        }
        status--;
    }
    if (status == 0) {
        qm.askAcceptDecline("修練還順利嗎？我知道你很忙，很抱歉打擾你，不過請快點跟我回來 #b瑞恩村#k。 #b瑪哈#k又有了奇怪的反應...好奇怪。跟之前的反應不一樣。好像更深沉更黑暗...我有這樣的感覺。");
    } else if (status == 1) {
        qm.forceStartQuest(21400);
        qm.dispose();
    }
}

function end(mode, type, selection) {

}