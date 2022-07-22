/*
	Konpei - Showa Town(801000000)
*/

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0) {
	cm.dispose();
    } else {
	if (mode == 1)
	    status++;
	if (status == 0) {
	    cm.sendSimple ("有什麼我可以為你服務的嗎？？\r #L0##b告訴我藏身之處的一些消息。#l\r\n#L1#帶我去的藏身之處。#l\r\n#L2#沒有。#l#k");
	} else if (status == 1) {
	    if (selection == 0) {
		cm.sendNext("我懶得說明。");
	    } if (selection == 1) {
		cm.sendNext("準備走了！");
	    } if (selection == 2) {
		cm.sendOk("我是一個忙碌的人！離我遠一點！");
	    } if(selection != 1) {
		cm.dispose();
	    }
	} else if (status == 2) {
	    cm.warp(801040000, 0);
	    cm.dispose();
	}
    }
}