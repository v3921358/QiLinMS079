/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű�����������
 */
importPackage(java.awt);
importPackage(Packages.tools);
importPackage(Packages.server);
importPackage(Packages.handling.world);

//��������
var ��һ�ؾ��� = 200;
var �ڶ��ؾ��� = 300;
var �����ؾ��� = 400;
var ���Ĺؾ��� = 500;
//��������
var questions = Array("���ʷ�ʦһתҪ���ٵȼ�",
        "���ʷ���һתҪ���ٵȼ�",
        "���ʷ�ʦתְ��Ҫ��������",
        "���ʹ�����תְ��Ҫ��������",
        "���ʶ��ټ����ܽ��ж�ת",
        "����սʿһתҪ��������");
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
        cm.�Ի�����();
        return;
    }
    if (mode == 1){
        status++;
    }else{
        status--;
	}
    //��һ��
	var ��ɫ = cm.getPlayer().id;
    if (cm.getParty() != null) {
		if(cm.getParty().getMembers().size()!=cm.��ȡָ����ͼ�������(cm.�жϵ�ͼ())){
			cm.sendNext("���ж��Ѳ��������������Ķ��ѣ������߳���Ķ��ѡ�");
			cm.�Ի�����();
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
                        cm.sendNext("�ˣ�����Ҫ��Ա��ͨ��֤����ж�Ա�ռ���ɺ�ѿ�Ƭ�ռ�����Ȼ����ҡ�");
                        eim.setProperty("leader1stpreamble", "done");
                        cm.�Ի�����();
                    } else {
                        var complete = eim.getProperty(curMap.toString() + "stageclear");
                        if (complete != null) {
                            cm.sendNext("��ϲ������ ͨ����һ�׶ε����ѿ���!");
                            cm.�Ի�����();
                        } else {
                            var numpasses = (party.size() - 1) < 1 ? 1 : (party.size() - 1);
                            var strpasses = "#b" + numpasses.toString() + " ��ͨ��֤#k";
                            if (!cm.haveItem(4001008, numpasses)) {
                                cm.sendNext("�Һܱ�Ǹ�Ҳ���������أ�����Ҫ: " + strpasses + " ������֮���Ҿͻ�������ء�");
                                cm.�Ի�����();
                            } else {
                                cm.sendNext("���ռ� " + strpasses + "! ��ϲ���ء�");
                                clear(1, eim, cm);
                                cm.givePartyExp(cm.getLevel()*��һ�ؾ���, party);
                                cm.gainItem(4001008, -numpasses);
                                cm.�Ի�����();
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
                cm.sendNext("���������Ҫ�ռ� #b#v4001007##t4001007##k �������������Ŀ��ͬ�Ĵ�Ϊ������������⡣");
            } else if (status == 0) {
                var complete = eim.getProperty(curMap.toString() + "stageclear");
                if (complete != null) {
                    cm.�Ի�����();
                } else {
					if (cm.Getcharacterz("" + ��ɫ + "", 499) == 0) {
						cm.sendNext("���Ѿ�����ˣ��޷��ظ���ɣ������ѡ�����������ɡ�");
						cm.�Ի�����();
						return;
					}
                    var qstring = "member1st" + cm.getChar().getId().toString();
                    var numcoupons = qanswers[parseInt(eim.getProperty(qstring))];
                    var qcorr = cm.haveItem(4001007, (numcoupons + 1));
                    var enough = false;
                    if (!qcorr) {
                        qcorr = cm.haveItem(4001007, numcoupons);
                        if (qcorr) {
                            cm.sendNext("���������Ҵ�Ӧ��� #b#t4001008##k. �����ȥ����Ķӳ��ɡ�");
                            cm.gainItem(4001007, -numcoupons);
							cm.Gaincharacterz("" + ��ɫ + "", 499, -cm.Getcharacterz("" + ��ɫ + "", 499));
                            cm.gainItem(4001008, 1);
                            enough = true;
                        }
                    }
                    if (!enough) {
                        cm.sendNext("�Һܱ�Ǹ���������ǲ���ȷ�Ĵ𰸣�������������ȷ��������");
                    }
                    cm.�Ի�����();
                }
            } else if (status == 1) {
                if (preamble == null) {
                    var qstring = "member1st" + cm.getChar().getId().toString();
                    var question = parseInt(eim.getProperty(qstring));
                    cm.sendNextPrev(questions[question]);
                } else {
                    cm.�Ի�����();
                }
            } else if (status == 2) {
                eim.setProperty(pstring, "done");
                cm.�Ի�����();
            } else {
                eim.setProperty(pstring, "done");
                cm.�Ի�����();
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
                    cm.sendNext("��ϲ���أ�");
                    party = eim.getPlayers();

                    cm.gainItem(4001008, -10);
                    clear(5, eim, cm);
                    cm.�Ի�����();
                } else {
                    cm.sendNext("��ӭ�������ս׶���ֻҪ��#b#v4001008# x 10#k�ռ����������Ҿ����ˣ�");
                }
                cm.�Ի�����();
            } else {
                cm.sendNext("��ӭ�������ս׶�~������ֻҪ������ #v4001008# �����ӳ������ˣ�");
                cm.�Ի�����();
            }
        } else {
            if (status == 0) {
                cm.sendNext("��ĺܲ���˼�飡");
                //���辭�鲢���͵�����ͼ��
                cm.warpPartyWithExp(103000805, 10000);
            }
            if (status == 1) {
                cm.�Ի�����();
            }
        }
    } else {
        cm.sendNext("��Ч�ĵ�ͼ��������GM��");
        cm.�Ի�����();
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
//�ڶ��أ������أ����Ĺ�
function rectanglestages(cm) {
    var debug = false;
    var eim = cm.getChar().getEventInstance();
    if (curMap == 2) {
        var nthtext = "2";
        var nthobj = "����";
        var nthverb = "��";
        var nthpos = "��������̫��";
        var curcombo = stage2combos;
        var currect = stage2rects;
        var objset = [0, 0, 0, 0];
    } else if (curMap == 3) {
        var nthtext = "3";
        var nthobj = "ƽ̨";
        var nthverb = "վ";
        var nthpos = "վ��̫������Ե";
        var curcombo = stage3combos;
        var currect = stage3rects;
        var objset = [0, 0, 0, 0, 0];
    } else if (curMap == 4) {
        var nthtext = "4";
        var nthobj = "��Ͱ";
        var nthverb = "վ";
        var nthpos = "վ��̫������Ե";
        var curcombo = stage4combos;
        var currect = stage4rects;
        var objset = [0, 0, 0, 0, 0, 0];
    }
    if (playerStatus) {
        //�ڶ��أ������أ����Ĺ�
        if (status == 0) {
            party = eim.getPlayers();
            preamble = eim.getProperty("leader" + nthtext + "preamble");
            if (preamble == null) {
                cm.sendNext("     �ˣ���ӭ������ " + nthtext + " �׶�. �����Աߣ���ῴ��һЩ " + nthobj + ", #b����Ҫ������Ա����������ҵĴ𰸣�����¶Ծ�������أ����Ͱɣ� \r\n�~���˲���#r" + nthpos + "��Ȼ�᲻�ܹ���Ŷ��");
                eim.setProperty("leader" + nthtext + "preamble", "done");
                var sequenceNum = Math.floor(Math.random() * curcombo.length);
                eim.setProperty("stage" + nthtext + "combo", sequenceNum.toString());
                cm.�Ի�����();
            } else {
                var complete = eim.getProperty(curMap.toString() + "stageclear");
                if (complete != null) {
                    var mapClear = curMap.toString() + "stageclear";
                    eim.setProperty(mapClear, "true");
                    cm.�Ի�����();
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
                            //�ڶ��ؽ���
                            //�����ŶӾ���
                            if (nthtext == 2) {
                                cm.givePartyExp(cm.getLevel()*�ڶ��ؾ���, party);
                            } else if (nthtext == 3) {
                                cm.givePartyExp(cm.getLevel()*�����ؾ���, party);
                            } else if (nthtext == 4) {
                                cm.givePartyExp(cm.getLevel()*���Ĺؾ���, party);
                            }

                            cm.�Ի�����();
                        } else {
                            failstage(eim, cm);
                            cm.�Ի�����();
                        }
                    } else {
                        if (debug) {
                            var outstring = "Objects contain:"
                            for (i = 0; i < objset.length; i++) {
                                outstring += "\r\n" + (i + 1).toString() + ". " + objset[i].toString();
                            }
                            cm.sendNext(outstring);
                        } else {
                            cm.sendNext("��Ҫ�ŶӺ�����ֻ����ȷ #b3#k �� #b" + nthobj + "#k��ϲ���ͨ�ء�");
                        }
                        cm.�Ի�����();
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
            cm.�Ի�����();
        }
    } else {
        if (status == 0) {
            var complete = eim.getProperty(curMap.toString() + "stageclear");
            if (complete != null) {
                cm.�Ի�����();
            } else {
                cm.�Ի�����();
            }
        } else {
            var complete = eim.getProperty(curMap.toString() + "stageclear");
            if (complete != null) {
                var target = eim.getMapInstance(103000800 + curMap);
                var targetPortal = target.getPortal("st00");
                cm.getChar().changeMap(target, targetPortal);
            }
            cm.�Ի�����();
        }
    }
}