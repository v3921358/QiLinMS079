
/*
 Name:  潮流轉蛋機
 Place: 轉蛋屋
 */
 
load('nashorn:mozilla_compat.js');
importPackage(Packages.handling.world);
importPackage(Packages.tools);
importPackage(Packages.server);
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var 中条蓝 ="#fUI/ChatBalloon/tutorial/w#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
var status = -1;

var requireItem = 1; /* 轉蛋券 */

function action(mode, _type, selection) {
    if (mode == 1) {
        status++;
    } else {
        cm.dispose();
        return;
    }

    switch (status) {
        case 0:
            if (cm.getPlayer().getLevel() <= 9) {
                cm.sendOk("飞天猪10级方能使用。");
                status = -1;
                //cm.gainItem(5220000, 1);
                cm.dispose();
                break;
            } else {
                cm.sendYesNo("                "+皇冠白+"- #w#e#r" + cm.getServerName() + " -"+皇冠白+"\r\n\r\n"+猫左+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+猫右+"\r\n\t     #d你好，请问你需要用#v4000463#抽奖吗？\r\n\t\t\t #L0#点我单次抽奖!#l\r\n\t\t\t #L3##r点我10连抽奖!#l\r\n\t\t\t #L4#点我20连抽奖!#l\r\n\t\t\t #L1##d点我查看奖品!#l\r\n\t\t\t #L6##r一键回收荣誉!#d#l\r\n\r\n" + (cm.getPlayer().isGM() ? "\t#L2##k点我更改此抽奖机内容!(GM显示功能)#l" : ""));
                break;
            }
        case 1:
            {
                if (selection == 1) {
					cm.dispose();
                    cm.sendOk(cm.getGashapon().ShowItem(1));
                    //cm.dispose();
                } else if (selection == 6) {
					cm.dispose();
					cm.openNpc(9050001,1);
                }
				else if (selection == 2) {
					cm.dispose();
                    cm.sendNext(cm.getGashapon().ShowItem("GM"));
                } else if (selection == 0) {
                    if (cm.haveItem(4000463, 1)) {
                        var gashapon = cm.getGashapon();
                        if (gashapon != null) {
                            if (cm.canHold()) {
                                cm.gainItem(4000463, -1);
                                //cm.getPlayer().modifyCSPoints(1, -requireItem, true);
                                var gashaponItem = gashapon.generateReward();
                                var item = MapleInventoryManipulator.addbyId_Gachapon(cm.getPlayer().getClient(), gashaponItem.getItemId(), gashaponItem.getQuantity());
                                cm.getItemLog("飞天猪", " 抽到 " + gashaponItem.getItemId() + "(" + cm.getItemName(gashaponItem.getItemId()) + ") " + gashaponItem.getQuantity() + "个。");
                                if (gashaponItem != null) {
                                    if (gashaponItem.canShowMsg()) {
                                        cm.getItemMegaphone(gashapon.getName(), item, gashaponItem.getQuantity());
                                    }
                                    //status = -1;
                                    cm.sendOk("恭喜你抽到了#b#i" + gashaponItem.getItemId() + ":##k。");
                                    cm.dispose();

                                } else {
                                    cm.sendOk("功能维护中。");
                                    cm.dispose();
                                }
                            } else {
                                cm.sendOk("请确认你的物品栏位还有空间。");
                                cm.dispose();
                            }
                        } else {
                            cm.sendOk("功能尚未开放。");
                            cm.dispose();

                        }
                    } else {
                        cm.sendOk("很抱歉由于你没有#b#z4000463##k，所以不能抽奖哦。");
                        cm.dispose();
                    }
                } else if (selection == 3) {
                    if (cm.haveItem(4000463,10)) {
                       
                        for (var i = 0; i < 10; i++) {
                            var gashapon = cm.getGashapon();
                            if (gashapon != null) {

                                if (cm.canHold()) {
                                    cm.gainItem(4000463, -1);
                                    //cm.getPlayer().modifyCSPoints(1, -requireItem, true);
                                    var gashaponItem = gashapon.generateReward();
                                    var item = MapleInventoryManipulator.addbyId_Gachapon(cm.getPlayer().getClient(), gashaponItem.getItemId(), gashaponItem.getQuantity());
                                    cm.getItemLog("抽奖大师", " 抽到 " + gashaponItem.getItemId() + "(" + cm.getItemName(gashaponItem.getItemId()) + ") " + gashaponItem.getQuantity() + "个。");
                                    if (gashaponItem != null) {
                                        if (gashaponItem.canShowMsg()) {
                                            cm.getItemMegaphone(gashapon.getName(), item, gashaponItem.getQuantity());
                                        }
                                        //status = -1;
                                         cm.playerMessage("恭喜你抽到了" + cm.getItemName(gashaponItem.getItemId()) + "。");
                                        cm.dispose();

                                    } else {
                                        cm.sendOk("功能维护中。");
                                        cm.dispose();
                                         return;
                                    }
                                } else {
                                    cm.sendOk("请确认你的物品栏位还有空间。");
                                    cm.dispose();
                                     return;
                                }
                            } else {
                                cm.sendOk("功能尚未开放。");
                                cm.dispose();
                                 return;

                            }
                        }
                    } else {
                        cm.sendOk("很抱歉由于你没有#b#z4000463#x10#k，所以不能抽奖哦。");
                        cm.dispose();
                         return;
                    }
               } else if (selection == 4) {
                    if (cm.haveItem(4000463,20)) {
               
                        for (var i = 0; i < 20; i++) {
                            var gashapon = cm.getGashapon();
                            if (gashapon != null) {

                                if (cm.canHold()) {
                                    cm.gainItem(4000463, -1);
                                    //cm.getPlayer().modifyCSPoints(1, -requireItem, true);
                                    var gashaponItem = gashapon.generateReward();
                                    var item = MapleInventoryManipulator.addbyId_Gachapon(cm.getPlayer().getClient(), gashaponItem.getItemId(), gashaponItem.getQuantity());
                                    cm.getItemLog("飞天猪", " 抽到 " + gashaponItem.getItemId() + "(" + cm.getItemName(gashaponItem.getItemId()) + ") " + gashaponItem.getQuantity() + "个。");
                                    if (gashaponItem != null) {
                                        if (gashaponItem.canShowMsg()) {
                                            cm.getItemMegaphone(gashapon.getName(), item, gashaponItem.getQuantity());
                                        }
                                        //status = -1;
                                        cm.playerMessage("恭喜你抽到了" + cm.getItemName(gashaponItem.getItemId()) + "。");
                                        cm.dispose();

                                    } else {
                                        cm.sendOk("功能维护中。");
                                        cm.dispose();
                                         return;
                                    }
                                } else {
                                    cm.sendOk("请确认你的物品栏位还有空间。");
                                    cm.dispose();
                                     return;
                                }
                            } else {
                                cm.sendOk("抽奖尚未开放。");
                                cm.dispose();
                                 return;

                            }
                        }
                    } else {
                        cm.sendOk("很抱歉由于你没有#b#z4000463#x20#k，所以不能抽奖哦。");
                        cm.dispose();
                         return;
                    }
                }
            }
            break;
        case 2:
            sel = selection;
            if (sel == 10000) {
                cm.sendGetText("请输入您要新增的物品代码。");
                status = 4;
                break;
            } else {
                cm.sendGetText("请输入您要更改的机率。");
                break;
            }
        case 3:
            cm.getGashapon().ChangeChance(cm.getPlayer(), sel, cm.getText());
            cm.sendYesNo("您已顺利调整机率!您是否要重载抽奖机率?\r\n(点选立即生效)");
            break;
        case 4:
            cm.processCommand("!reloadgashapon");
            cm.sendOk("已顺利重载抽奖机率!");
            status = -1;
            break;
        case 5:
            itemid = cm.getText();
            cm.sendGetText("请输入您要新增物品的机率。");
            break;
        case 6:
            chance = cm.getText();
            cm.sendGetText("请问你是否要让此物品上广播?(请填写是或否!)");
            break;
        case 7:
            if (cm.getText() == "是") {
                msg = true;
            } else {
                msg = false;
            }
            cm.sendGetText("请输入您要新增物品的数量。");
            break;
        case 8:
            quantity = cm.getText();
            cm.getGashapon().AddItem(cm.getPlayer(), itemid, chance, msg, quantity);
            cm.sendYesNo("您已顺利新增抽奖物品!您是否要重载抽奖?\r\n(点选立即生效)");
            break;
        case 9:
            cm.processCommand("!reloadgashapon");
            cm.sendOk("已顺利重载抽奖机率!");
            status = -1;
            break;
        default:
            cm.dispose();
    }
}
