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
            txt = "����ÿ�����̵�5��NPCŶ��\r\n\r\n";

            if (cm.getPlayer().getBossLog("��������") == 4){// cm.getPS()  ����˼�� ��ȡ����ֵ�������1 �͵ó��������Ѿ�����˵�һ�� �����������еڶ�������!

                txt += "#L1##b�ռ�100������Ƥ#v4000032#�����ң���#l";
                cm.sendSimple(txt);
            }else{
                txt += "���Ѿ���ɹ���Ȼ����ȥ��.����֮��-�ֿ����Ա-������!\r\n��ڶ���������";
                cm.sendOk(txt);
                cm.dispose();
            }

        } else if (selection == 1) {
            if (cm.haveItem(4000032,100)){
                cm.setBossLog("��������");
//cm.gainPS(1);//cm.gainPS(1);  ����˼�� ��������̵�һ����ʱ������� ����ֵ+1��������޷����ظ����ڶ����ˡ�ֻ���賿12��ˢ�²��У�
		
                cm.gainItem(4000032, -100);
                cm.gainItem(4002000, 5);
                //cm.gainNX(500);
				cm.gainExp(+120000);
				cm.gainMeso(+200000);
                cm.sendOk("���̵�5�����!��ϲ��ý��=2000000������=120000\r\n\r\nȻ����ȥ��..����֮��-�ֿ����Ա-������.������һ����");
				//cm.worldMessage(5,"���������񡿡�"+cm.getName()+"�����̵�5�����!��ϲ��ý��=2000000������=150000");
				//cm.����(2,"[��������]�����" + cm.getPlayer().getName() + "���廷���!��ϲ��ý��=10W������=12W ��ţ��Ʊ5��");
                cm.dispose();
            }else{
                cm.sendOk("�ռ�100������Ƥ#v4000032#������!");
                cm.dispose();
            }
        }
    }
}
