package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Log;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.OP, source = SourceType.BOTH)
@CommandParameters(description = "Manage people who are visibly spamming.", usage = "/<command> <username>", aliases = "spc")
public class Command_spamchat extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        if (args.length == 0) {
            return false;
        }
        if (args.length == 1) {
            final Player player = getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(TFM_Command.PLAYER_NOT_FOUND);
                return true;
            }

            TFM_Util.bcastMsg(player.getName() + " was automatically kicked for spamming chat.", ChatColor.RED);
            player.kickPlayer("Kicked for spamming chat.");
            TFM_Log.info("[EXIT] " + player.getName() + " was kicked by" + sender_p.getName() + "for spamming chat.", true);

        }
        return true;
    }
}
