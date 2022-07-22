package client.messages;

import java.util.ArrayList;
import client.MapleCharacter;
import client.MapleClient;
import client.messages.commands.*;
import client.messages.commands.巡查管理;
import constants.ServerConstants.PlayerGMRank;
import constants.ServerConstants.CommandType;
import constants.ServerConstants;
import constants.ServerConstants.PlayerGMRank.游戏管理;
import constants.ServerConstants.PlayerGMRank.玩家指令;
import database.DatabaseConnection;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import tools.FileoutputUtil;
import static tools.FileoutputUtil.CurrentReadable_Time;
import tools.MaplePacketCreator;

public class CommandProcessor {

    private final static HashMap<String, CommandObject> commands = new HashMap<>();
    private final static HashMap<Integer, ArrayList<String>> commandList = new HashMap<>();

    private static void sendDisplayMessage(MapleClient c, String msg, CommandType type) {
        if (c.getPlayer() == null) {
            return;
        }
        switch (type) {
           case 玩家指令:
                c.getPlayer().dropMessage(6, msg);
                break;
            case TRADE:
                c.getPlayer().dropMessage(-2, "错误 : " + msg);
                break;
        }

    }

    public static boolean processCommand(MapleClient c, String line, CommandType type) {
        if (line.charAt(0) == ServerConstants.PlayerGMRank.玩家指令.getCommandPrefix()) {
            String[] splitted = line.split(" ");
            splitted[0] = splitted[0].toLowerCase();

            CommandObject co = commands.get(splitted[0]);

            if (co == null || co.getType() != type) {
                c.getPlayer().dropMessage(1, "输入的指令不存在,\r\n\r\n请输入[ @帮助 ]或者打开指令列表");
                return true;
            }
            try {
                boolean ret = co.execute(c, splitted); //Don't really care about the return value. ;D
            } catch (Exception e) {
                sendDisplayMessage(c, "有错误.", type);
                if (c.getPlayer().isGM()) {
                    sendDisplayMessage(c, "错误: " + e, type);
                }
            }
            return true;
        }
        int 游戏指令开关;
        游戏指令开关 = gui./*Start*/.ConfigValuesMap.get("游戏指令开关");
        if (游戏指令开关 <= 0) {
            if (c.getPlayer().getGMLevel() > ServerConstants.PlayerGMRank.玩家指令.getLevel()) {
                // if (line.charAt(0) == ServerConstants.PlayerGMRank.GM.getCommandPrefix() || line.chaBrAt(0) == ServerConstants.PlayerGMRank.ADMIN.getCommandPrefix() || line.charAt(0) == ServerConstants.PlayerGMRank.INTERN.getCommandPrefix()) { //Redundant for now, but in case we change symbols later. This will become extensible.
                if ((line.charAt(0) == ServerConstants.PlayerGMRank.巡查管理.getCommandPrefix() || line.charAt(0) == ServerConstants.PlayerGMRank.游戏管理.getCommandPrefix())
                        || line.charAt(0) == ServerConstants.PlayerGMRank.巡查管理.getCommandPrefix()) {
                    //Redundant for now, but in case we change symbols later. This will become extensible.
                    
                    String[] splitted = line.split(" ");
                    splitted[0] = splitted[0].toLowerCase();

                    if (line.charAt(0) == '*') { //GM使用的符号
                        CommandObject co = commands.get(splitted[0]);
//                        if (splitted[0].equals("*指令大全")) {
//                            dropHelp(c, 0);
//                            return true;
//                        } else
if (co == null || co.getType() != type) {
    sendDisplayMessage(c, "指令不存在或者指令格式不正确，输入[*指令大全]进行查看。", type);
    return true;
}
if (c.getPlayer().getGMLevel() >= co.getReqGMLevel()) {
                            boolean ret;
                            ret = co.execute(c, splitted);
    if (ret > 0 && c.getPlayer() != null) { //incase d/c after command or something
        logGMCommandToDB(c.getPlayer(), line);
        System.out.println("[有指令]" + CurrentReadable_Time() + " : [ " + c.getPlayer().getName() + " ] 使用了 : " + line);
    }
} else {
    sendDisplayMessage(c, "你的权限不足够使用.", type);
}
return true;
                    }
                } else {                }
            }
        } else {
            sendDisplayMessage(c, "后台已经关闭游戏指令.", type);
        }
        return false;
    }

    private static void logGMCommandToDB(MapleCharacter player, String command) {

        PreparedStatement ps = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO 管理员指令记录 (cid, name, command, mapid, ip) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, player.getId());
            ps.setString(2, player.getName());
            ps.setString(3, command);
            ps.setInt(4, player.getMap().getId());
            ps.setString(5, player.getClient().getSessionIPAddress());
            ps.executeUpdate();
        } catch (SQLException ex) {
            if (abc.Game.封包异常 == 1) {
                FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, ex);
            }
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {/*
                 * Err.. Fuck?
                 */

            }
        }
    }

    static {

        Class[] CommandFiles = {
            //PlayerCommand.class, GMCommand.class, InternCommand.class, AdminCommand.class
            玩家指令.class,
            巡查管理.class,
            活动管理.class,
            游戏管理.class
        };

        for (Class clasz : CommandFiles) {
            try {
                PlayerGMRank rankNeeded = (PlayerGMRank) clasz.getMethod("getPlayerLevelRequired", new Class[]{}).invoke(null, (Object[]) null);
                Class[] a = clasz.getDeclaredClasses();
                ArrayList<String> cL = new ArrayList<String>();
                for (Class c : a) {
                    try {
                        if (!Modifier.isAbstract(c.getModifiers()) && !c.isSynthetic()) {
                            Object o = c.newInstance();
                            boolean enabled;
                            try {
                                enabled = c.getDeclaredField("enabled").getBoolean(c.getDeclaredField("enabled"));
                            } catch (NoSuchFieldException ex) {
                                enabled = true; //Enable all coded commands by default.
                            }
                            if (o instanceof CommandExecute && enabled) {
                                cL.add(rankNeeded.getCommandPrefix() + c.getSimpleName().toLowerCase());
                                commands.put(rankNeeded.getCommandPrefix() + c.getSimpleName().toLowerCase(), new CommandObject(rankNeeded.getCommandPrefix() + c.getSimpleName().toLowerCase(), (CommandExecute) o, rankNeeded.getLevel()));
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        FileoutputUtil.outputFileError(FileoutputUtil.ScriptEx_Log, ex);
                    }
                }
                Collections.sort(cL);
                commandList.put(rankNeeded.getLevel(), cL);
            } catch (Exception ex) {
                ex.printStackTrace();
                FileoutputUtil.outputFileError(FileoutputUtil.ScriptEx_Log, ex);
            }
        }
    }

    public static void dropHelp(MapleClient c, int type) {
        final StringBuilder sb = new StringBuilder("指令列表:\r\n ");
        int check = 0;
        if (type == 0) {
            check = c.getPlayer().getGMLevel();
//        } else if (type == 1) {
//            commandList = VipCommandList;
//            check = c.getPlayer().getVip();
        }
        for (int i = 0; i <= check; i++) {
            if (commandList.containsKey(i)) {
                sb.append(type == 1 ? "VIP" : "").append("权限等級： ").append(i).append("\r\n");
                for (String s : commandList.get(i)) {
                    sb.append(s);
                    sb.append(" \r\n");
                }
            }
        }
        c.sendPacket(MaplePacketCreator.getNPCTalk(9010000, (byte) 0, sb.toString(), "00 00", (byte) 0));
    }
}
