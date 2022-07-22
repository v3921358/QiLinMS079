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
            if (cm.getPlayer().getLevel() >= 8) {
                cm.sendNext("你要转职成为一位 #r法师#k ?");
				
            } else {
                cm.sendOk("你还不能转职成为 #r法师#k 蔡B8.")
                cm.dispose();
            }
        } else {
            if (cm.getPlayer().getLevel() >= 30 && cm.getJob() == 200) { // 法師
                if (cm.haveItem(4031012, 1)) {
                    if (cm.haveItem(4031012, 1)) {
                        status = 20;
                        cm.sendNext("我看到你完成了测试. 想要继续转职请点下一页!");
                    } else {
                        if (!cm.haveItem(4031009)) {
                            cm.gainItem(4031009, 1);
                        }
                        cm.sendOk("请去找 #r法师转职教官#k.")
                        cm.dispose();
                    }
                } else {
                    status = 10;
                    cm.sendNext("你已经可以转职了,要转职请点下一页.");
                }
            } else if (cm.getPlayer().getLevel() >= 70 && cm.getJob() == 210 || cm.getJob() == 220 || cm.getJob() == 230) {
                if (cm.canHoldByType(4, 2)) {
                    if (cm.haveItem(4031059, 1)) {
                        cm.gainItem(4031057, 1);
                        cm.gainItem(4031059, -1);
                        cm.warp(211000001, 0);
                        cm.sendOk("你完成了一个考验，现在去找 #b羅貝亞#k.");
                    } else {
                        cm.sendOk("嗨, #b#h0##k! 我需要一个 #b黑符#k. 快去找异次元空间拿給我.");
                    }
                } else {
                    cm.sendOk("你的背包沒有多余的3个空格。");
                    cm.dispose();
                    return;
                }
                cm.dispose();
            } else {
                cm.sendOk("你好,我是法师转职官.");
                cm.dispose();
            }
        }
    } else if (status == 1) {
        cm.sendNextPrev("一旦转职了就不能反悔,如果不想转职请点上一页.");
    } else if (status == 2) {
        cm.sendYesNo("你真的要成为一位 #r法师#k ?");
    } else if (status == 3) {
        if (cm.getJob() == 0) {
            cm.changeJob(200); // 法師
            cm.resetStats(4, 4, 20, 4);
        }
        cm.gainItem(1372005, 1);
        cm.sendOk("转职成功 ! 请去开创天下吧.");
        cm.dispose();
    } else if (status == 11) {
        cm.sendNextPrev("你可以選擇你要轉職成為一位 #r巫師(火,毒)#k, #r巫師(冰,雷)#k 或 #r僧侶#k.");
    } else if (status == 12) {
        cm.askAcceptDecline("但是我必須先測試你,你準備好了嗎 ?");
    } else if (status == 13) {
        cm.gainItem(4031009, 1);
        cm.warp(101020000);
        cm.sendOk("請去找 #b法師轉職教官#k . 他會幫助你的.");
        cm.dispose();
    } else if (status == 21) {
        cm.sendSimple("你想要成為什麼 ? #b\r\n#L0#巫師(火,毒)#l\r\n#L1#巫師(冰,雷)#l\r\n#L2#僧侶#l#k");
    } else if (status == 22) {
        if (selection == 0) {
            jobName = "巫師(火,毒)";
            jobId = 210; // FP
        } else if (selection == 1) {
            jobName = "巫師(冰,雷)";
            jobId = 220; // IL
        } else {
            jobName = "僧侶";
            jobId = 230; // CLERIC
        }
        cm.sendYesNo("你真的要成為一位 #r" + jobName + "#k?");
    } else if (status == 23) {
        cm.changeJob(jobId);
        cm.gainItem(4031012, -1);
        cm.sendOk("轉職成功 ! 請去開創天下吧.");
        cm.dispose();
    }
}