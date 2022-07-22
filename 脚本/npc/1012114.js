/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：月妙
 */

//通关年糕数量
var 通关数量 = 10;
var 通关经验 = 3000;


var status = -1;

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.对话结束();
        return;
    }
    if (mode == 1)
        status++;
    else
        status--;
    if (status == 0) {
        cm.sendSimple("我是这里的老虎，你给我我想要的，我就给你奖励，谁给我好吃的，我就会记住谁。#r队员无法获得总通关次数#k#b\r\n#L0##v4001101# x " + 通关数量 + " 通关#l\r\n#L1##v4001101# x 20 换 #v1002798##l#k\r\n#L3##b选择离开#l");
    } else if (status == 1) {
        if (selection == 0) {
            if (!cm.isLeader()) {
                cm.sendNext("只有队长给的我才要吃");
                cm.对话结束();
            } else {
                if (cm.haveItem(4001101, 通关数量)) {
                    cm.gainItem(4001101, -通关数量);
                    cm.showEffect(true, "quest/party/clear");
                    cm.playSound(true, "Party1/Clear");
                    cm.givePartyExp(通关经验);
					cm.gainzdjf(+1);//组队任务积分
					//给团队枫叶
					////枫叶
					cm.给团队道具(4001126,cm.随机数(50));
					////黄金枫叶
					cm.给团队道具(4000313,cm.随机数(10));
                    //记录通关信息
					cm.endPartyQuest(1200);
                    cm.warpParty(100000200);
                    cm.setBossRankCount("月妙", 1);
                    cm.givePartyBossLog("月妙");
                    cm.worldMessage(2, "[副本-月妙] : 恭喜 " + cm.getPlayer().getName() + " 带领队伍，完成月妙副本。");
                    cm.对话结束();
                } else {
                    cm.sendNext("你没有年糕啊？");
                    cm.对话结束();
                }
            }
        } else if (selection == 1) {
            if (cm.haveItem(1002798, 1)) {
                cm.sendOk("你已经有了");
            } else if (!cm.canHold(1002798, 1)) {
                cm.sendOk("你已经有了");
            } else if (cm.haveItem(4001101, 20)) {
                cm.gainItem(4001101, -20); 
                cm.gainItem(1002798, 1);
            } else {
                cm.sendOk("你需要20个月妙的元宵");
            }
            cm.对话结束();
        } else if (selection == 3) {
            cm.warp(100000200);
			cm.对话结束();
        }
    }
}