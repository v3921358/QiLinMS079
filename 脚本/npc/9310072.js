var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� =  "#fUI/ChatBalloon/37/nw#";
var �� =    "#fUI/ChatBalloon/37/e#";
var �� =    "#fUI/ChatBalloon/37/w#";
var ����è ="#fUI/ChatBalloon/37/s#";
var è���� ="#fUI/ChatBalloon/37/se#";
var è���� ="#fUI/ChatBalloon/37/sw#";
var �ʹڰ� ="#fUI/GuildMark/Mark/Etc/00009004/16#";
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

            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			text += ""
			text += "                  #k"+�ʹڰ�+" #r#e#w" + cm.getServerName() + "#n#k "+�ʹڰ�+"\r\n\r\n";
			text += "  "+è��+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+è��+"\r\n";
            text += "\t#L5##k[#v4170005##r#c4170005##k/1]#k[#v4170013##r#c4170013##k/1]#k[#v4170002##r#c4170002##k/1]�һ�#d#fUI/Basic/BtHide3/mouseOver/0# #v2340000##l\r\n\r\n"//3
			text += "\t#L6##k[#v4170001##r#c4170001##k/1]#k[#v4170004##r#c4170004##k/1]#k[#v4170009##r#c4170009##k/1]�һ�#d#fUI/Basic/BtHide3/mouseOver/0##v2049116##l\r\n\r\n"//3
			//text += "\t#L1##d#v4001126#�һ�#d#fUI/Basic/BtHide3/mouseOver/0#���  (#r���� 1:8000#d)#l\r\n"//3
			//text += "\t#L4##d#v4000313#�һ�#d#fUI/Basic/BtHide3/mouseOver/0#��ȯ  (#r���� 1:100#d)#l\r\n\r\n"//3
			//text += "     #L2#��Ҷһ�#d#fUI/Basic/BtHide3/mouseOver/0#��ȯ  (#r���� 1800��:1��#d)#l\r\n"//3			  
			//text += "     #L3#��ȯ�һ�#d#fUI/Basic/BtHide3/mouseOver/0#��  ��  (#r���� 1��:1800��#d)#l\r\n\r\n"//3
            cm.sendSimple(text);
		}else if (selection == 5) {
			if(cm.haveItem(4170005,1) && cm.haveItem(4170013,1) && cm.haveItem(4170002,1)){
				cm.gainItem(4170005,-1);
				cm.gainItem(4170013,-1);
				cm.gainItem(4170002,-1);
				cm.gainItem(2340000,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����һ���" + " : " + "[" + cm.getChar().getName() + "]ͨ���Ŷ��������棬�һ���һ��ף�����ᣡ")); 
				cm.dispose();
			}else{
				cm.sendOk("\t���ϲ��㡣");
				cm.dispose();
			}
		}else if (selection == 6) {
			if(cm.haveItem(4170001,1) && cm.haveItem(4170004,1) && cm.haveItem(4170009,1) ){
				cm.gainItem(4170001,-1);
				cm.gainItem(4170004,-1);
				cm.gainItem(4170009,-1);
				cm.gainItem(2049116,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����һ���" + " : " + "[" + cm.getChar().getName() + "]ͨ���Ŷ��������棬�һ���һ�����������ᣡ")); 
				cm.dispose();
			}else{
				cm.sendOk("\t���ϲ��㡣");
				cm.dispose();
			}
		}else if (selection == 3) {
			
			if(cm.getPlayer().getMeso() >= 10000 ){ //��Ʒ����
				cm.getPlayer().modifyCSPoints(1,-10000, true);//��ȯ
				cm.gainMeso(+18000000);
				
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����һ���" + " : " + "[" + cm.getChar().getName() + "]�һ���1800w��ң�")); 
		        cm.dispose();
			}else{
				cm.sendOk("\t��ȯ���㡣");
				cm.dispose();
			}
        }else if (selection == 2) { 
			/*if(cm.getPlayer().getBossLogD("��Ҷһ���ȯ") > 4){
				cm.sendOk("\t�����Ѿ��һ���5��.");
				cm.dispose();
				return;
			}*/
			if(cm.getPlayer().getMeso() >= 18000000 ){ //��Ʒ����
				cm.getPlayer().modifyCSPoints(1,10000, true);//��ȯ
				cm.gainMeso(-18000000);
				cm.getPlayer().setBossLog("��Ҷһ���ȯ");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "�����һ���" + " : " + "[" + cm.getChar().getName() + "]Ŭ����ש�һ���10000��ȯ��")); 
		        cm.dispose();
			}else{
				cm.sendOk("\t��Ҳ��㡣");
				cm.dispose();
			}
        } else if (selection == 1) { 
			var zliang = cm.getPlayer().getItemQuantity(4001126, false);
			if (zliang == 0) {
                    cm.sendOk("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 1
					cm.sendYesNo("��ǰ����: #r"+zliang+"#k �����Ƿ������ȫ���һ���");
					}
        } else if (selection == 4) { 
			var zliang = cm.getPlayer().getItemQuantity(4000313, false);
			if (zliang == 0) {
                    cm.sendOk("�����Ʒ����һ�.");
                    status = -1;
                } else {
                    beauty = 4
					cm.sendYesNo("��ǰ����: #r"+zliang+"#k �����Ƿ������ȫ���һ���");
					}
        }  else if (status == 2) {
            if (beauty == 1) {
				var zliang = cm.getPlayer().getItemQuantity(4001126, false);
                if (zliang > 0){
					cm.removeAll(4001126);
					cm.gainMeso(8000*zliang);					
							
                    cm.sendOk("�һ��ɹ������һ���:[#r"+(zliang)+"#k] ����");
					cm.worldMessage(6,"[���һ�]����� "+cm.getName()+" Ŭ����ש,���������һ��ˣ�"+(zliang*8000)+" ��ҡ�");
					cm.dispose();
                } else {
                    cm.sendOk("������Ʒ���㣬�޷��һ���");
                    cm.dispose()
                }            		
            }if (beauty == 4) {
				var zliang = cm.getPlayer().getItemQuantity(4000313, false);
                if (zliang > 0){
					cm.removeAll(4000313);  
					cm.getPlayer().modifyCSPoints(1,zliang*100, true);		
                    cm.sendOk("�һ��ɹ������һ���:[#r"+(zliang)+"#k] ����");
					cm.worldMessage(6,"[���һ�]����� "+cm.getName()+" Ŭ����ש,����������ûƽ��Ҷ�һ��ˣ�"+(zliang*100)+" ��ȯ��");
					cm.dispose();
                } else {
                    cm.sendOk("������Ʒ���㣬�޷��һ���");
                    cm.dispose()
                }            		
            }
        }
    }
}