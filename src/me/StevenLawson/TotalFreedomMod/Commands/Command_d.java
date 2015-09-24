package me.StevenLawson.TotalFreedomMod.Commands;

import me.RyanWild.CJFreedomMod.CJFM_Util;
import me.RyanWild.CJFreedomMod.Player.CJFM_DonatorList;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(description = "Donator Chat - Chat With other Donators", usage = "/<command> [message...]", aliases = "donatorchat")

public class Command_d extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {

        if (args.length == 0) {
            return false;
        } else {
            if (TFM_AdminList.isSeniorAdmin(sender) || CJFM_DonatorList.isSuperDonor(sender)) {
                CJFM_Util.donatorChatMessage(sender, StringUtils.join(args, " "), senderIsConsole);
                return true;
            } else {
                TFM_Util.playerMsg(sender, "You do not have permission to access Donator Chat!", ChatColor.RED);
                return true;
            }
        }
    }
}
