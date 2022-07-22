var weapon = [

//防具
	1003529,//帽子
	1052457,//衣服
	1072660,//鞋子
	1082430,//手套
	1102394,//披风
	1132151,//腰带
	//1152088,
	

//武器
	1302212,//紫金枫叶剑
	1312114,//紫金枫叶斧
	1402145,//紫金枫叶双手剑
	1412102,//紫金枫叶双手战斧
	1432135,//紫金枫叶之枪
	1442173,//紫金枫叶矛
	1332186,//紫金枫叶龙牙破
	1472177,//紫金枫叶拳甲
	1372131,//紫金枫叶治愈短杖
	1382160,//紫金枫叶治愈长杖
	1452165,//紫金枫叶弓
	1462156,//紫金枫叶弩
	1482138,//紫金枫叶冲拳
	1492138 //紫金枫叶红杰克

];
var req = [
    //[4000402, 200],//银心
    //[4000406, 200],//金心
	//[4021008, 50],//黑水晶
	//[4021006, 50],//黄晶
	//[4021009, 20],//星石
	[4001126, 500]//枫叶
	//[4000313, 200],//黄金枫叶
	//[4001083, 1],//扎昆
	//[4001084, 1]//闹钟
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
		Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『合成中心』" + " : " + "[" + cm.getChar().getName() + "]成功合成了紫金装备！！")); 
        cm.sendNext("#b已经兑换好了，请前往背包查看 #i" + weapon[sels] + "#");
        cm.dispose();
    } else {
        //cm.sendNext("#r发生错误: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}