/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű���Ů����
 */

var ��͵ȼ� = 51;
var ��ߵȼ� = 255;
var �������� = 2;
var ������� = 6;
var ��һ�ؾ��� = 2000;
var ���ͨ�� = 5000;
//Ů��������Ԥ��
/*
 ��Ʒ������
 */
var ����Ԥ�� = [
    [4000463, 40],//�����
	[4002002, 40],//ľ����Ʊ
	[4007000, 30],
	[4007001, 30],
	[4007002, 30],
	[4007003, 30],
	[4007004, 30],
	[4007005, 30],
	[4007006, 30],
	[4007007, 30],
	[4010000, 30],
	[4010001, 30],
	[4010002, 30],
	[4010003, 30],
	[4010004, 30],
	[4010005, 30],
	[4010006, 30],
	[4010007, 30],
	[4001158, 100] //Ů�����ë
];
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
	if(cm.getPlayer().getMapId() == 920010000 && cm.isLeader()){
        if (cm.getPlayer().haveItem(4001063, 20)) {
            cm.givePartyExp(cm.getLevel()*��һ�ؾ���);
            cm.gainItem(4001063, -20);
			cm.warpParty(920010100); 
			return;
        } else {
            cm.sendNext("�ұ�Զ�ž������������������ռ��������ҳ�ȥ��");
            cm.dispose();
            return;
        }
    }
    if (status == 0) {
		var �ı���Ϣ = "";
        for (var i = 0; i < ����Ԥ��.length; i++) {
            �ı���Ϣ += "   " + cm.��ʾ��Ʒ(����Ԥ��[i][0]) + "  " + ����Ԥ��[i][1] + " % #k\r\n";
        }
        cm.sendYesNo("\r\n     ��������Ů��ɡ�������Ҫ���������#b" + �������� + " - " + ������� + "#k���ȼ�Ҫ��#b" + ��͵ȼ� + " - " + ��ߵȼ� + "#k����Ҫ�μӸ���#bŮ����#k��������ʢ������#rħ����ĩ������ĸ��ʯ#kŶ��\r\n\r\n#d\t���ͨ�ؽ������� #r" + cm.getLevel()*���ͨ�� + "#k\r\n\r\n  �ܼ����: #r" + cm.getBossRank("Ů����", 2) + "\r\n#k   �������: #r" + cm.getBossLog("Ů����") + "\r\n\r\n#k#e��������Ԥ��:#n\r\n\r\n" + �ı���Ϣ + "");
    } else if (status == 1) {
        if (cm.getParty() == null) {
            cm.sendSimple("������������ң������öӳ����ҡ�");
        } else {
            var party = cm.getParty().getMembers();
            var mapId = cm.getMapId();
            var next = true;
            var levelValid = 0;
            var inMap = 0;
            var it = party.iterator();
            while (it.hasNext()) {
                var cPlayer = it.next();
                if ((cPlayer.getLevel() >= ��͵ȼ�) && (cPlayer.getLevel() <= ��ߵȼ�)) {
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
                var em = cm.getEventManager("OrbisPQ");
                if (em == null) {
                    cm.sendSimple("�Ҳ����ű�������GM");
                } else {
                    var prop = em.getProperty("state");
                    if (prop.equals("0") || prop == null) {
                        em.startInstance(cm.getParty(), cm.getMap());
                        cm.�Ի�����();
                        return;
                    } else {
                        cm.sendSimple("���������Ѿ��������� #r���������#k �볢�Ի�Ƶ�����ߵ�����������ɡ�");
                    }
                }
            } else {
                cm.sendSimple("��Ķ���ò��û�дﵽҪ��...:\r\n\r\n#rҪ��: " + �������� + " ��ҳ�Ա, ÿ���˵ĵȼ������� " + ��͵ȼ� + " �� �ȼ� " + ��ߵȼ� + ".");
            }
        }
    } else {
        cm.�Ի�����();
    }
}