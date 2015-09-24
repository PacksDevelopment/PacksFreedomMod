package me.StevenLawson.TotalFreedomMod;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class UFM_Util {

    public static void telnetFix(String message, ChatColor color) {
        TFM_Log.info(message, true);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (TFM_AdminList.isTelnetAdmin(player)) {
                player.sendMessage((color == null ? "" : color) + message);
            }
        }
    }

    public static void telnetFix(String message) {
        UFM_Util.telnetFix(message, null);
    }

    public static void telnetMessage(String adminName, String action, boolean isRed) {
        UFM_Util.telnetFix(adminName + " - " + action, (isRed ? ChatColor.RED : ChatColor.AQUA));
    }
}
