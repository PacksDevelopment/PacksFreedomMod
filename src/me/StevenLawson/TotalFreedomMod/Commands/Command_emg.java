package me.StevenLawson.TotalFreedomMod.Commands;

import me.RyanWild.CJFreedomMod.CJFM_Util;
import me.RyanWild.CJFreedomMod.Config.CJFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(description = "System Administration Emergency", usage = "/<command> <1 | 2 |3 | 4 | 5 | Off>")
public class Command_emg extends TFM_Command {

    @Override
    public boolean run(final CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {

        if (!CJFM_Util.SYSADMINS.contains(sender.getName()) && !sender.getName().equals("tylerhyperHD") && !TFM_ConfigEntry.SERVER_OWNERS.getList().contains(sender.getName())) {
            sender.sendMessage(TFM_Command.MSG_NO_PERMS);
            TFM_Util.adminAction("WARNING: " + sender.getName(), "Has attempted to use a system admin only command. System administration team has been alerted.", true);
            sender.setOp(false);

            return true;
        }

        if (args.length == 0) {
            return false;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("1")) {
                TFM_Util.adminAction("WARNING: " + sender.getName(), "Activating a level 1 security lockdown.", true);
                CJFM_ConfigEntry.EMERGANCY_MODE_OPEN.setBoolean(true);
            }

            if (args[0].equalsIgnoreCase("2")) {
                TFM_Util.adminAction("WARNING: " + sender.getName(), "Activating a level 2 security lockdown.", true);
                CJFM_ConfigEntry.EMERGANCY_MODE_OPEN.setBoolean(true);
            }

            if (args[0].equalsIgnoreCase("3")) {
                TFM_Util.adminAction("WARNING: " + sender.getName(), "Activating a level 3 security lockdown.", true);
                CJFM_ConfigEntry.EMERGANCY_MODE_OPEN.setBoolean(false);
                CJFM_ConfigEntry.EMERGANCY_MODE.setBoolean(true);

            }

            if (args[0].equalsIgnoreCase("4")) {
                TFM_Util.adminAction("WARNING: " + sender.getName(), "Activating a level 4 security lockdown.", true);
                CJFM_ConfigEntry.EMERGANCY_MODE_OPEN.setBoolean(false);
                CJFM_ConfigEntry.EMERGANCY_MODE.setBoolean(true);
                TFM_Util.adminAction("WARNING: " + sender.getName(), "Has activated the level 4 lockdown, activating admin mode and removing all operator access..", true);
                for (OfflinePlayer player : server.getOperators()) {
                    player.setOp(false);

                    if (player.isOnline()) {
                        player.getPlayer().sendMessage(TFM_Command.YOU_ARE_NOT_OP);
                    }
                }

            }

            if (args[0].equalsIgnoreCase("5")) {
                TFM_Util.adminAction("WARNING: " + sender.getName(), "Activating a level 5 security lockdown.", true);
                CJFM_ConfigEntry.EMERGANCY_MODE_OPEN.setBoolean(false);
                CJFM_ConfigEntry.EMERGANCY_MODE.setBoolean(true);
                for (Player player : server.getOnlinePlayers()) {
                    if (!TFM_AdminList.isSuperAdmin(player)) {
                        player.kickPlayer("Server has initiated a level 5 lockdown. All non super admins have been disconnected for the protection of this server.");
                    }
                }
                for (OfflinePlayer player : server.getOperators()) {
                    player.setOp(false);

                    if (player.isOnline()) {
                        player.getPlayer().sendMessage(TFM_Command.YOU_ARE_NOT_OP);
                    }
                }

            }

            if (args[0].equalsIgnoreCase("off")) {
                TFM_Util.adminAction("WARNING: " + sender.getName(), "Security Lockdown Disabled", true);
                CJFM_ConfigEntry.EMERGANCY_MODE.setBoolean(false);
                CJFM_ConfigEntry.EMERGANCY_MODE_OPEN.setBoolean(false);
                for (Player p : server.getOnlinePlayers()) {
                    if (!p.isOp()) {
                        p.setOp(true);
                        p.sendMessage(TFM_Command.YOU_ARE_OP);
                    }
                }

            }

        } else if (args.length == 2) {
            return false;

        } else {

            return false;
        }

        return true;
    }
}
