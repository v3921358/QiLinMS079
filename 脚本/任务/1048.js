var status = -1;

function start(mode, type, selection) {
    if (mode == -1) {
        qm.dispose();
    } else {
        if (mode == 1) {
            status++;
        } else {
            status--;
        }
        if (status == 0) {
            qm.sendOk("是位新的旅行者吧？還很陌生吧？我是冒險島運營員，好好聽著，人物的各項屬性都關係著以後的冒險經歷生存，所以正確的選擇職業，是很重要的。如果你還不知道應該選擇什麼職業。你可以到明珠港找#b坤#k談談，也許他會告訴你一些你想要知道的。");
            qm.forceCompleteQuest();
            qm.dispose();
        }
    }
}