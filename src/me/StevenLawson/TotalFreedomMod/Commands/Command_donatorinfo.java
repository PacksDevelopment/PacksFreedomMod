package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(description = "This command shows you how to donate to the server.", usage = "/<command>")
public class Command_donatorinfo extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        
        sender.sendMessage(ChatColor.AQUA + "Donating has not been setup yet!");
        
        //TODO: Setup donations
        
//        sender.sendMessage(ChatColor.AQUA + "This message has been wrote on the 30/08./13, things may change");
//        sender.sendMessage(ChatColor.BLUE + "Donator ($5) or Senior Donator ($10)");
//        sender.sendMessage(ChatColor.GREEN + "Commands:");
//        sender.sendMessage(ChatColor.YELLOW + "/cake, /cookie, /expel, /ender /donatorworld");
//        sender.sendMessage(ChatColor.GOLD + "Donator Link: [Not Defined]");
//        sender.sendMessage(ChatColor.RED + "Things to buy if you are already a donor:");
//        sender.sendMessage(ChatColor.BLACK + "Custom Prefix($6.50), Custom Title ($1)");
        return true;

    }
}
