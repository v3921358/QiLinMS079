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
 but WITHOUT ANY WARRANTY; w"ithout even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package scripting;

import java.util.Collection;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.Properties;
import java.util.concurrent.ScheduledFuture;
import javax.script.Invocable;
import javax.script.ScriptException;

import client.MapleCharacter;
import client.MapleClient;
import constants.ServerConfig;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import handling.world.World;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import server.MapleItemInformationProvider;
import server.MapleSquad;
import server.Randomizer;
import server.Timer.EventTimer;
import server.events.MapleEvent;
import server.events.MapleEventType;
import server.life.MapleMonster;
import server.life.MapleLifeFactory;
import server.life.OverrideMonsterStats;
import server.maps.MapleMap;
import server.maps.MapleMapObject;
import server.maps.MapleMapFactory;
import server.maps.MapleReactor;
import server.maps.MapleReactorFactory;
import tools.MaplePacketCreator;
import tools.FilePrinter;
import tools.FileoutputUtil;

public class EventManager {

    private static int[] eventChannel = new int[4];
    private Invocable iv;
    private int channel;
    private Map<String, EventInstanceManager> instances = new WeakHashMap<>();
    private Properties props = new Properties();
    private String name;
    private MapleClient c;

    public EventManager(ChannelServer cserv, Invocable iv, String name) {
        this.iv = iv;
        this.channel = cserv.getChannel();
        this.name = name;
    }

    public void cancel() {
        try {
            iv.invokeFunction("cancelSchedule", (Object) null);
        } catch (ScriptException | NoSuchMethodException ex) {
            System.err.println("Event name : " + name + ", method Name : cancelSchedule:\n" + ex);
            FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : cancelSchedule:\n" + ex);
        }
    }

    public ScheduledFuture<?> schedule(final String methodName, long delay) {
        return EventTimer.getInstance().schedule(new Runnable() {

            @Override
            public void run() {
                try {
                    iv.invokeFunction(methodName, (Object) null);
                } catch (ScriptException | NoSuchMethodException ex) {
                    System.err.println("Event name : " + name + ", method Name : " + methodName + ":\n" + ex);
                    FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : " + methodName + ":\n" + ex);

                }
            }
        }, delay);
    }

    public ScheduledFuture<?> schedule(final String methodName, long delay, final EventInstanceManager eim) {
        return EventTimer.getInstance().schedule(new Runnable() {

            @Override
            public void run() {
                try {
                    iv.invokeFunction(methodName, eim);
                } catch (ScriptException | NoSuchMethodException ex) {
                    System.err.println("Event name : " + name + ", method Name : " + methodName + ":\n" + ex);
                    FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : " + methodName + ":\n" + ex);
                    FileoutputUtil.log(FileoutputUtil.ScriptEx_Log, "Event name : " + name + ", method Name : " + methodName + ":\n" + ex);

                }
            }
        }, delay);
    }

    public ScheduledFuture<?> scheduleAtTimestamp(final String methodName, long timestamp) {
        return EventTimer.getInstance().scheduleAtTimestamp(new Runnable() {

            @Override
            public void run() {
                try {
                    iv.invokeFunction(methodName, (Object) null);
                } catch (ScriptException | NoSuchMethodException ex) {
                    System.err.println("Event name : " + name + ", method Name : " + methodName + ":\n" + ex);
                    FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : " + methodName + ":\n" + ex);
                    FileoutputUtil.log(FileoutputUtil.ScriptEx_Log, "Event name : " + name + ", method Name : " + methodName + ":\n" + ex);
                }
            }
        }, timestamp);
    }

    public int getChannel() {
        return channel;
    }

    public ChannelServer getChannelServer() {
        return ChannelServer.getInstance(channel);
    }

    public EventInstanceManager getInstance(String name) {
        return instances.get(name);
    }

    public Collection<EventInstanceManager> getInstances() {
        return Collections.unmodifiableCollection(instances.values());
    }

    public EventInstanceManager newInstance(String name) {
        EventInstanceManager ret = new EventInstanceManager(this, name, channel);
        instances.put(name, ret);
        return ret;
    }

    public void disposeInstance(String name) {
        instances.remove(name);
        if (getProperty("state") != null && instances.isEmpty()) {
            setProperty("state", "0");
        }
        if (getProperty("leader") != null && instances.isEmpty() && getProperty("leader").equals("false")) {
            setProperty("leader", "true");
        }
        if (this.name.equals("CWKPQ")) { //hard code it because i said so
            final MapleSquad squad = ChannelServer.getInstance(channel).getMapleSquad("CWKPQ");//so fkin hacky
            if (squad != null) {
                squad.clear();
            }
        }
    }

    public Invocable getIv() {
        return iv;
    }

    public void setProperty(String key, String value) {
        props.setProperty(key, value);
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public final Properties getProperties() {
        return props;
    }

    public String getName() {
        return name;
    }

    public void startInstance() {
        try {
            iv.invokeFunction("setup", (Object) null);
        } catch (ScriptException | NoSuchMethodException ex) {
            System.err.println("Event name : " + name + ", method Name : setup:\n" + ex);
            FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : setup:\n" + ex);
        }
    }

    public void startInstance(String mapid, MapleCharacter chr) {
        try {
            EventInstanceManager eim = (EventInstanceManager) iv.invokeFunction("setup", (Object) mapid);
            eim.registerCarnivalParty(chr, chr.getMap(), (byte) 0);
        } catch (ScriptException | NoSuchMethodException ex) {
            System.err.println("Event name : " + name + ", method Name : setup:\n" + ex);
            FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : setup:\n" + ex);
        }
    }

    public void startInstance_Party(String mapid, MapleCharacter chr) {
        try {
            EventInstanceManager eim = (EventInstanceManager) iv.invokeFunction("setup", (Object) mapid);
            eim.registerParty(chr.getParty(), chr.getMap());
        } catch (ScriptException | NoSuchMethodException ex) {
            System.err.println("Event name : " + name + ", method Name : setup:\n" + ex);
            FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : setup:\n" + ex);
        }
    }

    //GPQ
    public void startInstance(MapleCharacter character, String leader) {
        try {
            EventInstanceManager eim = (EventInstanceManager) (iv.invokeFunction("setup", (Object) null));
            eim.registerPlayer(character);
            eim.setProperty("leader", leader);
            eim.setProperty("guildid", String.valueOf(character.getGuildId()));
            setProperty("guildid", String.valueOf(character.getGuildId()));
        } catch (ScriptException | NoSuchMethodException ex) {
            System.err.println("Event name : " + name + ", method Name : setup-Guild:\n" + ex);
            FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : setup-Guild:\n" + ex);
        }
    }

    public void startInstance_CharID(MapleCharacter character) {
        try {
            EventInstanceManager eim = (EventInstanceManager) (iv.invokeFunction("setup", character.getId()));
            eim.registerPlayer(character);
        } catch (ScriptException | NoSuchMethodException ex) {
            System.err.println("Event name : " + name + ", method Name : setup-CharID:\n" + ex);
            FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : setup-CharID:\n" + ex);
        }
    }

    public void startInstance(MapleCharacter character) {
        try {
            EventInstanceManager eim = (EventInstanceManager) (iv.invokeFunction("setup", (Object) null));
            eim.registerPlayer(character);
        } catch (ScriptException | NoSuchMethodException ex) {
            System.err.println("Event name : " + name + ", method Name : setup-character:\n" + ex);
            FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : setup-character:\n" + ex);
        }
    }

    //PQ method: starts a PQ
    public void startInstance(MapleParty party, MapleMap map) {
        try {
            EventInstanceManager eim = (EventInstanceManager) (iv.invokeFunction("setup", party.getId()));
            eim.registerParty(party, map);
        } catch (ScriptException ex) {
            System.err.println("Event name : " + name + ", method Name : setup-partyid:\n" + ex);
            FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : setup-partyid:\n" + ex);
        } catch (NoSuchMethodException ex) {
            //ignore
            startInstance_NoID(party, map, ex);
        }
    }

    public void startInstance_NoID(MapleParty party, MapleMap map) {
        startInstance_NoID(party, map, null);
    }

    public void startInstance_NoID(MapleParty party, MapleMap map, final Exception old) {
        try {
            EventInstanceManager eim = (EventInstanceManager) (iv.invokeFunction("setup", (Object) null));
            eim.registerParty(party, map);
        } catch (ScriptException | NoSuchMethodException ex) {
            System.err.println("Event name : " + name + ", method Name : setup-party:\n" + ex);
            FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : setup-party:\n" + ex + "\n" + (old == null ? "no old exception" : old));
        }
    }

    //non-PQ method for starting instance
    public void startInstance(EventInstanceManager eim, String leader) {
        try {
            iv.invokeFunction("setup", eim);
            eim.setProperty("leader", leader);
        } catch (ScriptException | NoSuchMethodException ex) {
            System.err.println("Event name : " + name + ", method Name : setup-leader:\n" + ex);
            FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : setup-leader:\n" + ex);
        }
    }

    public void startInstance(MapleSquad squad, MapleMap map) {
        startInstance(squad, map, -1);
    }

    public void startInstance(MapleSquad squad, MapleMap map, int questID) {
        if (squad.getStatus() == 0) {
            return; //we dont like cleared squads
        }
        if (!squad.getLeader().isGM()) {
            if (squad.getMembers().size() < squad.getType().i) { //less than 3
                squad.getLeader().dropMessage(5, "這個遠征队至少要有 " + squad.getType().i + " 人以上才可以開戰.");
                return;
            }
            if (name.equals("CWKPQ") && squad.getJobs().size() < 5) {
                squad.getLeader().dropMessage(5, "The squad requires members from every type of job.");
                return;
            }
        }
        try {
            EventInstanceManager eim = (EventInstanceManager) (iv.invokeFunction("setup", squad.getLeaderName()));
            eim.registerSquad(squad, map, questID);
        } catch (ScriptException | NoSuchMethodException ex) {
            System.err.println("Event name : " + name + ", method Name : setup-squad:\n" + ex);
            FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : setup-squad:\n" + ex);
        }
    }

    public void warpAllPlayer(int from, int to) {
        //if (ChannelServer.getInstance(1).isShutdown()) {
        //    System.out.println("warpAllPlayer - 关闭伺服器中無法調用");
        //     return;
        //}
        final MapleMap tomap = getMapFactory().getMap(to);
        final MapleMap frommap = getMapFactory().getMap(from);
        List<MapleCharacter> list = frommap.getCharactersThreadsafe();
        if (tomap != null && list != null && frommap.getCharactersSize() > 0) {
            for (MapleMapObject mmo : list) {
                ((MapleCharacter) mmo).changeMap(tomap, tomap.getPortal(0));
            }
        }
    }

    public MapleMapFactory getMapFactory() {
        return getChannelServer().getMapFactory();
    }

    public OverrideMonsterStats newMonsterStats() {
        return new OverrideMonsterStats();
    }

    public List<MapleCharacter> newCharList() {
        return new ArrayList<>();
    }

    public MapleMonster getMonster(final int id) {
        return MapleLifeFactory.getMonster(id);
    }

    public void broadcastYellowMsg(final String msg) {
        getChannelServer().broadcastPacket(MaplePacketCreator.yellowChat(msg));
    }
    
    public void broadcastServerMsg(String msg) {
        getChannelServer().broadcastPacket(MaplePacketCreator.serverNotice(6, msg));
    }

    public void broadcastServerMsg(final int type, final String msg, final boolean weather) {
        if (!weather) {
            getChannelServer().broadcastPacket(MaplePacketCreator.serverNotice(type, msg));
        } else {
            for (MapleMap load : getMapFactory().getAllMaps()) {
                if (load.getCharactersSize() > 0) {
                    load.startMapEffect(msg, type);
                }
            }
        }
    }

    public boolean scheduleRandomEvent() {
        boolean omg = false;
        for (int i = 0; i < eventChannel.length; i++) {
            omg |= scheduleRandomEventInChannel(eventChannel[i]);
        }
        return omg;
    }

    public boolean scheduleRandomEventInChannel(int chz) {
        final ChannelServer cs = ChannelServer.getInstance(chz);
        if (cs == null || cs.getEvent() > -1) {
            return false;
        }
        MapleEventType t = null;
        while (t == null) {
            for (MapleEventType x : MapleEventType.values()) {
                if (Randomizer.nextInt(MapleEventType.values().length) == 0 && x != MapleEventType.爬繩子) {
                    t = x;
                    break;
                }
            }
        }
        final String msg = MapleEvent.scheduleEvent(t, cs);
        if (msg.length() > 0) {
            broadcastYellowMsg(msg);
            return false;
        }
        EventTimer.getInstance().schedule(new Runnable() {

            @Override
            public void run() {
                if (cs.getEvent() >= 0) {
                    MapleEvent.setEvent(cs, true);
                }
            }
        }, 600000);
        return true;
    }

    /*public boolean scheduleRandomEventInChannel(int chz) {
        final ChannelServer cs = ChannelServer.getInstance(chz);
        if (cs == null || cs.getEvent() > -1) {
            return false;
        }
        MapleEventType t = null;
        while (t == null) {
            for (MapleEventType x : MapleEventType.values()) {
                t = (cs.getChannel() == 2 || cs.getChannel() == 3) ? MapleEventType.終極忍耐 : (cs.getChannel() == 4 || cs.getChannel() == 5) ? MapleEventType.滾雪球 : (cs.getChannel() == 6 || cs.getChannel() == 7) ? MapleEventType.爬繩子 : MapleEventType.尋寶;
                break;
                //}
            }
        }
        final String msg = MapleEvent.scheduleEvent(t, cs);
        if (msg.length() > 0) {
            broadcastYellowMsg(msg);
            return false;
        }
        EventTimer.getInstance().schedule(new Runnable() {

            @Override
            public void run() {
                if (cs.getEvent() >= 0) {
                    MapleEvent.setEvent(cs, true);
                }
            }
        }, 600000);
        return true;
    }*/
    public void setWorldEvent() {
        for (int i = 0; i < eventChannel.length; i++) {
//            eventChannel[i] = 1; //2-8
            eventChannel[i] = Randomizer.nextInt(ChannelServer.getAllInstances().size()) + i; //2-13
            //eventChannel[i] = i + 2;
        }
    }

    public final void invokeFunctionMethod(final String methodName) {
        try {
            this.iv.invokeFunction(methodName, this);
        } catch (ScriptException | NoSuchMethodException ex) {
            System.out.println("Event name" + this.getName() + ", Instance name : " + name + ", method Name : " + methodName + ":\n" + ex);
            FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : setup-squad:\n" + ex);
        }
    }

    public final void worldMessage(final int type, final String message) {
        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(type, message));
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
    
}
