package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.OP, source = SourceType.BOTH)
@CommandParameters(description = "Set your gamemode.", usage = "/<command> <0 | 1 | 2>", aliases = "gm")
public class Command_gamemode extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        if (args.length == 0) {
            return false;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("0")) {
                sender_p.setGameMode(GameMode.SURVIVAL);
                sender.sendMessage("Your gamemode has been updated.");
                return true;
            }
            if (args[0].equalsIgnoreCase("1")) {
                sender_p.setGameMode(GameMode.CREATIVE);
                sender.sendMessage("Your gamemode has been updated.");
                return true;
            }
            if (args[0].equalsIgnoreCase("2")) {
                sender_p.setGameMode(GameMode.ADVENTURE);
                sender.sendMessage("Your gamemode has been updated.");
                return true;
            }
        } else {
            return false;
        }
        return true;
    }
}
