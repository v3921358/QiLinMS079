var status = -1;

function start(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status == 4) {
	    qm.sendNext("哎呀？ 你要拒絕, 是覺得自己一人也可以修練的意思嗎？ 比起自己一個人，接受其他人的幫助應該可以得到更好的結果啊. 現在也該學學跟其他人相處的方法了.");
	    qm.dispose();
	    return;
	}
	status--;
    }
    if (status == 0) {
	qm.sendNextS("一副好像想起什麼的臉 果然終極之矛認出你的樣子. 那麼你就是#b使用過終極之矛的英雄, 狂狼勇士#k沒錯. 這以外還沒有想起什麼呢？ 例如與終極之矛相關的技能...", 8);
    } else if (status == 1) {
	qm.sendNextPrevS("#b(告訴我了幾個記得的技能.)#k", 2);
    } else if (status == 2) {
	qm.sendNextPrevS("雖然不多但也有收穫了. 那麼從現在起要用盡全力找回以前的能力了. 雖然喪失記憶, 但至少是曾經做過的事，一定可以很快地找回能力的。", 8);
    } else if (status == 3) {
	qm.sendNextPrevS('要怎麼找回能力呢？', 2);
    } else if (status == 4) {
	qm.askAcceptDecline("這個嘛... 只有一個辦法. 修練! 修練! 修練! 修練在修練的話，總有一天身體可以找回那個被遺忘的感覺！");
    } else if (status == 5) {
	qm.forceStartQuest();
	qm.sendNext("如果使用更熟悉的武器應該會更好 已給您#b終極之矛#k 一把在修練時可以有效使用. 帶著那個武器...");
    } else if (status == 6) {
	qm.sendPrev("厄嗯, 稍等. 現在開始該怎麼做才好呢........");
	qm.dispose();
    }
}

function end(mode, type, selection) {
    qm.dispose();
}