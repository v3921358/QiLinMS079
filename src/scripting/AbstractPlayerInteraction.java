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
package scripting;

import java.awt.Point;
import java.util.List;

import client.inventory.Equip;
import client.SkillFactory;
import constants.GameConstants;
import client.ISkill;
import client.MapleCharacter;
import client.MapleClient;
import client.inventory.MapleInventoryType;
import client.inventory.MaplePet;
import client.MapleQuestStatus;
import client.MapleStat;
import client.inventory.IItem;
import client.inventory.Item;
import client.inventory.MapleInventory;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import handling.world.MaplePartyCharacter;
import handling.world.guild.MapleGuild;
import server.Randomizer;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.maps.MapleMap;
import server.maps.MapleReactor;
import server.maps.MapleMapObject;
import server.maps.SavedLocationType;
import server.maps.Event_DojoAgent;
import server.life.MapleMonster;
import server.life.MapleLifeFactory;
import server.quest.MapleQuest;
import tools.MaplePacketCreator;
import tools.packet.PetPacket;
import tools.packet.UIPacket;
import client.inventory.MapleInventoryIdentifier;
import client.messages.CommandProcessor;
import constants.ItemConstants;
import constants.ItemConstants.類型;
import constants.ServerConfig;
import constants.ServerConstants;
import database.DatabaseConnection;
import handling.channel.handler.InterServerHandler;
import handling.world.World;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.Map;
import server.custom.bankitem.BankItem;
import server.custom.bankitem.BankItemManager;
import server.custom.bankitem1.BankItem1;
import server.custom.bankitem1.BankItemManager1;
import server.custom.bankitem2.BankItem2;
import server.custom.bankitem2.BankItemManager2;
import server.custom.bossrank.BossRankInfo;
import server.custom.bossrank.BossRankManager;
import server.custom.bossrank1.BossRankInfo1;
import server.custom.bossrank1.BossRankManager1;
import server.custom.bossrank2.BossRankInfo2;
import server.custom.bossrank2.BossRankManager2;
import server.custom.bossrank3.BossRankInfo3;
import server.custom.bossrank3.BossRankManager3;
import server.custom.bossrank4.BossRankInfo4;
import server.custom.bossrank4.BossRankManager4;
import server.custom.bossrank5.BossRankInfo5;
import server.custom.bossrank5.BossRankManager5;
import server.custom.bossrank6.BossRankInfo6;
import server.custom.bossrank6.BossRankManager6;
import server.custom.bossrank7.BossRankInfo7;
import server.custom.bossrank7.BossRankManager7;
import server.custom.bossrank8.BossRankInfo8;
import server.custom.bossrank8.BossRankManager8;
import server.custom.bossrank9.BossRankInfo9;
import server.custom.bossrank9.BossRankManager9;
import server.custom.bossrank10.BossRankInfo10;
import server.custom.bossrank10.BossRankManager10;
import server.RandomRewards;
import server.events.MapleEvent;
import server.events.MapleEventType;
import server.maps.MapleMapFactory;
import tools.FileoutputUtil;
import tools.Pair;

public abstract class AbstractPlayerInteraction {

    private MapleClient c;

    public AbstractPlayerInteraction(final MapleClient c) {
        this.c = c;
    }

    public final MapleClient getClient() {
        return c;
    }

    public final MapleClient getC() {
        return c;
    }

    public MapleCharacter getChar() {
        return getClient().getPlayer();
    }

    public int getOneTimeLog(String bossid) {
        return getPlayer().getOneTimeLog(bossid);
    }

    public void setOneTimeLog(String bossid) {
        getPlayer().setOneTimeLog(bossid);
    }

    public void deleteOneTimeLog(String bossid) {
        getPlayer().deleteOneTimeLog(bossid);
    }

    public int getAcLog(String bossid) {
        return getPlayer().getAcLog(bossid);
    }

    public int getAcLogS(String bossid) {
        return getPlayer().getAcLogS(bossid);
    }

    public void setAcLog(String bossid) {
        getPlayer().setAcLog(bossid);
    }

    public int 获得破功() {
        return getPlayer().获得破功();
    }

    public void 添加破功(int pg) {
        c.getPlayer().添加破功(pg);
    }
    
   // public int getBossLog(String bossid) {
  //      return getPlayer().getBossLog(bossid);
   // }

   // public void setBossLog(String bossid) {
   //     getPlayer().setBossLog(bossid);
   // }
    
     

    
     /*
     * 获取挑战BOSS次数
     */
    public int getBossLog(String bossid) {
        return c.getPlayer().getBossLog(bossid);
    }

    public int getBossLog(String bossid, int type) {
        return c.getPlayer().getBossLog(bossid, type);
    }

    /*
     * 设置或增加挑战BOSS次数
     */
    public void setBossLog(String bossid) {
        c.getPlayer().setBossLog(bossid);
    }

    public void setBossLog(String bossid, int type) {
        c.getPlayer().setBossLog(bossid, type);
    }

    public void setBossLog(String bossid, int type, int count) {
        c.getPlayer().setBossLog(bossid, type, count);
    }

    /*
     * 重置挑战BOSS次数
     */
    public void resetBossLog(String bossid) {
        c.getPlayer().resetBossLog(bossid);
    }

    public void resetBossLog(String bossid, int type) {
        c.getPlayer().resetBossLog(bossid, type);
    }

    /*
     * 设置或增加组队成员的挑战BOSS次数
     * bossid 挑战BOSS任务的名称
     * type 0 = 0点重置 大于0不重置
     * count 设置的次数
     * checkMap 是否检测在同一地图
     */
    public void setPartyBossLog(String bossid) {
        setPartyBossLog(bossid, 0);
    }

    public void setPartyBossLog(String bossid, int type) {
        setPartyBossLog(bossid, type, 1);
    }

    public void setPartyBossLog(String bossid, int type, int count) {
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            c.getPlayer().setBossLog(bossid, type, count);
            return;
        }
        int cMap = getPlayer().getMapId();
        for (MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            MapleCharacter curChar = getPlayer().getMap().getCharacterById(chr.getId());
            if (curChar != null && curChar.getMapId() == cMap) {
                curChar.setBossLog(bossid, type, count);
            }
        }
    }
    
    public int getBossLogAcc(String bossid) {
        return c.getPlayer().getBossLogAcc(bossid);
    }
    
    public void setBossLogAcc(String bossid) {
        c.getPlayer().setBossLogAcc(bossid);
    }
    
    public void setBossLogAcc(String bossid, int bosscount) {
        c.getPlayer().setBossLogAcc(bossid, bosscount);
    }

    public final ChannelServer getChannelServer() {
        return getClient().getChannelServer();
    }

    public final MapleCharacter getPlayer() {
        return getClient().getPlayer();
    }

    public final EventManager getEventManager(final String event) {
        return getClient().getChannelServer().getEventSM().getEventManager(event);
    }

    public final EventInstanceManager getEventInstance() {
        return getClient().getPlayer().getEventInstance();
    }

    public final void warp(final int map) {
        final MapleMap mapz = getWarpMap(map);
        try {
            getClient().getPlayer().changeMap(mapz, mapz.getPortal(Randomizer.nextInt(mapz.getPortals().size())));
        } catch (Exception e) {
            getClient().getPlayer().changeMap(mapz, mapz.getPortal(0));
        }
    }

    public final void warp_Instanced(final int map) {
        final MapleMap mapz = getMap_Instanced(map);
        try {
            getClient().getPlayer().changeMap(mapz, mapz.getPortal(Randomizer.nextInt(mapz.getPortals().size())));
        } catch (Exception e) {
            getClient().getPlayer().changeMap(mapz, mapz.getPortal(0));
        }
    }

    public final void instantMapWarp(final int map, final int portal) {
        final MapleMap mapz = getWarpMap(map);
        if (portal != 0 && map == c.getPlayer().getMapId()) { //test
            final Point portalPos = new Point(c.getPlayer().getMap().getPortal(portal).getPosition());
            c.getSession().writeAndFlush(MaplePacketCreator.instantMapWarp((byte) portal)); //until we get packet for far movement, this will do
            c.getPlayer().checkFollow();
            c.getPlayer().getMap().movePlayer(c.getPlayer(), portalPos);

        } else {
            c.getPlayer().changeMap(mapz, mapz.getPortal(portal));
        }
    }

    public final void warp(final int map, final int portal) {
        final MapleMap mapz = getWarpMap(map);
        if (portal != 0 && map == getClient().getPlayer().getMapId()) { //test
            final Point portalPos = new Point(c.getPlayer().getMap().getPortal(portal).getPosition());
            if (portalPos.distanceSq(getPlayer().getPosition()) < 90000.0) { //estimation
                getClient().sendPacket(MaplePacketCreator.instantMapWarp((byte) portal)); //until we get packet for far movement, this will do
                getClient().getPlayer().checkFollow();
                getClient().getPlayer().getMap().movePlayer(c.getPlayer(), portalPos);
            } else {
                getClient().getPlayer().changeMap(mapz, mapz.getPortal(portal));
            }
        } else {
            getClient().getPlayer().changeMap(mapz, mapz.getPortal(portal));
        }
    }

    public final void warpS(final int map, final int portal) {
        final MapleMap mapz = getWarpMap(map);
        getClient().getPlayer().changeMap(mapz, mapz.getPortal(portal));
    }

    public final void warp(final int map, String portal) {
        final MapleMap mapz = getWarpMap(map);
        if (map == 109060000 || map == 109060002 || map == 109060004) {
            portal = mapz.getSnowballPortal();
        }
        if (map == getClient().getPlayer().getMapId()) { //test
            final Point portalPos = new Point(c.getPlayer().getMap().getPortal(portal).getPosition());
            if (portalPos.distanceSq(getPlayer().getPosition()) < 90000.0) { //estimation
                getClient().getPlayer().checkFollow();
                getClient().sendPacket(MaplePacketCreator.instantMapWarp((byte) getClient().getPlayer().getMap().getPortal(portal).getId()));
                getClient().getPlayer().getMap().movePlayer(c.getPlayer(), new Point(c.getPlayer().getMap().getPortal(portal).getPosition()));
            } else {
                getClient().getPlayer().changeMap(mapz, mapz.getPortal(portal));
            }
        } else {
            getClient().getPlayer().changeMap(mapz, mapz.getPortal(portal));
        }
    }

    public final void warpS(final int map, String portal) {
        final MapleMap mapz = getWarpMap(map);
        if (map == 109060000 || map == 109060002 || map == 109060004) {
            portal = mapz.getSnowballPortal();
        }
        getClient().getPlayer().changeMap(mapz, mapz.getPortal(portal));
    }

    public final void warpMap(final int mapid, final String portal) {
        final MapleMap map = getMap(mapid);
        for (MapleCharacter chr : getClient().getPlayer().getMap().getCharactersThreadsafe()) {
            chr.changeMap(map, map.getPortal(portal));
        }
    }

    public final void warpMap(final int mapid, final int portal) {
        final MapleMap map = getMap(mapid);
        for (MapleCharacter chr : getClient().getPlayer().getMap().getCharactersThreadsafe()) {
            chr.changeMap(map, map.getPortal(portal));
        }
    }

    public final void playPortalSE() {
        getClient().sendPacket(MaplePacketCreator.showOwnBuffEffect(0, 8));
    }

    private final MapleMap getWarpMap(final int map) {
        return ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(map);
    }

    public final MapleMap getMap() {
        return getClient().getPlayer().getMap();
    }

    public final MapleMap getMap(final int map) {
        return getWarpMap(map);
    }

    public final MapleMap getMap_Instanced(final int map) {
        return getClient().getPlayer().getEventInstance() == null ? getMap(map) : getClient().getPlayer().getEventInstance().getMapInstance(map);
    }

    public void spawnMonster(final int id, final int qty) {
        spawnMob(id, qty, new Point(c.getPlayer().getPosition()));
    }

    public final void spawnMobOnMap(final int id, final int qty, final int x, final int y, final int map) {
        for (int i = 0; i < qty; i++) {
            getMap(map).spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(id), new Point(x, y));
        }
    }

    public final void spawnMob(final int id, final int qty, final int x, final int y) {
        spawnMob(id, qty, new Point(x, y));
    }

    public final void spawnMob(final int id, final int x, final int y) {
        spawnMob(id, 1, new Point(x, y));
    }

    private void spawnMob(final int id, final int qty, final Point pos) {
        for (int i = 0; i < qty; i++) {
            getClient().getPlayer().getMap().spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(id), pos);
        }
    }

    public final void killMob(int ids) {
        getClient().getPlayer().getMap().killMonster(ids);
    }

    public final void killAllMob() {
        getClient().getPlayer().getMap().killAllMonsters(true);
    }

    public final void addHP(final int delta) {
        getClient().getPlayer().addHP(delta);
    }

    public final int getPlayerStat(final String type) {
        if (type.equals("LVL")) {
            return getClient().getPlayer().getLevel();
        } else if (type.equals("STR")) {
            return getClient().getPlayer().getStat().getStr();
        } else if (type.equals("DEX")) {
            return getClient().getPlayer().getStat().getDex();
        } else if (type.equals("INT")) {
            return getClient().getPlayer().getStat().getInt();
        } else if (type.equals("LUK")) {
            return getClient().getPlayer().getStat().getLuk();
        } else if (type.equals("HP")) {
            return getClient().getPlayer().getStat().getHp();
        } else if (type.equals("MP")) {
            return getClient().getPlayer().getStat().getMp();
        } else if (type.equals("MAXHP")) {
            return getClient().getPlayer().getStat().getMaxHp();
        } else if (type.equals("MAXMP")) {
            return getClient().getPlayer().getStat().getMaxMp();
        } else if (type.equals("RAP")) {
            return getClient().getPlayer().getRemainingAp();
        } else if (type.equals("RSP")) {
            return getClient().getPlayer().getRemainingSp();
        } else if (type.equals("GID")) {
            return getClient().getPlayer().getGuildId();
        } else if (type.equals("GRANK")) {
            return getClient().getPlayer().getGuildRank();
        } else if (type.equals("ARANK")) {
            return getClient().getPlayer().getAllianceRank();
        } else if (type.equals("GM")) {
            return getClient().getPlayer().isGM() ? 1 : 0;
        } else if (type.equals("ADMIN")) {
            return getClient().getPlayer().hasGmLevel(5) ? 1 : 0;
        } else if (type.equals("GENDER")) {
            return getClient().getPlayer().getGender();
        } else if (type.equals("FACE")) {
            return getClient().getPlayer().getFace();
        } else if (type.equals("HAIR")) {
            return getClient().getPlayer().getHair();
        }
        return -1;
    }

    public final String getName() {
        return getClient().getPlayer().getName();
    }

    //获取沒有时间限制的道具
    public final boolean haveItemTime(final int itemid) {
        if (haveItem(itemid)) {
            final MapleInventoryType type = GameConstants.getInventoryType(itemid);
            for (IItem item : getChar().getInventory(type)) { //omfg;
                if (item.getItemId() == itemid) {
                    return item.getExpiration() == -1;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    //獲取沒有时间限制的道具
    public final boolean haveItemTimeNo(final int itemid) {
        if (haveItem(itemid)) {
            final MapleInventoryType type = GameConstants.getInventoryType(itemid);
            for (IItem item : getChar().getInventory(type)) { //omfg;
                if (item.getItemId() == itemid) {
                    return item.getExpiration() > 0;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public final boolean haveItem(final int itemid) {
        return haveItem(itemid, 1);
    }

    public final boolean haveItem(final int itemid, final int quantity) {
        return haveItem(itemid, quantity, false, true);
    }

    public final boolean haveItem(final int itemid, final int quantity, final boolean checkEquipped, final boolean greaterOrEquals) {
        return getClient().getPlayer().haveItem(itemid, quantity, checkEquipped, greaterOrEquals);
    }

    public final boolean canHold() {
        for (int i = 1; i <= 5; i++) {
            if (c.getPlayer().getInventory(MapleInventoryType.getByType((byte) i)).getNextFreeSlot() <= -1) {
                return false;
            }
        }
        return true;
    }

    public final boolean canHoldByType(byte bytype, int num) {
        if ((c.getPlayer().getInventory(MapleInventoryType.getByType(bytype)).getSlotLimit() - (c.getPlayer().getInventory(MapleInventoryType.getByType(bytype)).getNumSlotLimit() + 1)) <= num) {
            return false;
        }
        return true;
    }

    public final boolean canHoldByTypea(byte bytype, int num) {
        if (c.getPlayer().getInventory(MapleInventoryType.getByType(bytype)).getSlotLimit() - (c.getPlayer().getInventory(MapleInventoryType.getByType(bytype)).getNextFreeSlot() - 1) <= num) {
            return false;
        }
        return true;
    }

    public final boolean canHold(final int itemid) {
        return getClient().getPlayer().getInventory(GameConstants.getInventoryType(itemid)).getNextFreeSlot() > -1;
    }

    public final boolean canHold(final int itemid, final int quantity) {
        return MapleInventoryManipulator.checkSpace(c, itemid, quantity, "");
    }

    public final MapleQuestStatus getQuestRecord(final int id) {
        return getClient().getPlayer().getQuestNAdd(MapleQuest.getInstance(id));
    }

    public final byte getQuestStatus(final int id) {
        return getClient().getPlayer().getQuestStatus(id);
    }

    public final boolean isQuestActive(final int id) {
        return getQuestStatus(id) == 1;
    }

    public final boolean isQuestFinished(final int id) {
        return getQuestStatus(id) == 2;
    }

    public final void showQuestMsg(final String msg) {
        getClient().sendPacket(MaplePacketCreator.showQuestMsg(msg));
    }

    public final void forceStartQuest(final int id, final String data) {
        MapleQuest.getInstance(id).forceStart(c.getPlayer(), 0, data);
    }

    public final void forceStartQuest(final int id, final int data, final boolean filler) {
        MapleQuest.getInstance(id).forceStart(c.getPlayer(), 0, filler ? String.valueOf(data) : null);
    }

    public void forceStartQuest(final int id) {
        MapleQuest.getInstance(id).forceStart(c.getPlayer(), 0, null);
    }

    public void forceCompleteQuest(final int id) {
        MapleQuest.getInstance(id).forceComplete(getPlayer(), 0);
    }

    public void spawnNpc(final int npcId) {
        getClient().getPlayer().getMap().spawnNpc(npcId, getClient().getPlayer().getPosition());
    }

    public final void spawnNpc(final int npcId, final int x, final int y) {
        getClient().getPlayer().getMap().spawnNpc(npcId, new Point(x, y));
    }

    public final void spawnNpc(final int npcId, final Point pos) {
        getClient().getPlayer().getMap().spawnNpc(npcId, pos);
    }

    public final void removeNpc(final int mapid, final int npcId) {
        getClient().getChannelServer().getMapFactory().getMap(mapid).removeNpc(npcId);
    }

    public final void forceStartReactor(final int mapid, final int id) {
        MapleMap map = getClient().getChannelServer().getMapFactory().getMap(mapid);
        MapleReactor react;

        for (final MapleMapObject remo : map.getAllReactorsThreadsafe()) {
            react = (MapleReactor) remo;
            if (react.getReactorId() == id) {
                react.forceStartReactor(c);
                break;
            }
        }
    }

    public final void destroyReactor(final int mapid, final int id) {
        MapleMap map = getClient().getChannelServer().getMapFactory().getMap(mapid);
        MapleReactor react;

        for (final MapleMapObject remo : map.getAllReactorsThreadsafe()) {
            react = (MapleReactor) remo;
            if (react.getReactorId() == id) {
                react.hitReactor(c);
                break;
            }
        }
    }

    public final void hitReactor(final int mapid, final int id) {
        MapleMap map = getClient().getChannelServer().getMapFactory().getMap(mapid);
        MapleReactor react;

        for (final MapleMapObject remo : map.getAllReactorsThreadsafe()) {
            react = (MapleReactor) remo;
            if (react.getReactorId() == id) {
                react.hitReactor(c);
                break;
            }
        }
    }

    public final int getJob() {
        return getClient().getPlayer().getJob();
    }

    public final void gainPotion(final int type, final int amount) {
        getClient().getPlayer().modifyCSPoints(type, amount, true);
    }

    public final int getPotion(final int type) {
        return getClient().getPlayer().getCSPoints(type);
    }

    public final void gainNX(final int amount) {
        gainPotion(1, amount);
    }

    public final int getNX() {
        return getPotion(1);
    }

    public final void gainMaplePoint(final int amount) {
        gainPotion(2, amount);
    }

    public final int getMaplePoint() {
        return getPotion(2);
    }

    public final void gainItemPeriod(final int id, final short quantity, final int period) { //period is in days
        gainItem(id, quantity, false, period, -1, "");
    }

    public final void gainItemPeriod(final int id, final short quantity, final long period, final String owner) { //period is in days
        gainItem(id, quantity, false, period, -1, owner);
    }

    public final void gainItem(final int id, final short quantity) {
        gainItem(id, quantity, false, 0, -1, "");
    }

    public final void gainItem(final int id, final short quantity, final boolean randomStats) {
        gainItem(id, quantity, randomStats, 0, -1, "");
    }

    public final void gainItem(final int id, final short quantity, final boolean randomStats, final int slots) {
        gainItem(id, quantity, randomStats, 0, slots, "");
    }

    public final void gainItem(final int id, final short quantity, final long period) {
        gainItem(id, quantity, false, period, -1, "");
    }

    public final void gainItemTime(final int id, final short quantity, final long period) {
        if (MapleItemInformationProvider.getInstance().isCash(id)) {
            gainItem(id, quantity, false, period, -1, "");
        } else {
            gainItem(id, quantity, false, 0, -1, "");
        }
    }

    public final void gainItem(final int id, final short quantity, final boolean randomStats, final long period, final int slots) {
        gainItem(id, quantity, randomStats, period, slots, "");
    }

    public final void gainItem(final int id, final short quantity, final boolean randomStats, final long period, final int slots, final String owner) {
        gainItem(id, quantity, randomStats, period, slots, owner, c);
    }
 //public final void gainItem(int id, int str, int dex, int luk, int Int, int hp, int mp, int watk, int matk, int wdef, int mdef, int hb, int mz, int ty, int yd, int time) {
 //       gainItemS(id, str, dex, luk, Int, hp, mp, watk, matk, wdef, mdef, hb, mz, ty, yd, this.c, time);
 //   }

  //  public final void gainItem(int id, int str, int dex, int luk, int Int, int hp, int mp, int watk, int matk, int wdef, int mdef, int hb, int mz, int ty, int yd) {
  //      gainItemS(id, str, dex, luk, Int, hp, mp, watk, matk, wdef, mdef, hb, mz, ty, yd, this.c, 0);
  //  }
    
    public final void gainItem(final int id, final short quantity, final boolean randomStats, final long period, final int slots, final String owner, final MapleClient cg) {
        if (quantity >= 0) {
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final MapleInventoryType type = GameConstants.getInventoryType(id);

            if (!MapleInventoryManipulator.checkSpace(cg, id, quantity, "")) {

                return;
            }
            if (type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(id) && !GameConstants.isBullet(id)) {
                final Equip item = (Equip) (randomStats ? ii.randomizeStats((Equip) ii.getEquipById(id)) : ii.getEquipById(id));
                if (period > 0) {
                    item.setExpiration(System.currentTimeMillis() + (period * 24 * 60 * 60 * 1000));
                }
                if (slots > 0) {
                    item.setUpgradeSlots((byte) (item.getUpgradeSlots() + slots));
                }
                if (owner != null) {
                    item.setOwner(owner);
                }
                final String name = ii.getName(id);
                if (id / 10000 == 114 && name != null && name.length() > 0) { //medal
                    final String msg = "你已获得称号 <" + name + ">";
                    cg.getPlayer().dropMessage(-1, msg);
                    cg.getPlayer().dropMessage(5, msg);
                }
                MapleInventoryManipulator.addbyItem(cg, item.copy());
            } else {
                final MaplePet pet;
                if (ItemConstants.類型.寵物(id)) {
                    pet = MaplePet.createPet(id, MapleInventoryIdentifier.getInstance());
                } else {
                    pet = null;
                }
                MapleInventoryManipulator.addById(cg, id, quantity, owner == null ? "" : owner, pet, period);
            }
        } else {
            MapleInventoryManipulator.removeById(cg, GameConstants.getInventoryType(id), id, -quantity, true, false);
        }
        cg.sendPacket(MaplePacketCreator.getShowItemGain(id, quantity, true));
    }

    public final void gainItem(int id, int str, int dex, int luk, int Int, int hp, int mp, int watk, int matk, int wdef, int mdef, int hb, int mz, int ty, int yd, int time) {
        gainItemS(id, str, dex, luk, Int, hp, mp, watk, matk, wdef, mdef, hb, mz, ty, yd, this.c, time);
    }

    public final void gainItem(int id, int str, int dex, int luk, int Int, int hp, int mp, int watk, int matk, int wdef, int mdef, int hb, int mz, int ty, int yd) {
        gainItemS(id, str, dex, luk, Int, hp, mp, watk, matk, wdef, mdef, hb, mz, ty, yd, this.c, 0);
    }

    public final void gainItemS(int id, int str, int dex, int luk, int Int, int hp, int mp, int watk, int matk, int wdef, int mdef, int hb, int mz, int ty, int yd, MapleClient cg, int time) {
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        MapleInventoryType type = GameConstants.getInventoryType(id);

        if (!(MapleInventoryManipulator.checkSpace(cg, id, 1, ""))) {
            return;
        }

        if ((type.equals(MapleInventoryType.EQUIP)) && (!(GameConstants.isThrowingStar(id))) && (!(GameConstants.isBullet(id)))) {
            Equip item = (Equip) (Equip) ii.getEquipById(id);

            String name = ii.getName(id);
            if ((id / 10000 == 114) && (name != null) && (name.length() > 0)) {
                String msg = "你已获得称号 <" + name + ">";
                cg.getPlayer().dropMessage(5, msg);
                cg.getPlayer().dropMessage(5, msg);
            }
            if (time > 0) {
                item.setExpiration(System.currentTimeMillis() + time * 60 * 60 * 1000);
            }

            if (str > 0) {
                item.setStr((short) str);
            }

            if (dex > 0) {
                item.setDex((short) dex);
            }

            if (luk > 0) {
                item.setLuk((short) luk);
            }

            if (Int > 0) {
                item.setInt((short) Int);
            }

            if (hp > 0) {
                item.setHp((short) hp);
            }

            if (mp > 0) {
                item.setMp((short) mp);
            }

            if (watk > 0) {
                item.setWatk((short) watk);
            }

            if (matk > 0) {
                item.setMatk((short) matk);
            }

            if (wdef > 0) {
                item.setWdef((short) wdef);
            }

            if (mdef > 0) {
                item.setMdef((short) mdef);
            }

            if (hb > 0) {
                item.setAvoid((short) hb);
            }

            if (mz > 0) {
                item.setAcc((short) mz);
            }

            if (ty > 0) {
                item.setJump((short) ty);
            }

            if (yd > 0) {
                item.setSpeed((short) yd);
            }

            MapleInventoryManipulator.addbyItem(cg, item.copy());
        } else {
            MapleInventoryManipulator.addById(cg, id, (short) 1, "", (byte) 0);
        }
        cg.getSession().write(MaplePacketCreator.getShowItemGain(id, (short) 1, true));
    } 
    
    public final void gainItemStatus(final int id, final short quantity) {
        gainItemStatus(id, quantity, false, 0, -1, "");
    }

    public final void gainItemStatus(final int id, final short quantity, final boolean randomStats, final long period, final int slots, final String owner) {
        gainItemStatus(id, quantity, randomStats, period, slots, owner, c);
    }

    public final void gainItemStatus(final int id, final short quantity, final boolean randomStats, final long period, final int slots, final String owner, final MapleClient cg) {
        if (quantity >= 0) {
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final MapleInventoryType type = GameConstants.getInventoryType(id);

            if (!MapleInventoryManipulator.checkSpace(cg, id, quantity, "")) {

                return;
            }
            if (type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(id) && !GameConstants.isBullet(id)) {
                final Equip item = (Equip) (randomStats ? ii.randomizeStats((Equip) ii.getEquipById(id)) : ii.getEquipById(id));
                if (period > 0) {
                    item.setExpiration(System.currentTimeMillis() + (period * 24 * 60 * 60 * 1000));
                }
                if (slots > 0) {
                    item.setUpgradeSlots((byte) (item.getUpgradeSlots() + slots));
                }
                if (owner != null) {
                    item.setOwner(owner);
                }
                item.setStr((short) 1);
                item.setDex((short) 1);
                item.setInt((short) 1);
                item.setLuk((short) 1);
                final String name = ii.getName(id);
                if (id / 10000 == 114 && name != null && name.length() > 0) { //medal
                    final String msg = "你已获得称号 <" + name + ">";
                    cg.getPlayer().dropMessage(-1, msg);
                    cg.getPlayer().dropMessage(5, msg);
                }
                MapleInventoryManipulator.addbyItem(cg, item.copy());
            } else {
                final MaplePet pet;
                if (ItemConstants.類型.寵物(id)) {
                    pet = MaplePet.createPet(id, MapleInventoryIdentifier.getInstance());
                } else {
                    pet = null;
                }
                MapleInventoryManipulator.addById(cg, id, quantity, owner == null ? "" : owner, pet, period);
            }
        } else {
            MapleInventoryManipulator.removeById(cg, GameConstants.getInventoryType(id), id, -quantity, true, false);
        }
        cg.sendPacket(MaplePacketCreator.getShowItemGain(id, quantity, true));
    }

    public final void changeMusic(final String songName) {
        getPlayer().getMap().broadcastMessage(MaplePacketCreator.musicChange(songName));
    }

    public final void worldMessage(final int type, final String message) {
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(type, message));
    }

    // default playerMessage and mapMessage to use type 5
    public final void playerMessage(final String message) {
        playerMessage(5, message);
    }

    public final void mapMessage(final String message) {
        mapMessage(5, message);
    }

    public final void guildMessage(final String message) {
        guildMessage(5, message);
    }

    public final void playerMessage(final int type, final String message) {
        getClient().sendPacket(MaplePacketCreator.serverNotice(type, message));
    }

    public final void mapMessage(final int type, final String message) {
        getClient().getPlayer().getMap().broadcastMessage(MaplePacketCreator.serverNotice(type, message));
    }

    public final void guildMessage(final int type, final String message) {
        if (getPlayer().getGuildId() > 0) {
            World.Guild.guildPacket(getPlayer().getGuildId(), MaplePacketCreator.serverNotice(type, message));
        }
    }

    public final MapleGuild getGuild() {
        return getGuild(getPlayer().getGuildId());
    }

    public final MapleGuild getGuild(int guildid) {
        return World.Guild.getGuild(guildid);
    }

    public final MapleParty getParty() {
        return getClient().getPlayer().getParty();
    }

    public final int getCurrentPartyId(int mapid) {
        return getMap(mapid).getCurrentPartyId();
    }

    public final boolean isLeader() {
        if (getParty() == null) {
            return false;
        }
        return getParty().getLeader().getId() == getClient().getPlayer().getId();
    }

    public final boolean isAllPartyMembersAllowedJob(final int job) {
        if (c.getPlayer().getParty() == null) {
            return false;
        }
        for (final MaplePartyCharacter mem : getClient().getPlayer().getParty().getMembers()) {
            if (mem.getJobId() / 100 != job) {
                return false;
            }
        }
        return true;
    }

    public final boolean allMembersHere() {
        if (c.getPlayer().getParty() == null) {
            return false;
        }
        for (final MaplePartyCharacter mem : getClient().getPlayer().getParty().getMembers()) {
            final MapleCharacter chr = getClient().getPlayer().getMap().getCharacterById(mem.getId());
            if (chr == null) {
                return false;
            }
        }
        return true;
    }

    public final void warpParty(final int mapId) {
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            warp(mapId, 0);
            return;
        }
        final MapleMap target = getMap(mapId);
        final int cMap = getPlayer().getMapId();

        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getChannelServer().getPlayerStorage().getCharacterById(chr.getId());
            if (curChar != null && (curChar.getMapId() == cMap || curChar.getEventInstance() == getPlayer().getEventInstance())) {
                curChar.changeMap(target, target.getPortal(0));
            }
        }
    }

    public final void warpParty(final int mapId, final int portal) {
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            if (portal < 0) {
                warp(mapId);
            } else {
                warp(mapId, portal);
            }
            return;
        }
        final boolean rand = portal < 0;
        final MapleMap target = getMap(mapId);
        final int cMap = getPlayer().getMapId();

        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getChannelServer().getPlayerStorage().getCharacterById(chr.getId());
            if (curChar != null && (curChar.getMapId() == cMap || curChar.getEventInstance() == getPlayer().getEventInstance())) {
                if (rand) {
                    try {
                        curChar.changeMap(target, target.getPortal(Randomizer.nextInt(target.getPortals().size())));
                    } catch (Exception e) {
                        curChar.changeMap(target, target.getPortal(0));
                    }
                } else {
                    curChar.changeMap(target, target.getPortal(portal));
                }
            }
        }
    }

    public final void warpParty_Instanced(final int mapId) {
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            warp_Instanced(mapId);
            return;
        }
        final MapleMap target = getMap_Instanced(mapId);

        final int cMap = getPlayer().getMapId();
        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getChannelServer().getPlayerStorage().getCharacterById(chr.getId());
            if (curChar != null && (curChar.getMapId() == cMap || curChar.getEventInstance() == getPlayer().getEventInstance())) {
                curChar.changeMap(target, target.getPortal(0));
            }
        }
    }

    public void gainMeso(int gain) {
        getClient().getPlayer().gainMeso(gain, true, false, true);
    }

    public void gainExp(int gain) {
        getClient().getPlayer().gainExp(gain, true, true, true);
    }

    public void gainExpR(int gain) {
        getClient().getPlayer().gainExp(gain * getClient().getChannelServer().getExpRate(), true, true, true);
    }

    public final void givePartyItems(final int id, final short quantity, final List<MapleCharacter> party) {
        for (MapleCharacter chr : party) {
            if (quantity >= 0) {
                MapleInventoryManipulator.addById(chr.getClient(), id, quantity);
            } else {
                MapleInventoryManipulator.removeById(chr.getClient(), GameConstants.getInventoryType(id), id, -quantity, true, false);
            }
            chr.getClient().sendPacket(MaplePacketCreator.getShowItemGain(id, quantity, true));
        }
    }

    public final void givePartyItems(final int id, final short quantity) {
        givePartyItems(id, quantity, false);
    }

    public final boolean canPartyHold() {
        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                for (int i = 1; i <= 5; i++) {
                    if (curChar.getInventory(MapleInventoryType.getByType((byte) i)).getNextFreeSlot() <= -1) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final void givePartyItems(final int id, final short quantity, final boolean removeAll) {
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            gainItem(id, (short) (removeAll ? -getPlayer().itemQuantity(id) : quantity));
            return;
        }

        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                gainItem(id, (short) (removeAll ? -curChar.itemQuantity(id) : quantity), false, 0, 0, "", curChar.getClient());
            }
        }
    }

    public final void givePartyExp(final int amount, final List<MapleCharacter> party) {
        for (final MapleCharacter chr : party) {
            chr.gainExp(amount * getClient().getChannelServer().getExpRate(), true, true, true);
        }
    }

    public final void givePartyExp(final int amount) {
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            gainExp(amount * getClient().getChannelServer().getExpRate());
            return;
        }
        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.gainExp(amount * getClient().getChannelServer().getExpRate(), true, true, true);
            }
        }
    }

    public final void givePartyNX(final int amount, final List<MapleCharacter> party) {
        for (final MapleCharacter chr : party) {
            chr.modifyCSPoints(1, amount, true);
        }
    }

    public final void givePartyNX(final int amount) {
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            gainNX(amount);
            return;
        }
        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.modifyCSPoints(1, amount, true);
            }
        }
    }

    public final void endPartyQuest(final int amount, final List<MapleCharacter> party) {
        for (final MapleCharacter chr : party) {
            chr.endPartyQuest(amount);
        }
    }

    public final void endPartyQuest(final int amount) {
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            getPlayer().endPartyQuest(amount);
            return;
        }
        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.endPartyQuest(amount);
            }
        }
    }

    public final void removeFromParty(final int id, final List<MapleCharacter> party) {
        for (final MapleCharacter chr : party) {
            final int possesed = chr.getInventory(GameConstants.getInventoryType(id)).countById(id);
            if (possesed > 0) {
                MapleInventoryManipulator.removeById(c, GameConstants.getInventoryType(id), id, possesed, true, false);
                chr.getClient().sendPacket(MaplePacketCreator.getShowItemGain(id, (short) -possesed, true));
            }
        }
    }

    public final void removeFromParty(final int id) {
        givePartyItems(id, (short) 0, true);
    }

    public final void useSkill(final int skill, final int level) {
        if (level <= 0) {
            return;
        }
        SkillFactory.getSkill(skill).getEffect(level).applyTo(c.getPlayer());
    }

    public final void useItem(final int id) {
        MapleItemInformationProvider.getInstance().getItemEffect(id).applyTo(c.getPlayer());
        getClient().sendPacket(UIPacket.getStatusMsg(id));
    }

    public final void useItemEffect(final int id) {
        MapleItemInformationProvider.getInstance().getItemEffect(id).applyTo(c.getPlayer());
        getClient().sendPacket(MaplePacketCreator.enableActions());
    }

    public final void cancelItem(final int id) {
        getClient().getPlayer().cancelEffect(MapleItemInformationProvider.getInstance().getItemEffect(id), false, -1);
    }

    public final int getMorphState() {
        return getClient().getPlayer().getMorphState();
    }

    public final void removeAll(final int id) {
        getClient().getPlayer().removeAll(id, true);
    }

    public final void gainCloseness(final int closeness, final int index) {
        final MaplePet pet = getPlayer().getPet(index);
        if (pet != null) {
            pet.setCloseness(pet.getCloseness() + closeness);
            getClient().sendPacket(PetPacket.updatePet(pet, getPlayer().getInventory(MapleInventoryType.CASH).getItem((byte) pet.getInventoryPosition())));
        }
    }

    public final void gainClosenessAll(final int closeness) {
        for (final MaplePet pet : getPlayer().getPets()) {
            if (pet != null) {
                pet.setCloseness(pet.getCloseness() + closeness);
                getClient().sendPacket(PetPacket.updatePet(pet, getPlayer().getInventory(MapleInventoryType.CASH).getItem((byte) pet.getInventoryPosition())));
            }
        }
    }

    public final void resetMap(final int mapid) {
        getMap(mapid).resetFully();
    }

    public final void openNpc(final int id) {
        openNpc(id, null);
    }

    public final void openNpc(final int id, final int mode) {
        openNpc(getClient(), id, mode, null);
    }

    public final void openNpc(final MapleClient cg, final int id) {
        NPCScriptManager.getInstance().dispose(cg);
        openNpc(cg, id, 0, null);
    }

    public final void openNpc(final int id, final String script) {
        openNpc(getClient(), id, script);
    }

    public final void openNpc(final MapleClient cg, final int id, final String script) {
        openNpc(getClient(), id, 0, script);
    }

    public final void openNpc(final MapleClient cg, final int id, final int mode, final String script) {
        cg.removeClickedNPC();
        NPCScriptManager.getInstance().start(cg, id, mode, script);
    }

    public final int getMapId() {
        return getClient().getPlayer().getMapId();
    }

    public final boolean haveMonster(final int mobid) {
        for (MapleMapObject obj : getClient().getPlayer().getMap().getAllMonstersThreadsafe()) {
            final MapleMonster mob = (MapleMonster) obj;
            if (mob.getId() == mobid) {
                return true;
            }
        }
        return false;
    }

    public final int getChannelNumber() {
        return getClient().getChannel();
    }

    public final int getMonsterCount(final int mapid) {
        return getClient().getChannelServer().getMapFactory().getMap(mapid).getNumMonsters();
    }

    public final void teachSkill(final int id, final byte level, final byte masterlevel) {
        getPlayer().changeSkillLevel(SkillFactory.getSkill(id), level, masterlevel);
    }

    public final void teachSkill(final int id, byte level) {
        final ISkill skil = SkillFactory.getSkill(id);
        if (getPlayer().getSkillLevel(skil) > level) {
            level = getPlayer().getSkillLevel(skil);
        }
        getPlayer().changeSkillLevel(skil, level, skil.getMaxLevel());
    }

    public final int getPlayerCount(final int mapid) {
        return getClient().getChannelServer().getMapFactory().getMap(mapid).getCharactersSize();
    }

    public final void dojo_getUp() {
        getClient().sendPacket(MaplePacketCreator.updateInfoQuest(1207, "pt=1;min=4;belt=1;tuto=1")); //todo
        getClient().sendPacket(MaplePacketCreator.Mulung_DojoUp2());
        getClient().sendPacket(MaplePacketCreator.instantMapWarp((byte) 6));
    }

    public final boolean dojoAgent_NextMap(final boolean dojo, final boolean fromresting) {
        if (dojo) {
            return Event_DojoAgent.warpNextMap(c.getPlayer(), fromresting);
        }
        return Event_DojoAgent.warpNextMap_Agent(c.getPlayer(), fromresting);
    }

    public final int dojo_getPts() {
        return getClient().getPlayer().getDojo();
    }

    public final MapleEvent getEvent(final String loc) {
        return getClient().getChannelServer().getEvent(MapleEventType.valueOf(loc));
    }

    public final int getSavedLocation(final String loc) {
        final Integer ret = getClient().getPlayer().getSavedLocation(SavedLocationType.fromString(loc));
        if (ret == null || ret == -1) {
            return 100000000;
        }
        return ret;
    }

    public final void saveLocation(final String loc) {
        getClient().getPlayer().saveLocation(SavedLocationType.fromString(loc));
    }

    public final void saveReturnLocation(final String loc) {
        getClient().getPlayer().saveLocation(SavedLocationType.fromString(loc), getClient().getPlayer().getMap().getReturnMap().getId());
    }

    public final void clearSavedLocation(final String loc) {
        getClient().getPlayer().clearSavedLocation(SavedLocationType.fromString(loc));
    }

    public final void summonMsg(final String msg) {
        if (!c.getPlayer().hasSummon()) {
            playerSummonHint(true);
        }
        getClient().sendPacket(UIPacket.summonMessage(msg));
    }

    public final void summonMsg(final int type) {
        if (!c.getPlayer().hasSummon()) {
            playerSummonHint(true);
        }
        getClient().sendPacket(UIPacket.summonMessage(type));
    }

    public final void showInstruction(final String msg, final int width, final int height) {
        getClient().sendPacket(MaplePacketCreator.sendHint(msg, width, height));
    }

    public final void playerSummonHint(final boolean summon) {
        getClient().getPlayer().setHasSummon(summon);
        getClient().sendPacket(UIPacket.summonHelper(summon));
    }

    public final String getInfoQuest(final int id) {
        return getClient().getPlayer().getInfoQuest(id);
    }

    public final void updateInfoQuest(final int id, final String data) {
        getClient().getPlayer().updateInfoQuest(id, data);
    }

    public final boolean getEvanIntroState(final String data) {
        return getInfoQuest(22013).equals(data);
    }

    public final void updateEvanIntroState(final String data) {
        updateInfoQuest(22013, data);
    }

    public final void Aran_Start() {
        getClient().sendPacket(UIPacket.Aran_Start());
    }

    public final void evanTutorial(final String data, final int v1) {
        getClient().sendPacket(MaplePacketCreator.getEvanTutorial(data));
    }

    public final void AranTutInstructionalBubble(final String data) {
        getClient().sendPacket(UIPacket.AranTutInstructionalBalloon(data));
    }

    public final void ShowWZEffect(final String data) {
        getClient().sendPacket(UIPacket.AranTutInstructionalBalloon(data));
    }

    public final void showWZEffect(final String data) {
        getClient().sendPacket(UIPacket.ShowWZEffect(data));
    }

    public final void EarnTitleMsg(final String data) {
        getClient().sendPacket(UIPacket.EarnTitleMsg(data));
    }

    public final void MovieClipIntroUI(final boolean enabled) {
        getClient().sendPacket(UIPacket.IntroDisableUI(enabled));
        getClient().sendPacket(UIPacket.IntroLock(enabled));
    }

    public MapleInventoryType getInvType(int i) {
        return MapleInventoryType.getByType((byte) i);
    }

    public String getItemName(final int id) {
        return MapleItemInformationProvider.getInstance().getName(id);
    }

    public void gainPet(int id, String name, int level, int closeness, int fullness) {
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        gainPet(id, name, level, closeness, fullness, ii.getPetLife(id), ii.getPetFlagInfo(id));
    }

    public void gainPet(int id, String name, int level, int closeness, int fullness, int period) {
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        gainPet(id, name, level, closeness, fullness, period, ii.getPetFlagInfo(id));
    }

    public void gainPet(int id, String name, int level, int closeness, int fullness, long period, short flags) {
        if (id > 5010000 || id < 5000000) {
            id = 5000000;
        }
        if (level > 30) {
            level = 30;
        }
        if (closeness > 30000) {
            closeness = 30000;
        }
        if (fullness > 100) {
            fullness = 100;
        }
        try {
            MapleInventoryManipulator.addById(c, id, (short) 1, "", MaplePet.createPet(id, name, level, closeness, fullness, MapleInventoryIdentifier.getInstance(), id == 5000054 ? (int) period : 0, flags), 45);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public void removeSlot(int invType, byte slot, short quantity) {
        MapleInventoryManipulator.removeFromSlot(c, getInvType(invType), slot, quantity, true);
    }

    public void gainGP(final int gp) {
        if (getPlayer().getGuildId() <= 0) {
            return;
        }
        World.Guild.gainGP(getPlayer().getGuildId(), gp); //1 for
    }

    public int getGP() {
        if (getPlayer().getGuildId() <= 0) {
            return 0;
        }
        return World.Guild.getGP(getPlayer().getGuildId()); //1 for
    }

    public void showMapEffect(String path) {
        getClient().sendPacket(UIPacket.MapEff(path));
    }

    public int itemQuantity(int itemid) {
        return getPlayer().itemQuantity(itemid);
    }

    public EventInstanceManager getDisconnected(String event) {
        EventManager em = getEventManager(event);
        if (em == null) {
            return null;
        }
        for (EventInstanceManager eim : em.getInstances()) {
            if (eim.isDisconnected(c.getPlayer()) && eim.getPlayerCount() > 0) {
                return eim;
            }
        }
        return null;
    }

    public boolean isAllReactorState(final int reactorId, final int state) {
        boolean ret = false;
        for (MapleReactor r : getMap().getAllReactorsThreadsafe()) {
            if (r.getReactorId() == reactorId) {
                ret = r.getState() == state;
            }
        }
        return ret;
    }

    public long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public void spawnMonster(int id) {
        spawnMonster(id, 1, new Point(getPlayer().getPosition()));
    }

    // summon one monster, remote location
    public void spawnMonster(int id, int x, int y) {
        spawnMonster(id, 1, new Point(x, y));
    }

    // multiple monsters, remote location
    public void spawnMonster(int id, int qty, int x, int y) {
        spawnMonster(id, qty, new Point(x, y));
    }

    // handler for all spawnMonster
    public void spawnMonster(int id, int qty, Point pos) {
        for (int i = 0; i < qty; i++) {
            getMap().spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(id), pos);
        }
    }

    public void sendNPCText(final String text, final int npc) {
        getMap().broadcastMessage(MaplePacketCreator.getNPCTalk(npc, (byte) 0, text, "00 00", (byte) 0));
    }

    public void warpAllPlayer(int from, int to) {

        final MapleMap tomap = getMapFactory().getMap(to);
        final MapleMap frommap = getMapFactory().getMap(from);
        List<MapleCharacter> list = frommap.getCharactersThreadsafe();
        if (tomap != null && frommap != null && list != null && frommap.getCharactersSize() > 0) {
            for (MapleMapObject mmo : list) {
                ((MapleCharacter) mmo).changeMap(tomap, tomap.getPortal(0));
            }
        }
    }

    public MapleMapFactory getMapFactory() {
        return getChannelServer().getMapFactory();
    }

    public void enterMTS() {
        InterServerHandler.EnterCashShop(c, c.getPlayer(), true);
    }

    public int getChannelOnline() {
        return getClient().getChannelServer().getConnectedClients();
    }

    public int getTotalOnline() {
        return ChannelServer.getAllInstances().stream().map((cserv) -> cserv.getConnectedClients()).reduce(0, Integer::sum);
    }

    public int getMP() {
        return getPlayer().getMP();
    }

    public void setMP(int x) {
        getPlayer().setMP(x);
    }

    public int save(boolean dc, boolean fromcs) {
        try {
            return getPlayer().saveToDB(dc, fromcs);
        } catch (UnsupportedOperationException ex) {
        }
        return 0;
    }

    public void save() {
        save(false, false);
    }

    public boolean hasSquadByMap() {
        return getPlayer().getMap().getSquadByMap() != null;
    }

    public boolean hasEventInstance() {
        return getPlayer().getEventInstance() != null;
    }

    public boolean hasEMByMap() {
        return getPlayer().getMap().getEMByMap() != null;
    }

    public void processCommand(String line) {
        CommandProcessor.processCommand(getClient(), line, ServerConstants.CommandType.NORMAL);
    }

    public void warpPlayer(int from, int to) {
        final MapleMap mapto = c.getChannelServer().getMapFactory().getMap(to);
        final MapleMap mapfrom = c.getChannelServer().getMapFactory().getMap(from);
        for (MapleCharacter chr : mapfrom.getCharactersThreadsafe()) {
            chr.changeMap(mapto, mapto.getPortal(0));
        }
    }

    public void isVipMedalName() {
        if (getOneTimeLog("关闭VIP星星數顯示") < 1) {
            setOneTimeLog("关闭VIP星星數顯示");
            c.getPlayer().dropMessage(5, "关闭VIP星星數顯示。");
        } else {
            deleteOneTimeLog("关闭VIP星星數顯示");
            c.getPlayer().dropMessage(5, "开启VIP星星數顯示。");
        }
    }

    public int getVip() {
        return getPlayer().getVip();
    }

    public void getItemLog(String mob, String itemmob) {
        FileoutputUtil.logToFile("logs/Data/" + mob + ".txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 账号 " + c.getAccountName() + " 账号ID " + c.getAccID() + " 角色名 " + c.getPlayer().getName() + " 角色ID " + c.getPlayer().getId() + " " + itemmob);
    }

    public int getAccNewTime(String time) {
        return getPlayer().getAccNewTime(time);
    }

    public int getQianDaoTime(String time) {
        return getPlayer().getQianDaoTime(time);
    }

    public int getQianDaoAcLog(String time) {
        return getPlayer().getQianDaoAcLog(time);
    }

    public void giveEventPrize() {
        final int reward = RandomRewards.getInstance().getEventReward();
        if (reward == 0) {
            getPlayer().gainMeso(66666, true, false, false);
            getPlayer().dropMessage(5, "你获得 66666 金币");
        } else if (reward == 1) {
            getPlayer().gainMeso(399999, true, false, false);
            getPlayer().dropMessage(5, "你获得 399999 金币");
        } else if (reward == 2) {
            getPlayer().gainMeso(666666, true, false, false);
            getPlayer().dropMessage(5, "你获得 666666 金币");
        } else if (reward == 3) {
            getPlayer().addFame(10);
            getPlayer().dropMessage(5, "你获得 10 名聲");
        } else {
            int max_quantity = 1;
            switch (reward) {
                case 5062000:
                    max_quantity = 3;
                    break;
                case 5220000:
                    max_quantity = 25;
                    break;
                case 4031307:
                case 5050000:
                    max_quantity = 5;
                    break;
                case 2022121:
                    max_quantity = 10;
                    break;
            }
            final int quantity = (max_quantity > 1 ? Randomizer.nextInt(max_quantity) : 0) + 1;
            if (MapleInventoryManipulator.checkSpace(getPlayer().getClient(), reward, quantity, "")) {
                MapleInventoryManipulator.addById(getPlayer().getClient(), reward, (short) quantity);
                getPlayer().dropMessage(5, "恭喜获得" + MapleItemInformationProvider.getInstance().getName(reward));
            } else {
                getPlayer().gainMeso(100000, true, false, false);
                getPlayer().dropMessage(5, "參加獎 100000 金币");
            }

        }
    }

    public List<IItem> getMonsterRidinglist() {
        //MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        MapleInventory Equip = c.getPlayer().getInventory(MapleInventoryType.EQUIP);
        List<IItem> ret = new ArrayList();
        for (IItem tep : Equip) {
            //Map stats = ii.getEquipStats(tep.getItemId());
            //if (stats.containsKey("cash")) {
            if (tep.getItemId() >= 1930000 && tep.getItemId() <= 1992050) {
                ret.add(tep);
            }
            //}
        }
        return ret;
    }

    public String getCharacterNameById(int id) {
        String name = c.getPlayer().getCharacterNameById(id);
        return name;
    }

    public final int getCharacterIdByName(String name) {
        int id = c.getPlayer().getCharacterIdByName(name);
        return id;
    }

    public int getCharacterByNameLevel(String name) {
        int level = c.getPlayer().getCharacterByName(name).getLevel();
        return level;
    }

    public List<IItem> getCsEquipList() {
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        MapleInventory Equip = c.getPlayer().getInventory(MapleInventoryType.EQUIP);
        List<IItem> ret = new ArrayList();
        for (IItem tep : Equip) {
            if (ii.isCash(tep.getItemId())) {
                ret.add(tep);
            }
        }
        return ret;
    }

    public Equip getEquipStat(byte slot) {
        Equip sel = (Equip) c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem(slot);

        return sel;
    }

    public void dropCs(byte type, final short src, final short quantity) {
        MapleInventoryManipulator.dropCs(c, MapleInventoryType.getByType(type), src, quantity);
    }

    public final boolean canwncs() {
        for (int i : GameConstants.blockedMaps) {
            if (c.getPlayer().getMapId() == i) {
                c.getPlayer().dropMessage(5, "當前地图無法使用.");
                return false;
            }
        }

        if (c.getPlayer().getMapId() == 749060605 || c.getPlayer().getMapId() == 229010000 || c.getPlayer().getMapId() == 910000000) {
            c.getPlayer().dropMessage(5, "當前地图無法使用.");
            return false;
        }

        if (c.getPlayer().getLevel() < 10 && c.getPlayer().getJob() != 200) {
            c.getPlayer().dropMessage(5, "你的等級不足10級無法使用.");
            return false;
        }
        if (c.getPlayer().hasBlockedInventory(true) || c.getPlayer().getMap().getSquadByMap() != null || c.getPlayer().getEventInstance() != null || c.getPlayer().getMap().getEMByMap() != null || c.getPlayer().getMapId() >= 990000000/* || FieldLimitType.VipRock.check(c.getPlayer().getMap().getFieldLimit())*/) {
            c.getPlayer().dropMessage(5, "請稍後再試");
            return false;
        }
        if ((c.getPlayer().getMapId() >= 680000210 && c.getPlayer().getMapId() <= 680000502) || (c.getPlayer().getMapId() / 1000 == 980000 && c.getPlayer().getMapId() != 980000000) || (c.getPlayer().getMapId() / 100 == 1030008) || (c.getPlayer().getMapId() / 100 == 922010) || (c.getPlayer().getMapId() / 10 == 13003000)) {
            c.getPlayer().dropMessage(5, "請稍後再試.");
            return false;
        }
        return true;
    }
    //在线时间
    public int getGamePoints() {
        return this.c.getPlayer().getGamePoints();
    }

    public void gainGamePoints(int amount) {
        this.c.getPlayer().gainGamePoints(amount);
    }

    public void resetGamePoints() {
        this.c.getPlayer().updateGamePoints(0);
    }

    public int getGamePointsPD() {
        return this.c.getPlayer().get在线时间();//getGamePointsPD
    }

    public void gainGamePointsPD(int amount) {
        this.c.getPlayer().gainGamePointsPD(amount);
    }

    public void resetGamePointsPD() {
        this.c.getPlayer().resetGamePointsPD();
    }

    public int getEquipItemType(int itemid) {
        if (類型.帽子(itemid)) {
            return 1;
        }
        if (類型.臉飾(itemid)) {
            return 2;
        }
        if (類型.眼飾(itemid)) {
            return 3;
        }
        if (類型.耳環(itemid)) {
            return 4;
        }
        if (類型.上衣(itemid)) {
            return 5;
        }
        if (類型.套服(itemid)) {
            return 6;
        }
        if (類型.褲裙(itemid)) {
            return 7;
        }
        if (類型.鞋子(itemid)) {
            return 8;
        }
        if (類型.手套(itemid)) {
            return 9;
        }
        if (類型.盾牌(itemid)) {
            return 9;
        }
        if (類型.披風(itemid)) {
            return 10;
        }
        if (類型.戒指(itemid)) {
            return 11;
        }
        if (類型.墜飾(itemid)) {
            return 12;
        }
        if (類型.腰帶(itemid)) {
            return 13;
        }
        if (類型.勳章(itemid)) {
            return 15;
        }
        if (類型.武器(itemid)) {
            return 16;
        }
        if (類型.副手(itemid)) {
            return 17;
        }

        return 0;
    }

    public void forceReAddItem(Item item, byte type) {
        //c.getPlayer().forceReAddItem(item, MapleInventoryType.getByType(type));
        c.getPlayer().forceReAddItem_Flag(item, MapleInventoryType.getByType(type));
        c.getPlayer().equipChanged();
    }

    public void StatsZs() {
        Map<MapleStat, Integer> statups = new EnumMap<>(MapleStat.class);

        c.getPlayer().setLevel((short) 1);
        c.getPlayer().levelUp();
        if (c.getPlayer().getExp() < 0) {
            c.getPlayer().gainExp(-c.getPlayer().getExp(), false, false, true);
        }
        c.getPlayer().getStat().str = (short) 4;
        c.getPlayer().getStat().dex = (short) 4;
        c.getPlayer().getStat().int_ = (short) 4;
        c.getPlayer().getStat().luk = (short) 4;
        c.getPlayer().setHpMpApUsed((short) 0);
        c.getPlayer().setRemainingAp((short) (13));
        c.getPlayer().setRemainingSp(0);

        c.getSession().write(MaplePacketCreator.updateSp(c.getPlayer(), false));

        //c.getPlayer().getStat().maxhp = 50;
        //c.getPlayer().getStat().maxmp = 50;
        //c.getPlayer().getStat().setHp(50);
        //c.getPlayer().getStat().setMp(50);
        statups.put(MapleStat.STR, Integer.valueOf(c.getPlayer().getStat().getStr()));
        statups.put(MapleStat.DEX, Integer.valueOf(c.getPlayer().getStat().getDex()));
        statups.put(MapleStat.LUK, Integer.valueOf(c.getPlayer().getStat().getLuk()));
        statups.put(MapleStat.INT, Integer.valueOf(c.getPlayer().getStat().getInt()));
        statups.put(MapleStat.HP, (int) c.getPlayer().getStat().getHp());
        statups.put(MapleStat.MAXHP, (int) c.getPlayer().getStat().getMaxHp());
        statups.put(MapleStat.MP, (int) c.getPlayer().getStat().getMp());
        statups.put(MapleStat.MAXMP, (int) c.getPlayer().getStat().getMaxMp());
        statups.put(MapleStat.AVAILABLEAP, (int) c.getPlayer().getRemainingAp());
        c.getPlayer().getStat().recalcLocalStats();
        c.getSession().write(MaplePacketCreator.updatePlayerStats(statups, c.getPlayer()));
        c.getPlayer().fakeRelog();
    }

    public void maxSkillsByJob() {
        c.getPlayer().maxSkillsByJob();
    }
    
    public String getServerName() {
        //return MapleParty.开服名字;//var MC = cm.etServerName();
        return ServerConfig.SERVERNAME;
    }
    
    public void gainDY(int gain) {
        c.getPlayer().modifyCSPoints(2, gain, true);
    }
    
     public final void worldMessage2(final int type, final String message) {//喇叭
        switch (type) {
            case 1: // 弹窗
            case 2: // 粉蓝色底蓝色字
            case 3: // 粉紫色底紫色字(带频道标记)
            case 5: // 无底色粉红字
            case 6: // 无底色粉蓝字
            case 9: // 无底色粉蓝字    
            case 11:// 带爱心的白色底粉红字
            case 12:// 带电话的粉蓝底素字
            case 13:// 带电话的粉蓝底素字
            case 14:// 带电话的粉蓝底素字
            case 15:// 带电话的粉蓝底素字
            case 16:// 带电话的粉蓝底素字
            case 17:// 带电话的粉蓝底素字
            case 18:// 带电话的粉蓝底素字
                World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(type, c.getChannel(), message));
                break;
            default:
                World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(6, c.getChannel(), message));
                break;
        }
    }
//黄色喇叭

    public final void 全服黄色喇叭(final String message) {
        World.Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, c.getChannel(), message));
    }
    
    //判断背包每个栏位剩余多少空格
    public boolean canHoldSlots(int slot) {
        for (int i = 1; i <= 5; i++) {
            if (this.c.getPlayer().getInventory(MapleInventoryType.getByType((byte) i)).isFull(slot)) {
                return false;
            }
        }
        return true;
    }
    
    //清理背包 道具删除
    public int getItemQuantity(int itemid) {
        return this.c.getPlayer().getItemQuantity(itemid);
    }
    
    public final int getNX(int 类型) {
        return c.getPlayer().getCSPoints(类型);
    }

    public final void gainD(final int amount) {
        c.getPlayer().modifyCSPoints(2, amount, true);
    }
    
    public final int 判断职业() {
        return c.getPlayer().getJob();
    }

    public final void 判断组队() {
        c.getPlayer().getParty();
    }

    public final void 判断频道() {
        getClient().getChannel();
    }
    
     public final void 给抵用券(final int amount) {
        c.getPlayer().modifyCSPoints(2, amount, true);
    }

    public final void 收抵用券(final int amount) {
        c.getPlayer().modifyCSPoints(2, -amount, true);
    }
    

    public final void 给点券(final int amount) {
        c.getPlayer().modifyCSPoints(1, amount, true);
    }

    public final void 收点券(final int amount) {
        c.getPlayer().modifyCSPoints(1, -amount, true);
    }
    
    public final void 给物品(final int id, final short quantity) {
        gainItem(id, quantity, false, 0, -1, "");
    }
    
     public final void 物品兑换1(final int id1, final short shuliang1, final int id2, final int shuliang2) {

        if (!haveItem(id1, shuliang1, true, true)) {
            c.getPlayer().dropMessage(1, "你没有足够的兑换物品。");
            return;
        }
        gainItem(id1, (short) -shuliang1, false, 0, -1, "");
        gainItem(id2, (short) shuliang2, false, 0, -1, "");
        c.getPlayer().dropMessage(1, "兑换成功。");
    }

    public final void 概率给物品(final int id, final short quantity, double 概率2, String a) {
        概率给物品(id, quantity, 概率2);
    }

    public final void 概率给物品(final int id, final short quantity, double 概率2) {
        if (概率2 > 100) {
            概率2 = 100;
        }
        if (概率2 <= 0) {
            概率2 = 0;
        }
        final double 概率 = Math.ceil(Math.random() * 100);
        if (概率2 > 0) {
            if (概率 <= 概率2) {
                gainItem(id, quantity, false, 0, -1, "");
            }
        }
    }

    public final void 概率给物品2(final int id, final short quantity, double 概率2, String a) {
        概率给物品2(id, quantity, 概率2);
    }

    public final void 概率给物品2(final int id, final short quantity, double 概率2) {
        if (概率2 > 100) {
            概率2 = 100;
        }
        if (概率2 <= 0) {
            概率2 = 0;
        }
        final double 概率 = Math.ceil(Math.random() * 100);
        if (概率2 > 0) {
            if (概率 <= 概率2) {
                short 数量 = (short) Math.ceil(Math.random() * quantity);
                if (数量 == 0) {
                    数量 = 1;
                }
                gainItem(id, 数量, false, 0, -1, "");
            }
        }
    }

    public final void 收物品(final int id, final short quantity) {
        gainItem(id, (short) -quantity, false, 0, -1, "");
    }
    
     public final void gainItemS(final String Owner, final int id, final int sj, final int Flag, final int str, final int dex, final int luk, final int Int, final int hp, int mp, int watk, int matk, int wdef, int mdef, int hb, int mz, int ty, int yd, final MapleClient cg) {
        if (1 >= 0) {
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final MapleInventoryType type = GameConstants.getInventoryType(id);

            if (!MapleInventoryManipulator.checkSpace(cg, id, 1, "")) {
                return;
            }
            if (type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(id) && !GameConstants.isBullet(id)) {
                final Equip item = (Equip) (ii.getEquipById(id));

                final String name = ii.getName(id);
                if (id / 10000 == 114 && name != null && name.length() > 0) { //medal
                    final String msg = "你已获得称号 <" + name + ">";
                    cg.getPlayer().dropMessage(5, msg);
                    //cg.getPlayer().dropMessage(5, msg);
                }
                if (Owner != null) {
                    item.setOwner(Owner);
                }
                if (sj > 0) {
                    item.setUpgradeSlots((byte) (short) sj);
                }
                if (Flag > 0) {
                    item.setFlag((byte) (short) Flag);
                }
                if (str > 0) {
                    item.setStr((short) str);
                }
                if (dex > 0) {
                    item.setDex((short) dex);
                }
                if (luk > 0) {
                    item.setLuk((short) luk);
                }
                if (Int > 0) {
                    item.setInt((short) Int);
                }
                if (hp > 0) {
                    item.setHp((short) hp);
                }
                if (mp > 0) {
                    item.setMp((short) mp);
                }
                if (watk > 0) {
                    item.setWatk((short) watk);
                }
                if (matk > 0) {
                    item.setMatk((short) matk);
                }
                if (wdef > 0) {
                    item.setWdef((short) wdef);
                }
                if (mdef > 0) {
                    item.setMdef((short) mdef);
                }
                if (hb > 0) {
                    item.setAvoid((short) hb);
                }
                if (mz > 0) {
                    item.setAcc((short) mz);
                }
                if (ty > 0) {
                    item.setJump((short) ty);
                }
                if (yd > 0) {
                    item.setSpeed((short) yd);
                }
                MapleInventoryManipulator.addbyItem(cg, item.copy());
            } else {
                MapleInventoryManipulator.addById(cg, id, (short) 1, "", (byte) 0);
            }
        } else {
            MapleInventoryManipulator.removeById(cg, GameConstants.getInventoryType(id), id, -1, true, false);
        }
        cg.sendPacket(MaplePacketCreator.getShowItemGain(id, (short) 1, true));
    }
    
    public final void gainItem(final String Owner, final int id, final int sj, final int Flag, final int str, final int dex, final int luk, final int Int, final int hp, int mp, int watk, int matk, int wdef, int mdef, int hb, int mz, int ty, int yd) {
        gainItemS(Owner, id, sj, Flag, str, dex, luk, Int, hp, mp, watk, matk, wdef, mdef, hb, mz, ty, yd, c);
    }

    public final void 给属性装备(final int id, final int sj, final int Flag, final int str, final int dex, final int luk, final int Int, final int hp, int mp, int watk, int matk, int wdef, int mdef, int hb, int mz, int ty, int yd) {
        给属性装备(id, sj, Flag, str, dex, luk, Int, hp, mp, watk, matk, wdef, mdef, hb, mz, ty, yd, 0, c);
    }

    public final void 给属性装备(final int id, final int sj, final int Flag, final int str, final int dex, final int luk, final int Int, final int hp, int mp, int watk, int matk, int wdef, int mdef, int hb, int mz, int ty, int yd, int 给予时间) {
        给属性装备(id, sj, Flag, str, dex, luk, Int, hp, mp, watk, matk, wdef, mdef, hb, mz, ty, yd, 给予时间, c);
    }

    public final void 给属性装备(final int id, final int sj, final int Flag, final int str, final int dex, final int luk, final int Int, final int hp, int mp, int watk, int matk, int wdef, int mdef, int hb, int mz, int ty, int yd, long 给予时间, final MapleClient cg) {
        if (1 >= 0) {
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final MapleInventoryType type = GameConstants.getInventoryType(id);

            if (!MapleInventoryManipulator.checkSpace(cg, id, 1, "")) {
                return;
            }
            if (type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(id) && !GameConstants.isBullet(id)) {
                final Equip item = (Equip) (ii.getEquipById(id));

                final String name = ii.getName(id);
                if (id / 10000 == 114 && name != null && name.length() > 0) { //medal
                    final String msg = "你已获得称号 <" + name + ">";
                    cg.getPlayer().dropMessage(5, msg);
                    //cg.getPlayer().dropMessage(5, msg);
                }
                if (sj > 0) {
                    item.setUpgradeSlots((byte) (short) sj);
                }
                if (Flag > 0) {
                    item.setFlag((byte) (short) Flag);
                }
                if (str > 0) {
                    item.setStr((short) str);
                }
                if (dex > 0) {
                    item.setDex((short) dex);
                }
                if (luk > 0) {
                    item.setLuk((short) luk);
                }
                if (Int > 0) {
                    item.setInt((short) Int);
                }
                if (hp > 0) {
                    item.setHp((short) hp);
                }
                if (mp > 0) {
                    item.setMp((short) mp);
                }
                if (watk > 0) {
                    item.setWatk((short) watk);
                }
                if (matk > 0) {
                    item.setMatk((short) matk);
                }
                if (wdef > 0) {
                    item.setWdef((short) wdef);
                }
                if (mdef > 0) {
                    item.setMdef((short) mdef);
                }
                if (hb > 0) {
                    item.setAvoid((short) hb);
                }
                if (mz > 0) {
                    item.setAcc((short) mz);
                }
                if (ty > 0) {
                    item.setJump((short) ty);
                }
                if (yd > 0) {
                    item.setSpeed((short) yd);
                }
                if (给予时间 > 0) {
                    item.setExpiration(System.currentTimeMillis() + (给予时间 * 60 * 60 * 1000));
                }
                MapleInventoryManipulator.addbyItem(cg, item.copy());
            } else {
                MapleInventoryManipulator.addById(cg, id, (short) 1, "", (byte) 0);
            }
        } else {
            MapleInventoryManipulator.removeById(cg, GameConstants.getInventoryType(id), id, -1, true, false);
        }
        cg.sendPacket(MaplePacketCreator.getShowItemGain(id, (short) 1, true));
    }
    
    public int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public int 判断日() {
        return Calendar.getInstance().get(Calendar.DATE);
    }

    public int 判断时() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public int getMin() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    public int 判断分() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    public int getSec() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }
    
    public final boolean 是否队长() {
        if (getParty() == null) {
            return false;
        }
        return getParty().getLeader().getId() == c.getPlayer().getId();
    }
    
     public final void 团队传送地图(final int mapId, final int portal) {
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            if (portal < 0) {
                warp(mapId);
            } else {
                warp(mapId, portal);
            }
            return;
        }
        final boolean rand = portal < 0;
        final MapleMap target = getMap(mapId);
        final int cMap = getPlayer().getMapId();

        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getChannelServer().getPlayerStorage().getCharacterById(chr.getId());
            if (curChar != null && (curChar.getMapId() == cMap || curChar.getEventInstance() == getPlayer().getEventInstance())) {
                if (rand) {
                    try {
                        curChar.changeMap(target, target.getPortal(Randomizer.nextInt(target.getPortals().size())));
                    } catch (Exception e) {
                        curChar.changeMap(target, target.getPortal(0));
                    }
                } else {
                    curChar.changeMap(target, target.getPortal(portal));
                }
            }
        }
    }
     
      public void 给金币(int gain) {
        c.getPlayer().gainMeso(gain, true, false, true);
    }

    public void 收金币(int gain) {
        c.getPlayer().gainMeso(-gain, true, false, true);
    }


    public void 给经验(int gain) {
        c.getPlayer().gainExp(gain, true, true, true);
    }

    public void 收经验(int gain) {
        c.getPlayer().gainExp(-gain, true, true, true);
    }
    
     public final void 给团队道具(final int id, final short quantity) {
        givePartyItems(id, quantity, false);
    }

    public final void 收团队道具(final int id, final short quantity) {
        givePartyItems2(id, quantity, false);
    }
    
    public final void givePartyItems2(final int id, final short quantity, final boolean removeAll) {
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            gainItem(id, (short) (removeAll ? -getPlayer().itemQuantity(id) : -quantity));
            return;
        }

        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                gainItem(id, (short) (removeAll ? -curChar.itemQuantity(id) : -quantity), false, 0, 0, "", curChar.getClient());
            }
        }
    }
    
    public final void 给团队经验(final int amount) {
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            gainExp(amount);
            return;
        }
        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.gainExp(amount, true, true, true);
            }
        }
    }
    
    

    public final void 给团队点券(final int amount, final List<MapleCharacter> party) {
        for (final MapleCharacter chr : party) {
            chr.modifyCSPoints(1, amount, true);
        }
    }

    public final void 给团队抵用券(final int amount, final List<MapleCharacter> party) {
        for (final MapleCharacter chr : party) {
            chr.modifyCSPoints(2, amount, true);
        }
    }

    public final void givePartyDY(final int amount) {
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            gainDY(amount);
            return;
        }
        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.modifyCSPoints(2, amount, true);
            }
        }
    }

    public final void givePartyMeso(final int amount) {
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            gainMeso(amount);
            return;
        }
        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.gainMeso(amount, true);
            }
        }
    }

    public final void 给团队金币(final int amount) {
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            gainMeso(amount);
            return;
        }
        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.gainMeso(amount, true);
            }
        }
    }

  public final void 销毁物品(final int id) {
        c.getPlayer().removeAll(id);
    }
    
  public void 打开NPC(int id, int wh) {
        NPCScriptManager.getInstance().dispose(c);
        NPCScriptManager.getInstance().start(getClient(), id, wh);
    }
  
  public final int 判断地图() {
        return c.getPlayer().getMap().getId();
    }

    public final int 判断地图指定怪物数量(final int mobid) {
        int a = 0;
        for (MapleMapObject obj : c.getPlayer().getMap().getAllMonstersThreadsafe()) {
            final MapleMonster mob = (MapleMonster) obj;
            if (mob.getId() == mobid) {
                a += 1;
            }
        }
        return a;
    }
    
    public final boolean 判断当前地图指定怪物是否存在(final int mobid) {
        for (MapleMapObject obj : c.getPlayer().getMap().getAllMonstersThreadsafe()) {
            final MapleMonster mob = (MapleMonster) obj;
            if (mob.getId() == mobid) {
                return true;
            }
        }
        return false;
    }
    
    public int 判断技能等级(int id) {
        return getPlayer().getSkillLevel(id);
    }
    
    public final void 给予技能(final int id, final byte level, final byte masterlevel) {
        getPlayer().changeSkillLevel(SkillFactory.getSkill(id), level, masterlevel);
    }
    
  

    public void 给家族GP点(final int gp) {
        if (getPlayer().getGuildId() <= 0) {
            return;
        }
        World.Guild.gainGP(getPlayer().getGuildId(), gp); //1 for
    }

   

    public int 判断家族GP点() {
        if (getPlayer().getGuildId() <= 0) {
            return 0;
        }
        return World.Guild.getGP(getPlayer().getGuildId()); //1 for
    }
    /*
     public int 判断每日值(String bossid) {
        return getPlayer().getBossLog(bossid);
    }

    public int 判断每日(String bossid) {
        return getPlayer().getBossLog(bossid);
    }


    public void 增加每日值(String bossid) {
        getPlayer().setBossLog(bossid);
    }

    public void 增加每日(String bossid) {
        getPlayer().setBossLog(bossid);
    }

    public void 给个人每日(String bossid) {
        getPlayer().setBossLog(bossid);
    }
    */
    public final void 给团队每日(String bossid) {//给团队BOOSLOG？
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            setBossLog(bossid);
            return;
        }
        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.setBossLog(bossid);
            }
        }
    }

    public int 判断团队每日(String bossid) {
        int a = 0;
        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                a += curChar.getBossLog(bossid);
            }
        }
        return a;
    }

    public int 判断队友是否在场(String bossid) {
        int a = 0;
        for (final MaplePartyCharacter chr : getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                a += curChar.getBossLog(bossid);
            }
        }
        return a;
    }
    
    public int 判断星期() {

        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

    }
    public int 获取当前星期() {

        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

    }
    
    //新增
     
    //bossrank
    /**
     * 积分排行
     *
     * @param bossname
     * @return
     */
    public List<BossRankInfo> getBossRankPointsTop(String bossname) {
        return BossRankManager.getInstance().getRank(bossname, 1);
    }

    public List<BossRankInfo1> getBossRankPointsTop1(String bossname) {
        return BossRankManager1.getInstance().getRank(bossname, 1);
    }

    public List<BossRankInfo2> getBossRankPointsTop2(String bossname) {
        return BossRankManager2.getInstance().getRank(bossname, 1);
    }

    public List<BossRankInfo3> getBossRankPointsTop3(String bossname) {
        return BossRankManager3.getInstance().getRank(bossname, 1);
    }

    public List<BossRankInfo4> getBossRankPointsTop4(String bossname) {
        return BossRankManager4.getInstance().getRank(bossname, 1);
    }

    public List<BossRankInfo5> getBossRankPointsTop5(String bossname) {
        return BossRankManager5.getInstance().getRank(bossname, 1);
    }

    public List<BossRankInfo6> getBossRankPointsTop6(String bossname) {
        return BossRankManager6.getInstance().getRank(bossname, 1);
    }

    public List<BossRankInfo7> getBossRankPointsTop7(String bossname) {
        return BossRankManager7.getInstance().getRank(bossname, 1);
    }

    public List<BossRankInfo8> getBossRankPointsTop8(String bossname) {
        return BossRankManager8.getInstance().getRank(bossname, 1);
    }

    public List<BossRankInfo9> getBossRankPointsTop9(String bossname) {
        return BossRankManager9.getInstance().getRank(bossname, 1);
    }

    public List<BossRankInfo10> getBossRankPointsTop10(String bossname) {
        return BossRankManager10.getInstance().getRank(bossname, 1);
    }

    /**
     * *******************************************************************
     */
    /**
     * *
     * 次数排行
     *
     * @param bossname
     * @return
     */
    public List<BossRankInfo> getBossRankCountTop(String bossname) {
        return BossRankManager.getInstance().getRank(bossname, 2);
    }

    public List<BossRankInfo1> getBossRankCountTop1(String bossname) {
        return BossRankManager1.getInstance().getRank(bossname, 2);
    }

    public List<BossRankInfo2> getBossRankCountTop2(String bossname) {
        return BossRankManager2.getInstance().getRank(bossname, 2);
    }

    public List<BossRankInfo3> getBossRankCountTop3(String bossname) {
        return BossRankManager3.getInstance().getRank(bossname, 2);
    }

    public List<BossRankInfo4> getBossRankCountTop4(String bossname) {
        return BossRankManager4.getInstance().getRank(bossname, 2);
    }

    public List<BossRankInfo5> getBossRankCountTop5(String bossname) {
        return BossRankManager5.getInstance().getRank(bossname, 2);
    }

    public List<BossRankInfo6> getBossRankCountTop6(String bossname) {
        return BossRankManager6.getInstance().getRank(bossname, 2);
    }

    public List<BossRankInfo7> getBossRankCountTop7(String bossname) {
        return BossRankManager7.getInstance().getRank(bossname, 2);
    }

    public List<BossRankInfo8> getBossRankCountTop8(String bossname) {
        return BossRankManager8.getInstance().getRank(bossname, 2);
    }

    public List<BossRankInfo9> getBossRankCountTop9(String bossname) {
        return BossRankManager9.getInstance().getRank(bossname, 2);
    }

    public List<BossRankInfo10> getBossRankCountTop10(String bossname) {
        return BossRankManager10.getInstance().getRank(bossname, 2);
    }

    /**
     * *******************************************************************
     */
    /**
     * 得到种类为type的排行，1为积分 2次数
     *
     * @param bossname
     * @param type
     * @return
     */
    public List<BossRankInfo> getBossRankTop(String bossname, byte type) {
        return BossRankManager.getInstance().getRank(bossname, type);
    }

    public List<BossRankInfo1> getBossRankTop1(String bossname, byte type) {
        return BossRankManager1.getInstance().getRank(bossname, type);
    }

    public List<BossRankInfo2> getBossRankTop2(String bossname, byte type) {
        return BossRankManager2.getInstance().getRank(bossname, type);
    }

    public List<BossRankInfo3> getBossRankTop3(String bossname, byte type) {
        return BossRankManager3.getInstance().getRank(bossname, type);
    }

    public List<BossRankInfo4> getBossRankTop4(String bossname, byte type) {
        return BossRankManager4.getInstance().getRank(bossname, type);
    }

    public List<BossRankInfo5> getBossRankTop5(String bossname, byte type) {
        return BossRankManager5.getInstance().getRank(bossname, type);
    }

    public List<BossRankInfo6> getBossRankTop6(String bossname, byte type) {
        return BossRankManager6.getInstance().getRank(bossname, type);
    }

    public List<BossRankInfo7> getBossRankTop7(String bossname, byte type) {
        return BossRankManager7.getInstance().getRank(bossname, type);
    }

    public List<BossRankInfo8> getBossRankTop8(String bossname, byte type) {
        return BossRankManager8.getInstance().getRank(bossname, type);
    }

    public List<BossRankInfo9> getBossRankTop9(String bossname, byte type) {
        return BossRankManager9.getInstance().getRank(bossname, type);
    }

    public List<BossRankInfo10> getBossRankTop10(String bossname, byte type) {
        return BossRankManager10.getInstance().getRank(bossname, type);
    }

    /**
     * *******************************************************************
     */
    /**
     * 增加1点积分
     *
     * @param bossname
     * @return
     */
    public int setBossRankPoints(String bossname) {
        return setBossRank(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, 1);
    }

    public int setBossRankPoints1(String bossname) {
        return setBossRank1(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, 1);
    }

    public int setBossRankPoints2(String bossname) {
        return setBossRank2(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, 1);
    }

    public int setBossRankPoints3(String bossname) {
        return setBossRank3(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, 1);
    }

    public int setBossRankPoints4(String bossname) {
        return setBossRank4(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, 1);
    }

    public int setBossRankPoints5(String bossname) {
        return setBossRank5(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, 1);
    }

    public int setBossRankPoints6(String bossname) {
        return setBossRank6(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, 1);
    }

    public int setBossRankPoints7(String bossname) {
        return setBossRank7(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, 1);
    }

    public int setBossRankPoints8(String bossname) {
        return setBossRank8(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, 1);
    }

    public int setBossRankPoints9(String bossname) {
        return setBossRank9(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, 1);
    }

    public int setBossRankPoints10(String bossname) {
        return setBossRank10(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, 1);
    }

    /**
     * *******************************************************************
     */
    /**
     * 增加1点次数
     *
     * @param bossname
     * @return
     */
    public int setBossRankCount(String bossname) {
        return setBossRank(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, 1);
    }

    public int setBossRankCount1(String bossname) {
        return setBossRank1(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, 1);
    }

    public int setBossRankCount2(String bossname) {
        return setBossRank2(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, 1);
    }

    public int setBossRankCount3(String bossname) {
        return setBossRank3(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, 1);
    }

    public int setBossRankCount4(String bossname) {
        return setBossRank4(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, 1);
    }

    public int setBossRankCount5(String bossname) {
        return setBossRank5(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, 1);
    }

    public int setBossRankCount6(String bossname) {
        return setBossRank6(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, 1);
    }

    public int setBossRankCount7(String bossname) {
        return setBossRank7(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, 1);
    }

    public int setBossRankCount8(String bossname) {
        return setBossRank8(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, 1);
    }

    public int setBossRankCount9(String bossname) {
        return setBossRank9(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, 1);
    }

    public int setBossRankCount10(String bossname) {
        return setBossRank10(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, 1);
    }

    /**
     * *******************************************************************
     */
    /**
     * 增加指定数量积分
     *
     * @param bossname
     * @param add
     * @return
     */
    public int setBossRankPoints(String bossname, int add) {
        return setBossRank(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, add);
    }

    public int setBossRankPoints1(String bossname, int add) {
        return setBossRank1(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, add);
    }

    public int setBossRankPoints2(String bossname, int add) {
        return setBossRank2(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, add);
    }

    public int setBossRankPoints3(String bossname, int add) {
        return setBossRank3(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, add);
    }

    public int setBossRankPoints4(String bossname, int add) {
        return setBossRank4(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, add);
    }

    public int setBossRankPoints5(String bossname, int add) {
        return setBossRank5(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, add);
    }

    public int setBossRankPoints6(String bossname, int add) {
        return setBossRank6(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, add);
    }

    public int setBossRankPoints7(String bossname, int add) {
        return setBossRank7(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, add);
    }

    public int setBossRankPoints8(String bossname, int add) {
        return setBossRank8(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, add);
    }

    public int setBossRankPoints9(String bossname, int add) {
        return setBossRank9(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, add);
    }

    public int setBossRankPoints10(String bossname, int add) {
        return setBossRank10(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 1, add);
    }

    /**
     * *******************************************************************
     */
    /**
     * 增加指定数量次数
     *
     * @param bossname
     * @param add
     * @return
     */
    public int setBossRankCount(String bossname, int add) {
        return setBossRank(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, add);
    }

    public int setBossRankCount1(String bossname, int add) {
        return setBossRank1(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, add);
    }

    public int setBossRankCount2(String bossname, int add) {
        return setBossRank2(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, add);
    }

    public int setBossRankCount3(String bossname, int add) {
        return setBossRank3(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, add);
    }

    public int setBossRankCount4(String bossname, int add) {
        return setBossRank4(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, add);
    }

    public int setBossRankCount5(String bossname, int add) {
        return setBossRank5(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, add);
    }

    public int setBossRankCount6(String bossname, int add) {
        return setBossRank6(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, add);
    }

    public int setBossRankCount7(String bossname, int add) {
        return setBossRank7(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, add);
    }

    public int setBossRankCount8(String bossname, int add) {
        return setBossRank8(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, add);
    }

    public int setBossRankCount9(String bossname, int add) {
        return setBossRank9(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, add);
    }

    public int setBossRankCount10(String bossname, int add) {
        return setBossRank10(getPlayer().getId(), getPlayer().getName(), bossname, (byte) 2, add);
    }

    /**
     * *******************************************************************
     * @param add
     * @return
     */
    public int 任务(int sj, int add) {
        return setBossRank8(getPlayer().getId(), getPlayer().getName(), "赛季积分", (byte) sj, add);
    }

    public int 给赛季积分(int sj, int add) {
        return setBossRank8(getPlayer().getId(), getPlayer().getName(), "赛季积分", (byte) sj, add);
    }

    public int 给SSP点(int add) {
        return setBossRank6(getPlayer().getId(), getPlayer().getName(), "超级技能点", (byte) 2, add);
    }

    public int 收SSP点(int add) {
        return setBossRank6(getPlayer().getId(), getPlayer().getName(), "超级技能点", (byte) 2, -add);
    }

    public int 给炼金经验(int add) {
        return setBossRank5(getPlayer().getId(), getPlayer().getName(), "炼金经验", (byte) 2, add);
    }

    public int 给唠叨经验(int add) {
        return setBossRank4(getPlayer().getId(), getPlayer().getName(), "唠叨经验", (byte) 2, add);
    }

    public int 给泡点经验(int add) {
        return setBossRank3(getPlayer().getId(), getPlayer().getName(), "泡点经验", (byte) 2, add);
    }

    public int 给挖矿经验(int add) {
        return setBossRank2(getPlayer().getId(), getPlayer().getName(), "挖矿经验", (byte) 2, add);
    }

    public int 给钓鱼经验(int add) {
        return setBossRank1(getPlayer().getId(), getPlayer().getName(), "钓鱼经验", (byte) 2, add);
    }

    /**
     * *******************************************************************
     */
    /**
     * 增加指定type类型add数量 (type 1:积分 2:次数 )
     *
     * @param bossname
     * @param type 1:积分 2:次数
     * @param add
     * @return
     */
    /**
     * *******************************************************************
     */
    public int setBossRank(String bossname, byte type, int add) {
        return setBossRank(getPlayer().getId(), getPlayer().getName(), bossname, type, add);
    }

    public int setBossRank1(String bossname, byte type, int add) {
        return setBossRank1(getPlayer().getId(), getPlayer().getName(), bossname, type, add);
    }

    public int setBossRank2(String bossname, byte type, int add) {
        return setBossRank2(getPlayer().getId(), getPlayer().getName(), bossname, type, add);
    }

    public int setBossRank3(String bossname, byte type, int add) {
        return setBossRank3(getPlayer().getId(), getPlayer().getName(), bossname, type, add);
    }

    public int setBossRank4(String bossname, byte type, int add) {
        return setBossRank4(getPlayer().getId(), getPlayer().getName(), bossname, type, add);
    }

    public int setBossRank5(String bossname, byte type, int add) {
        return setBossRank5(getPlayer().getId(), getPlayer().getName(), bossname, type, add);
    }

    public int setBossRank6(String bossname, byte type, int add) {
        return setBossRank6(getPlayer().getId(), getPlayer().getName(), bossname, type, add);
    }

    public int setBossRank7(String bossname, byte type, int add) {
        return setBossRank7(getPlayer().getId(), getPlayer().getName(), bossname, type, add);
    }

    public int setBossRank8(String bossname, byte type, int add) {
        return setBossRank8(getPlayer().getId(), getPlayer().getName(), bossname, type, add);
    }

    public int setBossRank9(String bossname, byte type, int add) {
        return setBossRank9(getPlayer().getId(), getPlayer().getName(), bossname, type, add);
    }

    public int setBossRank10(String bossname, byte type, int add) {
        return setBossRank10(getPlayer().getId(), getPlayer().getName(), bossname, type, add);
    }

    /**
     * *******************************************************************
     */
    /**
     * 增加指定玩家指定type的数量
     *
     * @param cid 玩家ID
     * @param cname 玩家名
     * @param bossname
     * @param type 1:积分 2:次数
     * @param add 数量
     * @return
     */
    /**
     * *******************************************************************
     */
    public int setBossRank(int cid, String cname, String bossname, byte type, int add) {
        return BossRankManager.getInstance().setLog(cid, cname, bossname, type, add);
    }

    public int setBossRank1(int cid, String cname, String bossname, byte type, int add) {
        return BossRankManager1.getInstance().setLog(cid, cname, bossname, type, add);
    }

    public int setBossRank2(int cid, String cname, String bossname, byte type, int add) {
        return BossRankManager2.getInstance().setLog(cid, cname, bossname, type, add);
    }

    public int setBossRank3(int cid, String cname, String bossname, byte type, int add) {
        return BossRankManager3.getInstance().setLog(cid, cname, bossname, type, add);
    }

    public int setBossRank4(int cid, String cname, String bossname, byte type, int add) {
        return BossRankManager4.getInstance().setLog(cid, cname, bossname, type, add);
    }

    public int setBossRank5(int cid, String cname, String bossname, byte type, int add) {
        return BossRankManager5.getInstance().setLog(cid, cname, bossname, type, add);
    }

    public int setBossRank6(int cid, String cname, String bossname, byte type, int add) {
        return BossRankManager6.getInstance().setLog(cid, cname, bossname, type, add);
    }

    public int setBossRank7(int cid, String cname, String bossname, byte type, int add) {
        return BossRankManager7.getInstance().setLog(cid, cname, bossname, type, add);
    }

    public int setBossRank8(int cid, String cname, String bossname, byte type, int add) {
        return BossRankManager8.getInstance().setLog(cid, cname, bossname, type, add);
    }

    public int setBossRank9(int cid, String cname, String bossname, byte type, int add) {
        return BossRankManager9.getInstance().setLog(cid, cname, bossname, type, add);
    }

    public int setBossRank10(int cid, String cname, String bossname, byte type, int add) {
        return BossRankManager10.getInstance().setLog(cid, cname, bossname, type, add);
    }

    /**
     * *******************************************************************
     */
    public int getBossRankPoints(String bossname) {
        return getBossRank(bossname, (byte) 1);
    }

    public int getBossRankPoints1(String bossname) {
        return getBossRank1(bossname, (byte) 1);
    }

    public int getBossRankPoints2(String bossname) {
        return getBossRank2(bossname, (byte) 1);
    }

    public int getBossRankPoints3(String bossname) {
        return getBossRank3(bossname, (byte) 1);
    }

    public int getBossRankPoints4(String bossname) {
        return getBossRank4(bossname, (byte) 1);
    }

    public int getBossRankPoints5(String bossname) {
        return getBossRank5(bossname, (byte) 1);
    }

    public int getBossRankPoints6(String bossname) {
        return getBossRank6(bossname, (byte) 1);
    }

    public int getBossRankPoints7(String bossname) {
        return getBossRank7(bossname, (byte) 1);
    }

    public int getBossRankPoints8(String bossname) {
        return getBossRank8(bossname, (byte) 1);
    }

    public int getBossRankPoints9(String bossname) {
        return getBossRank9(bossname, (byte) 1);
    }

    public int getBossRankPoints10(String bossname) {
        return getBossRank10(bossname, (byte) 1);
    }

    /**
     * *******************************************************************
     */
    public int getBossRankCount(String bossname) {
        return getBossRank(bossname, (byte) 2);
    }

    public int getBossRankCount1(String bossname) {
        return getBossRank1(bossname, (byte) 2);
    }

    public int getBossRankCount2(String bossname) {
        return getBossRank2(bossname, (byte) 2);
    }

    public int getBossRankCount3(String bossname) {
        return getBossRank3(bossname, (byte) 2);
    }

    public int getBossRankCount4(String bossname) {
        return getBossRank4(bossname, (byte) 2);
    }

    public int getBossRankCount5(String bossname) {
        return getBossRank5(bossname, (byte) 2);
    }

    public int getBossRankCount6(String bossname) {
        return getBossRank6(bossname, (byte) 2);
    }

    public int getBossRankCount7(String bossname) {
        return getBossRank7(bossname, (byte) 2);
    }

    public int getBossRankCount8(String bossname) {
        return getBossRank8(bossname, (byte) 2);
    }

    public int getBossRankCount9(String bossname) {
        return getBossRank9(bossname, (byte) 2);
    }

    public int getBossRankCount10(String bossname) {
        return getBossRank10(bossname, (byte) 2);
    }

    /**
     * *******************************************************************
     * @param bossname
     * @param type
     * @return
     */
    public int getBossRank(String bossname, byte type) {
        return getBossRank(getPlayer().getId(), bossname, type);
    }

    public int getBossRank1(String bossname, byte type) {
        return getBossRank1(getPlayer().getId(), bossname, type);
    }

    public int getBossRank2(String bossname, byte type) {
        return getBossRank2(getPlayer().getId(), bossname, type);
    }

    public int getBossRank3(String bossname, byte type) {
        return getBossRank3(getPlayer().getId(), bossname, type);
    }

    public int getBossRank4(String bossname, byte type) {
        return getBossRank4(getPlayer().getId(), bossname, type);
    }

    public int getBossRank5(String bossname, byte type) {
        return getBossRank5(getPlayer().getId(), bossname, type);
    }

    public int getBossRank6(String bossname, byte type) {
        return getBossRank6(getPlayer().getId(), bossname, type);
    }

    public int getBossRank7(String bossname, byte type) {
        return getBossRank7(getPlayer().getId(), bossname, type);
    }

    public int getBossRank8(String bossname, byte type) {
        return getBossRank8(getPlayer().getId(), bossname, type);
    }

    public int getBossRank9(String bossname, byte type) {
        return getBossRank9(getPlayer().getId(), bossname, type);
    }

    public int getBossRank10(String bossname, byte type) {
        return getBossRank10(getPlayer().getId(), bossname, type);
    }

    /**
     * *******************************************************************
     */
    public int getBossRank(int cid, String bossname, byte type) {
        int ret = -1;
        BossRankInfo info = BossRankManager.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1://积分
                ret = info.getPoints();
                break;
            case 2://次数
                ret = info.getCount();
                break;
        }
        return ret;
    }

    public int getBossRank1(int cid, String bossname, byte type) {
        int ret = -1;
        BossRankInfo1 info = BossRankManager1.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1://积分
                ret = info.getPoints();
                break;
            case 2://次数
                ret = info.getCount();
                break;
        }
        return ret;
    }

    public int getBossRank2(int cid, String bossname, byte type) {
        int ret = -1;
        BossRankInfo2 info = BossRankManager2.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1://积分
                ret = info.getPoints();
                break;
            case 2://次数
                ret = info.getCount();
                break;
        }
        return ret;
    }

    public int getBossRank3(int cid, String bossname, byte type) {
        int ret = -1;
        BossRankInfo3 info = BossRankManager3.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1://积分
                ret = info.getPoints();
                break;
            case 2://次数
                ret = info.getCount();
                break;
        }
        return ret;
    }

    public int getBossRank4(int cid, String bossname, byte type) {
        int ret = -1;
        BossRankInfo4 info = BossRankManager4.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1://积分
                ret = info.getPoints();
                break;
            case 2://次数
                ret = info.getCount();
                break;
        }
        return ret;
    }

    public int getBossRank5(int cid, String bossname, byte type) {
        int ret = -1;
        BossRankInfo5 info = BossRankManager5.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1://积分
                ret = info.getPoints();
                break;
            case 2://次数
                ret = info.getCount();
                break;
        }
        return ret;
    }

    public int getBossRank6(int cid, String bossname, byte type) {
        int ret = -1;
        BossRankInfo6 info = BossRankManager6.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1://积分
                ret = info.getPoints();
                break;
            case 2://次数
                ret = info.getCount();
                break;
        }
        return ret;
    }

    public int getBossRank7(int cid, String bossname, byte type) {
        int ret = -1;
        BossRankInfo7 info = BossRankManager7.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1://积分
                ret = info.getPoints();
                break;
            case 2://次数
                ret = info.getCount();
                break;
        }
        return ret;
    }

    public int getBossRank8(int cid, String bossname, byte type) {
        int ret = -1;
        BossRankInfo8 info = BossRankManager8.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1://积分
                ret = info.getPoints();
                break;
            case 2://次数
                ret = info.getCount();
                break;
        }
        return ret;
    }

    public int getBossRank9(int cid, String bossname, byte type) {
        int ret = -1;
        BossRankInfo9 info = BossRankManager9.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1://积分
                ret = info.getPoints();
                break;
            case 2://次数
                ret = info.getCount();
                break;
        }
        return ret;
    }

    public int getBossRank10(int cid, String bossname, byte type) {
        int ret = -1;
        BossRankInfo10 info = BossRankManager10.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1://积分
                ret = info.getPoints();
                break;
            case 2://次数
                ret = info.getCount();
                break;
        }
        return ret;
    }

    //BankItem
    /**
     * 获取背包种类道具集合
     *
     * @param type
     * @return
     */
    public List<IItem> getItemsByType(byte type) {
        List<IItem> items = new ArrayList<>();
        final MapleInventoryType itemtype = MapleInventoryType.getByType(type);
        final MapleInventory mi = getPlayer().getInventory(itemtype);
        if (mi != null) {
            for (IItem item : mi.list()) {
                items.add(item);
            }
        }
        return items;
    }

    public List<IItem> getItemsByType1(byte type) {
        List<IItem> items = new ArrayList<>();
        final MapleInventoryType itemtype = MapleInventoryType.getByType(type);
        final MapleInventory mi = getPlayer().getInventory(itemtype);
        if (mi != null) {
            for (IItem item : mi.list()) {
                items.add(item);
            }
        }
        return items;
    }

    public List<IItem> getItemsByType2(byte type) {
        List<IItem> items = new ArrayList<>();
        final MapleInventoryType itemtype = MapleInventoryType.getByType(type);
        final MapleInventory mi = getPlayer().getInventory(itemtype);
        if (mi != null) {
            for (IItem item : mi.list()) {
                items.add(item);
            }
        }
        return items;
    }

    /**
     * 存指定道具
     *
     * @param item
     * @param count
     * @return
     */
    public int saveBankItem(IItem item, short count) {
        return BankItemManager.getInstance().saveItem(getPlayer(), item, count);
    }

    public int saveBankItem1(IItem item, short count) {
        return BankItemManager1.getInstance().saveItem(getPlayer(), item, count);
    }

    public int saveBankItem2(IItem item, short count) {
        return BankItemManager2.getInstance().saveItem(getPlayer(), item, count);
    }

    /**
     * 获取存储的道具列表
     *
     * @return
     */
    public List<BankItem> getBankItems() {
        return BankItemManager.getInstance().getItems(getPlayer().getId());
    }

    public List<BankItem1> getBankItems1() {
        return BankItemManager1.getInstance().getItems(getPlayer().getguildid());
    }

    public List<BankItem2> getBankItems2() {
        return BankItemManager2.getInstance().getItems(getPlayer().getId());
    }

    /**
     * 获取点数
     *
     * @return
     */
    public int GetPiot(String Name, int Channale) {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM FullPoint WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public int Getsaiji(String Name, int Channale) {
        int ret = -1;
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM saiji WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public void Gainsaiji(String Name, int Channale, int saiji) {
        try {
            int ret = Getsaiji(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO saiji (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);

                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e);
                    }
                }
            }
            ret += saiji;
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE saiji SET `Point` = ? WHERE Name = ? and channel = ?");
            ps.setInt(1, ret);
            ps.setString(2, Name);
            ps.setInt(3, Channale);
            ps.execute();
            ps.close();
        } catch (SQLException sql) {
            System.err.println("獲取錯誤!!55" + sql);
        }
    }

    public List<ArrayList> getSevenDayPayLog(int day) {
        List ret = new ArrayList<>();
        for (int i = 0; i < day; i++) {
            ret.add(0);
        }
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM paylog WHERE account = ?");
            ps.setString(1, c.getAccountName());
            ResultSet rs = ps.executeQuery();
            
            Timestamp currtime = new Timestamp(System.currentTimeMillis());
            while (rs.next()) {
                int rmb = rs.getInt("rmb");
                Timestamp time = rs.getTimestamp("paytime");
                int diffday = (int) ((currtime.getTime() - time.getTime()) / (1000 * 60 * 60 * 24));
                if (diffday < day) {
                    ret.set(diffday, (int) (ret.get(diffday)) + rmb);
                }
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("获取充值记录失败" + e);
        }
        return ret;
    }
    
//    public List<CharNameAndId> getPayRankingTop() {
//        List<CharNameAndId> ret = new LinkedList<>();
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, -(cal.get(Calendar.DAY_OF_WEEK) - 1));
//        
//        try {
//            Connection con = DatabaseConnection.getConnection();
//            PreparedStatement ps = con.prepareStatement("SELECT SUM(rmb) FROM paylog WHERE ORDER BY rmb DESC LIMIT 10");
//            ps.setString(1, c.getAccountName());
//            ResultSet rs = ps.executeQuery();
//            
//            Timestamp currtime = new Timestamp(System.currentTimeMillis());
//            while (rs.next()) {
//                int rmb = rs.getInt("rmb");
//                Timestamp time = rs.getTimestamp("paytime");
//                int diffday = (int) ((currtime.getTime() - time.getTime()) / (1000 * 60 * 60 * 24));
//                if (diffday < day) {
//                    ret.set(diffday, (int) (ret.get(diffday)) + rmb);
//                }
//            }
//            ps.close();
//            rs.close();
//        } catch (SQLException e) {
//            System.err.println("获取充值记录失败" + e);
//        }
//        return ret;
//    }
    
     public int getRMB() {
        return getPlayer().getRMB();
    }
    
    public void setRMB(int rmb) {
        getPlayer().setRMB(rmb);
    }
    
    public void gainRMB(int rmb) {
        getPlayer().gainRMB(rmb);
    }
    
    public int getTotalRMB() {
        return getPlayer().getTotalRMB();
    }

    public MapleItemInformationProvider getItemInfo() {
        return MapleItemInformationProvider.getInstance();
    }
    
    public int getDaysPQLog(String pqName, int days) {
        return getPlayer().getDaysPQLog(pqName, 0, days);
    }

    public int getPQLog(String pqName) {
        return getPlayer().getPQLog(pqName);
    }

    public int getPQLog(String pqName, int type) {
        return getPlayer().getPQLog(pqName, type);
    }

    public int getPQLog(String pqName, int type, int days) {
        return getPlayer().getDaysPQLog(pqName, type, days);
    }

    public void setPQLog(String pqName) {
        getPlayer().setPQLog(pqName);
    }

    public void setPQLog(String pqName, int type) {
        getPlayer().setPQLog(pqName, type);
    }

    public void setPQLog(String pqName, int type, int count) {
        getPlayer().setPQLog(pqName, type, count);
    }

    public void resetPQLog(String pqName) {
        getPlayer().resetPQLog(pqName);
    }

    public void resetPQLog(String pqName, int type) {
        getPlayer().resetPQLog(pqName, type);
    }

    public void setPartyPQLog(String pqName) {
        this.setPartyPQLog(pqName, 0);
    }

    public void setPartyPQLog(String pqName, int type) {
        this.setPartyPQLog(pqName, type, 1);
    }

    public void setPartyPQLog(String pqName, int type, int count) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            getPlayer().setPQLog(pqName, type, count);
            return;
        }
        int n4 = this.getPlayer().getMapId();
        for (MaplePartyCharacter partyCharacter : this.getPlayer().getParty().getMembers()) {
            MapleCharacter player = this.getPlayer().getMap().getCharacterById(partyCharacter.getId());
            if (player == null || player.getMapId() != n4) continue;
            player.setPQLog(pqName, type, count);
        }
    }

    /*
     * 新增函数
     */
    public int getEventCount(String eventId) {
        return c.getPlayer().getEventCount(eventId);
    }

    public int getEventCount(String eventId, int type) {
        return c.getPlayer().getEventCount(eventId, type);
    }

    public void setEventCount(String eventId) {
        c.getPlayer().setEventCount(eventId);
    }

    public void setEventCount(String eventId, int type) {
        c.getPlayer().setEventCount(eventId, type);
    }

    public void setEventCount(String eventId, int type, int count) {
        c.getPlayer().setEventCount(eventId, type, count);
    }

    public void resetEventCount(String eventId) {
        c.getPlayer().resetEventCount(eventId);
    }

    public void resetEventCount(String eventId, int type) {
        c.getPlayer().resetEventCount(eventId, type);
    }

    public void setPartyEventCount(String eventId) {
        setPartyEventCount(eventId, 0);
    }

    public void setPartyEventCount(String eventId, int type) {
        setPartyEventCount(eventId, type, 1);
    }

    public void setPartyEventCount(String eventId, int type, int count) {
        if (getPlayer().getParty() == null || getPlayer().getParty().getMembers().size() == 1) {
            c.getPlayer().setEventCount(eventId, type, count);
            return;
        }
        int checkMap = getPlayer().getMapId();
        for (MaplePartyCharacter partyPlayer : getPlayer().getParty().getMembers()) {
            MapleCharacter chr = getPlayer().getMap().getCharacterById(partyPlayer.getId());
            if (chr != null && chr.getMapId() == checkMap) {
                chr.setEventCount(eventId, type, count);
            }
        }
    }

    public boolean checkPartyEventCount(String eventId) {
        return checkPartyEventCount(eventId, 1);
    }

    public boolean checkPartyEventCount(String eventId, int checkcount) {
        MapleParty party = c.getPlayer().getParty();
        int count;
        if (party == null || party.getMembers().size() == 1) {
            count = getEventCount(eventId);
            return count >= 0 && count < checkcount;
        }
        int check = 0;
        int partySize = party.getMembers().size();
        for (MaplePartyCharacter partyPlayer : party.getMembers()) {
            MapleCharacter chr = getPlayer().getMap().getCharacterById(partyPlayer.getId());
            if (chr != null) {
                count = chr.getEventCount(eventId);
                if (count >= 0 && count < checkcount) {
                    check++;
                }
            }
        }
        return partySize == check;
    }
    
}
