/*      

 NPC类型:	综合NPC

 */
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 正方形 = "#fUI/UIWindow/Quest/icon3/6#";
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 正在进行中 = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var 完成 = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var 正在进行中蓝 = "#fUI/UIWindow/MonsterCarnival/icon1#";
var 完成红 = "#fUI/UIWindow/MonsterCarnival/icon0#";
var 礼包物品 = "#v1302000#";
var x1 = "1302000,+1";// 物品ID,数量
var x2;
var x3;
var x4;
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 圆形 = "#fUI/UIWindow/Quest/icon3/6#";
var 美化new = "#fUI/UIWindow/Quest/icon2/7#";
var 美化ne = "#fUI/UIWindow/Quest/icon6/7#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";

var status = 0;
var typede = 0;
var slot = Array();
var dsa = "";

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        if (status == 0) {
            var zyms = "";
            zyms = "#b#h0# #r一旦物品删除就无法找回哦.请慎重!!!\r\n";
			//zyms += "" + epp + "" + epp + "\r\n";
            zyms += " #L1##b"+猫右+"删除装备栏道具"+猫右+"#k#l  #L2##b"+猫右+"删除消耗栏道具"+猫右+"#k#l\r\n\r\n";
            zyms += " #L3##b"+猫右+"删除设置栏道具"+猫右+"#k#l  #L4##b"+猫右+"删除其他栏道具"+猫右+"#k#l\r\n\r\n";
            zyms += " #L5##b"+猫右+"删除特殊栏道具"+猫右+"#k#l  #L6##b"+猫右+"一键删所有道具"+猫右+"#k#l\r\n\r\n";
			//zyms += "" + epp + "" + epp + "\r\n";
            cm.sendSimple(zyms);



        } else if (selection == 1) { //删除装备栏道具
            dsd = 100;
            var avail = "";
            for (var i = 0; i < 96; i++) {
                if (cm.getInventory(1).getItem(i) != null) {
                    avail += "#L" + cm.getInventory(1).getItem(i).getItemId() + "# #z" + cm.getInventory(1).getItem(i).getItemId() + "# #i" + cm.getInventory(1).getItem(i).getItemId() + ":##l\r\n";
                }
                slot.push(i);
            }
            cm.sendSimple(dsa + "#b请选择需要删除的道具:\r\n#b" + avail);

        } else if (selection == 2) { //删除消耗栏道具
            dsd = 400;
            var avail = "";
            for (var i = 0; i < 96; i++) {
                if (cm.getInventory(2).getItem(i) != null) {
                    avail += "#L" + cm.getInventory(2).getItem(i).getItemId() + "# #z" + cm.getInventory(2).getItem(i).getItemId() + "# #i" + cm.getInventory(2).getItem(i).getItemId() + ":##l\r\n";
                }
                slot.push(i);
            }
            cm.sendSimple(dsa + "#b请选择需要删除的道具:\r\n#b" + avail);

        } else if (selection == 3) { //删除其他栏道具
            dsd = 400;
            var avail = "";
            for (var i = 0; i < 96; i++) {
                if (cm.getInventory(3).getItem(i) != null) {
                    avail += "#L" + cm.getInventory(3).getItem(i).getItemId() + "# #z" + cm.getInventory(3).getItem(i).getItemId() + "# #i" + cm.getInventory(3).getItem(i).getItemId() + ":##l\r\n";
                }
                slot.push(i);
            }
            cm.sendSimple(dsa + "#b请选择需要删除的道具:\r\n#b" + avail);

        } else if (selection == 4) { //删除设置栏道具
            dsd = 400;
            var avail = "";
            for (var i = 0; i < 96; i++) {
                if (cm.getInventory(4).getItem(i) != null) {
                    avail += "#L" + cm.getInventory(4).getItem(i).getItemId() + "# #z" + cm.getInventory(4).getItem(i).getItemId() + "# #i" + cm.getInventory(4).getItem(i).getItemId() + ":##l\r\n";
                }
                slot.push(i);
            }
            cm.sendSimple(dsa + "#b请选择需要删除的道具:\r\n#b" + avail);

        } else if (selection == 5) { //删除特殊栏道具
            dsd = 400;
            var avail = "";
            for (var i = 0; i < 96; i++) {
                if (cm.getInventory(5).getItem(i) != null) {
                    avail += "#L" + cm.getInventory(5).getItem(i).getItemId() + "# #z" + cm.getInventory(5).getItem(i).getItemId() + "# #i" + cm.getInventory(5).getItem(i).getItemId() + ":##l\r\n";
                }
                slot.push(i);
            }
            cm.sendSimple(dsa + "#b请选择需要删除的道具:\r\n#b" + avail);

		} else if (selection == 6) { 
			cm.dispose();
			cm.openNpc(9330079,"一键删除道具");
        } else if (status == 2) {//删除道具 
            itemss = selection;
            if (dsd == 100) {
                var shul = cm.getPlayer().getItemQuantity(itemss);
                if (cm.getMeso() < 0) {
                    cm.sendOk("你没有足够的金币,删除道具需要收取手续费50万金币。");
                } else {
                    //cm.gainMeso(-500000);
                    cm.gainItem(itemss, -shul);
                    cm.sendOk(dsa + "我已经将您背包里的 #i" + itemss + ":#从你的背包清理！");
                }
              status = -1;
            }
            
        
            
            if (dsd == 400) {
                var shul = cm.getPlayer().getItemQuantity(itemss);
                if (cm.getMeso() < 0) {
                    cm.sendOk("你没有足够的金币,删除道具需要收取手续费50万金币。");
                } else {
                    //cm.gainMeso(-500000);
                    cm.gainItem(itemss, -shul);
                    cm.sendOk(dsa + "我已经将您背包里的 #i" + itemss + ":#从你的背包清理！");
                }
            }
            status = -1;
        } else {
            cm.dispose();


        }
    }
}

