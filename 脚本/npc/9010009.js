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
			//��ʾ��ƷIDͼƬ�õĴ�����  #v����д��ID#
            text += "#e#r��ã�������ð�յ�˳����:#bPS.�ȼ�Խ�߽���Խ��.\r\n#r�ͻ������кö�ö໷��Ҳ���㲻һ���ܹ����꣬���������Ƕ���Ŷ��ÿһ�����з����.\r\n"//3
			text += "#L22#�����˽�#b���̹���#k.\r\n\r\n"//3
			text += "#L1##e#d#v4031149# ��һ��.\r\n#b����; \t[����"+cm.getLevel()*500+"]\t\t[���"+cm.getLevel()*1000+"]\r\n\t\t[�ƽ��Ҷ*5]\t\t[���þ�1000]\r\n\r\n"//3
	        text += "#L3##e#d#v4031149# �ڶ���.\r\n#b����; \t[����"+cm.getLevel()*1000+"]\t\t[���"+cm.getLevel()*2000+"]\r\n\t\t[�ƽ��Ҷ*10]\t\t[���þ�1500]\r\n\r\n"//3
			text += "#L4##e#d#v4031149# ������.\r\n#b����; \t[����"+cm.getLevel()*1500+"]\t\t[���"+cm.getLevel()*3000+"]\r\n\t\t[�ƽ��Ҷ*15]\t\t[���þ�2000]\r\n\r\n"//3
			text += "#L5##e#d#v4031149# ������.\r\n#b����; \t[����"+cm.getLevel()*2000+"]\t\t[���"+cm.getLevel()*4000+"]\r\n\t\t[�ƽ��Ҷ*20]\t\t[���þ�2500]\r\n\r\n"//3
			text += "#L6##e#d#v4031149# ������.\r\n#b����; \t[����"+cm.getLevel()*2500+"]\t\t[���"+cm.getLevel()*5000+"]\r\n\t\t[�ƽ��Ҷ*25]\t\t[���þ�3000]\r\n\r\n"//3
			text += "#L7##e#d#v4031149# ������.\r\n#b����; \t[����"+cm.getLevel()*3000+"]\t\t[���"+cm.getLevel()*6000+"]\r\n\t\t[�ƽ��Ҷ*30]\t\t[���þ�1000]\r\n\r\n"//3
			text += "#L8##e#d#v4031149# ������.\r\n#b����; \t[����"+cm.getLevel()*3500+"]\t\t[���"+cm.getLevel()*7000+"]\r\n\t\t[�ƽ��Ҷ*35]\t\t[���1500]\r\n\r\n"//3
			text += "#L9##e#d#v4031149# �ڰ���.\r\n#b����; \t[����"+cm.getLevel()*4000+"]\t\t[���"+cm.getLevel()*8000+"]\r\n\t\t[�ƽ��Ҷ*40]\t\t[���2000]\r\n\r\n"//3
			text += "#L10##e#d#v4031149# �ھ���.\r\n#b����; \t[����"+cm.getLevel()*4500+"]\t\t[���"+cm.getLevel()*9000+"]\r\n\t\t[�ƽ��Ҷ*45]\t\t[���2500]\r\n\r\n"//3
			text += "#L11##e#d#v4031149# ��ʮ��. \r\n#b����; \t[����"+cm.getLevel()*10000+"]\t\t[���"+cm.getLevel()*10000+"]\r\n\t\t[�ƽ��Ҷ*50]\t\t[���3000]\r\n\r\n"//3
            //text += "#L2##e#d#v4031017# �������һ��̶�����.#l\r\n\r\n"//3
            //text += "#L3##e#d#v04032226# ÿ�ջ�����һ���1Сʱ/1��.ʤ�����ɣ�#l\r\n"//3
            //text += "#L4##e#d#v1382057#�������������#l\r\n"//3
            //text += "#L5##e#d#v1402046#������ڤ������#l\r\n"//3
            //text += "#L6##e#d#v1432047#������ʥǹ����#l\r\n"//3
            //text += "#L7##e#d#v1442063#������������#l\r\n"//3
            //text += "#L8##e#d#v1452057#���㾪�繭����#l\r\n"//3
            //text += "#L9##e#d#v1462050#����ڤ��������#l\r\n"//3
            //text += "#L10##e#d#v1472068#����󱯸�����#l\r\n"//3
            cm.sendSimple(text);
        } else if (selection == 1) {
			cm.dispose();
		cm.openNpc(9010009, 1);
        } else if (selection == 22) {
			cm.dispose();
		cm.openNpc(9010009, 22);
        } else if (selection == 3) {
			cm.dispose();
		cm.openNpc(9010009, 5);
        } else if (selection == 4) {
			cm.dispose();
		cm.openNpc(9010009, 6);
        } else if (selection == 5) {
			cm.dispose();
		cm.openNpc(9010009, 7);
        } else if (selection == 6) {
			cm.dispose();
		cm.openNpc(9010009, 8);
        } else if (selection == 7) {
			cm.dispose();
		cm.openNpc(9010009, 9);
        } else if (selection == 8) {
			cm.dispose();
		cm.openNpc(9010009, 10);
        } else if (selection == 9) {
			cm.dispose();
		cm.openNpc(9010009, 11);
        } else if (selection == 10) {
			cm.dispose();
		cm.openNpc(9010009, 12);
		} else if (selection == 11) {
			cm.dispose();
		cm.openNpc(9010009, 13);
	}
    }
}


