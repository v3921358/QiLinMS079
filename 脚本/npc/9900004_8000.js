
var ���ڽ����� = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var ��� = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var ���ڽ������� = "#fUI/UIWindow/MonsterCarnival/icon1#";
var ��ɺ� = "#fUI/UIWindow/MonsterCarnival/icon0#";
function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("��л��Ĺ��٣�");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			text += "\t\t\t  #e#d��ӭ�������߽���ϵͳ\r\n\r\n"
			text += "\t\t\t#r���ĵ�ǰ����ʱ�䣺"+cm.getGamePoints()+" ����#k!#n\r\n"
			if(cm.getGamePoints() >= 60 && cm.getBossLog("zxsj") == 0){
					text += "#L2##r"+��ɺ�+"����ʱ�䳬��60���ӣ�"+���+"#l\r\n\r\n\r\n"
				} else if(cm.getGamePoints() >= 60 && cm.getBossLog("zxsj") > 0){
					text += ""+��ɺ�+"#r����ʱ�䳬��60���ӣ�#l"+���+"\r\n\r\n"//3
				} else {
					text += ""+���ڽ�������+"#r����ʱ�䳬��60���ӣ���Ʒ����ʯ2����#l"+���ڽ�����+"\r\n\r\n"
			}
			
			if(cm.getGamePoints() >= 120 && cm.getBossLog("zxsj") == 1){
					text += "#L3##r"+��ɺ�+"����ʱ�䳬��120���ӣ�"+���+"#l\r\n\r\n\r\n"
				} else if(cm.getGamePoints() >= 120 && cm.getBossLog("zxsj") > 1){
					text += ""+��ɺ�+"#r����ʱ�䳬��120���ӣ�#l"+���+"\r\n\r\n"
				} else {
					text += ""+���ڽ�������+"#r����ʱ�䳬��120���ӣ���Ʒ�ƽ��Ҷ20����#l"+���ڽ�����+"\r\n\r\n"
			}
			
			if(cm.getGamePoints() >= 180 && cm.getBossLog("zxsj") == 2){
					text += "#L4##r"+��ɺ�+"����ʱ�䳬��180���ӣ�"+���+"#l\r\n\r\n\r\n"
				} else if(cm.getGamePoints() >= 180 && cm.getBossLog("zxsj") > 2){
					text += ""+��ɺ�+"#r����ʱ�䳬��180���ӣ�#l"+���+"\r\n\r\n"
				} else {
					text += ""+���ڽ�������+"#r����ʱ�䳬��180���ӣ���Ʒ�ʼҷ���2�ţ�#l"+���ڽ�����+"\r\n\r\n"
			}
			
			if(cm.getGamePoints() >= 240 && cm.getBossLog("zxsj") == 3){
					text += "#L5##r"+��ɺ�+"����ʱ�䳬��240���ӣ�"+���+"#l\r\n\r\n\r\n"
				} else if(cm.getGamePoints() >= 240 && cm.getBossLog("zxsj") > 3){
					text += ""+��ɺ�+"#r����ʱ�䳬��240���ӣ�#l"+���+"\r\n\r\n"
				} else {
					text += ""+���ڽ�������+"#r����ʱ�䳬��240���ӣ���Ʒ��Ҷ100�� ����3����#l"+���ڽ�����+"\r\n\r\n"
			}
			
			if(cm.getGamePoints() >= 300 && cm.getBossLog("zxsj") == 4){
					text += "#L6##r"+��ɺ�+"����ʱ�䳬��300���ӣ�"+���+"#l\r\n\r\n\r\n"
				} else if(cm.getGamePoints() >= 300 && cm.getBossLog("zxsj") > 4){
					text += ""+��ɺ�+"#r����ʱ�䳬��300���ӣ�#l"+���+"\r\n\r\n"
				} else {
					text += ""+���ڽ�������+"#r����ʱ�䳬��300���ӣ���Ʒ1000����ȯ 1000��ȯ#l"+���ڽ�����+"\r\n\r\n"
			}
			if(cm.getGamePoints() >= 360 && cm.getBossLog("zxsj") == 5){
					text += "#L7##r"+��ɺ�+"����ʱ�䳬��360���ӣ�"+���+"#l\r\n\r\n\r\n"
				} else if(cm.getGamePoints() >= 360 && cm.getBossLog("zxsj") > 5){
					text += ""+��ɺ�+"#r����ʱ�䳬��360���ӣ�#l"+���+"\r\n\r\n"
				} else {
					text += ""+���ڽ�������+"#r����ʱ�䳬��360���ӣ���Ʒ�Ͷ�����+ǩ��ͼ��+��������#l"+���ڽ�����+"\r\n\r\n"
			}
            cm.sendSimple(text);
        } else if (selection == 2) {
			cm.gainItem(5040000, 2);
			cm.setBossLog('zxsj');
            cm.sendOk("��ȡ�����ɹ���");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]��ȡ��60�������߽�����.");
            cm.dispose();
        } else if (selection == 3) {
			//cm.gainExpR(20000);
			cm.gainItem(4000313, 20);
			cm.setBossLog('zxsj');
            cm.sendOk("��ȡ�����ɹ���");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]��ȡ��120�������߽�����");
            cm.dispose();
        } else if (selection == 4) {
			//cm.gainMeso(50000);
			cm.gainItem(5150040, 2);
			//cm.gainItem(5121015,3,3);
			cm.setBossLog('zxsj');
            cm.sendOk("��ȡ�����ɹ���");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]��ȡ��180�������߽�����");
            cm.dispose();
        } else if (selection == 5) {
			cm.gainItem(5390002, 3);//����
			cm.gainItem(4001126, 100);//��Ҷ
			cm.setBossLog('zxsj');
            cm.sendOk("��ȡ�����ɹ���");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]��ȡ��240�������߽�����");
            cm.dispose();
        } else if (selection == 6) {
			cm.gainDY(1000);//����ȯ
			//cm.gainItem(5050000, 1, 7);//�����
			cm.gainNX(1000);
			cm.setBossLog('zxsj');
            cm.sendOk("��ȡ�����ɹ���");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]��ȡ��300�������߽�����");
            cm.dispose();
		} else if (selection == 7) {
			//cm.gainDY(200);//����ȯ
			//cm.gainItem(5040000, 5, 7);//����ʯ
			//cm.gainItem(5150040, 1, 15);//�ʼ�
			//cm.gainDY(666);
			cm.gainItem(4001266, 1);//�Ͷ�����
			cm.gainItem(4032398, 1);//ͼ��
			cm.gainExpR(666666);
			cm.setBossLog('zxsj');
            cm.sendOk("��ȡ�����ɹ���");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]��ȡ��360�������߽�����");
            cm.dispose();
		}
    }
}


