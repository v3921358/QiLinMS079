/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű����������н���
 */
importPackage(java.awt);
importPackage(Packages.tools);
importPackage(Packages.server);
importPackage(Packages.handling.world);
var status;

function start() {
    status = -1;
    action(1, 0, 0);
}
var time = 0;
function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.�Ի�����();
        return;
    } else {
        if (mode == 1){
            status++;
        }else{
            status--;
		}
        var mapId = cm.getMapId();
        if (mapId == 103000890) {
            cm.warp(103000000, "mid00");
            cm.removeAll(4001007);
            cm.removeAll(4001008);
            cm.�Ի�����();
        } else {
            var outText;
            if (mapId == 103000805) {
                outText = "��ȷ��Ҫ�뿪��ͼ����";
                time = 1;
            } else {
                outText = "һ�����뿪��ͼ���㽫���ò�������������������������ٴγ��ԡ��㻹��Ҫ�뿪�����ͼ��";
                time = 0;
            }
            if (status == 0) {
                cm.sendYesNo(outText);
            } else if (mode == 1) {
                if (time == 1) {
                    getPrize();
                }
				cm.warp(103000890, "st00");
                cm.�Ի�����();
            }
        }
    }
}
//����ͨ�ؽ�������
/*
cm.���ʸ���Ʒ(��Ʒ���룬�̶�����������,��ע)��
cm.���ʸ���Ʒ2(��Ʒ���룬�������������,��ע)��
*/
function getPrize() {
	var eim = cm.getEventInstance();
	var ����ͨ�ؾ��� = 3000;
	ver ���� = 1;
	var ��ɫ = cm.getPlayer().id;
	var ����� = cm.getBossRank("��������", 2);
	var ���ط�ĩ��ȡ���� = 10;
	//����ʢ��ĸ�󣬶���
	if(cm.�ж�ÿ��ֵ("�����������ط�ĩ")<=���ط�ĩ��ȡ����){
		cm.���ʸ���Ʒ2(4032135,20,70,"���ط�ĩ");
		cm.����ÿ��ֵ("�����������ط�ĩ");
		if(�����>=50){
		cm.���ʸ���Ʒ(4032135,10,100,"���ط�ĩ");
		}
	}else{
		cm.���˹���("�����޷���ȡ���ط�ĩ��");
	}
	cm.���ʸ���Ʒ2(4002000,3,80,"����ţ��Ʊ");
	cm.���ʸ���Ʒ2(4001009,2,50,"��Ƥ��");
	cm.���ʸ���Ʒ(4006000,10,60,"ħ��ʯ");
	//cm.���ʸ���Ʒ2(4310113,2,7,"���ܲ���");
	//cm.���ʸ���Ʒ2(4310022,2,100,"ӣ���ڼ����");
	//cm.���ʸ���Ʒ2(4310044,2,10,"è�������");
	cm.���ʸ���Ʒ2(4006000,20,50,"ħ��ʯ");
	cm.���ʸ���Ʒ2(4006001,10,50,"�ٻ�ʯ");
	cm.���ʸ���Ʒ2(4004000,2,30,"����ĸ��");
	cm.���ʸ���Ʒ2(4004001,2,30,"�ǻ�ĸ��");
	cm.���ʸ���Ʒ2(4004002,2,30,"����ĸ��");
	cm.���ʸ���Ʒ2(4004003,2,30,"����ĸ��");
	cm.���ʸ���Ʒ2(4170002,1,50,"������ĵ�");
	cm.���ʸ���Ʒ2(4004004,3,1,"�ڰ�ˮ��ĸ��");
	cm.���ʸ���Ʒ2(4020000,3,50,"ʯ��ʯĸ��");
	cm.���ʸ���Ʒ2(4020001,3,50,"��ˮ��ĸ��");
	cm.���ʸ���Ʒ2(4020002,3,50,"����ʯĸ��");
	cm.���ʸ���Ʒ2(4020003,3,50,"��ĸ��ĸ��");
	cm.���ʸ���Ʒ2(4020004,3,50,"����ʯĸ��");
	cm.���ʸ���Ʒ2(4020005,3,50,"����ʯĸ��");
	cm.���ʸ���Ʒ2(4020006,3,50,"�ƾ�ĸ��");
	cm.���ʸ���Ʒ2(4020007,3,50,"��ʯĸ��");
	cm.���ʸ���Ʒ2(4020008,3,50,"��ˮ��ĸ��");
	cm.������(cm.getLevel()*����ͨ�ؾ���);
	//�����ɾͺ��ȡ����ľ��顣
	cm.worldMessage(2, "[����-��������] : ��ϲ " + cm.getPlayer().getName() + " ��ɷ������и�����");
	if(�����>=10){
		cm.������(cm.getLevel()*����ͨ�ؾ���/2);
	}
	//�����ɾͺ�һ�����ʻ�ȡAP�㡣
	if(�����>=150){
		if(cm.�ж�ÿ��ֵ("������AP��")<=2){
			if(cm.�ٷ���(5)){
				cm.��������(1);
				cm.����ÿ��ֵ("������AP��");
				cm.���˹���("��ȡ 1 AP");
			}
		}
	}
	//��¼ͨ����Ϣ
		cm.gainzdjf(����);//����������
		cm.getPlayer().endPartyQuest(1201);
		cm.setBossRankCount("��������",1);
		cm.setBossLog("��������");
		cm.dispose();
		}
		



function formatSeconds(value) {
    var theTime = parseInt(value);
    var theTime1 = 0;
    var theTime2 = 0;
    if (theTime > 60) {
        theTime1 = parseInt(theTime / 60);
        theTime = parseInt(theTime % 60);
        if (theTime1 > 60) {
            theTime2 = parseInt(theTime1 / 60);
            theTime1 = parseInt(theTime1 % 60);
        }
    }
    var result = "" + parseInt(theTime) + " �� ";
    if (theTime1 > 0) {
        result = "" + parseInt(theTime1) + " �� " + result;
    }
    if (theTime2 > 0) {
        result = "" + parseInt(theTime2) + " : " + result;
    }
    return result;
} 