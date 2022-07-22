/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：罗密欧与朱丽叶
 */


function action(mode, type, selection) {
    var em = cm.getEventManager("Romeo");
    if (em == null) {
        cm.sendOk("找不到脚本，请联系GM！");
        cm.对话结束();
        return;
    }
    switch (cm.getPlayer().getMapId()) {
        case 261000011:
            cm.对话结束();
            cm.openNpc(2112004, 1);
            break;
        case 926100000:
            cm.sendOk("你应该尝试在这里调查各地。看看库中的文件，直到你可以找到入口实验室.");
            cm.对话结束();
            break;
        case 926100001:
            cm.sendOk("请消除所有的怪物。");
            cm.对话结束();
            break;
        case 926100100:
            cm.sendOk("请把烧杯里的溢体装满。");
            cm.对话结束();
            break;
        case 926100200:
            if (cm.haveItem(4001130, 1)) {
                cm.sendOk("哦，我的信找到了，谢谢！");
                cm.gainItem(4001130, -1);
                em.setProperty("stage", "1");
            } else if (cm.haveItem(4001134, 1)) {
                cm.gainItem(4001134, -1);
                cm.sendOk("谢谢你，现在帮我找#t4001135#.");
                em.setProperty("stage4", "1");
            } else if (cm.haveItem(4001135, 1)) {
                cm.gainItem(4001135, -1);
                cm.sendOk("谢谢你，已经过关了。.");
                em.setProperty("stage4", "2");
                cm.getMap().getReactorByName("rnj3_out3").hitReactor(cm.getClient());
            } else {
                cm.sendOk("现在我们必须停止冲突，请帮我找出#t4001134# 和 #t4001135#。");
            }
            cm.对话结束();
            break;
        case 926100300:
            cm.sendOk("我们一定要到实验室的顶部.");
            cm.对话结束();
            break;
        case 926100400:
            cm.sendOk("当你准备好了，我们要快去救救我的爱人.");
            cm.对话结束();
            break;
        case 926100401:
            cm.warpParty(926100500); //urete
            cm.对话结束();
            break;
    }

}
