/*
ð�յ�(079)��Ϸ�����
 �ű���������
 */

var ��͵ȼ� = 110;
var ��ߵȼ� = 255;
var �������� = 2;
var ������� = 6;
var ����ͨ�ؾ��� = 10000;
var ����Ԥ�� = [
    [4001009, 30],
	[4001010, 30],
	[4001011, 30],
	[4001012, 30],
	[4001013, 30],
	[4001014, 30],
	[4001021, 30]
];
var status = 0;

function start() {
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }
    if (status == 1) {
		var �ı���Ϣ = "";
        for (var i = 0; i < ����Ԥ��.length; i++) {
            �ı���Ϣ += "   " + cm.��ʾ��Ʒ(����Ԥ��[i][0]) + "  " + ����Ԥ��[i][1] + " % #k\r\n";
        }
        cm.sendYesNo("\r\n     ��������һȺ�����ĺ���ռ���ˣ������ҽ�ѵ��ѵ�����𣿸�����Ҫ��������� #b" + �������� + " - " + ������� + "#k���ȼ�Ҫ��#b" + ��͵ȼ� + " - " + ��ߵȼ� + "#k����Ҫ�μӸ���#b������#k��������ʢ������#r��Ƥ��#kŶ��\r\n\r\n#d\t���ͨ�ؽ������� #r" + cm.getLevel()*����ͨ�ؾ��� + "#k\r\n\r\n   �ܼ����: #r" + cm.getBossRank("������", 2) + "\r\n#k   �������: #r" + cm.getBossLog("������") + "\r\n\r\n#k#e��������Ԥ��:#n\r\n\r\n" + �ı���Ϣ + "");
    } else if (status == 2) {
        cm.removeAll(4001117);
        cm.removeAll(4001120);
        cm.removeAll(4001121);
        cm.removeAll(4001122);
        if (cm.getPlayer().getParty() == null || !cm.isLeader()) {
            cm.sendOk("���Ҷӳ������ҡ�");
        } else {
            var party = cm.getPlayer().getParty().getMembers();
            var mapId = cm.getPlayer().getMapId();
            var next = true;
            var size = 0;
            var it = party.iterator();
            while (it.hasNext()) {
                var cPlayer = it.next();
                var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
                if (ccPlayer == null || ccPlayer.getLevel() < ��͵ȼ� || ccPlayer.getLevel() > ��ߵȼ�) {
                    next = false;
                    break;
                }
                size += (ccPlayer.isGM() ? 2 : 1);
            }
            if (next && size >= ��������) {
                if (checkMap()) {
                    var em = cm.getEventManager("Pirate");
                    if (em == null) {
                        cm.sendOk("�Ҳ����ű�������ϵGM����");
                    } else {
                        em.startInstance(cm.getPlayer().getParty(), cm.getPlayer().getMap());
                    }
                } else {
                    cm.sendOk("Ŀǰ��������ս�ˡ�");
                }
            } else {
                cm.sendOk("����: #b������#k\r\n����: #b" + ��С���� + " - " + ������� + "#k\r\n�ȼ�: #b" + ��͵ȼ� + " - " + ��ߵȼ� + "#k");
            }
        }
        cm.�Ի�����();
    }
}

function checkMap() {
    var map = [925100000, 925100100, 925100200, 925100201, 925100202, 925100300, 925100301, 925100302, 925100400, 925100400, 925100500];
    for (var i = 0; i < map.length; i++) {
        if (cm.getPlayerCount(map[i]))
            return false;
    }
    return true;
}