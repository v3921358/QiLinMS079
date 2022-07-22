/*
 * Cygnus 2nd Job advancement
 */

var status = -1;

function start(mode, type, selection) {
    qm.sendNext("你確定你已經準備好第二職業了？");
    qm.forceStartQuest();
    qm.dispose();
}

function end(mode, type, selection) {
    qm.dispose();
}