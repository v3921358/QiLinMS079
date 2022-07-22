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
            txt = "我是每日跑商第5环NPC哦！\r\n\r\n";

            if (cm.getPlayer().getBossLog("跑商任务") == 4){// cm.getPS()  的意思是 读取跑商值如果等于1 就得出他跑商已经完成了第一环 就运行他进行第二环跑商!

                txt += "#L1##b收集100个鳄鱼皮#v4000032#交给我！！#l";
                cm.sendSimple(txt);
            }else{
                txt += "你已经完成过了然后你去找.林中之城-仓库管理员-吴先生!\r\n请第二天再来！";
                cm.sendOk(txt);
                cm.dispose();
            }

        } else if (selection == 1) {
            if (cm.haveItem(4000032,100)){
                cm.setBossLog("跑商任务");
//cm.gainPS(1);//cm.gainPS(1);  的意思是 你完成跑商第一环的时候给予你 跑商值+1这样你就无法在重复做第二环了。只有凌晨12点刷新才行！
		
                cm.gainItem(4000032, -100);
                cm.gainItem(4002000, 5);
                //cm.gainNX(500);
				cm.gainExp(+120000);
				cm.gainMeso(+200000);
                cm.sendOk("跑商第5环完成!恭喜获得金币=2000000、经验=120000\r\n\r\n然后你去找..林中之城-仓库管理员-吴先生.进行下一环！");
				//cm.worldMessage(5,"【跑商任务】【"+cm.getName()+"】跑商第5环完成!恭喜获得金币=2000000、经验=150000");
				//cm.喇叭(2,"[跑商任务]：玩家" + cm.getPlayer().getName() + "第五环完成!恭喜获得金币=10W、经验=12W 蜗牛邮票5张");
                cm.dispose();
            }else{
                cm.sendOk("收集100个鳄鱼皮#v4000032#交给我!");
                cm.dispose();
            }
        }
    }
}
