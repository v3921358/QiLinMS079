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
package handling.world;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class MapleParty implements Serializable {

    private static final long serialVersionUID = 9179541993413738569L;
    private MaplePartyCharacter leader;
    private final List<MaplePartyCharacter> members = new LinkedList<>();
    private int id;
    private int averageLevel = 0;
    
     public static String 开服名字 = "芒果冒险岛";
    public static String IP地址 = "127.0.0.1"; //服务器IP
    public static int 互相伤害 = 0;
    public static int 通缉BOSS = 0;
    public static int 地图回收 = 0;
    public static int 通缉地图 = 0;
    public static int 神秘商人线程 = 0;
    public static int 神秘商人 = 0;
    public static int 神秘商人频道 = 0;
    public static int 神秘商人地图 = 0;
    public static int 神秘商人坐标X = 0;
    public static int 神秘商人坐标Y = 0;
    public static int 神秘商人时间 = 0;
    public static int 蝙蝠魔A部队 = 0;
    public static int 蝙蝠魔B部队 = 0;
    public static int 蝙蝠魔C部队 = 0;
    public static int 蝙蝠魔D部队 = 0;
    public static int 幸运职业 = 0;
    public static int 特等奖 = 0;
    public static int 一等奖 = 0;
    public static int 二等奖 = 0;
    public static int 三等奖 = 0;
    
    public static int 服务端 = 1;
    public static int 服务端启动中 = 0;
    public static int OX答题活动 = 0;
    public static int 任务修复 = 0;
    
    public static int 服务端启动模式 = 0;
    public static int 容纳人数 = 500;
    public static int 冷却1 = 0;
    public static String 启动账号 = "";
    public static int 反馈冷却 = 0;
    public static int 启动进度 = 0;
    public static int 反馈冷却1 = 0;
    public static int 反馈冷却2 = 0;
    public static int 反馈冷却3 = 0;
    public static int yzcs = 0;
    public static int 版本检测 = 0;
    public static int 版本检测2 = 0;
    public static int 机器人待机 = 0;
    public static int 每日清理 = 0;
    public static int 伤害显示 = 0;
    public static int 出题间隔 = 0;
    public static int 信息同步 = 0;
    public static int 服务端调查 = 0;
    public static int 服务端运行时长 = 0;
    public static int 玩家登陆次数 = 0;
    public static int 角色登陆次数 = 0;
    public static int 游戏公告群输出 = 0;
    public static int 人数统计 = 0;
    public static int 流畅模式 = 0;
    public static int 试密码 = 0;
    public static int 调试3 = 0;
    public static int 市场测试 = 0;
    public static int 技能调试 = 0;
    public static int 云黑名单 = 0;
    public static int 大逃杀活动 = 0;
    public static int 双区域模式 = 0;
    public static int 队列人数 = 0;
    public static int 商城出售通知 = 1;
    ///////////////////////////
    public static int ZEVMSmxd = 0;
    ///////////////////////////
    public static int 单机使用模式 = 0;
    public static int 联机使用模式 = 0;
    public static int 远控启动开关 = 0;
    public static int 强制更新开关 = 0;
    public static int 广告阅读开关 = 0;
    public static int 配置检测开关 = 0;
    public static int 自动更新开关 = 0;
    public static int 脚本编辑器授权开关 = 0;
    //
    public static int 脚本判断1 = 0;
    public static int 脚本收取1 = 0;
    public static int 脚本给予1 = 0;
    public static int 脚本判断数量 = 0;
    public static int 脚本收取数量 = 0;
    public static int 买了否冷 = 0;
    public static String 复读机彩旦 = "";
    //
    public static int 活动安排 = 0;
    public static int 雪球赛 = 0;
    //

    private void calculateAverageLevel() {
        int value = 0;
        for (MaplePartyCharacter chr : members) {
            value += chr.getLevel();
        }
        value = (int) ((double) averageLevel / (double) members.size());
        this.averageLevel = value;
    }

    public MapleParty(int id, MaplePartyCharacter chrfor) {
        this.leader = chrfor;
        this.members.add(this.leader);
        this.id = id;
        this.averageLevel = 0;
    }

    public boolean containsMembers(MaplePartyCharacter member) {
        return members.contains(member);
    }

    public void addMember(MaplePartyCharacter member) {
        members.add(member);
        calculateAverageLevel();
    }

    public void removeMember(MaplePartyCharacter member) {
        members.remove(member);
        calculateAverageLevel();
    }

    public void updateMember(MaplePartyCharacter member) {
        for (int i = 0; i < members.size(); i++) {
            MaplePartyCharacter chr = members.get(i);
            if (chr.equals(member)) {
                members.set(i, member);
            }
        }
        calculateAverageLevel();
    }

    public MaplePartyCharacter getMemberById(int id) {
        for (MaplePartyCharacter chr : members) {
            if (chr.getId() == id) {
                return chr;
            }
        }
        return null;
    }

    public MaplePartyCharacter getMemberByIndex(int index) {
        return members.get(index);
    }

    public Collection<MaplePartyCharacter> getMembers() {
        return new LinkedList<>(members);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MaplePartyCharacter getLeader() {
        return leader;
    }

    public void setLeader(MaplePartyCharacter nLeader) {
        leader = nLeader;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MapleParty other = (MapleParty) obj;
        return id == other.id;
    }

    public int getAverageLevel() {
        return this.averageLevel;
    }
}
