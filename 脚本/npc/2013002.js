/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű���Ů��������
 */
var status;
var ���ͨ�� = 5000;

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
                cm.sendNext("��ȷ�������������û�п�����,������λ��һ��ͺ�,ȷ�϶��п��������ҶԻ�");
            } else if (status == 1) {
                //Ů����ʢ����ĩ����				
				cm.���ʸ���Ʒ2(4001158, 2, 100, "Ů�����ë");
                cm.���ʸ���Ʒ2(4007000, 2, 30, "ħ����ĩ(��ɫ)");
                cm.���ʸ���Ʒ2(4007001, 2, 30, "ħ����ĩ(��ɫ)");
                cm.���ʸ���Ʒ2(4007002, 2, 30, "ħ����ĩ(��ɫ)");
                cm.���ʸ���Ʒ2(4007003, 2, 30, "ħ����ĩ(��ɫ)");
                cm.���ʸ���Ʒ2(4007004, 2, 30, "ħ����ĩ(��ɫ)");
                cm.���ʸ���Ʒ2(4007005, 2, 30, "ħ����ĩ(��ɫ)");
                cm.���ʸ���Ʒ2(4007006, 2, 30, "ħ����ĩ(��ɫ)");
                cm.���ʸ���Ʒ2(4007007, 2, 30, "ħ����ĩ(��ɫ)");
                cm.���ʸ���Ʒ2(4010000, 5, 30, "��ͭĸ��");
                cm.���ʸ���Ʒ2(4000463, 2, 40, "��������");
                cm.���ʸ���Ʒ2(4002002, 2, 40, "ľ��Ʊ");
                cm.���ʸ���Ʒ2(4010001, 5, 30, "����ĸ��");
                cm.���ʸ���Ʒ2(4010002, 5, 30, "﮿�ʯĸ��");
                cm.���ʸ���Ʒ2(4010003, 5, 30, "���ʯĸ��");
                cm.���ʸ���Ʒ2(4010004, 5, 30, "����ĸ��");
                cm.���ʸ���Ʒ2(4010005, 5, 30, "�Ͽ�ʯĸ��");
                cm.���ʸ���Ʒ2(4010006, 5, 30, "�ƽ�ĸ��");
                cm.���ʸ���Ʒ2(4010007, 5, 30, "�ĸ��");


                //��¼ͨ����Ϣ
				cm.������(cm.getLevel()*���ͨ��);
				cm.gainzdjf(+1);//����������
                cm.setBossRankCount("Ů����", 1);
                cm.setBossLog("Ů����");
                cm.worldMessage(2, "[����-Ů����] : ��ϲ " + cm.getPlayer().getName() + " �����Ů����������");
                cm.gainItem(randItem, qty);
                cm.warp(200080101);
                cm.dispose();
                break;
            }
    }
}