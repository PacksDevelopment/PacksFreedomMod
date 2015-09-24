package me.StevenLawson.TotalFreedomMod.Commands;

import me.RyanWild.CJFreedomMod.CJFM_Util;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(description = "Wild's personal bypass.", usage = "/<command>")
public class Command_wildbypass extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        if (args.length == 0) {
            if (senderIsConsole) {
                TFM_Util.adminAction("Wild1145's Robot - ", "WARNING: WILD1145 BYPASS PROGRAM ACTIVATED", true);
                new BukkitRunnable() {
                    @Override
                    public void run() {

                    }
                }.runTaskLater(plugin, 2L * 60L);
                TFM_Util.adminAction("Wild1145's Robot - ", "HIDE THE FUCKING CHILDREN!!!!!", true);

                new BukkitRunnable() {
                    @Override
                    public void run() {

                    }
                }.runTaskLater(plugin, 2L * 20L);

                server.dispatchCommand(sender, "glist unban wild1145");
                server.dispatchCommand(sender, "lockdown");
                server.dispatchCommand(sender, "emg 3");
                server.dispatchCommand(sender, "fr");

                TFM_Util.adminAction("Wild1145's Robot - ", "WARNING: Wild's Program 1 is complete. Please standby for him to join the game", true);
            }
        }

        if (args.length == 1) {
            if (CJFM_Util.SYSADMINS.contains(sender.getName())) {
                if (args[0].equalsIgnoreCase("disable")) {
                    TFM_Util.adminAction("Wild1145's Robot - ", "ATTENTION: WILD1145 BYPASS PROGRAM CLOSING DOWN", false);
                    new BukkitRunnable() {
                        @Override
                        public void run() {

                        }
                    }.runTaskLater(plugin, 2L * 60L);
                    TFM_Util.adminAction("Wild1145's Robot - ", "FIND THE FUCKING CHILDREN!!!!!", false);

                    new BukkitRunnable() {
                        @Override
                        public void run() {

                        }
                    }.runTaskLater(plugin, 2L * 20L);

                    server.dispatchCommand(sender, "lockdown off");
                    server.dispatchCommand(sender, "emg off");
                    server.dispatchCommand(sender, "fr purge");
                    new BukkitRunnable() {
                        @Override
                        public void run() {

                        }
                    }.runTaskLater(plugin, 2L * 60L);

                    TFM_Util.adminAction("Wild1145's Robot - ", "Success: Program Disabled - Server Restored to normal.", false);
                }

            }
        }

        return false;
    }
}
