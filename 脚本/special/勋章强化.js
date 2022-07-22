var status = 0;
var fstype = 0;
var price = 888888; //砸卷价格
var types = new Array("装备栏", "消耗栏", "任务栏", "杂物栏", "现金栏");
var chance3 = (Math.floor(Math.random() * 8) + 1);

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
        if (mode == 1) status++;
        if (status == 0) {
          cm.sendSimple("\t#v1142265#你好!伟大的#e#b#h ##n#k,每次强化需要3个出席图章，全属性增加1,属性最高加到30\r\n\r\n#b#e#k\r\n#L1##b《《强化勋章》》\r\n\r\n#l");//3
        } else if (status == 1) {
            
           
            if (selection == 1) { //彩色枫叶
                fstype = 2;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
               // var itemid = Math.floor(item.itemId()/10000);
                
                 if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
                }else  if (cm.haveItem(4032398, 3) == false) {
                    cm.sendOk("对不起,你的#z4032398#不足");
                    cm.dispose();
       
                }else  if (item.getStr() >= 30) {
                    cm.sendOk("对不起,你的勋章已经升级到最高级别");
                    cm.dispose();
       
                }else if (Math.floor(item.getItemId()/10000) != 114 ) {
             cm.sendOk("第一格装备是#v" + item.getItemId() + "#,不是勋章!");                                   cm.dispose();    
                }
               else {
             cm.sendNext("你确定要强化这个勋章#v" + item.getItemId() + "吗？");
                }
            }  
                
        } else if (status == 2) {
            
           
            if (fstype == 2) {
                                         
                    var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                    var ii = Packages.server.MapleItemInformationProvider.getInstance();
                   cm.gainItem(4032398, -3);
                 item.setStr(item.getStr()+1);
                item.setDex(item.getDex()+1);
                item.setInt(item.getInt()+1);
                item.setLuk(item.getLuk()+1);
                item.setWatk(item.getWatk()+1);
                item.setMatk(item.getMatk()+1);
                Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                        Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
                cm.dispose();
                return;
            }
           
        }
    }
	    }
