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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import javax.script.ScriptException;

import client.MapleCharacter;
import client.MapleClient;
import client.MapleQuestStatus;
import constants.ServerConfig;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import handling.world.MaplePartyCharacter;
import java.util.Calendar;
import java.util.Collections;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import server.MapleCarnivalParty;
import server.MapleItemInformationProvider;
import server.MapleSquad;
import server.Timer.EventTimer;
import server.quest.MapleQuest;
import server.life.MapleMonster;
import server.maps.MapleMap;
import server.maps.MapleMapFactory;
import tools.FilePrinter;
import tools.MaplePacketCreator;
import tools.packet.UIPacket;

public class EventInstanceManager {

    private List<MapleCharacter> chars = new LinkedList<>(); //this is messy
    private List<Integer> dced = new LinkedList<>();
    private List<MapleMonster> mobs = new LinkedList<>();
    private Map<Integer, Integer> killCount = new HashMap<>();
    private final EventManager em;
    private final int channel;
    private final String name;
    private Properties props = new Properties();
    private long timeStarted = 0;
    private long eventTime = 0;
    private List<Integer> mapIds = new LinkedList<>();
    private List<Boolean> isInstanced = new LinkedList<>();
    private ScheduledFuture<?> eventTimer;
    private final ReentrantReadWriteLock mutex = new ReentrantReadWriteLock();
    private final Lock rL = mutex.readLock(), wL = mutex.writeLock();
    private boolean disposed = false;
    private MapleClient c;

    public EventInstanceManager(EventManager em, String name, int channel) {
        this.em = em;
        this.name = name;
        this.channel = channel;
    }

    public void registerPlayer(MapleCharacter chr) {
        if (disposed || chr == null) {
            return;
        }
        try {
            wL.lock();
            try {
                chars.add(chr);
            } finally {
                wL.unlock();
            }
            chr.setEventInstance(this);
            em.getIv().invokeFunction("playerEntry", this, chr);

        } catch (NullPointerException ex) {
            FilePrinter.printError("EventInstanceManager.txt", ex);

        } catch (RejectedExecutionException ex) {
        } catch (ScriptException | NoSuchMethodException ex) {
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : playerEntry:\n" + ex);
            System.err.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : playerEntry:\n" + ex);
        }
    }

    public void changedMap(final MapleCharacter chr, final int mapid) {
        if (disposed) {
            return;
        }
        try {
            em.getIv().invokeFunction("changedMap", this, chr, mapid);
        } catch (NullPointerException npe) {
        } catch (Exception ex) {
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : changedMap:\n" + ex);
            System.err.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : changedMap:\n" + ex);
        }
    }

    public void timeOut(final long delay, final EventInstanceManager eim) {
        if (disposed || eim == null) {
            return;
        }
        eventTimer = EventTimer.getInstance().schedule(new Runnable() {

            public void run() {
                if (disposed || eim == null || em == null) {
                    return;
                }
                try {
                    em.getIv().invokeFunction("scheduledTimeout", eim);
                } catch (Exception ex) {
                    FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : scheduledTimeout:\n" + ex);
                    System.err.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : scheduledTimeout:\n" + ex);
                }
            }
        }, delay);
    }

    public void stopEventTimer() {
        eventTime = 0;
        timeStarted = 0;
        if (eventTimer != null) {
            eventTimer.cancel(false);
        }
    }

    public void restartEventTimer(long time) {
        try {
            if (disposed) {
                return;
            }
            timeStarted = System.currentTimeMillis();
            eventTime = time;
            if (eventTimer != null) {
                eventTimer.cancel(false);
            }
            eventTimer = null;
            final int timesend = (int) time / 1000;

            for (MapleCharacter chr : getPlayers()) {
                chr.getClient().sendPacket(MaplePacketCreator.getClock(timesend));
            }
            timeOut(time, this);
        } catch (Exception ex) {
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : restartEventTimer:\n" + ex);
            System.err.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : restartEventTimer:\n");
            ex.printStackTrace();
        }
    }

    public void startEventTimer(long time) {
        restartEventTimer(time); //just incase
    }

    public boolean isTimerStarted() {
        return eventTime > 0 && timeStarted > 0;
    }

    public long getTimeLeft() {
        return eventTime - (System.currentTimeMillis() - timeStarted);
    }

    public void registerParty(MapleParty party, MapleMap map) {
        if (disposed) {
            return;
        }
        for (MaplePartyCharacter pc : party.getMembers()) {
            MapleCharacter c = map.getCharacterById(pc.getId());
            registerPlayer(c);
        }
    }

    public void unregisterPlayer(final MapleCharacter chr) {
        if (disposed) {
            chr.setEventInstance(null);
            return;
        }
        wL.lock();
        try {
            unregisterPlayer_NoLock(chr);
        } finally {
            wL.unlock();
        }
    }

    private boolean unregisterPlayer_NoLock(final MapleCharacter chr) {
        if (name.equals("CWKPQ")) { //hard code it because i said so
            final MapleSquad squad = ChannelServer.getInstance(channel).getMapleSquad("CWKPQ");//so fkin hacky
            if (squad != null) {
                squad.removeMember(chr.getName());
                if (squad.getLeaderName().equals(chr.getName())) {
                    em.setProperty("leader", "false");
                }
            }
        }
        chr.setEventInstance(null);
        if (disposed) {
            return false;
        }
        if (chars.contains(chr)) {
            chars.remove(chr);
            return true;
        }
        return false;
    }

    public boolean check() {
        for (MapleCharacter chr : getPlayers()) {
            if (!(chr.getLevel() >= 30 && chr.getLevel() <= 50)) {
                return false;
            }
        }
        return true;
    }

    public boolean check1() {
        for (MapleCharacter chr : getPlayers()) {
            if (!(chr.getLevel() >= 51 && chr.getLevel() <= 120)) {
                return false;
            }
        }
        return true;
    }

    public final boolean disposeIfPlayerBelow(final byte size, final int towarp) {
        if (disposed) {
            return true;
        }
        MapleMap map = null;
        if (towarp > 0) {
            map = this.getMapFactory().getMap(towarp);
        }

        wL.lock();
        try {
            if (chars.size() <= size) {
                final List<MapleCharacter> chrs = new LinkedList<>(chars);
                for (MapleCharacter chr : chrs) {
                    unregisterPlayer_NoLock(chr);
                    if (towarp > 0) {
                        chr.changeMap(map, map.getPortal(0));
                    }
                }
                dispose_NoLock();
                return true;
            }
        } finally {
            wL.unlock();
        }
        return false;
    }

    public final void saveBossQuest(final int points) {
        if (disposed) {
            return;
        }
        for (MapleCharacter chr : getPlayers()) {
            final MapleQuestStatus record = chr.getQuestNAdd(MapleQuest.getInstance(150001));

            if (record.getCustomData() != null) {
                record.setCustomData(String.valueOf(points + Integer.parseInt(record.getCustomData())));
            } else {
                record.setCustomData(String.valueOf(points)); // First time
            }
        }
    }

    public final void saveNX(final int points) {
        if (disposed) {
            return;
        }
        for (MapleCharacter chr : getPlayers()) {
            chr.modifyCSPoints(1, points, true);
        }
    }

    public List<MapleCharacter> getPlayers() {
        if (disposed) {
            return Collections.emptyList();
        }
        rL.lock();
        try {
            return new LinkedList<>(chars);
        } finally {
            rL.unlock();
        }
    }

    public List<Integer> getDisconnected() {
        return dced;
    }

    public final int getPlayerCount() {
        if (disposed) {
            return 0;
        }
        return chars.size();
    }

    public void registerMonster(MapleMonster mob) {
        if (disposed) {
            return;
        }
        mobs.add(mob);
        mob.setEventInstance(this);
    }

    public void unregisterMonster(MapleMonster mob) {
        mob.setEventInstance(null);
        if (disposed) {
            return;
        }
        mobs.remove(mob);
        if (mobs.isEmpty()) {
            try {
                em.getIv().invokeFunction("allMonstersDead", this);
            } catch (RejectedExecutionException ex) {
            } catch (ScriptException | NoSuchMethodException ex) {
                FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : allMonstersDead:\n" + ex);
                System.err.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : allMonstersDead:\n" + ex);
            }
        }
    }

    public void playerKilled(MapleCharacter chr) {
        if (disposed) {
            return;
        }
        try {
            em.getIv().invokeFunction("playerDead", this, chr);
        } catch (RejectedExecutionException ex) {
        } catch (ScriptException | NoSuchMethodException ex) {
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : playerDead:\n" + ex);
            System.err.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : playerDead:\n" + ex);
        }
    }

    public boolean revivePlayer(MapleCharacter chr) {
        if (disposed) {
            return false;
        }
        try {
            Object b = em.getIv().invokeFunction("playerRevive", this, chr);
            if (b instanceof Boolean) {
                return (Boolean) b;
            }
        } catch (RejectedExecutionException ex) {
        } catch (ScriptException | NoSuchMethodException ex) {
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : playerRevive:\n" + ex);
            System.err.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : playerRevive:\n" + ex);
        }
        return true;
    }

    public void playerDisconnected(final MapleCharacter chr, int idz) {
        if (disposed) {
            return;
        }
        byte ret;
        try {
            ret = ((Double) em.getIv().invokeFunction("playerDisconnected", this, chr)).byteValue();
        } catch (Exception e) {
            ret = 0;
        }

        wL.lock();
        try {
            if (disposed) {
                return;
            }
            dced.add(idz);
            if (chr != null) {
                unregisterPlayer_NoLock(chr);
            }
            if (ret == 0) {
                if (getPlayerCount() <= 0) {
                    dispose_NoLock();
                }
            } else if ((ret > 0 && getPlayerCount() < ret) || (ret < 0 && (isLeader(chr) || getPlayerCount() < (ret * -1)))) {
                final List<MapleCharacter> chrs = new LinkedList<>(chars);
                for (MapleCharacter player : chrs) {
                    if (player.getId() != idz) {
                        removePlayer(player);
                    }
                }
                dispose_NoLock();
            }
        } catch (Exception ex) {
            FilePrinter.printError("EventInstanceManager.txt", ex);

        } finally {
            wL.unlock();
        }

    }

    /**
     *
     * @param chr
     * @param mob
     */
    public void monsterKilled(final MapleCharacter chr, final MapleMonster mob) {
        if (disposed) {
            return;
        }
        try {
            Integer kc = killCount.get(chr.getId());
            int inc = (int) (em.getIv().invokeFunction("monsterValue", this, mob.getId()));
            if (disposed) {
                return;
            }
            if (kc == null) {
                kc = inc;
            } else {
                kc += inc;
            }
            killCount.put(chr.getId(), kc);
            if (chr.getCarnivalParty() != null && (mob.getStats().getPoint() > 0 || mob.getStats().getCP() > 0)) {
                em.getIv().invokeFunction("monsterKilled", this, chr, mob.getStats().getCP() > 0 ? mob.getStats().getCP() : mob.getStats().getPoint());
            }
        } catch (RejectedExecutionException ex) {
        } catch (ScriptException | NoSuchMethodException ex) {
            System.err.println("Event name" + (em == null ? "null" : em.getName()) + ", Instance name : " + name + ", method Name : monsterValue:\n" + ex);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + (em == null ? "null" : em.getName()) + ", Instance name : " + name + ", method Name : monsterValue:\n" + ex);
        }
    }

    public void monsterDamaged(final MapleCharacter chr, final MapleMonster mob, final int damage) {
        if (disposed || mob.getId() != 9700037) { //ghost PQ boss only.
            return;
        }
        try {
            em.getIv().invokeFunction("monsterDamaged", this, chr, mob.getId(), damage);
        } catch (RejectedExecutionException ex) {
        } catch (ScriptException ex) {
            System.err.println("Event name" + (em == null ? "null" : em.getName()) + ", Instance name : " + name + ", method Name : monsterValue:\n" + ex);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : restartEventTimer:\n" + ex);
        } catch (NoSuchMethodException ex) {
            System.err.println("Event name" + (em == null ? "null" : em.getName()) + ", Instance name : " + name + ", method Name : monsterValue:\n" + ex);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : restartEventTimer:\n" + ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : restartEventTimer:\n" + ex);
        }
    }

    public int getKillCount(MapleCharacter chr) {
        if (disposed) {
            return 0;
        }
        Integer kc = killCount.get(chr.getId());
        if (kc == null) {
            return 0;
        } else {
            return kc;
        }
    }

    public void dispose_NoLock() {
        if (disposed || em == null) {
            return;
        }
        final String emN = em.getName();
        try {

            disposed = true;
            for (MapleCharacter chr : chars) {
                chr.setEventInstance(null);
            }
            chars.clear();
            chars = null;
            for (MapleMonster mob : mobs) {
                mob.setEventInstance(null);
            }
            mobs.clear();
            mobs = null;
            killCount.clear();
            killCount = null;
            dced.clear();
            dced = null;
            timeStarted = 0;
            eventTime = 0;
            props.clear();
            props = null;
            for (int i = 0; i < mapIds.size(); i++) {
                if (isInstanced.get(i)) {
                    this.getMapFactory().removeInstanceMap(mapIds.get(i));
                }
            }
            mapIds.clear();
            mapIds = null;
            isInstanced.clear();
            isInstanced = null;
            em.disposeInstance(name);
        } catch (Exception e) {
            System.err.println("Caused by : " + emN + " instance name: " + name + " method: dispose: " + e);
            FilePrinter.printError("EventInstanceManager.txt", "Caused by : " + emN + " instance name: " + name + " method: dispose: " + e);
        }
    }

    public void dispose() {
        wL.lock();
        try {
            dispose_NoLock();
        } finally {
            wL.unlock();
        }

    }

    public ChannelServer getChannelServer() {
        return ChannelServer.getInstance(channel);
    }

    public List<MapleMonster> getMobs() {
        return mobs;
    }

    /*public final void giveAchievement(final int type) {
     if (disposed) {
     return;
     }
     for (MapleCharacter chr : getPlayers()) {
     chr.finishAchievement(type);
     }
     }*/
    public final void broadcastPlayerMsg(final int type, final String msg) {
        if (disposed) {
            return;
        }
        for (MapleCharacter chr : getPlayers()) {
            chr.getClient().sendPacket(MaplePacketCreator.serverNotice(type, msg));
        }
    }

    public final MapleMap createInstanceMap(final int mapid) {
        if (disposed) {
            return null;
        }
        final int assignedid = getChannelServer().getEventSM().getNewInstanceMapId();
        mapIds.add(assignedid);
        isInstanced.add(true);
        return this.getMapFactory().CreateInstanceMap(mapid, true, true, true, assignedid);
    }

    public final MapleMap createInstanceMapS(final int mapid) {
        if (disposed) {
            return null;
        }
        final int assignedid = getChannelServer().getEventSM().getNewInstanceMapId();
        mapIds.add(assignedid);
        isInstanced.add(true);
        return this.getMapFactory().CreateInstanceMap(mapid, false, false, false, assignedid);
    }

    public final MapleMap setInstanceMap(final int mapid) { //gets instance map from the channelserv
        if (disposed) {
            return this.getMapFactory().getMap(mapid);
        }
        mapIds.add(mapid);
        isInstanced.add(false);
        return this.getMapFactory().getMap(mapid);
    }

    public final MapleMapFactory getMapFactory() {
        return getChannelServer().getMapFactory();
    }

    public final MapleMap getMapInstance(int args) {
        if (disposed) {
            return null;
        }
        try {
            boolean instanced = false;
            int trueMapID;
            if (args >= mapIds.size()) {
                //assume real map
                trueMapID = args;
            } else {
                trueMapID = mapIds.get(args);
                instanced = isInstanced.get(args);
            }
            MapleMap map;
            if (!instanced) {
                map = this.getMapFactory().getMap(trueMapID);
                if (map == null) {
                    return null;
                }
                // in case reactors need shuffling and we are actually loading the map
                if (map.getCharactersSize() == 0) {
                    if (em.getProperty("shuffleReactors") != null && em.getProperty("shuffleReactors").equals("true")) {
                        map.shuffleReactors();
                    }
                }
            } else {
                map = this.getMapFactory().getInstanceMap(trueMapID);
                if (map == null) {
                    return null;
                }
                // in case reactors need shuffling and we are actually loading the map
                if (map.getCharactersSize() == 0) {
                    if (em.getProperty("shuffleReactors") != null && em.getProperty("shuffleReactors").equals("true")) {
                        map.shuffleReactors();
                    }
                }
            }
            return map;
        } catch (NullPointerException ex) {
            FilePrinter.printError("EventInstanceManager.txt", ex);
            return null;
        }
    }

    public final void schedule(final String methodName, final long delay) {
        //if (ChannelServer.getInstance(1).isShutdown()) {
        //    System.err.println("schedule - 关闭伺服器中無法調用");
        //    return;
        //}
        if (disposed) {
            return;
        }
        EventTimer.getInstance().schedule(new Runnable() {
            @Override
            public void run() {
                if (disposed || EventInstanceManager.this == null || em == null) {
                    return;
                }
                try {
                    em.getIv().invokeFunction(methodName, EventInstanceManager.this);
                } catch (NullPointerException npe) {
                } catch (RejectedExecutionException ex) {
                } catch (ScriptException | NoSuchMethodException ex) {
                    System.err.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : " + methodName + ":\n" + ex);
                    FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : " + methodName + ":\n" + ex);
                }
            }
        }, delay);
    }

    public final String getName() {
        return name;
    }

    public final void setProperty(final String key, final String value) {
        if (disposed) {
            return;
        }
        props.setProperty(key, value);
    }

    public final Object setProperty(final String key, final String value, final boolean prev) {
        if (disposed) {
            return null;
        }
        return props.setProperty(key, value);
    }

    public final String getProperty(final String key) {
        if (disposed) {
            return "";
        }
        return props.getProperty(key);
    }

    public final Properties getProperties() {
        return props;
    }

    public final void leftParty(final MapleCharacter chr) {
        if (disposed) {
            return;
        }
        try {
            em.getIv().invokeFunction("leftParty", this, chr);
        } catch (Exception ex) {
            System.err.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : leftParty:\n" + ex);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : leftParty:\n" + ex);
        }
    }

    public final void disbandParty() {
        if (disposed) {
            return;
        }
        try {
            em.getIv().invokeFunction("disbandParty", this);
        } catch (Exception ex) {
            System.out.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : disbandParty:\n" + ex);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : disbandParty:\n" + ex);
        }
    }

    //Separate function to warp players to a "finish" map, if applicable
    public final void finishPQ() {
        if (disposed) {
            return;
        }
        try {
            em.getIv().invokeFunction("clearPQ", this);
        } catch (RejectedExecutionException ex) {
        } catch (ScriptException | NoSuchMethodException ex) {
            System.err.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : clearPQ:\n" + ex);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : clearPQ:\n" + ex);
        }
    }

    public final void removePlayer(final MapleCharacter chr) {
        if (disposed) {
            return;
        }
        try {
            em.getIv().invokeFunction("playerExit", this, chr);
        } catch (RejectedExecutionException ex) {
        } catch (ScriptException | NoSuchMethodException ex) {
            System.err.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : playerExit:\n" + ex);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : playerExit:\n" + ex);
        }
    }

    public final void registerCarnivalParty(final MapleCharacter leader, final MapleMap map, final byte team) {
        if (disposed) {
            return;
        }
        leader.clearCarnivalRequests();
        List<MapleCharacter> characters = new LinkedList<>();
        final MapleParty party = leader.getParty();

        if (party == null) {
            return;
        }

        for (MaplePartyCharacter pc : party.getMembers()) {
            final MapleCharacter c = map.getCharacterById(pc.getId());
            if (c != null) {
                characters.add(c);
                registerPlayer(c);
                c.resetCP();
            }
        }

        final MapleCarnivalParty carnivalParty = new MapleCarnivalParty(leader, characters, team);
        for (MapleCharacter chr : characters) {
            chr.setCarnivalParty(carnivalParty);
        }

        try {
            em.getIv().invokeFunction("registerCarnivalParty", this, carnivalParty);
        } catch (RejectedExecutionException ex) {
        } catch (ScriptException ex) {
            System.err.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : registerCarnivalParty:\n" + ex);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : registerCarnivalParty:\n" + ex);
        } catch (NoSuchMethodException ex) {
            //ignore
        }
    }

    public void onMapLoad(final MapleCharacter chr) {
        if (disposed) {
            return;
        }
        try {
            em.getIv().invokeFunction("onMapLoad", this, chr);
        } catch (RejectedExecutionException ex) {
        } catch (ScriptException ex) {
            System.err.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : onMapLoad:\n" + ex);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : onMapLoad:\n" + ex);
        } catch (NoSuchMethodException ex) {
            // Ignore, we don't want to update this for all events.
        }
    }

    public boolean isLeader(final MapleCharacter chr) {
        return (chr != null && chr.getParty() != null && chr.getParty().getLeader().getId() == chr.getId());
    }

    public void registerSquad(MapleSquad squad, MapleMap map, int questID) {
        if (disposed) {
            return;
        }
        final int mapid = map.getId();

        for (String chr : squad.getMembers()) {
            MapleCharacter player = squad.getChar(chr);
            if (player != null && player.getMapId() == mapid) {
                if (questID > 0) {
                    player.getQuestNAdd(MapleQuest.getInstance(questID)).setCustomData(String.valueOf(System.currentTimeMillis()));
                }
                registerPlayer(player);
                /*                if (player.getParty() != null) {
                 PartySearch ps = World.Party.getSearch(player.getParty());
                 if (ps != null) {
                 World.Party.removeSearch(ps, "The Party Listing has been removed because the Party Quest has started.");
                 }
                 }*/
            }
        }
        squad.setStatus((byte) 2);
        squad.getBeginMap().broadcastMessage(MaplePacketCreator.stopClock());
    }

    public boolean isDisconnected(final MapleCharacter chr) {
        if (disposed) {
            return false;
        }
        return (dced.contains(chr.getId()));
    }

    public void removeDisconnected(final int id) {
        if (disposed) {
            return;
        }
        dced.remove(id);
    }

    public EventManager getEventManager() {
        return em;
    }

    public void applyBuff(final MapleCharacter chr, final int id) {
        MapleItemInformationProvider.getInstance().getItemEffect(id).applyTo(chr);
        chr.getClient().sendPacket(UIPacket.getStatusMsg(id));
    }
    
    public String getServerName() {
        //return MapleParty.开服名字;//var MC = cm.etServerName();
        return ServerConfig.SERVERNAME;
    }
    
    public int 获取当前星期() {

        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

    }
    
    public final MapleClient getClient() {
        return c;
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
    
    public MapleItemInformationProvider getItemInfo() {
        return MapleItemInformationProvider.getInstance();
    }
    
     public void setPQLog(String log) {
        getPlayers().parallelStream().forEach(p -> p.setPQLog(log));
    }
     
     public void setEventCount(String log) {
        getPlayers().parallelStream().forEach(p -> p.setEventCount(log));
    }
    
}
