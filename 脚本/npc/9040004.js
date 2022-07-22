/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：排行榜
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
var 箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 心 = "#fUI/GuildMark.img/Mark/Etc/00009001/13#";
var 心1 = "#fUI/GuildMark.img/Mark/Etc/00009001/8#";
var 蘑菇 = "#fUI/UIWindow.img/Minigame/Common/mark#";


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
    cm.个人存档();
    var MC = cm.getServerName();
    var 名人榜开关 = cm.GetPiot("名人榜开关", "1");

    if (status == 0) {
        var selStr = "\t\t\t\t#e#r< 荣 耀 排 行 榜 >#k#n\r\n\r\n";
        if (cm.GetPiot("名人榜开关", "1") <= 0) {
            selStr += "\t\t\t\t#L5##b玩家等级排行榜#l\r\n";
	selStr += "\t\t\t\t#L6##b玩家满级排行榜#l\r\n";
            selStr += "\t\t\t\t#L2##b玩家财富排行榜#l\r\n";
            selStr += "\t\t\t\t#L13##b在线时常排行榜#l\r\n";
	selStr += "\t\t\t\t#L4##b重生永恒排行榜#l\r\n";
            selStr += "\t\t\t\t#L0##b家族势力排行榜#l\r\n";
            selStr += "\t\t\t\t#L19##b人气明星排行榜#l\r\n";
            selStr += "\t\t\t\t#L25##b娱乐豆豆排行榜#l\r\n";
	selStr += "\t\t\t\t#L20##b战斗力值排行榜#l\r\n";
	
	//selStr += "\t\t\t\t#L51##b杀怪数量排行榜#l\r\n";
		selStr += "\t\t\t\t#L53##b组队任务排行榜#l\r\n";
            selStr += "\t\t\t\t#L52##b钓鱼积分排行榜#l\r\n";
            selStr += "\t\t\t\t#L21##b五子棋积分排行#l\r\n";
            selStr += "\t\t\t\t#L22##b记忆大考验排行#l\r\n\r\n";
        } else {
            selStr += "\r\n维护中···";
            selStr += "\t\t\t\t#L666666##b返回界面#l";
        }
        if (cm.getPlayer().getGMLevel() == 6) {
            if (cm.GetPiot("名人榜开关", "1") <= 0) {
                selStr += "\r\n\t\t\t\t#L1000##b名人榜#g[开启中]#r[GM]#k#l";
            }
            if (cm.GetPiot("名人榜开关", "1") >= 1) {
                selStr += "\r\n\t\t\t\t#L1001##b名人榜#r[关闭中]#r[GM]#k#l";
            }
        }
        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
            //玩家等级排行榜
            case 5111:
                var text = " ┌─────────< #e#r等  级#k#n >──────────┐#n\r\n";
                text += "   #r提示：#b每日凌晨(维护)1:00,刷新等级前20名的玩家。#k\r\n\r\n";
                var rankinfo_list = cm.getBossRankCountTop10("等级排行");
                if (rankinfo_list != null) {
                    for (var i = 0; i < rankinfo_list.size(); i++) {
                        if (i == 20) {
                            break;
                        }
                        var info = rankinfo_list.get(i);
                        //显示名次
                        text += i == 0 ? "#r" : i == 1 ? "#b" : i == 2 ? "#d" : "";
                        text += "  \t\t\t\t#eTop." + (i + 1) + "#k#n\r\n";
                        //t玩家名字
                        text += "  \t\t\t\t玩家名字:#b" + cm.角色ID取名字(info.getCid()) + "#k\r\n";
                        //t玩家等级
                        text += "  \t\t\t\t玩家等级:#b" + info.getCount() + "#k\r\n";
                        //t玩家职业
                        text += "  \t\t\t\t玩家职业:#b" + cm.职业(info.getPoints()) + "#k\r\n";
                        //t所属家族
                        if (info.getCname() == 0) {
                            text += "  \t\t\t\t所属家族:#b没有加入家族#k\r\n";
                        } else {
                            text += "  \t\t\t\t所属家族:#b" + cm.获取家族名称(info.getCname()) + "#k\r\n";
                        }
                        text += "\r\n";
                    }
                }
                text += " └────────────────────────┘#n\r\n\r\n";
                cm.sendOkS(text, 3);
                cm.dispose();
                break;

                //每日签到排行
            case 1:
                var text = " ┌─────────< #e#r签  到#k#n >──────────┐#n\r\n";
                text += "   #r提示：#b每日在小z签到的记录排行榜。#k\r\n\r\n";
                var rankinfo_list = cm.getBossRankCountTop("签到");
                if (rankinfo_list != null) {
                    for (var i = 0; i < rankinfo_list.size(); i++) {
                        if (i == 20) {
                            break;
                        }
                        var info = rankinfo_list.get(i);

                        text += i == 0 ? "#r" : i == 1 ? "#b" : i == 2 ? "#b" : "";
                        text += "\tTop." + (i + 1) + "\t\t";
                        // 填充名字空格
                        text += info.getCname();
                        for (var j = 16 - info.getCname().getBytes().length; j > 0; j--) {
                            text += " ";
                        }
                        text += "\t\t#k#n签到#r #e" + info.getCount();
                        text += "#k#n 天\t\t#k";
                        text += "";
                    }
                }
                text += "\r\n\r\n\r\n\r\n └────────────────────────┘#n\r\n\r\n";
                cm.sendOkS(text, 3);
                cm.dispose();
                break;
                //战斗力排行
            case 20:
                var text = " ┌─────────< #e#r战斗力#k#n >──────────┐#n\r\n";
                text += "   #r提示：#b战斗力是根据玩家自身属性的综合计算的。#k\r\n\r\n";
                var rankinfo_list = cm.getBossRankCountTop("战斗力统计");
                if (rankinfo_list != null) {
                    for (var i = 0; i < rankinfo_list.size(); i++) {
                        if (i == 20) {
                            break;
                        }
                        var info = rankinfo_list.get(i);
                        text += i == 0 ? "#r" : i == 1 ? "#b" : i == 2 ? "#b" : "";
                        text += "\tTop." + (i + 1) + ".\t\t";
                        // 填充名字空格
                        text += info.getCname();
                        for (var j = 16 - info.getCname().getBytes().length; j > 0; j--) {
                            text += " ";
                        }
                        text += "\t\t战斗力:" + info.getCount();
                        text += "\t#k";
                        text += "";
                    }
                }
                text += "\r\n\r\n\r\n\r\n └────────────────────────┘#n\r\n\r\n";
                cm.sendOkS(text, 3);
                cm.dispose();
                break;
				
				

				
				
				
				case 51:
				this.db = Packages.database.DatabaseConnection.getConnection();
				//数据库角色表杀怪数量字段 SG 
			var sql = "select name,SG,gender from characters where gm<=0 order by SG desc limit 10;";
			var pstmt = db.prepareStatement(sql);
			var list = pstmt.executeQuery();
			var text = "\t\t\t\t#e#d★ 杀怪数量排行榜 ★#k#n\r\n\r\n";
			text += "\t#e名次#n\t#e玩家昵称#n\t\t  #e数量#n\t\t#e荣誉称号#n\r\n";
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
				
				// 填充名字空格
				text += list.getString("name");
				for (var j = 16 - list.getString("name").getBytes().length; j > 0; j--) {
					text += " ";
				}

				// 填充角色表杀怪数量字段
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
						text += " ★世界偶像★#k";
					} else {
						text += " ★杀怪魔皇★#k";
					}
				} else if (i == 2) {
					text += " ★杀怪魔将★#k";
				} else if (i == 3) {
					text += "★杀怪魔兵★#k";
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
				//数据库角色表杀怪数量字段 SG 
			var sql = "select name,钓鱼排名,gender from characters where gm<=0 order by 钓鱼排名 desc limit 10;";
			var pstmt = db.prepareStatement(sql);
			var list = pstmt.executeQuery();
			var text = "\t\t\t\t\t#e#d★ 钓鱼排行榜 ★#k#n\r\n\r\n";
			text += "\t#e名次#n\t#e玩家昵称#n\t\t  #e数量#n\t\t#e荣誉称号#n\r\n";
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
				
				// 填充名字空格
				text += list.getString("name");
				for (var j = 16 - list.getString("name").getBytes().length; j > 0; j--) {
					text += " ";
				}

				// 填充角色表杀怪数量字段
				text += "\t " + list.getInt("钓鱼排名");
				var famevalues = list.getInt("钓鱼排名");
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
						text += " ★世界偶像★#k";
					} else {
						text += "★世界冠军★#k";
					}
				} else if (i == 2) {
					text += "★钓鱼达人★#k";
				} else if (i == 3) {
					text += "★钓鱼小白★#k";
				} else if (i >= 4) {
					text += "★钓鱼白痴★#k";
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
				//数据库角色表杀怪数量字段 SG 
			var sql = "select name,组队排名,gender from characters where gm<=0 order by 组队排名 desc limit 10;";
			var pstmt = db.prepareStatement(sql);
			var list = pstmt.executeQuery();
			var text = "\t\t\t\t\t#e#d★ 组队任务排行榜 ★#k#n\r\n\r\n";
			text += "\t#e名次#n\t#e玩家昵称#n\t\t  #e数量#n\t\t#e荣誉称号#n\r\n";
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
				
				// 填充名字空格
				text += list.getString("name");
				for (var j = 16 - list.getString("name").getBytes().length; j > 0; j--) {
					text += " ";
				}

				// 填充角色表杀怪数量字段
				text += "\t " + list.getInt("组队排名");
				var famevalues = list.getInt("组队排名");
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
						text += " ★世界偶像★#k";
					} else {
						text += "★任务偶像★#k";
					}
				} else if (i == 2) {
					text += "★任务达人★#k";
				} else if (i == 3) {
					text += "★任务新手★#k";
				} else if (i >= 4) {
					text += "★任务白痴★#k";
				}
				text += "\r\n";
			}
			list.close();
			pstmt.close();
			cm.sendOkS(text, 3);
			cm.dispose();
				
				
			break;	

			
			
				

			case 4:
				selStr = "\t\t\t#e#r< 重 生 永 恒 排 行 榜 >#k#n\r\n\r\n";
				selStr += ""+cm.永恒重生排行榜()+"#k\r\n\r\n";
                cm.sendOkS(selStr,3);
                break;  

			case 6:
				selStr = "\t\t\t\t#e#r< 满 级 排 行 榜 >#k#n\r\n\r\n";
				selStr += "   这里是巅峰王者排行榜，都是岛上具有超凡实力的人。#k\r\n\r\n";
				selStr += ""+cm.满级排行榜()+"\r\n";
                cm.sendOkS(selStr,3);
                break;


            case 1000:
                cm.GainPiot("名人榜开关", "1", -名人榜开关);
                cm.GainPiot("名人榜开关", "1", 1);
                cm.dispose();
                cm.openNpc(9900004, 7);
                break;
            case 1001:
                cm.GainPiot("名人榜开关", "1", -名人榜开关);
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
                cm.声望排行榜();
                cm.dispose();
                break;
            case 26:
                cm.杀怪排行榜();
                cm.dispose();
			case 27:
                cm.战斗力排行榜();
                cm.dispose();
                break;

			case 28:
                cm.钓鱼积分排行榜();
                cm.dispose();
                break;
            case 19:
                cm.人气排行榜();
                cm.dispose();
                break;
            case 23:
                cm.豆豆排行榜();
                cm.dispose();
                break;
            case 13:
                cm.总在线时间排行榜();
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
        name = "无名";
    }
    rs1.close();
    ps1.close();
    return name;
}


 