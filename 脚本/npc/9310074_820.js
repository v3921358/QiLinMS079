var status = -1;
var gailv = 50;//����ٷ�֮����Ҫ����ٷֺ�ֻҪ���������������!!

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
			cm.sendOk("��лʹ��~!");
            cm.dispose();
        }
        status--;
    }
    if (status == 0) {
        if (cm.getInventory(1).getItem(1) == null) {
            cm.sendOk("װ������һ���������Ʒ����Ŷ~!");
            cm.dispose();
            return;
        }
		
		
				
		
		//if (cm.getInventory(1).getItem(1).getLevel() >= 5) {
         //   cm.sendOk("ǿ����װ���ȼ����ܴ���#r120#k��Ŷ~!\r\n��ǰ#v"+Id+"##t"+Id+"# #b ��ǿ������:#r"+cm.getInventory(1).getItem(1).getLevel()+"");
         //   cm.dispose();
        //    return;
        //}
		/*
		if (cm.getInventory(1).getItem(1).getUpgradeSlots() >= 5) {
			var Id = cm.getInventory(1).getItem(1).getItemId();
            cm.sendOk("���Ҿ�������ܳ���#r 5 #k��Ŷ~!\r\n��ǰ#v"+Id+"##t"+Id+"# #b ���Ҿ����Ϊ:#r"+cm.getInventory(1).getItem(1).getUpgradeSlots()+"");
            cm.dispose();
            return;
        }
		*/
		/*
		if ((cm.getInventory(1).getItem(1).getUpgradeSlots() + cm.getInventory(1).getItem(1).getLevel()) >= 12) {
			var Id = cm.getInventory(1).getItem(1).getItemId();
            cm.sendOk("�������������ܳ���#r 12 #k��Ŷ~!\r\n��ǰ#v"+Id+"##t"+Id+"# #b ��ǿ������Ϊ:#r"+(cm.getInventory(1).getItem(1).getUpgradeSlots() + cm.getInventory(1).getItem(1).getLevel())+"");

            cm.dispose();
            return;
        }
		*/
		
		
		if (!cm.haveItem(4000463,5) && cm.getMeso() < 500) {
            cm.sendOk("ÿ��ǿ����Ҫ����#b5#k��#i4000463:#��500����");
            cm.dispose();
            return;
        }
		var Id = cm.getInventory(1).getItem(1).getItemId();
        var selStr = "���û�ӭ����#rǿ������#k (�ɹ�����Ϊ50%)\r\n\r\n";
            selStr += "��Ҫǿ���ĵ����� #v"+Id+"##t"+Id+"# \r\n#b Ŀǰ���Ҿ����Ϊ:#r"+cm.getInventory(1).getItem(1).getUpgradeSlots()+"#b\r\nÿ��ǿ������#r1#b�ο��Ҿ����,����#b5#k��#i4000463:#��500����.\r\n\r\n#rǿ��ʧ�ܸ���==50%����ȷ��Ҫǿ����";//��ǰ��ǿ������:#r"+cm.getInventory(1).getItem(1).getLevel()+"
        cm.sendYesNo(selStr);
    } else if (status == 1) {
		
		if (cm.getBossLog("ǿ������4") == 5){
				cm.sendOk("������Ѿ�ǿ����5����,������������!");
				cm.dispose();
			} else if (cm.getInventory(1).getItem(1) == null)  {
				cm.sendOk("���Ҫǿ����װ�����ڵ�һ����ܽ���.");
				cm.dispose();
			} else if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("��ʱװ�����ܽ���ǿ��.");
				cm.dispose();
			} else {
		
		
		s1 = Math.floor(Math.random() * (100 - 1) + 1);
		if(s1 <= gailv){
			cm.gainMeso(-50);
			cm.gainItem(4000463,-5);
			cm.getInventory(1).getItem(1).setUpgradeSlots(cm.getInventory(1).getItem(1).getUpgradeSlots()+1);
			cm.setBossLog('ǿ������4');
			cm.ˢ��״̬();
			cm.����(2,"��ϲ[" + cm.getPlayer().getName() + "]������ǿ����������һ�ο�ǿ��������"); 
			//cm.ȫ����ɫ����("[��ϲ] : " + " : " + "��� [" + cm.getChar().getName() + "]������ǿ����������һ�ο�ǿ��������")
			cm.dispose();
		} else {
			cm.setBossLog('ǿ������4');
			cm.gainMeso(-50);
			cm.gainItem(4000463,-5);
			cm.sendOk("ǿ��ʧ��~!");
            cm.dispose();
		}
    }
}
}