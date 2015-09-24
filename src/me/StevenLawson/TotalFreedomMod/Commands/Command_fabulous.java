package me.StevenLawson.TotalFreedomMod.Commands;

import static me.StevenLawson.TotalFreedomMod.Commands.Command_smite.smite;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Use the camera", usage = "/<command> <is | not>")
public class Command_fabulous extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            return false;
        }
        if (args.length == 1)
        {
            if (args[0].equals("is"))
            {
                TFM_Util.adminAction(ChatColor.GOLD + "WARNING: " + sender.getName(), "is now fabulous.", false);
                sender_p.chat(ChatColor.RED + " FABULOUSSSSSSSSS!");
                return true;
            }

            else if (args[0].equals("not"))
            {
                sender_p.chat(ChatColor.DARK_RED + "F**k me I am not fabulousssss");
                TFM_Util.adminAction(ChatColor.GREEN + "WARNING: " + sender.getName(), "has become unfabulous. F**k them.", false);
                sender_p.chat(ChatColor.RED + "Everyone hates me now. F**k me.");
                smite(sender_p);
                return true;
            }
        }

        return true;
    }
}
