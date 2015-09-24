package me.StevenLawson.TotalFreedomMod.Commands;

import me.RyanWild.CJFreedomMod.CJFM_Util;
import me.RyanWild.CJFreedomMod.Config.CJFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.TFM_Ban;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(description = "Executive Command", usage = "/<command> <personal | adminworld>")
public class Command_exec extends TFM_Command {

    @Override
    public boolean run(final CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        if (args[0].equalsIgnoreCase("Personal")) {
            if (!CJFM_Util.EXECUTIVES.contains(sender.getName()) && !sender.getName().equals("tylerhyperHD")) {
                TFM_Util.playerMsg(sender, "Thou art not an executive...", ChatColor.RED);
                return true;
            }

            if (sender.getName().equals("Camzie99") && !args[1].equalsIgnoreCase("kyle")) {
                if (args.length != 2) {
                    return false;
                }
                final Player player = Bukkit.getPlayer(args[1]);

                TFM_Util.adminAction(sender.getName(), "I am REALLY annoyed with you " + player.getName() + "!!!", true);
                TFM_Util.bcastMsg(player.getName() + ", YOU SHALL FACE MY PURPLE WRATH!!! ", ChatColor.RED);

                final String ip = player.getAddress().getAddress().getHostAddress().trim();

                // remove from whitelist
                player.setWhitelisted(false);

                // deop
                player.setOp(false);

                // ban IP
                TFM_BanManager.addIpBan(new TFM_Ban(ip, player.getName()));

                // ban name
                TFM_BanManager.addUuidBan(new TFM_Ban(player.getUniqueId(), player.getName(), sender.getName(), null, "Annoying the Purple Lord."));

                // set gamemode to survival
                player.setGameMode(GameMode.SURVIVAL);

                // clear inventory
                player.closeInventory();
                player.getInventory().clear();

                // ignite player
                player.setFireTicks(10000);

                // generate explosion
                player.getWorld().createExplosion(player.getLocation(), 4F);

                // Shoot the player in the sky
                player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        // strike lightning
                        player.getWorld().strikeLightning(player.getLocation());

                        // kill (if not done already)
                        player.setHealth(0.0);
                    }
                }.runTaskLater(plugin, 2L * 20L);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        // message
                        TFM_Util.adminAction(sender.getName(), "Banning " + player.getName() + ", IP: " + ip, true);

                        // generate explosion
                        player.getWorld().createExplosion(player.getLocation(), 4F);

                        // kick player
                        player.kickPlayer(ChatColor.DARK_PURPLE + "Thou hast annoyed the Purple Lord\nThy hast been committed to death by PURPLENESS!!");
                    }
                }.runTaskLater(plugin, 3L * 20L);
                return true;
            } else {
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "           .        .                                      ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "           \\'.____.'/                                     ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "          __'-.  .-'__                         .--.        ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "          '_i:'oo':i_'---...____...----i\"\"\"-.-'.-\"\\\\ ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "            /._  _.\\       :       /   '._   ;/    ;'-._  ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "           (  o  o  )       '-.__.'       '. '.     '-.\"  ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "            '-.__.-' _.--.                 '-.:            ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "             : '-'  /     ;   _..--,  /       ;            ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "             :      '-._.-'  ;     ; :       :             ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "              :  `      .'    '-._.' :      /              ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "               \\  :    /    ____....--\\    :             ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                '._\\  :\"\"\"\"\"    '.     !.:           ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                 : |: :           'www'| \\ '|             ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                 | || |              : |  | :              ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                 | || |             .' !  | |              ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                .' !| |            /__I   | |              ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "               /__I.' !                  .' !              ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                  /__I                  /__I               ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "                                                           ");
                TFM_Util.bcastMsg(TFM_Util.randomChatColor() + "        ~~>           *THIS IS A COW*          <~~         ");
            }
            return true;
        } else if (args[0].equalsIgnoreCase("adminworld")) {
            if (!(CJFM_Util.EXECUTIVES.contains(sender.getName())) && !(CJFM_Util.SYSADMINS.contains(sender.getName()))) {
                TFM_Util.playerMsg(sender, "Thou dost not have permission to do that...", ChatColor.RED);
                return true;
            }
            if (args.length != 2) {
                return false;
            }

            if (args[1].equalsIgnoreCase("on")) {
                TFM_Util.adminAction(sender.getName(), "Enabling AdminWorld!", false);
                CJFM_ConfigEntry.ENABLE_ADMINWORLD.setBoolean(true);
                return true;
            }

            if (args[1].equalsIgnoreCase("off")) {
                TFM_Util.adminAction(sender.getName(), "Disabling AdminWorld!", true);
                CJFM_ConfigEntry.ENABLE_ADMINWORLD.setBoolean(false);
                return true;
            }
        }
        return false;
    }
}
