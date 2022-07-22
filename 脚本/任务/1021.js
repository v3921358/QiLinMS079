/* Author: Xterminator (Modified by RMZero213)
	NPC Name: 		Roger
	Map(s): 		Maple Road : Lower level of the Training Camp (2)
	Description: 		Quest - Roger's Apple
*/
var status = -1;

function start(mode, type, selection) {
    if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;
	
	if (status == 0) {
	    qm.sendNext("嗨, 怎麼了嗎? 我是羅傑，可以教你一些有用的知識");
	} else if (status == 1) {
		qm.sendNextPrev("你問我為什麼在這嗎? 哈哈哈!\r我想要教導那些剛進楓之谷的冒險者們。");
	} else if (status == 2) {
	qm.sendAcceptDecline("所以..... 讓我們來玩點有趣的~!");
	} else if (status == 3) {
	    if (!qm.haveItem(2010007)) {
		qm.gainItem(2010007, 1);
	    }
	    qm.sendNext("請把它吃完然後\r\n我等等會給你#r神秘小禮物#k。 請務必收下啊。 使用後你會變得更強壯。 打開消耗欄，雙擊一下蘋果 很簡單的，按一下鍵盤的 #bI#k就能了喔！");
	} else if (status == 4) {
	    qm.forceStartQuest();
	    qm.dispose();
	}
    }
}
        
function end(mode, type, selection) {
    if (mode == -1) {
	qm.dispose();
    } else {
	if (mode == 1)
	    status++;
	else
	    status--;
	if (status == 0) {
	    if (qm.getPlayerStat("HP") < 50) {
		qm.sendNext("哈囉，你還沒把我給你的蘋果吃掉啊，趕快吃了再來找我吧。");
		qm.dispose();
	    } else {
		qm.sendNext("你看～是不是很簡單？ 你可以在右側的欄位設定#b熱鍵#k。 哈哈，你聽不懂對吧？ 喔，每隔一段時間，血量就會恢復了。 雖然很花時間，但好好運用的話可以幫助不少的。");
	    }
	} else if (status == 1) {
	    qm.gainExp(10);
	    qm.gainItem(2010000, 3);
	    qm.gainItem(2010009, 3);
	    qm.forceCompleteQuest();
	    qm.dispose();
	}
    }
}