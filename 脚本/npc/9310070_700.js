/*
 * 
 * @QILIN
 * @npc翅膀进价+2级
 */
 var 奖励 = "#fUI/CashShop/CSDiscount/bonus#";
 var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
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
                
   cm.sendOk("感谢使用.");
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
	for(i = 0; i < 10; i++){
		text += "";
	}				
	text += "#r翅膀七级进价需要以下物品:#n\r\n#v1102386##d#z1102386# X 1 #v4000463##d#z4000463# X 3000\r\n        #v4000313##d#z4000313# X 5000 #v4001126##d#z4001126# X 5000\r\n        #v4000038##z4000038# X 1000\r\n         "+奖励+"\r\n#v1102798##z1102798# 全属性+50 X 1"
	text += "\r\n#e#k#L1#"+正方箭头+"确定进价七级翅膀";//永久
	//text += "     \r\n"
        cm.sendSimple(text);
        } else if (selection == 1) {
                      if(!cm.canHold(1102798,1)){
			cm.sendOk("请清理你的背包，至少空出2个位置！");
            cm.dispose();
        } else if(cm.haveItem(1102386,1) && cm.haveItem(4000463,3000) && cm.haveItem(4000313,5000) && cm.haveItem(4001126,5000) && cm.haveItem(4000038,1000)){
				cm.gainItem(1102386, -1);
				cm.gainItem(4000313, -5000);
				cm.gainItem(4000463, -3000);
				cm.gainItem(4001126, -5000);
				cm.gainItem(4000038, -1000);
            cm.给属性装备(1102798, 0, 0, 50, 50, 50, 50, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0);
            cm.sendOk("恭喜你成功进价为七级翅膀全属性+50，祝你游戏愉快。");
            cm.dispose();
cm.全服黄色喇叭("[翅膀进价] : 恭喜玩家 【"+cm.getPlayer().getName()+"】 成功进价七级翅膀全属性+50，未来的明日之星！")
 
			}else{
            cm.sendOk("进价翅膀材料或者不足#v1102386#X1#v4000463#X3000#v4000313#X5000#v4001126#X5000#v4000038#X500\r\n");
            cm.dispose();
			}
		}
    }
}




