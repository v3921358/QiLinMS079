package constants;

import server.life.MapleMonster;
import server.maps.MapleMap;
import server.maps.MapleMapObject;

public class MapConstants {

    public static boolean isBlockFM(final int mapid) {
        int header = mapid / 100000;
        if (isEventMap(mapid)) {
            return true;
        }
        if (header == 9800 && (mapid % 10 == 1 || mapid % 1000 == 100)) {
            return true;
        }
        if (mapid / 10000 == 92502) {
            return true;
        }
        if (header == 7090) {
            return true;
        }
        if (header == 1090) {
            return true;
        }
        switch (mapid) {
            case 702060000:
                return true;
            default:
                return false;
        }
    }
    
    public static boolean isForceRespawn(int mapid) {
        switch (mapid) {
            case 925100100: //crocs and stuff
                return true;
            default:
                return false;
        }
    }

    public static boolean isCar(final int mapid) {
        switch (mapid) {
            case 680000000:
            case 980000000:
            case 980030000:
                return true;
        }
        return false;
    }

    public static boolean isStartingEventMap(final int mapid) {
        switch (mapid) {
            case 109010000:
            case 109020001:
            case 109030001:
            case 109030101:
            case 109030201:
            case 109030301:
            case 109030401:
            case 109040000:
            case 109060001:
            case 109060002:
            case 109060003:
            case 109060004:
            case 109060005:
            case 109060006:
            case 109080000:
            case 109080001:
            case 109080002:
            case 109080003:
                return true;
        }
        return false;
    }

    public static boolean isEventMap(final int mapid) {
        return (mapid >= 109010000 && mapid < 109050000) || (mapid > 109050001 && mapid < 109090000) || (mapid >= 809040000 && mapid <= 809040100);
    }

    public static boolean inBossMap(int mapid) {
        if (mapid / 10000 == 92502) {// 武陵道場
            return true;
        }
        if (mapid == 220040000) {// 时间之路
            return true;
        }

        switch (mapid) {
            case 105100300: // 巴洛古
            case 220080001: // 鐘王
            case 230040420: // 海怒斯
            case 240060000: // 龍王前置
            case 240060100: // 龍王前置
            case 240060200: // 龍王
            case 280030000: // 炎魔
            case 551030200: // 夢幻主題公園
            case 740000000: // PQ
            case 741020102: // 黑輪王
            case 749050301: // 洽吉
            case 802000211: // 日本台場BOSS
            case 922010900: // 時空的裂縫
            case 925020200: // 武陵
            case 930000600: // 劇毒森林
            case 270050100: //皮卡丘
            case 702060000://妖僧
                return true;
        }
        return false;
    }

    public static int isMonsterSpawn(MapleMap map) { //回傳生怪數量倍率
        if (MapConstants.isBossMap(map.getId()) || MapConstants.isEventMap(map.getId())) {
            return 1;
        }

        for (MapleMapObject obj : map.getAllMonstersThreadsafe()) { //判斷地图有boss 回傳倍率1
            final MapleMonster mob = (MapleMonster) obj;
            if (mob.getStats().isBoss()) {
                return 1;
            }
        }

        switch (map.getId()) {
            case 220060000:
            case 220060100:
            case 220060200:
            case 220060300:
            case 220070000:// 遺忘的时间之路<1> 6230400 6230500
            case 220070100:// 遺忘的时间之路<2> 8140200 8140300
            case 220070200:// 遺忘的时间之路<3> 7130010
            case 220070300:// 遺忘的时间之路<4> 8142000
            case 270010100:// 回憶之路1 2600701 2600700
            case 270010200:// 回憶之路2 2600702 2600700
            case 270010300:// 回憶之路3 2600703 2600700
            case 270010400:// 回憶之路4 2600704 2600703 2600700
            case 270010500:// 回憶之路5 2600704 2600700
            case 270020100:// 悔恨之路1 2600706 2600700
            case 270020200:// 悔恨之路2 2600707 2600700
            case 270020300:// 悔恨之路3 2600708 2600700
            case 270020400:// 悔恨之路4 2600709 2600708 2600700
            case 270020500:// 悔恨之路5 2600709 2600700
            case 270030100:// 忘卻之路1 2600711 2600700
            case 270030200:// 忘卻之路2 2600712 2600700
            case 270030300:// 忘卻之路3 2600713 2600700
            case 270030400:// 忘卻之路4 2600714 2600713 2600700
            case 270030500:// 忘卻之路5 2600714	
            case 220060201://  彎曲的时间
            case 220060301://  糾結的时间
            case 220070201://  毀壞的时间
            case 220070301://  禁忌的时间  
                return 2;
        }
        return 1;
    }

    public static boolean isBossMap(int mapid) {
        if (mapid / 10000 == 92502) {// 武陵道場
            return true;
        }
        switch (mapid) {
            case 105100300: // 巴洛古
            case 220080001: // 鐘王
            case 230040420: // 海怒斯
            case 240060000: // 龍王前置
            case 240060100: // 龍王前置
            case 240060200: // 龍王
            case 270050100: // 皮卡啾
            case 280030000: // 炎魔
            case 551030200: // 夢幻主題公園
            case 740000000: // PQ
            case 741020101: // 黑輪王
            case 741020102: // 黑輪王
            case 749050301: // 洽吉
            case 802000211: // 日本台場BOSS
            case 802000611: // 日本台場BOSS
            case 922010900: // 時空的裂縫
            case 925020200: // 武陵
            case 930000600: // 劇毒森林
            case 270030500: // 忘卻    
            case 270010500: // 忘卻
            case 270020500: // 忘卻
            case 749040001://年獸
                return true;
        }
        return false;
    }

}
