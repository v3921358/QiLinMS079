
/*
 Name:  �����D���C
 Place: �D����
 */
 
load('nashorn:mozilla_compat.js');
importPackage(Packages.handling.world);
importPackage(Packages.tools);
importPackage(Packages.server);
var �ʹڰ� ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var ������ ="#fUI/ChatBalloon/tutorial/w#";
var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� =  "#fUI/ChatBalloon/37/nw#";
var �� =    "#fUI/ChatBalloon/37/e#";
var �� =    "#fUI/ChatBalloon/37/w#";
var ����è ="#fUI/ChatBalloon/37/s#";
var è���� ="#fUI/ChatBalloon/37/se#";
var è���� ="#fUI/ChatBalloon/37/sw#";
var status = -1;

var requireItem = 1; /* �D��ȯ */

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
                cm.sendOk("������10������ʹ�á�");
                status = -1;
                //cm.gainItem(5220000, 1);
                cm.dispose();
                break;
            } else {
                cm.sendYesNo("                "+�ʹڰ�+"- #w#e#r" + cm.getServerName() + " -"+�ʹڰ�+"\r\n\r\n"+è��+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+è��+"\r\n\t     #d��ã���������Ҫ��#v4000463#�齱��\r\n\t\t\t #L0#���ҵ��γ齱!#l\r\n\t\t\t #L3##r����10���齱!#l\r\n\t\t\t #L4#����20���齱!#l\r\n\t\t\t #L1##d���Ҳ鿴��Ʒ!#l\r\n\t\t\t #L6##rһ����������!#d#l\r\n\r\n" + (cm.getPlayer().isGM() ? "\t#L2##k���Ҹ��Ĵ˳齱������!(GM��ʾ����)#l" : ""));
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
                                cm.getItemLog("������", " �鵽 " + gashaponItem.getItemId() + "(" + cm.getItemName(gashaponItem.getItemId()) + ") " + gashaponItem.getQuantity() + "����");
                                if (gashaponItem != null) {
                                    if (gashaponItem.canShowMsg()) {
                                        cm.getItemMegaphone(gashapon.getName(), item, gashaponItem.getQuantity());
                                    }
                                    //status = -1;
                                    cm.sendOk("��ϲ��鵽��#b#i" + gashaponItem.getItemId() + ":##k��");
                                    cm.dispose();

                                } else {
                                    cm.sendOk("����ά���С�");
                                    cm.dispose();
                                }
                            } else {
                                cm.sendOk("��ȷ�������Ʒ��λ���пռ䡣");
                                cm.dispose();
                            }
                        } else {
                            cm.sendOk("������δ���š�");
                            cm.dispose();

                        }
                    } else {
                        cm.sendOk("�ܱ�Ǹ������û��#b#z4000463##k�����Բ��ܳ齱Ŷ��");
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
                                    cm.getItemLog("�齱��ʦ", " �鵽 " + gashaponItem.getItemId() + "(" + cm.getItemName(gashaponItem.getItemId()) + ") " + gashaponItem.getQuantity() + "����");
                                    if (gashaponItem != null) {
                                        if (gashaponItem.canShowMsg()) {
                                            cm.getItemMegaphone(gashapon.getName(), item, gashaponItem.getQuantity());
                                        }
                                        //status = -1;
                                         cm.playerMessage("��ϲ��鵽��" + cm.getItemName(gashaponItem.getItemId()) + "��");
                                        cm.dispose();

                                    } else {
                                        cm.sendOk("����ά���С�");
                                        cm.dispose();
                                         return;
                                    }
                                } else {
                                    cm.sendOk("��ȷ�������Ʒ��λ���пռ䡣");
                                    cm.dispose();
                                     return;
                                }
                            } else {
                                cm.sendOk("������δ���š�");
                                cm.dispose();
                                 return;

                            }
                        }
                    } else {
                        cm.sendOk("�ܱ�Ǹ������û��#b#z4000463#x10#k�����Բ��ܳ齱Ŷ��");
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
                                    cm.getItemLog("������", " �鵽 " + gashaponItem.getItemId() + "(" + cm.getItemName(gashaponItem.getItemId()) + ") " + gashaponItem.getQuantity() + "����");
                                    if (gashaponItem != null) {
                                        if (gashaponItem.canShowMsg()) {
                                            cm.getItemMegaphone(gashapon.getName(), item, gashaponItem.getQuantity());
                                        }
                                        //status = -1;
                                        cm.playerMessage("��ϲ��鵽��" + cm.getItemName(gashaponItem.getItemId()) + "��");
                                        cm.dispose();

                                    } else {
                                        cm.sendOk("����ά���С�");
                                        cm.dispose();
                                         return;
                                    }
                                } else {
                                    cm.sendOk("��ȷ�������Ʒ��λ���пռ䡣");
                                    cm.dispose();
                                     return;
                                }
                            } else {
                                cm.sendOk("�齱��δ���š�");
                                cm.dispose();
                                 return;

                            }
                        }
                    } else {
                        cm.sendOk("�ܱ�Ǹ������û��#b#z4000463#x20#k�����Բ��ܳ齱Ŷ��");
                        cm.dispose();
                         return;
                    }
                }
            }
            break;
        case 2:
            sel = selection;
            if (sel == 10000) {
                cm.sendGetText("��������Ҫ��������Ʒ���롣");
                status = 4;
                break;
            } else {
                cm.sendGetText("��������Ҫ���ĵĻ��ʡ�");
                break;
            }
        case 3:
            cm.getGashapon().ChangeChance(cm.getPlayer(), sel, cm.getText());
            cm.sendYesNo("����˳����������!���Ƿ�Ҫ���س齱����?\r\n(��ѡ������Ч)");
            break;
        case 4:
            cm.processCommand("!reloadgashapon");
            cm.sendOk("��˳�����س齱����!");
            status = -1;
            break;
        case 5:
            itemid = cm.getText();
            cm.sendGetText("��������Ҫ������Ʒ�Ļ��ʡ�");
            break;
        case 6:
            chance = cm.getText();
            cm.sendGetText("�������Ƿ�Ҫ�ô���Ʒ�Ϲ㲥?(����д�ǻ��!)");
            break;
        case 7:
            if (cm.getText() == "��") {
                msg = true;
            } else {
                msg = false;
            }
            cm.sendGetText("��������Ҫ������Ʒ��������");
            break;
        case 8:
            quantity = cm.getText();
            cm.getGashapon().AddItem(cm.getPlayer(), itemid, chance, msg, quantity);
            cm.sendYesNo("����˳�������齱��Ʒ!���Ƿ�Ҫ���س齱?\r\n(��ѡ������Ч)");
            break;
        case 9:
            cm.processCommand("!reloadgashapon");
            cm.sendOk("��˳�����س齱����!");
            status = -1;
            break;
        default:
            cm.dispose();
    }
}
