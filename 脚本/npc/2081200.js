/*  NPC : 葛雷托
 法師 4轉 任務腳本
 地圖代碼 (240010501)
 */

var status = -1;
var pass = false;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1)
        status++;
    else
        status--;

    if (status == 0) {
        if (!(cm.getJob() == 211 || cm.getJob() == 221 || cm.getJob() == 231)) {
            cm.sendOk("為什麼你要見我??還有你想要問我關於什麼事情??");
            cm.dispose();
            return;
        } else if (cm.getPlayer().getLevel() < 120) {
            cm.sendOk("你等級尚未到達120級.");
            cm.dispose();
            return;
        } else {
            if (cm.getQuestStatus(6914) == 2) {
                pass = true;
            }
            if (cm.getJob() == 211) {
                cm.sendSimple("恭喜你有資格4轉. \r\n請問你想4轉嗎??\r\n#b#L0#我想成為大魔島士.#l\r\n#b#L1#像我想一下...#l");
            } else if (cm.getJob() == 221) {
                cm.sendSimple("恭喜你有資格4轉. \r\n請問你想4轉嗎??\r\n#b#L0#我想成為大魔島士.#l\r\n#b#L1#像我想一下...#l");
            } else if (cm.getJob() == 231) {
                cm.sendSimple("恭喜你有資格4轉. \r\n請問你想4轉嗎??\r\n#b#L0#我想成為主教.#l\r\n#b#L1#像我想一下...#l");
            } else {
                cm.sendOk("好吧假如你想要4轉麻煩再來找我");
                cm.dispose();
                return;
            }
        }
    } else if (status == 1) {
        if (selection == 1) {
            cm.sendOk("好吧假如你想要4轉麻煩再來找我");
            cm.dispose();
            return;
        }
        if (cm.getPlayerStat("RSP") > (cm.getPlayerStat("LVL") - 120) * 3) {
            cm.sendOk("你的技能點數還沒點完..");
            cm.dispose();
            return;
        }
        if (pass) {
            cm.sendNext("即將四轉。");
        } else {
            if (!cm.haveItem(4031860) || !cm.haveItem(4031861)) {
                cm.sendOk("我需要#t4031860# x1 #t4031861# x1。");
                cm.dispose();
                return;
            } else {
                cm.sendNext("即將四轉。");
            }
        }
    } else if (status == 2) {
        if (cm.canHold(2280003)) {
            cm.gainItem(2280003, 1);
            if (cm.getJob() == 211) {
                cm.changeJob(212);
                cm.teachSkill(2121001, 0, 10);
                cm.teachSkill(2121006, 0, 10);
                cm.teachSkill(2121002, 0, 10);
                cm.gainItem(4031860, -1);
                cm.gainItem(4031861, -1);
                cm.sendNext("恭喜你轉職為 #b大魔島士#k.我送你一些神秘小禮物^^");
            } else if (cm.getJob() == 221) {
                cm.changeJob(222);
                cm.teachSkill(2221001, 0, 10);
                cm.teachSkill(2221006, 0, 10);
                cm.teachSkill(2221002, 0, 10);
                cm.gainItem(4031860, -1);
                cm.gainItem(4031861, -1);
                cm.sendNext("恭喜你轉職為 #b大魔島士#k.我送你一些神秘小禮物^^");
            } else {
                cm.changeJob(232);
                cm.teachSkill(2321001, 0, 10);
                cm.teachSkill(2321005, 0, 10);
                cm.teachSkill(2321002, 0, 10);
                cm.gainItem(4031860, -1);
                cm.gainItem(4031861, -1);
                cm.sendNext("恭喜你轉職為 #b主教#k.我送你一些神秘小禮物^^");
            }
        } else {
            cm.sendOk("你沒有多的欄位請清空再來嘗試一次!");
            cm.dispose();
            return;
        }

    } else if (status == 3) {
        cm.sendNext("不要忘記了這一切都取決於你練了多少.");
    } else if (status == 4) {
        cm.sendNextPrev("我已你為榮.");
        cm.dispose();
    }
}