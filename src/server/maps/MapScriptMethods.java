/*
 This file is part of the OdinMS Maple Story Server
 Copyright (C) 2008 ~ 2010 Patrick Huy <patrick.huy@frz.cc> 
 Matthias Butz <matze@odinms.de>
 Jan Christian Meyer <vimes@odinms.de>

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License version 3
 as published by the Free Software Foundation. You may not use, modify
 or distribute this program under any other version of the
 GNU Affero General Public License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package server.maps;

import java.awt.Point;

import client.MapleClient;
import client.MapleQuestStatus;
import client.SkillFactory;
import scripting.EventManager;
import scripting.NPCScriptManager;
import server.Randomizer;
import server.MapleItemInformationProvider;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import server.life.OverrideMonsterStats;
import server.quest.MapleQuest;
import server.quest.MapleQuest.MedalQuest;
import tools.FilePrinter;

import tools.MaplePacketCreator;
import tools.packet.UIPacket;

public class MapScriptMethods {

    private static final Point witchTowerPos = new Point(-60, 184);
    private static final String[] mulungEffects = {
        "你的勇氣的挑戰睦隆道場是難能可貴的！",
        "如果你想品嚐失利的苦澀，進來吧！",
        "我会讓你後悔徹底挑戰睦隆道場！快點！"};

    private static enum onFirstUserEnter {

        dojang_Eff,
        PinkBeen_before,
        onRewordMap,
        StageMsg_together,
        StageMsg_davy,
        party6weatherMsg,
        StageMsg_juliet,
        StageMsg_romio,
        moonrabbit_mapEnter,
        astaroth_summon,
        boss_Ravana,
        killing_BonusSetting,
        killing_MapSetting,
        metro_firstSetting,
        balog_bonusSetting,
        balog_summon,
        easy_balog_summon,
        Sky_TrapFEnter,
        shammos_Fenter,
        PRaid_D_Fenter,
        PRaid_B_Fenter,
        GhostF,
        summon_pepeking,
        NULL;

        private static onFirstUserEnter fromString(String Str) {
            try {
                return valueOf(Str);
            } catch (IllegalArgumentException ex) {
                return NULL;
            }
        }
    };

    private static enum onUserEnter {

        babyPigMap,
        crash_Dragon,
        evanleaveD,
        getDragonEgg,
        meetWithDragon,
        go1010100,
        go1010200,
        go1010300,
        go1010400,
        evanPromotion,
        PromiseDragon,
        evanTogether,
        incubation_dragon,
        TD_MC_Openning,
        TD_MC_gasi,
        TD_MC_title,
        cygnusJobTutorial,
        cygnusTest,
        startEreb,
        dojang_Msg,
        dojang_1st,
        reundodraco,
        undomorphdarco,
        explorationPoint,
        goAdventure,
        go10000,
        go20000,
        go30000,
        go40000,
        go50000,
        go1000000,
        go1010000,
        go1020000,
        go2000000,
        goArcher,
        goPirate,
        goRogue,
        goMagician,
        goSwordman,
        goLith,
        iceCave,
        mirrorCave,
        aranDirection,
        rienArrow,
        rien,
        check_count,
        Massacre_first,
        Massacre_result,
        TD_MC_gasi2,
        aranTutorAlone,
        evanAlone,
        dojang_QcheckSet,
        Sky_StageEnter,
        outCase,
        balog_buff,
        balog_dateSet,
        Sky_BossEnter,
        Sky_GateMapEnter,
        shammos_Enter,
        shammos_Result,
        shammos_Base,
        dollCave00,
        dollCave01,
        Sky_Quest,
        enterBlackfrog,
        onSDI,
        blackSDI,
        summonIceWall,
        metro_firstSetting,
        start_itemTake,
        PRaid_D_Enter,
        PRaid_B_Enter,
        PRaid_Revive,
        PRaid_W_Enter,
        PRaid_WinEnter,
        PRaid_FailEnter,
        Ghost,
        TD_MC_keycheck,
        pepeking_effect,
        findvioleta,
        NULL;

        private static onUserEnter fromString(String Str) {
            try {
                return valueOf(Str);
            } catch (IllegalArgumentException ex) {
                return NULL;
            }
        }
    };

    public static void startScript_FirstUser(MapleClient c, String scriptName) {
        if (c.getPlayer() == null) {
            return;
        } //o_O
        if (c.getPlayer().hasGmLevel(5)) {
            c.getPlayer().dropMessage("[系統提示]您已經建立與地图腳本:" + scriptName + "的連接。(FirstUser)");
        }
        switch (onFirstUserEnter.fromString(scriptName)) {
            case dojang_Eff: {
                int temp = (c.getPlayer().getMapId() - 925000000) / 100;
                int stage = (int) (temp - ((temp / 100) * 100));

                sendDojoClock(c, getTiming(stage) * 60);
                sendDojoStart(c, stage - getDojoStageDec(stage));
                break;
            }
            case PinkBeen_before: {
                handlePinkBeanStart(c);
                break;
            }
            case onRewordMap: {
                reloadWitchTower(c);
                break;
            }
            case GhostF: {
                c.getPlayer().getMap().startMapEffect("這個地图感覺陰森森的..有種莫名的奇怪感覺..", 5120025);
                break;
            }
            //5120018 = ludi(none), 5120019 = orbis(start_itemTake - onUser)
            case moonrabbit_mapEnter: {
                c.getPlayer().getMap().startMapEffect("粥環繞月球的月見草種子和保護月球兔子！", 5120016);
                break;
            }
            case StageMsg_together: {
                switch (c.getPlayer().getMapId()) {
                    case 103000800:
                        break;
                    case 103000801:
                        break;
                    case 103000802:
                        break;
                    case 103000803:
                        break;
                    case 103000804:
                        c.getPlayer().getMap().startMapEffect("請打敗超級綠水靈!", 5120017);
                        break;
                }
                break;
            }
            case StageMsg_romio: {
                switch (c.getPlayer().getMapId()) {
                    case 926100000:
                        c.getPlayer().getMap().startMapEffect("通過調查實驗室，請找到隱藏的門!", 5120021);
                        break;
                    case 926100001:
                        c.getPlayer().getMap().startMapEffect("通過這個黑暗中找到自己的方式!", 5120021);
                        break;
                    case 926100100:
                        c.getPlayer().getMap().startMapEffect("把燒杯填滿!", 5120021);
                        break;
                    case 926100200:
                        c.getPlayer().getMap().startMapEffect("通過每個門获得用於實驗中的文件!", 5120021);
                        break;
                    case 926100203:
                        c.getPlayer().getMap().startMapEffect("請打敗所有怪物!", 5120021);
                        break;
                    case 926100300:
                        c.getPlayer().getMap().startMapEffect("通過實驗找到自己的方式!", 5120021);
                        break;
                    case 926100401:
                        c.getPlayer().getMap().startMapEffect("請保護我的愛人!", 5120021);

                        break;
                }
                break;
            }
            case StageMsg_juliet: {
                switch (c.getPlayer().getMapId()) {
                    case 926110000:
                        c.getPlayer().getMap().startMapEffect("通過調查實驗室，請找到隱藏的門!", 5120022);
                        break;
                    case 926110001:
                        c.getPlayer().getMap().startMapEffect("通過這個黑暗中找到自己的方式!", 5120022);
                        break;
                    case 926110100:
                        c.getPlayer().getMap().startMapEffect("把燒杯填滿!", 5120022);
                        break;
                    case 926110200:
                        c.getPlayer().getMap().startMapEffect("通過每個門获得用於實驗中的文件!", 5120022);
                        break;
                    case 926110203:
                        c.getPlayer().getMap().startMapEffect("請打敗所有怪物!", 5120022);
                        break;
                    case 926110300:
                        c.getPlayer().getMap().startMapEffect("通過實驗找到自己的方式!", 5120022);
                        break;
                    case 926110401:
                        c.getPlayer().getMap().startMapEffect("請保護我的愛人!", 5120022);
                        break;
                }
                break;
            }
            case party6weatherMsg: {
                switch (c.getPlayer().getMapId()) {
                    case 930000000:
                        c.getPlayer().getMap().startMapEffect("進入傳送點，我要對你們施放變身魔法了！", 5120023);
                        break;
                    case 930000100:
                        c.getPlayer().getMap().startMapEffect("消滅所有怪物！", 5120023);
                        break;
                    case 930000200:
                        c.getPlayer().getMap().startMapEffect("對荊棘施放稀釋的毒液4個！", 5120023);
                        break;
                    case 930000300:
                        c.getPlayer().getMap().startMapEffect("媽媽你在哪裡嗚嗚 哭哭我迷路了", 5120023);
                        break;
                    case 930000400:
                        c.getPlayer().getMap().startMapEffect("找我對話拿淨化之珠其中一個队員集滿10個怪物株給我！", 5120023);
                        break;
                    case 930000500:
                        c.getPlayer().getMap().startMapEffect("從怪人書桌中尋找紫色魔力石！", 5120023);
                        break;
                    case 930000600:
                        c.getPlayer().getMap().startMapEffect("將紫色魔力石放在祭壇上！", 5120023);
                        break;
                }
                break;
            }
            case StageMsg_davy: {
                switch (c.getPlayer().getMapId()) {
                    case 925100000:
                        c.getPlayer().getMap().startMapEffect("打倒船上的所有怪物!", 5120020);
                        break;
                    case 925100100:
                        c.getPlayer().getMap().startMapEffect("我們必須證明自己！請給我海盜獎牌!", 5120020);
                        break;
                    case 925100200:
                        c.getPlayer().getMap().startMapEffect("打倒船上所有的怪物!", 5120020);
                        break;
                    case 925100300:
                        c.getPlayer().getMap().startMapEffect("打倒船上所有的怪物!", 5120020);
                        break;
                    case 925100400:
                        c.getPlayer().getMap().startMapEffect("打敗怪物然後把鑰匙放入門縫裡!", 5120020);
                        break;
                    case 925100500:
                        c.getPlayer().getMap().startMapEffect("打倒這隻BOSS即可過關。", 5120020);
                        break;
                }
                final EventManager em = c.getChannelServer().getEventSM().getEventManager("Pirate");
                if (c.getPlayer().getMapId() == 925100500 && em != null && em.getProperty("stage5") != null) {
                    int mobId = Randomizer.nextBoolean() ? 9300119 : 9300119; //lord pirate
                    final int st = Integer.parseInt(em.getProperty("stage5"));
                    switch (st) {
                        case 1:
                            mobId = /*Randomizer.nextBoolean() ? 9300119 : */ 9300105; //angry
                            break;
                        case 2:
                            mobId = /*Randomizer.nextBoolean() ? */ 9300106/* : 9300105*/; //enraged
                            break;
                    }
                    final MapleMonster shammos = MapleLifeFactory.getMonster(mobId);
                    if (c.getPlayer().getEventInstance() != null) {
                        c.getPlayer().getEventInstance().registerMonster(shammos);
                    }
                    c.getPlayer().getMap().spawnMonsterOnGroundBelow(shammos, new Point(411, 236));
                }
                break;
            }
            case astaroth_summon: {
                c.getPlayer().getMap().resetFully();
                c.getPlayer().getMap().spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(9400633), new Point(600, -26)); //rough estimate
                break;
            }
            case boss_Ravana: { //event handles this so nothing for now until i find out something to do with it
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.serverNotice(5, "Ravana has appeared!"));
                break;
            }
            case killing_BonusSetting: { //spawns monsters according to mapid
                //910320010-910320029 = Train 999 bubblings.
                //926010010-926010029 = 30 Yetis
                //926010030-926010049 = 35 Yetis
                //926010050-926010069 = 40 Yetis
                //926010070-926010089 - 50 Yetis (specialized? immortality)
                //TODO also find positions to spawn these at
                c.getPlayer().getMap().resetFully();
                c.sendPacket(MaplePacketCreator.showEffect("killing/bonus/bonus"));
                c.sendPacket(MaplePacketCreator.showEffect("killing/bonus/stage"));
                Point pos1 = null, pos2 = null, pos3 = null;
                int spawnPer = 0;
                int mobId = 0;
                //9700019, 9700029
                //9700021 = one thats invincible
                if (c.getPlayer().getMapId() >= 910320010 && c.getPlayer().getMapId() <= 910320029) {
                    pos1 = new Point(121, 218);
                    pos2 = new Point(396, 43);
                    pos3 = new Point(-63, 43);
                    mobId = 9700020;
                    spawnPer = 10;
                } else if (c.getPlayer().getMapId() >= 926010010 && c.getPlayer().getMapId() <= 926010029) {
                    pos1 = new Point(0, 88);
                    pos2 = new Point(-326, -115);
                    pos3 = new Point(361, -115);
                    mobId = 9700019;
                    spawnPer = 10;
                } else if (c.getPlayer().getMapId() >= 926010030 && c.getPlayer().getMapId() <= 926010049) {
                    pos1 = new Point(0, 88);
                    pos2 = new Point(-326, -115);
                    pos3 = new Point(361, -115);
                    mobId = 9700019;
                    spawnPer = 15;
                } else if (c.getPlayer().getMapId() >= 926010050 && c.getPlayer().getMapId() <= 926010069) {
                    pos1 = new Point(0, 88);
                    pos2 = new Point(-326, -115);
                    pos3 = new Point(361, -115);
                    mobId = 9700019;
                    spawnPer = 20;
                } else if (c.getPlayer().getMapId() >= 926010070 && c.getPlayer().getMapId() <= 926010089) {
                    pos1 = new Point(0, 88);
                    pos2 = new Point(-326, -115);
                    pos3 = new Point(361, -115);
                    mobId = 9700019;//9700029;
                    spawnPer = 20;
                } else {
                    break;
                }
                for (int i = 0; i < spawnPer; i++) {
                    c.getPlayer().getMap().spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(mobId), new Point(pos1));
                    c.getPlayer().getMap().spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(mobId), new Point(pos2));
                    c.getPlayer().getMap().spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(mobId), new Point(pos3));
                }
                c.getPlayer().startMapTimeLimitTask(120, c.getPlayer().getMap().getReturnMap());
                break;
            }
            case shammos_Fenter: {
                if (c.getPlayer().getMapId() >= 921120100 && c.getPlayer().getMapId() < 921120500) {
                    final MapleMonster shammos = MapleLifeFactory.getMonster(9300275);
                    if (c.getPlayer().getEventInstance() != null) {
                        c.getPlayer().getEventInstance().registerMonster(shammos);
                        if (c.getPlayer().getEventInstance().getProperty("HP") != null) {
                            shammos.setHp(Long.parseLong(c.getPlayer().getEventInstance().getProperty("HP")));
                        } else {
                            c.getPlayer().getEventInstance().setProperty("HP", "50000");
                        }
                    }
                    c.getPlayer().getMap().spawnMonsterWithEffectBelow(shammos, new Point(c.getPlayer().getMap().getPortal(0).getPosition()), 12);
                    shammos.switchController(c.getPlayer(), false);
                    c.sendPacket(MaplePacketCreator.getNodeProperties(shammos, c.getPlayer().getMap()));

                }
                break;
            }
            case PRaid_D_Fenter: {
                switch (c.getPlayer().getMapId() % 10) {
                    case 0:
                        c.getPlayer().getMap().startMapEffect("Eliminate all the monsters!", 5120033);
                        break;
                    case 1:
                        c.getPlayer().getMap().startMapEffect("Break the boxes and eliminate the monsters!", 5120033);
                        break;
                    case 2:
                        c.getPlayer().getMap().startMapEffect("Eliminate the Officer!", 5120033);
                        break;
                    case 3:
                        c.getPlayer().getMap().startMapEffect("Eliminate all the monsters!", 5120033);
                        break;
                    case 4:
                        c.getPlayer().getMap().startMapEffect("Find the way to the other side!", 5120033);
                        break;
                }
                break;
            }
            case PRaid_B_Fenter: {
                c.getPlayer().getMap().startMapEffect("Defeat the Ghost Ship Captain!", 5120033);
                break;
            }
            case balog_summon:
            case easy_balog_summon: { //we dont want to reset
                break;
            }
            case metro_firstSetting:
            case killing_MapSetting:
            case Sky_TrapFEnter:
            case balog_bonusSetting: { //not needed
                c.getPlayer().getMap().resetFully();
                break;
            }
            case summon_pepeking: {
                c.getPlayer().getMap().resetFully();
                final int rand = Randomizer.nextInt(10);
                int mob_ToSpawn = 100100;
                if (rand >= 4) { //60%
                    mob_ToSpawn = 3300007;
                } else if (rand >= 1) {
                    mob_ToSpawn = 3300006;
                } else {
                    mob_ToSpawn = 3300005;
                }
                c.getPlayer().getMap().spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(mob_ToSpawn), c.getPlayer().getPosition());
                break;
            }
            default: {
                System.err.println("未處理的腳本 : " + scriptName + ", 型態 : onFirstUserEnter - 地图ID " + c.getPlayer().getMapId());
                FilePrinter.printError("MapScriptMethods.txt", "未處理的腳本 : " + scriptName + ", 型態 : onUserEnter - 地图ID " + c.getPlayer().getMapId());
                break;
            }
        }
    }

    public static void startScript_User(MapleClient c, String scriptName) {
        if (c.getPlayer() == null) {
            return;
        } //o_O
        if (c.getPlayer().hasGmLevel(5)) {
            c.getPlayer().dropMessage("[系統提示]您已經建立與地图腳本:" + scriptName + "的連接。(User)");
        }
        String data = "";
        switch (onUserEnter.fromString(scriptName)) {
            case cygnusTest:
            case cygnusJobTutorial: {
                showIntro(c, "Effect/Direction.img/cygnusJobTutorial/Scene" + (c.getPlayer().getMapId() - 913040100));
                break;
            }
            case shammos_Enter: { //nothing to go on inside the map
                c.sendPacket(MaplePacketCreator.sendPyramidEnergy("shammos_LastStage", String.valueOf((c.getPlayer().getMapId() % 1000) / 100)));
                if (c.getPlayer().getEventInstance() != null && c.getPlayer().getMapId() == 921120500) {
                    NPCScriptManager.getInstance().dispose(c); //only boss map.
                    NPCScriptManager.getInstance().start(c, 2022006);
                }
                break;
            }
            case start_itemTake: { //nothing to go on inside the map
                final EventManager em = c.getChannelServer().getEventSM().getEventManager("OrbisPQ");
                if (em != null && em.getProperty("pre").equals("0")) {
                    NPCScriptManager.getInstance().dispose(c);
                    NPCScriptManager.getInstance().start(c, 2013001);
                }
                break;
            }
            case PRaid_W_Enter: {
                c.sendPacket(MaplePacketCreator.sendPyramidEnergy("PRaid_expPenalty", "0"));
                c.sendPacket(MaplePacketCreator.sendPyramidEnergy("PRaid_ElapssedTimeAtField", "0"));
                c.sendPacket(MaplePacketCreator.sendPyramidEnergy("PRaid_Point", "-1"));
                c.sendPacket(MaplePacketCreator.sendPyramidEnergy("PRaid_Bonus", "-1"));
                c.sendPacket(MaplePacketCreator.sendPyramidEnergy("PRaid_Total", "-1"));
                c.sendPacket(MaplePacketCreator.sendPyramidEnergy("PRaid_Team", ""));
                c.sendPacket(MaplePacketCreator.sendPyramidEnergy("PRaid_IsRevive", "0"));
                c.getPlayer().writePoint("PRaid_Point", "-1");
                c.getPlayer().writeStatus("Red_Stage", "1");
                c.getPlayer().writeStatus("Blue_Stage", "1");
                c.getPlayer().writeStatus("redTeamDamage", "0");
                c.getPlayer().writeStatus("blueTeamDamage", "0");
                break;
            }
            case Ghost: {
                c.getPlayer().getMap().startMapEffect("這個地图感覺陰森森的..有種莫名的奇怪感覺..", 5120025);
                break;
            }
            case PRaid_D_Enter:
            case PRaid_B_Enter:
            case PRaid_WinEnter: //handled by event
            case PRaid_FailEnter: //also
            case PRaid_Revive: //likely to subtract points or remove a life, but idc rly
            case metro_firstSetting:
            case blackSDI:
            case summonIceWall:
            case onSDI:
            case enterBlackfrog:
            case Sky_Quest: //forest that disappeared 240030102
            case dollCave00:
            case dollCave01:
            case shammos_Base:
            case shammos_Result:
            case Sky_BossEnter:
            case Sky_GateMapEnter:
            case balog_dateSet:
            case balog_buff:
            case outCase:
            case Sky_StageEnter:
            case dojang_QcheckSet:
            case evanTogether:
            case aranTutorAlone:
            case evanAlone: { //no idea
                c.sendPacket(MaplePacketCreator.enableActions());
                break;
            }
            case startEreb:
            case mirrorCave:
            case babyPigMap:
            case evanleaveD: {
                c.sendPacket(UIPacket.IntroDisableUI(false));
                c.sendPacket(UIPacket.IntroLock(false));
                c.sendPacket(MaplePacketCreator.enableActions());
                break;
            }
            case dojang_Msg: {
                c.getPlayer().getMap().startMapEffect(mulungEffects[Randomizer.nextInt(mulungEffects.length)], 5120024);
                break;
            }
            case dojang_1st: {
                c.getPlayer().writeMulungEnergy();
                break;
            }
            case undomorphdarco:
            case reundodraco: {
                c.getPlayer().cancelEffect(MapleItemInformationProvider.getInstance().getItemEffect(2210016), false, -1);
                break;
            }
            case goAdventure: {
                // BUG in MSEA v.91, so let's skip this part.
                showIntro(c, "Effect/Direction3.img/goAdventure/Scene" + (c.getPlayer().getGender() == 0 ? "0" : "1"));
                break;
            }
            case crash_Dragon:
                showIntro(c, "Effect/Direction4.img/crash/Scene" + (c.getPlayer().getGender() == 0 ? "0" : "1"));
                break;
            case getDragonEgg:
                showIntro(c, "Effect/Direction4.img/getDragonEgg/Scene" + (c.getPlayer().getGender() == 0 ? "0" : "1"));
                break;
            case meetWithDragon:
                showIntro(c, "Effect/Direction4.img/meetWithDragon/Scene" + (c.getPlayer().getGender() == 0 ? "0" : "1"));
                break;
            case PromiseDragon:
                showIntro(c, "Effect/Direction4.img/PromiseDragon/Scene" + (c.getPlayer().getGender() == 0 ? "0" : "1"));
                break;
            case evanPromotion:
                switch (c.getPlayer().getMapId()) {
                    case 900090000:
                        data = "Effect/Direction4.img/promotion/Scene0" + (c.getPlayer().getGender() == 0 ? "0" : "1");
                        break;
                    case 900090001:
                        data = "Effect/Direction4.img/promotion/Scene1";
                        break;
                    case 900090002:
                        data = "Effect/Direction4.img/promotion/Scene2" + (c.getPlayer().getGender() == 0 ? "0" : "1");
                        break;
                    case 900090003:
                        data = "Effect/Direction4.img/promotion/Scene3";
                        break;
                    case 900090004:
                        c.sendPacket(UIPacket.IntroDisableUI(false));
                        c.sendPacket(UIPacket.IntroLock(false));
                        c.sendPacket(MaplePacketCreator.enableActions());
                        final MapleMap mapto = c.getChannelServer().getMapFactory().getMap(900010000);
                        c.getPlayer().changeMap(mapto, mapto.getPortal(0));
                        return;
                }
                showIntro(c, data);
                break;
            case TD_MC_title: {
                c.sendPacket(UIPacket.IntroDisableUI(false));
                c.sendPacket(UIPacket.IntroLock(false));
                c.sendPacket(MaplePacketCreator.enableActions());
                c.sendPacket(UIPacket.MapEff("temaD/enter/mushCatle"));
                break;
            }
            case explorationPoint: {
                if (c.getPlayer().getMapId() == 104000000) {
                    c.sendPacket(UIPacket.IntroDisableUI(false));
                    c.sendPacket(UIPacket.IntroLock(false));
                    c.sendPacket(MaplePacketCreator.enableActions());
                    c.sendPacket(UIPacket.MapNameDisplay(c.getPlayer().getMapId()));
                }
                MedalQuest m = null;
                for (MedalQuest mq : MedalQuest.values()) {
                    for (int i : mq.maps) {
                        if (c.getPlayer().getMapId() == i) {
                            m = mq;
                            break;
                        }
                    }
                }
                if (m != null && c.getPlayer().getLevel() >= m.level && c.getPlayer().getQuestStatus(m.questid) != 2) {
                    if (c.getPlayer().getQuestStatus(m.lquestid) != 1) {
                        MapleQuest.getInstance(m.lquestid).forceStart(c.getPlayer(), 0, "0");
                    }
                    if (c.getPlayer().getQuestStatus(m.questid) != 1) {
                        MapleQuest.getInstance(m.questid).forceStart(c.getPlayer(), 0, null);
                        final StringBuilder sb = new StringBuilder("enter=");
                        for (int i = 0; i < m.maps.length; i++) {
                            sb.append("0");
                        }
                        c.getPlayer().updateInfoQuest(m.questid - 2005, sb.toString());
                        MapleQuest.getInstance(m.questid - 1995).forceStart(c.getPlayer(), 0, "0");
                    }
                    final String quest = c.getPlayer().getInfoQuest(m.questid - 2005);
                    final MapleQuestStatus stat = c.getPlayer().getQuestNAdd(MapleQuest.getInstance(m.questid - 1995));
                    if (stat.getCustomData() == null) { //just a check.
                        stat.setCustomData("0");
                    }
                    int number = Integer.parseInt(stat.getCustomData());
                    final StringBuilder sb = new StringBuilder("enter=");
                    boolean changedd = false;
                    for (int i = 0; i < m.maps.length; i++) {
                        try {
                            boolean changed = false;
                            if (c.getPlayer().getMapId() == m.maps[i]) {
                                if (!quest.trim().equals("") && quest.substring(i + 6, i + 7).equals("0")) {
                                    sb.append("1");
                                    changed = true;
                                    changedd = true;
                                }
                            }
                            if (!quest.trim().equals("") && !changed) {
                                sb.append(quest.substring(i + 6, i + 7));
                            }
                        } catch (Exception e) {
                            FilePrinter.printError("MSMethod Length Error", e, quest);
                        }
                    }
                    if (changedd) {
                        number++;
                        c.getPlayer().updateInfoQuest(m.questid - 2005, sb.toString());
                        MapleQuest.getInstance(m.questid - 1995).forceStart(c.getPlayer(), 0, String.valueOf(number));
                        c.getPlayer().dropMessage(-1, "訪問 " + number + "/" + m.maps.length + " 個地區.");
                        c.getPlayer().dropMessage(-1, "稱號 " + String.valueOf(m) + " 已完成了");
                        c.sendPacket(MaplePacketCreator.showQuestMsg("稱號 " + String.valueOf(m) + " 已完成訪問 " + number + "/" + m.maps.length + " 個地區"));
                    }
                }
                break;
            }
            case go10000:
            case go1020000:
                c.sendPacket(UIPacket.IntroDisableUI(false));
                c.sendPacket(UIPacket.IntroLock(false));
                c.sendPacket(MaplePacketCreator.enableActions());
            case go20000:
            case go30000:
            case go40000:
            case go50000:
            case go1000000:
            case go2000000:
            case go1010000:
            case go1010100:
            case go1010200:
            case go1010300:
            case go1010400: {
                c.sendPacket(UIPacket.MapNameDisplay(c.getPlayer().getMapId()));
                break;
            }
            case goArcher: {
                showIntro(c, "Effect/Direction3.img/archer/Scene" + (c.getPlayer().getGender() == 0 ? "0" : "1"));
                break;
            }
            case goPirate: {
                showIntro(c, "Effect/Direction3.img/pirate/Scene" + (c.getPlayer().getGender() == 0 ? "0" : "1"));
                break;
            }
            case goRogue: {
                showIntro(c, "Effect/Direction3.img/rogue/Scene" + (c.getPlayer().getGender() == 0 ? "0" : "1"));
                break;
            }
            case goMagician: {
                showIntro(c, "Effect/Direction3.img/magician/Scene" + (c.getPlayer().getGender() == 0 ? "0" : "1"));
                break;
            }
            case goSwordman: {
                showIntro(c, "Effect/Direction3.img/swordman/Scene" + (c.getPlayer().getGender() == 0 ? "0" : "1"));
                break;
            }
            case goLith: {
                showIntro(c, "Effect/Direction3.img/goLith/Scene" + (c.getPlayer().getGender() == 0 ? "0" : "1"));
                break;
            }
            case TD_MC_Openning: {
                showIntro(c, "Effect/Direction2.img/open");
                break;
            }
            case TD_MC_gasi: {
                showIntro(c, "Effect/Direction2.img/gasi");
                break;
            }
            case TD_MC_gasi2: {
                break;
            }
            case aranDirection: {
                switch (c.getPlayer().getMapId()) {
                    case 914090010:
                        data = "Effect/Direction1.img/aranTutorial/Scene0";
                        break;
                    case 914090011:
                        data = "Effect/Direction1.img/aranTutorial/Scene1" + (c.getPlayer().getGender() == 0 ? "0" : "1");
                        break;
                    case 914090012:
                        data = "Effect/Direction1.img/aranTutorial/Scene2" + (c.getPlayer().getGender() == 0 ? "0" : "1");
                        break;
                    case 914090013:
                        data = "Effect/Direction1.img/aranTutorial/Scene3";
                        break;
                    case 914090100:
                        data = "Effect/Direction1.img/aranTutorial/HandedPoleArm" + (c.getPlayer().getGender() == 0 ? "0" : "1");
                        break;
                    case 914090200:
                        data = "Effect/Direction1.img/aranTutorial/Maha";
                        break;
                }
                showIntro(c, data);
                break;
            }
            case iceCave: {
                c.getPlayer().changeSkillLevel(SkillFactory.getSkill(20000014), (byte) -1, (byte) 0, -1);
                c.getPlayer().changeSkillLevel(SkillFactory.getSkill(20000015), (byte) -1, (byte) 0, -1);
                c.getPlayer().changeSkillLevel(SkillFactory.getSkill(20000016), (byte) -1, (byte) 0, -1);
                c.getPlayer().changeSkillLevel(SkillFactory.getSkill(20000017), (byte) -1, (byte) 0, -1);
                c.getPlayer().changeSkillLevel(SkillFactory.getSkill(20000018), (byte) -1, (byte) 0, -1);
                c.sendPacket(UIPacket.ShowWZEffect("Effect/Direction1.img/aranTutorial/ClickLirin"));
                c.sendPacket(UIPacket.IntroDisableUI(false));
                c.sendPacket(UIPacket.IntroLock(false));
                c.sendPacket(MaplePacketCreator.enableActions());

                break;
            }
            case rienArrow: {
                if (c.getPlayer().getInfoQuest(21019).equals("miss=o;helper=clear")) {
                    c.getPlayer().updateInfoQuest(21019, "miss=o;arr=o;helper=clear");
                    c.sendPacket(UIPacket.AranTutInstructionalBalloon("Effect/OnUserEff.img/guideEffect/aranTutorial/tutorialArrow3"));
                }
                break;
            }
            case rien: {
                if (c.getPlayer().getQuestStatus(21101) == 2 && c.getPlayer().getInfoQuest(21019).equals("miss=o;arr=o;helper=clear")) {
                    c.getPlayer().updateInfoQuest(21019, "miss=o;arr=o;ck=1;helper=clear");
                }
                c.sendPacket(UIPacket.IntroDisableUI(false));
                c.sendPacket(UIPacket.IntroLock(false));
                break;
            }
            case check_count: {
                if (c.getPlayer().getMapId() == 950101010 && (!c.getPlayer().haveItem(4001433, 20) || c.getPlayer().getLevel() < 50)) { //ravana Map
                    final MapleMap mapp = c.getChannelServer().getMapFactory().getMap(950101100); //exit Map
                    c.getPlayer().changeMap(mapp, mapp.getPortal(0));
                }
                break;
            }
            case Massacre_first: { //sends a whole bunch of shit.
                if (c.getPlayer().getPyramidSubway() == null) {
                    c.getPlayer().setPyramidSubway(new Event_PyramidSubway(c.getPlayer()));
                }
                break;
            }
            case Massacre_result: { //clear, give exp, etc.
                //if (c.getPlayer().getPyramidSubway() == null) {
                c.sendPacket(MaplePacketCreator.showEffect("killing/fail"));
                //} else {
                //	c.sendPacket(MaplePacketCreator.showEffect("killing/clear"));
                //}
                //left blank because pyramidsubway handles this.
                break;
            }
            case TD_MC_keycheck: {
                c.sendPacket(MaplePacketCreator.enableActions());
                break;
            }
            case pepeking_effect: {
                c.sendPacket(MaplePacketCreator.showEffect("pepeKing/frame/W"));
                if (c.getPlayer().getMap().getAllMonster().isEmpty()) {
                    int rand = Randomizer.rand(0, 2);
                    MapleMonster mob = MapleLifeFactory.getMonster(3300005 + rand);
                    OverrideMonsterStats oms = new OverrideMonsterStats();
                    oms.setOExp(7110);
                    oms.setOHp(mob.getMobMaxHp());
                    oms.setOMp(mob.getMobMaxMp());
                    mob.setOverrideStats(oms);
                    c.getPlayer().getMap().spawnMonsterOnGroundBelow(mob, new Point(358, -68));
                    if (rand == 0) {
                        c.sendPacket(MaplePacketCreator.showEffect("pepeKing/pepe/pepeB"));
                    } else if (rand == 1) {
                        c.sendPacket(MaplePacketCreator.showEffect("pepeKing/pepe/pepeG"));
                    } else if (rand == 2) {
                        c.sendPacket(MaplePacketCreator.showEffect("pepeKing/pepe/pepeW"));
                    }
                } else {
                    c.sendPacket(MaplePacketCreator.showEffect("pepeKing/pepe/pepeB"));
                }
                c.sendPacket(MaplePacketCreator.showEffect("pepeKing/chat/nugu"));
                c.sendPacket(MaplePacketCreator.showEffect("pepeKing/frame/B"));
                break;
            }
            case findvioleta: {
                c.getPlayer().getMap().resetFully();
                break;
            }

            default: {
                System.err.println("未處理的腳本 : " + scriptName + ", 型態 : onUserEnter - 地图ID " + c.getPlayer().getMapId());
                FilePrinter.printError("MapScriptMethods.txt", "未處理的腳本 : " + scriptName + ", 型態 : onUserEnter - 地图ID " + c.getPlayer().getMapId());
                break;
            }
        }
    }

    private static final int getTiming(int ids) {
        if (ids <= 5) {
            return 5;
        } else if (ids >= 7 && ids <= 11) {
            return 6;
        } else if (ids >= 13 && ids <= 17) {
            return 7;
        } else if (ids >= 19 && ids <= 23) {
            return 8;
        } else if (ids >= 25 && ids <= 29) {
            return 9;
        } else if (ids >= 31 && ids <= 35) {
            return 10;
        } else if (ids >= 37 && ids <= 38) {
            return 15;
        }
        return 0;
    }

    private static final int getDojoStageDec(int ids) {
        if (ids <= 5) {
            return 0;
        } else if (ids >= 7 && ids <= 11) {
            return 1;
        } else if (ids >= 13 && ids <= 17) {
            return 2;
        } else if (ids >= 19 && ids <= 23) {
            return 3;
        } else if (ids >= 25 && ids <= 29) {
            return 4;
        } else if (ids >= 31 && ids <= 35) {
            return 5;
        } else if (ids >= 37 && ids <= 38) {
            return 6;
        }
        return 0;
    }

    private static void showIntro(final MapleClient c, final String data) {
        c.sendPacket(UIPacket.IntroDisableUI(true));
        c.sendPacket(UIPacket.IntroLock(true));
        c.sendPacket(UIPacket.ShowWZEffect(data));
    }

    private static void sendDojoClock(MapleClient c, int time) {
        c.sendPacket(MaplePacketCreator.getClock(time));
    }

    private static void sendDojoStart(MapleClient c, int stage) {
        c.sendPacket(MaplePacketCreator.environmentChange("Dojang/start", 4));
        c.sendPacket(MaplePacketCreator.environmentChange("dojang/start/stage", 3));
        c.sendPacket(MaplePacketCreator.environmentChange("dojang/start/number/" + stage, 3));
        c.sendPacket(MaplePacketCreator.trembleEffect(0, 1));
    }

    private static void handlePinkBeanStart(MapleClient c) {
        final MapleMap map = c.getPlayer().getMap();
        map.resetFully();

        if (!map.containsNPC(2141000)) {
            map.spawnNpc(2141000, new Point(-190, -42));
        }
    }

    private static void reloadWitchTower(MapleClient c) {
        final MapleMap map = c.getPlayer().getMap();
        map.killAllMonsters(false);

        final int level = c.getPlayer().getLevel();
        int mob;
        if (level <= 10) {
            mob = 9300367;
        } else if (level <= 20) {
            mob = 9300368;
        } else if (level <= 30) {
            mob = 9300369;
        } else if (level <= 40) {
            mob = 9300370;
        } else if (level <= 50) {
            mob = 9300371;
        } else if (level <= 60) {
            mob = 9300372;
        } else if (level <= 70) {
            mob = 9300373;
        } else if (level <= 80) {
            mob = 9300374;
        } else if (level <= 90) {
            mob = 9300375;
        } else if (level <= 100) {
            mob = 9300376;
        } else {
            mob = 9300377;
        }
        map.spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(mob), witchTowerPos);
    }
}
