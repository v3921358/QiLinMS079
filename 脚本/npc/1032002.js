var status = 0;
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        if (status == 0) {
            var txt = "";
			
            txt = "#e#r����ÿ�����̵�3��NPCŶ��\r\n\r\n��ʾ����Ҫ��ɵڶ��������������ʾ����������\r\n\r\n";
			txt += "#L2##kϡ�е�������#l\r\n\r\n";

            if (cm.getPlayer().getBossLog("��������") == 2){// cm.getPS()  ����˼�� ��ȡ����ֵ�������1 �͵ó��������Ѿ�����˵�һ�� �����������еڶ�������!
				txt += "\r\n\t\t#d���̵���������\r\n";
                txt += "#L1##b�ռ�100����֦#v4000003#�����ң���#l";
				
                cm.sendSimple(txt);
            }else{
                txt += "\r\n#b���Ѿ���ɹ���Ȼ����ȥ��.��ʿ����-�ֿ��ϰ�-������!\r\n��ڶ���������";
                cm.sendOk(txt);
                cm.dispose();
            }

        } else if (selection == 1) {
            if (cm.haveItem(4000003,100)){
                cm.setBossLog("��������");
//cm.gainPS(1);//cm.gainPS(1);  ����˼�� ��������̵�һ����ʱ������� ����ֵ+1��������޷����ظ����ڶ����ˡ�ֻ���賿12��ˢ�²��У�
		
                cm.gainItem(4000003, -100);
                //cm.gainNX(500);
				cm.gainExp(+70000);
				cm.gainMeso(+70000);
                cm.sendOk("���̵�3�����!��ϲ��ý��=70000������=70000\r\n\r\nȻ����ȥ��.��ʿ����-�ֿ��ϰ�-������.������һ����");
				//cm.worldMessage(5,"���������񡿡�"+cm.getName()+"�����̵�3�����!��ϲ��ý��=70000������=100000");
				//cm.����(2,"[��������]�����" + cm.getPlayer().getName() + "���������!��ϲ��ý��=7W������=7W");
                cm.dispose();
            }else{
                cm.sendOk("�ռ�100����֦#v4000003#������!");
                cm.dispose();
            }
		
       // }
		
		} else if (selection == 2) {
				cm.dispose();
				cm.openNpc(1032002,1);
                
            }
		
		
    }
}
