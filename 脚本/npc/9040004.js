/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű������а�
 */
importPackage(Packages.database);
var Z = "#fUI/GuildMark.img/Mark/Letter/00005025/1#";
var Y = "#fUI/GuildMark.img/Mark/Letter/00005024/3#";
var X = "#fUI/GuildMark.img/Mark/Letter/00005023/1#";
var D = "#fUI/GuildMark.img/Mark/Letter/00005003/1#";
var M = "#fUI/GuildMark.img/Mark/Letter/00005012/1#";
var A = "#fUI/GuildMark.img/Mark/Letter/00005000/1#";
var P = "#fUI/GuildMark.img/Mark/Letter/00005015/1#";
var Z = "#fUI/GuildMark.img/Mark/Letter/00005025/9#";
var ��ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var �� = "#fUI/GuildMark.img/Mark/Etc/00009001/13#";
var ��1 = "#fUI/GuildMark.img/Mark/Etc/00009001/8#";
var Ģ�� = "#fUI/UIWindow.img/Minigame/Common/mark#";


function start() {
    status = -1;
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    cm.���˴浵();
    var MC = cm.getServerName();
    var ���˰񿪹� = cm.GetPiot("���˰񿪹�", "1");

    if (status == 0) {
        var selStr = "\t\t\t\t#e#r< �� ҫ �� �� �� >#k#n\r\n\r\n";
        if (cm.GetPiot("���˰񿪹�", "1") <= 0) {
            selStr += "\t\t\t\t#L5##b��ҵȼ����а�#l\r\n";
	selStr += "\t\t\t\t#L6##b����������а�#l\r\n";
            selStr += "\t\t\t\t#L2##b��ҲƸ����а�#l\r\n";
            selStr += "\t\t\t\t#L13##b����ʱ�����а�#l\r\n";
	selStr += "\t\t\t\t#L4##b�����������а�#l\r\n";
            selStr += "\t\t\t\t#L0##b�����������а�#l\r\n";
            selStr += "\t\t\t\t#L19##b�����������а�#l\r\n";
            selStr += "\t\t\t\t#L25##b���ֶ������а�#l\r\n";
	selStr += "\t\t\t\t#L20##bս����ֵ���а�#l\r\n";
	
	//selStr += "\t\t\t\t#L51##bɱ���������а�#l\r\n";
		selStr += "\t\t\t\t#L53##b����������а�#l\r\n";
            selStr += "\t\t\t\t#L52##b����������а�#l\r\n";
            selStr += "\t\t\t\t#L21##b�������������#l\r\n";
            selStr += "\t\t\t\t#L22##b�����������#l\r\n\r\n";
        } else {
            selStr += "\r\nά���С�����";
            selStr += "\t\t\t\t#L666666##b���ؽ���#l";
        }
        if (cm.getPlayer().getGMLevel() == 6) {
            if (cm.GetPiot("���˰񿪹�", "1") <= 0) {
                selStr += "\r\n\t\t\t\t#L1000##b���˰�#g[������]#r[GM]#k#l";
            }
            if (cm.GetPiot("���˰񿪹�", "1") >= 1) {
                selStr += "\r\n\t\t\t\t#L1001##b���˰�#r[�ر���]#r[GM]#k#l";
            }
        }
        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
            //��ҵȼ����а�
            case 5111:
                var text = " ��������������������< #e#r��  ��#k#n >����������������������#n\r\n";
                text += "   #r��ʾ��#bÿ���賿(ά��)1:00,ˢ�µȼ�ǰ20������ҡ�#k\r\n\r\n";
                var rankinfo_list = cm.getBossRankCountTop10("�ȼ�����");
                if (rankinfo_list != null) {
                    for (var i = 0; i < rankinfo_list.size(); i++) {
                        if (i == 20) {
                            break;
                        }
                        var info = rankinfo_list.get(i);
                        //��ʾ����
                        text += i == 0 ? "#r" : i == 1 ? "#b" : i == 2 ? "#d" : "";
                        text += "  \t\t\t\t#eTop." + (i + 1) + "#k#n\r\n";
                        //t�������
                        text += "  \t\t\t\t�������:#b" + cm.��ɫIDȡ����(info.getCid()) + "#k\r\n";
                        //t��ҵȼ�
                        text += "  \t\t\t\t��ҵȼ�:#b" + info.getCount() + "#k\r\n";
                        //t���ְҵ
                        text += "  \t\t\t\t���ְҵ:#b" + cm.ְҵ(info.getPoints()) + "#k\r\n";
                        //t��������
                        if (info.getCname() == 0) {
                            text += "  \t\t\t\t��������:#bû�м������#k\r\n";
                        } else {
                            text += "  \t\t\t\t��������:#b" + cm.��ȡ��������(info.getCname()) + "#k\r\n";
                        }
                        text += "\r\n";
                    }
                }
                text += " ����������������������������������������������������#n\r\n\r\n";
                cm.sendOkS(text, 3);
                cm.dispose();
                break;

                //ÿ��ǩ������
            case 1:
                var text = " ��������������������< #e#rǩ  ��#k#n >����������������������#n\r\n";
                text += "   #r��ʾ��#bÿ����Сzǩ���ļ�¼���а�#k\r\n\r\n";
                var rankinfo_list = cm.getBossRankCountTop("ǩ��");
                if (rankinfo_list != null) {
                    for (var i = 0; i < rankinfo_list.size(); i++) {
                        if (i == 20) {
                            break;
                        }
                        var info = rankinfo_list.get(i);

                        text += i == 0 ? "#r" : i == 1 ? "#b" : i == 2 ? "#b" : "";
                        text += "\tTop." + (i + 1) + "\t\t";
                        // ������ֿո�
                        text += info.getCname();
                        for (var j = 16 - info.getCname().getBytes().length; j > 0; j--) {
                            text += " ";
                        }
                        text += "\t\t#k#nǩ��#r #e" + info.getCount();
                        text += "#k#n ��\t\t#k";
                        text += "";
                    }
                }
                text += "\r\n\r\n\r\n\r\n ����������������������������������������������������#n\r\n\r\n";
                cm.sendOkS(text, 3);
                cm.dispose();
                break;
                //ս��������
            case 20:
                var text = " ��������������������< #e#rս����#k#n >����������������������#n\r\n";
                text += "   #r��ʾ��#bս�����Ǹ�������������Ե��ۺϼ���ġ�#k\r\n\r\n";
                var rankinfo_list = cm.getBossRankCountTop("ս����ͳ��");
                if (rankinfo_list != null) {
                    for (var i = 0; i < rankinfo_list.size(); i++) {
                        if (i == 20) {
                            break;
                        }
                        var info = rankinfo_list.get(i);
                        text += i == 0 ? "#r" : i == 1 ? "#b" : i == 2 ? "#b" : "";
                        text += "\tTop." + (i + 1) + ".\t\t";
                        // ������ֿո�
                        text += info.getCname();
                        for (var j = 16 - info.getCname().getBytes().length; j > 0; j--) {
                            text += " ";
                        }
                        text += "\t\tս����:" + info.getCount();
                        text += "\t#k";
                        text += "";
                    }
                }
                text += "\r\n\r\n\r\n\r\n ����������������������������������������������������#n\r\n\r\n";
                cm.sendOkS(text, 3);
                cm.dispose();
                break;
				
				

				
				
				
				case 51:
				this.db = Packages.database.DatabaseConnection.getConnection();
				//���ݿ��ɫ��ɱ�������ֶ� SG 
			var sql = "select name,SG,gender from characters where gm<=0 order by SG desc limit 10;";
			var pstmt = db.prepareStatement(sql);
			var list = pstmt.executeQuery();
			var text = "\t\t\t\t#e#d�� ɱ���������а� ��#k#n\r\n\r\n";
			text += "\t#e����#n\t#e����ǳ�#n\t\t  #e����#n\t\t#e�����ƺ�#n\r\n";
			for (var i = 1; i <= 10; i++) {
				if (!list.next()) {
					break;
				}
				if (i == 1) {
					text += "#r";
				} else if (i == 2) {
					text += "#g";
				} else if (i == 3) {
					text += "#b";
				}
				text += "\t " + i + "\t\t ";
				
				// ������ֿո�
				text += list.getString("name");
				for (var j = 16 - list.getString("name").getBytes().length; j > 0; j--) {
					text += " ";
				}

				// ����ɫ��ɱ�������ֶ�
				text += "\t " + list.getInt("SG");
				var famevalues = list.getInt("SG");
				var famelength = 0;
				while (famevalues > 0) {
					famevalues = Math.floor(famevalues/10);
					famelength += 1;
				}
				for (var j = 8 - famelength; j > 0; j--) {
					text += " ";
				}

				if (i == 1) {
					if (list.getInt("gender") == 0) {
						text += " ������ż���#k";
					} else {
						text += " ��ɱ��ħ�ʡ�#k";
					}
				} else if (i == 2) {
					text += " ��ɱ��ħ����#k";
				} else if (i == 3) {
					text += "��ɱ��ħ����#k";
				}
				text += "\r\n";
			}
			list.close();
			pstmt.close();
			cm.sendOkS(text, 3);
			cm.dispose();
				
				
			break;	
				
			
			case 52:
				this.db = Packages.database.DatabaseConnection.getConnection();
				//���ݿ��ɫ��ɱ�������ֶ� SG 
			var sql = "select name,��������,gender from characters where gm<=0 order by �������� desc limit 10;";
			var pstmt = db.prepareStatement(sql);
			var list = pstmt.executeQuery();
			var text = "\t\t\t\t\t#e#d�� �������а� ��#k#n\r\n\r\n";
			text += "\t#e����#n\t#e����ǳ�#n\t\t  #e����#n\t\t#e�����ƺ�#n\r\n";
			for (var i = 1; i <= 10; i++) {
				if (!list.next()) {
					break;
				}
				if (i == 1) {
					text += "#r";
				} else if (i == 2) {
					text += "#r";
				} else if (i == 3) {
					text += "#r";
				} else if (i >= 4) {
					text += "#b";
				}
				text += "\t " + i + "\t\t ";
				
				// ������ֿո�
				text += list.getString("name");
				for (var j = 16 - list.getString("name").getBytes().length; j > 0; j--) {
					text += " ";
				}

				// ����ɫ��ɱ�������ֶ�
				text += "\t " + list.getInt("��������");
				var famevalues = list.getInt("��������");
				var famelength = 0;
				while (famevalues > 0) {
					famevalues = Math.floor(famevalues/10);
					famelength += 1;
				}
				for (var j = 8 - famelength; j > 0; j--) {
					text += " ";
				}

				if (i == 1) {
					if (list.getInt("gender") == 0) {
						text += " ������ż���#k";
					} else {
						text += "������ھ���#k";
					}
				} else if (i == 2) {
					text += "�������ˡ�#k";
				} else if (i == 3) {
					text += "�����С�ס�#k";
				} else if (i >= 4) {
					text += "�����׳ա�#k";
				}
				text += "\r\n";
			}
			list.close();
			pstmt.close();
			cm.sendOkS(text, 3);
			cm.dispose();
				
				
			break;	


			
			case 53:
				this.db = Packages.database.DatabaseConnection.getConnection();
				//���ݿ��ɫ��ɱ�������ֶ� SG 
			var sql = "select name,�������,gender from characters where gm<=0 order by ������� desc limit 10;";
			var pstmt = db.prepareStatement(sql);
			var list = pstmt.executeQuery();
			var text = "\t\t\t\t\t#e#d�� ����������а� ��#k#n\r\n\r\n";
			text += "\t#e����#n\t#e����ǳ�#n\t\t  #e����#n\t\t#e�����ƺ�#n\r\n";
			for (var i = 1; i <= 10; i++) {
				if (!list.next()) {
					break;
				}
				if (i == 1) {
					text += "#r";
				} else if (i == 2) {
					text += "#r";
				} else if (i == 3) {
					text += "#r";
				} else if (i >= 4) {
					text += "#b";
				}
				text += "\t " + i + "\t\t ";
				
				// ������ֿո�
				text += list.getString("name");
				for (var j = 16 - list.getString("name").getBytes().length; j > 0; j--) {
					text += " ";
				}

				// ����ɫ��ɱ�������ֶ�
				text += "\t " + list.getInt("�������");
				var famevalues = list.getInt("�������");
				var famelength = 0;
				while (famevalues > 0) {
					famevalues = Math.floor(famevalues/10);
					famelength += 1;
				}
				for (var j = 8 - famelength; j > 0; j--) {
					text += " ";
				}

				if (i == 1) {
					if (list.getInt("gender") == 0) {
						text += " ������ż���#k";
					} else {
						text += "������ż���#k";
					}
				} else if (i == 2) {
					text += "��������ˡ�#k";
				} else if (i == 3) {
					text += "���������֡�#k";
				} else if (i >= 4) {
					text += "������׳ա�#k";
				}
				text += "\r\n";
			}
			list.close();
			pstmt.close();
			cm.sendOkS(text, 3);
			cm.dispose();
				
				
			break;	

			
			
				

			case 4:
				selStr = "\t\t\t#e#r< �� �� �� �� �� �� �� >#k#n\r\n\r\n";
				selStr += ""+cm.�����������а�()+"#k\r\n\r\n";
                cm.sendOkS(selStr,3);
                break;  

			case 6:
				selStr = "\t\t\t\t#e#r< �� �� �� �� �� >#k#n\r\n\r\n";
				selStr += "   �������۷��������а񣬶��ǵ��Ͼ��г���ʵ�����ˡ�#k\r\n\r\n";
				selStr += ""+cm.�������а�()+"\r\n";
                cm.sendOkS(selStr,3);
                break;


            case 1000:
                cm.GainPiot("���˰񿪹�", "1", -���˰񿪹�);
                cm.GainPiot("���˰񿪹�", "1", 1);
                cm.dispose();
                cm.openNpc(9900004, 7);
                break;
            case 1001:
                cm.GainPiot("���˰񿪹�", "1", -���˰񿪹�);
                cm.dispose();
                cm.openNpc(9900004, 7);
                break
            case 21:
                cm.dispose();
                cm.openNpc(9040004, 1);
                break;
            case 22:
                cm.dispose();
                cm.openNpc(9040004, 2);
                break;
            case 666666:
                cm.dispose();
                cm.openNpc(9900004, 0);
                break;
            case 0:
                cm.displayGuildRanks();
                cm.dispose();
                break;
            case 9:
                cm.MapleMSpvpdeaths();
                cm.dispose();
                break;
            case 25:
                cm.�������а�();
                cm.dispose();
                break;
            case 26:
                cm.ɱ�����а�();
                cm.dispose();
			case 27:
                cm.ս�������а�();
                cm.dispose();
                break;

			case 28:
                cm.����������а�();
                cm.dispose();
                break;
            case 19:
                cm.�������а�();
                cm.dispose();
                break;
            case 23:
                cm.�������а�();
                cm.dispose();
                break;
            case 13:
                cm.������ʱ�����а�();
                cm.dispose();
                break;
            case 10:
                cm.MapleMSpvpkills();
                cm.dispose();
                break;
            case 5:
                cm.showlvl();
                cm.dispose()
                break;

            case 2:
                cm.showmeso();
                cm.dispose();
                break;
        }
    }
}
function getname(id) {
    var con1 = DatabaseConnection.getConnection();
    ps1 = con1.prepareStatement("SELECT name FROM characters WHERE id = ?");
    ps1.setInt(1, id);
    var rs1 = ps1.executeQuery();
    var name;
    if (rs1.next()) {
        name = rs1.getString("name");
    } else {
        name = "����";
    }
    rs1.close();
    ps1.close();
    return name;
}


 