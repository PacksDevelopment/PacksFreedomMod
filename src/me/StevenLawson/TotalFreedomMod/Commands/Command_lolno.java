package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Lolno.", usage = "/<command>")
public class Command_lolno extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        sender_p.chat("Lolno. Just.. just.. go. just GO AWAY!");
        Bukkit.dispatchCommand(sender, "expel");
        return true;
    }
}
