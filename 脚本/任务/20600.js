/*
 * Cygnus Skill - Training Never ends
 */

var status = -1;

function start(mode, type, selection) {
    status++;

    if (status == 0) {
	qm.askAcceptDecline("#h0#. 你有沒有在訓練懈怠，因為達到100級？我們都知道你是多麼強大，但訓練是不完整的。一起來看看這些騎士指揮官。他們訓練了一天一夜，準備為自己的黑精靈可能遇到的問題。");
    } else {
	if (mode == 1) {
	    qm.forceStartQuest();
	}
	qm.dispose();
    }
}

function end(mode, type, selection) {
    qm.dispose();
}