var status = 0;
var fstype = 0;
var price = 888888; //�Ҿ�۸�
var types = new Array("װ����", "������", "������", "������", "�ֽ���");
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
          cm.sendSimple("\t#v1142265#���!ΰ���#e#b#h ##n#k,ÿ��ǿ����Ҫ3����ϯͼ�£�ȫ��������1,������߼ӵ�30\r\n\r\n#b#e#k\r\n#L1##b����ǿ��ѫ�¡���\r\n\r\n#l");//3
        } else if (status == 1) {
            
           
            if (selection == 1) { //��ɫ��Ҷ
                fstype = 2;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
               // var itemid = Math.floor(item.itemId()/10000);
                
                 if (item == null) {
                    cm.sendOk("�Բ���,��װ������һ��û��װ��!");
                    cm.dispose();
                }else  if (cm.haveItem(4032398, 3) == false) {
                    cm.sendOk("�Բ���,���#z4032398#����");
                    cm.dispose();
       
                }else  if (item.getStr() >= 30) {
                    cm.sendOk("�Բ���,���ѫ���Ѿ���������߼���");
                    cm.dispose();
       
                }else if (Math.floor(item.getItemId()/10000) != 114 ) {
             cm.sendOk("��һ��װ����#v" + item.getItemId() + "#,����ѫ��!");                                   cm.dispose();    
                }
               else {
             cm.sendNext("��ȷ��Ҫǿ�����ѫ��#v" + item.getItemId() + "��");
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
