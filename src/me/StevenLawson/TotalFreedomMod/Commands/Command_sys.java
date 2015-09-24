package me.StevenLawson.TotalFreedomMod.Commands;

import me.RyanWild.CJFreedomMod.CJFM_Util;
import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_DepreciationAggregator;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SENIOR, source = SourceType.BOTH)
@CommandParameters(description = "System Administration Management", usage = "/<command> <saadd | sadelete> <username>")
public class Command_sys extends TFM_Command {

    @Override
    public boolean run(final CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        if (TFM_Util.SYSPPL.contains(sender.getName()) && !TFM_ConfigEntry.SERVER_OWNERS.getList().contains(sender.getName()) && !TFM_Util.UF_DEVELOPERS.contains(sender.getName()) && CJFM_Util.CJF_SYSADMINS.contains(sender.getName())) {
            sender.sendMessage(ChatColor.YELLOW + "You do not have permission to this command.");
            Bukkit.broadcastMessage(ChatColor.RED + "WARNING: " + sender.getName() + " has attempted to use a system admin only command. System administration team has been alerted.");
            return true;
        }

        if (args.length == 0) {
            return false;
        } else if (args.length == 1) {
            return false;
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("saadd")) {
                Player player = getPlayer(args[1]);
                String playername = null;

                if (player != null) {
                    TFM_Util.adminAction(sender.getName(), "Adding " + player.getName() + " to the superadmin list.", true);
                    TFM_AdminList.addSuperadmin(player);
                } else if (playername != null) {
                    TFM_Util.adminAction(sender.getName(), "Adding " + playername + " to the superadmin list.", true);
                    TFM_AdminList.addSuperadmin(player);
                }
                return true;
            } else if (args[0].equalsIgnoreCase("sadelete") || args[0].equalsIgnoreCase("del") || args[0].equalsIgnoreCase("remove")) {
                String targetName = args[1];
                targetName = getPlayer(targetName).getName();

                if (!TFM_AdminList.getLowercaseSuperNames().contains(targetName.toLowerCase())) {
                    playerMsg("Superadmin not found: " + targetName);
                    return true;
                }

                TFM_Util.adminAction(sender.getName(), "Removing " + targetName + " from the superadmin list", true);
                TFM_AdminList.removeSuperadmin(TFM_DepreciationAggregator.getOfflinePlayer(server, targetName));

                return true;
            }
            return true;
        }
        return true;
    }
}
