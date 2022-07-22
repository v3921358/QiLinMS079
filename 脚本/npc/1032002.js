var status = 0;
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
            var txt = "";
			
            txt = "#e#r我是每日跑商第3环NPC哦！\r\n\r\n提示：需要完成第二环跑商任务才显示第三环任务\r\n\r\n";
			txt += "#L2##k稀有道具制作#l\r\n\r\n";

            if (cm.getPlayer().getBossLog("跑商任务") == 2){// cm.getPS()  的意思是 读取跑商值如果等于1 就得出他跑商已经完成了第一环 就运行他进行第二环跑商!
				txt += "\r\n\t\t#d跑商第三环任务\r\n";
                txt += "#L1##b收集100个树枝#v4000003#交给我！！#l";
				
                cm.sendSimple(txt);
            }else{
                txt += "\r\n#b你已经完成过了然后你去找.勇士部落-仓库老板-王先生!\r\n请第二天再来！";
                cm.sendOk(txt);
                cm.dispose();
            }

        } else if (selection == 1) {
            if (cm.haveItem(4000003,100)){
                cm.setBossLog("跑商任务");
//cm.gainPS(1);//cm.gainPS(1);  的意思是 你完成跑商第一环的时候给予你 跑商值+1这样你就无法在重复做第二环了。只有凌晨12点刷新才行！
		
                cm.gainItem(4000003, -100);
                //cm.gainNX(500);
				cm.gainExp(+70000);
				cm.gainMeso(+70000);
                cm.sendOk("跑商第3环完成!恭喜获得金币=70000、经验=70000\r\n\r\n然后你去找.勇士部落-仓库老板-王先生.进行下一环！");
				//cm.worldMessage(5,"【跑商任务】【"+cm.getName()+"】跑商第3环完成!恭喜获得金币=70000、经验=100000");
				//cm.喇叭(2,"[跑商任务]：玩家" + cm.getPlayer().getName() + "第三环完成!恭喜获得金币=7W、经验=7W");
                cm.dispose();
            }else{
                cm.sendOk("收集100个树枝#v4000003#交给我!");
                cm.dispose();
            }
		
       // }
		
		} else if (selection == 2) {
				cm.dispose();
				cm.openNpc(1032002,1);
                
            }
		
		
    }
}
