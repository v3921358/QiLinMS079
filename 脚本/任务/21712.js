var status = -1;

function start(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 2) {
            qm.sendNext("你還是不瞭解發生了甚麼事情嗎? 我很樂意再次說明給你聽");
            qm.dispose();
            return;
        }
        status--;
    }
    if (status == 0) {
        qm.sendNext("#t4032315#... #r這個娃娃製造著奇怪的噪音#k. 你無法用你的耳朵聽見 , 因為只有 #o1210102# 能聽到奇怪的噪音. 我相信就是因為這個原因導致 #o1210102# 的個性變得兇暴.");
    } else if (status == 1) {
        qm.askAcceptDecline("#o1210102# 因為受到噪音的影響變成了憤世嫉俗的菇菇寶貝,且開始攻擊了沒受到噪音影響的 #o1210102#s, 導致了所有的 #o1210102# 都在備戰狀態. #造成所有的 #o1210102# 改變的原因就是因為這個娃娃#k! 瞭解了嗎?");
    } else if (status == 2) {
        qm.forceStartQuest();
        qm.sendNextS("我發現這是第一次菇菇寶貝發生變異, 且沒有原因可以說明這個娃娃是自然產生的, 也就是說一切都是有人在幕後指使的. 最近我應該多注意 #o1210102#.", 9);
    } else if (status == 3) {
        qm.sendPrevS("#b(你可以去找出是甚麼導致了 #o1210102# 的變異, 接著回報給 #p1002104# 所有收集到的情報.)#k", 2);
        qm.dispose();
    }
}

function end(mode, type, selection) {
    qm.dispose();
}