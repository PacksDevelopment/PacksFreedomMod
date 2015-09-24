package me.StevenLawson.TotalFreedomMod;

import me.RyanWild.CJFreedomMod.CJFM_Util;
import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TFM_BusyTagManager {

    public static void onPlayerManager(Player player) {
        if (player.getName().equals("tylerhyperHD")) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[Off Duty Lead Developer]");
            return;
        }
        if (TFM_Util.ADMINMGRS.contains(player.getName())) {
            player.setPlayerListName(ChatColor.DARK_AQUA + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[Off Duty Admin Manager]");
            return;
        }
        if (TFM_Util.UF_DEVELOPERS.contains(player.getName())) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[Off Duty Developer]");
            return;
        } else if (TFM_AdminList.isSuperAdmin(player)) {
            if (TFM_ConfigEntry.SERVER_OWNERS.getList().contains(player.getName())) {
                player.setPlayerListName(ChatColor.BLUE + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[Off Duty Owner]");
                return;
            } else if (TFM_ConfigEntry.SERVER_COOWNERS.getList().contains(player.getName())) {
                player.setPlayerListName(ChatColor.BLUE + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[Off Duty Co-Owner]");
                return;
            } else if (CJFM_Util.SYSADMINS.contains(player.getName())) {
                player.setPlayerListName(ChatColor.DARK_RED + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[Off Duty System-Admin]");
                return;
            } else if (CJFM_Util.EXECUTIVES.contains(player.getName())) {
                player.setPlayerListName(ChatColor.BLUE + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[Off Duty Executive]");
                return;
            } else if (TFM_AdminList.isSeniorAdmin(player)) {
                player.setPlayerListName(ChatColor.LIGHT_PURPLE + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[Off Duty Senior Admin]");
                return;
            } else if (TFM_AdminList.isTelnetAdmin(player, true)) {
                player.setPlayerListName(ChatColor.DARK_GREEN + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[Off Duty Telnet Admin]");
                return;
            } else {
                player.setPlayerListName(ChatColor.AQUA + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[Off Duty Super Admin]");
                return;
            }
        }
    }
}
