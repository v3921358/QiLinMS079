/*
ð�յ�(079)��Ϸ�����
 �ű���������
 */

var ����ͨ�ؾ��� = 10000;
ver ���� = 1;

var ��ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";

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
                selStr = "��Ҫ��������򲻹���?#k\r\n";
        if (cm.getMapId() == 925100000) {
            selStr = "#r������������һ��#k\r\n\r\n��Ҫ�ռ� #b6 #k�� #v4001117#�ſ���ͨ��\r\n\r\n";
        }
        if (cm.getMapId() == 925100100) {
            selStr = "#r�����������ڶ���#k\r\n\r\n��Ҫ�ռ� #b50#k�� #v4001120# #v4001121# #v4001122#�ſ���ͨ��\r\n";
        }

        if (cm.getMapId() == 925100300) {
            selStr = "#r����������������#k\r\n\r\n����Ҫɱ���������еĹ���ſ���ͨ��\r\n";
        }
        if (cm.getMapId() == 925100500) {
            selStr = "#r�������������Ĺ�#k\r\n\r\n����ս��BOSS��û����\r\n";
            if (cm.getMap().getAllMonstersThreadsafe().size() == 0) {
                selStr += "#L2##b��ȡ����#l#k\r\n";
            }

        }
        selStr += "#L1##b�������˳�����#l#k\r\n";
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
                    //������ʢ����Ƥ������
                    cm.���ʸ���Ʒ2(4001009, 2, 30, "ľ����Ƥ��");
                    cm.���ʸ���Ʒ2(4001010, 2, 30, "Ģ������Ƥ��");
                    cm.���ʸ���Ʒ2(4001011, 2, 30, "������Ƥ��");
                    cm.���ʸ���Ʒ2(4001012, 2, 30, "��������Ƥ��");
                    cm.���ʸ���Ʒ2(4001013, 2, 30, "��ˮ����Ƥ��");
                    cm.���ʸ���Ʒ2(4001014, 2, 30, "����������Ƥ��");
					cm.���ʸ���Ʒ2(4001021, 2, 30, "������Ƥ��");
					
					//��¼��Ϣ
					cm.gainzdjf(����);//����������
                    cm.setBossRankCount("������", 1);
                    cm.setBossLog("������");
                    cm.worldMessage(2, "[����-������] : ��ϲ " + cm.getPlayer().getName() + " ��ɺ�����������");
                    cm.gainExp(cm.getLevel()*����ͨ�ؾ���);
                    cm.warp(251010404, 0);
                    cm.dispose();

                } else {
                    cm.sendOk("����ǰ��ͼ����");
                    cm.dispose();
                }
                break;



        }
    }
}