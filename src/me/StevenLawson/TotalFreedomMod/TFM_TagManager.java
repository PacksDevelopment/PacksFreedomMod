package me.StevenLawson.TotalFreedomMod;

import me.RyanWild.CJFreedomMod.CJFM_Util;
import me.RyanWild.CJFreedomMod.Player.CJFM_DonatorList;
import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class TFM_TagManager {

    public static void onPlayerManager(Player player) {
        if (player.getName().equals("tylerhyperHD")) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&5Lead Developer&8]");
            return;
        } else if (TFM_Util.ADMINMGRS.contains(player.getName())) {
            player.setPlayerListName(ChatColor.DARK_AQUA + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&3Admin Manager&8]");
            return;
        } else if (TFM_Util.DEVELOPERS.contains(player.getName())) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&5TFM Developer&8]");
            return;
        } else if (TFM_Util.UF_DEVELOPERS.contains(player.getName())) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&5Developer&8]");
            return;
        } else if (TFM_AdminList.isSuperAdmin(player)) {
            if (TFM_ConfigEntry.SERVER_OWNERS.getList().contains(player.getName())) {
                player.setPlayerListName(ChatColor.BLUE + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&9Owner&8]");
                return;
            } else if (TFM_ConfigEntry.SERVER_COOWNERS.getList().contains(player.getName())) {
                player.setPlayerListName(ChatColor.BLUE + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&9Co-Owner&8]");
                return;
            } else if (CJFM_Util.FAMOUS.contains(player.getName().toLowerCase())) {
                player.setPlayerListName("[Fake]" + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&7Fake&8]");
                TFM_Util.bcastMsg(":WARNING: " + player.getName() + " is completely and utterly FAKE! - This server is in Offline Mode so anybody can join as anyone!", ChatColor.RED);
                return;
            } else if (CJFM_Util.SYSADMINS.contains(player.getName())) {
                player.setPlayerListName(ChatColor.DARK_RED + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&4System-Admin&8]");
                return;
            } else if (CJFM_Util.EXECUTIVES.contains(player.getName())) {
                player.setPlayerListName(ChatColor.BLUE + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&9Executive&8]");
                return;
            } else if (CJFM_DonatorList.isSeniorDonor(player)) {
                player.setPlayerListName(ChatColor.GOLD + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&6[Senior Donator]");
                return;
            } else if (CJFM_DonatorList.isSuperDonor(player)) {
                player.setPlayerListName(ChatColor.YELLOW + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&e[Super Donator]");
                return;
            } else if (TFM_AdminList.isSeniorAdmin(player)) {
                player.setPlayerListName(ChatColor.LIGHT_PURPLE + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&dSenior Admin&8]");
                return;
            } else if (TFM_AdminList.isTelnetAdmin(player, true)) {
                player.setPlayerListName(ChatColor.DARK_GREEN + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&2Telnet Admin&8]");
                return;
            } else {
                player.setPlayerListName(ChatColor.AQUA + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&BSuper Admin&8]");
                return;
            }
        }
    }

    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.getName().equals("tylerhyperHD")) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&5Lead Developer&8]");
            afterNameSet(player);
            return;
        } else if (TFM_Util.ADMINMGRS.contains(player.getName())) {
            player.setPlayerListName(ChatColor.DARK_AQUA + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&3Admin Manager&8]");
            afterNameSet(player);
            return;
        } else if (TFM_Util.DEVELOPERS.contains(player.getName())) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&5TFM Developer&8]");
            afterNameSet(player);
            return;
        } else if (TFM_Util.UF_DEVELOPERS.contains(player.getName())) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&5Developer&8]");
            afterNameSet(player);
            return;
        } else if (TFM_AdminList.isSuperAdmin(player)) {
            if (TFM_ConfigEntry.SERVER_OWNERS.getList().contains(player.getName())) {
                player.setPlayerListName(ChatColor.BLUE + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&9Owner&8]");
                afterNameSet(player);
                return;
            } else if (TFM_ConfigEntry.SERVER_COOWNERS.getList().contains(player.getName())) {
                player.setPlayerListName(ChatColor.BLUE + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&9Co-Owner&8]");
                afterNameSet(player);
                return;
            } else if (CJFM_Util.FAMOUS.contains(player.getName().toLowerCase())) {
                player.setPlayerListName("[Fake]" + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&7Fake&8]");
                TFM_Util.bcastMsg(":WARNING: " + player.getName() + " is completely and utterly FAKE! - This server is in Offline Mode so anybody can join as anyone!", ChatColor.RED);
                afterNameSet(player);
                return;
            } else if (CJFM_Util.SYSADMINS.contains(player.getName())) {
                player.setPlayerListName(ChatColor.DARK_RED + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&4System-Admin&8]");
                afterNameSet(player);
                return;
            } else if (CJFM_Util.EXECUTIVES.contains(player.getName())) {
                player.setPlayerListName(ChatColor.BLUE + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&9Executive&8]");
                afterNameSet(player);
                return;
            } else if (CJFM_DonatorList.isSeniorDonor(player)) {
                player.setPlayerListName(ChatColor.GOLD + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&6[Senior Donator]");
                afterNameSet(player);
                return;
            } else if (CJFM_DonatorList.isSuperDonor(player)) {
                player.setPlayerListName(ChatColor.YELLOW + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&e[Super Donator]");
                afterNameSet(player);
                return;
            } else if (TFM_AdminList.isSeniorAdmin(player)) {
                player.setPlayerListName(ChatColor.LIGHT_PURPLE + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&dSenior Admin&8]");
                afterNameSet(player);
                return;
            } else if (TFM_AdminList.isTelnetAdmin(player, true)) {
                player.setPlayerListName(ChatColor.DARK_GREEN + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&2Telnet Admin&8]");
                afterNameSet(player);
                return;
            } else {
                player.setPlayerListName(ChatColor.AQUA + player.getName());
                TFM_PlayerData.getPlayerData(player).setTag("&8[&BSuper Admin&8]");
                afterNameSet(player);
                return;
            }
        }
    }

    public static void afterNameSet(final Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (TFM_ConfigEntry.ADMIN_ONLY_MODE.getBoolean()) {
                    player.sendMessage(ChatColor.RED + "UnlimitedFreedom is currently closed to non-superadmins.");
                }

                if (TFM_ConfigEntry.TRAINING_SESSION.getBoolean()) {
                    player.sendMessage(ChatColor.RED + "UnlimitedFreedom is currently in a training session.");
                }

                if (TotalFreedomMod.lockdownEnabled) {
                    TFM_Util.playerMsg(player, "Warning: UnlimitedFreedom is currenty in lockdown-mode, new players will not be able to join!", ChatColor.RED);
                }
            }
        }.runTaskLater(TotalFreedomMod.plugin, 20L * 1L);
    }
}
