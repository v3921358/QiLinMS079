package server.custom.rank;

import client.BuddyEntry;
import client.MapleCharacter;
import client.MapleQuestStatus;
import constants.GameConstants;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.quest.MapleQuest;

public class RankManager {

    private static class InstanceHolder {

        public static final RankManager instance = new RankManager();
    }

    public static RankManager getInstance() {
        return InstanceHolder.instance;
    }

    private RankManager() {

    }
    private Map<Integer, List<MiniGamePoints>> miniGamePointsMap = new HashMap<>();
    private Map<Integer, List<MiniGamePoints>> topMap = new HashMap<>();
    private long updateTime = 1000 * 60 * 60 * 1;//一小时更新一次
    private long lastUpdateTime;

    public void loadRank(int quest) {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<MiniGamePoints> miniGamePoints_list = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM queststatus WHERE quest = ?");
            ps.setInt(1, quest);
            rs = ps.executeQuery();
            while (rs.next()) {
                int characterid = rs.getInt("characterid");
                String customData = rs.getString("customData");
                if (customData == null || customData.length() < 5 || customData.indexOf(",") == -1) {
                    customData = "0,0,0"; //refresh
                }
                MiniGamePoints ngp = new MiniGamePoints();
                ngp.setCharacterid(characterid);
                ngp.setWins(Integer.parseInt(customData.split(",")[2]));
                ngp.setTies(Integer.parseInt(customData.split(",")[1]));
                ngp.setLosses(Integer.parseInt(customData.split(",")[0]));
                miniGamePoints_list.add(ngp);
            }
            Collections.sort(miniGamePoints_list);
            miniGamePointsMap.put(quest, miniGamePoints_list);
        } catch (SQLException ex) {
            Logger.getLogger(RankManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RankManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String args[]) {
    }

    
    public List<MiniGamePoints> getTop(int gameType, int top) {
        List<MiniGamePoints> ret = null;
        if (System.currentTimeMillis() - lastUpdateTime > updateTime) {
            topMap.clear();
            miniGamePointsMap.clear();
        }
        if (!miniGamePointsMap.containsKey(gameType)) {
            loadRank(gameType);
        }
        if (!miniGamePointsMap.containsKey(gameType)) {
            return null;
        }
        
        if(topMap.containsKey(gameType)){
            return topMap.get(gameType);
        }
        
        ret = new ArrayList<>();
        int topIndex = 0;
        for (MiniGamePoints mgp : miniGamePointsMap.get(gameType)) {
            if (topIndex >= 10) {
                break;
            }
            mgp.setBuddyEntry(BuddyEntry.getByIdfFromDB(mgp.getCharacterid()));
            ret.add(mgp);
            topIndex++;
        }
        topMap.put(gameType, ret);
        lastUpdateTime=System.currentTimeMillis();
        return ret;
    }

    //questids:
    //omok - win = 122200
    //matchcard - win = 122210
    // 1 五子棋 GameConstants.OMOK_SCORE = 122200;
    //2 GameConstants.MATCH_SCORE = 122210;
    public String getData(MapleCharacter chr, int GameType) {
        MapleQuest quest = MapleQuest.getInstance(GameType);
        MapleQuestStatus record;
        if (chr.getQuestNoAdd(quest) == null) {
            record = chr.getQuestNAdd(quest);
            record.setCustomData("0,0,0");
        } else {
            record = chr.getQuestNoAdd(quest);
            if (record.getCustomData() == null || record.getCustomData().length() < 5 || record.getCustomData().indexOf(",") == -1) {
                record.setCustomData("0,0,0"); //refresh
            }
        }
        return record.getCustomData();
    }


    public Map<Integer, List<MiniGamePoints>> getMiniGamePointsMap() {
        return miniGamePointsMap;
    }

    public void setMiniGamePointsMap(Map<Integer, List<MiniGamePoints>> miniGamePointsMap) {
        this.miniGamePointsMap = miniGamePointsMap;
    }

}
