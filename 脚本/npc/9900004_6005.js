var MapleInventoryManipulator = Java.type('server.MapleInventoryManipulator');
var MapleItemInformationProvider = Java.type('server.MapleItemInformationProvider');
var DatabaseConnection = Java.type('database.DatabaseConnection');

var status = 0;
var ringnum = 0;
var id = 0;
var itemIndex;
var itemList = Array(//Array(id,Num)
Array(4000000,50),Array(4000001,50),Array(4000002,50),//����ţ�ǣ���Ģ���ǣ�������
Array(4000003,50),Array(4000004,50),Array(4000005,50),//��֦����Ҷ��Ҷ��
Array(4000006,50),Array(4000007,50),Array(4000008,50),//��������Ĵ��֣��������֮β������
Array(4000009,50),Array(4000010,50),Array(4000011,50),//��Ģ���ǣ���ˮ���飬Ģ��ѿ�� 
Array(4000012,50),Array(4000013,50),Array(4000014,50),//��Ģ���ǣ��������֮β������ͷ��
Array(4000015,50),Array(4000016,50),Array(4000018,50),//��Ģ���ǣ�����ţ�ǣ�ľ��
Array(4000019,50),Array(4000026,50),Array(4000029,50),//����ţ�ǣ��������ޣ��㽶
Array(4000031,50),Array(4000032,50),Array(4000034,50),//�������ޣ�����Ƥ����Ƥ
Array(4000035,50),Array(4000036,50),Array(4000037,50),//�����������ҩ����ˮ���ˮ��
Array(4000039,50),Array(4000042,50),Array(4000043,50),//�������㣬�����򣬺��зǯ
Array(4000044,50),Array(4000045,50),Array(4000048,50),//���зǯ���ڹ�ǣ�С��ѩ��Ƥ

Array(4000051,50),Array(4000052,50),Array(4000053,25),//Ұ��֮β������֮β�����˵Ľ�ֺ��
Array(4000054,25),Array(4000059,50),Array(4000060,50),//�����˵Ľ�ֺ�ף��ǹ⾫����ǿ飬�¹⾫����ǿ�
Array(4000061,50),Array(4000069,50),Array(4000074,50),//�չ⾫����տ飬��ʬ���ݣ���ɫ��ʨβ

Array(4000070,50),Array(4000071,50),Array(4000072,50),//�����ʨβ���ƶ���ʨβ��������ʨβ
Array(4000101,50),Array(4000102,50),Array(4000106,50),//��ɫ���Ȧ����ɫ���Ȧ�������è���޻���
Array(4000107,50),Array(4000108,50),Array(4000115,50),//�����è�Ļ�ɫ˿������è���ޣ�����
Array(4000128,50),Array(4000129,50),Array(4000143,50),//��С���ñ�ӣ���С���С�飬��ʬ����
Array(4000161,50),Array(4000162,50),Array(4000180,50),//�����β�ͣ���������Ƥ���������
Array(4000181,50),Array(4000182,50),Array(4000183,50),//�䶳��ᣬʯ�ҷ�ƿ��ī֭ƿ
Array(4000187,50),Array(4000188,50),Array(4000189,50),//��צ��Ѽ������ë
Array(4000190,50),Array(4000191,50),Array(4000192,50),//ɽ��ǣ���ɽ��ǣ��ǻ�
Array(4000196,50),Array(4000197,50),Array(4000204,50),//ľ�壬ʯ�壬����Ȯ��ͷ
Array(4000205,50),Array(4000206,50),Array(4000207,50),//�������߹ǣ�����
Array(4000292,50),Array(4000293,50),Array(4000294,50),//ɽ�������۹�������۹�
Array(4000329,50),Array(4000330,50),Array(4000331,50),//�����������ƵĴ̣������ƵĻ�
Array(4000379,50),Array(4000380,50),Array(4000380,50),//��ɫ��������ɫ��������ɫ����
Array(4000463,1),Array(4004000,10),Array(4004001,10),//�������ң�����ĸ���ǻ�ĸ��
Array(4004002,10),Array(4004003,10),Array(4004004,10),//����ĸ������ĸ�󣬺ڰ�ˮ��ĸ��
Array(1462019,1),Array(1452022,1),Array(1382012,1),//��Ҷϵ������
Array(1472032,1),Array(1332025,1),Array(1492020,1),//��Ҷϵ������
Array(1482020,1),Array(1442024,1),Array(1302030,1),//��Ҷϵ������
Array(1412011,1),Array(1422014,1),Array(1432012,1)

);
var myDate = new Date();
var year = myDate.getFullYear();
var month = myDate.getMonth() + 1;
var days = myDate.getDate();
var giftList = Array(//Array(id,Num)
	Array(2022468,3)//
);
var finalGiftList = Array(//Array(id,Num)
	Array(2022468,5)//
);
function start() {
    status = -1;
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
        	id = cm.getPlayer().getId();
        	ringnum = cm.getBossLog("paoshang");
			
        	var strlen = "���������������ҵ�������#e#r��ע������������Ͼ�Ϊ���������һ�������������� 20����������� ����ף�� ��#l\r\n\r\n";
        	if(ringnum == 0)
            	strlen += "#r�淨���飺 #bÿ����������յ�һ����������Ҫ������Ҫ�Ĳ��������������ҵ��������һ����һ���޴�Ľ�ƷŶ��ÿ���Ҷ����ṩ����20���������������ȡ�޶�󽱣�";
            else if (ringnum <= 20){
            	strlen += "���Ѿ��������ε�ί����ô��";
            }else{
				strlen = "�����������Ѿ�����˵����������̣�";
				cm.sendOk(strlen);
				cm.dispose();
				return;
			}
            cm.sendNext(strlen);
        }else if(status == 1){
			if(cm.getInventory(4).isFull()){
    		cm.sendOk("#b�뱣֤������λ������2���ո�,�����޷�����.");
    		cm.dispose();
    		return;
			}
			if (cm.getInventory(2).isFull()){
            cm.sendOk("#b�뱣֤������λ������2���ո�,�����޷�����.");
            cm.dispose();
            return;
			}
        	itemIndex = getOneTimeLog(id);
        	if(ringnum > 0 && ringnum < 20){//ÿ�ֽ���
        		if(cm.haveItem(itemList[itemIndex][0],itemList[itemIndex][1])){
        			cm.gainItem(itemList[itemIndex][0],-itemList[itemIndex][1]);
        			cm.getPlayer().gainMeso(500000, true);
        			if(ringnum%5 == 0){
        				cm.gainItem(4000463,1);//��������
						//cm.gainItem(3600001,50);//������
						cm.gainItem(4000313,10);//�ƽ��Ҷ
        				cm.gainItem(4001126,200);//��Ҷ
        			}
        			cm.sendNext("��ϲ�������������̣��������");
        		}else{
        		    cm.sendOk(""+ringnum+"�Բ�������û����������Ҫ�Ĳ��ϣ�����Ŷ��\r\n\r\n�������Ҫ�����Ѽ�"+itemList[itemIndex][1]+"��#v"+itemList[itemIndex][0]+"#\r\n�ڴ����ĺ���Ϣ");
					cm.dispose();
					return;
        		}
        	}else if(ringnum == 20){//���ս���
        		if(cm.haveItem(itemList[itemIndex][0],itemList[itemIndex][1])){
        			cm.gainItem(itemList[itemIndex][0],-itemList[itemIndex][1]);
        			cm.getPlayer().gainMeso(1000000, true);
        			cm.gainItem(4000463,30);//��������
					cm.gainItem(2340000,2);//ף����
					//cm.gainItem(3600001,200);//������
					cm.gainItem(4031217,1);//�ƽ�Կ��
					cm.gainItem(4001126,500);//��Ҷ
					cm.setmoneyb(60);//����Ԫ��
					cm.gainItem(4310038, 5);//boss�ٻ���
					//cm.gainItem(2049100, 5);//ѫ���� �ͼ�
					cm.gainItem(4000038, 2);//��
        			//cm.gainItem(4000463,30);//�н��
					//cm.gainItem(2022529,1);//��������� +100%˫����ҵ���
					//cm.gainItem(2022530,1);//ӭ�������� +100%˫������
					//cm.gainItem(2022112,5);//СħŮ
					cm.setBossLog("paoshang");
        			cm.sendNext("��ϲ�������������̣��������");
        		}else{
        			cm.sendOk(""+ringnum+"�Բ�������û����������Ҫ�Ĳ��ϣ�����Ŷ��\r\n\r\n�������Ҫ�����Ѽ�"+itemList[itemIndex][1]+"��#v"+itemList[itemIndex][0]+"#\r\n�ڴ����ĺ���Ϣ");
					cm.dispose();
					return;
        		}
        	}else{
        		var ran = Math.floor(Math.random() * itemList.length);
        		var itmeid = itemList[ran][0];
        		var itemnum = itemList[ran][1];
        		if(itemIndex == -1){
        			setOneTimeLog(ran,id);
        		}else{
        			changeOneTimeLog(ran,id);
        		}
        		var strlen1 = "����ǰ�ܻ�����Ϊ�� "+(ringnum+1)+"\r\n\r\n";
				strlen1 += "���Ѿ��ɹ�����ȡ�˱����ܻ���";
	        	strlen1 += " �������Ҫ�����Ѽ�"+itemnum +"��#v"+itmeid+"#\r\n�ڴ����ĺ���Ϣ";
        		cm.setBossLog("paoshang");
        		cm.sendOk(strlen1);
        		cm.dispose();
        	}
        }else if(status == 2){
        	if(ringnum < 20){
	        	var ran = Math.floor(Math.random() * itemList.length);
	        	var itemid = itemList[ran][0];
	        	var itemnum = itemList[ran][1];
	        	if(itemIndex == -1){
	        		setOneTimeLog(ran,id);
	        	}else{
	        		changeOneTimeLog(ran,id);
	        	}
	        	var strlen = "����ǰ�ܻ�����Ϊ�� "+ (ringnum+1) +"\r\n\r\n";
	        	strlen += " �������Ҫ�����Ѽ�"+itemnum +"��#v"+itemid +"#\r\n�ڴ����ĺ���Ϣ";;
	        	cm.setBossLog("paoshang");
	        	cm.sendOk(strlen);
	        	cm.dispose();
	        }else{
	        	cm.sendOk("���Ѿ���������̣�");
	        	cm.dispose();
	        }
	    }
    }          
}
function getBossLog(boss,id) {
        var con = DatabaseConnection.getConnection();
        var count = 0;
        var ps;
        //ps = con.prepareStatement("SELECT COUNT(*) FROM bosslog WHERE characterid = ? AND bossid = ? AND lastattempt >= subtime(CURRENT_TIMESTAMP, '23:0:0.0')");
		var day = ""+year+"-"+month+"-"+days+"";
		ps = con.prepareStatement("SELECT COUNT(*) FROM bosslog WHERE characterid = ? AND bossid = ?");// AND lastattempt >= ?
        ps.setInt(1, id);
        ps.setString(2, boss);
		//ps.setString(3,day);
        var rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        } else {
            count = -1;
        }
        rs.close();
        ps.close();
        return count;
}

function setOneTimeLog(bossid,id) {
    var con1 = DatabaseConnection.getConnection();
    var ps = con1.prepareStatement("insert into onetimelog (characterid, log) values (?,?)");
    ps.setInt(1, id);
    ps.setString(2, bossid);
    ps.executeUpdate();
    ps.close();
}
function changeOneTimeLog(bossid,id) {
    var con1 = DatabaseConnection.getConnection();
    var ps = con1.prepareStatement("update onetimelog set log = ? where characterid = ?");
    ps.setString(1, bossid);
    ps.setInt(2, id);
    ps.executeUpdate();
    ps.close();
}

function getOneTimeLog(id) {
        var con = DatabaseConnection.getConnection();
        var count = 0;
        var ps;
        ps = con.prepareStatement("SELECT log FROM onetimelog WHERE characterid = ?");
        ps.setInt(1, id);
        var rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getString("log");
        } else {
            count = -1;
        }
        rs.close();
        ps.close();
        return count;
}

