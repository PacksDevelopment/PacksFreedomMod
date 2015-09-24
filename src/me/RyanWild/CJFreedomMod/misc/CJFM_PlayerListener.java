package me.RyanWild.CJFreedomMod.misc;

import me.RyanWild.CJFreedomMod.CJFM_Util;
import me.RyanWild.CJFreedomMod.Config.CJFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.Commands.Command_doomhammer;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Ban;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerData;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import static me.StevenLawson.TotalFreedomMod.TotalFreedomMod.plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class CJFM_PlayerListener implements Listener {

    private static final int MIN_WORD_LENGTH = 4;

    @EventHandler(priority = EventPriority.HIGH)
    public static void onPlayerJoinEvent(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (TFM_AdminList.isSuperAdmin(player)) {
            TFM_PlayerData.getPlayerData(player).setCommandSpy(true);
        }

    }

    @EventHandler
    public void onPlayerUseItem(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (item == null) {
            return;
        }
        if (item.equals(Command_doomhammer.getDoomHammer())) {
            CamPlayer cplayer = new CamPlayer(player);
            final Entity e = cplayer.getTargetEntity(50);
            if (e instanceof LivingEntity) {
                Player eplayer = (Player) e;
                String reason = "Hit by " + player.getName() + "'s Doom Hammer";
                TFM_Util.adminAction(player.getName() + "'s Doom Hammer", "Casting oblivion over " + eplayer.getName(), true);
                TFM_Util.bcastMsg(eplayer.getName() + " will be completely obliviated!", ChatColor.RED);

                // remove from whitelist
                eplayer.setWhitelisted(false);

                // deop
                eplayer.setOp(false);

                // ban IP address:
                final String ip = eplayer.getAddress().getAddress().getHostAddress();
                for (String playerIp : TFM_PlayerList.getEntry(eplayer).getIps()) {
                    TFM_BanManager.addIpBan(new TFM_Ban(playerIp, eplayer.getName()));
                }

                TFM_BanManager.addUuidBan(player);

                // set gamemode to survival
                eplayer.setGameMode(GameMode.SURVIVAL);

                // clear inventory
                eplayer.closeInventory();
                eplayer.getInventory().clear();

                // ignite player
                eplayer.setFireTicks(10000);

                // generate explosion
                eplayer.getWorld().createExplosion(eplayer.getLocation().getX(), eplayer.getLocation().getY(), eplayer.getLocation().getZ(), 4f, false, false);

                // strike lightning
                eplayer.getWorld().strikeLightning(eplayer.getLocation());

                // kill (if not done already)
                eplayer.setHealth(0.0);

                // message
                TFM_Util.adminAction(player.getName() + "'s Doom Hammer", "Banning " + eplayer.getName() + ", IP: " + TFM_Util.getFuzzyIp(ip), true);

                // generate explosion
                eplayer.getWorld().createExplosion(eplayer.getLocation().getX(), eplayer.getLocation().getY(), eplayer.getLocation().getZ(), 4f, false, false);

                //kick player
                eplayer.kickPlayer(ChatColor.RED + "FUCKOFF, and get your shit together!\nHit by " + player.getName() + "'s Doom Hammer");
            } else {
                player.getWorld().strikeLightningEffect(player.getLocation());
            }
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        plugin.adminBusy.onPlayerCommandPreprocess(event);

        Player player = event.getPlayer();
        String command = event.getMessage().toLowerCase().trim();
        if (command.contains("175:") || command.contains("double_plant:")) {
            event.setCancelled(true);
            TFM_Util.bcastMsg(player.getName() + " just attempted to use the crash item! Deal with them appropriately please!", ChatColor.DARK_RED);
        }

        if (command.contains("&k") && !TFM_AdminList.isSuperAdmin(player)) {
            event.setCancelled(true);
            TFM_Util.playerMsg(player, ChatColor.RED + "You are not permitted to use &k!");
        }

        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (command.contains("msg" + pl) && (plugin.playerManager.getInfo(pl).isBusy())) {
                event.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void setFlyOnJump(PlayerToggleFlightEvent event) {
        final Player player = event.getPlayer();
        if (event.isFlying() && event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            player.setFlying(false);
            Vector jump = player.getLocation().getDirection().multiply(0.8).setY(1.1);
            player.setVelocity(player.getVelocity().add(jump));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();
                if (player.getGameMode() == GameMode.CREATIVE) {
                    TFM_Util.playerMsg(player, "NO GM / GOD PVP!", ChatColor.DARK_RED);
                    event.setCancelled(true);
                }
            }
            if (event.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) event.getDamager();
                if (arrow.getShooter() instanceof Player) {
                    Player player = (Player) arrow.getShooter();
                    if (player.getGameMode() == GameMode.CREATIVE) {
                        TFM_Util.playerMsg(player, "NO GM / GOD PVP!", ChatColor.DARK_RED);
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerChangeGameMode(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SURVIVAL) {
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (CJFM_ConfigEntry.DEVELOPMENT_MODE.getBoolean() && CJFM_Util.DEVELOPERS.contains(event.getPlayer().getName())) {
            event.setMessage(ChatColor.DARK_PURPLE + event.getMessage());
        }

        if (player.getWorld().getName().equalsIgnoreCase("adminworld")) {
            event.setCancelled(true);

            for (Player pl : Bukkit.getOnlinePlayers()) {
                if (pl.getWorld().getName().equalsIgnoreCase("adminworld")) {
                    TFM_Util.playerMsg(pl, event.getMessage());
                }
            }
        }

    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerChat(final AsyncPlayerChatEvent event) {
        plugin.adminBusy.onPlayerChat(event);

    }
}
