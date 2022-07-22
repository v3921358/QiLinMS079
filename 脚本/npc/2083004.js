
var status = 0;

var ttt ="#fUI/UIWindow.img/Quest/icon9/0#";
var xxx ="#fUI/UIWindow.img/Quest/icon8/0#";
var sss ="#fUI/UIWindow.img/QuestIcon/3/0#";


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
		 if (cm.getPlayerStat("LVL") < 160 ) {
			cm.sendOk("需要#r160级#k的队长队长带队，才可以挑战。");
			cm.dispose();
			 }
		if (mode == 1)
		status++;
		else
		status--;


	if (status == 0) {

	    var textz = "\r\n勇士:#d#h ##k，每日可挑战暗黑龙王3次。\r\n\r\n";
		
		textz += "#d当前已挑战[#r"+cm.getPlayer().getBossLogD('暗黑龙王')+"#k/3]次#l\r\n\r\n";

		textz += "#b#L0#挑战【暗黑龙王】#l\r\n\r\n";
		
		//textz += "#b#L1#解开封印【召唤--扎昆手臂(经验)】#l\r\n\r\n";
		
	//	textz += "#b#L2#解开封印【召唤--三号BOSS】#l\r\n\r\n";
		
	//	textz += "#r#L3#解开封印【召唤--四号BOSS】#l\r\n\r\n";
		
	//	textz += "#r#L5#解开封印【召唤--五号BOSS】#l\r\n\r\n";
		
		textz += "#r#L4#我要离开#l\r\n\r\n";

		

		cm.sendSimple (textz);  

			
			}else if (cm.getParty() == null){
					cm.sendOk("请组队后和我谈话。");
					cm.dispose();
				}else if (status == 1) {    
				if (selection == 0){					
					var party = cm.getParty().getMembers();
                    var mapId = cm.getMapId();
                    var next = true;
                    var levelValid = 0;
                    var inMap = 0;					
                    var it = party.iterator();					
                    while (it.hasNext()) {
                        var cPlayer = it.next();
						if (cPlayer.getBossLogD("暗黑龙王") > 2) {
							cm.sendOk("你的队伍里已经有人挑战过了！");							
							next = false;
							cm.dispose();
							break;							
						}
					}
				if (next == true) {
				if(cm.getPlayerStat("LVL") < 160){
					cm.sendOk("需要#r160级#k的队长队长带队，才可以挑战。");
					
					cm.dispose();
				// }else if(cm.getMapFactory().getMap(910000021).playerCount() != 0){
					// cm.sendOk("已经有其他队伍在挑战了.");
					// cm.dispose();						
				}else if (cm.getParty() == null){
					cm.sendOk("请组队后和我谈话。");
					cm.dispose();
				}else if (!cm.isLeader()) { // 不是队长
                    cm.sendOk("队长必须在这里。请让他和我说话。");
                    cm.dispose();
                }else if (cm.getPlayer().getBossLogD('暗黑龙王') >= 3) {
					cm.sendOk("你今天挑战次数超过3次!");
					cm.dispose();	
				}else if(cm.getMonsterCount(910000021) > 0){
					 cm.sendOk("请先消灭掉该地图已经召唤出的暗黑龙王!");
					 cm.dispose();
				// }else if (cm.getMeso() < 5000000) {
					// cm.sendOk("你身上不足500万金币!");
					// cm.dispose();		
			
			}else{ 
                   // cm.sendOk("当前船长并没有清理完，无法继续召唤！");
                  //  cm.dispose();
		//}else if(party.getMembers().size() < 0) {
	           // cm.sendOk("需要 6 人以上的组队才可以释放强大的魔法！!");
                   // cm.dispose();	
		//}else if (cm.getMeso() >= 5000000) {	//&&cm.haveItem(4001257,1)&&cm.haveItem(4001258,1)&&cm.haveItem(4001259,1)&&cm.haveItem(4001260,1)// #i04001257# #i04001258# #i04001259# #i04001260#
					cm.mapMessage("随着一声咆哮，暗黑龙王出现了。");
					cm.getMap(910000021).resetFully();
					cm.warpParty(910000021)
                    //cm.gainMeso(-5000000);						
					cm.setPartyBossLog("暗黑龙王");
                    cm.spawnMobOnMap(8810026,1,653,94,910000021);
                    cm.changeMusic("Bgm18/BlackWing");
					cm.worldMessage(6,"勇士 "+cm.getName()+" 带队去挑战暗黑龙王了，让我们祝他好运。");

					cm.dispose();
					}
				} else {
					cm.sendOk("你的队伍里已经有人挑战过3次了！");
					cm.dispose();
				}
					
	}else if (selection == 1){
		var party = cm.getPlayer().getParty();		
		if (cm.getBossLog('PlayQuest56') > 2) {
			cm.sendOk("你今天挑战次数超过2次!");
			cm.dispose();	
	}	else if (cm.getMeso() < 2000000) {
			cm.sendOk("你身上不足200万金币!");
			cm.dispose();
			}else{ 
	//	}else if(party.getMembers().size() < 0) {
	 //           cm.sendOk("需要 6 人以上的组队才可以释放强大的魔法！!");
     //               cm.dispose();	
		//&&cm.haveItem(4001257,1)&&cm.haveItem(4001258,1)&&cm.haveItem(4001259,1)&&cm.haveItem(4001260,1)// #i04001257# #i04001258# #i04001259# #i04001260#
                        cm.gainMeso(-2000000);
						cm.setBossLog('PlayQuest56');
                        cm.spawnMonster(8800003,1);
                        cm.dispose();
						cm.喇叭(2,"[扎昆副本]：玩家" + cm.getPlayer().getName() + "开始挑战扎昆手臂~");
                        
			cm.dispose();			
}
    }else if (selection == 2){
		var party = cm.getPlayer().getParty();		
		if (cm.getBossLog('PlayQuest52') >= 2) {
			cm.sendOk("你今天挑战次数超过2次!");
			cm.dispose();	
	}	else if (cm.getMeso() < 5000000) {
			cm.sendOk("你身上不足500万金币!");
			cm.dispose();
			}else{ 
	//	}else if(party.getMembers().size() < 0) {
	//            cm.sendOk("需要 6 人以上的组队才可以释放强大的魔法！!");
    //                cm.dispose();	
		//&&cm.haveItem(4001257,1)&&cm.haveItem(4001258,1)&&cm.haveItem(4001259,1)&&cm.haveItem(4001260,1)// #i04001257# #i04001258# #i04001259# #i04001260#
                       cm.gainMeso(-5000000);
					   cm.setBossLog('PlayQuest52');
                        cm.spawnMonster(9400591,1);
                        cm.dispose();
						cm.喇叭(2,"[绯红副本]：玩家" + cm.getPlayer().getName() + "开始挑战三号BOSS，开始爆肝啊啊啊啊!!!");
                       
			cm.dispose();	
}
     }else if (selection == 3){
		var party = cm.getPlayer().getParty();		
		if (cm.getBossLog('PlayQuest53') >= 2) {
			cm.sendOk("你今天挑战次数超过2次!");
			cm.dispose();	
	}	else if (cm.getMeso() < 5000000) {
			cm.sendOk("你身上不足500万金币!");
			cm.dispose();
			}else{ 
		//}else if(party.getMembers().size() < 0) {
	     //       cm.sendOk("需要 6 人以上的组队才可以释放强大的魔法！!");
        //            cm.dispose();	
		//&&cm.haveItem(4001257,1)&&cm.haveItem(4001258,1)&&cm.haveItem(4001259,1)&&cm.haveItem(4001260,1)// #i04001257# #i04001258# #i04001259# #i04001260#
                       cm.gainMeso(-5000000);
					   cm.setBossLog('PlayQuest53');
                        cm.spawnMonster(9400592,1);
                        cm.dispose();
						cm.喇叭(2,"[绯红副本]：玩家" + cm.getPlayer().getName() + "开始挑战四号BOSS!!!希望他能活着出来");
                       
			cm.dispose();	
			}
			}else if (selection == 5){
		var party = cm.getPlayer().getParty();		
		if (cm.getBossLog('PlayQuest54') >= 2) {
			cm.sendOk("你今天挑战次数超过2次!");
			cm.dispose();	
	}	else if (cm.getMeso() < 5000000) {
			cm.sendOk("你身上不足500万金币!");
			cm.dispose();
			}else{ 
		//}else if(party.getMembers().size() < 0) {
	     //       cm.sendOk("需要 6 人以上的组队才可以释放强大的魔法！!");
        //            cm.dispose();	
		//&&cm.haveItem(4001257,1)&&cm.haveItem(4001258,1)&&cm.haveItem(4001259,1)&&cm.haveItem(4001260,1)// #i04001257# #i04001258# #i04001259# #i04001260#
                         cm.gainMeso(-5000000);
						 cm.setBossLog('PlayQuest54');
                        cm.spawnMonster(9400593,1);
                        cm.dispose();
						cm.喇叭(2,"[绯红副本]：玩家" + cm.getPlayer().getName() + "开始挑战五号BOSS,准备接受暴虐吧");
                       
			cm.dispose();	
			}
	     }else if (selection == 4){
			cm.warp(910000000);
			cm.dispose();	
			}
			
		}										
	}
}
