// 狩獵玩偶 and 消滅傀儡師！
var status = -1;

function action(mode, type, selection) {
	if (mode == 1) {
		status++;
	} else {
		status--;
	}
	if (status == 0) {
		if (cm.getQuestStatus(21731) == 1 && cm.getPlayer().isAran() || cm.getQuestStatus(20730) == 1 && cm.getPlayer().isKOC()) {
			cm.sendNextS("我是#p1204001#黑色翅膀的成員，你怎麼敢來打擾我呢?? 你害我的老毛病又犯了，我發誓要效忠於黑魔法師，要是我抓住你了，我會讓你付出代價的！", 9);
			status++;
		} else {
			cm.sendNext("沒事別來妨礙我。");
			cm.dispose();
		}
	} else if (status == 1) {
		cm.sendNextPrevS("#b(黑色翅膀? 他們是誰? 而怎麼會又跟黑魔法師扯到關係，也許該報告才對。)#k", 3);
	} else if (status == 2) {
		if (cm.getQuestStatus(21731) == 1 && cm.getPlayer().isAran()) {
			var em = cm.getEventManager("FrancisAran");
			if (em == null) {
				cm.sendOk("當前副本有問題，請聯絡管理員....");
			} else {
				var prop = em.getProperty("started");

				if (prop.equals("0") || prop == null) {
					em.startInstance(cm.getPlayer());
					cm.dispose();
					return;
				} else {
					cm.sendOk("裡面已經有人在挑戰...");
				}
			}
			cm.dispose();
		} else if (cm.getQuestStatus(20730) == 1 && cm.getPlayer().isKOC()) {
			var em = cm.getEventManager("Francis");
			if (em == null) {
				cm.sendOk("當前副本有問題，請聯絡管理員....");
			} else {
				var prop = em.getProperty("started");

				if (prop.equals("0") || prop == null) {
					em.startInstance(cm.getPlayer());
					cm.dispose();
					return;
				} else {
					cm.sendOk("裡面已經有人在挑戰...");
				}
			}
			cm.dispose();
		}
	}
}
