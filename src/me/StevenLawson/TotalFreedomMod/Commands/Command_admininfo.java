package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Apply for admin.", usage = "/<command>", aliases = "ai")
public class Command_admininfo extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You wish to apply for admin do you?");
            sender.sendMessage(ChatColor.RED + "Well, first of all you need to register a forum account at http://ufreedom.boards.net");
            sender.sendMessage(ChatColor.AQUA + "Then, copy the template at http://ufreedom.boards.net/thread/3/application-super-admin-status-carefully");
            sender.sendMessage(ChatColor.GOLD + "After that, make a new thread by going to this link http://ufreedom.boards.net/thread/new/25");
            sender.sendMessage(ChatColor.GREEN + "Then, paste the template, and answer all of the questions.");
            return true;
        } else {
            return false;
        }
    }
}
