var weapon = [
//����
1003946,
1052647,
1072853,
1082540,
1102612,
//1113288,//������װ��ָ
//1122262,//������׹
1132242,

//����
1302289,//������
1312165,//����ս��
1402210,//����˫�ֽ�
1412147,//����˫��ս��
1432178,//����֮ì
1442234,//����ǹ

1332238,//�����и���
1472226,//����ȭ��

1372188,//��������
1382121,//��������

1452216,//������
1462093,//������

1482179,//������ȭ
1492190//������ܿ�

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
		Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "���ϳ����ġ�" + " : " + "[" + cm.getChar().getName() + "]�ɹ��ϳ��˸���װ������")); 
        cm.sendNext("#b�Ѿ��һ����ˣ���ǰ�������鿴 #i" + weapon[sels] + "#");
        cm.dispose();
    } else {
        //cm.sendNext("#r��������: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}