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
package handling.channel;

import client.MapleClient;
import database.DBConPool;
import database.DatabaseConnection;
import java.util.List;
import java.util.LinkedList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;
import server.Timer;
import tools.FileoutputUtil;
import tools.MaplePacketCreator;

public class MapleGuildRanking {
    int 职业排行显示 = 10;
    private static final MapleGuildRanking instance = new MapleGuildRanking();
    private final List<GuildRankingInfo> ranks = new LinkedList<>();
    private final List<levelRankingInfo> ranks1 = new LinkedList<>();
    private final List<mesoRankingInfo> ranks2 = new LinkedList<>();
    private final Map<Integer, List<JobRankingInfo>> JobRanks = new HashMap();
    //战士类
    private final List<levelRankingInfo> ranks剑客 = new LinkedList<>();//110
    
    public List<levelRankingInfo> 剑客() {
        if (ranks剑客.isEmpty()) {
            剑客职业排行();
        }
        return ranks剑客;
    }

    private void 剑客职业排行() {
        ranks剑客.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 110  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks剑客.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("剑客排行错误");
        }
    }

    private final List<levelRankingInfo> ranks勇士 = new LinkedList<>();//111

    public List<levelRankingInfo> 勇士() {
        if (ranks勇士.isEmpty()) {
            勇士职业排行();
        }
        return ranks勇士;
    }

    private void 勇士职业排行() {
        ranks勇士.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 111  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks勇士.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("勇士排行错误");
        }
    }

    private final List<levelRankingInfo> ranks英雄 = new LinkedList<>();//112

    public List<levelRankingInfo> 英雄() {
        if (ranks英雄.isEmpty()) {
            英雄职业排行();
        }
        return ranks英雄;
    } 

    private void 英雄职业排行() {
        ranks英雄.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 112  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks英雄.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("英雄排行错误");
        }
    }

    private final List<levelRankingInfo> ranks枪战士 = new LinkedList<>();//130

    public List<levelRankingInfo> 枪战士() {
        if (ranks枪战士.isEmpty()) {
            枪战士职业排行();
        }
        return ranks枪战士;
    }

    private void 枪战士职业排行() {
        ranks枪战士.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 130  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks枪战士.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("枪战士排行错误");
        }
    }

    private final List<levelRankingInfo> ranks龙骑士 = new LinkedList<>();//131

    public List<levelRankingInfo> 龙骑士() {
        if (ranks龙骑士.isEmpty()) {
            龙骑士职业排行();
        }
        return ranks龙骑士;
    }

    private void 龙骑士职业排行() {
        ranks龙骑士.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 131  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks龙骑士.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("龙骑士排行错误");
        }
    }

    private final List<levelRankingInfo> ranks黑骑士 = new LinkedList<>();//132

    public List<levelRankingInfo> 黑骑士() {
        if (ranks黑骑士.isEmpty()) {
            黑骑士职业排行();
        }
        return ranks黑骑士;
    }

    private void 黑骑士职业排行() {
        ranks黑骑士.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 132  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks黑骑士.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("黑骑士排行错误");
        }
    }

    private final List<levelRankingInfo> ranks准骑士 = new LinkedList<>();//120

    public List<levelRankingInfo> 准骑士() {
        if (ranks准骑士.isEmpty()) {
            准骑士职业排行();
        }
        return ranks准骑士;
    }

    private void 准骑士职业排行() {
        ranks准骑士.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 120  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks准骑士.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("准骑士排行错误");
        }
    }

    private final List<levelRankingInfo> ranks骑士 = new LinkedList<>();//121

    public List<levelRankingInfo> 骑士() {
        if (ranks骑士.isEmpty()) {
            骑士职业排行();
        }
        return ranks骑士;
    }

    private void 骑士职业排行() {
        ranks骑士.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 121  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks骑士.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("骑士排行错误");
        }
    }

    private final List<levelRankingInfo> ranks圣骑士 = new LinkedList<>();//122

    public List<levelRankingInfo> 圣骑士() {
        if (ranks圣骑士.isEmpty()) {
            圣骑士职业排行();
        }
        return ranks圣骑士;
    }

    private void 圣骑士职业排行() {
        ranks圣骑士.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 122  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks圣骑士.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("圣骑士排行错误");
        }
    }

    //魔法师类
    private final List<levelRankingInfo> ranks火毒法师 = new LinkedList<>();//210

    public List<levelRankingInfo> 火毒法师() {
        if (ranks火毒法师.isEmpty()) {
            火毒法师职业排行();
        }
        return ranks火毒法师;
    }

    private void 火毒法师职业排行() {
        ranks火毒法师.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 210  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks火毒法师.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("火毒法师排行错误");
        }
    }

    private final List<levelRankingInfo> ranks火毒巫师 = new LinkedList<>();//211

    public List<levelRankingInfo> 火毒巫师() {
        if (ranks火毒巫师.isEmpty()) {
            火毒巫师职业排行();
        }
        return ranks火毒巫师;
    }

    private void 火毒巫师职业排行() {
        ranks火毒巫师.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 211  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks火毒巫师.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("火毒巫师排行错误");
        }
    }

    private final List<levelRankingInfo> ranks火毒魔导师 = new LinkedList<>();//212

    public List<levelRankingInfo> 火毒魔导师() {
        if (ranks火毒魔导师.isEmpty()) {
            火毒魔导师职业排行();
        }
        return ranks火毒魔导师;
    }

    private void 火毒魔导师职业排行() {
        ranks火毒魔导师.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 212  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks火毒魔导师.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("火毒魔导师排行错误");
        }
    }

    private final List<levelRankingInfo> ranks冰雷法师 = new LinkedList<>();//220

    public List<levelRankingInfo> 冰雷法师() {
        if (ranks冰雷法师.isEmpty()) {
            冰雷法师职业排行();
        }
        return ranks冰雷法师;
    }

    private void 冰雷法师职业排行() {
        ranks冰雷法师.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 220  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks冰雷法师.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("冰雷法师排行错误");
        }
    }

    private final List<levelRankingInfo> ranks冰雷巫师 = new LinkedList<>();//221

    public List<levelRankingInfo> 冰雷巫师() {
        if (ranks冰雷巫师.isEmpty()) {
            冰雷巫师职业排行();
        }
        return ranks冰雷巫师;
    }

    private void 冰雷巫师职业排行() {
        ranks冰雷巫师.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 221  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks冰雷巫师.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("冰雷巫师排行错误");
        }
    }

    private final List<levelRankingInfo> ranks冰雷魔导师 = new LinkedList<>();//222

    public List<levelRankingInfo> 冰雷魔导师() {
        if (ranks冰雷魔导师.isEmpty()) {
            冰雷魔导师职业排行();
        }
        return ranks冰雷魔导师;
    }

    private void 冰雷魔导师职业排行() {
        ranks冰雷魔导师.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 222  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks冰雷魔导师.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("冰雷魔导师排行错误");
        }
    }
    private final List<levelRankingInfo> ranks牧师 = new LinkedList<>();//230

    public List<levelRankingInfo> 牧师() {
        if (ranks牧师.isEmpty()) {
            牧师职业排行();
        }
        return ranks牧师;
    }

    private void 牧师职业排行() {
        ranks牧师.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 230  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks牧师.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("牧师排行错误");
        }
    }

    private final List<levelRankingInfo> ranks祭师 = new LinkedList<>();//231

    public List<levelRankingInfo> 祭师() {
        if (ranks祭师.isEmpty()) {
            祭师职业排行();
        }
        return ranks祭师;
    }

    private void 祭师职业排行() {
        ranks祭师.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 231  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks祭师.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("祭师排行错误");
        }
    }

    private final List<levelRankingInfo> ranks主教 = new LinkedList<>();//232

    public List<levelRankingInfo> 主教() {
        if (ranks主教.isEmpty()) {
            主教职业排行();
        }
        return ranks主教;
    }

    private void 主教职业排行() {
        ranks主教.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 232  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks主教.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("主教排行错误");
        }
    }

    //弓箭手
    private final List<levelRankingInfo> ranks猎人 = new LinkedList<>();//310

    public List<levelRankingInfo> 猎人() {
        if (ranks猎人.isEmpty()) {
            猎人职业排行();
        }
        return ranks猎人;
    }

    private void 猎人职业排行() {
        ranks猎人.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 310  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks猎人.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("猎人排行错误");
        }
    }

    private final List<levelRankingInfo> ranks射手 = new LinkedList<>();//311

    public List<levelRankingInfo> 射手() {
        if (ranks射手.isEmpty()) {
            射手职业排行();
        }
        return ranks射手;
    }

    private void 射手职业排行() {
        ranks射手.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 311  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks射手.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("射手排行错误");
        }
    }

    private final List<levelRankingInfo> ranks神射手 = new LinkedList<>();//312

    public List<levelRankingInfo> 神射手() {
        if (ranks神射手.isEmpty()) {
            神射手职业排行();
        }
        return ranks神射手;
    }

    private void 神射手职业排行() {
        ranks神射手.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 312  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks神射手.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("神射手排行错误");
        }
    }

    private final List<levelRankingInfo> ranks弩弓手 = new LinkedList<>();//320

    public List<levelRankingInfo> 弩弓手() {
        if (ranks弩弓手.isEmpty()) {
            弩弓手职业排行();
        }
        return ranks弩弓手;
    }

    private void 弩弓手职业排行() {
        ranks弩弓手.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 320  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks弩弓手.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("弩弓手排行错误");
        }
    }

    private final List<levelRankingInfo> ranks游侠 = new LinkedList<>();//321

    public List<levelRankingInfo> 游侠() {
        if (ranks游侠.isEmpty()) {
            游侠职业排行();
        }
        return ranks游侠;
    }

    private void 游侠职业排行() {
        ranks游侠.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 321  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks游侠.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("游侠排行错误");
        }
    }

    private final List<levelRankingInfo> ranks箭神 = new LinkedList<>();//322

    public List<levelRankingInfo> 箭神() {
        if (ranks箭神.isEmpty()) {
            箭神职业排行();
        }
        return ranks箭神;
    }

    private void 箭神职业排行() {
        ranks箭神.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 322  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks箭神.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("箭神排行错误");
        }
    }
    //飞侠类
    private final List<levelRankingInfo> ranks刺客 = new LinkedList<>();//410

    public List<levelRankingInfo> 刺客() {
        if (ranks刺客.isEmpty()) {
            刺客职业排行();
        }
        return ranks刺客;
    }

    private void 刺客职业排行() {
        ranks刺客.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 410  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks刺客.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("刺客排行错误");
        }
    }

    private final List<levelRankingInfo> ranks无影人 = new LinkedList<>();//411

    public List<levelRankingInfo> 无影人() {
        if (ranks无影人.isEmpty()) {
            无影人职业排行();
        }
        return ranks无影人;
    }

    private void 无影人职业排行() {
        ranks无影人.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 411  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks无影人.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("无影人排行错误");
        }
    }

    private final List<levelRankingInfo> ranks隐士 = new LinkedList<>();//412

    public List<levelRankingInfo> 隐士() {
        if (ranks隐士.isEmpty()) {
            隐士职业排行();
        }
        return ranks隐士;
    }

    private void 隐士职业排行() {
        ranks隐士.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 412  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks隐士.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("隐士排行错误");
        }
    }

    private final List<levelRankingInfo> ranks侠客 = new LinkedList<>();//420

    public List<levelRankingInfo> 侠客() {
        if (ranks侠客.isEmpty()) {
            侠客职业排行();
        }
        return ranks侠客;
    }

    private void 侠客职业排行() {
        ranks侠客.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 420  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks侠客.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("侠客排行错误");
        }
    }
    private final List<levelRankingInfo> ranks独行客 = new LinkedList<>();//421

    public List<levelRankingInfo> 独行客() {
        if (ranks独行客.isEmpty()) {
            独行客职业排行();
        }
        return ranks独行客;
    }

    private void 独行客职业排行() {
        ranks独行客.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 421  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks独行客.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("独行客排行错误");
        }
    }
    private final List<levelRankingInfo> ranks侠盗 = new LinkedList<>();//422

    public List<levelRankingInfo> 侠盗() {
        if (ranks侠盗.isEmpty()) {
            侠盗职业排行();
        }
        return ranks侠盗;
    }

    private void 侠盗职业排行() {
        ranks侠盗.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 422  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks侠盗.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("侠盗排行错误");
        }
    }
    //海盗类
    private final List<levelRankingInfo> ranks拳手 = new LinkedList<>();//510

    public List<levelRankingInfo> 拳手() {
        if (ranks拳手.isEmpty()) {
            拳手职业排行();
        }
        return ranks拳手;
    }

    private void 拳手职业排行() {
        ranks拳手.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 510  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks拳手.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("拳手排行错误");
        }
    }

    private final List<levelRankingInfo> ranks斗士 = new LinkedList<>();//511

    public List<levelRankingInfo> 斗士() {
        if (ranks斗士.isEmpty()) {
            斗士职业排行();
        }
        return ranks斗士;
    }

    private void 斗士职业排行() {
        ranks斗士.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 511  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks斗士.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("斗士排行错误");
        }
    }
    private final List<levelRankingInfo> ranks冲锋队长 = new LinkedList<>();//512

    public List<levelRankingInfo> 冲锋队长() {
        if (ranks冲锋队长.isEmpty()) {
            冲锋队长职业排行();
        }
        return ranks冲锋队长;
    }

    private void 冲锋队长职业排行() {
        ranks冲锋队长.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 512  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks冲锋队长.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("冲锋队长排行错误");
        }
    }
    private final List<levelRankingInfo> ranks火枪手 = new LinkedList<>();//520

    public List<levelRankingInfo> 火枪手() {
        if (ranks火枪手.isEmpty()) {
            火枪手职业排行();
        }
        return ranks火枪手;
    }

    private void 火枪手职业排行() {
        ranks火枪手.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 520  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks火枪手.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("火枪手排行错误");
        }
    }
    private final List<levelRankingInfo> ranks大副 = new LinkedList<>();//521

    public List<levelRankingInfo> 大副() {
        if (ranks大副.isEmpty()) {
            大副职业排行();
        }
        return ranks大副;
    }

    private void 大副职业排行() {
        ranks大副.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 521  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks大副.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("大副排行错误");
        }
    }
    private final List<levelRankingInfo> ranks船长 = new LinkedList<>();//522

    public List<levelRankingInfo> 船长() {
        if (ranks船长.isEmpty()) {
            船长职业排行();
        }
        return ranks船长;
    }

    private void 船长职业排行() {
        ranks船长.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE job = 522  ORDER BY `level` DESC LIMIT " + 职业排行显示 + "");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks船长.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("船长排行错误");
        }
    }

    public void RankingUpdate() {
        //  System.out.println("");//排 名 已 加 载 完 成 : : :
        Timer.WorldTimer.getInstance().register(new Runnable() {

            public void run() {
                try {
                    reload();
                    showLevelRank();
                    showMesoRank();
                    //   System.out.println("Ranking update");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.err.println("Could not update rankings");
                }
            }
        }, 1 * 60 * 60 * 1000, 1 * 60 * 60 * 1000);
    }

    public static MapleGuildRanking getInstance() {
        return instance;
    }

    public List<GuildRankingInfo> getGuildRank() {
        if (ranks.isEmpty()) {
            reload();
        }
        return ranks;
    }

    public List<levelRankingInfo> getLevelRank() {
        if (ranks1.isEmpty()) {
            showLevelRank();
        }
        return ranks1;
    }

    public List<mesoRankingInfo> getMesoRank() {
        if (ranks2.isEmpty()) {
            showMesoRank();
        }
        return ranks2;
    }

    private void reload() {
        ranks.clear();

        Connection con = DatabaseConnection.getConnection();
        ResultSet rs;
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM guilds ORDER BY `GP` DESC LIMIT 10")) {
            rs = ps.executeQuery();
            while (rs.next()) {
                final GuildRankingInfo rank = new GuildRankingInfo(
                        rs.getString("name"),
                        rs.getInt("GP"),
                        rs.getInt("logo"),
                        rs.getInt("logoColor"),
                        rs.getInt("logoBG"),
                        rs.getInt("logoBGColor"));

                ranks.add(rank);
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println("家族排行错误" + e);
        }
    }

    private void showLevelRank() {
        ranks1.clear();
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE gm < 1 ORDER BY `level` DESC LIMIT 10");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                final levelRankingInfo rank1 = new levelRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks1.add(rank1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println("人物排行错误");
        }
    }

   

   

    private void showMesoRank() {
        ranks2.clear();

        Connection con = DatabaseConnection.getConnection();
        ResultSet rs;
        try (PreparedStatement ps = con.prepareStatement("SELECT *, ( chr.meso + s.meso ) as money FROM `characters` as chr , `storages` as s WHERE chr.gm < 1  AND s.accountid = chr.accountid ORDER BY money DESC LIMIT 10")) {
            rs = ps.executeQuery();
            while (rs.next()) {
                final mesoRankingInfo rank2 = new mesoRankingInfo(
                        rs.getString("name"),
                        rs.getLong("money"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                ranks2.add(rank2);
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println("金币排行错误");
        }
    }
    

   

    public List<JobRankingInfo> getJobRank(int type) {
        if (JobRanks.get(type) == null || JobRanks.get(type).isEmpty()) {
            loadJobRank(type);
        }
        return JobRanks.get(type);
    }

    

    private void loadJobRank(int type) {
        if (JobRanks.get(type) != null) {
            JobRanks.get(type).clear();
        }
        String jobRange = "";

        if (type == 1) {
            jobRange = "and job >= '100' and job <= '132'";
        } else if (type == 2) {
            jobRange = "and job >= '200' and job <= '232'";
        } else if (type == 3) {
            jobRange = "and job >= '300' and job <= '322'";
        } else if (type == 4) {
            jobRange = "and job >= '400' and job <= '422'";
        } else if (type == 5) {
            jobRange = "and job >= '500' and job <= '522'";
        } else if (type == 6) {
            jobRange = "and job >= '2000' and job <= '2112'";
        }

        try (Connection con = DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE gm = 0 " + jobRange + " and accountid in (select id from accounts where banned= '0') ORDER BY `level` DESC LIMIT 10");
            ResultSet rs = ps.executeQuery();
            LinkedList<JobRankingInfo> JobRankList = new LinkedList();
            while (rs.next()) {
                final JobRankingInfo JobRank = new JobRankingInfo(
                        rs.getString("name"),
                        rs.getInt("level"),
                        rs.getInt("job"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("int"),
                        rs.getInt("luk"));
                JobRankList.add(JobRank);
            }
            JobRanks.put(type, JobRankList);
            ps.close();
            rs.close();
        } catch (SQLException e) {
            FileoutputUtil.outError("logs/資料庫異常.txt", e);
            System.err.println("未能顯示职业" + type + "排行");
        }
    }
    
    
    
    

    public static class mesoRankingInfo {

        private final String name;
        private final long meso;
        private final int str, dex, _int, luk;

        public mesoRankingInfo(String name, long meso, int str, int dex, int intt, int luk) {
            this.name = name;
            this.meso = meso;
            this.str = str;
            this.dex = dex;
            this._int = intt;
            this.luk = luk;
        }

        public String getName() {
            return name;
        }

        public long getMeso() {
            return meso;
        }

        public int getStr() {
            return str;
        }

        public int getDex() {
            return dex;
        }

        public int getInt() {
            return _int;
        }

        public int getLuk() {
            return luk;
        }
    }

    public static class JobRankingInfo {

        private final String name;
        private final int level, str, dex, _int, luk, job;

        public JobRankingInfo(String name, int level, int job, int str, int dex, int intt, int luk) {
            this.name = name;
            this.level = level;
            this.job = job;
            this.str = str;
            this.dex = dex;
            this._int = intt;
            this.luk = luk;
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        public int getStr() {
            return str;
        }

        public int getDex() {
            return dex;
        }

        public int getInt() {
            return _int;
        }

        public int getLuk() {
            return luk;
        }

        public int getJob() {
            return job;
        }
    }

    public static class levelRankingInfo {

        private final String name;
        private final int level, str, dex, _int, luk;

        public levelRankingInfo(String name, int level, int str, int dex, int intt, int luk) {
            this.name = name;
            this.level = level;
            this.str = str;
            this.dex = dex;
            this._int = intt;
            this.luk = luk;
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        public int getStr() {
            return str;
        }

        public int getDex() {
            return dex;
        }

        public int getInt() {
            return _int;
        }

        public int getLuk() {
            return luk;
        }
    }

    public static class GuildRankingInfo {

        private final String name;
        private final int gp, logo, logocolor, logobg, logobgcolor;

        public GuildRankingInfo(String name, int gp, int logo, int logocolor, int logobg, int logobgcolor) {
            this.name = name;
            this.gp = gp;
            this.logo = logo;
            this.logocolor = logocolor;
            this.logobg = logobg;
            this.logobgcolor = logobgcolor;
        }

        public String getName() {
            return name;
        }

        public int getGP() {
            return gp;
        }

        public int getLogo() {
            return logo;
        }

        public int getLogoColor() {
            return logocolor;
        }

        public int getLogoBg() {
            return logobg;
        }

        public int getLogoBgColor() {
            return logobgcolor;
        }
    }
}
