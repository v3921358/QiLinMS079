/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：女神塔奖励
 */
var status;
var 最后通关 = 5000;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    var em = cm.getEventManager("OrbisPQ");
    if (em == null) {
        cm.dispose();
        return;
    }
    for (var i = 4001044; i < 4001064; i++) {
        cm.removeAll(i);
    }
    switch (cm.getMapId()) {
        case 920010100:
            cm.showEffect(false, "quest/party/clear");
            cm.playSound(false, "Party1/Clear");
            cm.gainExp(33000);
            cm.getPlayer().endPartyQuest(1203);
            cm.warp(920011100);
            cm.dispose();
            break;
        default:
            if (mode == -1) {
                cm.dispose();
            }
            if (mode == 1)
                status++;
            else
                status--;
            if (status == 0) {
                cm.sendNext("请确认你的其他栏有没有空两格,另外栏位空一格就好,确认都有空再来跟我对话");
            } else if (status == 1) {
                //女神塔盛产粉末，恩				
				cm.概率给物品2(4001158, 2, 100, "女神的羽毛");
                cm.概率给物品2(4007000, 2, 30, "魔法粉末(褐色)");
                cm.概率给物品2(4007001, 2, 30, "魔法粉末(白色)");
                cm.概率给物品2(4007002, 2, 30, "魔法粉末(蓝色)");
                cm.概率给物品2(4007003, 2, 30, "魔法粉末(绿色)");
                cm.概率给物品2(4007004, 2, 30, "魔法粉末(黄色)");
                cm.概率给物品2(4007005, 2, 30, "魔法粉末(紫色)");
                cm.概率给物品2(4007006, 2, 30, "魔法粉末(红色)");
                cm.概率给物品2(4007007, 2, 30, "魔法粉末(黑色)");
                cm.概率给物品2(4010000, 5, 30, "青铜母矿");
                cm.概率给物品2(4000463, 2, 40, "国庆纪念币");
                cm.概率给物品2(4002002, 2, 40, "木邮票");
                cm.概率给物品2(4010001, 5, 30, "钢铁母矿");
                cm.概率给物品2(4010002, 5, 30, "锂矿石母矿");
                cm.概率给物品2(4010003, 5, 30, "朱矿石母矿");
                cm.概率给物品2(4010004, 5, 30, "银的母矿");
                cm.概率给物品2(4010005, 5, 30, "紫矿石母矿");
                cm.概率给物品2(4010006, 5, 30, "黄金母矿");
                cm.概率给物品2(4010007, 5, 30, "锂母矿");


                //记录通关信息
				cm.给经验(cm.getLevel()*最后通关);
				cm.gainzdjf(+1);//组队任务积分
                cm.setBossRankCount("女神塔", 1);
                cm.setBossLog("女神塔");
                cm.worldMessage(2, "[副本-女神塔] : 恭喜 " + cm.getPlayer().getName() + " ，完成女神塔副本。");
                cm.gainItem(randItem, qty);
                cm.warp(200080101);
                cm.dispose();
                break;
            }
    }
}