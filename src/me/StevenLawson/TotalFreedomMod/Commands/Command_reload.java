package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Kicks everyone and stops the server.", usage = "/<command>", aliases = "rl")
public class Command_reload extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        TFM_Util.bcastMsg("Server is reloading! Please relog", ChatColor.LIGHT_PURPLE);

        for (Player player : server.getOnlinePlayers()) {
            player.kickPlayer("Server is reloading! Come back in about 30 seconds.");
        }

        server.reload();

        return true;
    }
}
