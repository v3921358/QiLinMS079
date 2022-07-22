var status = -1;

function start(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status == 6) {
	    qm.sendNext("有什麼好猶豫的呢？假如#p1201001#沒有反應，也沒什麼好失望的。快點去摸#p1201001#。在適當的地方#b點選#k就可以了。");
	    qm.dispose();
	    return;
	}
	status--;
    }
    if (status == 0) {
	qm.sendNextS("和黑魔法師決鬥的英雄...幾乎沒有任何相關的資訊保存下來。預言書上也只記錄有五名英雄，沒有任何和外貌有關的資料。你難道什麼都想不起來嗎？", 8);
    } else if (status == 1) {
	qm.sendNextPrevS("什麼都想不起來...", 2);
    } else if (status == 2) {
	qm.sendNextPrevS("原來如此。黑魔法師的詛咒不會那麼容易就被解除。可是就算如此，英雄您和過去之間應該有什麼連結。到底有什麼武器呢？因為決鬥的關係，武器或衣服都不見了...啊！對了！ #b武器#k！", 8);
    } else if (status == 3) {
	qm.sendNextPrevS("武器？", 2);
    } else if (status == 4) {
	qm.sendNextPrevS("之前在冰雪中挖掘英雄時曾經找到一些厲害的武器。當時推測應該是英雄使用過的東西，因此保存在村莊中央。您經過時沒看到嗎？ #b#p1201001##k... \r\r#i4032372#\r\r長成這樣...", 8);
    } else if (status == 5) {
	qm.sendNextPrevS("難怪我覺得很奇怪，有把 #p1201001# 就在村莊裡面...", 2);
    } else if (status == 6) {
	qm.askAcceptDecline("是，就是那個。根據紀錄英雄的武器會認主人。假如您是使用 #p1201001#的英雄，抓住 #p1201001#時應該會有什麼反應。快點去按#b#p1201001#吧。#k");
    } else if (status == 7) {
	if (qm.getQuestStatus(21100) == 0) {
	    qm.forceCompleteQuest();
	}
	qm.sendOkS("假如 #p1201001#有反應的話，您就使用#p1201001#的英雄 #b狂狼勇士#k。", 8);
	qm.showWZEffect("Effect/Direction1.img/aranTutorial/ClickPoleArm");
	qm.dispose();
    }
}

function end(mode, type, selection) {
    qm.dispose();
}
