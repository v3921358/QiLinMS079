var Ǧ��ͼ�� = "#fUI/UIWindow.img/PvP/btWrite/mouseOver/0#";
var ������ = "#fUI/StatusBar/BtClaim/normal/0#";
var ����1 = "#fEffect/CharacterEff/1082565/0/0#";
var ����2 = "#fEffect/CharacterEff/1082565/2/0#";
var ����3 = "#fEffect/CharacterEff/1082565/4/0#";
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
           // cm.Guaguale();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
			if(cm.��ȡ�ƹ���ID() == 0){
			var �ƹ�״̬ = "#r����д�ƹ���#k";
			} else {
			var �ƹ�״̬ = "";	
			}
			var text = "��ã�������#rð��С�����ļϵͳ#k\r\n\r\n#d"+������+"����ƹ�ID��"+cm.getPlayer().getAccountID()+"#r  #d"+�ƹ�״̬+"\r\n";	
			if(cm.��ȡ�ƹ���ID() == 0){
			text += "#L0#"+Ǧ��ͼ��+"��дС���#l#k\r\n\r\n";
			}
			text += "#L1#"+"�ƹ�ֵ�̳�[�ƹ�ֵΪ#e#r"+cm.��ȡ�ƹ�ֵ()+"#n#d]#l\r\n\r\n";
			text += "#L2#"+"#b�ƹ�ϵͳ���� - �����ƹ�ֵ��ϸ˵��";
            cm.sendSimple(text);
        } else if (status == 1) {
            if (selection == 0) {
                if(cm.��ȡ�ƹ���ID() > 0){
               cm.sendOk("��ã����Ѿ���д���ƹ����ˡ��벻Ҫ�ٴ���д��");
               cm.dispose();
                }else{
					cm.dispose();
               cm.openNpc(9010000, "�ƹ���");
			   
                }
            } else if (selection == 1) {
				cm.dispose();
               cm.openNpc(9010000, "�ƹ��̵�");
			  
            } else if (selection == 2) {
                cm.sendOk("�ƹ�ϵͳ˵����\r\nÿ����Ҷ���һ���Լ����ƹ�ֵ������ƹ�ֵ��#r"+cm.getPlayer().getAccountID()+"#k������������д���ƹ��˵��ƹ�ֵ�����ƹ����ҳ�ֵ���ƹ��˿����õ���Ӧ��ֵ���Ļ��֡�\r\n���磺\r\n��д������ƹ�ֵ����ҳ�ֵ��#r1000#kԪ����Ϳ�����ȡ#r1000#k���ƹ�ֵ������\r\n���ƹ�����ת��һ��,�ƹ��˿��Ի��#r2#k���ƹ�ֵ����.\r\n�ƹ��˿����������ñ��ƹ��ˡ�Ҳ����˵�����ϵͳû���������ޡ�");
                cm.dispose();
        }
    }

    }    }
