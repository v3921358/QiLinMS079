/*
QILIN ð�յ�
QQ104053090
*/
var ë�� = "#fUI/ChatBalloon.img/pet/12/nw#";
var status = 0;
var sjsx=Math.floor(Math.random()*10);
var ������� = 1000000;
importPackage(java.util);
importPackage(Packages.client);
importPackage(Packages.server);
importPackage(Packages.tools);
importPackage(Packages.tools.packet);
function start() {
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == -1) {
		cm.dispose();
	} else {
		if ((mode == 0 && status == 2) || (mode == 0 && status == 13)) {
			cm.dispose();
			return;
		}
		if (mode == 1)
			status++;
		else
			status--;
		if (status == 0) {
			cm.sendNext("#r#e\t\t\t  "+ ë�� +" װ �� �� �� "+ ë�� +"#k#n\r\n\r\n#e�����������������������ǽ��ܡ�������������������#k#n\r\n#kA:��ά����+2 ��+1 ħ+2\r\n\B:��ά����+2\r\nC:��ά����+1\r\n\
S:��ά����+3 ��+3 ħ+3\r\n#e�����������������������ݽ��ܡ�������������������#k#n  1.��Ҫ���ѵ�װ�������װ������һ������\r\n2.ÿһ��װ��ֻ�ܾ���һ��\r\n3.ð�ս�ң�[5000000]#k\r\n4.ȷ������װ������һҳ");
				} else if (status == 1) {
			var cc = cm.getInventory(1).getItem(1);
			if(cm.getInventory(1).getItem(1)!= null ){
			 cm.sendYesNo("��Ҫ���ѵ�װ��Ϊ:#v"+cc.getItemId()+"#\r\n\r\n#r#eȷ��Ҫ��ʼ������?");
			} else{
			cm.sendOk("#b��һ�����޶�����#k");	
			cm.dispose();
			} 
			
		} else if (status == 2) {
			var item = cm.getInventory(1).getItem(1).copy();
			var xx = cm.getInventory(1).getItem(1).getOwner();
			var dmID = cm.getInventory(1).getItem(1).getItemId();
            if(xx == "A��"){
				cm.sendOk("����װ���Ѿ����ѹ�#rA��#k�ˣ��޷��ټ������ѣ�");
			    cm.dispose();
				}else if(xx == "B��"){
				cm.sendOk("����װ���Ѿ����ѹ�#rB��#k�ˣ��޷��ټ������ѣ�");
			    cm.dispose();
				}else if(xx == "C��"){
				cm.sendOk("����װ���Ѿ����ѹ�#rC��#k�ˣ��޷��ټ������ѣ�");
			    cm.dispose();
				}else if(xx == "S��"){
				cm.sendOk("����װ���Ѿ����ѹ�#rS��#k�ˣ��޷��ټ������ѣ�");
			    cm.dispose();
			} else if (cm.getMeso() < �������){//�ж϶��ٽ��
				cm.sendOk("��Ҳ���"+�������+"��");
				cm.dispose();
			} else if (cm.isCash(cm.getInventory(1).getItem(1).getItemId())) {
				cm.sendOk("�ֽ���߲��ܽ��о��ѣ�");
				cm.dispose();
			} else if (cm.getInventory(1).getItem(1).getUniqueId() > 0) {
                    cm.sendOk("�ֽ�װ���޷����ѡ�");
                    cm.dispose();
			} else if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("��ʱװ�����ܾ���.");
				cm.dispose();
			} else if (sjsx == 10) {//S��

			cm.gainMeso(-�������);//�۳����ٽ��
			var statup = new java.util.ArrayList();
			var itemId1 = cm.getInventory(1).getItem(1).getItemId();
			var item = cm.getInventory(1).getItem(1).copy();
			var ii = MapleItemInformationProvider.getInstance();
			var type =  ii.getInventoryType(itemId1);
			var sx0 = item.getStr();//��ȡװ����ǰ����0
			var sx1 = item.getDex();//��ȡװ����ǰ����1
			var sx2 = item.getInt();//��ȡװ����ǰ����2
			var sx3 = item.getLuk();//��ȡװ����ǰ����3
			var sx4 = item.getHp();//��ȡװ����ǰHP4
			var sx5 = item.getMp();//��ȡװ����ǰMP5
			var sx6 = item.getWatk();//��ȡװ����ǰ�﹥6
			var sx7 = item.getMatk();//��ȡװ����ǰħ��7
			var sx8 = item.getWdef();//��ȡװ����ǰ���8
			var sx9 = item.getMdef();//��ȡװ����ǰħ��9
			var sx10= item.getAcc();//��ȡװ����ǰ����10
			var sx11= item.getAvoid();//��ȡװ����ǰ�ر�11
			var sx12= item.getHands();//��ȡװ����ǰ�ּ�12
			var sx13= item.getSpeed();//��ȡװ����ǰ�ƶ��ٶ�13
			var sx14= item.getJump();//��ȡװ����ǰ��Ծ��14
			item.setFlag(1);//����
			item.setStr(sx0+3);
			item.setDex(sx1+3);
			item.setInt(sx2+3);
			item.setLuk(sx3+3);
			item.setHp(sx4);
			item.setMp(sx5);
			item.setWatk(sx6+3);
			item.setMatk(sx7+3);
			item.setWdef(sx8);
			item.setMdef(sx9);
			item.setAcc(sx10);
			item.setAvoid(sx11);
			item.setHands(sx12);
			item.setSpeed(sx13);
			item.setJump(sx14);
			item.setOwner("S��");
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
			MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //�ɹ�
			cm.sendOk("�Ѿ��Ѿ���#rS��#k�ɹ���");
			cm.serverNotice("[װ������][" + cm.getPlayer().getName() + "]�ɹ������Ѿ�����S�������ף����(��)�ɣ���")
			cm.dispose();
			} else if (sjsx == 8 || sjsx == 9) {//A��
			cm.gainMeso(-�������);//�۳����ٽ��
			var statup = new java.util.ArrayList();
			var itemId1 = cm.getInventory(1).getItem(1).getItemId();
			var item = cm.getInventory(1).getItem(1).copy();
			var ii = MapleItemInformationProvider.getInstance();
			var type =  ii.getInventoryType(itemId1);
			var sx0 = item.getStr();//��ȡװ����ǰ����0
			var sx1 = item.getDex();//��ȡװ����ǰ����1
			var sx2 = item.getInt();//��ȡװ����ǰ����2
			var sx3 = item.getLuk();//��ȡװ����ǰ����3
			var sx4 = item.getHp();//��ȡװ����ǰHP4
			var sx5 = item.getMp();//��ȡװ����ǰMP5
			var sx6 = item.getWatk();//��ȡװ����ǰ�﹥6
			var sx7 = item.getMatk();//��ȡװ����ǰħ��7
			var sx8 = item.getWdef();//��ȡװ����ǰ���8
			var sx9 = item.getMdef();//��ȡװ����ǰħ��9
			var sx10= item.getAcc();//��ȡװ����ǰ����10
			var sx11= item.getAvoid();//��ȡװ����ǰ�ر�11
			var sx12= item.getHands();//��ȡװ����ǰ�ּ�12
			var sx13= item.getSpeed();//��ȡװ����ǰ�ƶ��ٶ�13
			var sx14= item.getJump();//��ȡװ����ǰ��Ծ��14
			item.setFlag(1);//����
			item.setStr(sx0+2);
			item.setDex(sx1+2);
			item.setInt(sx2+2);
			item.setLuk(sx3+2);
			item.setHp(sx4);
			item.setMp(sx5);
			item.setWatk(sx6+1);
			item.setMatk(sx7+2);
			item.setWdef(sx8);
			item.setMdef(sx9);
			item.setAcc(sx10);
			item.setAvoid(sx11);
			item.setHands(sx12);
			item.setSpeed(sx13);
			item.setJump(sx14);
			item.setOwner("A��");
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
			MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //�ɹ�
			cm.sendOk("�Ѿ��Ѿ���#rA��#k�ɹ���");
			cm.serverNotice("[װ������][" + cm.getPlayer().getName() + "]�ɹ������Ѿ�����A�������ף����(��)�ɣ���")
			cm.dispose();
			} else if (sjsx == 7 || sjsx == 6 || sjsx == 5) {//B��
			cm.gainMeso(-�������);//�۳����ٽ��
			var statup = new java.util.ArrayList();
			var itemId1 = cm.getInventory(1).getItem(1).getItemId();
			var item = cm.getInventory(1).getItem(1).copy();
			var ii = MapleItemInformationProvider.getInstance();
			var type =  ii.getInventoryType(itemId1);
			var sx0 = item.getStr();//��ȡװ����ǰ����0
			var sx1 = item.getDex();//��ȡװ����ǰ����1
			var sx2 = item.getInt();//��ȡװ����ǰ����2
			var sx3 = item.getLuk();//��ȡװ����ǰ����3
			var sx4 = item.getHp();//��ȡװ����ǰHP4
			var sx5 = item.getMp();//��ȡװ����ǰMP5
			var sx6 = item.getWatk();//��ȡװ����ǰ�﹥6
			var sx7 = item.getMatk();//��ȡװ����ǰħ��7
			var sx8 = item.getWdef();//��ȡװ����ǰ���8
			var sx9 = item.getMdef();//��ȡװ����ǰħ��9
			var sx10= item.getAcc();//��ȡװ����ǰ����10
			var sx11= item.getAvoid();//��ȡװ����ǰ�ر�11
			var sx12= item.getHands();//��ȡװ����ǰ�ּ�12
			var sx13= item.getSpeed();//��ȡװ����ǰ�ƶ��ٶ�13
			var sx14= item.getJump();//��ȡװ����ǰ��Ծ��14
			item.setFlag(1);//����
			item.setStr(sx0+2);
			item.setDex(sx1+2);
			item.setInt(sx2+2);
			item.setLuk(sx3+2);
			item.setHp(sx4);
			item.setMp(sx5);
			item.setWatk(sx6);
			item.setMatk(sx7);
			item.setWdef(sx8);
			item.setMdef(sx9);
			item.setAcc(sx10);
			item.setAvoid(sx11);
			item.setHands(sx12);
			item.setSpeed(sx13);
			item.setJump(sx14);
			item.setOwner("B��");
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
			MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //�ɹ�
			cm.sendOk("�Ѿ��Ѿ���#rB��#k�ɹ���");
			cm.serverNotice("[װ������][" + cm.getPlayer().getName() + "]�ɹ������Ѿ�����B�������ף����(��)�ɣ���")
			cm.dispose();
			} else {//C��
			cm.gainMeso(-�������);//�۳����ٽ��
			var statup = new java.util.ArrayList();
			var itemId1 = cm.getInventory(1).getItem(1).getItemId();
			var item = cm.getInventory(1).getItem(1).copy();
			var ii = MapleItemInformationProvider.getInstance();
			var type =  ii.getInventoryType(itemId1);
			var sx0 = item.getStr();//��ȡװ����ǰ����0
			var sx1 = item.getDex();//��ȡװ����ǰ����1
			var sx2 = item.getInt();//��ȡװ����ǰ����2
			var sx3 = item.getLuk();//��ȡװ����ǰ����3
			var sx4 = item.getHp();//��ȡװ����ǰHP4
			var sx5 = item.getMp();//��ȡװ����ǰMP5
			var sx6 = item.getWatk();//��ȡװ����ǰ�﹥6
			var sx7 = item.getMatk();//��ȡװ����ǰħ��7
			var sx8 = item.getWdef();//��ȡװ����ǰ���8
			var sx9 = item.getMdef();//��ȡװ����ǰħ��9
			var sx10= item.getAcc();//��ȡװ����ǰ����10
			var sx11= item.getAvoid();//��ȡװ����ǰ�ر�11
			var sx12= item.getHands();//��ȡװ����ǰ�ּ�12
			var sx13= item.getSpeed();//��ȡװ����ǰ�ƶ��ٶ�13
			var sx14= item.getJump();//��ȡװ����ǰ��Ծ��14
			item.setFlag(1);//����
			item.setStr(sx0+1);
			item.setDex(sx1+1);
			item.setInt(sx2+1);
			item.setLuk(sx3+1);
			item.setHp(sx4);
			item.setMp(sx5);
			item.setWatk(sx6);
			item.setMatk(sx7);
			item.setWdef(sx8);
			item.setMdef(sx9);
			item.setAcc(sx10);
			item.setAvoid(sx11);
			item.setHands(sx12);
			item.setSpeed(sx13);
			item.setJump(sx14);
			item.setOwner("C��");
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
			MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //�ɹ�
			cm.sendOk("�Ѿ��Ѿ���#rC��#k�ɹ���");
			cm.serverNotice("[װ������][" + cm.getPlayer().getName() + "]�ɹ������Ѿ�����C�������ף����(��)�ɣ���")
			cm.dispose();
				}
			}
	}}
