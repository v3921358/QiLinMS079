var status = -1;

function start(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status == 6) {
	    qm.sendNext("我需要修煉這種東西需要意志和實力，超越您的老師是非常驚人的，但你不能讓你自己墜落下去，你必須不斷的修煉才能獲得強大的力量，同時找回失去的記憶。");
	    qm.dispose();
	    return;
	}
	status--;
    }
    if (status == 0) {
	qm.sendNext("……現在你的能力是什麼程度，我大概瞭解了……呵呵……沒想到我這把老骨頭還能有今天……真是感動得要流眼淚……不，是鼻涕……");
    } else if (status == 1) {
	qm.sendNextPrevS("#b(……也沒怎麼修煉嘛……)#k", 2);
    } else if (status == 2) {
	qm.sendNextPrev("好，現在讓我們開始第3階段的最後一階段的鍛煉。這次修煉的對象是……#r#o9300343##k！豬豬！你瞭解他們嗎？");
    } else if (status == 3) {
	qm.sendNextPrevS("一點點……", 2);
    } else if (status == 4) {
	qm.sendNextPrev("他們是天生的戰士！從出生的那一刻起，對食物就充滿了無窮的憤怒，凡是他們經過的地方都不會留下任何食物。很可怕吧？");
    } else if (status == 5) {
	qm.sendNextPrevS("#b(他不是在開玩笑吧？)#k", 2);
    } else if (status == 6) {
	qm.askAcceptDecline("來，快點#b再次進入修煉場#k，去和那些天生的戰士們修煉用的豬中戰鬥吧，打倒#r30只#k後，你的能力將會有一個質的飛躍。全力以赴地去戰鬥吧！超越我這個老師！");
    } else if (status == 7) {
	qm.forceStartQuest();
	qm.sendOk("快走吧！去打倒那些#o9300343#！");
	qm.dispose();
    }
}

function end(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status == 2) {
	    qm.sendNext("你捨不得離開老師？ 嗅嗅聞聞.... 我太感動了，但你不能到此為止！");
	    qm.dispose();
	    return;
	}
	status--;
    }
    if (status == 0) {
	qm.sendNext("這麼快就打倒了30只#o9300343#……我這老頭子果然沒有看錯。雖然你失去了曾經的記憶，失去了曾經的能力，但你仍然是個英雄！只要手上的長矛還在！");
    } else if (status == 1) {
	qm.sendNextPrevS("#b(這麼說是為了安慰我嗎？)#k", 2);
    } else if (status == 2) {
	qm.sendYesNo("我已經沒什麼可繼續教你的了。你已經超越了我這個老頭子。你可以下山了……唉，沒什麼好憂鬱的。我這老頭子能夠有機會指導你，已經很滿足了。");
    } else if (status == 3) {
	if (qm.getQuestStatus(21703) == 1) {
	    qm.forceCompleteQuest();
	    qm.teachSkill(21000000, qm.getPlayer().getSkillLevel(21000000), 10);   // Combo Ability Skill
	    qm.gainExp(2800);
	}
	qm.sendNextS("我想起了技能#b連擊能力#k！ 我還想跟著有點癡呆的老頭子訓練有沒有效果呢，沒想到真的有效！)", 2);
	qm.AranTutInstructionalBubble("Effect/BasicEff.img/AranGetSkill");
    } else if (status == 4) {
	qm.sendPrev("現在你回去找#p1201000#吧。他看到你的進步會很高興的！");
	qm.dispose();
    }
}