/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package configs;

import commons.configuration.Property;

/**
 *
 * @author zisedk
 */
public class ServerConfig {

    /**
     * 当前游戏的版本号
     * 默认: 118
     */
    @Property(key = "server.maple.version", defaultValue = "123")
    public static short MAPLE_VERSION;
    /**
     * 当前游戏补丁的版本号
     * 默认: 1
     */
    @Property(key = "server.maple.patch", defaultValue = "1")
    public static String MAPLE_PATCH;
    /**
     * 服务器标识
     * 默认: 4
     * 解释: 4 = CHS, 7 = MSEA, 8 = GlobalMS, 5 = Test Server
     */
    @Property(key = "server.maple.type", defaultValue = "5")
    public static byte MAPLE_TYPE;
    /**
     * 新手默认地图
     * 默认: 50000
     */
    @Property(key = "world.beginner.map", defaultValue = "50000")
    public static int BEGINNER_SPAWN_MAP;
    /**
     * 是否启用自动封号系统
     * 默认: true
     */
    @Property(key = "world.autoban.enable", defaultValue = "false")
    public static boolean AUTO_BAN_ENABLE;
    /**
     * 是否开启自由市场聊天聊天信息用小黑板显示
     * 默认: false
     */
    @Property(key = "server.market.chalk.board", defaultValue = "true")
    public static boolean chalkboard;
    /**
     * 是否开启给角色负面BUFF
     * 默认: false
     */
    @Property(key = "server.apply.debuff", defaultValue = "false")
    public static boolean applyDebuff;
    /**
     * 是否开启给怪物BUFF
     * 默认: false
     */
    @Property(key = "server.apply.monster.status", defaultValue = "false")
    public static boolean applyMonsterStatus;
}
