package me.StevenLawson.TotalFreedomMod.Commands;

import java.util.Random;
import me.StevenLawson.TotalFreedomMod.FOPM_TFM_Util;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "PotatoPotatoPotato", usage = "/<command>")
public class Command_potato extends TFM_Command {

    public static final String POTATO_LYRICS = "They're red, they're white, they're brown. They get that way underground. There can't be much to do. So now they have blue ones too. We don't care what they look like we'll eat them. Any way they can fit on our plate. Every way we can conjure to heat them. We're delighted and think they're just great.";
    private final Random random = new Random();

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        if (!sender.getName().equalsIgnoreCase("tylerhyperHD") && !sender.getName().equalsIgnoreCase("cowgomooo12")) {
            playerMsg("Only those with ultimate potato powers may use this command!", ChatColor.RED);
            return true;
        }

        StringBuilder output = new StringBuilder();
        Random randomGenerator = new Random();

        String[] words = POTATO_LYRICS.split(" ");
        for (String word : words) {
            String color_code = Integer.toHexString(1 + randomGenerator.nextInt(14));
            output.append(ChatColor.COLOR_CHAR).append(color_code).append(word).append(" ");
        }

        ItemStack heldItem = new ItemStack(Material.BAKED_POTATO);
        ItemMeta heldItemMeta = heldItem.getItemMeta();
        if (sender.getName().equals("tylerhyperHD")) {
            heldItemMeta.setDisplayName((new StringBuilder()).append(ChatColor.WHITE).append("Tyler's Special").append(ChatColor.DARK_GRAY).append(" Potato").toString());
        } else {
            heldItemMeta.setDisplayName((new StringBuilder()).append(ChatColor.WHITE).append("Cow's Special").append(ChatColor.DARK_GRAY).append(" Potato").toString());
        }
        heldItem.setItemMeta(heldItemMeta);

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().setItem(player.getInventory().firstEmpty(), heldItem);
            FOPM_TFM_Util.randomAchievement();
        }

        TFM_Util.bcastMsg(output.toString());
        return true;
    }
}
