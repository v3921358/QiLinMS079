var status = -1;

function start(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 3) {
            qm.sendOk("有很緊急的事情。要是拒絕的話，肯定會後悔的哦？#b有關你長矛的事情#k，也就是有關你的過去。誰知道呢？……說不定這個長矛能夠喚醒你的能力？");
            qm.dispose();
            return;
        }
        status--;
    }
    if (status == 0) {
        qm.sendNext("...你問我為什麼這副德性嗎? ... 我不太想說...不，我無法對主人你隱瞞...");
    } else if (status == 1) {
        qm.sendNextPrev("...你被困在冰雪中數百年的時間...我也被困在冰雪中。漫長等待的歲月。沒有主人獨自生活真的太...因此我的心裡產生了黑暗。");
    } else if (status == 2) {
        qm.sendNextPrev("可是當你醒來後，黑暗完全消失了。主人回來了，就沒什麼好遺憾的。應該會忘的一乾二淨...可是那像是我的錯覺。");
    } else if (status == 3) {
        qm.askAcceptDecline("拜託你。狂狼勇士...請阻止我。可以停止我暴走的人只有主人你了。我無法再忍耐了！ 請你 #r擊敗暴走的我吧#k！")
    } else if (status == 4) {
        var em = qm.getEventManager("aran4th");
        var a1 = qm.getMap(914020000);
        if (qm.getQuestStatus(21401) == 0) {
            qm.forceStartQuest(21401);
        }
        if (em == null) {
            qm.sendNext("找不到副本，請聯絡管理員。");
        } else {
            if (a1.playerCount() <= 0) {
                a1.killAllMonsters(true);
                em.startInstance(qm.getPlayer());
                qm.dispose();
            } else {
                qm.sendNext("嗯...等一下。暴走實在是太嚴重了...這樣下去會受傷。");
            }
        }
    }
}

function end(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
        qm.sendNext("謝謝你。狂狼勇士。託你的福~即時阻止了暴走中的我。雖然想說真是太好了...不過你是主人，這本是就是理所當然的事。");
    } else if (status == 1) {
        qm.askAcceptDecline("看起來現在你的等級真的上升很多。居然可以擊敗暴走的我...就算喚醒從前的能力也可以充分的享受。");
    } else if (status == 2) {
        if (qm.getPlayerStat("RSP") > (qm.getPlayerStat("LVL") - 120) * 3) {
            qm.sendNext("技能點數沒點完。");
            qm.dispose();
        } else {
            if (qm.getJob() != 2112) {
                qm.changeJob(2112);
            }
            qm.gainItem(1142132, 1);
            qm.gainItem(2280003, 1);
        }
        qm.sendNext("你沉睡中的技能全部都喚醒了...已經遺忘了很久，需要再次修煉，但是只要練習就會有所幫助。");
    } else if (status == 3) {
        qm.sendNextPrev("啊，我順便把這期間得知的楓葉祝福技能做成技能書交給你。這是之前你沒有的技能，不過我想應該會有幫助吧！");
    } else if (status == 4) {
        qm.sendNextPrev("可是只憑這些技能，還跟你之前的力量相差甚遠。雖然聽說你失去的技能可用技能書找回...如果你能將技能全部找回來，熟練這些技能，那就跟真正的你相差不遠了。");
    } else if (status == 5) {
        qm.forceCompleteQuest();
        qm.dispose();
    }
}