﻿/* Author: Xterminator
	NPC Name: 		Cloy
	Map(s): 		Victoria Road : Henesys Park (100000200)
	Description: 		Pet Master
*/
var status = -1;

function action(mode, type, selection) {
    if (status == 1 && mode == 0 || status == 5 && mode == 1 || status == 10 && mode == 1 || status == 13 && mode == 1 || status == 15 && mode == 0　|| status == 0 && mode == -1) {
	cm.dispose();
	return;
	}
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
	cm.sendNext("你... 是不是将我的孩子带在身边呢？　藉由使用生命水的魔法，我成功研发出赋予玩偶生命的魔法。　而人们将我获得生命的孩子称为 #b宠物#k。　如果持有宠物的话，任何事情都可以来问我。");
	} else if (status == 1) {
        cm.sendSimple("对于什么问题感到有兴趣呢？#b\r\n#L0#请针对宠物说明。#l\r\n#L1#宠物要怎么养？#l\r\n#L2#宠物也是会死吗？#l\r\n#L3#请告诉宠物猫,黑色猫的命令语。#l\r\n#L4#请告诉宠物狗的命令语。#l\r\n#L5#请告诉粉红兔，白兔的命令语。#l\r\n#L6#请告诉小魔龙。#l\r\n#L7#请告诉麋鹿的命令语。#l\r\n#L8#请告诉黑色猪的命令语。#l\r\n#L9#请告诉熊猫的命令语。#l\r\n#L10#请告诉哈士奇的命令语。#l\r\n#L11#请告诉迪诺龙、妮诺龙的命令语。#l\r\n#L12#请告诉猴子的命令语。#l\r\n#L13#请告诉电子鸡的命令语。#l\r\n#L14#请告诉白虎的命令语。#l\r\n#L15#请告诉企鹅的密令语。#l\r\n#L16#请告诉黄金猪的命令语。#l\r\n#L17#请告诉机器人的命令语。#l\r\n#L18#请告诉迷你雪吉拉的命令语。#l\r\n#L19#请告诉巴洛谷的命令语。#l\r\n#L20#请告诉神奇宝贝的命令语。#l\r\n#L21#请告诉绿红蓝的命令语。#l\r\n#L22#请告诉黑龙的命令语。#l\r\n#L23#请告诉黑色鬼精灵的命令语。#l\r\n#L24#请告诉豪猪的命令语。#l\r\n#L25#请告诉雪宝的命令语。#l\r\n#L26#请告诉臭鼬的命令语。#l\r\n#L27#请告诉我转移宠物亲密度的方法。#l");
    } else if (status == 2) {
	if (selection == 0) {
	    status = 3;
	    cm.sendNext("想对宠物有所了解嘛。很久以前，我再做出的木偶身上用了生命水，透过魔法成功的做出了魔法动物。虽然难以相信，木偶成了有生命的生命体。它们能听懂人类的话，是很乖巧可爱的家伙。");
	} else if (selection == 1) {
	    status = 6;
	    cm.sendNext("宠物对于特别的指令会有高兴和难过等不同的反应。给宠物下指令后，听主人的话，就会提高与主人之间的亲密度。双击宠物的话就能看到亲密度，等级，饱满度等资讯。");
	} else if (selection == 2) {
	    status = 11;
	    cm.sendNext("死掉啊！其实这些小家伙并不是真正活着的，所以它们会死，我也不知道对不对啊。这些小家伙是将我的魔法力量与生命水的力量灌注在木偶身体里做出来的。当然当它们活动的时候，是与其他动物没什么两样");
	} else if (selection == 3) {
	    cm.sendNext("#r褐色小猫，黑色小猫#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b座#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 4) {
	    cm.sendNext("#r褐色小狗#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 5) {
	    cm.sendNext("#r粉红兔子，白色兔子#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b抱抱#k (等级 10 ~ 30)\r\n#b睡觉, 困了, 去睡觉#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 6) {
	    cm.sendNext("#r小魔龙#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 7) {
	    cm.sendNext("#r麋鹿#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b圣诞快乐，圣诞快乐#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 11 ~ 30)\r\n#b寂寞, 孤独#k (等级 11 ~ 30)\r\n#b撒娇#k (等级 11 ~ 30)\r\n#b走#k (等级 21 ~ 30)");
	    cm.dispose();
	} else if (selection == 8) {
	    cm.sendNext("#r黑色猪#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 9) {
	    cm.sendNext("#r熊猫#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 10) {
	    cm.sendNext("#r哈士奇#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 11) {
	    cm.sendNext("#r迪诺龙、妮诺龙#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 12) {
	    cm.sendNext("#r猴子#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 13) {
	    cm.sendNext("#r电子鸡#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 14) {
	    cm.sendNext("#r白虎#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 15) {
	    cm.sendNext("#r企鹅#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 16) {
	    cm.sendNext("#r黄金猪#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 17) {
	    cm.sendNext("#r机器人#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 18) {
	    cm.sendNext("#r迷你雪吉拉#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 19) {
	    cm.sendNext("#r巴洛谷#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 20) {
	    cm.sendNext("#r神奇宝贝#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 21) {
	    cm.sendNext("#r绿红蓝#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 22) {
	    cm.sendNext("#r黑龙#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 23) {
	    cm.sendNext("#r黑色鬼精灵#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 24) {
	    cm.sendNext("#r豪猪#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 25) {
	    cm.sendNext("#r雪宝#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 26) {
	    cm.sendNext("#r臭鼬#k的指令语旁边的等级意味着能够使用那条指令的宠物的等级。\r\n#b坐#k (等级 1 ~ 30)\r\n#b不要, 住手, 不行, 不可以#k (等级 1 ~ 30)\r\n#b笨蛋, 傻瓜, 讨厌#k (等级 1 ~ 30)\r\n#b爱你#k (等级 1~30)\r\n#b便便#k (等级 1 ~ 30)\r\n#b说, 说吧, 说话#k (等级 10 ~ 30)\r\n#b撒娇#k (等级 10 ~ 30)\r\n#b站, 站起来, 起来#k (等级 20 ~ 30)");
	    cm.dispose();
	} else if (selection == 27) {
	    status = 14;
	    cm.sendNext("为了移动宠物能力值需要魔法卷轴，带着这本书给艾灵森林的妖精玛莉的话，就可以将你真心培育的宠物等级和亲密度移动其他宠物身上去。只给对于宠物如此关心的你而已，免费给你有点困难，所以只要支付25万枫币的话，就可以把书让给你，对了，即使有咒文书，如果没有可移动的新宠物，也是没有用的。");
	}
    } else if (status == 3) {
	cm.sendNext("可是那生命水只在世界树的根部长出来一点点而已，不能赋予那些孩子太多的时间真可惜啊！不过就算变成木偶也能再赋予它生命，在一起要好好疼它哦。");
    } else if (status == 4) {
	cm.sendNextPrev("对了小家伙队特别指令会有所反应的。会闹也会学乖一切都靠你发现了。小家伙们很害怕离开主人身边，要经常疼它们。别让它们孤独哦");
    cm.dispose();
	} else if (status == 6) {
	cm.sendNext("经常和宠物聊天，关心它，亲密度就会提高，宠物的等级也会跟着提高。亲密度提高到某一程度时，宠物就会升级，等级高的话，还会学人说话，要努力抚养，当然不是那么容易吧");
    } else if (status == 7) {
	cm.sendNextPrev("虽然是木偶，可是这些家伙也有生命，也会觉得肚子饿的。#b饱满度#k是显示肚子饱的程度的，最高是100，降到一定程度的，宠物会不听话等等，变得神经质呢。要多花点心思啊。");
    } else if (status == 8) {
	cm.sendNextPrev("对了！宠物不大喜欢吃人类的食物。我的徒弟#b科尔#k在弓箭手村的市集里卖#b宠物食品#k，如果需要食物就到弓箭手村去。最好先买好食物，以防宠物失去力气哦。");
    } else if (status == 9) {
	cm.sendNext("阿对了！如果太久没喂宠物吃东西的话它会自己回家。虽然下次将它拿出来在喂它也可以，但因为对健康不好，所以每一餐都要准时喂食喔。解说能够理解吗?");
	cm.safeDispose();
	} else if (status == 11) {
	cm.sendNext("过一段时间后对了！这些家伙会停掉的。就会恢复到原本木偶的样子。魔法的力量和生命水用光的话，不过并不是永远停掉哦，再给它擦上生命水的话，就能复活哦。");
    } else if (status == 12) {
	cm.sendNextPrev("虽然能让它们恢复过来，不过停止还是让人满伤心的锁已在它们活着的时候一定要好好爱护它们啊。可要记得按时喂它们。有一个生命，一直追随你、关注你，你不觉得这是非常快乐的事情吗？");
    cm.dispose();
	} else if (status == 15) {
	cm.sendYesNo("将减去25万枫币，确定要购买吗？");
    } else if (status == 16) {
	if (cm.getMeso() < 250000) {
	    cm.sendOk("请确认是否有足够的枫币，或者其他栏是否满了。");
	} else {
	    cm.gainMeso(-250000);
	    cm.gainItem(4160011, 1);
	}
	cm.safeDispose();
	return;
    }
}