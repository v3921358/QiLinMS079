/*
	Mu Lung Training Center entrance
*/
var status = -1;
var sel;

//function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	cm.sendNext("#b(懦夫!不敢來跟我PK....)");
	cm.dispose();
	return;
    }

    if (status == 0) {
	cm.sendSimple("#e< 公告 >#n \r\n凡是有勇氣挑戰武陵道場者，歡迎你前來武陵道場！. \r\n - 武公 - \r\n#b#L0#我要挑戰武陵道場50樓!!#l\r\n#b#L1#仔細閱讀規則...#l")
    } else if (status == 1) {
	sel = selection;
	if (sel == 1) {
	    cm.sendNext("#e<公告: 發行挑戰! >#n\r\n我是武陵道場的主人名叫武公。很久以前我是在武陵山開始修練仙術，現在我的內功已達到快超越極限的階段。以前武陵道場的主人懦弱到不像樣的程度。所以今天開始以我接管武陵道場。只有強者可以擁有武陵道場的資格。想要得到武術指點的人儘管來挑戰！或著想要挑戰我的人也無妨。我會讓你知道你的無知！！");
	} else {
	    cm.sendYesNo("#b(你真的想要參加武公挑戰塔嗎？？)");
	}
    } else if (status == 2) {
	if (sel == 1) {
	    cm.sendNextPrev("歡迎你來挑戰。如果沒有勇氣的話，找其他夥伴一起也無妨。");
	} else {
	    cm.saveLocation("MULUNG_TC");
	    cm.warp(925020000, 0);
	    cm.dispose();
	}
    } else if (status == 3) {
	cm.dispose();
    }
}