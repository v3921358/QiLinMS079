/**
 * @触发条件：开拍卖功能
 * @每日签到：领取物品 npc
 * @npcName：天启岛运营员
 * @npcID：   9900004
 **/
var status = 0;
var 黑水晶 = 4021008;
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 圆形 = "#fUI/UIWindow/Quest/icon3/6#";
var 美化new = "#fUI/UIWindow/Quest/icon5/1#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 忠告 = "#k温馨提示：任何非法程序和外挂封号处理.封杀侥幸心理.";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            var txt1 = "#L1#" + 蓝色箭头 + "50个#d#v4000026# 兑换1个#v4001266#" + 美化new + "\r\n\r\n";
            var txt2 = "#L2#" + 蓝色箭头 + "50个#d#v4000034# 兑换1个#v4001266#" + 美化new + "\r\n\r\n";
            var txt3 = "#L3#" + 蓝色箭头 + "50个#d#v4000030# 兑换1个#v4001266#" + 美化new + "\r\n\r\n";
            var txt4 = "#L4#" + 蓝色箭头 + "50个#d#v4000283# 兑换1个#v4001266#" + 美化new + "\r\n\r\n";
            var txt5 = "#L5#" + 蓝色箭头 + "50个#d#v4000157# 兑换1个#v4001266#" + 美化new + "\r\n\r\n";
            var txt6 = "#L6#" + 蓝色箭头 + "50个#d#v4000079# 兑换1个#v4001266#" + 美化new + "\r\n\r\n";
            var txt7 = "#L7#" + 蓝色箭头 + "50个#d#v4000239# 兑换1个#v4001266#" + 美化new + "\r\n\r\n";
            var txt8 = "#L8#" + 蓝色箭头 + "50个#d#v4000242# 兑换1个#v4001266#" + 美化new + "\r\n\r\n";
            var txt9 = "#L9#" + 蓝色箭头 + "50个#d#v4000180# 兑换1个#v4001266#" + 美化new + "\r\n\r\n";
            var txt10 = "#L10#" + 蓝色箭头 + "50个#d#v4000273# 兑换1个#v4001266#" + 美化new + "\r\n\r\n";
            cm.sendSimple("欢迎来到【 #r" + cm.getServerName() + "#k 】#k。\r\n\r\n" + txt1 + "" + txt2 + "" + txt3 + "" + txt4 + "" + txt5 + "" + txt6 + "" + txt7 + "" + txt8 + "" + txt9 + "" + txt10 + "");

        } else if (status == 1) {
            if (selection == 1) { //更多功能
			if(cm.itemQuantity(4000026)>=50){
			cm.gainItem(4000026,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("兑换成功！");
                   cm.dispose();
			}else{
			cm.sendOk("你没有50个#v4000026#无法兑换！");
			cm.dispose();
			}
            } else if (selection == 2) { //更多功能
			if(cm.itemQuantity(4000034)>=50){
			cm.gainItem(4000034,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("兑换成功！");
                   cm.dispose();
			}else{
			cm.sendOk("你没有50个#v4000034#无法兑换！");
			cm.dispose();
			}
            } else if (selection == 3) { //更多功能
			if(cm.itemQuantity(4000030)>=50){
			cm.gainItem(4000030,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("兑换成功！");
                   cm.dispose();
			}else{
			cm.sendOk("你没有50个#v4000030#无法兑换！");
			cm.dispose();
			}
			} else if (selection == 4) { //更多功能
			if(cm.itemQuantity(4000283)>=50){
			cm.gainItem(4000283,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("兑换成功！");
                   cm.dispose();
			}else{
			cm.sendOk("你没有50个#v4000283#无法兑换！");
			cm.dispose();
			}
			} else if (selection == 5) { //更多功能
			if(cm.itemQuantity(4000157)>=50){
			cm.gainItem(4000157,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("兑换成功！");
                   cm.dispose();
			}else{
			cm.sendOk("你没有50个#v4000157#无法兑换！");
			cm.dispose();
			}
			} else if (selection == 6) { //更多功能
			if(cm.itemQuantity(4000079)>=50){
			cm.gainItem(4000079,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("兑换成功！");
                   cm.dispose();
			}else{
			cm.sendOk("你没有50个#v4000079#无法兑换！");
			cm.dispose();
			}
			} else if (selection == 7) { //更多功能
			if(cm.itemQuantity(4000239)>=50){
			cm.gainItem(4000239,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("兑换成功！");
                   cm.dispose();
			}else{
			cm.sendOk("你没有50个#v4000239#无法兑换！");
			cm.dispose();
			}
			} else if (selection == 8) { //更多功能
			if(cm.itemQuantity(4000242)>=50){
			cm.gainItem(4000242,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("兑换成功！");
                   cm.dispose();
			}else{
			cm.sendOk("你没有50个#v4000242#无法兑换！");
			cm.dispose();
			}
			} else if (selection == 9) { //更多功能
			if(cm.itemQuantity(4000180)>=50){
			cm.gainItem(4000180,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("兑换成功！");
                   cm.dispose();
			}else{
			cm.sendOk("你没有50个#v4000180#无法兑换！");
			cm.dispose();
			}
			} else if (selection == 10) { //更多功能
			if(cm.itemQuantity(4000273)>=50){
			cm.gainItem(4000273,-50);
			cm.gainItem(4001266,+1);
			
                   cm.sendOk("兑换成功！");
                   cm.dispose();
			}else{
			cm.sendOk("你没有50个#v4000273#无法兑换！");
			cm.dispose();
			}
			
            }
        }
    }
}
