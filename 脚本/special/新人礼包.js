var head = "#fUI/UIWindow2.img/Quest/quest_info/summary_icon/summary#\r\n";
var icon = "#fUI/UIWindow/Minigame/Common/mark#";
var sl1 = 0;//�һ�����

var status = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == 1) {
		status++;
	} else {
		if (status >= 0) {
			cm.dispose();
			return;
		}
		status--;
	}
          if (status == 0) {

			//var text = "#h0#e#d ��ӭ����" + cm.getServerName() + "#k,�ȴ���˽�һ�±���������ɫ\r\n";
			    var text = "#r����������ܣ�ȷ�ϵ���һҳ������ȡ#k#n\r\n";
				text += "#v1332066##z1332066# 1�� ����+2\r\n";
				text += "#v1052550##z1052550# 1�� ����+2\r\n";
				text += "#v1003713##z1003713# 1�� ����+2\r\n";
				text += "#v1082493##z1082493# 1�� ����+2\r\n";
				text += "#v1142358##z1142358# 1�� ����+1\r\n";
				text += "#v1112171##z1112171# 1�� \r\n";
				text += "#v2000005##z2000005#  10ƿ\r\n";
				text += "ð�ս��:10�� ���þ�5000��#k#n\r\n";
			cm.sendSimple(text);
		cm.sendNextS(text, 1);
		} else if (status == 1) {

		if (cm.getOneTimeLog("���ּݵ�") < 1 && cm.getPlayerStat("LVL") < 30) {
			cm.setOneTimeLog("���ּݵ�");	
			cm.gainItem(2000005, 10);//����ҩˮ
			cm.gainItem(1332066,2,2,2,2,0,0,10,10,0,0,0,0,0,0,72);//���ֹκ���
			cm.gainItem(1052550,2,2,2,2,0,0,10,10,0,0,0,0,0,0,72); //��������
			cm.gainItem(1003713,2,2,2,2,0,0,10,10,0,0,0,0,0,0,72); //��������
			cm.gainItem(1082493,2,2,2,2,0,0,10,10,0,0,0,0,0,0,72); //����ñ��
			cm.gainItem(1142358,1,1,1,1,10,10,1,1,0,0,0,0,0,0); //����ѫ������ɰ�
			cm.gainItem(1112171,1); //������Ƭ
			//cm.������װ��(1332066, 1, 0, 20, 20, 20, 20, 50, 50, 30, 30,0, 0, 0, 0, 0, 0, 24);//���ֹκ���ʱ�䵥λ/Сʱ
			//cm.������װ��(1142358, 1, 0, 1, 1, 1, 1, 50, 50, 1, 1,0, 0, 0, 0, 0, 0, 0);//����ѫ������ɰ�
			//cm.������װ��(1142099, 1, 0, 20, 20, 20, 20, 500, 500, 35, 35,0, 0, 0, 0, 40, 40, 24);//ʱ�䵥λ/Сʱ
			//cm.������װ��(1142101, 1, 0, 20, 20, 20, 20, 500, 500, 15, 15,0, 0, 0, 0, 40, 40, 0);
			cm.gainMeso(100000);//���10��
			cm.gainDY(5000);//������þ�5000��               
			cm.sendOk("��ȡ�ɹ���");
			cm.ȫ����ɫ����("[��������] : ��ϲ��� "+cm.getPlayer().getName()+" �ɹ���ȡ�����´������")
            cm.dispose();
				
		} else {
            cm.sendOk("������ȡ����������˻������ĵȼ�����30�������������ˡ�");
            cm.dispose();



            }
        }

}
