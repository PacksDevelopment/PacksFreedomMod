package me.RyanWild.CJFreedomMod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import me.RyanWild.CJFreedomMod.Player.CJFM_DonatorList;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Log;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerRank;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import static me.StevenLawson.TotalFreedomMod.TotalFreedomMod.mySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CJFM_Util {
    public static TotalFreedomMod plugin;
    public static final List<String> CJF_EXECUTIVES = Arrays.asList("Camzie99", "Kyled1986");
    public static final List<String> EXECUTIVES = Arrays.asList("");
    public static final List<String> CJF_SYSADMINS = Arrays.asList("wild1145", "Varuct", "thecjgcjg", "darthsalamon");
    public static final List<String> SYSADMINS = Arrays.asList("");
    public static final List<String> CJF_DEVELOPERS = Arrays.asList("Madgeek1450", "DarthSalamon", "wild1145", "Paldiu", "Wahoozel", "Camzie99", "hawkeyeshi");
    public static final List<String> DEVELOPERS = Arrays.asList("tylerhyperHD");
    public static final List<String> FAMOUS = Arrays.asList("skythekidrs", "antvenom", "deadlox", "stampylongnose", "sethbling", "asfjerome", "dantdm", "pokemondanlv45", "zexyzek", "ssundee",
            "explodingtnt", "kurtjmac", "xephos", "honeydew", "captainsparklez", "truemu", "jeb_", "grumm", "notch", "chimneyswift", "vechs",
            "cavemanfilms", "tobyturner", "inthelittlewood", "sips_", "sjin", "lividcofee", "etho");

    public static void updateDatabase(String SQLquery) throws SQLException {
        Connection c = mySQL.openConnection();
        Statement statement = c.createStatement();
        statement.executeUpdate(SQLquery);
    }

    public void getValueFromDB(String SQLquery) throws SQLException {
        Connection c = mySQL.openConnection();
        Statement statement = c.createStatement();
        ResultSet res = statement.executeQuery(SQLquery);
        res.next();
    }

    private final Server server;

    public CJFM_Util(TotalFreedomMod plugin) {
        TotalFreedomMod.plugin = plugin;
        this.server = plugin.getServer();
    }

    @SuppressWarnings("Convert2Lambda")
    public void sendSyncMessage(final CommandSender sendTo, final String message) {
        Bukkit.getScheduler().runTask(plugin, new Runnable() {
            @Override
            public void run() {
                sendTo.sendMessage(message);
            }
        });
    }

    public static void SeniorAdminChatMessage(CommandSender sender, String message, boolean senderIsConsole) {
        String name = sender.getName() + " " + TFM_PlayerRank.fromSender(sender).getPrefix() + ChatColor.WHITE;
        TFM_Log.info("[Senior-Admin] " + name + ": " + message);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (TFM_AdminList.isSeniorAdmin(player)) {
                player.sendMessage("[" + ChatColor.YELLOW + "Senior-Admin" + ChatColor.WHITE + "] " + ChatColor.DARK_RED + name + ": " + ChatColor.YELLOW + message);
            }
        }
    }

    public static void donatorChatMessage(CommandSender sender, String message, boolean senderisConsole) {
        String name = sender.getName() + " " + TFM_PlayerRank.fromSender(sender).getPrefix() + ChatColor.WHITE;
        TFM_Log.info("[DonatorChat]" + name + ":" + message);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (CJFM_DonatorList.isSuperDonor(player)
                    || TFM_AdminList.isSeniorAdmin(player)) {
                player.sendMessage("[" + ChatColor.LIGHT_PURPLE + "Donator Chat" + ChatColor.WHITE + "] " + ChatColor.DARK_RED + name + ": " + ChatColor.LIGHT_PURPLE + message);
            }
        }
    }

    public void msg(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.YELLOW + message);

    }

    public void adminAction(String admin, String action, ChatColor color) {
        Bukkit.broadcastMessage(color + admin + " - " + action);
    }

    public void adminAction(CommandSender sender, String action) {
        adminAction(sender.getName(), action, ChatColor.RED);
    }

}
