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
package server.life;

import constants.GameConstants;
import client.MapleCharacter;
import client.MapleDisease;
import client.status.MonsterStatus;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import server.maps.MapleMist;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.EnumMap;
import tools.FileoutputUtil;
import tools.MaplePacketCreator;

public class MobSkill {

    /**
     *
     * @param skillId, skillLevel 技能ID 技能等級
     * @param mpCon 可用的MP
     * @param spawnEffect 召喚效果
     * @param hp HP
     * @param duration, cooltime 長短 冷卻时间
     * @param prop 支撐
     * @param limit 限制
     * @param toSummon 召喚
     * @param x y 座標
     * @param lt rb 座標
     *
     */
    private final int skillId, skillLevel;
    private int mpCon, spawnEffect, hp, x, y;
    private long duration, cooltime;
    private float prop;
//    private short effect_delay;
    private short limit;
    private List<Integer> toSummon = new ArrayList<>();
    private Point lt, rb;
    private boolean summonOnce;

    //怪物技能
    public MobSkill(int skillId, int level) {
        this.skillId = skillId;
        this.skillLevel = level;
    }

    //設置可用的MP
    public void setMpCon(int mpCon) {
        this.mpCon = mpCon;
    }

    //增加召喚
    public void addSummons(List<Integer> toSummon) {
        this.toSummon = toSummon;
    }

    /*   public void setEffectDelay(short effect_delay) {
     this.effect_delay = effect_delay;
     }*/
    //設置召喚效果
    public void setSpawnEffect(int spawnEffect) {
        this.spawnEffect = spawnEffect;
    }

    //設置HP
    public void setHp(int hp) {
        this.hp = hp;
    }

    //設置X軸
    public void setX(int x) {
        this.x = x;
    }

    //設置Y軸
    public void setY(int y) {
        this.y = y;
    }

    //設置長短
    public void setDuration(long duration) {
        this.duration = duration;
    }

    //設置冷卻时间
    public void setCoolTime(long cooltime) {
        this.cooltime = cooltime;
    }

    //設置支撐
    public void setProp(float prop) {
        this.prop = prop;
    }

    //設置座標
    public void setLtRb(Point lt, Point rb) {
        this.lt = lt;
        this.rb = rb;
    }

    //設置限制
    public void setLimit(short limit) {
        this.limit = limit;
    }

    //確定當前BUFF
    public boolean checkCurrentBuff(MapleCharacter player, MapleMonster monster) {
        boolean stop = false;
        switch (skillId) {
            case 100:
            case 110:
            case 150:
                stop = monster.isBuffed(MonsterStatus.WEAPON_ATTACK_UP);
                break;
            case 101:
            case 111:
            case 151:
                stop = monster.isBuffed(MonsterStatus.WEAPON_DEFENSE_UP);
                break;
            case 102:
            case 112:
            case 152:
                stop = monster.isBuffed(MonsterStatus.MAGIC_ATTACK_UP);
                break;
            case 103:
            case 113:
            case 153:
                stop = monster.isBuffed(MonsterStatus.MAGIC_DEFENSE_UP);
                break;
            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
                stop = monster.isBuffed(MonsterStatus.DAMAGE_IMMUNITY) || monster.isBuffed(MonsterStatus.MAGIC_IMMUNITY) || monster.isBuffed(MonsterStatus.WEAPON_IMMUNITY);
                break;
            case 200:
                stop = player.getMap().getNumMonsters() >= limit;
                break;
        }
        return stop;
    }

    //套用效果
    public void applyEffect(MapleCharacter player, MapleMonster monster, boolean skill) {
        MapleDisease disease = null;
        Map<MonsterStatus, Integer> stats = new EnumMap<>(MonsterStatus.class);
        List<Integer> reflection = new LinkedList<>();

         if (gui.Qhms.ConfigValuesMap.get("怪物状态开关") <= 0) {
                   switch (skillId) {
                case 102:
                case 112:
                case 152:
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [防御增幅]"));
                    break;
                case 131:
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [召唤毒雾]"));
                    break;
                case 128:
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [诱导]"));
                    break;
                case 103:
                case 113:
                case 153:
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [魔法防御增幅]"));
                    break;
                case 110:
                case 150:
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [物理攻击增幅]"));
                    break;
                case 101:
                case 111:
                case 151:
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [魔法攻击增幅]"));
                    break;
                case 120:
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat(""  + monster.stats.getName() + " 使用技能 [封印]"));
                    break;
                case 121:
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [黑暗]"));
                    break;
                case 122:
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [弱化]"));
                    break;
                case 124:
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [诅咒]"));
                    break;
                case 114:
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [治愈]"));
                    break;
                case 140:
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [物理无效]"));
                    break;
                case 141:
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [魔法无效]"));
                    break;
                case 200:
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 [召唤小弟]"));
                    break;
                default:
                    monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("" + monster.stats.getName() + " 使用技能 " + skillId));
                    break;
            }
        }
        
        switch (skillId) {
            case 100:
            case 110:
            case 150:
                stats.put(MonsterStatus.WEAPON_ATTACK_UP, x);
                break;
            case 101:
            case 111:
            case 151:
                stats.put(MonsterStatus.MAGIC_ATTACK_UP, x);
                break;
            case 102:
            case 112:
            case 152:
                stats.put(MonsterStatus.WEAPON_DEFENSE_UP, x);
                break;
            case 103:
            case 113:
            case 153:
                stats.put(MonsterStatus.MAGIC_DEFENSE_UP, x);
                break;
            case 154:
                stats.put(MonsterStatus.ACC, x);
                break;
            case 155:
                stats.put(MonsterStatus.AVOID, x);
                break;
            case 156:
                stats.put(MonsterStatus.SPEED, x);
                break;
            case 157:
                stats.put(MonsterStatus.SEAL, x); //o.o
                break;
            case 114:
                if (lt != null && rb != null && skill && monster != null) {
                    List<MapleMapObject> objects = getObjectsInRange(monster, MapleMapObjectType.MONSTER);
                    final int healHP = this.getHP();
                    for (MapleMapObject mons : objects) {
                        ((MapleMonster) mons).heal(healHP, 0, true);
                    }
                } else if (monster != null) {
                    monster.heal(this.getHP(), 0, true);
                }
                break;
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126: // Slow
            case 128: // Seduce
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
                disease = MapleDisease.getByMobSkill(skillId);
                break;
            case 127:
                if (lt != null && rb != null && skill && monster != null && player != null) {
                    for (MapleCharacter character : getPlayersInRange(monster, player)) {
                        character.dispel();
                    }
                } else if (player != null) {
                    player.dispel();
                }
                break;

            case 129: // Banish
                if (monster != null) {
                    if (monster.getEventInstance() != null && monster.getEventInstance().getName().contains("BossQuest")) {
                        break;
                    }
                    final BanishInfo info = monster.getStats().getBanishInfo();
                    if (info != null) {
                        if (lt != null && rb != null && skill && player != null) {
                            for (MapleCharacter chr : getPlayersInRange(monster, player)) {
                                chr.changeMapBanish(info.getMap(), info.getPortal(), info.getMsg());
                            }
                        } else if (player != null) {
                            player.changeMapBanish(info.getMap(), info.getPortal(), info.getMsg());
                        }
                    }
                }
                break;
            case 131: // Mist
                if (monster != null) {
                    monster.getMap().spawnMist(new MapleMist(calculateBoundingBox(monster.getPosition(), true), monster, this), x * 10, false);
                }
                break;
            case 140:
                stats.put(MonsterStatus.WEAPON_IMMUNITY, x);
                break;
            case 141:
                stats.put(MonsterStatus.MAGIC_IMMUNITY, x);
                break;
            case 142: // Weapon / Magic Immunity
                stats.put(MonsterStatus.DAMAGE_IMMUNITY, x);
                break;
            case 143: // Weapon Reflect
                stats.put(MonsterStatus.WEAPON_DAMAGE_REFLECT, x);
                stats.put(MonsterStatus.WEAPON_IMMUNITY, x);
                reflection.add(x);
                if (monster == null) {
                    break;
                }
                monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("[系統提示] 注意 " + monster.getStats().getName() + " 开启了物理反射状态。"));
                break;
            case 144: // Magic Reflect
                stats.put(MonsterStatus.MAGIC_DAMAGE_REFLECT, x);
                stats.put(MonsterStatus.MAGIC_IMMUNITY, x);
                reflection.add(x);
                if (monster == null) {
                    break;
                }
                monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("[系統提示] 注意 " + monster.getStats().getName() + " 开启了魔法反射状态。"));
                break;
            case 145: // Weapon / Magic reflect
                stats.put(MonsterStatus.WEAPON_DAMAGE_REFLECT, x);
                stats.put(MonsterStatus.WEAPON_IMMUNITY, x);
                stats.put(MonsterStatus.MAGIC_DAMAGE_REFLECT, x);
                stats.put(MonsterStatus.MAGIC_IMMUNITY, x);
                reflection.add(x);
                if (monster == null) {
                    break;
                }
                monster.getMap().broadcastMessage(MaplePacketCreator.yellowChat("[系統提示] 注意 " + monster.getStats().getName() + " 开启了物理和魔法反射状态。"));
                break;
            case 200:
                if (monster == null) {
                    return;
                }
                for (Integer mobId : getSummons()) {
                    MapleMonster toSpawn;
                    try {
                        toSpawn = MapleLifeFactory.getMonster(GameConstants.getCustomSpawnID(monster.getId(), mobId));
                    } catch (RuntimeException e) { //monster doesn't exist
                        continue;
                    }
                    if (toSpawn == null) {
                        continue;
                    }
                    toSpawn.setPosition(monster.getPosition());
                    int ypos = (int) monster.getPosition().getY(), xpos = (int) monster.getPosition().getX();

                    switch (mobId) {
                        case 8500003: // Pap bomb high
                            toSpawn.setFh((int) Math.ceil(Math.random() * 19.0));
                            ypos = -590;
                            break;
                        case 8500004: // Pap bomb
                            //Spawn between -500 and 500 from the monsters X position
                            xpos = (int) (monster.getPosition().getX() + Math.ceil(Math.random() * 1000.0) - 500);
                            ypos = (int) monster.getPosition().getY();
                            break;
                        case 8510100: //Pianus bomb
                            if (Math.ceil(Math.random() * 5) == 1) {
                                ypos = 78;
                                xpos = (int) (0 + Math.ceil(Math.random() * 5)) + ((Math.ceil(Math.random() * 2) == 1) ? 180 : 0);
                            } else {
                                xpos = (int) (monster.getPosition().getX() + Math.ceil(Math.random() * 1000.0) - 500);
                            }
                            break;
                        case 8820007: //mini bean
                            continue;
                    }
                    // Get spawn coordinates (This fixes monster lock)
                    // TODO get map left and right wall.
                    switch (monster.getMap().getId()) {
                        case 220080001: //Pap map
                            if (xpos < -890) {
                                xpos = (int) (-890 + Math.ceil(Math.random() * 150));
                            } else if (xpos > 230) {
                                xpos = (int) (230 - Math.ceil(Math.random() * 150));
                            }
                            break;
                        case 230040420: // Pianus map
                            if (xpos < -239) {
                                xpos = (int) (-239 + Math.ceil(Math.random() * 150));
                            } else if (xpos > 371) {
                                xpos = (int) (371 - Math.ceil(Math.random() * 150));
                            }
                            break;
                    }
                    monster.getMap().spawnMonsterWithEffect(toSpawn, getSpawnEffect(), monster.getMap().calcPointBelow(new Point(xpos, ypos - 1)));
                }
                break;
            default:
                if (disease != null) {
                    break;
                }
                //FileoutputUtil.logToFile("logs/怪物技能錯誤/怪物技能錯誤.txt", "\r\n " + monster.getStats().getName() + "未處理的怪物技能:skillid : " + this.skillId);
                //System.out.println("未處理的怪物技能:skillid : " + this.skillId);
        }

        if (stats.size() > 0 && monster != null) {
            if (lt != null && rb != null && skill) {
                for (MapleMapObject mons : getObjectsInRange(monster, MapleMapObjectType.MONSTER)) {
                    ((MapleMonster) mons).applyMonsterBuff(stats, getX(), getSkillId(), getDuration(), this, reflection);
                }
            } else {
                monster.applyMonsterBuff(stats, getX(), getSkillId(), getDuration(), this, reflection);
            }

        }
        if (disease != null && player != null) {
            if (lt != null && rb != null && skill && monster != null) {
                for (MapleCharacter chr : getPlayersInRange(monster, player)) {
                    chr.getDiseaseBuff(disease, this);
                }
            } else {
                player.getDiseaseBuff(disease, this);
            }
        }
        if (monster != null) {
            monster.setMp(monster.getMp() - getMpCon());
        }
    }

    //得到技能ID 傳回當前技能ID
    public int getSkillId() {
        return skillId;
    }

    //得到技能等級 傳回當前技能等級
    public int getSkillLevel() {
        return skillLevel;
    }

    //得到可用MP 傳回當前可用MP
    public int getMpCon() {
        return mpCon;
    }

    //得到限制 傳回集合名單限制
    public List<Integer> getSummons() {
        return Collections.unmodifiableList(toSummon);
    }

    /*    public short getEffectDelay() {
     return effect_delay;
     }*/
    //得到召喚效果 傳回當前召喚效果
    public int getSpawnEffect() {
        return spawnEffect;
    }

    //得到HP 傳回當前HP
    public int getHP() {
        return hp;
    }

    //得到X軸 傳回當前X軸
    public int getX() {
        return x;
    }

    //得到Y軸 傳回當前Y軸
    public int getY() {
        return y;
    }

    //得到長短 傳回當前長短
    public long getDuration() {
        return duration;
    }

    //得到冷卻时间 傳回當前冷卻时间
    public long getCoolTime() {
        return cooltime;
    }

    //得到座標lt 傳回當前座標lt
    public Point getLt() {
        return lt;
    }

    //得到座標Rb 傳回當前座標Rb
    public Point getRb() {
        return rb;
    }

    //得到限制 傳回當前限制
    public int getLimit() {
        return limit;
    }

    //製造選擇結果 傳回隨機結果
    public boolean makeChanceResult() {
        return prop >= 1.0 || Math.random() < prop;
    }

    //計算限制盒子的邊界
    private Rectangle calculateBoundingBox(Point posFrom, boolean facingLeft) {
        Point mylt, myrb;
        if (facingLeft) {
            mylt = new Point(lt.x + posFrom.x, lt.y + posFrom.y);
            myrb = new Point(rb.x + posFrom.x, rb.y + posFrom.y);
        } else {
            myrb = new Point(lt.x * -1 + posFrom.x, rb.y + posFrom.y);
            mylt = new Point(rb.x * -1 + posFrom.x, lt.y + posFrom.y);
        }
        final Rectangle bounds = new Rectangle(mylt.x, mylt.y, myrb.x - mylt.x, myrb.y - mylt.y);
        return bounds;
    }

    //得到玩家在的範圍
    private List<MapleCharacter> getPlayersInRange(MapleMonster monster, MapleCharacter player) {
        final Rectangle bounds = calculateBoundingBox(monster.getPosition(), monster.isFacingLeft());
        List<MapleCharacter> players = new ArrayList<>();
        players.add(player);
        return monster.getMap().getPlayersInRectThreadsafe(bounds, players);
    }

    //得到物件在地範圍
    private List<MapleMapObject> getObjectsInRange(MapleMonster monster, MapleMapObjectType objectType) {
        final Rectangle bounds = calculateBoundingBox(monster.getPosition(), monster.isFacingLeft());
        List<MapleMapObjectType> objectTypes = new ArrayList<>();
        objectTypes.add(objectType);
        return monster.getMap().getMapObjectsInRect(bounds, objectTypes);
    }

    public void setOnce(boolean o) {
        this.summonOnce = o;
    }

    public boolean onlyOnce() {
        return summonOnce;
    }

}
