/* 
 * ��    ��: ����QQ1500663066,���QQ327321366
 * �ű���;: ����
 * ����ʱ��: 2020.4.15
 */


var ca = java.util.Calendar.getInstance();
var �� = ca.get(java.util.Calendar.YEAR); //������
var �� = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var �� = ca.get(java.util.Calendar.DATE);//��ȡ��
var ʱ = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var �� = ca.get(java.util.Calendar.MINUTE);//��÷���
var �� = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);

var status = -1;
var beauty = 0;
var tosend = 0;
var sl;
var mats;

var ��1 = "#fUI/GuildMark/Mark/Animal/00002011/6#";
var ��2 = "#fUI/GuildMark/Mark/Animal/00002011/7#";
var ��3 = "#fUI/GuildMark/Mark/Animal/00002011/8#";
var ��4 = "#fUI/GuildMark/Mark/Animal/00002011/9#";
var ��5 = "#fUI/GuildMark/Mark/Animal/00002011/5#";
var ������ = "#fUI/UIWindow/Quest/icon3/6#";
var С���� = "#fEffect/CharacterEff/1112925/0/0#"; //����
var ������ = "#fUI/StatusBar/BtClaim/normal/0#";
var ���ͼ�� = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var ���ͼ�� = "#fUI/CashShop/CashItem/0#";
var t2 = "#fEffect/CharacterEff/1112902/0/0#";
var ���� ="#fMap/MapHelper/weather/witch/3#";

function start() {
    action(1, 0, 0);
	
}

function action(mode, type, selection) {






    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0 && status == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            if (status == 0) {
                cm.sendNext("�����Ҫ����н�����������Ұɡ�");
                cm.dispose();
            }
            status--;
        }
		

		
		
        if (status == 0) {
			���� = 500;
			if (�� == 52) {
				���� = 1000;
				����˵�� = "#e#r��ϲ������ǰ��ֵ�ɻ�� 1:1000 ��������ʱ��С�#n\r\n";
			} else {
				���� = 200;
				����˵�� = "";
			}
			��� = getmoneyb();
			Ԫ�� = getmoneyb();
            �ۼƳ�ֵ = getmoney();
			��� = cm.getChar().getCSPoints(1);
			
			��ȡ��� = ���*����;
            var text = "";
            text += "\t\t\t       "+ ���� +"#e#d��������#k#n"+ ���� + "#l\r\n\r\n"; 
			text += "#d[����Ϊ���Ĺ����,���������ڷ�������Ӫ]\r\n"			
			text += "#r�������� 1:"+����+" 1Ԫ="+����+"��� #k\r\n"	
			
			
			
			
			text += ""+���ͼ��+"�ۼƳ�ֵ��"+�ۼƳ�ֵ+"     "+���ͼ��+"��ǰ��"+���+" \r\n"
			text += ""+���ͼ��+"��ǰ���"+���+"     \r\n"
			if (��� > 0) {
				text += "#e"+���ͼ��+"����:"+��ȡ���+" ������ȡŶ��("+���+" Ԫ��)#n\r\n"
				text += "#L4##e#b- - - <<<������ȡ>>> - - -#l\r\n"
			} 
			
			text += "#L3##e#r- - - <<<�鿴�ۼ����>>> - - -#l\r\n"
            text += "#L0##e#r- - - <<<���������ֵ>>> - - -#l\r\n"			
			
			cm.sendSimple(text);
 
        } else if (status == 1) {
			sele1 = selection;

            if (selection == 0) { //������ֵ�ӿ�
                    cm.openWeb("https://pay.starmonte.net:8091/pay/recharge/58712b4a08fb4e7fbe37972426459c94"); //����ҳ
					cm.playerMessage(5, "�Ѿ�Ϊ����������ֵ��վ,���û�е�����վ����ϵGM��ֵ");
					cm.dispose();
            } else if (selection == 1) {
				��� = cm.getHyPay(1);
				if (��� >= 1) {
					cm.д���ƹ�ֵ(���);
					cm.addHyPay(���);
					//cm.gainmoney(-���);
					cm.gain(-���);
					cm.worldMessage(9, cm.getC().getChannel(),"���������ġ�" + " : " + " [" + cm.getPlayer().getName() + "] �Ա�������֧�֣� ����ˣ�"+���+"Ԫ�����ƹ��˻��10%������", true);
					cm.dispose();
				} else {
					cm.sendOk("��Ǹ����û�г�ֵ��");
					cm.dispose();
				
				}
			} else if (selection == 2) {
				var msg = "";
				msg += "1Ԫ�� = "+����+"��� ��ǰԪ����"+Ԫ��+"\r\n"
				msg += "������Ԫ�������һ����\r\n"
				cm.sendGetNumber(msg, 1,1, 2000);
			} else if (selection == 3) {
				cm.dispose();
			    cm.openNpc(9900004,2102);
			} else if (selection == 4) {	
                                      
				if (��� >= 1) {
					setmoneyb(0);
					cm.gainNX(��ȡ���);				
					//cm.worldMessage(9, cm.getC().getChannel(),"���������ġ�" + " : " + " [" + cm.getPlayer().getName() + "] �Ա�������֧�֣� ����ˣ�"+���+"Ԫ�����ƹ��˻��10%������", true);
					cm.dispose();
				} else {
					cm.sendOk("��Ǹ����û�г�ֵ��");
					cm.dispose();
				
				}
			} else if (selection == 8) {				
					 
							 
			} else if (selection == 9) { //�ױ��һ�
                    cm.dispose();
			        cm.openNpc(9900004,27);
			} else {
				//cm.sendOk("δ����Ȼ�����Ӵ...");
				cm.dispose();
			}
				 							 
		 

        
        } else if (status == 2) {
			
			sele2 = selection;
			
            if (sele1 == 0) {
	
			} else	if (sele1 == 1) {
			} else	if (sele1 == 2) {
				if (Ԫ�� >= sele2) {
					cm.gainmoney(-sele2);
					cm.gainNX(sele2*����);
					cm.sendOk("�һ��ɹ�");
                    cm.dispose();
				} else {
					cm.sendOk("Ԫ�������޷��һ�");
                    cm.dispose();
				}
				
			

			
			} else {
				
			}	
        
			

            status = -1;
        } else {
            cm.dispose();
        }
    }
}





//V95�˵ĳ�ֵ��ȡ
function getmoney() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("money");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}

function setmoney(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET money = "+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}

function gainmoney(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET money = money+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}


function getmoneyb() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("moneyb");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}
function setmoneyb(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET moneyb = "+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}







function getmoneyc() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("moneyc");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}

function gainmoneyc(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET moneyc = moneyc+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}