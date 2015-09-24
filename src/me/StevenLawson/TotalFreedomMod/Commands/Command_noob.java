package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "For all the noobs out there", usage = "/<command> <player>")
public class Command_noob extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        if (args.length != 1) {
            return false;
        }

        Player p = getPlayer(args[0]);

        if (p == null) {
            playerMsg(TFM_Command.PLAYER_NOT_FOUND);
            return true;
        }

        // strike with lightning effect:
        final Location targetPos = p.getLocation();
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                final Location strike_pos = new Location(targetPos.getWorld(), targetPos.getBlockX() + x, targetPos.getBlockY(), targetPos.getBlockZ() + z);
                targetPos.getWorld().strikeLightning(strike_pos);
            }
        }
        //  TFM_BanManager.getInstance().addUuidBan(Player);
        return true;
    }

}
