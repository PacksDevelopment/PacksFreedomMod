package me.StevenLawson.TotalFreedomMod;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.UF_DEVELOPERS;
import static me.StevenLawson.TotalFreedomMod.TotalFreedomMod.server;
import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class FOPM_TFM_Util {
// Uses extremely old FOPM changes to the TFM

    public static final List<ChatColor> COLOURS = Arrays.asList(
            ChatColor.DARK_BLUE,
            ChatColor.DARK_GREEN,
            ChatColor.DARK_AQUA,
            ChatColor.DARK_RED,
            ChatColor.DARK_PURPLE,
            ChatColor.GOLD,
            ChatColor.BLUE,
            ChatColor.GREEN,
            ChatColor.AQUA,
            ChatColor.RED,
            ChatColor.LIGHT_PURPLE,
            ChatColor.YELLOW
    );

    public static final List<Achievement> ACHIEVEMENTS = Arrays.asList(
            Achievement.ACQUIRE_IRON,
            Achievement.BAKE_CAKE,
            Achievement.BOOKCASE,
            Achievement.BREED_COW,
            Achievement.BREW_POTION,
            Achievement.BUILD_BETTER_PICKAXE,
            Achievement.BUILD_FURNACE,
            Achievement.BUILD_HOE,
            Achievement.BUILD_PICKAXE,
            Achievement.BUILD_SWORD,
            Achievement.BUILD_WORKBENCH,
            Achievement.COOK_FISH,
            Achievement.DIAMONDS_TO_YOU,
            Achievement.ENCHANTMENTS,
            Achievement.END_PORTAL,
            Achievement.EXPLORE_ALL_BIOMES,
            Achievement.FLY_PIG,
            Achievement.FULL_BEACON,
            Achievement.GET_BLAZE_ROD,
            Achievement.GET_DIAMONDS,
            Achievement.GHAST_RETURN,
            Achievement.KILL_COW,
            Achievement.KILL_ENEMY,
            Achievement.KILL_WITHER,
            Achievement.MAKE_BREAD,
            Achievement.MINE_WOOD,
            Achievement.NETHER_PORTAL,
            Achievement.ON_A_RAIL,
            Achievement.OPEN_INVENTORY,
            Achievement.OVERKILL,
            Achievement.OVERPOWERED,
            Achievement.SNIPE_SKELETON,
            Achievement.SPAWN_WITHER,
            Achievement.THE_END
    );

    public static Random random = new Random();

    public static boolean inGod(Player player) {
        return TFM_PlayerData.getPlayerData(player).inGod();
    }

    public static void setGod(Player player, boolean enabled) {
        TFM_PlayerData.getPlayerData(player).setGod(enabled);
    }

    public static boolean isHighRank(Player player) {
        String name = player.getName();
        if (name.equals("tylerhyperHD") || UF_DEVELOPERS.contains(name) || TFM_ConfigEntry.SERVER_OWNERS.getList().contains(name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isHighRank(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }
        return isHighRank((Player) sender);
    }

    public static void DevChatMessage(CommandSender sender, String message, boolean senderIsConsole) {
        String name = sender.getName() + " " + TFM_PlayerRank.fromSender(sender).getPrefix() + ChatColor.WHITE;
        TFM_Log.info("[Dev Chat] " + name + ": " + message);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equals("tylerhyperHD") || UF_DEVELOPERS.contains(player.getName()) || TFM_ConfigEntry.SERVER_OWNERS.getList().contains(player.getName())) {
                player.sendMessage(ChatColor.AQUA + "[" + ChatColor.DARK_PURPLE + "Dev Chat" + ChatColor.AQUA + "] " + ChatColor.DARK_RED + name + ": " + ChatColor.RED + message);
            }
        }
    }

    public static void TelnetAdminChatMessage(CommandSender sender, String message, boolean senderIsConsole) {
        String name = sender.getName() + " " + TFM_PlayerRank.fromSender(sender).getPrefix() + ChatColor.WHITE;
        TFM_Log.info("[Telnet Admin Chat] " + name + ": " + message);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (TFM_AdminList.isTelnetAdmin(player)) {
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_GREEN + "Telnet Chat" + ChatColor.GRAY + "] " + ChatColor.DARK_RED + name + ": " + ChatColor.RED + message);
            }
        }
    }

    public static void SeniorAdminChatMessage(CommandSender sender, String message, boolean senderIsConsole) {
        String name = sender.getName() + " " + TFM_PlayerRank.fromSender(sender).getPrefix() + ChatColor.WHITE;
        TFM_Log.info("[Senior-Admin] " + name + ": " + message);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (TFM_AdminList.isSeniorAdmin(player)) {
                player.sendMessage(ChatColor.AQUA + "[" + ChatColor.RED + "SrA Chat" + ChatColor.AQUA + "] " + ChatColor.DARK_RED + name + ": " + ChatColor.RED + message);
            }
        }
    }

    public static void asciiDog() {
        //This was VERY annoying to make!
        TFM_Util.bcastMsg("                     ,", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("                ,.  | \\ ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("               |: \\ ; :\\ ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("               :' ;\\| ::\\", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("                \\ : | `::\\ ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("                _)  |   `:`. ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("              ,' , `.    ;: ; ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("            ,' ;:  ;\"'  ,:: |", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("           /,   ` .    ;::: |:`-.__ ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("        _,' _o\\  ,::.`:' ;  ;   . ' ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("    _,-'           `:.          ;\"\"", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg(" ,-'                     ,:         `-;, ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg(" \\,                       ;:           ;--._ ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("  `.______,-,----._     ,' ;:        ,/ ,  ,` ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("         / /,-';'  \\     ; `:      ,'/,::.::: ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("       ,',;-'-'_,--;    ;   :.   ,',',;:::::: ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("      ( /___,-'     `.     ;::,,'o/  ,::::::: ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("       `'             )    ;:,'o /  ;\"-   -:: ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("                      \\__ _,'o ,'         ,:: ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("                         ) `--'       ,..:::: ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("                         ; `.        ,::::::: ", TFM_Util.randomChatColor());
        TFM_Util.bcastMsg("                          ;  ``::.    ::::::: ", TFM_Util.randomChatColor());
    }

    public static void asciiHorse() {
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + ",  ,.~\"\"\"\"\"~~..");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + " )\\,)\\`-,       `~._                                     .--._");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + " \\  \\ | )           `~._                   .-\"\"\"\"\"-._   /     `.");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "/ ('  ( _(\\            `~~,__________..-\"'          `-<        \\");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + " )   )   `   )/)   )        \\                            \\,-.     |");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "') /)`      \\` \\,-')/\\      (                             \\ /     |");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "(_(\\ /7      |.   /'  )'  _(`                              Y      |");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "   \\       (  `.     ')_/`                                |      /");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "     \\       \\   \\                                         |)    (");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "      \\ _  /\\/   /                                         (      `~.");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "       `-._)     |                                        / \\        `,");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                |                          |           .'   )      (`");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                \\                        _,\\          /     \\_    (`");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                  `.,      /       __..'7\"  \\         |       )  (");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                  .'     _/`-..--\"\"      `.   `.        \\      `._/");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                .'    _.j     /            `-.  `.       \\");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "              .'   _.'   \\    |               `.  `.      \\");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "             |   .'       ;   ;               .'  .'`.     \\");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "             \\_  `.       |   \\             .'  .'   /    .'");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "               `.  `-, __ \\   /           .'  .'     |   (");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                 `.  `'` \\|  |           /  .-`     /   .'");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                   `-._.--t  ;          |_.-)      /  .'");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                          ; /           \\  /      / .'");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                         / /             `'     .' /");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                        /,_\\                  .',_(");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                       /___(                 /___(");
    }

    public static void asciiUnicorn() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), Sound.FIREWORK_TWINKLE, 1.0F, 1.0F);
        }
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                                                         ,/");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                                                        //");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                                                      ,//");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                                          ___   /|   |//");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                                      `__/\\_ --(/|___/-/");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                                   \\|\\_-\\___ __-_`- /-/ \\.");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                                  |\\_-___,-\\_____--/_)' ) \\");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                                   \\ -_ /     __ \\( `( __`\\|");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                                   `\\__|      |\\)\\ ) /(/|");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "           ,._____.,            ',--//-|      \\  |  '   /");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "          /     __. \\,          / /,---|       \\       /");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "        |  | ( (  \\   |      ,/\\'__/'/          |     |");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "        |  \\  \\`--, `_/_------______/           \\(   )/");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "        | | \\  \\_. \\,                            \\___/\\");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "        | |  \\_   \\  \\                                 \\");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "        \\ \\    \\_ \\   \\   /                             \\");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "         \\ \\  \\._  \\__ \\_|       |                       \\");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "          \\ \\___  \\      \\       |                        \\");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "           \\__ \\__ \\  \\_ |       \\                         |");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "           |  \\_____ \\  ____      |                           |");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "           | \\  \\__ ---' .__\\     |        |                 |");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "           \\  \\__ ---   /   )     |        \\                /");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "            \\   \\____/ / ()(      \\          `---_         /|");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "             \\__________/(,--__    \\_________.    |       ./ |");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "               |     \\ \\  `---_\\--,           \\   \\_,./   |");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "               |      \\  \\_ ` \\    /`---_______-\\   \\\\    /");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                \\      \\.___,`|   /              \\   \\\\   \\");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                 \\     |  \\_ \\|   \\              (   |:    |");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                  \\    \\      \\    |             /  / |    ;");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                   \\    \\      \\    \\          ( `_'   \\  |");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                    \\.   \\      \\.   \\          `__/   |  |");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                      \\   \\       \\.  \\                |  |");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                       \\   \\        \\  \\               (  )");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                        \\   |        \\  |                |  |");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                         |  \\         \\ \\               I  `");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                         ( __;        ( _;                ('-_';");
        TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                         |___\\       \\___:              \\___:");
    }

    public static void spawnMob(Player player, EntityType entity, int amount) {
        int i = 0;
        do {
            player.getWorld().spawnEntity(player.getLocation(), entity);
            i++;
        } while (i <= amount);
    }

    public static String getPlayerFromIp(String ip) {
        for (TFM_Player player : TFM_PlayerList.getAllPlayers()) {
            if (player.getIps().contains(ip)) {
                return " " + player.getLastLoginName();
            }
        }
        return "";
    }

    public static boolean isDoubleJumper(Player player) {
        return TFM_PlayerData.getPlayerData(player).isDoubleJumper();
    }

    public static int broadcastMessage(String message) {
        return server.broadcastMessage(message);
    }

    public static void setDoubleJumper(Player player, boolean state) {
        TFM_PlayerData.getPlayerData(player).setDoubleJumper(state);
    }

    public static ChatColor randomChatColour() {
        return COLOURS.get(random.nextInt(COLOURS.size()));
    }

    public static Achievement randomAchievement() {
        return ACHIEVEMENTS.get(random.nextInt(ACHIEVEMENTS.size()));
    }

    public static ChatColor randomChatColor() {
        return randomChatColour();
    }
}
