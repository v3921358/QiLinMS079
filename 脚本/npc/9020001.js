/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：废弃都市
 */
importPackage(java.awt);
importPackage(Packages.tools);
importPackage(Packages.server);
importPackage(Packages.handling.world);

//经验设置
var 第一关经验 = 200;
var 第二关经验 = 300;
var 第三关经验 = 400;
var 第四关经验 = 500;
//设置问题
var questions = Array("请问法师一转要多少等级",
        "请问飞侠一转要多少等级",
        "请问法师转职需要多少智力",
        "请问弓箭手转职需要多少敏捷",
        "请问多少级才能进行二转",
        "请问战士一转要多少力量");
var qanswers = Array(8, 10, 20, 25, 30, 35);
var status;
var curMap;
var playerStatus;
var chatState;
var party;
var preamble;
var stage2rects = Array(new java.awt.Rectangle(-770,-132,28,178),new java.awt.Rectangle(-733,-337,26,105),new java.awt.Rectangle(-601,-328,29,105),new java.awt.Rectangle(-495,-125,24,165));
var stage2combos = Array(Array(0,1,1,1),Array(1,0,1,1),Array(1,1,0,1),Array(1,1,1,0));
var stage3rects = Array(new java.awt.Rectangle(608,-180,140,50),new java.awt.Rectangle(791,-117,140,45),new java.awt.Rectangle(958,-180,140,50),new java.awt.Rectangle(876,-238,140,45),new java.awt.Rectangle(702,-238,140,45));
var stage3combos = Array(Array(0,0,1,1,1),Array(0,1,0,1,1),Array(0,1,1,0,1),Array(0,1,1,1,0),Array(1,0,0,1,1),Array(1,0,1,0,1),Array(1,0,1,1,0),Array(1,1,0,0,1),Array(1,1,0,1,0),Array(1,1,1,0,0));
var stage4rects = Array(new java.awt.Rectangle(910,-236,35,5),new java.awt.Rectangle(877,-184,35,5),new java.awt.Rectangle(946,-184,35,5),new java.awt.Rectangle(845,-132,35,5),new java.awt.Rectangle(910,-132,35,5),new java.awt.Rectangle(981,-132,35,5));
var stage4combos = Array(Array(0,0,0,1,1,1),Array(0,0,1,0,1,1),Array(0,0,1,1,0,1),Array(0,0,1,1,1,0),Array(0,1,0,0,1,1),Array(0,1,0,1,0,1),Array(0,1,0,1,1,0),Array(0,1,1,0,0,1),Array(0,1,1,0,1,0),Array(0,1,1,1,0,0),Array(1,0,0,0,1,1),Array(1,0,0,1,0,1),Array(1,0,0,1,1,0),Array(1,0,1,0,0,1),Array(1,0,1,0,1,0),Array(1,0,1,1,0,0),Array(1,1,0,0,0,1),Array(1,1,0,0,1,0),Array(1,1,0,1,0,0),Array(1,1,1,0,0,0));
var eye = 9300002;
var necki = 9300000;
var slime = 9300003;
var monsterIds = Array(eye, eye, eye, necki, necki, necki, necki, necki, necki, slime);
var prizeIdScroll = Array(2040502, 2040505, 2040802, 2040002, 2040402, 2040602);
var prizeIdUse = Array(2000001, 2000002, 2000003, 2000006, 2000004, 2022000, 2022003);
var prizeQtyUse = Array(80, 80, 80, 50, 5, 15, 15);
var prizeIdEquip = Array(1032004, 1032005, 1032009, 1032006, 1032007, 1032010, 1032002, 1002026, 1002089, 1002090);
var prizeIdEtc = Array(4010000, 4010001, 4010002, 4010003, 4010004, 4010005, 4010006, 4020000, 4020001, 4020002, 4020003, 4020004, 4020005, 4020006, 4020007, 4020008, 4003000);
var prizeQtyEtc = Array(15, 15, 15, 15, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 3, 3, 30);

function start() {
    status = -1;
    mapId = cm.getMapId();
    if (mapId == 103000800)
        curMap = 1;
    else if (mapId == 103000801)
        curMap = 2;
    else if (mapId == 103000802)
        curMap = 3;
    else if (mapId == 103000803)
        curMap = 4;
    else if (mapId == 103000804)
        curMap = 5;
    playerStatus = cm.isLeader();
    preamble = null;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.对话结束();
        return;
    }
    if (mode == 1){
        status++;
    }else{
        status--;
	}
    //第一关
	var 角色 = cm.getPlayer().id;
    if (cm.getParty() != null) {
		if(cm.getParty().getMembers().size()!=cm.获取指定地图玩家数量(cm.判断地图())){
			cm.sendNext("你有队友不在这里，请拉回你的队友，或者踢出你的队友。");
			cm.对话结束();
		}
	}
    if (curMap == 1) {
        if (playerStatus) {
            if (status == 0) {
                var eim = cm.getEventInstance();
                if (eim != null) {
                    party = eim.getPlayers();
                    preamble = eim.getProperty("leader1stpreamble");
                    if (preamble == null) {
                        cm.sendNext("嗨，我需要队员的通行证，请叫队员收集完成后把卡片收集起来然后给我。");
                        eim.setProperty("leader1stpreamble", "done");
                        cm.对话结束();
                    } else {
                        var complete = eim.getProperty(curMap.toString() + "stageclear");
                        if (complete != null) {
                            cm.sendNext("恭喜您过关 通往下一阶段的门已开启!");
                            cm.对话结束();
                        } else {
                            var numpasses = (party.size() - 1) < 1 ? 1 : (party.size() - 1);
                            var strpasses = "#b" + numpasses.toString() + " 张通行证#k";
                            if (!cm.haveItem(4001008, numpasses)) {
                                cm.sendNext("我很抱歉我不能让你过关，我需要: " + strpasses + " 交给我之后我就会让你过关。");
                                cm.对话结束();
                            } else {
                                cm.sendNext("你收集 " + strpasses + "! 恭喜过关。");
                                clear(1, eim, cm);
                                cm.givePartyExp(cm.getLevel()*第一关经验, party);
                                cm.gainItem(4001008, -numpasses);
                                cm.对话结束();
                            }
                        }
                    }
                }
            }
        } else {
            var eim = cm.getChar().getEventInstance();
            pstring = "member1stpreamble" + cm.getChar().getId().toString();
            preamble = eim.getProperty(pstring);
            if (status == 0 && preamble == null) {
                var qstring = "member1st" + cm.getChar().getId().toString();
                var question = eim.getProperty(qstring);
                if (question == null) {
                    var questionNum = Math.floor(Math.random() * questions.length);
                    eim.setProperty(qstring, questionNum.toString());
                }
                cm.sendNext("在这里，你需要收集 #b#v4001007##t4001007##k 过击败鳄鱼的数目相同的答案为单独提出的问题。");
            } else if (status == 0) {
                var complete = eim.getProperty(curMap.toString() + "stageclear");
                if (complete != null) {
                    cm.对话结束();
                } else {
					if (cm.Getcharacterz("" + 角色 + "", 499) == 0) {
						cm.sendNext("你已经完成了，无法重复完成，你可以选择帮助队友完成。");
						cm.对话结束();
						return;
					}
                    var qstring = "member1st" + cm.getChar().getId().toString();
                    var numcoupons = qanswers[parseInt(eim.getProperty(qstring))];
                    var qcorr = cm.haveItem(4001007, (numcoupons + 1));
                    var enough = false;
                    if (!qcorr) {
                        qcorr = cm.haveItem(4001007, numcoupons);
                        if (qcorr) {
                            cm.sendNext("来，这是我答应你的 #b#t4001008##k. 快点拿去给你的队长吧。");
                            cm.gainItem(4001007, -numcoupons);
							cm.Gaincharacterz("" + 角色 + "", 499, -cm.Getcharacterz("" + 角色 + "", 499));
                            cm.gainItem(4001008, 1);
                            enough = true;
                        }
                    }
                    if (!enough) {
                        cm.sendNext("我很抱歉，但是这是不正确的答案！请在您给我正确的数量。");
                    }
                    cm.对话结束();
                }
            } else if (status == 1) {
                if (preamble == null) {
                    var qstring = "member1st" + cm.getChar().getId().toString();
                    var question = parseInt(eim.getProperty(qstring));
                    cm.sendNextPrev(questions[question]);
                } else {
                    cm.对话结束();
                }
            } else if (status == 2) {
                eim.setProperty(pstring, "done");
                cm.对话结束();
            } else {
                eim.setProperty(pstring, "done");
                cm.对话结束();
            }
        }
    } else if (2 <= curMap && 4 >= curMap) {
        rectanglestages(cm);
    } else if (curMap == 5) {
        var eim = cm.getChar().getEventInstance();
        //var stage5done = eim.getProperty("5stageclear");
		var stage5done = eim.getProperty("5stageclear");
        if (stage5done == null) {
            if (playerStatus) {
                var passes = cm.haveItem(4001008, 10);
                if (passes) {
                    cm.sendNext("恭喜过关！");
                    party = eim.getPlayers();

                    cm.gainItem(4001008, -10);
                    clear(5, eim, cm);
                    cm.对话结束();
                } else {
                    cm.sendNext("欢迎来到最终阶段你只要把#b#v4001008# x 10#k收集起来交给我就行了！");
                }
                cm.对话结束();
            } else {
                cm.sendNext("欢迎来到最终阶段~现在你只要把所有 #v4001008# 交给队长就行了！");
                cm.对话结束();
            }
        } else {
            if (status == 0) {
                cm.sendNext("真的很不可思议！");
                //给予经验并传送到到地图。
                cm.warpPartyWithExp(103000805, 10000);
            }
            if (status == 1) {
                cm.对话结束();
            }
        }
    } else {
        cm.sendNext("无效的地图，请联络GM！");
        cm.对话结束();
    }
}

function clear(stage, eim, cm) {
    eim.setProperty(stage.toString() + "stageclear", "true");

    cm.showEffect(true, "quest/party/clear");
    cm.playSound(true, "Party1/Clear");
    cm.environmentChange(true, "gate");

    var mf = eim.getMapFactory();
    map = mf.getMap(103000800 + stage);
    var nextStage = eim.getMapFactory().getMap(103000800 + stage);
    var portal = nextStage.getPortal("next00");
    if (portal != null) {
        portal.setScriptName("kpq" + (stage + 1).toString());
    }
}

function failstage(eim, cm) {
    cm.showEffect(true, "quest/party/wrong_kor");
    cm.playSound(true, "Party1/Failed");
}
//第二关，第三关，第四关
function rectanglestages(cm) {
    var debug = false;
    var eim = cm.getChar().getEventInstance();
    if (curMap == 2) {
        var nthtext = "2";
        var nthobj = "绳子";
        var nthverb = "挂";
        var nthpos = "挂在绳子太低";
        var curcombo = stage2combos;
        var currect = stage2rects;
        var objset = [0, 0, 0, 0];
    } else if (curMap == 3) {
        var nthtext = "3";
        var nthobj = "平台";
        var nthverb = "站";
        var nthpos = "站在太靠近边缘";
        var curcombo = stage3combos;
        var currect = stage3rects;
        var objset = [0, 0, 0, 0, 0];
    } else if (curMap == 4) {
        var nthtext = "4";
        var nthobj = "酒桶";
        var nthverb = "站";
        var nthpos = "站在太靠近边缘";
        var curcombo = stage4combos;
        var currect = stage4rects;
        var objset = [0, 0, 0, 0, 0, 0];
    }
    if (playerStatus) {
        //第二关，第三关，第四关
        if (status == 0) {
            party = eim.getPlayers();
            preamble = eim.getProperty("leader" + nthtext + "preamble");
            if (preamble == null) {
                cm.sendNext("     嗨，欢迎来到第 " + nthtext + " 阶段. 在我旁边，你会看到一些 " + nthobj + ", #b你需要三名队员挂在上面猜我的答案，如果猜对就让你过关，加油吧！ \r\n喔~对了不能#r" + nthpos + "不然会不能过关哦！");
                eim.setProperty("leader" + nthtext + "preamble", "done");
                var sequenceNum = Math.floor(Math.random() * curcombo.length);
                eim.setProperty("stage" + nthtext + "combo", sequenceNum.toString());
                cm.对话结束();
            } else {
                var complete = eim.getProperty(curMap.toString() + "stageclear");
                if (complete != null) {
                    var mapClear = curMap.toString() + "stageclear";
                    eim.setProperty(mapClear, "true");
                    cm.对话结束();
                } else {
                    var totplayers = 0;
                    for (i = 0; i < objset.length; i++) {
                        for (j = 0; j < party.size(); j++) {
                            var present = currect[i].contains(party.get(j).getPosition());
                            if (present) {
                                objset[i] = objset[i] + 1;
                                totplayers = totplayers + 1;
                            }
                        }
                    }
                    if (totplayers > 0 || debug) {
                        var combo = curcombo[parseInt(eim.getProperty("stage" + nthtext + "combo"))];
                        var testcombo = true;
                        for (i = 0; i < objset.length; i++) {
                            if (combo[i] != objset[i])
                                testcombo = false;
                        }
                        if (testcombo || debug) {
                            clear(curMap, eim, cm);
                            //var exp = (Math.pow(2, curMap) * 500);
                            //第二关奖励
                            //给予团队经验
                            if (nthtext == 2) {
                                cm.givePartyExp(cm.getLevel()*第二关经验, party);
                            } else if (nthtext == 3) {
                                cm.givePartyExp(cm.getLevel()*第三关经验, party);
                            } else if (nthtext == 4) {
                                cm.givePartyExp(cm.getLevel()*第四关经验, party);
                            }

                            cm.对话结束();
                        } else {
                            failstage(eim, cm);
                            cm.对话结束();
                        }
                    } else {
                        if (debug) {
                            var outstring = "Objects contain:"
                            for (i = 0; i < objset.length; i++) {
                                outstring += "\r\n" + (i + 1).toString() + ". " + objset[i].toString();
                            }
                            cm.sendNext(outstring);
                        } else {
                            cm.sendNext("需要团队合作，只有正确 #b3#k 个 #b" + nthobj + "#k组合才能通关。");
                        }
                        cm.对话结束();
                    }
                }
            }
        } else {
            var complete = eim.getProperty(curMap.toString() + "stageclear");
            if (complete != null) {
                var target = eim.getMapInstance(103000800 + curMap);
                var targetPortal = target.getPortal("st00");
                cm.getChar().changeMap(target, targetPortal);
            }
            cm.对话结束();
        }
    } else {
        if (status == 0) {
            var complete = eim.getProperty(curMap.toString() + "stageclear");
            if (complete != null) {
                cm.对话结束();
            } else {
                cm.对话结束();
            }
        } else {
            var complete = eim.getProperty(curMap.toString() + "stageclear");
            if (complete != null) {
                var target = eim.getMapInstance(103000800 + curMap);
                var targetPortal = target.getPortal("st00");
                cm.getChar().changeMap(target, targetPortal);
            }
            cm.对话结束();
        }
    }
}