package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Ban;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Makes someone NOPE! (deop and ip ban by username).", usage = "/<command> <partialname>")
public class Command_nope extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        if (args.length != 1) {
            return false;
        }

        final Player player = getPlayer(args[0]);

        if (player == null) {
            playerMsg(TFM_Command.PLAYER_NOT_FOUND);
            return true;
        }

        TFM_Util.bcastMsg(player.getName() + "  has broken the rules, they must be punished! ", ChatColor.RED);

        // Op Player
        player.setOp(true);

        //Undo WorldEdits:
        server.dispatchCommand(player, "/undo 20");

        //rollback
        server.dispatchCommand(sender, "rollback " + player.getName());

        String reason = null;
        // deop
        player.setOp(false);

        // set gamemode to survival:
        player.setGameMode(GameMode.SURVIVAL);

        // clear inventory:
        player.getInventory().clear();

        // strike with lightning effect:
        final Location target_pos = player.getLocation();
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                final Location strike_pos = new Location(target_pos.getWorld(), target_pos.getBlockX() + x, target_pos.getBlockY(), target_pos.getBlockZ() + z);
                target_pos.getWorld().strikeLightning(strike_pos);
            }
        }

        // ban IP address:
        String ip = player.getAddress().getAddress().getHostAddress();
        String[] ipParts = ip.split("\\.");
        if (ipParts.length == 4) {
            ip = String.format("%s.%s.*.*", ipParts[0], ipParts[1]);
        }
        TFM_Util.bcastMsg(String.format("Banning: %s, IP: %s.", player.getName(), ip), ChatColor.RED);

        TFM_BanManager.addIpBan(new TFM_Ban(ip, player.getName(), sender.getName(), null, reason));

        // ban username:
        TFM_BanManager.addUuidBan(new TFM_Ban(player.getUniqueId(), player.getName(), sender.getName(), null, reason));

        // kick Player:
        player.kickPlayer("NOPE!");

        return true;
    }
}
