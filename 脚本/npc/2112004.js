/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�������ŷ������Ҷ
 */


function action(mode, type, selection) {
    var em = cm.getEventManager("Romeo");
    if (em == null) {
        cm.sendOk("�Ҳ����ű�������ϵGM��");
        cm.�Ի�����();
        return;
    }
    switch (cm.getPlayer().getMapId()) {
        case 261000011:
            cm.�Ի�����();
            cm.openNpc(2112004, 1);
            break;
        case 926100000:
            cm.sendOk("��Ӧ�ó��������������ء��������е��ļ���ֱ��������ҵ����ʵ����.");
            cm.�Ի�����();
            break;
        case 926100001:
            cm.sendOk("���������еĹ��");
            cm.�Ի�����();
            break;
        case 926100100:
            cm.sendOk("����ձ��������װ����");
            cm.�Ի�����();
            break;
        case 926100200:
            if (cm.haveItem(4001130, 1)) {
                cm.sendOk("Ŷ���ҵ����ҵ��ˣ�лл��");
                cm.gainItem(4001130, -1);
                em.setProperty("stage", "1");
            } else if (cm.haveItem(4001134, 1)) {
                cm.gainItem(4001134, -1);
                cm.sendOk("лл�㣬���ڰ�����#t4001135#.");
                em.setProperty("stage4", "1");
            } else if (cm.haveItem(4001135, 1)) {
                cm.gainItem(4001135, -1);
                cm.sendOk("лл�㣬�Ѿ������ˡ�.");
                em.setProperty("stage4", "2");
                cm.getMap().getReactorByName("rnj3_out3").hitReactor(cm.getClient());
            } else {
                cm.sendOk("�������Ǳ���ֹͣ��ͻ��������ҳ�#t4001134# �� #t4001135#��");
            }
            cm.�Ի�����();
            break;
        case 926100300:
            cm.sendOk("����һ��Ҫ��ʵ���ҵĶ���.");
            cm.�Ի�����();
            break;
        case 926100400:
            cm.sendOk("����׼�����ˣ�����Ҫ��ȥ�Ⱦ��ҵİ���.");
            cm.�Ի�����();
            break;
        case 926100401:
            cm.warpParty(926100500); //urete
            cm.�Ի�����();
            break;
    }

}
