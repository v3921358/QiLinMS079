var weapon = [
//防具
1004492,//帽子
1052929,//衣服
1073057,//鞋子
1082647,//手套
1102828,//披风
1142833,//勋章
1132287,//腰带
//1152187,//
1012524,//脸饰品

//武器
1302336,
1312201,
1332277,
1342103,
1372225,
1382263,
1402253,
1412180,
1432216,
1442270,
1452255,
1462241,
1472263,
1482218,
1492233


];
var req = [
    [4000402, 200],//银心
    [4000406, 200],//金心
	//[4021008, 50],//黑水晶
	//[4021006, 50],//黄晶
	[4021009, 20],//星石
	[4001126, 1000],//枫叶
	[4000313, 200],//黄金枫叶
	//[4001083, 1],//扎昆
	[4001084, 1]//闹钟
	//[4000463, 100],//国庆币
	//[4000487, 100],//暗影币
	
	
    
	
	
    
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
        msg += "\r\n#d需要:#b ";
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
        cm.sendSimple("#b#e您好，制作#r布莱克武器#b需要以下材料，没有材料可不行哦\r\n\r\n" + msg + "");
    } else if (status == 1) {
        sels = selection;
        if (!cm.canHold(weapon[sels])) {
            cm.sendNext("#r背包空间不足");
            cm.dispose();
            return;
        }
        for (var i = 0; i < req.length; i++) {
            if (!cm.haveItem(req[i][0], req[i][1])) {
                cm.sendNext("#b你身上没有#r足够的材料#k，继续收集材料去吧！");
                cm.dispose();
                return;
            }
        }
        cm.sendYesNo("#b是否要兑换#r布莱克武器系列#r #i" + weapon[sels] + "#? \r\n");
    } else if (status == 2) {
        for (var i = 0; i < req.length; i++) {
            cm.gainItem(req[i][0], -req[i][1]);
        }
        cm.gainItem(weapon[sels], 1);
		Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『合成中心』" + " : " + "[" + cm.getChar().getName() + "]成功合成了12周年装备！！")); 
        cm.sendNext("#b已经兑换好了，请前往背包查看 #i" + weapon[sels] + "#");
        cm.dispose();
    } else {
        //cm.sendNext("#r发生错误: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}