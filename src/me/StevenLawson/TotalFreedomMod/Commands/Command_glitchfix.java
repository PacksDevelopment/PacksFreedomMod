package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH, blockHostConsole = true)
@CommandParameters(description = "If you can't chat properly, use this.", usage = "/<command>")
public class Command_glitchfix extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        TFM_PlayerData plox = TFM_PlayerData.getPlayerDataSync(sender_p);

        plox.setAdminChat(false);
        plox.setMuted(false);
        return true;
    }
}
