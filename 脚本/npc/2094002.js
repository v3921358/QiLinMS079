/*
冒险岛(079)游戏服务端
 脚本：海盗船
 */

var 海盗通关经验 = 10000;
ver 积分 = 1;

var 箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
        var
                selStr = "你要放弃吗？你打不过了?#k\r\n";
        if (cm.getMapId() == 925100000) {
            selStr = "#r海盗副本，第一关#k\r\n\r\n你要收集 #b6 #k个 #v4001117#才可以通关\r\n\r\n";
        }
        if (cm.getMapId() == 925100100) {
            selStr = "#r海盗副本，第二关#k\r\n\r\n你要收集 #b50#k个 #v4001120# #v4001121# #v4001122#才可以通关\r\n";
        }

        if (cm.getMapId() == 925100300) {
            selStr = "#r海盗副本，第三关#k\r\n\r\n你需要杀掉这里所有的怪物，才可以通关\r\n";
        }
        if (cm.getMapId() == 925100500) {
            selStr = "#r海盗副本，第四关#k\r\n\r\n你挑战完BOSS了没？？\r\n";
            if (cm.getMap().getAllMonstersThreadsafe().size() == 0) {
                selStr += "#L2##b领取奖励#l#k\r\n";
            }

        }
        selStr += "#L1##b放弃，退出副本#l#k\r\n";
        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 10:
                var FantMap = cm.getMap(925100000);
                FantMap.resetFully();
                cm.warpParty(925100000, 0);
                cm.dispose();
                break;
            case 1:
                cm.warpParty(251010404, 0);
                cm.dispose();
                break;
            case 2:
                if (cm.getMap().getAllMonstersThreadsafe().size() == 0) {
                    //海盗船盛产橡皮擦，恩
                    cm.概率给物品2(4001009, 2, 30, "木妖橡皮擦");
                    cm.概率给物品2(4001010, 2, 30, "蘑菇王橡皮擦");
                    cm.概率给物品2(4001011, 2, 30, "猴子橡皮擦");
                    cm.概率给物品2(4001012, 2, 30, "大幽灵橡皮擦");
                    cm.概率给物品2(4001013, 2, 30, "绿水灵橡皮擦");
                    cm.概率给物品2(4001014, 2, 30, "三眼章鱼橡皮擦");
					cm.概率给物品2(4001021, 2, 30, "狸子橡皮擦");
					
					//记录信息
					cm.gainzdjf(积分);//组队任务积分
                    cm.setBossRankCount("海盗船", 1);
                    cm.setBossLog("海盗船");
                    cm.worldMessage(2, "[副本-海盗船] : 恭喜 " + cm.getPlayer().getName() + " 完成海盗船副本。");
                    cm.gainExp(cm.getLevel()*海盗通关经验);
                    cm.warp(251010404, 0);
                    cm.dispose();

                } else {
                    cm.sendOk("清理当前地图怪物");
                    cm.dispose();
                }
                break;



        }
    }
}