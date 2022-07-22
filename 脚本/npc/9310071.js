/*
QILIN 冒险岛
QQ104053090
*/
var 毛球 = "#fUI/ChatBalloon.img/pet/12/nw#";
var status = 0;
var sjsx=Math.floor(Math.random()*10);
var 金币数量 = 1000000;
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
			cm.sendNext("#r#e\t\t\t  "+ 毛球 +" 装 备 升 星 "+ 毛球 +"#k#n\r\n\r\n#e——————————升星介绍——————————#k#n\r\n#kA:四维属性+2 攻+1 魔+2\r\n\B:四维属性+2\r\nC:四维属性+1\r\n\
S:四维属性+3 攻+3 魔+3\r\n#e——————————内容介绍——————————#k#n  1.需要觉醒的装备请放在装备栏第一个格子\r\n2.每一件装备只能觉醒一次\r\n3.冒险金币：[5000000]#k\r\n4.确定觉醒装备点下一页");
				} else if (status == 1) {
			var cc = cm.getInventory(1).getItem(1);
			if(cm.getInventory(1).getItem(1)!= null ){
			 cm.sendYesNo("你要觉醒的装备为:#v"+cc.getItemId()+"#\r\n\r\n#r#e确定要开始觉醒吗?");
			} else{
			cm.sendOk("#b第一格子无东西！#k");	
			cm.dispose();
			} 
			
		} else if (status == 2) {
			var item = cm.getInventory(1).getItem(1).copy();
			var xx = cm.getInventory(1).getItem(1).getOwner();
			var dmID = cm.getInventory(1).getItem(1).getItemId();
            if(xx == "A级"){
				cm.sendOk("您的装备已经觉醒过#rA级#k了，无法再继续觉醒！");
			    cm.dispose();
				}else if(xx == "B级"){
				cm.sendOk("您的装备已经觉醒过#rB级#k了，无法再继续觉醒！");
			    cm.dispose();
				}else if(xx == "C级"){
				cm.sendOk("您的装备已经觉醒过#rC级#k了，无法再继续觉醒！");
			    cm.dispose();
				}else if(xx == "S级"){
				cm.sendOk("您的装备已经觉醒过#rS级#k了，无法再继续觉醒！");
			    cm.dispose();
			} else if (cm.getMeso() < 金币数量){//判断多少金币
				cm.sendOk("金币不足"+金币数量+"！");
				cm.dispose();
			} else if (cm.isCash(cm.getInventory(1).getItem(1).getItemId())) {
				cm.sendOk("现金道具不能进行觉醒！");
				cm.dispose();
			} else if (cm.getInventory(1).getItem(1).getUniqueId() > 0) {
                    cm.sendOk("现金装备无法觉醒。");
                    cm.dispose();
			} else if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("限时装备不能觉醒.");
				cm.dispose();
			} else if (sjsx == 10) {//S级

			cm.gainMeso(-金币数量);//扣除多少金币
			var statup = new java.util.ArrayList();
			var itemId1 = cm.getInventory(1).getItem(1).getItemId();
			var item = cm.getInventory(1).getItem(1).copy();
			var ii = MapleItemInformationProvider.getInstance();
			var type =  ii.getInventoryType(itemId1);
			var sx0 = item.getStr();//获取装备当前力量0
			var sx1 = item.getDex();//获取装备当前敏捷1
			var sx2 = item.getInt();//获取装备当前智力2
			var sx3 = item.getLuk();//获取装备当前运气3
			var sx4 = item.getHp();//获取装备当前HP4
			var sx5 = item.getMp();//获取装备当前MP5
			var sx6 = item.getWatk();//获取装备当前物攻6
			var sx7 = item.getMatk();//获取装备当前魔攻7
			var sx8 = item.getWdef();//获取装备当前物防8
			var sx9 = item.getMdef();//获取装备当前魔防9
			var sx10= item.getAcc();//获取装备当前命中10
			var sx11= item.getAvoid();//获取装备当前回避11
			var sx12= item.getHands();//获取装备当前手技12
			var sx13= item.getSpeed();//获取装备当前移动速度13
			var sx14= item.getJump();//获取装备当前跳跃力14
			item.setFlag(1);//上锁
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
			item.setOwner("S级");
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
			MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //成功
			cm.sendOk("已觉醒觉醒#rS级#k成功！");
			cm.serverNotice("[装备觉醒][" + cm.getPlayer().getName() + "]成功觉醒已觉醒至S级！大家祝贺他(她)吧！！")
			cm.dispose();
			} else if (sjsx == 8 || sjsx == 9) {//A级
			cm.gainMeso(-金币数量);//扣除多少金币
			var statup = new java.util.ArrayList();
			var itemId1 = cm.getInventory(1).getItem(1).getItemId();
			var item = cm.getInventory(1).getItem(1).copy();
			var ii = MapleItemInformationProvider.getInstance();
			var type =  ii.getInventoryType(itemId1);
			var sx0 = item.getStr();//获取装备当前力量0
			var sx1 = item.getDex();//获取装备当前敏捷1
			var sx2 = item.getInt();//获取装备当前智力2
			var sx3 = item.getLuk();//获取装备当前运气3
			var sx4 = item.getHp();//获取装备当前HP4
			var sx5 = item.getMp();//获取装备当前MP5
			var sx6 = item.getWatk();//获取装备当前物攻6
			var sx7 = item.getMatk();//获取装备当前魔攻7
			var sx8 = item.getWdef();//获取装备当前物防8
			var sx9 = item.getMdef();//获取装备当前魔防9
			var sx10= item.getAcc();//获取装备当前命中10
			var sx11= item.getAvoid();//获取装备当前回避11
			var sx12= item.getHands();//获取装备当前手技12
			var sx13= item.getSpeed();//获取装备当前移动速度13
			var sx14= item.getJump();//获取装备当前跳跃力14
			item.setFlag(1);//上锁
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
			item.setOwner("A级");
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
			MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //成功
			cm.sendOk("已觉醒觉醒#rA级#k成功！");
			cm.serverNotice("[装备觉醒][" + cm.getPlayer().getName() + "]成功觉醒已觉醒至A级！大家祝贺他(她)吧！！")
			cm.dispose();
			} else if (sjsx == 7 || sjsx == 6 || sjsx == 5) {//B级
			cm.gainMeso(-金币数量);//扣除多少金币
			var statup = new java.util.ArrayList();
			var itemId1 = cm.getInventory(1).getItem(1).getItemId();
			var item = cm.getInventory(1).getItem(1).copy();
			var ii = MapleItemInformationProvider.getInstance();
			var type =  ii.getInventoryType(itemId1);
			var sx0 = item.getStr();//获取装备当前力量0
			var sx1 = item.getDex();//获取装备当前敏捷1
			var sx2 = item.getInt();//获取装备当前智力2
			var sx3 = item.getLuk();//获取装备当前运气3
			var sx4 = item.getHp();//获取装备当前HP4
			var sx5 = item.getMp();//获取装备当前MP5
			var sx6 = item.getWatk();//获取装备当前物攻6
			var sx7 = item.getMatk();//获取装备当前魔攻7
			var sx8 = item.getWdef();//获取装备当前物防8
			var sx9 = item.getMdef();//获取装备当前魔防9
			var sx10= item.getAcc();//获取装备当前命中10
			var sx11= item.getAvoid();//获取装备当前回避11
			var sx12= item.getHands();//获取装备当前手技12
			var sx13= item.getSpeed();//获取装备当前移动速度13
			var sx14= item.getJump();//获取装备当前跳跃力14
			item.setFlag(1);//上锁
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
			item.setOwner("B级");
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
			MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //成功
			cm.sendOk("已觉醒觉醒#rB级#k成功！");
			cm.serverNotice("[装备觉醒][" + cm.getPlayer().getName() + "]成功觉醒已觉醒至B级！大家祝贺他(她)吧！！")
			cm.dispose();
			} else {//C级
			cm.gainMeso(-金币数量);//扣除多少金币
			var statup = new java.util.ArrayList();
			var itemId1 = cm.getInventory(1).getItem(1).getItemId();
			var item = cm.getInventory(1).getItem(1).copy();
			var ii = MapleItemInformationProvider.getInstance();
			var type =  ii.getInventoryType(itemId1);
			var sx0 = item.getStr();//获取装备当前力量0
			var sx1 = item.getDex();//获取装备当前敏捷1
			var sx2 = item.getInt();//获取装备当前智力2
			var sx3 = item.getLuk();//获取装备当前运气3
			var sx4 = item.getHp();//获取装备当前HP4
			var sx5 = item.getMp();//获取装备当前MP5
			var sx6 = item.getWatk();//获取装备当前物攻6
			var sx7 = item.getMatk();//获取装备当前魔攻7
			var sx8 = item.getWdef();//获取装备当前物防8
			var sx9 = item.getMdef();//获取装备当前魔防9
			var sx10= item.getAcc();//获取装备当前命中10
			var sx11= item.getAvoid();//获取装备当前回避11
			var sx12= item.getHands();//获取装备当前手技12
			var sx13= item.getSpeed();//获取装备当前移动速度13
			var sx14= item.getJump();//获取装备当前跳跃力14
			item.setFlag(1);//上锁
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
			item.setOwner("C级");
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
			MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //成功
			cm.sendOk("已觉醒觉醒#rC级#k成功！");
			cm.serverNotice("[装备觉醒][" + cm.getPlayer().getName() + "]成功觉醒已觉醒至C级！大家祝贺他(她)吧！！")
			cm.dispose();
				}
			}
	}}
