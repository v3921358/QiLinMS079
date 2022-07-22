/* Dances with Balrog
 Warrior Job Advancement
 Victoria Road : Warriors' Sanctuary (102000003)
 
 Custom Quest 100003, 100005
 */

var status = 0;
var jobId;
var jobName;


function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && status == 2) {
        cm.sendOk("請重試.");
        cm.dispose();
        return;
    }
    if (mode == 1)
        status++;
    else
        status--;
    if (status == 0) {
        if (cm.getJob() == 0) {
            if (cm.getPlayer().getLevel() >= 10) {
                cm.sendNext("你要轉職成為一位 #r劍士#k ?");
            } else {
                cm.sendOk("你還不能轉職成為 #r劍士#k 蔡B8.");
                cm.dispose();
            }
        } else {
            if (cm.getPlayer().getLevel() >= 30 && cm.getJob() == 100) { // 劍士
                if (cm.haveItem(4031012, 1)) {
                    if (cm.haveItem(4031012, 1)) {
                        status = 20;
                        cm.sendNext("我看到你完成了測試. 想要繼續轉職請點下一頁!");
                    } else {
                        if (!cm.haveItem(4031008)) {
                            cm.gainItem(4031008, 1);
                        }
                        cm.sendOk("請去找 #r劍士轉職教官#k.")
                        cm.dispose();
                    }
                } else {
                    status = 10;
                    cm.sendNext("你已經可以轉職了,要轉職請點下一頁.");
                }
            } else if (cm.getPlayer().getLevel() >= 70 && cm.getJob() == 110 || cm.getJob() == 120 || cm.getJob() == 130) {
                if (cm.canHoldByType(4, 2)) {
                    if (cm.haveItem(4031059, 1)) {
                        cm.gainItem(4031057, 1);
                        cm.gainItem(4031059, -1);
                        cm.warp(211000001, 0);
                        cm.sendOk("你完成了一個考驗，現在去找 #b泰勒斯#k.");
                    } else {
                        cm.sendOk("嗨, #b#h0##k! 我需要一個 #b黑符#k. 快去找異次元空間拿給我");
                    }
                } else {
                    cm.sendOk("你的背包沒有多餘的3個空格。");
                    cm.dispose();
                    return;
                }
                cm.dispose();
            } else {
                cm.sendOk("你好,我是劍士轉職官.");
                cm.dispose();
            }
        }
    } else if (status == 1) {
        cm.sendNextPrev("一旦轉職了就不能反悔,如果不想轉職請點上一頁.");
    } else if (status == 2) {
        cm.sendYesNo("你真的要成為一位 #r劍士#k ?");
    } else if (status == 3) {
        if (cm.getJob() == 0) {
            cm.changeJob(100); // 劍士
            cm.resetStats(35, 4, 4, 4);
        }
        cm.gainItem(1402001, 1);
        cm.sendOk("轉職成功 ! 請去開創天下吧.");
        cm.dispose();
    } else if (status == 11) {
        cm.sendNextPrev("你可以選擇你要轉職成為一位 #r狂戰士#k, #r見習騎士#k 或 #r槍騎兵#k.")
    } else if (status == 12) {
        cm.askAcceptDecline("但是我必須先測試你,你準備好了嗎 ?");
    } else if (status == 13) {
        cm.gainItem(4031008, 1);
        cm.warp(102020300);
        cm.sendOk("請去找 #b劍士轉職教官#k . 他會幫助你的.");
        cm.dispose();
    } else if (status == 21) {
        cm.sendSimple("你想要成為什麼 ? #b\r\n#L0#狂戰士#l\r\n#L1#見習騎士#l\r\n#L2#槍騎兵#l#k");
    } else if (status == 22) {
        var jobName;
        if (selection == 0) {
            jobName = "狂戰士";
            job = 110; // FIGHTER
        } else if (selection == 1) {
            jobName = "見習騎士";
            job = 120; // PAGE
        } else {
            jobName = "槍騎兵";
            job = 130; // SPEARMAN
        }
        cm.sendYesNo("你真的要成為一位 #r" + jobName + "#k?");
    } else if (status == 23) {
        cm.changeJob(job);
        cm.gainItem(4031012, -1);
        cm.sendOk("轉職成功 ! 請去開創天下吧.");
        cm.dispose();
    }
}