/**
 * @��������������������
 * @ÿ��ǩ������ȡ��Ʒ npc
 * @npcName����������ӪԱ
 * @npcID��   9900004
 **/
var status = 0;
var ��ˮ�� = 4021008;
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var Բ�� = "#fUI/UIWindow/Quest/icon3/6#";
var ����new = "#fUI/UIWindow/Quest/icon5/1#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var �Ҹ� = "#k��ܰ��ʾ���κηǷ��������ҷ�Ŵ���.��ɱ��������.";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            var txt1 = "#L1#" + ��ɫ��ͷ + "50��#d#v4000026# �һ�1��#v4001266#" + ����new + "\r\n\r\n";
            var txt2 = "#L2#" + ��ɫ��ͷ + "50��#d#v4000034# �һ�1��#v4001266#" + ����new + "\r\n\r\n";
            var txt3 = "#L3#" + ��ɫ��ͷ + "50��#d#v4000030# �һ�1��#v4001266#" + ����new + "\r\n\r\n";
            var txt4 = "#L4#" + ��ɫ��ͷ + "50��#d#v4000283# �һ�1��#v4001266#" + ����new + "\r\n\r\n";
            var txt5 = "#L5#" + ��ɫ��ͷ + "50��#d#v4000157# �һ�1��#v4001266#" + ����new + "\r\n\r\n";
            var txt6 = "#L6#" + ��ɫ��ͷ + "50��#d#v4000079# �һ�1��#v4001266#" + ����new + "\r\n\r\n";
            var txt7 = "#L7#" + ��ɫ��ͷ + "50��#d#v4000239# �һ�1��#v4001266#" + ����new + "\r\n\r\n";
            var txt8 = "#L8#" + ��ɫ��ͷ + "50��#d#v4000242# �һ�1��#v4001266#" + ����new + "\r\n\r\n";
            var txt9 = "#L9#" + ��ɫ��ͷ + "50��#d#v4000180# �һ�1��#v4001266#" + ����new + "\r\n\r\n";
            var txt10 = "#L10#" + ��ɫ��ͷ + "50��#d#v4000273# �һ�1��#v4001266#" + ����new + "\r\n\r\n";
            cm.sendSimple("��ӭ������ #r" + cm.getServerName() + "#k ��#k��\r\n\r\n" + txt1 + "" + txt2 + "" + txt3 + "" + txt4 + "" + txt5 + "" + txt6 + "" + txt7 + "" + txt8 + "" + txt9 + "" + txt10 + "");

        } else if (status == 1) {
            if (selection == 1) { //���๦��
			if(cm.itemQuantity(4000026)>=50){
			cm.gainItem(4000026,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("�һ��ɹ���");
                   cm.dispose();
			}else{
			cm.sendOk("��û��50��#v4000026#�޷��һ���");
			cm.dispose();
			}
            } else if (selection == 2) { //���๦��
			if(cm.itemQuantity(4000034)>=50){
			cm.gainItem(4000034,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("�һ��ɹ���");
                   cm.dispose();
			}else{
			cm.sendOk("��û��50��#v4000034#�޷��һ���");
			cm.dispose();
			}
            } else if (selection == 3) { //���๦��
			if(cm.itemQuantity(4000030)>=50){
			cm.gainItem(4000030,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("�һ��ɹ���");
                   cm.dispose();
			}else{
			cm.sendOk("��û��50��#v4000030#�޷��һ���");
			cm.dispose();
			}
			} else if (selection == 4) { //���๦��
			if(cm.itemQuantity(4000283)>=50){
			cm.gainItem(4000283,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("�һ��ɹ���");
                   cm.dispose();
			}else{
			cm.sendOk("��û��50��#v4000283#�޷��һ���");
			cm.dispose();
			}
			} else if (selection == 5) { //���๦��
			if(cm.itemQuantity(4000157)>=50){
			cm.gainItem(4000157,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("�һ��ɹ���");
                   cm.dispose();
			}else{
			cm.sendOk("��û��50��#v4000157#�޷��һ���");
			cm.dispose();
			}
			} else if (selection == 6) { //���๦��
			if(cm.itemQuantity(4000079)>=50){
			cm.gainItem(4000079,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("�һ��ɹ���");
                   cm.dispose();
			}else{
			cm.sendOk("��û��50��#v4000079#�޷��һ���");
			cm.dispose();
			}
			} else if (selection == 7) { //���๦��
			if(cm.itemQuantity(4000239)>=50){
			cm.gainItem(4000239,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("�һ��ɹ���");
                   cm.dispose();
			}else{
			cm.sendOk("��û��50��#v4000239#�޷��һ���");
			cm.dispose();
			}
			} else if (selection == 8) { //���๦��
			if(cm.itemQuantity(4000242)>=50){
			cm.gainItem(4000242,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("�һ��ɹ���");
                   cm.dispose();
			}else{
			cm.sendOk("��û��50��#v4000242#�޷��һ���");
			cm.dispose();
			}
			} else if (selection == 9) { //���๦��
			if(cm.itemQuantity(4000180)>=50){
			cm.gainItem(4000180,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("�һ��ɹ���");
                   cm.dispose();
			}else{
			cm.sendOk("��û��50��#v4000180#�޷��һ���");
			cm.dispose();
			}
			} else if (selection == 10) { //���๦��
			if(cm.itemQuantity(4000273)>=50){
			cm.gainItem(4000273,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("�һ��ɹ���");
                   cm.dispose();
			}else{
			cm.sendOk("��û��50��#v4000273#�޷��һ���");
			cm.dispose();
			}
			
            }
        }
    }
}
