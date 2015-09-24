package me.StevenLawson.TotalFreedomMod.Commands;

import java.util.ArrayList;
import java.util.List;
import me.RyanWild.CJFreedomMod.Config.CJFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerData;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

@CommandPermissions(level = AdminLevel.SENIOR, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Gain a doomhammer!", usage = "/<command>")
public class Command_doomhammer extends TFM_Command {    

    @Override
    public boolean run(final CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        PlayerInventory inventory = sender_p.getInventory();
        
        if (args.length != 0) {
            return false;
        }

        if (CJFM_ConfigEntry.DHAMMER_MODE.getBoolean() == false) {
            TFM_Util.adminAction(sender.getName(), "Enabling the Doom Hammer!", true);
            CJFM_ConfigEntry.DHAMMER_MODE.setBoolean(true);
            inventory.addItem(getDoomHammer());
            for (Player player : server.getOnlinePlayers()) {
                if (!TFM_AdminList.isSeniorAdmin(player)) {
                    player.setGameMode(GameMode.SURVIVAL);
                    TFM_PlayerData playerData = TFM_PlayerData.getPlayerData(player);
                    playerData.setFrozen(true);
                }
            }
            return true;
        }

        if (CJFM_ConfigEntry.DHAMMER_MODE.getBoolean() == true) {
            TFM_Util.adminAction(sender.getName(), "Disabling the Doom Hammer, YOU ARE SAFE... FOR NOW!!!!", true);
            CJFM_ConfigEntry.DHAMMER_MODE.setBoolean(false);
            inventory.removeItem(getDoomHammer());
            for (Player player : server.getOnlinePlayers()) {
                if (!TFM_AdminList.isSeniorAdmin(player)) {
                    player.setGameMode(GameMode.CREATIVE);
                    TFM_PlayerData playerData = TFM_PlayerData.getPlayerData(player);
                    playerData.setFrozen(false);
                }
            }
            return true;
        }

        return true;
    }

    public static ItemStack getDoomHammer() {
        ItemStack dhammer = new ItemStack(Material.DIAMOND_AXE, 1);
        ItemMeta dhammermeta = dhammer.getItemMeta();
        List<String> lores = new ArrayList<String>();
        lores.add(ChatColor.RED + "The all powerful Doom Hammer!");
        dhammermeta.setDisplayName(ChatColor.DARK_RED + "Doom Hammer");
        dhammermeta.setLore(lores);
        dhammer.setItemMeta(dhammermeta);
        return dhammer;
    }
}
