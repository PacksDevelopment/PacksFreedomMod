//Made by Hawkeyeshi
package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Displays a unicorn in someone's chat.", usage = "/<command> <player>")
public class Command_unicorn extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        if (args.length == 0) {
            return false;
        }

        final Player player = getPlayer(args[0]);

        if (player == null) {
            playerMsg(TFM_Command.PLAYER_NOT_FOUND);
            return true;
        }

        sender.sendMessage("Unicorn sent to player successfully.");

        player.playSound(player.getLocation(), Sound.FIREWORK_TWINKLE, 1.0F, 1.0F);

        player.sendMessage(TFM_Util.randomChatColor() + "                                                         ,/");
        player.sendMessage(TFM_Util.randomChatColor() + "                                                        //");
        player.sendMessage(TFM_Util.randomChatColor() + "                                                      ,//");
        player.sendMessage(TFM_Util.randomChatColor() + "                                          ___   /|   |//");
        player.sendMessage(TFM_Util.randomChatColor() + "                                      `__/\\_ --(/|___/-/");
        player.sendMessage(TFM_Util.randomChatColor() + "                                   \\|\\_-\\___ __-_`- /-/ \\.");
        player.sendMessage(TFM_Util.randomChatColor() + "                                  |\\_-___,-\\_____--/_)' ) \\");
        player.sendMessage(TFM_Util.randomChatColor() + "                                   \\ -_ /     __ \\( `( __`\\|");
        player.sendMessage(TFM_Util.randomChatColor() + "                                   `\\__|      |\\)\\ ) /(/|");
        player.sendMessage(TFM_Util.randomChatColor() + "           ,._____.,            ',--//-|      \\  |  '   /");
        player.sendMessage(TFM_Util.randomChatColor() + "          /     __. \\,          / /,---|       \\       /");
        player.sendMessage(TFM_Util.randomChatColor() + "        |  | ( (  \\   |      ,/\\'__/'/          |     |");
        player.sendMessage(TFM_Util.randomChatColor() + "        |  \\  \\`--, `_/_------______/           \\(   )/");
        player.sendMessage(TFM_Util.randomChatColor() + "        | | \\  \\_. \\,                            \\___/\\");
        player.sendMessage(TFM_Util.randomChatColor() + "        | |  \\_   \\  \\                                 \\");
        player.sendMessage(TFM_Util.randomChatColor() + "        \\ \\    \\_ \\   \\   /                             \\");
        player.sendMessage(TFM_Util.randomChatColor() + "         \\ \\  \\._  \\__ \\_|       |                       \\");
        player.sendMessage(TFM_Util.randomChatColor() + "          \\ \\___  \\      \\       |                        \\");
        player.sendMessage(TFM_Util.randomChatColor() + "           \\__ \\__ \\  \\_ |       \\                         |");
        player.sendMessage(TFM_Util.randomChatColor() + "           |  \\_____ \\  ____      |                           |");
        player.sendMessage(TFM_Util.randomChatColor() + "           | \\  \\__ ---' .__\\     |        |                 |");
        player.sendMessage(TFM_Util.randomChatColor() + "           \\  \\__ ---   /   )     |        \\                /");
        player.sendMessage(TFM_Util.randomChatColor() + "            \\   \\____/ / ()(      \\          `---_         /|");
        player.sendMessage(TFM_Util.randomChatColor() + "             \\__________/(,--__    \\_________.    |       ./ |");
        player.sendMessage(TFM_Util.randomChatColor() + "               |     \\ \\  `---_\\--,           \\   \\_,./   |");
        player.sendMessage(TFM_Util.randomChatColor() + "               |      \\  \\_ ` \\    /`---_______-\\   \\\\    /");
        player.sendMessage(TFM_Util.randomChatColor() + "                \\      \\.___,`|   /              \\   \\\\   \\");
        player.sendMessage(TFM_Util.randomChatColor() + "                 \\     |  \\_ \\|   \\              (   |:    |");
        player.sendMessage(TFM_Util.randomChatColor() + "                  \\    \\      \\    |             /  / |    ;");
        player.sendMessage(TFM_Util.randomChatColor() + "                   \\    \\      \\    \\          ( `_'   \\  |");
        player.sendMessage(TFM_Util.randomChatColor() + "                    \\.   \\      \\.   \\          `__/   |  |");
        player.sendMessage(TFM_Util.randomChatColor() + "                      \\   \\       \\.  \\                |  |");
        player.sendMessage(TFM_Util.randomChatColor() + "                       \\   \\        \\  \\               (  )");
        player.sendMessage(TFM_Util.randomChatColor() + "                        \\   |        \\  |                |  |");
        player.sendMessage(TFM_Util.randomChatColor() + "                         |  \\         \\ \\               I  `");
        player.sendMessage(TFM_Util.randomChatColor() + "                         ( __;        ( _;                ('-_';");
        player.sendMessage(TFM_Util.randomChatColor() + "                         |___\\       \\___:              \\___:");

        player.sendMessage("   ");
        player.sendMessage("   ");
        player.sendMessage(TFM_Util.randomChatColor() + "You've been " + TFM_Util.randomChatColor() + "unicorned by " + TFM_Util.randomChatColor() + sender.getName());
        return true;
    }
}
