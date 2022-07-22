var 皇冠白 = "#fEffect/CharacterEff/1003252/0/0#";
var 小雪花 = "#fEffect/CharacterEff/1003393/0/0#";
var 音符 = "#fEffect/CharacterEff/1032063/0/0#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var 中条蓝 ="#fUI/ChatBalloon/tutorial/w#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
function start() {
    status = -1;
    action(1, 0, 0);
}
var 冒险币 = 5000;
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {
            //cm.sendOk("感谢你的光临！");
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
			text = "                    #k"+皇冠白+" #r#e#w小小冒险岛#n#k "+皇冠白+"\r\n\r\n";//#n#k豆豆点：#r" + cm.getBeans() + "#k点\t\t//
			text += "   "+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n";
			text += "\t   #e#d这里有最个性的时装,还附有超强的属性\r\n\t  萌新也可以制作哦~#n\r\n\r\n"
			text += "\t  #d#L0##r我是女神#l\t\t\t#L1##b我是男神#n#l\t\r\n\r\n"
			text += "\t  #L3##d透明帽子#n#l\t\t\t#L4##d时装属性转换#l\r\n"
            cm.sendSimple(text);//\r\n\r\n#b#L2##r精品上衣
        } else if (status == 1) {
            if (selection == 0) {//副本传送
				cm.dispose();
				cm.openNpc(9310031,1);
            } else if (selection == 1) {//副本兑换奖励
				cm.dispose();
				cm.openNpc(9310031,2);
            }else if(selection == 2){
				cm.dispose();
                cm.openNpc(9310031,3);
			}else if(selection == 3){
				cm.dispose();
                cm.openNpc(9310031,4);
			}else if(selection == 4){
				cm.dispose();
                cm.openNpc(9310031,5);
			}
        }
    }
}