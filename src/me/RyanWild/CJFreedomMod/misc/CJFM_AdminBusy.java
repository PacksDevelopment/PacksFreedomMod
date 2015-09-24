package me.RyanWild.CJFreedomMod.misc;

import me.RyanWild.CJFreedomMod.CJFM_Addon;
import me.RyanWild.CJFreedomMod.CJFM_Util;
import me.RyanWild.CJFreedomMod.misc.CJFM_PlayerManager;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_BusyTagManager;
import me.StevenLawson.TotalFreedomMod.TFM_TagManager;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import static me.StevenLawson.TotalFreedomMod.TotalFreedomMod.server;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CJFM_AdminBusy extends CJFM_Addon {

    private static final int MIN_WORD_LENGTH = 4;

    public CJFM_AdminBusy(TotalFreedomMod plugin) {
        super(plugin);
    }

    public void toggleBusyStatus(Player player) {
        final CJFM_PlayerManager.PlayerInfo info = plugin.playerManager.getInfo(player);

        info.setBusy(!info.isBusy());

        plugin.util.adminAction(player, ChatColor.AQUA + "Has gone o" + (info.isBusy() ? "ff" : "n") + " Duty");

        if (CJFM_Util.SYSADMINS.contains(player.getName())) {
            player.setPlayerListName((info.isBusy() ? ChatColor.GRAY + player.getName() : ChatColor.DARK_RED + player.getName()));
        } else if (CJFM_Util.EXECUTIVES.contains(player.getName())) {
            player.setPlayerListName((info.isBusy() ? ChatColor.GRAY + player.getName() : ChatColor.BLUE + player.getName()));
        } else if (CJFM_Util.DEVELOPERS.contains(player.getName())) {
            player.setPlayerListName((info.isBusy() ? ChatColor.GRAY + player.getName() : ChatColor.DARK_PURPLE + player.getName()));
        } else if (TFM_AdminList.isSeniorAdmin(player)) {
            player.setPlayerListName((info.isBusy() ? ChatColor.GRAY + player.getName() : ChatColor.LIGHT_PURPLE + player.getName()));
        } else if (TFM_AdminList.isTelnetAdmin(player, true)) {
            player.setPlayerListName((info.isBusy() ? ChatColor.GRAY + player.getName() : ChatColor.DARK_GREEN + player.getName()));
        } else if (TFM_AdminList.isSuperAdmin(player)) {
            player.setPlayerListName((info.isBusy() ? ChatColor.GRAY + player.getName() : ChatColor.AQUA + player.getName()));
        }

        if (plugin.playerManager.getInfo(player).isBusy()) {
            TFM_BusyTagManager.onPlayerManager(player);
        } else {
            TFM_TagManager.onPlayerManager(player);
        }
    }

    public void onPlayerChat(AsyncPlayerChatEvent event) {
        final String[] words = event.getMessage().split(" ");
        for (final String word : words) {
            if (word.length() < MIN_WORD_LENGTH) {
                continue;
            }

            final Player player = server.getPlayer(word);
            if (player == null) {
                continue;
            }

            if (!TFM_AdminList.isSuperAdmin(player)) {
                return;
            }

            if (plugin.playerManager.getInfo(player).isBusy()) {
                plugin.util.sendSyncMessage(event.getPlayer(), ChatColor.RED + player.getName() + " is off duty right now, try again later or contact another admin");

            }

        }
    }

    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        final String[] words = event.getMessage().split(" ");
        for (final String word : words) {
            if (word.length() < MIN_WORD_LENGTH) {
                continue;
            }

            final Player player = server.getPlayer(word);
            if (player == null) {
                continue;
            }

            if (!TFM_AdminList.isSuperAdmin(player)) {
                return;
            }

            if (plugin.playerManager.getInfo(player).isBusy()) {
                plugin.util.sendSyncMessage(event.getPlayer(), ChatColor.RED + player.getName() + " is off duty right now, try again later or contact another admin");

            }

        }

    }
}
