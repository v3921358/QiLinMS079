var weapon = [

//����
	1003529,//ñ��
	1052457,//�·�
	1072660,//Ь��
	1082430,//����
	1102394,//����
	1132151,//����
	//1152088,
	

//����
	1302212,//�Ͻ��Ҷ��
	1312114,//�Ͻ��Ҷ��
	1402145,//�Ͻ��Ҷ˫�ֽ�
	1412102,//�Ͻ��Ҷ˫��ս��
	1432135,//�Ͻ��Ҷ֮ǹ
	1442173,//�Ͻ��Ҷì
	1332186,//�Ͻ��Ҷ������
	1472177,//�Ͻ��Ҷȭ��
	1372131,//�Ͻ��Ҷ��������
	1382160,//�Ͻ��Ҷ��������
	1452165,//�Ͻ��Ҷ��
	1462156,//�Ͻ��Ҷ��
	1482138,//�Ͻ��Ҷ��ȭ
	1492138 //�Ͻ��Ҷ��ܿ�

];
var req = [
    //[4000402, 200],//����
    //[4000406, 200],//����
	//[4021008, 50],//��ˮ��
	//[4021006, 50],//�ƾ�
	//[4021009, 20],//��ʯ
	[4001126, 500]//��Ҷ
	//[4000313, 200],//�ƽ��Ҷ
	//[4001083, 1],//����
	//[4001084, 1]//����
	//[4000463, 100],//�����
	//[4000487, 100],//��Ӱ��
	
	
    
	
	
    
];
var sels;
var status = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }
    if (status == 0) {
        var msg = "";
        msg += "\r\n#d��Ҫ:#b ";
        msg += "\r\n\r\n";
        for (var ii = 0; ii < req.length; ii++) {
            msg += "#i" + req[ii][0] + "##z" + req[ii][0] + "#x" + req[ii][1];
            if (ii % 3 == 0) {
                msg += "\r\n";
            }
        }
        msg += "\r\n";
        msg += "#g----------------------------------------------\r\n";
        for (var i = 0; i < weapon.length; i++) {
            msg += "#r#L" + i + "#";
            msg += "#i" + weapon[i] + "##z" + weapon[i] + "##l\r\n";
        }
        cm.sendSimple("#b#e���ã�����#r����������#b��Ҫ���²��ϣ�û�в��Ͽɲ���Ŷ\r\n\r\n" + msg + "");
    } else if (status == 1) {
        sels = selection;
        if (!cm.canHold(weapon[sels])) {
            cm.sendNext("#r�����ռ䲻��");
            cm.dispose();
            return;
        }
        for (var i = 0; i < req.length; i++) {
            if (!cm.haveItem(req[i][0], req[i][1])) {
                cm.sendNext("#b������û��#r�㹻�Ĳ���#k�������ռ�����ȥ�ɣ�");
                cm.dispose();
                return;
            }
        }
        cm.sendYesNo("#b�Ƿ�Ҫ�һ�#r����������ϵ��#r #i" + weapon[sels] + "#? \r\n");
    } else if (status == 2) {
        for (var i = 0; i < req.length; i++) {
            cm.gainItem(req[i][0], -req[i][1]);
        }
        cm.gainItem(weapon[sels], 1);
		Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���ϳ����ġ�" + " : " + "[" + cm.getChar().getName() + "]�ɹ��ϳ����Ͻ�װ������")); 
        cm.sendNext("#b�Ѿ��һ����ˣ���ǰ�������鿴 #i" + weapon[sels] + "#");
        cm.dispose();
    } else {
        //cm.sendNext("#r��������: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}