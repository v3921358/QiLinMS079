var 皇冠白 ="#fUI/GuildMark/Mark/Plant/00003006/15#";
load("nashorn:mozilla_compat.js");
importPackage(Packages.client);
importPackage(Packages.client.inventory);
importPackage(Packages.server);
importPackage(Packages.tools);
var 琴符 = "#fEffect/CharacterEff/1003252/0/0#";
var 音符 = "#fEffect/CharacterEff/1032063/0/0#";
var 花 = "#fUI/UIWindow.img/AriantMatch/characterIcon/0#";
var s = "#fUI/StatusBar/BtClaim/normal/0#";
var h = "#fUI/CashShop/CSEffect/effect/1#";
var 时间之石 = 4021010;
var status = 0;
var zones = 0;
var ItemId = Array(

		//双刀
		Array(2290153, 300, "双刀风暴20"),
		Array(2290154, 300, "龙卷风20"),
		Array(2290156, 300, "暗影飞跃斩20"),
		Array(2290158, 300, "怪物炸弹20"),
		//Array(22901, 300, ""),
		
		//战士
		//Array(2290002, 300, ""),
		Array(2290000, 300, "磁石"),
		Array(2290002, 300, "阿基里斯"),
		Array(2290004, 300, "突进"),
		Array(2290006, 300, "稳如泰山"),
		Array(2290008, 300, "进阶斗气"),
		Array(2290010, 300, "轻舞飞扬"),
		Array(2290012, 300, "连环环破"),
		Array(2290014, 300, "寒冰掌"),
		Array(2290016, 300, "葵花宝典"),
		Array(2290018, 300, "圣灵之剑"),
		Array(2290020, 300, "圣域"),
		Array(2290022, 300, "恶龙附身"),
		
		//魔法师
		Array(2290024, 300, "魔法反击"),
		Array(2290026, 300, "创世之破"),
		Array(2290028, 300, "终极无限"),
		Array(2290030, 300, "连环爆破"),
		Array(2290032, 300, "连环闪电"),
		Array(2290034, 300, "圣灵之盾"),
		Array(2290036, 300, "火凤球"),
		Array(2290038, 300, "冰破魔兽"),
		Array(2290040, 300, "天降落星"),
		Array(2290042, 300, "冰凤球"),
		Array(2290044, 300, "火魔兽"),
		Array(2290046, 300, "落霜冰破"),
		Array(2290048, 300, "圣光普照"),
		Array(2290050, 300, "光芒飞箭"),
		
		//弓箭手
		Array(2290052, 300, "火眼晶晶"),
		Array(2290054, 300, "飞龙冲击波"),
		Array(2290056, 300, "神箭手"),
		Array(2290058, 300, "击退箭"),
		Array(2290060, 300, "暴风箭雨"),
		Array(2290062, 300, "火凤凰"),
		Array(2290064, 300, "集中精力"),
		Array(2290066, 300, "神弩手"),
		Array(2290068, 300, "刺眼箭"),
		Array(2290070, 300, "穿透箭"),
		Array(2290072, 300, "冰破鹰"),
		Array(2290074, 300, "一击要害"),
		
		//飞侠
		Array(2290076, 300, "假动作"),
		Array(2290078, 300, "武器用毒液"),
		Array(2290080, 300, "挑畔"),
		Array(2290082, 300, "忍者伏击"),
		Array(2290084, 300, "三连环光击破"),
		Array(2290086, 300, "忍者冲击"),
		Array(2290088, 300, "暗器伤人"),
		Array(2290090, 300, "一出双击"),
		Array(2290092, 300, "暗杀"),
		Array(2290094, 300, "烟雾弹"),
		
		//Array(2290096, 300, "冒险岛勇士20"),
		
		//海盗
		Array(2290097, 300, "潜能出渊"),
		Array(2290099, 300, "超能量"),
		Array(2290101, 300, "超级变身"),
		Array(2290102, 300, "金手指"),
		Array(2290104, 300, "索命"),
		Array(2290106, 300, "光速拳"),
		Array(2290108, 300, "极速领域"),
		Array(2290110, 300, "伺机待发"),
		Array(2290112, 300, "属性强化"),
		Array(2290114, 300, "超级章鱼炮台"),
		Array(2290115, 300, "地毯式空袭"),
		Array(2290117, 300, "金属风暴"),
		Array(2290119, 300, "极速射"),
		Array(2290121, 300, "能量炮击"),
		Array(2290123, 300, "心灵控制"),
		Array(2290124, 300, "导航辅助"),
		//Array(2290125, 300, "冒险岛勇士30"),
		
		//战神
		Array(2290126, 300, "战神之舞"),
		Array(2290128, 300, "攻击策略"),
		Array(2290130, 300, "战神的意志"),
		Array(2290132, 300, "巨熊咆哮"),
		Array(2290134, 300, "防守策略"),
		Array(2290136, 300, "钻石星辰"),
		Array(2290138, 300, "战神之盾"),
		
		//龙神
		Array(2290140, 300, "魔龙分身"),
		Array(2290142, 300, "火焰轮"),
		Array(2290144, 300, "魔法精通"),
		Array(2290146, 300, "熊熊烈火"),
		Array(2290148, 300, "黑暗迷雾"),
		Array(2290150, 300, "灵魂之石"),
		Array(2290151, 300, "玛瑙的祝福")
		
		
		
		
		//Array(22901, 300, ""),
		//Array(22901, 300, ""),
		//Array(22901, 300, ""),



        //Array(1332247, 500, "旋涡匕首"),
		//Array(1302297, 500, "旋涡剑"),
		//Array(1322223, 500, "旋涡锤"),
		//Array(1342090, 500, "旋涡刀"),
		//Array(1382231, 500, "旋涡短杖"),
		//Array(1312173, 500, "旋涡斧"),
		//Array(1452226, 500, "旋涡弓"),
		//Array(1442242, 500, "旋涡戟"),
		//Array(1152160, 500, "旋涡剑"),
		//Array(1402220, 500, "旋涡双手剑"),
		//Array(1482189, 500, "漩涡冲拳"),
		//Array(1432187, 500, "漩涡矛"),
		//Array(1462213, 500, "漩涡弩"),
		//Array(1472235, 500, "漩涡拳甲"),
		//Array(1492199, 500, "漩涡手铳"),
		//Array(1122269, 200, "旋涡吊坠"),
		//Array(1032224, 200, "旋涡耳环"),
		//Array(1052669, 200, "旋涡皇家外套"),
		//Array(1102623, 200, "旋涡披风"),
		//Array(1082556, 200, "旋涡手套"),
		//Array(1012438, 200, "旋涡文身"),
		//Array(1072870, 200, "旋涡鞋"),
		//Array(1022211, 200, "旋涡眼睛"),
		//Array(1132247, 200, "旋涡腰带"),
		//Array(1003976, 200, "漩涡帽子")
        );
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
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            if (cm.getLevel < 10) {
                cm.sendOK("你的等级不足10级。。打开干嘛？", 2);
                cm.dispose();
            } else {
                selStr = "\t\t#e#k"+皇冠白+" 想要用荣誉兑换什么物品呢? "+皇冠白+"\r\n==============================================\r\n\t\t\t#d目前拥有荣誉数量:#r"+cm.getBeans()+"#n\r\n";
                for (var i = 0; i < ItemId.length; i++) {
                    selStr += "\r\n#L" + i + "##b#z" + ItemId[i][0] + "##k (需要#r " + ItemId[i][1] + " #k点荣誉值！)";
                }
                cm.sendSimple(selStr);
                zones == 0;
            }
        } else if (status == 1) {
            if (zones == 1) {
                cm.sendNext("你让我帮你做什么呢？", 2);
                zones = 2;
            } else if (zones == 0) {
				if (cm.getBeans() >= ItemId[selection][1]) { 
				var finalitem = Array();
				finalitem.push(ItemId[selection][1]);
				if (finalitem.length != 0) {
			var item;
            var random = new java.util.Random();
            var finalchance = random.nextInt(finalitem.length);
            var notice = finalitem[finalchance][3];
              item = cm.gainGachaponItem(ItemId[selection][0], 1, "荣誉兑换");
			if (item != -1) {
				cm.gainBeans(-(ItemId[selection][1]));
                     //   cm.gainGachaponItem(ItemId[selection][0], 1, "荣誉兑换", notice);
                        
						Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]成功用荣誉兑换了技能书！！")); 
						cm.sendOk("你兑换了1个 #b#t" + item + "");
                        cm.dispose();
					}
					else
					{
					cm.sendOk("请你确认在背包的装备，消耗，设置，其他，特殊窗口中是否有一格以上的空间。");
					cm.safeDispose();
					}
					cm.safeDispose();
					}
                    } else {
                        cm.sendOk("荣誉不足！");
                        cm.dispose();
                    }
                }
            } else if (status == 2) {
                if (zones == 2) {
                    cm.sendNext("我用于提升我魔法的时间之石被一群蘑菇妖偷走了,你能帮去抢回来吗？");
                    zones = 3;
                }
            } else if (status == 3) {
                if (zones == 3) {
                    cm.sendNext("行,这个没问题？你告诉我那些偷了你时间之石的蘑菇妖现在在什么地方呢?", 2);
                    zones = 4;
                }
            } else if (status == 4) {
                if (zones == 4) {
                    cm.sendNext("这个物品是#b全世界掉落#k的。你可以在世界上的#b任何一个怪物#k上获取！");
                    zones = 5;
                }
            } else if (status == 5) {
                if (zones == 5) {
                    cm.sendYesNo("如果你能帮我这个大忙的话,我会给你一些丰厚的奖励的，您是否愿意帮我？");
                }
            } else if (status == 6) {
                cm.setBossLog('MogoQuest');
                cm.gainItem(5220001, 1);
                cm.sendOk("非常感谢！获得后和我说话，就能换购物品了！");
                cm.dispose();
            }
    }
}	
