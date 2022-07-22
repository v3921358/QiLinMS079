/*
 *  Scarf Snowman - Happy Ville NPC
 */


function start() {
    cm.sendYesNo("這裡的風景這麼美 你真的要回去嗎?");
}

function action(mode, type, selection) {
    if (mode == 1) {
	cm.warp(209000000);
    } else {
	cm.sendNext("你需要更多的時間裝飾樹, 阿? 如果你想要離開這個地方，隨時都可以來跟我說話?");
    }
    cm.dispose();
}