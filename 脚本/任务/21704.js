var status = -1;

function start(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 2) {
            qm.dispose();
            return;
        }
        status--;
    }
    if (status == 0) {
        qm.sendNextS("修煉進行的不錯吧？企鵝老師#p1202006#個性很強，我還擔心他要是癡呆了就不好辦了……他對英雄的技能確實很有研究，對你應該能幫上不少忙。", 8);
    } else if (status == 1) {
        qm.sendNextPrevS("#b(告訴他自己回想起來連擊能力這個技能。)#k", 2);
    } else if (status == 2) {
        qm.askAcceptDecline("是嗎！看來除了#p1202006#的訓練方式之外，你自己仍然記的從前的那些技能也很關鍵啊……看來只是在冰窖裡封凍的太久，需要時間恢復而已。#b繼續加油修煉吧，爭取早日恢復所有的技能！#k  \r\n\r\n#fUI/UIWindow.img/QuestIcon/8/0# 500 exp");
    } else if (status == 3) {
        qm.forceCompleteQuest();
        qm.gainExp(500);
        qm.dispose();
    }
}

function end(mode, type, selection) {
    qm.dispose();
}