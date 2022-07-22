var status = -1;

function start(mode, type, selection) {
}

function end(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
        qm.askAcceptDecline("野烏鴉抓到了嗎？呵呵呵...果然是我的主人！很好，那麼將帶來的 紅珠玉交出來！我會重新放在本體上...咦...？為什麼不回答？該不會...忘記帶回來了吧？");
    } else if (status == 1) {
        qm.sendNext("什麼？你真的沒拿回 紅珠玉？為什麼？該不會是真的忘了吧？啊啊！怎麼會這樣...就算被黑魔法師詛咒，經過了這麼久冰雪封印都解除了，健忘症倒是還沒解除啊...");
    } else if (status == 2) {
        qm.sendNextPrev("不行。真的太不像話了。這個時候我更應該代替主人打起精神...呼呼...呼呼......");
    } else if (status == 3) {
        qm.sendNextPrev("再去看看，反正小偷已經逃走了。那麼就重新製作 紅珠玉吧！之前曾經做過一次，你知道材料吧？好吧！那麼快去蒐集材料吧...");
    } else if (status == 4) {
        qm.sendNextPrev("   #i4001173#");
    } else if (status == 5) {
        qm.sendNextPrev("材料也沒有，而且還不知道怎麼做.....沒有夢也沒有希望。啊啊啊！");
    } else if (status == 6) {
        qm.sendNextPrevS("#b(瑪哈開始大發雷霆。先逃離這裡再說。莉琳可能可以幫我。)");
        qm.forceCompleteQuest();
        qm.dispose();
    }
}