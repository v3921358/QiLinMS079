function start() {
    status = -1;

    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("��л��Ĺ��٣�");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }
        if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
            text += "\r\n\r\n#r��������1:100���\r\n#k\r\n"
			//text += "#e#r#L0#��ֵ���а�#l#n\r\n\r\n"
            text += "#e#r#L1#������վ#l#n\r\n\r\n"
		//	text += "#e#r#L2#��������ȡ-��ǰ#g "+cm.getwzcz() +"#r��ȯ��δ��ȡ������ȡ��#l#n\r\n\r\n"
            text += "#e#r#L3#�������#l#n\r\n\r\n"
            cm.sendOk(text); 
		} else if (selection == 0) {
			cm.showcz();
		   cm.dispose();
        } else if (selection == 1) {
         cm.openWeb("http://new.shoukabao.cn/Payment/Service/eb81c45ec46083d9cbc89c884cd691e6");
		// cm.openWeb("http://WWW.BAIDU.COM");
		 cm.sendOk("��ֵ��Ϻ�,#r�뵥��ȷ�Ϸ��أ���ֵ��ȡ\r\n");
          status = -1;
        } else if (selection == 2) {
            if(cm.getwzcz()==0){
		cm.sendOk("����ǰδ���ֽ��Ϊ"+ cm.getwzcz() +"��ȯ ���һ�ʧ�ܣ�\r\n#r.");
		 status = -1;
		}else{
		var  j = cm.getwzcz();
		cm.setwzcz(-j);
		cm.gainDJ(j*1);
        cm.gaincz(+(j/100));	
		//********************************************************
		
		if(cm.gettgr()!=0){//���ƹ���
			cm.gaintgrjl(cm.gettgr(),+j*0.2);//5%
		}
		
		
		
		
		//***********************************
        cm.sendOk("���ֳɹ������"+ j*1 + "���\r\n#r.");
		 status = -1;
        }
		} else if (selection == 3) {
		cm.openNpc(9900004, "��ֵ���");
		} else if (selection == 4) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b�뱣֤װ����λ������1���ո�,�����޷��һ�");
				cm.dispose();
			} else if (cm.getcz()<200) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("û��ֵ���������ֵ�ɣ�\r\n��ǰ��ֵ��"+cm.getcz()+"Ԫ��");
				cm.dispose();
			} else if (!cm.getczlz() == 1) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("��������ʲô����");
				cm.dispose();
			} else{
				cm.setczlz(1);
				cm.gainMeso(1500000);//�����
				cm.sendOk("�ɹ��һ�#k")
				status = -1;
			}
		} else if (selection == 5) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b�뱣֤װ����λ������1���ո�,�����޷��һ�");
				cm.dispose();
			} else if (cm.getcz()<500) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("û��ֵ���������ֵ�ɣ�\r\n��ǰ��ֵ��"+cm.getcz()+"Ԫ��");
				cm.dispose();
			} else if (!cm.getczlz() == 2) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("��������ʲô����");
				cm.dispose();
			} else{
				cm.setczlz(1);
				cm.gainItem(4001126, 5000);//��Ʒ
				cm.gainItem(3010070, 1);//��Ʒ
				cm.sendOk("�ɹ��һ�#k")
				status = -1;
			}
		} else if (selection == 6) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b�뱣֤װ����λ������1���ո�,�����޷��һ�");
				cm.dispose();
			} else if (cm.getcz()<1000) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("û��ֵ���������ֵ�ɣ�\r\n��ǰ��ֵ��"+cm.getcz()+"Ԫ��");
				cm.dispose();
			} else if (!cm.getczlz() == 3) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("��������ʲô����");
				cm.dispose();
			} else{
				cm.setczlz(1);
				cm.gainItem(4001126, 9999);//��Ʒ
				cm.gainItem(4001126, 1);//��Ʒ
				cm.gainItem(1102086, 1);//��Ʒ
				cm.sendOk("�ɹ��һ�#k")
				status = -1;
			}
			} else if (selection == 7) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b�뱣֤װ����λ������1���ո�,�����޷��һ�");
				cm.dispose();
			} else if (cm.getcz()<5000) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("û��ֵ���������ֵ�ɣ�\r\n��ǰ��ֵ��"+cm.getcz()+"Ԫ��");
				cm.dispose();
			} else if (!cm.getczlz() == 4) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("��������ʲô����");
				cm.dispose();
			} else{
				cm.setczlz(1);
				cm.gainItem(4001126, 9999);//��Ʒ
				cm.gainItem(4001126, 9999);//��Ʒ
				cm.gainItem(4001126, 2);//��Ʒ
				cm.gainItem(1102041, 1);//��Ʒ
				cm.sendOk("�ɹ��һ�#k")
				status = -1;
			}
				} else if (selection == 8) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b�뱣֤װ����λ������1���ո�,�����޷��һ�");
				cm.dispose();
			} else if (cm.getcz()<10000) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("û��ֵ���������ֵ�ɣ�\r\n��ǰ��ֵ��"+cm.getcz()+"Ԫ��");
				cm.dispose();
			} else if (!cm.getczlz() == 5) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("��������ʲô����");
				cm.dispose();
			} else{
				cm.setczlz(1);
				cm.gainItem(1102163, 1);//��Ʒ
				cm.sendOk("�ɹ��һ�#k")
				status = -1;
			}
			} else if (selection == 9) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b�뱣֤װ����λ������1���ո�,�����޷��һ�");
				cm.dispose();
			} else if (cm.getcz()<50000) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("û��ֵ���������ֵ�ɣ�\r\n��ǰ��ֵ��"+cm.getcz()+"Ԫ��");
				cm.dispose();
			} else if (!cm.getczlz() == 5) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("��������ʲô����");
				cm.dispose();
			} else{
				cm.setczlz(1);
				cm.gainItem(1102163, 1);//��Ʒ
				cm.sendOk("�ɹ��һ�#k")
				status = -1;
			}
			} else if (selection == 10) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b�뱣֤װ����λ������1���ո�,�����޷��һ�");
				cm.dispose();
			} else if (cm.getcz()<100000) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("û��ֵ���������ֵ�ɣ�\r\n��ǰ��ֵ��"+cm.getcz()+"Ԫ��");
				cm.dispose();
			} else if (!cm.getczlz() == 6) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("��������ʲô����");
				cm.dispose();
			} else{
				cm.setczlz(1);
				cm.gainItem(1142742, 1);//��Ʒ
				cm.sendOk("�ɹ��һ�#k")
				status = -1;
			}
			} else if (selection == 11) {
		if (cm.getInventory(1).isFull(0)){
				cm.sendOk("#b�뱣֤װ����λ������1���ո�,�����޷��һ�");
				cm.dispose();
			} else if (cm.getcz()<500000) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("û��ֵ���������ֵ�ɣ�\r\n��ǰ��ֵ��"+cm.getcz()+"Ԫ��");
				cm.dispose();
			} else if (!cm.getczlz() == 6) {//�ж��ۻ���100Ԫ���� ��һ�����û��ȡ
				cm.sendOk("��������ʲô����");
				cm.dispose();
			} else{

				cm.sendOk("����ϵȺ����#k")
				status = -1;
			}
    }
}}