/*
ð�յ�(079)��Ϸ�����
 �ű�������ɭ��
 */
var �������� = 2;
var ������� = 6;
var ��͵ȼ� = 91;
var ��ߵȼ� = 255;
var ����ͨ�ؾ��� = 8000;
//����ɭ�ֽ���Ԥ��
/*
 ��Ʒ������
 */
var ����Ԥ�� = [
    [4002000, 50],
	[4002001, 50],
	[4002002, 50],
	[4002003, 50]
];
var status = 0;

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.dispose();
        }
        status--;
    }
    if (status == 1) {
		var �ı���Ϣ = "";
        for (var i = 0; i < ����Ԥ��.length; i++) {
            �ı���Ϣ += "   " + cm.��ʾ��Ʒ(����Ԥ��[i][0]) + "  " + ����Ԥ��[i][1] + " % #k\r\n";
        }
        cm.sendYesNo("\r\n     ����ɭ�֡�������Ҫ���������#b" + �������� + " - " + ������� + "#k���ȼ�Ҫ��#b" + ��͵ȼ� + " - " + ��ߵȼ� + "#k����Ҫ�μӸ���#b����ɭ��#k��������ʢ������#r��Ʊ#kŶ��\r\n\r\n#d\t���ͨ�ؽ������� #r" + cm.getLevel()*����ͨ�ؾ��� + "#k\r\n\r\n   �ܼ����: #r" + cm.getBossRank("����ɭ��", 2) + "\r\n#k   �������: #r" + cm.getBossLog("����ɭ��") + "\r\n\r\n#k#e��������Ԥ��:#n\r\n\r\n" + �ı���Ϣ + "");

    } else if (status == 2) {
        if (cm.getPlayer().getParty() == null || !cm.isLeader()) {
            cm.sendOk("�����Ķӳ�������̸����");
        } else {
            var party = cm.getPlayer().getParty().getMembers();
            var mapId = cm.getPlayer().getMapId();
            var next = true;
            var size = 0;
            var it = party.iterator();
            while (it.hasNext()) {
                var cPlayer = it.next();
                var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
                if (ccPlayer == null || ccPlayer.getLevel() > ��ߵȼ� || ccPlayer.getLevel() < ��͵ȼ�) {
                    next = false;
                    break;
                }

            }
            if (party.size() > ������� || party.size() < ��������) {
                next = false;
            }
			
            if (next) {
                var em = cm.getEventManager("Ellin");
                if (em == null) {
                    cm.sendOk("��ǰ���������⣬���������Ա....");
                } else {
                    var prop = em.getProperty("state");
                    if (prop.equals("0") || prop == null) {
                        em.startInstance(cm.getParty(), cm.getMap());
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("�����Ѿ�������,�����Ժ��ڽ��뿴��,�����ǻ�Ƶ");
                    }
                }
            } else {
                cm.sendOk("����: #b����ɭ��#k\r\n����: #b" + �������� + " - " + ������� + "#k\r\n�ȼ�: #b" + ��͵ȼ� + " - " + ��ߵȼ� + "#k");
            }
        }

        cm.dispose();
    }
}