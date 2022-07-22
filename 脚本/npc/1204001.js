/*
 * NPC : Francis (Doll master)
 * Map : 910510200
 */

var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	status--;
    }
    if (status == 0) {
	cm.sendNextS("我是#p1204001#黑色翅膀的成員，你怎麼敢來打擾我呢?? 你害我的老毛病又犯了，我發誓要效忠於黑魔法師，要是我抓住你了，我會讓你付出代價的！", 9);
    } else if (status == 1) {
	cm.sendNextPrevS("#b(黑色翅膀? 他們是誰? 而怎麼會又跟黑魔法師扯到關係，也許該報告才對。)#k", 3);
    } else if (status == 2) {
	cm.forceStartQuest(21760, "0");
	cm.warp(105070300, 3);
	cm.dispose();
    }
}