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
                
   cm.sendOk("��л��Ĺ��٣�");
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
   cm.sendSimple("#e���ã���ӭ����#rð�յ�#k�������ۻ�Ա:\r\n\r\n  #dʣ����:#b" + cm.getMeso() + "\r\n#r#L1#�ӻ�С��#l\r\n#L4#�弶����#l\r\n#L6#�弶����#l\r\n#L5#����ר��#l#k#l#l ");
    } else if (status == 1) {
           if (selection == 0) {
      cm.sendOk("#e��Ϸ�еĽ����ȫ���Կ�Ŭ���Լ�׬ȡ�������������QQȺ����");
            cm.dispose();
    }else if  (selection == 1) {
           //cm.openShop(20);
		   cm.openShop(43);
		       cm.dispose();
    }else if  (selection == 4) {
           cm.openShop(74);
		       cm.dispose();
    }else if  (selection == 6) {
           cm.openShop(63);
		       cm.dispose(); 
    }else if  (selection == 2) {
           cm.openShop(109);
		       cm.dispose(); 
    }else if  (selection == 5) {
           cm.openShop(77);
		       cm.dispose();
    }else if  (selection == 3) {
           cm.openShop(104);
		       cm.dispose();
    }else if  (selection == 7) {
           cm.openShop(110);
		       cm.dispose(); 
    }else if  (selection == 8) {
           cm.openShop(111);
		       cm.dispose();
    }else if  (selection == 9) {
           cm.openShop(93);
		       cm.dispose();
    }else if  (selection == 10) {
           cm.openShop(66);
                       cm.dispose();
    }else if  (selection == 11) {
           cm.openShop(109);
    }else if  (selection == 12) {
           cm.openNpc(108);
    }else if  (selection == 13) {
           cm.openShop(103);
      

}
}
}
}

