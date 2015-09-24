package me.StevenLawson.TotalFreedomMod;

import me.RyanWild.CJFreedomMod.CJFM_Util;
import me.RyanWild.CJFreedomMod.Player.CJFM_DonatorList;
import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.DEVELOPERS;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.UF_DEVELOPERS;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum TFM_PlayerRank {

    DEVELOPER("a " + ChatColor.DARK_PURPLE + "TotalFreedom Developer", ChatColor.DARK_PURPLE + "[TF-Dev]"),
    UF_DEVELOPER("a " + ChatColor.DARK_PURPLE + "Developer", ChatColor.DARK_PURPLE + "[Dev]"),
    TYLER("the " + ChatColor.DARK_PURPLE + "Lead Developer" + ChatColor.AQUA + " of " + ChatColor.YELLOW + "UnlimitedFreedom", ChatColor.DARK_PURPLE + "[L-Dev]"),
    IMPOSTOR("an " + ChatColor.YELLOW + ChatColor.UNDERLINE + "Impostor", ChatColor.YELLOW.toString() + ChatColor.UNDERLINE + "[IMP]"),
    NON_OP("a " + ChatColor.GREEN + "Non-OP", ChatColor.GREEN.toString()),
    OP("an " + ChatColor.RED + "OP", ChatColor.RED + "[OP]"),
    SUPER("a " + ChatColor.GOLD + "Super Admin", ChatColor.GOLD + "[SA]"),
    TELNET("a " + ChatColor.DARK_GREEN + "Super Telnet Admin", ChatColor.DARK_GREEN + "[STA]"),
    SENIOR("a " + ChatColor.LIGHT_PURPLE + "Senior Admin", ChatColor.LIGHT_PURPLE + "[SrA]"),
    OWNER("the " + ChatColor.BLUE + "Owner" + ChatColor.AQUA + " of " + ChatColor.YELLOW + "UnlimitedFreedom", ChatColor.BLUE + "[Owner]"),
    COOWNER("a " + ChatColor.BLUE + "Co-Owner", ChatColor.BLUE + "[Co-Owner]"),
    CONSOLE("the " + ChatColor.DARK_PURPLE + "Console", ChatColor.DARK_PURPLE + "[Console]"),
    ADMINMGR("an " + ChatColor.DARK_AQUA + "Admin Manager", ChatColor.DARK_AQUA + "[Admin-Mgr]"),
    //CJFM
    EXECUTIVE("an " + ChatColor.BLUE + "Executive", ChatColor.BLUE + "[Executive]"),
    SYSADMIN("a " + ChatColor.DARK_RED + "System Admin", ChatColor.DARK_RED + "[Sys-Admin]"),
    SUPERDONATOR("a " + ChatColor.YELLOW + "Super Donator", ChatColor.YELLOW + "[Super Donator]"),
    SENIORDONATOR("a" + ChatColor.GOLD + "Senior Donator", ChatColor.GOLD + "[Senior Donator]");
    private final String loginMessage;
    private final String prefix;

    private TFM_PlayerRank(String loginMessage, String prefix) {
        this.loginMessage = loginMessage;
        this.prefix = prefix;
    }

    public static String getLoginMessage(CommandSender sender) {
        // Handle console
        if (!(sender instanceof Player)) {
            return fromSender(sender).getLoginMessage();
        }

        // Handle admins
        final TFM_Admin entry = TFM_AdminList.getEntry((Player) sender);
        if (entry == null) {
            // Player is not an admin
            return fromSender(sender).getLoginMessage();
        }

        // Custom login message
        final String loginMessage = entry.getCustomLoginMessage();

        if (loginMessage == null || loginMessage.isEmpty()) {
            return fromSender(sender).getLoginMessage();
        }

        return ChatColor.translateAlternateColorCodes('&', loginMessage);
    }

    public static TFM_PlayerRank fromSender(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return CONSOLE;
        }

        if (TFM_AdminList.isAdminImpostor((Player) sender)) {
            return IMPOSTOR;
        }
        
        if (TFM_Util.ADMINMGRS.contains(sender.getName())) {
            return ADMINMGR;
        }

        if (CJFM_Util.SYSADMINS.contains(sender.getName())) {
            return SYSADMIN;
        }
        
        if (CJFM_Util.EXECUTIVES.contains(sender.getName())) {
            return EXECUTIVE;
        }

        if (CJFM_Util.DEVELOPERS.contains(sender.getName().toLowerCase())) {
            return DEVELOPER;
        }

        if (CJFM_DonatorList.isSuperDonor(sender)) {
            return SUPERDONATOR;
        }

        if (CJFM_DonatorList.isSeniorDonor(sender)) {
            return SENIORDONATOR;
        }

        if (sender.getName().equals("tylerhyperHD")) {
            return TYLER;
        }

        if (UF_DEVELOPERS.contains(sender.getName())) {
            return UF_DEVELOPER;
        }

        if (DEVELOPERS.contains(sender.getName())) {
            return DEVELOPER;
        }

        final TFM_Admin entry = TFM_AdminList.getEntryByIp(TFM_Util.getIp((Player) sender));

        final TFM_PlayerRank rank;

        if (entry != null && entry.isActivated()) {
            if (TFM_ConfigEntry.SERVER_OWNERS.getList().contains(sender.getName())) {
                return OWNER;
            }

            if (TFM_ConfigEntry.SERVER_COOWNERS.getList().contains(sender.getName())) {
                return COOWNER;
            }

            if (entry.isSeniorAdmin()) {
                rank = SENIOR;
            } else if (entry.isTelnetAdmin()) {
                rank = TELNET;
            } else {
                rank = SUPER;
            }
        } else {
            if (sender.isOp()) {
                rank = OP;
            } else {
                rank = NON_OP;
            }

        }
        return rank;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getLoginMessage() {
        return loginMessage;
    }
}
