package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.OP, source = SourceType.BOTH)
@CommandParameters(description = "Gives you advice on how to save your builds with WorldEdit.", usage = "/<command>")
public class Command_savebuild extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        playerMsg(ChatColor.BLUE + "##### World Edit Saving Assistance #####");
        playerMsg(ChatColor.DARK_AQUA + "If you need to save your builds then follow this guide step-by-step to do so!");
        playerMsg(ChatColor.GREEN + "1) Do //wand");
        playerMsg(ChatColor.GREEN + "2) Select the two furthest & opposite edges of your build (One at the bottom south east & the other at the top north west for example)");
        playerMsg(ChatColor.GREEN + "3) Do //copy");
        playerMsg(ChatColor.GREEN + "4) //schematic save <name>");
        playerMsg(ChatColor.GREEN + "5) Use //schematic load (name of your build) to place it back!");
        playerMsg(ChatColor.BLUE + "That's it! Have fun saving and loading schematics with WorldEdit!");

        return true;
    }
}
