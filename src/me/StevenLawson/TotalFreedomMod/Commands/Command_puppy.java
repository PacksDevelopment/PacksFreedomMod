package me.StevenLawson.TotalFreedomMod.Commands;

import static me.StevenLawson.TotalFreedomMod.Commands.Command_smite.smite;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Pupp", aliases = "pup", usage = "/<command>")
public class Command_puppy extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        TFM_Util.adminAction(ChatColor.DARK_RED + "WARNING: " + sender.getName(), "is now a puppy. Woof Woof Mother F**ker", false);
        sender_p.chat("Woof Woof Woof");
        sender_p.chat(ChatColor.AQUA + "OH NO I SPAMMED 3 TIMES");
        smite(sender_p);
        sender_p.chat(ChatColor.AQUA + "Okay, I won't be a puppy. I'll be a cat.");
        sender_p.chat(ChatColor.AQUA + "NOOOOOOOOOO. NOT A CAT");
        smite(sender_p);
        return true;
    }
}
