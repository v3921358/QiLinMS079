/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�������
 */

//ͨ���������
var ͨ������ = 10;
var ͨ�ؾ��� = 3000;


var status = -1;

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.�Ի�����();
        return;
    }
    if (mode == 1)
        status++;
    else
        status--;
    if (status == 0) {
        cm.sendSimple("����������ϻ������������Ҫ�ģ��Ҿ͸��㽱����˭���ҺóԵģ��Ҿͻ��ס˭��#r��Ա�޷������ͨ�ش���#k#b\r\n#L0##v4001101# x " + ͨ������ + " ͨ��#l\r\n#L1##v4001101# x 20 �� #v1002798##l#k\r\n#L3##bѡ���뿪#l");
    } else if (status == 1) {
        if (selection == 0) {
            if (!cm.isLeader()) {
                cm.sendNext("ֻ�жӳ������Ҳ�Ҫ��");
                cm.�Ի�����();
            } else {
                if (cm.haveItem(4001101, ͨ������)) {
                    cm.gainItem(4001101, -ͨ������);
                    cm.showEffect(true, "quest/party/clear");
                    cm.playSound(true, "Party1/Clear");
                    cm.givePartyExp(ͨ�ؾ���);
					cm.gainzdjf(+1);//����������
					//���Ŷӷ�Ҷ
					////��Ҷ
					cm.���Ŷӵ���(4001126,cm.�����(50));
					////�ƽ��Ҷ
					cm.���Ŷӵ���(4000313,cm.�����(10));
                    //��¼ͨ����Ϣ
					cm.endPartyQuest(1200);
                    cm.warpParty(100000200);
                    cm.setBossRankCount("����", 1);
                    cm.givePartyBossLog("����");
                    cm.worldMessage(2, "[����-����] : ��ϲ " + cm.getPlayer().getName() + " ������飬����������");
                    cm.�Ի�����();
                } else {
                    cm.sendNext("��û����Ⱑ��");
                    cm.�Ի�����();
                }
            }
        } else if (selection == 1) {
            if (cm.haveItem(1002798, 1)) {
                cm.sendOk("���Ѿ�����");
            } else if (!cm.canHold(1002798, 1)) {
                cm.sendOk("���Ѿ�����");
            } else if (cm.haveItem(4001101, 20)) {
                cm.gainItem(4001101, -20); 
                cm.gainItem(1002798, 1);
            } else {
                cm.sendOk("����Ҫ20�������Ԫ��");
            }
            cm.�Ի�����();
        } else if (selection == 3) {
            cm.warp(100000200);
			cm.�Ի�����();
        }
    }
}