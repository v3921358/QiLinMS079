package server.life;

import constants.GameConstants;
import client.inventory.Equip;
import client.inventory.IItem;
import client.ISkill;
import client.inventory.Item;
import client.MapleDisease;
import client.MapleBuffStat;
import client.MapleCharacter;
import client.inventory.MapleInventoryType;
import client.MapleClient;
import client.SkillFactory;
import client.status.MonsterStatus;
import client.status.MonsterStatusEffect;
import constants.ServerConfig;
import constants.SkillType;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import handling.world.MaplePartyCharacter;
import handling.world.World;
import server.MapleItemInformationProvider;
import server.Randomizer;
import server.Timer.MobTimer;
import server.maps.MapScriptMethods;
import server.maps.MapleMap;
import server.maps.MapleMapObject;
import server.maps.MapleMapObjectType;
import scripting.EventInstanceManager;
import tools.Pair;
import tools.MaplePacketCreator;
import tools.packet.MobPacket;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ScheduledFuture;
import java.util.EnumMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import static scripting.NPCConversationManager.角色ID取名字;
import server.MapleStatEffect;

public class MapleMonster extends AbstractLoadedMapleLife {

    /**
     *
     * @param MapleMonsterStats 怪物状态
     * @param OverrideMonsterStats 改寫怪物能力值
     * @param hp 怪物HP
     * @param MP 怪物MP
     * @param carnivalTeam 怪物擂台東西
     * @param venom_counter 當前毒液
     * @param map 判斷地图
     * @param Sponge 海綿(?
     * @param linkoid = 0, lastNode = -1, lastNodeController = -1,
     * highestDamageChar = 0 怪物關联
     * @param WeakReference 弱化
     * @param fake, dropsDisabled, controllerHasAggro, controllerKnowsAboutAggro
     * 判斷控制
     * @param attackers 判斷攻击者
     * @param eventInstance 事件管理
     * @param usedSkills 使用過的技能
     * @param stolen 怪物只能被動一次(?
     * @param dropItemSchedule 怪物掉落物品
     * @param shouldDropItem 應該掉落該有的物品
     */
    MapleMonsterStats stats;
    private OverrideMonsterStats ostats = null;
    private long hp, nextKill;
    private int mp;
    private byte venom_counter, carnivalTeam;
    private MapleMap map;
    private WeakReference<MapleMonster> sponge = new WeakReference<>(null);
    private int linkoid = 0, lastNode = -1, lastNodeController = -1, highestDamageChar = 0; // Just a reference for monster EXP distribution after dead
    private WeakReference<MapleCharacter> controller = new WeakReference<>(null);
    private boolean fake, dropsDisabled, controllerHasAggro, controllerKnowsAboutAggro;
    private final Collection<AttackerEntry> attackers = new LinkedList<>();
    private EventInstanceManager eventInstance;
    private MonsterListener listener = null;
    private byte[] reflectpack = null, nodepack = null;
    private final EnumMap<MonsterStatus, MonsterStatusEffect> stati = new EnumMap<>(MonsterStatus.class);
    private final LinkedList<MonsterStatusEffect> poisons = new LinkedList<>();
    private final ReentrantReadWriteLock poisonsLock = new ReentrantReadWriteLock();
    private Map<Integer, Long> usedSkills;
    private int stolen = -1; //monster can only be stolen ONCE
    private ScheduledFuture<?> dropItemSchedule;
    private boolean shouldDropItem = false;
    private long lastAbsorbMP;

    //获得怪物ID 怪物能力值
    public MapleMonster(final int id, final MapleMonsterStats stats) {
        super(id);
        initWithStats(stats);
    }

    //获得怪物ID 怪物能力值
    public MapleMonster(final MapleMonster monster) {
        super(monster);
        initWithStats(monster.stats);
    }

    //得到目前怪物該有的状态 傳回給状态
    public final MapleMonsterStats getStats() {
        return stats;
    }

    //初始化怪物能力
    private void initWithStats(final MapleMonsterStats stats) {
        setStance(5);
        this.stats = stats;
        hp = stats.getHp();
        mp = stats.getMp();
        venom_counter = 0;
        carnivalTeam = -1;
        fake = false;
        dropsDisabled = false;

        if (stats.getNoSkills() > 0) {
            usedSkills = new HashMap<>();
        }
    }

    //禁止該怪物掉的物品
    public final void disableDrops() {
        this.dropsDisabled = true;
    }

    //控制怪物禁止掉的物品 傳回禁止該怪物掉的物品
    public final boolean dropsDisabled() {
        return dropsDisabled;
    }

    //設定當前地图
    public final void setMap(final MapleMap map) {
        this.map = map;
        startDropItemSchedule();
    }

    //得到當前地图 傳回地图
    public final MapleMap getMap() {
        return map;
    }

    //設定怪物海綿
    public final void setSponge(final MapleMonster mob) {
        sponge = new WeakReference<>(mob);
    }

    //得到當前怪物海綿 傳回海綿值
    public final MapleMonster getSponge() {
        return sponge.get();
    }

    //設定怪物HP
    public final void setHp(long hp) {
        this.hp = hp;
    }

    //得到怪物當前HP 傳回HP
    public final long getHp() {
        return hp;
    }

    //得到怪物當前最大的HP 傳回目前的HP
    public final long getMobMaxHp() {
        if (ostats != null) {
            return ostats.getHp();
        }
        return stats.getHp();
    }

    //設定怪物MP
    public final void setMp(int mp) {
        if (mp < 0) {
            mp = 0;
        }
        this.mp = mp;
    }

    //得到怪物當前MP 傳回MP
    public final int getMp() {
        return mp;
    }

    //得到怪物當前最大的MP 傳回目前的MP
    public final int getMobMaxMp() {
        if (ostats != null) {
            return ostats.getMp();
        }
        return stats.getMp();
    }

    //得到怪物EXP 傳回EXP
    public final int getMobExp() {
        if (ostats != null) {
            return ostats.getExp();
        }
        return stats.getExp();
    }
    
    public final int getMobLevel() {
        if (ostats != null) {
            return ostats.getlevel();
        }
        return stats.getLevel();
    }

    //設定怪物的能力值
    public final void setOverrideStats(final OverrideMonsterStats ostats) {
        this.ostats = ostats;
        this.hp = ostats.getHp();
        this.mp = ostats.getMp();
    }

    //得到毒液 傳回毒液
    public final byte getVenomMulti() {
        return venom_counter;
    }

    //設定當前毒液
    public final void setVenomMulti(final byte venom_counter) {
        this.venom_counter = venom_counter;
    }

    /**
     * 竊取MP
     *
     * @param amount 竊取數量
     */
    public final void absorbMP(int amount) {
        if (!canAbsorbMP()) {
            return;
        }
        if (this.getMp() >= amount) {
            this.setMp(this.getMp() - amount);
        } else {
            this.setMp(0);
        }
        this.lastAbsorbMP = System.currentTimeMillis();
    }

    public final long getLastAbsorbMP() {
        return this.lastAbsorbMP;
    }

    public final boolean canAbsorbMP() {
        return System.currentTimeMillis() - this.lastAbsorbMP > 10 * 1000;
    }

    //伤害判断
    public final void damage(final MapleCharacter from, final long damage, final boolean updateAttackTime) {
        damage(from, damage, updateAttackTime, 0);
    }

    /**
     * 攻击怪物
     *
     * @param from 攻击怪物角色
     * @param damage 攻击怪物的数字
     * @param updateAttackTime 攻击时间
     * @param lastSkill 最後一次攻击的技能是什麼
     */
    public final void damage(final MapleCharacter from, final long damage, final boolean updateAttackTime, final int lastSkill) {

        /* 檢查參數 */
        if (from == null || damage <= 0 || !isAlive()) {
            return;
        }

        /* 定義攻击者目前是组队還是個人 */
        AttackerEntry attacker = from.getParty() != null
                ? new PartyAttackerEntry(from.getParty().getId(), map.getChannel())
                : new SingleAttackerEntry(from, map.getChannel());

        boolean replaced = false;
        for (final AttackerEntry aentry : attackers) {
            if (aentry.equals(attacker)) {
                attacker = aentry;
                replaced = true;
                break;
            }
        }
        if (!replaced) {
            attackers.add(attacker);
        }

        /* 攻击的數字最高為怪物血量 */
        final long rightDamage = Math.max(0, Math.min(damage, hp));

        attacker.addDamage(from, rightDamage, updateAttackTime);

        if (this.getStats().getSelfD() != -1) {

            final long newHp = this.getHp() - rightDamage;
            this.setHp(newHp);

            if (this.getHp() > 0) {
                if (this.getHp() < this.getStats().getSelfDHp()) { // HP is below the selfd level
                    this.getMap().killMonster(this, from, false, false, this.getStats().getSelfD(), lastSkill);
                } else { // Show HP
                    for (final AttackerEntry mattacker : attackers) {

                        for (final AttackingMapleCharacter cattacker : mattacker.getAttackers()) {
                            if (cattacker != null && cattacker.getAttacker().getMap() == from.getMap()) { // current attacker is on the map of the monster
                                if (cattacker.getLastAttackTime() >= System.currentTimeMillis() - 4000) {
                                    cattacker.getAttacker().getClient().sendPacket(MobPacket.showMonsterHP(getObjectId(), (int) Math.ceil((hp * 100.0) / getMobMaxHp())));
                                }
                            }
                        }
                    }
                }
            } else { // Character killed it without explosing :(
                this.getMap().killMonster(this, from, true, false, (byte) 1, lastSkill);
            }

        } else {

            if (this.getSponge() != null) {
                if (this.getSponge().getHp() > 0) { // If it's still alive, dont want double/triple rewards
                    // Sponge are always in the same map, so we can use this.map
                    // The only mob that uses sponge are PB/HT
                    final long newHp = this.getSponge().getHp() - rightDamage;
                    this.getSponge().setHp(newHp);

                    if (this.getSponge().getHp() <= 0) {
                        this.getMap().killMonster(sponge.get(), from, true, false, (byte) 1, lastSkill);
                    } else {
                        this.getMap().broadcastMessage(MobPacket.showBossHP(sponge.get()));
                    }
                }
            }

            if (this.getHp() > 0) {

                final long newHp = this.getHp() - rightDamage;
                this.setHp(newHp);

                /* 檢查有無Event */
                if (eventInstance != null) {
                    eventInstance.monsterDamaged(from, this, (int) rightDamage);
                } else {
                    final EventInstanceManager em = from.getEventInstance();
                    if (em != null) {
                        em.monsterDamaged(from, this, (int) rightDamage);
                    }
                }

                if (this.getSponge() == null && hp > 0) {
                    switch (this.getStats().getHPDisplayType()) {

                        case 0:
                            this.getMap().broadcastMessage(MobPacket.showBossHP(this), this.getPosition());
                            break;
                        case 1:
                            this.getMap().broadcastMessage(MobPacket.damageFriendlyMob(this, damage, true));
                            break;
                        case -1:
                        case 2:
                            this.getMap().broadcastMessage(MobPacket.showMonsterHP(getObjectId(), (int) Math.ceil((hp * 100.0) / getMobMaxHp())));
                            from.mulungEnergyModify(true);
                            break;
                        case 3:
                            for (final AttackerEntry mattacker : attackers) {
                                for (final AttackingMapleCharacter cattacker : mattacker.getAttackers()) {
                                    if (cattacker != null && cattacker.getAttacker().getMap() == from.getMap()) { // current attacker is on the map of the monster
                                        if (cattacker.getLastAttackTime() >= System.currentTimeMillis() - 4000) {
                                            cattacker.getAttacker().getClient().sendPacket(MobPacket.showMonsterHP(getObjectId(), (int) Math.ceil((hp * 100.0) / getMobMaxHp())));
                                        }
                                    }
                                }
                            }
                            break;
                    }
                }

                if (this.getHp() <= 0) {
                    if (getStats().getHPDisplayType() == -1) {
                        this.getMap().broadcastMessage(MobPacket.showMonsterHP(getObjectId(), (int) Math.ceil((hp * 100.0) / getMobMaxHp())));

                    }
                    this.getMap().killMonster(this, from, true, false, (byte) 1, lastSkill);
                }
            }
        }
        startDropItemSchedule();
    }

    /**
     * 怪物補血
     *
     * @param hp 補血的血量
     * @param mp 補血的魔量
     * @param broadcast 是否要通知地图上的角色
     */
    public final void heal(int hp, int mp, final boolean broadcast) {

        long totalHP = getHp() + hp;
        int totalMP = getMp() + mp;

        totalHP = totalHP > this.getMobMaxHp() ? this.getMobMaxHp() : totalHP;
        totalMP = totalMP > this.getMobMaxMp() ? this.getMobMaxMp() : totalMP;

        this.setHp(totalHP);
        this.setMp(totalMP);

        if (broadcast) {
            this.getMap().broadcastMessage(MobPacket.healMonster(this.getObjectId(), hp));
        }

        if (this.getSponge() != null) {
            totalHP = this.getSponge().getHp() + hp;
            totalMP = this.getSponge().getMp() + mp;
            totalHP = totalHP > this.getSponge().getMobMaxHp() ? this.getSponge().getMobMaxHp() : totalHP;
            totalMP = totalMP > this.getSponge().getMobMaxMp() ? this.getSponge().getMobMaxMp() : totalMP;
            this.getSponge().setHp(totalHP);
            this.getSponge().setMp(totalMP);
        }
    }

    /**
     * 依照怪物總伤害給角色經驗值
     *
     * @param attacker 攻击此怪物的角色
     * @param exp 获得經驗值
     * @param highestDamage 是否為最高攻击
     * @param numExpSharers 多少人分享
     * @param pty
     * @param classBounsExpPercent
     * @param Premium_Bonus_EXP_PERCENT
     * @param lastskillID
     */
    //经验设置 吸怪检测 最高伤害
    private void giveExpToCharacter(final MapleCharacter attacker, int exp, final boolean highestDamage, final int numExpSharers, final byte pty, final byte classBounsExpPercent, final byte Premium_Bonus_EXP_PERCENT, final int lastskillID) {

        /**
         * 判断最高攻击 *
         */
        if (highestDamage) {
            if (eventInstance != null) {
                eventInstance.monsterKilled(attacker, this);
            } else {
                final EventInstanceManager em = attacker.getEventInstance();
                if (em != null) {
                    em.monsterKilled(attacker, this);
                }
            }
            highestDamageChar = attacker.getId();
        }
        
        double 怪物坐标X = getPosition().getX();
        double 怪物坐标Y = getPosition().getY();
        double X坐标误差 = attacker.getPosition().getX() - 怪物坐标X;
        double Y坐标误差 = attacker.getPosition().getY() - 怪物坐标Y;
        if (gui.Qhms.ConfigValuesMap.get("吸怪检测开关") != null) {
            if (gui.Qhms.ConfigValuesMap.get("吸怪检测开关") == 0) {
                if (attacker.记录坐标X == 0 && attacker.记录坐标Y == 0) {
                    attacker.记录坐标X = 怪物坐标X;
                    attacker.记录坐标Y = 怪物坐标Y;
                } else if (怪物坐标X != attacker.记录坐标X || 怪物坐标Y != attacker.记录坐标Y) {
                    attacker.记录坐标X = 0;
                    attacker.记录坐标Y = 0;
                } else if (怪物坐标X == attacker.记录坐标X && 怪物坐标Y == attacker.记录坐标Y) {
                    attacker.吸怪指数++;
                    if (attacker.吸怪指数 == 50) {
                        int ch = World.Find.findChannel(角色ID取名字(attacker.getId()));
                        MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(角色ID取名字(attacker.getId()));
                        if (target.ban("被吸怪检测封号", attacker.isAdmin(), false, false)) {
                            String 信息 = "[系统提醒] : 玩家 " + target.getName() + " 因为使用非法插件，游戏吸怪/定点生怪，破坏游戏平衡，被系统永久封号。";
                            World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, 信息));
                        }
                    }
                }
            }
        }
        int 记录地图 = attacker.getMapId();
        if (attacker.打怪地图 == 0) {
            attacker.打怪地图 = 记录地图;
        } else if (记录地图 != attacker.打怪地图) {
            attacker.打怪地图 = 0;
            attacker.打怪数量 = 0;
            attacker.X坐标误差 = 0;
            attacker.Y坐标误差 = 0;
        }
        if (attacker.打怪地图 > 0) {
            if (X坐标误差 > attacker.X坐标误差) {
                attacker.X坐标误差 = X坐标误差;
            }
            if (Y坐标误差 > attacker.Y坐标误差) {
                attacker.Y坐标误差 = Y坐标误差;
            }
        }
        
         //当经验大于0
        if (exp > 0) {
            if (gui.Qhms.ConfigValuesMap.get("越级打怪开关") == 0) {
                int 怪物 = getMobLevel();
                int 玩家 = attacker.getLevel();
                if (玩家 < 怪物) {
                    int 相差 = 怪物 - 玩家;
                    if (相差 >= 10 && 相差 <= 20) {
                        exp = (int) (exp * 0.6);
                    } else if (相差 >= 21 && 相差 <= 30) {
                        exp = (int) (exp * 0.4);
                    } else if (相差 >= 31) {
                        exp = (int) (exp * 0.2);
                    }
                }
            }

        if (exp > 0) {

            // 檢查怪物有無 SHOWDOWN Buff  怪物本身的经验

            final MonsterStatusEffect mse = stati.get(MonsterStatus.SHOWDOWN);
            if (mse != null) {
                exp += (int) (exp * (mse.getX() / 100.0));
            }
            //檢查攻击者有無 HOLY_SYMBOL Buff//神圣祈祷，牧师技能
            final Integer holySymbol = attacker.getBuffedValue(MapleBuffStat.HOLY_SYMBOL);
            if (holySymbol != null) {
                if (numExpSharers == 1) {
                    exp *= 1.0 + (holySymbol.doubleValue() / 500.0);
                } else {
                    exp *= 1.0 + (holySymbol.doubleValue() / 100.0);
                }
            }
            // 受詛咒状态，經驗砍半
           // if (attacker.hasDisease(MapleDisease.CURSE)) {
           //     exp /= 2;
           // }
            
            int 职业 = attacker.getJob();
            int 职业2 = MapleParty.幸运职业;
            if ((职业 == 职业2) || (职业 - 职业2 == 1) || (职业2 - 职业 == -1)) {
                exp += exp * 0.5;
            }
            //被诅咒减少经验
            if (attacker.hasDisease(MapleDisease.CURSE)) {
                if (attacker.getEquippedFuMoMap().get(32) != null) {
                    exp *= 5;
                } else {
                    exp /= 2;
                }
            }

            // exp *= attacker.getEXPMod() * (int) (attacker.getStat().expBuff / 100.0);
            double lastexp = attacker.getStat().realExpBuff - 100.0 <= 0 ? 100 : attacker.getStat().realExpBuff - 100;
            //exp *= attacker.getEXPMod() * (int) (lastexp / 100.0);
            //exp = (int) Math.min(Integer.MAX_VALUE, exp * (attacker.getLevel() < 10 ? GameConstants.getExpRate_Below10(attacker.getJob()) : ChannelServer.getInstance(map.getChannel()).getExpRate()));
            //do this last just incase someone has a 2x exp card and its set to max value
            
            //不同等级经验倍数设置 给予经验
            exp *= attacker.getEXPMod() * (int) (attacker.getStat().expBuff / 100.0);
            //exp = Math.min(Integer.MAX_VALUE, exp * (attacker.getLevel() < 10 ? GameConstants.getExpRate_Below10(attacker.getJob()) : ChannelServer.getInstance(map.getChannel()).getExpRate()));
            //do this last just incase someone has a 2x exp card and its set to max value
            
        if (("情怀冒险岛".equals(ServerConfig.SERVERNAME)) || ("枫之谷".equals(ServerConfig.SERVERNAME))) {
        if (attacker.getLevel() < 10)
            exp = 1 * exp * ChannelServer.getInstance(this.map.getChannel()).getExpRate();
        
        else if ((attacker.getLevel() >= 10) && (attacker.getLevel() < 30))
            exp = 5 * exp * ChannelServer.getInstance(this.map.getChannel()).getExpRate();
        
        else if ((attacker.getLevel() >= 30) && (attacker.getLevel() < 60))
            exp = 4 * exp * ChannelServer.getInstance(this.map.getChannel()).getExpRate();
        
        else if ((attacker.getLevel() >= 60) && (attacker.getLevel() < 90))   
            exp = 3 * exp * ChannelServer.getInstance(this.map.getChannel()).getExpRate();
        
        else if ((attacker.getLevel() >= 90) && (attacker.getLevel() < 120))   
            exp = 2 * exp * ChannelServer.getInstance(this.map.getChannel()).getExpRate();
        
         else
            exp *= ChannelServer.getInstance(this.map.getChannel()).getExpRate();
       }
        
    
       //全局经验倍数   
        //if (attacker.getLevel() < 10)
        //    exp = 1 * exp * ChannelServer.getInstance(this.map.getChannel()).getExpRate();
        
        else exp *= ChannelServer.getInstance(this.map.getChannel()).getExpRate();  
            
            
            
             //未知经验加成?似乎没作用
            int classBonusExp = 0;
            if (classBounsExpPercent > 0) {
                classBonusExp = (int) ((exp / 100.0) * classBounsExpPercent);
            }
            
            //网吧经验
            int Premium_Bonus_EXP = 0;
            //if (Premium_Bonus_EXP_PERCENT == 0) {
            if (gui.Qhms.ConfigValuesMap.get("网吧经验加成") != 0) {
                int 网吧经验加成 = gui.Qhms.ConfigValuesMap.get("网吧经验加成");
                //Premium_Bonus_EXP = (int) ((exp / 100.0) * Premium_Bonus_EXP_PERCENT);
                Premium_Bonus_EXP += (int) ((exp / 100.0) * 网吧经验加成);
            }
            //道具佩戴经验加成
            int Equipment_Bonus_EXP = (int) ((exp / 100.0) * attacker.getStat().equipmentBonusExp);
//            if (attacker.getStat().equippedFairy) {
            if (attacker.getStat().精灵吊坠) {
                Equipment_Bonus_EXP += (int) ((exp / 100.0) * attacker.getFairyExp());
            }
            //结婚经验加成
            int wedding_EXP = 0;
            if (attacker.getMarriageId() > 0 && attacker.getMap().getCharacterById_InMap(attacker.getMarriageId()) != null && gui.Qhms.ConfigValuesMap.get("结婚经验加成") != 0) {
                //wedding_EXP += (exp / 100.0d) * 30.0d;
                int 结婚经验加成 = gui.Qhms.ConfigValuesMap.get("结婚经验加成");
                wedding_EXP += (exp / 100.0d) * 结婚经验加成;
            }
            //人气经验加成
            if (gui.Qhms.ConfigValuesMap.get("人气经验加成") > 0) {
                if (attacker.getFame() > 0) {
                    attacker.人气经验加成();
                }
            }
            
            int premiumBonusExp = 0;
            if (Premium_Bonus_EXP_PERCENT > 0) {
                premiumBonusExp = (int) ((exp / 100.0) * Premium_Bonus_EXP_PERCENT);
            }
            
            int equpBonusExp = (int) ((exp / 100.0) * attacker.getStat().equipmentBonusExp);
            if (attacker.getStat().equippedFairy) {
                equpBonusExp += (int) ((exp / 100.0) * attacker.getFairyExp());
            }
            attacker.gainExpMonster(exp, true, highestDamage, pty, classBonusExp, equpBonusExp, premiumBonusExp);

        }
        attacker.mobKilled(getId(), lastskillID);
    }
    }
    
    /*private void giveExpToCharacter(final MapleCharacter attacker, int exp, final boolean highestDamage, final int numExpSharers, final byte pty, final byte classBounsExpPercent, final byte Premium_Bonus_EXP_PERCENT, final int lastskillID) {


         //判斷最高攻击 
         
        if (highestDamage) {
            if (eventInstance != null) {
                eventInstance.monsterKilled(attacker, this);
            } else {
                final EventInstanceManager em = attacker.getEventInstance();
                if (em != null) {
                    em.monsterKilled(attacker, this);
                }
            }
            highestDamageChar = attacker.getId();
        }

        if (exp > 0) {

            // 檢查怪物有無 SHOWDOWN Buff
            final MonsterStatusEffect mse = stati.get(MonsterStatus.SHOWDOWN);
            if (mse != null) {
                exp += (int) (exp * (mse.getX() / 100.0));
            }
            //檢查攻击者有無 HOLY_SYMBOL Buff
            final Integer holySymbol = attacker.getBuffedValue(MapleBuffStat.HOLY_SYMBOL);
            if (holySymbol != null) {
                if (numExpSharers == 1) {
                    exp *= 1.0 + (holySymbol.doubleValue() / 500.0);
                } else {
                    exp *= 1.0 + (holySymbol.doubleValue() / 100.0);
                    if (exp > (int) ((double) (getMobExp()) * 1.2D)) {
                        exp = (int) ((double) (getMobExp()) * 1.2D);
                    }
                }
            }
            // 受詛咒状态，經驗砍半
            if (attacker.hasDisease(MapleDisease.CURSE)) {
                exp /= 2;
            }

            // exp *= attacker.getEXPMod() * (int) (attacker.getStat().expBuff / 100.0);
            double lastexp = attacker.getStat().realExpBuff - 100.0 <= 0 ? 100 : attacker.getStat().realExpBuff - 100;
            exp *= attacker.getEXPMod() * (int) (lastexp / 100.0);
            exp = (int) Math.min(Integer.MAX_VALUE, exp * (attacker.getLevel() < 10 ? GameConstants.getExpRate_Below10(attacker.getJob()) : ChannelServer.getInstance(map.getChannel()).getExpRate()));
            //do this last just incase someone has a 2x exp card and its set to max value
            int classBonusExp = 0;
            if (classBounsExpPercent > 0) {
                classBonusExp = (int) ((exp / 100.0) * classBounsExpPercent);
            }
            int premiumBonusExp = 0;
            if (Premium_Bonus_EXP_PERCENT > 0) {
                premiumBonusExp = (int) ((exp / 100.0) * Premium_Bonus_EXP_PERCENT);
            }
            int equpBonusExp = (int) ((exp / 100.0) * attacker.getStat().equipmentBonusExp);
            if (attacker.getStat().equippedFairy) {
                equpBonusExp += (int) ((exp / 100.0) * attacker.getFairyExp());
            }

            attacker.gainExpMonster(exp, true, highestDamage, pty, classBonusExp, equpBonusExp, premiumBonusExp);

        }
        attacker.mobKilled(getId(), lastskillID);
    }*/
    /**
     * 怪物被誰殺的
     *
     * @param killer 殺掉此怪物的角色
     * @param lastSkill 最後一個技能
     * @return
     */
    public final int killBy(final MapleCharacter killer, final int lastSkill) {

        int totalBaseExp = getMobExp();
        int baseExp;

        /* 找出最高攻击的人 */
        AttackerEntry highest = null;
        long highdamage = 0;
        for (final AttackerEntry attackEntry : attackers) {
            if (attackEntry.getDamage() > highdamage) {
                highest = attackEntry;
                highdamage = attackEntry.getDamage();
            }
        }

        /* 計算获得的經驗值*/
        for (final AttackerEntry attackEntry : attackers) {
            baseExp = (int) Math.ceil(totalBaseExp * ((double) attackEntry.getDamage() / getMobMaxHp()));
            attackEntry.killedMob(getMap(), baseExp, attackEntry == highest, lastSkill);
        }

        /* 取消控制者 */
        final MapleCharacter controll = this.getController();
        if (controll != null) { // this can/should only happen when a hidden gm attacks the monster
            controll.getClient().sendPacket(MobPacket.stopControllingMonster(getObjectId()));
            controll.stopControllingMonster(this);
        }

        //成就系統 ?
        //int achievement = 0;
        switch (getId()) {
            case 9400121:
            //achievement = 12;
            //break;
            case 8500002:
            //achievement = 13;
            //break;
            case 8510000:
            case 8520000:
            //achievement = 14;
            //break;
            default:
                break;
        }

        /*if (achievement != 0) {
         if (killer != null && killer.getParty() != null) {
         for (MaplePartyCharacter mp : killer.getParty().getMembers()) {
         final MapleCharacter mpc = killer.getMap().getCharacterById(mp.getId());
         if (mpc != null) {
         mpc.finishAchievement(achievement);
         }
         }
         } else if (killer != null) {
         killer.finishAchievement(achievement);
         }
         }
         if (killer != null && stats.isBoss()) {
         killer.finishAchievement(18);
         }*/
 /* 召喚小弟囉 */
        this.spawnRevives(getMap());

        /**
         * 檢查活動事件 *
         */
        if (eventInstance != null) {
            eventInstance.unregisterMonster(this);
            eventInstance = null;
        }

        // ?
        if (killer != null && killer.getPyramidSubway() != null) {
            killer.getPyramidSubway().onKill(killer);
        }

        MapleMonster oldSponge = getSponge();
        sponge = new WeakReference<>(null);
        if (oldSponge != null && oldSponge.isAlive()) {
            boolean set = true;
            for (MapleMapObject mon : map.getAllMonstersThreadsafe()) {
                MapleMonster mons = (MapleMonster) mon;
                if (mons.getObjectId() != oldSponge.getObjectId() && mons.getObjectId() != this.getObjectId() && (mons.getSponge() == oldSponge || mons.getLinkOid() == oldSponge.getObjectId())) { //sponge was this, please update
                    set = false;
                    break;
                }
            }
            if (set) { //all sponge monsters are dead, please kill off the sponge
                map.killMonster(oldSponge, killer, true, false, (byte) 1);
            }
        }

        nodepack = null;
        reflectpack = null;
        stati.clear();
        //attackers.clear();
        cancelDropItem();
        if (listener != null) {
            listener.monsterKilled();
        }
        int v1 = highestDamageChar;
        this.highestDamageChar = 0; //reset so we dont kill twice
        return v1;
    }

    //召喚重生
    public final void spawnRevives(final MapleMap map) {
        final List<Integer> toSpawn = stats.getRevives();

        if (toSpawn == null) {
            return;
        }
        MapleMonster spongy = null;
        switch (getId()) {
            case 8810118:
            case 8810119:
            case 8810120:
            case 8810121: //must update sponges
                for (final int i : toSpawn) {
                    final MapleMonster mob = MapleLifeFactory.getMonster(i);

                    mob.setPosition(getPosition());
                    if (eventInstance != null) {
                        eventInstance.registerMonster(mob);
                    }
                    if (dropsDisabled()) {
                        mob.disableDrops();
                    }
                    switch (mob.getId()) {
                        case 8810119:
                        case 8810120:
                        case 8810121:
                        case 8810122:
                            spongy = mob;
                            break;
                    }
                }
                if (spongy != null) {
                    map.spawnRevives(spongy, this.getObjectId());
                    for (MapleMapObject mon : map.getAllMonstersThreadsafe()) {
                        MapleMonster mons = (MapleMonster) mon;
                        if (mons.getObjectId() != spongy.getObjectId() && (mons.getSponge() == this || mons.getLinkOid() == this.getObjectId())) { //sponge was this, please update
                            mons.setSponge(spongy);
                            mons.setLinkOid(spongy.getObjectId());
                        }
                    }
                }
                break;
            case 8810026:
            case 8810130:
            case 8820008:
            case 8820009:
            case 8820010:
            case 8820011:
            case 8820012:
            case 8820013: {
                final List<MapleMonster> mobs = new ArrayList<>();

                for (final int i : toSpawn) {
                    final MapleMonster mob = MapleLifeFactory.getMonster(i);

                    mob.setPosition(getPosition());
                    if (eventInstance != null) {
                        eventInstance.registerMonster(mob);
                    }
                    if (dropsDisabled()) {
                        mob.disableDrops();
                    }
                    switch (mob.getId()) {
                        case 8810018: // Horntail Sponge
                        case 8810118:
                        case 8820009: // PinkBeanSponge0
                        case 8820010: // PinkBeanSponge1
                        case 8820011: // PinkBeanSponge2
                        case 8820012: // PinkBeanSponge3
                        case 8820013: // PinkBeanSponge4
                        case 8820014: // PinkBeanSponge5
                            spongy = mob;
                            break;
                        default:
                            mobs.add(mob);
                            break;
                    }
                }
                if (spongy != null) {
                    map.spawnRevives(spongy, this.getObjectId());

                    for (final MapleMonster i : mobs) {
                        i.setSponge(spongy);
                        map.spawnRevives(i, this.getObjectId());
                    }
                }
                break;
            }
            default: {
                for (final int i : toSpawn) {
                    final MapleMonster mob = MapleLifeFactory.getMonster(i);

                    if (eventInstance != null) {
                        eventInstance.registerMonster(mob);
                    }
                    mob.setPosition(getPosition());
                    if (dropsDisabled()) {
                        mob.disableDrops();
                    }
                    map.spawnRevives(mob, this.getObjectId());

                    if (mob.getId() == 9300216) {
                        map.broadcastMessage(MaplePacketCreator.environmentChange("Dojang/clear", 4));
                        map.broadcastMessage(MaplePacketCreator.environmentChange("dojang/end/clear", 3));
                    }
                }
                break;
            }
        }
    }

    /**
     * 設定怪物擂台團队
     *
     * @param team 哪個图队
     */
    public final void setCarnivalTeam(final byte team) {
        carnivalTeam = team;
    }

    /**
     * 傳回怪物擂台團队
     *
     * @return 怪物擂台團队
     */
    public final byte getCarnivalTeam() {
        return carnivalTeam;
    }

    /**
     * 傳回目前控制此怪物的角色
     *
     * @return 目前控制此怪物的角色
     */
    public final MapleCharacter getController() {
        return controller.get();
    }

    /**
     * 設定當前控制的角色
     *
     * @param controller 當前控制的角色
     */
    public final void setController(final MapleCharacter controller) {
        this.controller = new WeakReference<>(controller);
    }

    //控制开关判斷
    public final void switchController(final MapleCharacter newController, final boolean immediateAggro) {
        final MapleCharacter controllers = getController();
        if (controllers == newController) {
            return;
        } else if (controllers != null) {
            controllers.stopControllingMonster(this);
            controllers.getClient().sendPacket(MobPacket.stopControllingMonster(getObjectId()));
            sendStatus(controllers.getClient());
        }
        newController.controlMonster(this, immediateAggro);
        setController(newController);
        if (immediateAggro) {
            setControllerHasAggro(true);
        }
        setControllerKnowsAboutAggro(false);
        if (getId() == 9300275 && map.getId() >= 921120100 && map.getId() < 921120500) { //shammos
            if (lastNodeController != -1 && lastNodeController != newController.getId()) { //new controller, please re update
                resetShammos(newController.getClient());
            } else {
                setLastNodeController(newController.getId());
            }
        }
    }

    //重置雷克斯副本的npc
    public final void resetShammos(MapleClient c) {
        map.killAllMonsters(true);
        map.broadcastMessage(MaplePacketCreator.serverNotice(5, "A player has moved too far from Shammos. Shammos is going back to the start."));
        for (MapleCharacter chr : map.getCharactersThreadsafe()) {
            chr.changeMap(chr.getMap(), chr.getMap().getPortal(0));
        }
        MapScriptMethods.startScript_FirstUser(c, "shammos_Fenter");
    }

    /**
     * 設定怪物死亡的監聽函數
     *
     * @param listener 怪物死亡事件監聽
     */
    public final void setListener(final MonsterListener listener) {
        this.listener = listener;
    }

    //是否控制是仇恨 傳回 目前控制是仇恨
    public final boolean isControllerHasAggro() {
        return controllerHasAggro;
    }

    //設定控制是仇恨
    public final void setControllerHasAggro(final boolean controllerHasAggro) {
        this.controllerHasAggro = controllerHasAggro;
    }

    //是否控制大概誰是仇恨 傳回 目前控制大概誰是仇恨
    public final boolean isControllerKnowsAboutAggro() {
        return controllerKnowsAboutAggro;
    }

    //控制大概誰是仇恨
    public final void setControllerKnowsAboutAggro(final boolean controllerKnowsAboutAggro) {
        this.controllerKnowsAboutAggro = controllerKnowsAboutAggro;
    }

    public final void sendStatus(final MapleClient client) {
        if (reflectpack != null) {
            client.getSession().writeAndFlush(reflectpack);
        }
        if (poisons.size() > 0) {
            poisonsLock.readLock().lock();
            try {
                client.getSession().writeAndFlush(MobPacket.applyMonsterStatus(this, poisons));
            } finally {
                poisonsLock.readLock().unlock();
            }
        }
    }

    //參考 發送召喚数据 判斷
    @Override
    public final void sendSpawnData(final MapleClient client) {
        if (!isAlive()) {
            return;
        }
        client.sendPacket(MobPacket.spawnMonster(this, (lastNode >= 0 ? -2 : -1), fake ? 0xfc : (lastNode >= 0 ? 12 : 0), 0));
        sendStatus(client);
        //if (reflectpack != null) {
        //    client.sendPacket(reflectpack);
        //}
        if (lastNode >= 0) {
            client.sendPacket(MaplePacketCreator.getNodeProperties(this, map));
            if (getId() == 9300275 && map.getId() >= 921120100 && map.getId() < 921120500) { //shammos
                if (lastNodeController != -1) { //new controller, please re update. sendSpawn only comes when you get too far then come back anyway
                    resetShammos(client);
                } else {
                    setLastNodeController(client.getPlayer().getId());
                }
            }
        }
    }

    //參考 發送銷毀数据 判斷
    @Override
    public final void sendDestroyData(final MapleClient client) {
        if (lastNode == -1) {
            client.sendPacket(MobPacket.killMonster(getObjectId(), 0));
        }
        if (getId() == 9300275 && map.getId() >= 921120100 && map.getId() < 921120500) { //shammos
            resetShammos(client);
        }
    }

    //參考 PlayerCommand 玩家指令 @mob 查看怪物信息
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append(stats.getName());
        sb.append("(");
        sb.append(getId());
        sb.append(") (等級 ");
        sb.append(stats.getLevel());
        sb.append(") 在 (X");
        sb.append(getPosition().x);
        sb.append("/ Y");
        sb.append(getPosition().y);
        sb.append(") 座標 ");
        sb.append(getHp());
        sb.append("/ ");
        sb.append(getMobMaxHp());
        sb.append("血量, ");
        sb.append(getMp());
        sb.append("/ ");
        sb.append(getMobMaxMp());
        sb.append(" 魔力, MobOID: ");
        sb.append(getObjectId());
        sb.append(" || 仇恨目標 : ");
        final MapleCharacter chr = controller.get();
        sb.append(chr != null ? chr.getName() : "無");

        return sb.toString();
    }

    //參考 得到怪物型態 傳回怪物的型態
    @Override
    public final MapleMapObjectType getType() {
        return MapleMapObjectType.MONSTER;
    }

    //設定事件例子
    public final void setEventInstance(final EventInstanceManager eventInstance) {
        this.eventInstance = eventInstance;
    }

    //得到目前事件例子 傳回 事件例子
    public final EventInstanceManager getEventInstance() {
        return eventInstance;
    }

    //得到状态原始技能ID 傳回 效果技能ID
    public final int getStatusSourceID(final MonsterStatus status) {
        final MonsterStatusEffect effect = stati.get(status);
        if (effect != null) {
            return effect.getSkill();
        }
        return -1;
    }

    //得到效果 傳回效果
    public final ElementalEffectiveness getEffectiveness(final Element e) {
        if (stati.size() > 0 && stati.get(MonsterStatus.DOOM) != null) {
            return ElementalEffectiveness.NORMAL; // like blue snails
        }
        return stats.getEffectiveness(e);
    }

    //套用怪物BUFF 判斷
    public void applyMonsterBuff(final Map<MonsterStatus, Integer> effect, final int x, final int skillId, long duration, final MobSkill skill, final List<Integer> reflection) {
        final MapleCharacter con = getController();

        for (Entry<MonsterStatus, Integer> z : effect.entrySet()) {
            if (stati.containsKey(z.getKey())) {
                cancelStatus(z.getKey());
            }
            final MonsterStatusEffect effectz = new MonsterStatusEffect(z.getKey(), z.getValue(), 0, skill, true, reflection.size() > 0);
            effectz.setCancelTask(duration);
            stati.put(z.getKey(), effectz);
        }
        if (reflection.size() > 0) {
            List<MonsterStatusEffect> mse = new ArrayList<>();
            for (Entry<MonsterStatus, Integer> z : effect.entrySet()) {
                mse.add(new MonsterStatusEffect(z.getKey(), z.getValue(), 0, skill, true, reflection.size() > 0));
            }
            this.reflectpack = MobPacket.applyMonsterStatus(this, mse);
            if (con != null) {
                map.broadcastMessage(con, reflectpack, getTruePosition());
                con.getClient().getSession().writeAndFlush(this.reflectpack);
            } else {
                map.broadcastMessage(reflectpack, getTruePosition());
            }
        } else {
            for (Entry<MonsterStatus, Integer> z : effect.entrySet()) {
                final MonsterStatusEffect effectz = new MonsterStatusEffect(z.getKey(), z.getValue(), 0, skill, true, reflection.size() > 0);
                if (con != null) {
                    map.broadcastMessage(con, MobPacket.applyMonsterStatus(this, effectz), getTruePosition());
                    con.getClient().getSession().writeAndFlush(MobPacket.applyMonsterStatus(this, effectz));
                } else {
                    map.broadcastMessage(MobPacket.applyMonsterStatus(this, effectz), getTruePosition());
                }
            }
        }
    }

    public final void applyStatus(final MapleCharacter from, final MonsterStatusEffect status, final boolean poison, long duration, final boolean checkboss, final MapleStatEffect eff) {
        //if (!isAlive()) {
        //    return;
       // }
        //if (from.hasGmLevel(5)) {
        //    if (status.getStatus() != null) {
        //        from.dropMessage(6, "怪物: " + getId() + " 状态: " + status.getStatus().name() + " 是否為毒: " + poison + " 持續时间: " + duration + " 技能: " + SkillFactory.getSkillName(eff.getSourceId()));
        //    }
       // }
       
       if (!isAlive()) {
            return;
        }
        if (gui.Qhms.ConfigValuesMap.get("怪物状态开关") <= 0) {
        if (from.hasGmLevel(5)) {
            String 状态 = "";
            if (status.getStatus() != null) {
            switch (status.getStati().name()) {
                        case "STUN":
                            状态 = "怪物无法移动,[昏迷]，[冰冻]";
                            break;
                        case "POISON":
                            状态 = "怪物持续掉血,[中毒]，[灼烧]";
                            break;
                        case "SPEED":
                            状态 = "怪物减少移动速度,[缓速]，[束缚]";
                            break;
                        case "DOOM":
                            状态 = "怪物改变外观,[巫毒]，[变身]";
                            break;
                        case "SEAL":
                            状态 = "怪物无法使用技能,[封印]，[沉默]";
                            break;
                        case "SHADOW_WEB":
                            状态 = "怪物定身，无法移动,[束缚]，[昏迷]，[定身]";
                            break;
                        case "SHOWDOWN":
                            状态 = "怪物被激怒,[挑衅]，[诱导]";
                            break;
                        case "MDEF":
                            状态 = "怪物防御发生变化,[魔防]";
                            break;
                        case "WDEF":
                            状态 = "怪物防御发生变化,[物防]";
                            break;
                        default:
                            from.dropMessage(5, "怪物状态: " + status.getStati().name() + "");
                            break;
                    }
                }
                from.dropMessage(5, "怪物状态: " + 状态 + "");
            }
        }
       
        ISkill skilz = SkillFactory.getSkill(status.getSkill());
        if (skilz != null) {
            switch (stats.getEffectiveness(skilz.getElement())) {
                case IMMUNE:
                case STRONG:
                    return;
                case NORMAL:
                case WEAK:
                    break;
                default:
                    return;
            }
        }
        // compos don't have an elemental (they have 2 - so we have to hack here...)
        final int statusSkill = status.getSkill();
        switch (statusSkill) {
            case SkillType.火毒魔導士.火毒合擊: { // FP compo
                switch (stats.getEffectiveness(Element.POISON)) {
                    case IMMUNE:
                    case STRONG:
                        return;
                }
                break;
            }
            case SkillType.冰雷魔導士.冰雷合擊: { // IL compo
                switch (stats.getEffectiveness(Element.ICE)) {
                    case IMMUNE:
                    case STRONG:
                        return;
                }
                break;
            }
            case SkillType.暗夜行者3.飛毒殺:
            case SkillType.夜使者.飛毒殺:
            case SkillType.暗影神偷.飛毒殺: {
                switch (stats.getEffectiveness(Element.POISON)) {
                    case WEAK:
                        return;
                }
                break;
            }
        }

        if (duration >= 2000000000) {
            duration = 5000; //teleport master
        }

        final MonsterStatus stat = status.getStatus();

        if (getId() == 5100002 && stat == MonsterStatus.POISON) {
            return;
        }

        if (stats.isNoDoom() && stat == MonsterStatus.DOOM) {
            return;
        }
        if (stat == MonsterStatus.FREEZE) {
            switch (getId()) {
                case 9400253:
                case 9400254:
                    return;
            }
        }
        if (stats.isBoss()) {
            if (stat == MonsterStatus.POISON) {
                return;
            }
            if (stat == MonsterStatus.STUN) {
                return;
            } else if (stat != (MonsterStatus.SPEED) && stat != (MonsterStatus.NINJA_AMBUSH) && stat != (MonsterStatus.WATK)) {
                return;
            } else if (getId() == 8850011 && stat == MonsterStatus.MAGIC_CRASH) {
                return;
            } else if (stat == MonsterStatus.FREEZE) {
                return;
            }
        }

        if (stats.isFriendly() || isFake()) {
            if (stat == MonsterStatus.STUN || stat == MonsterStatus.SPEED || stat == MonsterStatus.POISON || stat == MonsterStatus.VENOMOUS_WEAPON) {
                return;
            }
        }

        if ((stat == MonsterStatus.VENOMOUS_WEAPON || stat == MonsterStatus.POISON) && eff == null) {
            return;
        }

        if (stati.containsKey(stat)) {
            return;
        }

        if (stat == MonsterStatus.POISON || stat == MonsterStatus.VENOMOUS_WEAPON) {
            //int count = 0;
            poisonsLock.readLock().lock();
            try {
                for (MonsterStatusEffect mse : poisons) {
                    if (mse != null && (mse.getSkill() == eff.getSourceId() || mse.getSkill() == GameConstants.getLinkedAttackSkill(eff.getSourceId()) || GameConstants.getLinkedAttackSkill(mse.getSkill()) == eff.getSourceId())) {
                        //count++;
                        return;
                    }
                }
            } finally {
                poisonsLock.readLock().unlock();
            }

        }

        if (poison && getHp() > 1 && eff != null) {
            duration = Math.max(duration, eff.getDOTTime() * 1000);
        }
        duration += from.getStat().dotTime * 1000;

        long aniTime = duration;
        //if (skilz != null) {
        //    aniTime += skilz.getAnimationTime();
        //}

        status.setCancelTask(aniTime);

        if (poison && getHp() > 1) { // 中毒[POISON]
            if (status.getchr() != null) {
                return;
            }
            status.setDotTime(duration);
            int dam = (int) Math.min(Short.MAX_VALUE, (long) (getMobMaxHp() / (70.0 - from.getSkillLevel(status.getSkill())) + 0.999));
            if (from.hasGmLevel(5)) {
                from.dropMessage(6, "[持續伤害] 開始處理效果 - 技能ID：" + eff.getSourceId());
            }
            status.setValue(status.getStatus(), dam);
            status.setPoisonDamage(dam, from);
            int poisonDamage = (int) (aniTime / 1000 * status.getX());
            if (from.hasGmLevel(5)) {
                from.dropMessage(6, "[持續伤害] 持續伤害： " + ((getHp() > poisonDamage) ? poisonDamage : (getHp() - 1)) + " 持續时间：" + aniTime + " 持續掉血：" + status.getX());
            }
        } else if (statusSkill == 5211004 && getHp() > 1) {//烈焰喷射
            if (status.getchr() != null) {
                return;
            }
            status.setDotTime(duration);
            int dam = (int) Math.min(Short.MAX_VALUE, (long) (getMobMaxHp() / (70.0 - from.getSkillLevel(status.getSkill())) + 0.999));
            if (from.isAdmin()) {
                from.dropMessage(6, "[持續伤害] 開始處理效果 - 技能ID：" + eff.getSourceId());
            }
            status.setValue(status.getStatus(), dam);
            status.setPoisonDamage(dam, from);
            int poisonDamage = (int) (aniTime / 1000 * status.getX());
            if (from.isAdmin()) {
                from.dropMessage(6, "[持續伤害] 持續伤害： " + ((getHp() > poisonDamage) ? poisonDamage : (getHp() - 1)) + " 持續时间：" + aniTime + " 持續掉血：" + status.getX());
            }
        } else if (statusSkill == 4111003 || statusSkill == 14111001) { // shadow web
            status.setValue(status.getStatus(), (int) (getMobMaxHp() / 50.0 + 0.999));
            status.setPoisonDamage(status.getX(), from);
        } else if (statusSkill == 4341003) { // monsterbomb
            status.setPoisonDamage((int) (eff.getDamage() * from.getStat().getCurrentMaxBaseDamage() / 100.0), from);
        } else if (statusSkill == 4121004 || statusSkill == 4221004) { // NINJA_AMBUSH
            status.setValue(status.getStatus(), Math.min(Short.MAX_VALUE, (int) (eff.getDamage() * from.getStat().getCurrentMaxBaseDamage() / 100.0)));
            int dam = (int) (aniTime / 1000 * status.getX() / 2);
            status.setPoisonDamage(dam, from);
            if (dam > 0) {
                if (dam >= hp) {
                    dam = (int) (hp - 1);
                }
                damage(from, dam, false);
            }
        }

        final MapleCharacter con = getController();
        if (stat == MonsterStatus.POISON || stat == MonsterStatus.VENOMOUS_WEAPON) {
            poisonsLock.writeLock().lock();
            try {
                poisons.add(status);
                status.scheduledoPoison(this);
            } finally {
                poisonsLock.writeLock().unlock();
            }
        } else {
            stati.put(stat, status);
        }
        if (con != null) {
            map.broadcastMessage(con, MobPacket.applyMonsterStatus(this, status), getTruePosition());
            con.getClient().sendPacket(MobPacket.applyMonsterStatus(this, status));
        } else {
            map.broadcastMessage(MobPacket.applyMonsterStatus(this, status), getTruePosition());
        }
        if (from.getDebugMessage()) {
            from.dropMessage(6, "開始 => 給予怪物状态: 持續时间[" + aniTime + "] 状态效果[" + status.getStatus().name() + "] 開始时间[" + System.currentTimeMillis() + "]");
        }

    }

    //消除技能
    public final void dispelSkill(final MobSkill skillId) {
        List<MonsterStatus> toCancel = new ArrayList<>();
        for (Entry<MonsterStatus, MonsterStatusEffect> effects : stati.entrySet()) {
            MonsterStatusEffect mse = effects.getValue();
            if (mse != null && mse.getMobSkill() != null && mse.getMobSkill().getSkillId() == skillId.getSkillId()) { //not checking for level.
                toCancel.add(effects.getKey());
            }
        }
        for (MonsterStatus stat : toCancel) {
            cancelStatus(stat);
        }
    }

    /*public final void cancelStatus(final Collection<MonsterStatus> stats) {
        List<MonsterStatusEffect> mses = new ArrayList<>();
        for (MonsterStatus stat : stats) {
            if (stat == MonsterStatus.SUMMON) {
                return;
            }
            final MonsterStatusEffect mse = stati.get(stat);
            if (mse == null || !isAlive()) {
                return;
            }
            if (mse.isReflect()) {
                reflectpack = null;
            }
            mse.cancelPoisonSchedule(this);
            mses.add(mse);
        }

        final MapleCharacter con = getController();
        if (con != null) {
            //    con.addSleep(100);
            map.broadcastMessage(con, MobPacket.cancelMonsterStatus(this, mses), getTruePosition());
            con.getClient().sendPacket(MobPacket.cancelMonsterStatus(this, mses));
        } else {
            map.broadcastMessage(MobPacket.cancelMonsterStatus(this, mses), getTruePosition());
        }
        for (MonsterStatus stat : stats) {
            stati.remove(stat);
        }
    }*/
    public final void cancelStatus(final MonsterStatus stat) {
        if (stat == MonsterStatus.EMPTY || stat == MonsterStatus.SUMMON) {
            return;
        }
        final MonsterStatusEffect mse = stati.get(stat);
        if (mse == null || !isAlive()) {
            return;
        }
        if (mse.isReflect()) {
            reflectpack = null;
        }
        mse.cancelPoisonSchedule(this);
        final MapleCharacter con = getController();
        if (con != null) {
            map.broadcastMessage(con, MobPacket.cancelMonsterStatus(this, mse), getTruePosition());
            con.getClient().sendPacket(MobPacket.cancelMonsterStatus(this, mse));
        } else {
            map.broadcastMessage(MobPacket.cancelMonsterStatus(this, mse), getTruePosition());
        }
        stati.remove(stat);
    }

    public final void cancelSingleStatus(final MonsterStatusEffect stat) {
        if (stat == null || stat.getStatus() == MonsterStatus.EMPTY || stat.getStatus() == MonsterStatus.SUMMON || !isAlive()) {
            return;
        }
        if (stat.getStatus() != MonsterStatus.POISON && stat.getStatus() != MonsterStatus.VENOMOUS_WEAPON) {
            cancelStatus(stat.getStatus());
            return;
        }
        poisonsLock.writeLock().lock();
        try {
            if (!poisons.contains(stat)) {
                return;
            }
            poisons.remove(stat);
            if (stat.isReflect()) {
                reflectpack = null;
            }
            stat.cancelPoisonSchedule(this);
            final MapleCharacter con = getController();
            if (con != null) {
                map.broadcastMessage(con, MobPacket.cancelMonsterStatus(this, stat), getTruePosition());
                con.getClient().getSession().writeAndFlush(MobPacket.cancelMonsterStatus(this, stat));
            } else {
                map.broadcastMessage(MobPacket.cancelMonsterStatus(this, stat), getTruePosition());
            }
        } finally {
            poisonsLock.writeLock().unlock();
        }
    }

    public final void doPoison(final MonsterStatusEffect status, final WeakReference<MapleCharacter> weakChr) {
        if ((status.getStatus() == MonsterStatus.VENOMOUS_WEAPON || status.getStatus() == MonsterStatus.POISON || status.getStatus() == MonsterStatus.NEUTRALISE) && poisons.size() <= 0) {
            return;
        }
        if (status.getStatus() != MonsterStatus.VENOMOUS_WEAPON && status.getStatus() != MonsterStatus.POISON && status.getStatus() == MonsterStatus.NEUTRALISE && !stati.containsKey(status.getStatus())) {
            return;
        }
        if (weakChr == null) {
            return;
        }
        int damage = status.getPoisonDamage();
        final boolean shadowWeb = status.getSkill() == 4111003 || status.getSkill() == 14111001;
        final MapleCharacter chr = weakChr.get();
        boolean cancel = damage <= 0 || chr == null || chr.getMapId() != map.getId();
        if (damage >= hp) {
            damage = (int) hp - 1;
            cancel = !shadowWeb || cancel;
        }
        if (!cancel) {
            damage(chr, damage, false);
            if (shadowWeb) {
                map.broadcastMessage(MobPacket.damageMonster(getObjectId(), damage), getTruePosition());
            }
        }
    }

    //設定暫時效果
    public final void setTempEffectiveness(final Element e, final long milli) {
        stats.setEffectiveness(e, ElementalEffectiveness.WEAK);
        MobTimer.getInstance().schedule(new Runnable() {

            @Override
            public void run() {
                stats.removeEffectiveness(e);
            }
        }, milli);
    }

    //是否已經BUFF 傳回目前状态
    public final boolean isBuffed(final MonsterStatus status) {
        return stati.containsKey(status);
    }

    //得到當前BUF 傳回目前状态
    public final MonsterStatusEffect getBuff(final MonsterStatus status) {
        return stati.get(status);
    }

    public final int getStatiSize() {
        return stati.size() + (poisons.size() > 0 ? 1 : 0);
    }

    public final ArrayList<MonsterStatusEffect> getAllBuffs() {
        ArrayList<MonsterStatusEffect> ret = new ArrayList<MonsterStatusEffect>();
        for (MonsterStatusEffect e : stati.values()) {
            ret.add(e);
        }
        poisonsLock.readLock().lock();
        try {
            for (MonsterStatusEffect e : poisons) {
                ret.add(e);
            }
        } finally {
            poisonsLock.readLock().unlock();
        }
        return ret;
    }

    //設定 假
    public final void setFake(final boolean fake) {
        this.fake = fake;
    }

    //是否假 傳回假
    public final boolean isFake() {
        return fake;
    }

    //是否活著 傳回給HP
    public final boolean isAlive() {
        return hp > 0;
    }

    public boolean isAttackedBy(MapleCharacter chr) {
        for (AttackerEntry aentry : attackers) {
            if (aentry.contains(chr)) {
                return true;
            }
        }
        return false;
    }

    //是否第一下攻击 傳回状态是第一下攻击
    public final boolean isFirstAttack() {
        return stats.isFirstAttack();
    }

    //得到技能 傳回状态得到技能
    public final List<Pair<Integer, Integer>> getSkills() {
        return stats.getSkills();
    }

    //是否是技能 傳回状态的技能
    public final boolean hasSkill(final int skillId, final int level) {
        return stats.hasSkill(skillId, level);
    }

    // 得到最後使用的技能
    public final long getLastSkillUsed(final int skillId) {
        if (usedSkills.containsKey(skillId)) {
            return usedSkills.get(skillId);
        }
        return 0;
    }

    //設定最後使用的技能
    public final void setLastSkillUsed(final int skillId, final long now, final long cooltime) {
        switch (skillId) {
            case 140:
                usedSkills.put(skillId, now + (cooltime * 2));
                usedSkills.put(141, now);
                break;
            case 141:
                usedSkills.put(skillId, now + (cooltime * 2));
                usedSkills.put(140, now + cooltime);
                break;
            default:
                usedSkills.put(skillId, now + cooltime);
                break;
        }
    }

    //得到技能是空 傳回得到技能是空
    public final byte getNoSkills() {
        return stats.getNoSkills();
    }

    //得到是給状态
    public final int getBuffToGive() {
        return stats.getBuffToGive();
    }

    public int getLevel() {
        return stats.getLevel();
    }

    //毒任務
    private final class PoisonTask implements Runnable {

        /**
         *
         * @param poisonDamage 毒伤害
         * @param chr 角色
         * @param status 状态
         * @param MP 怪物MP
         * @param cancelTask 取消任務
         * @param shadowWeb 盜賊-3轉 暗殺者 影網術
         * @param map 判斷地图
         */
        private final int poisonDamage;
        private final MapleCharacter chr;
        private final MonsterStatusEffect status;
        private final Runnable cancelTask;
        private final boolean shadowWeb;
        private final MapleMap map;

        private PoisonTask(final int poisonDamage, final MapleCharacter chr, final MonsterStatusEffect status, final Runnable cancelTask, final boolean shadowWeb) {
            this.poisonDamage = poisonDamage;
            this.chr = chr;
            this.status = status;
            this.cancelTask = cancelTask;
            this.shadowWeb = shadowWeb;
            this.map = chr.getMap();
        }

        @Override
        public void run() {
            long damage = poisonDamage;
            if (damage >= hp) {
                damage = hp - 1;
                if (!shadowWeb) {
                    cancelTask.run();
                    status.cancelTask();
                }
            }
            if (hp > 1 && damage > 0) {
                damage(chr, damage, false);
                if (shadowWeb) {
                    map.broadcastMessage(MobPacket.damageMonster(getObjectId(), damage), getPosition());
                }
            }
        }
    }

    //正在攻击的角色
    private static class AttackingMapleCharacter {

        /**
         *
         * @param attacker 攻击者
         * @param lastAttackTime 最後攻击时间
         */
        private final WeakReference<MapleCharacter> attacker;
        private long lastAttackTime;

        public AttackingMapleCharacter(final MapleCharacter attacker, final long lastAttackTime) {
            super();
            this.attacker = new WeakReference<>(attacker);
            this.lastAttackTime = lastAttackTime;
        }

        //得到最後攻击时间 傳回 最後攻击时间
        public final long getLastAttackTime() {
            return lastAttackTime;
        }

        //設定最後攻击时间
        public final void setLastAttackTime(final long lastAttackTime) {
            this.lastAttackTime = lastAttackTime;
        }

        //得到現在攻击者 傳回攻击者
        public final MapleCharacter getAttacker() {
            return attacker.get();
        }
    }

    private interface AttackerEntry {

        List<AttackingMapleCharacter> getAttackers();

        public void addDamage(MapleCharacter from, long damage, boolean updateAttackTime);

        public long getDamage();

        public boolean contains(MapleCharacter chr);

        public void killedMob(MapleMap map, int baseExp, boolean mostDamage, int lastSkill);
    }

    private final class SingleAttackerEntry implements AttackerEntry {

        private long damage = 0;
        private final int chrid;
        private long lastAttackTime;
        private final int channel;

        public SingleAttackerEntry(final MapleCharacter from, final int cserv) {
            this.chrid = from.getId();
            this.channel = cserv;
        }

        @Override
        public void addDamage(final MapleCharacter from, final long damage, final boolean updateAttackTime) {
            if (chrid == from.getId()) {
                this.damage += damage;
                if (updateAttackTime) {
                    lastAttackTime = System.currentTimeMillis();
                }
            }
        }

        @Override
        public final List<AttackingMapleCharacter> getAttackers() {
            final MapleCharacter chr = map.getCharacterById(chrid);
            if (chr != null) {
                return Collections.singletonList(new AttackingMapleCharacter(chr, lastAttackTime));
            } else {
                return Collections.emptyList();
            }
        }

        @Override
        public boolean contains(final MapleCharacter chr) {
            return chrid == chr.getId();
        }

        @Override
        public long getDamage() {
            return damage;
        }

        @Override
        public void killedMob(final MapleMap map, final int baseExp, final boolean mostDamage, final int lastSkill) {
            final MapleCharacter chr = map.getCharacterById(chrid);
            if (chr != null && chr.isAlive()) {
                giveExpToCharacter(chr, baseExp, mostDamage, 1, (byte) 0, (byte) 0, (byte) 0, lastSkill);
            }
        }

        @Override
        public int hashCode() {
            return chrid;
        }

        @Override
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final SingleAttackerEntry other = (SingleAttackerEntry) obj;
            return chrid == other.chrid;
        }
    }

    private static final class ExpMap {

        public final int exp;
        public final byte ptysize;
        public final byte Class_Bonus_EXP;
        public final byte Premium_Bonus_EXP;

        public ExpMap(final int exp, final byte ptysize, final byte Class_Bonus_EXP, final byte Premium_Bonus_EXP) {
            super();
            this.exp = exp;
            this.ptysize = ptysize;
            this.Class_Bonus_EXP = Class_Bonus_EXP;
            this.Premium_Bonus_EXP = Premium_Bonus_EXP;
        }
    }

    private static final class OnePartyAttacker {

        public MapleParty lastKnownParty;
        public long damage;
        public long lastAttackTime;

        public OnePartyAttacker(final MapleParty lastKnownParty, final long damage) {
            super();
            this.lastKnownParty = lastKnownParty;
            this.damage = damage;
            this.lastAttackTime = System.currentTimeMillis();
        }
    }

    private class PartyAttackerEntry implements AttackerEntry {

        private long totDamage;
        private final Map<Integer, OnePartyAttacker> attackers = new HashMap<>(6);
        private final int partyid;
        private final int channel;

        public PartyAttackerEntry(final int partyid, final int cserv) {
            this.partyid = partyid;
            this.channel = cserv;
        }

        @Override
        public List<AttackingMapleCharacter> getAttackers() {
            final List<AttackingMapleCharacter> ret = new ArrayList<>(attackers.size());
            for (final Entry<Integer, OnePartyAttacker> entry : attackers.entrySet()) {
                final MapleCharacter chr = map.getCharacterById(entry.getKey());
                if (chr != null) {
                    ret.add(new AttackingMapleCharacter(chr, entry.getValue().lastAttackTime));
                }
            }
            return ret;
        }

        private Map<MapleCharacter, OnePartyAttacker> resolveAttackers() {
            final Map<MapleCharacter, OnePartyAttacker> ret = new HashMap<>(attackers.size());
            for (final Entry<Integer, OnePartyAttacker> aentry : attackers.entrySet()) {
                final MapleCharacter chr = map.getCharacterById(aentry.getKey());
                if (chr != null) {
                    ret.put(chr, aentry.getValue());
                }
            }
            return ret;
        }

        //包含 傳回目前是誰在攻击
        @Override
        public final boolean contains(final MapleCharacter chr) {
            return attackers.containsKey(chr.getId());
        }

        //得到伤害 傳回總伤害
        @Override
        public final long getDamage() {
            return totDamage;
        }

        //增加伤害的公式
        @Override
        public void addDamage(final MapleCharacter from, final long damage, final boolean updateAttackTime) {
            final OnePartyAttacker oldPartyAttacker = attackers.get(from.getId());
            if (oldPartyAttacker != null) {
                oldPartyAttacker.damage += damage;
                oldPartyAttacker.lastKnownParty = from.getParty();
                if (updateAttackTime) {
                    oldPartyAttacker.lastAttackTime = System.currentTimeMillis();
                }
            } else {
                // TODO actually this causes wrong behaviour when the party changes between attacks
                // only the last setup will get exp - but otherwise we'd have to store the full party
                // constellation for every attack/everytime it changes, might be wanted/needed in the
                // future but not now
                final OnePartyAttacker onePartyAttacker = new OnePartyAttacker(from.getParty(), damage);
                attackers.put(from.getId(), onePartyAttacker);
                if (!updateAttackTime) {
                    onePartyAttacker.lastAttackTime = 0;
                }
            }
            totDamage += damage;
        }

        //參考殺死怪物的公式
        @Override
        public final void killedMob(final MapleMap map, final int baseExp, final boolean mostDamage, final int lastSkill) {
            MapleCharacter pchr, highest = null;
            long iDamage, highestDamage = 0;
            int iexp;
            MapleParty party;
            double averagePartyLevel, expWeight, levelMod, innerBaseExp, expFraction;
            List<MapleCharacter> expApplicable;
            final Map<MapleCharacter, ExpMap> expMap = new HashMap<>(6);
            byte Class_Bonus_EXP;
            byte Premium_Bonus_EXP;
            byte added_partyinc = 0;

            for (final Entry<MapleCharacter, OnePartyAttacker> attacker : resolveAttackers().entrySet()) {
                party = attacker.getValue().lastKnownParty;
                averagePartyLevel = 0;

                Class_Bonus_EXP = 0;
                Premium_Bonus_EXP = 0;
                expApplicable = new ArrayList<>();
                for (final MaplePartyCharacter partychar : party.getMembers()) {
                    if (attacker.getKey().getLevel() - partychar.getLevel() <= 5 || stats.getLevel() - partychar.getLevel() <= 5) {
                        pchr = map.getCharacterById(partychar.getId());
                        if (pchr != null) {
                            if (pchr.isAlive() && pchr.getMap() == map) {
                                expApplicable.add(pchr);
                                averagePartyLevel += pchr.getLevel();

                                if (Class_Bonus_EXP == 0) {
//                                    Class_Bonus_EXP = ServerConstants.Class_Bonus_EXP(pchr.getJob());
                                }
                                if (pchr.getStat().equippedWelcomeBackRing && Premium_Bonus_EXP == 0) {
                                    Premium_Bonus_EXP = 80;
                                }
                                if (pchr.getStat().hasPartyBonus && added_partyinc < 4) {
                                    added_partyinc++;
                                }
                            }
                        }
                    }
                }
                if (expApplicable.size() > 1) {
                    averagePartyLevel /= expApplicable.size();
                } else {
                    Class_Bonus_EXP = 0; //no class bonus if not in a party.
                }
                iDamage = attacker.getValue().damage;
                if (iDamage > highestDamage) {
                    highest = attacker.getKey();
                    highestDamage = iDamage;
                }
                innerBaseExp = baseExp * ((double) iDamage / totDamage);
                expFraction = innerBaseExp / (expApplicable.size() + 1);

                for (final MapleCharacter expReceiver : expApplicable) {
                    iexp = expMap.get(expReceiver) == null ? 0 : expMap.get(expReceiver).exp;
                    expWeight = (expReceiver == attacker.getKey() ? 2.0 : 0.3); //hopefully this is correct o.o -/+0.4
                    levelMod = expReceiver.getLevel() / averagePartyLevel;
                    if (levelMod > 1.0 || attackers.containsKey(expReceiver.getId())) {
                        levelMod = 1.0;
                    }
                    iexp += (int) Math.round(expFraction * expWeight * levelMod);
                    expMap.put(expReceiver, new ExpMap(iexp, (byte) (expApplicable.size() + added_partyinc), Class_Bonus_EXP, Premium_Bonus_EXP));
                }
            }
            ExpMap expmap;
            for (final Entry<MapleCharacter, ExpMap> expReceiver : expMap.entrySet()) {
                expmap = expReceiver.getValue();
                giveExpToCharacter(expReceiver.getKey(), expmap.exp, mostDamage ? expReceiver.getKey() == highest : false, expMap.size(), expmap.ptysize, expmap.Class_Bonus_EXP, expmap.Premium_Bonus_EXP, lastSkill);
            }
        }

        /*@Override
        public final void killedMob(final MapleMap map, final int baseExp, final boolean mostDamage, final int lastSkill) {
            MapleCharacter pchr, highest = null;
            long iDamage, highestDamage = 0;
            int iexp;
            MapleParty party;
            double averagePartyLevel, levelMod, innerBaseExp;
            List<MapleCharacter> expApplicable;
            final Map<MapleCharacter, ExpMap> expMap = new HashMap<>(6);
            byte Class_Bonus_EXP;
            byte Premium_Bonus_EXP;
            byte added_partyinc = 0;

            for (final Entry<MapleCharacter, OnePartyAttacker> attacker : resolveAttackers().entrySet()) {
                party = attacker.getValue().lastKnownParty;
                averagePartyLevel = 0;

                Class_Bonus_EXP = 0;
                Premium_Bonus_EXP = 0;
                expApplicable = new ArrayList<>();
                for (final MaplePartyCharacter partychar : party.getMembers()) {
                    if (attacker.getKey().getLevel() - partychar.getLevel() <= 5 || stats.getLevel() - partychar.getLevel() <= 5) {
                        pchr = map.getCharacterById(partychar.getId());
                        if (pchr != null) {
                            if (pchr.isAlive() && pchr.getMap() == map) {
                                expApplicable.add(pchr);
                                averagePartyLevel += pchr.getLevel();

                                if (pchr.getStat().equippedWelcomeBackRing && Premium_Bonus_EXP == 0) {
                                    Premium_Bonus_EXP = 80;
                                }
                                if (pchr.getStat().hasPartyBonus && added_partyinc < 4) {
                                    added_partyinc++;
                                }
                            }
                        }
                    }
                }
                if (expApplicable.size() <= 1) {
                    Class_Bonus_EXP = 0; //no class bonus if not in a party.
                }

                iDamage = attacker.getValue().damage;
                if (iDamage > highestDamage) {
                    highest = attacker.getKey();
                    highestDamage = iDamage;
                }
                innerBaseExp = baseExp * ((double) iDamage / totDamage);

                for (final MapleCharacter expReceiver : expApplicable) {
                    iexp = expMap.get(expReceiver) == null ? 0 : expMap.get(expReceiver).exp;
                    levelMod = expReceiver.getLevel() / averagePartyLevel * 0.4;
                    iexp += (int) Math.round(((attacker.getKey().getId() == expReceiver.getId() ? 0.6 : 0.0) + levelMod) * innerBaseExp);
                    expMap.put(expReceiver, new ExpMap(iexp, (byte) (expApplicable.size() + added_partyinc), Class_Bonus_EXP, Premium_Bonus_EXP));
                }
            }
            ExpMap expmap;
            for (final Entry<MapleCharacter, ExpMap> expReceiver : expMap.entrySet()) {
                expmap = expReceiver.getValue();
                giveExpToCharacter(expReceiver.getKey(), expmap.exp, mostDamage ? expReceiver.getKey() == highest : false, expMap.size(), expmap.ptysize, expmap.Class_Bonus_EXP, expmap.Premium_Bonus_EXP, lastSkill);
            }
        }*/
        //參考公式
        @Override
        public final int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + partyid;
            return result;
        }

        //參考公式
        @Override
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final PartyAttackerEntry other = (PartyAttackerEntry) obj;
            return partyid == other.partyid;
        }
    }

    //得到怪物關联 傳回怪物關联
    public int getLinkOid() {
        return linkoid;
    }

    //設定怪物關联
    public void setLinkOid(int lo) {
        this.linkoid = lo;
    }

    //得到狀況 傳回狀況
    public final Map<MonsterStatus, MonsterStatusEffect> getStati() {
        return stati;
    }

    //自己加能力
    public void addEmpty() {
        for (MonsterStatus stat : MonsterStatus.values()) {
            if (stat.isDefault()) {
                stati.put(stat, new MonsterStatusEffect(stat, 0, 0, null, false));
            }
        }
    }

    //得到怪物被動 傳回怪物被動
    public final int getStolen() {
        return stolen;
    }

    //設定怪物被動
    public final void setStolen(final int s) {
        this.stolen = s;
    }

    //處理盜賊2轉俠盜-妙手術
    public final void handleSteal(MapleCharacter chr) {
        double showdown = 100.0;
        final MonsterStatusEffect mse = getBuff(MonsterStatus.SHOWDOWN);
        if (mse != null) {
            showdown += mse.getX();
        }

        ISkill steal = SkillFactory.getSkill(4201004);
        final int level = chr.getSkillLevel(steal), chServerrate = ChannelServer.getInstance(chr.getClient().getChannel()).getDropRate();
        if (level > 0 && !getStats().isBoss() && stolen == -1 && steal.getEffect(level).makeChanceResult()) {
            final MapleMonsterInformationProvider mi = MapleMonsterInformationProvider.getInstance();
            final List<MonsterDropEntry> de = mi.retrieveDrop(getId());
            if (de == null) {
                stolen = 0;
                return;
            }
            final List<MonsterDropEntry> dropEntry = new ArrayList<>(de);
            Collections.shuffle(dropEntry);
            IItem idrop;
            for (MonsterDropEntry d : dropEntry) {
                if (d.itemId > 0 && d.questid == 0 && d.itemId / 10000 != 238 && Randomizer.nextInt(999999) < (int) (10 * d.chance * chServerrate * chr.getDropMod() * chr.getDropm() * ((chr.getVipExpRate() / 100D) + 1.0D) * (chr.getStat().dropBuff / 100.0) * (showdown / 100.0))) { //kinda op
                    if (GameConstants.getInventoryType(d.itemId) == MapleInventoryType.EQUIP) {
                        Equip eq = (Equip) MapleItemInformationProvider.getInstance().getEquipById(d.itemId);
                        idrop = MapleItemInformationProvider.getInstance().randomizeStats(eq);
                    } else {
                        idrop = new Item(d.itemId, (byte) 0, (short) (d.Maximum != 1 ? Randomizer.nextInt(d.Maximum - d.Minimum) + d.Minimum : 1), (byte) 0);
                    }
                    stolen = d.itemId;
                    map.spawnMobDrop(idrop, map.calcDropPos(getPosition(), getTruePosition()), this, chr, (byte) 0, (short) 0);
                    break;
                }
            }
        } else {
            stolen = 0; //failed once, may not go again
        }
    }

    //設定最後節點
    public final void setLastNode(final int lastNode) {
        this.lastNode = lastNode;
    }

    //得到最後節點 傳回最後節點
    public final int getLastNode() {
        return lastNode;
    }

    //設定最後節點控制
    public final void setLastNodeController(final int lastNode) {
        this.lastNodeController = lastNode;
    }

    //得到最後節點控制 傳回最後節點
    public final int getLastNodeController() {
        return lastNodeController;
    }

    //取消掉落物品
    public final void cancelDropItem() {
        if (dropItemSchedule != null) {
            dropItemSchedule.cancel(false);
            dropItemSchedule = null;
        }
    }

    //啟動物品掉落时间
    public final void startDropItemSchedule() {
        cancelDropItem();
        if (stats.getDropItemPeriod() <= 0 || !isAlive()) {
            return;
        }
        final int itemId;
        switch (getId()) {
            case 9300061:
                itemId = 4001101;
                break;
            case 9300102:
                itemId = 4031507;
                break;
            default: //until we find out ... what other mobs use this and how to get the ITEMID
                return;
        }
        shouldDropItem = false;
        dropItemSchedule = MobTimer.getInstance().register(new Runnable() {

            @Override
            public void run() {
                if (isAlive() && map != null) {
                    if (shouldDropItem) {
                        map.spawnAutoDrop(itemId, getPosition());
                    } else {
                        shouldDropItem = true;
                    }
                }
            }
        }, stats.getDropItemPeriod() * 1000);
    }

    //得到當前節點封包 傳回節點封包
    public byte[] getNodePacket() {
        return nodepack;
    }

    //設定節點封包
    public void setNodePacket(final byte[] np) {
        this.nodepack = np;
    }

    //殺死所有怪物 函數
    public final void killed() {
        if (listener != null) {
            listener.monsterKilled();
        }
        listener = null;
    }
}
