package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_PlayerData;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Give someone a blowjob.", usage = "/<command> <player>")
public class Command_blowjob extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (!sender.getName().equals("AwesomePinch") && !sender.getName().equals("tylerhyperHD"))
        {
            sender.sendMessage("You cannot give others blowjobs u fuck. No perms for you.");
            sender.sendMessage(TFM_Command.MSG_NO_PERMS);

            return true;
        }
        if (args.length != 1)
        {
            return false;
        }

        final Player player = getPlayer(args[0]);

        if (player == null)
        {
            sender.sendMessage("Player not found!");
            return true;
        }

        TFM_Util.adminAction(sender.getName(), "Giving " + player.getName() + " a blowjob.", true);
        Bukkit.dispatchCommand(sender, "tpo " + player.getName() + " " + sender.getName());
        // Freezes the player to make them get fucked
        TFM_PlayerData playerdata = TFM_PlayerData.getPlayerData(player);
        playerdata.setFrozen(!playerdata.isFrozen());
        player.chat("Ohhhh yeahhhhh.");
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                TFM_Util.bcastMsg(player.getName() + " got fucked too hard, and fell asleep whilst the fuck happened.", ChatColor.RED);
                player.setHealth(0.0);
                player.sendMessage(ChatColor.RED + "You just got a blowjob by a gay guy in Minecraft!");
                // Makes them unfreeze after the deed is done
                TFM_PlayerData playerdata = TFM_PlayerData.getPlayerData(player);
                playerdata.setFrozen(!playerdata.isFrozen());
            }
        }.runTaskLater(plugin, 4L * 40L);
        return true;
    }
}
