/* Dawnveil
    To Victoria Island
	Puro
    Made by Daenerys
*/
function start() {
    cm.sendYesNo("�ҿ���ֱ������ȥ��𽹬������ȥ��");
}

function action(mode, type, selection) {
    if (mode == 0) {
	cm.sendNext("��... �������㲢û��#b10000#k��ң������ҿɰﲻ���㡣");
	} else {
    if(cm.getPlayer().getMeso() >= 10000) {
	cm.gainMeso(-10000);
	cm.saveLocation("MULUNG_TC");
	cm.warp(700000000,0);
    }
    cm.dispose();
}
}