var letters = Array("Lirin最喜欢的动物是什么?”、“雪人金字塔主要颜色的面积是多少?”,“请把我的信件(驯鹿)”、“情人节玫瑰是48级?”,“要多少经验水平1 - 2 ?”、“交流点投票调频谁?”、“谁是这个服务器的所有者(提示:_ _ _ _ _ _)吗?”,“初学者成为魔术师什么水平?”、“埃文开始的什么城市?”、“黑色的翅膀是什么镇?”、“什么是野生猎人,战斗法师,和力学?”、“什么类型的龙是米尔?”、“米尔的祖先是谁?”、“阿然使用什么武器?”、“技工的工作老师是谁?”、“第三工作进步所需的物品是什么?”、“口袋妖怪起动器人大给了你什么?”、“野生动物的猎人骑什么?”、“完成这个工作类:骑士?”、“潜力的队伍是:少见,史诗,吗?”,“什么是最高的雕像粉红色Bean的名字吗?”、“最接近树蜂科动物是什么镇?”、“你能去Zakum什么水平?");
var answers = Array("WOLF", "YELLOW", "REINDEER", "BLUE", "FIFTEEN", "PHOENIX", "AWESOME", "EIGHT", "HENESYS", "EDELSTEIN", "RESISTANCE", "ONYX", "AFRIEN", "POLEARM", "CHECKY", "DARKCRYSTAL", "GAGA", "JAGUAR", "CYGNUS", "UNIQUE", "ARIEL", "LEAFRE", "FIFTY");

function init() {
    em.setProperty("state", "0");
}

function monsterValue(eim, mobId) {
    return 1;
}

function setup() {
    em.setProperty("state", "1");

    var eim = em.newInstance("English0");
    eim.setInstanceMap(702090301).resetFully();
    eim.setInstanceMap(702090302).resetFully();
    eim.setInstanceMap(702090303).resetFully();
    var ee = java.lang.Math.floor(java.lang.Math.random() * letters.length);
    eim.setProperty("question", letters[ee]);
    eim.setProperty("answer", answers[ee]);
    eim.startEventTimer(300000); //5 mins lol

    return eim;
}

function playerEntry(eim, player) {
    var map = eim.getMapInstance(0);
    player.changeMap(map, map.getPortal(0));
    player.sendEnglishQuiz(eim.getProperty("question"));
}

function playerDead(eim, player) {
}

function changedMap(eim, player, mapid) {
    switch (mapid) {
	case 702090301: // 1st Stage
	case 702090302: // 2nd Stage
	case 702090303: // 3rd Stage
	    return; // Everything is fine
    }
    eim.unregisterPlayer(player);

    if (eim.disposeIfPlayerBelow(2, 702090400)) {
	em.setProperty("state", "0");
    }
}

function playerRevive(eim, player) {
}

function playerDisconnected(eim, player) {
    return -2;
}

function leftParty(eim, player) {			
    // If only 2 players are left, uncompletable
    if (eim.disposeIfPlayerBelow(2, 702090400)) {
	em.setProperty("state", "0");
    } else {
	playerExit(eim, player);
    }
}

function disbandParty(eim) {
    // Boot whole party and end
    eim.disposeIfPlayerBelow(100, 702090400);

    em.setProperty("state", "0");
}


function scheduledTimeout(eim) {
    clearPQ(eim);
}

function playerExit(eim, player) {
    eim.unregisterPlayer(player);

    var exit = eim.getMapFactory().getMap(702090400);
    player.changeMap(exit, exit.getPortal(0));
    if (eim.disposeIfPlayerBelow(2, 702090400)) {
	em.setProperty("state", "0");
    }
}

function clearPQ(eim) {
    // KPQ does nothing special with winners
    eim.disposeIfPlayerBelow(100, 702090400);

    em.setProperty("state", "0");
}

function allMonstersDead(eim) {
}

function cancelSchedule() {
}