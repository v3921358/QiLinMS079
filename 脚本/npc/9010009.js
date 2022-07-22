function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("感谢你的光临！");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			//显示物品ID图片用的代码是  #v这里写入ID#
            text += "#e#r你好！这里是冒险岛顺丰快递:#bPS.等级越高奖励越高.\r\n#r送货任务有好多好多环，也许你不一定能够跑完，能做多少是多少哦，每一环都有丰厚奖励.\r\n"//3
			text += "#L22#点我了解#b跑商攻略#k.\r\n\r\n"//3
			text += "#L1##e#d#v4031149# 第一轮.\r\n#b奖励; \t[经验"+cm.getLevel()*500+"]\t\t[金币"+cm.getLevel()*1000+"]\r\n\t\t[黄金枫叶*5]\t\t[抵用卷1000]\r\n\r\n"//3
	        text += "#L3##e#d#v4031149# 第二轮.\r\n#b奖励; \t[经验"+cm.getLevel()*1000+"]\t\t[金币"+cm.getLevel()*2000+"]\r\n\t\t[黄金枫叶*10]\t\t[抵用卷1500]\r\n\r\n"//3
			text += "#L4##e#d#v4031149# 第三轮.\r\n#b奖励; \t[经验"+cm.getLevel()*1500+"]\t\t[金币"+cm.getLevel()*3000+"]\r\n\t\t[黄金枫叶*15]\t\t[抵用卷2000]\r\n\r\n"//3
			text += "#L5##e#d#v4031149# 第四轮.\r\n#b奖励; \t[经验"+cm.getLevel()*2000+"]\t\t[金币"+cm.getLevel()*4000+"]\r\n\t\t[黄金枫叶*20]\t\t[抵用卷2500]\r\n\r\n"//3
			text += "#L6##e#d#v4031149# 第五轮.\r\n#b奖励; \t[经验"+cm.getLevel()*2500+"]\t\t[金币"+cm.getLevel()*5000+"]\r\n\t\t[黄金枫叶*25]\t\t[抵用卷3000]\r\n\r\n"//3
			text += "#L7##e#d#v4031149# 第六轮.\r\n#b奖励; \t[经验"+cm.getLevel()*3000+"]\t\t[金币"+cm.getLevel()*6000+"]\r\n\t\t[黄金枫叶*30]\t\t[抵用卷1000]\r\n\r\n"//3
			text += "#L8##e#d#v4031149# 第七轮.\r\n#b奖励; \t[经验"+cm.getLevel()*3500+"]\t\t[金币"+cm.getLevel()*7000+"]\r\n\t\t[黄金枫叶*35]\t\t[点卷1500]\r\n\r\n"//3
			text += "#L9##e#d#v4031149# 第八轮.\r\n#b奖励; \t[经验"+cm.getLevel()*4000+"]\t\t[金币"+cm.getLevel()*8000+"]\r\n\t\t[黄金枫叶*40]\t\t[点卷2000]\r\n\r\n"//3
			text += "#L10##e#d#v4031149# 第九轮.\r\n#b奖励; \t[经验"+cm.getLevel()*4500+"]\t\t[金币"+cm.getLevel()*9000+"]\r\n\t\t[黄金枫叶*45]\t\t[点卷2500]\r\n\r\n"//3
			text += "#L11##e#d#v4031149# 第十轮. \r\n#b奖励; \t[经验"+cm.getLevel()*10000+"]\t\t[金币"+cm.getLevel()*10000+"]\r\n\t\t[黄金枫叶*50]\t\t[点卷3000]\r\n\r\n"//3
            //text += "#L2##e#d#v4031017# 副本蛋兑换固定奖励.#l\r\n\r\n"//3
            //text += "#L3##e#d#v04032226# 每日活动奖励兑换（1小时/1次.胜利即可）#l\r\n"//3
            //text += "#L4##e#d#v1382057#永恒冰轮杖制作#l\r\n"//3
            //text += "#L5##e#d#v1402046#永恒玄冥剑制作#l\r\n"//3
            //text += "#L6##e#d#v1432047#永恒显圣枪制作#l\r\n"//3
            //text += "#L7##e#d#v1442063#永恒神光戟制作#l\r\n"//3
            //text += "#L8##e#d#v1452057#永恒惊电弓制作#l\r\n"//3
            //text += "#L9##e#d#v1462050#永恒冥雷弩制作#l\r\n"//3
            //text += "#L10##e#d#v1472068#永恒大悲赋制作#l\r\n"//3
            cm.sendSimple(text);
        } else if (selection == 1) {
			cm.dispose();
		cm.openNpc(9010009, 1);
        } else if (selection == 22) {
			cm.dispose();
		cm.openNpc(9010009, 22);
        } else if (selection == 3) {
			cm.dispose();
		cm.openNpc(9010009, 5);
        } else if (selection == 4) {
			cm.dispose();
		cm.openNpc(9010009, 6);
        } else if (selection == 5) {
			cm.dispose();
		cm.openNpc(9010009, 7);
        } else if (selection == 6) {
			cm.dispose();
		cm.openNpc(9010009, 8);
        } else if (selection == 7) {
			cm.dispose();
		cm.openNpc(9010009, 9);
        } else if (selection == 8) {
			cm.dispose();
		cm.openNpc(9010009, 10);
        } else if (selection == 9) {
			cm.dispose();
		cm.openNpc(9010009, 11);
        } else if (selection == 10) {
			cm.dispose();
		cm.openNpc(9010009, 12);
		} else if (selection == 11) {
			cm.dispose();
		cm.openNpc(9010009, 13);
	}
    }
}


