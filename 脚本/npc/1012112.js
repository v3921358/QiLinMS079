/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�������
 */
var ��С�ȼ� = 1;
var ��ߵȼ� = 255;
var �������� = 1;
var ������� = 6;

var status = -1;
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.�Ի�����();
            return;
        }
        status--;
    }
    if (cm.getMapId() == 910010100) {
        cm.warp(100000200);
        cm.�Ի�����();
	 } else if (status == 0) {	
	 cm.sendYesNo("\r\n     ��Ը������������һ���������������ᷢ���ϰ������⣬�㽫�޷���������������ΰ����ŶӺ���������������ԣ�������� #b��Ӷӳ�#k ����˵��.\r\n\r\n#rҪ��:  " + �������� + " - " + ������� + " ��Ա ���м��� " + ��С�ȼ� + " - " + ��ߵȼ� + ".\r\n   #k�ܼ����: #r"+cm.getBossRank("����",2)+"\r\n#k   �������: #r"+cm.getBossLog("����")+"\r\n\r\n��������:\r\n "+cm.��ʾ��Ʒ(4001101)+"\r\n "+cm.��ʾ��Ʒ(1002798)+" \r\n "+cm.��ʾ��Ʒ(4001126)+" \r\n "+cm.��ʾ��Ʒ(4000313)+"");
    } else if (status == 1) {
        if (cm.getParty() == null) {
            cm.sendSimple("����Ӻ������Ұѡ�");
        } else if (!cm.isLeader()) {
            cm.sendSimple("������볢�ԣ������ #b��Ӷӳ�#k ����˵��.#b#l");
        } else {
            var party = cm.getParty().getMembers();
            var mapId = cm.getMapId();
            var next = true;
            var levelValid = 0;
            var inMap = 0;
            var it = party.iterator();

            while (it.hasNext()) {
                var cPlayer = it.next();
                if ((cPlayer.getLevel() >= ��С�ȼ�) && (cPlayer.getLevel() <= ��ߵȼ�)) {
                    levelValid += 1;
                } else {
                    next = false;
                }
                if (cPlayer.getMapid() == mapId) {
                    inMap += (cPlayer.getJobId() == 900 ? 6 : 1);
                }
            }
            if (party.size() > ������� || inMap < ��������) {
                next = false;
            }
            if (next) {
                var em = cm.getEventManager("HenesysPQ");
                if (em == null) {
                    cm.sendSimple("PQ������һ����������ϵGM�����ͼ.#b#l");
                } else {
                    var prop = em.getProperty("state");
                    if (prop.equals("0") || prop == null) {
                        for (var i = 4001095; i < 4001099; i++) {
                            cm.givePartyItems(i, 0, true);
                        }
                        for (var i = 4001100; i < 4001101; i++) {
                            cm.givePartyItems(i, 0, true);
                        }
                        em.startInstance(cm.getParty(), cm.getMap());
                        cm.�Ի�����();
                        return;
                    } else {
                        cm.sendSimple("��һ���ѽ��� #r��������#k ������볢����һ��Ƶ�������ߵȴ���ǰ���������.#b#");
                    }
                }
            } else {
                cm.sendOk("����: #b����#k\r\n����: #b" + �������� + " - " + ������� + "#k\r\n�ȼ�: #b" + ��С�ȼ� + " - " + ��ߵȼ� + "#k");
                cm.�Ի�����();
            }
        }
    }
}