var status = -1;

function action(mode, type, selection) {
    if (mode == 1)
        status++;
    else
        status--;
    if (status == 0) {
		if (cm.getQuestStatus(21612) == 1 || cm.getQuestStatus(21614) == 1) {
			cm.sendNext("你是來找我們老大的是吧...");
		} else {
			cm.sendOk("是人類嗎？？沒事的話趕緊離開這裡吧！");
			cm.dispose();
		}
    } else if (status == 1) {
		cm.sendNext("那我就帶你去見我們老大吧!");
	} else if (status == 2) {
		cm.warp(140010210,0);
		cm.dispose();
	}
}