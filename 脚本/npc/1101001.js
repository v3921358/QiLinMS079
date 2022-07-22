 /* 
	NPC Name: 		Divine Bird
	Map(s): 		Erev
	Description: 		Buff
*/

function start() {
    cm.useItem(2022458);
    cm.sendOk("不要停止訓練，這個世界需要你來守護。");
}

function action(mode, type, selection) {
    cm.dispose();
}