package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Make yourself an imposter.", usage = "/<command> [add/remove]")
public class Command_imposter extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        if (TFM_Util.imposters.contains(sender_p.getName())) {
            TFM_Util.imposters.remove(sender_p.getName());
            sender_p.sendMessage(ChatColor.RED + "Successfully removed from imposter list.");
        } else {
            TFM_Util.imposters.add(sender_p.getName());
            sender_p.sendMessage(ChatColor.BLUE + "Sucessfully added to the imposter list.");
        }
        return true;
    }

}
