package me.StevenLawson.TotalFreedomMod.Commands;

import me.RyanWild.CJFreedomMod.Player.CJFM_DonatorList;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.World.CJFM_DonatorWorld;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.OP, source = SourceType.BOTH)
@CommandParameters(description = "Go to the Donator World.", usage = "/<command> [guest < list | purge | add <player> | remove <player> > | time <morning | noon | evening | night> | weather <off | on | storm>]")
public class Command_donatorworld extends TFM_Command {

    private enum CommandMode {

        TELEPORT, GUEST, TIME, WEATHER
    }

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        CommandMode commandMode = null;

        if (args.length == 0) {
            commandMode = CommandMode.TELEPORT;
        } else if (args.length >= 2) {
            if ("guest".equalsIgnoreCase(args[0])) {
                commandMode = CommandMode.GUEST;
            } else if ("time".equalsIgnoreCase(args[0])) {
                commandMode = CommandMode.TIME;
            } else if ("weather".equalsIgnoreCase(args[0])) {
                commandMode = CommandMode.WEATHER;
            }
        }

        if (commandMode == null) {
            return false;
        }

        try {
            switch (commandMode) {
                case TELEPORT: {

                    if (!(sender instanceof Player) || sender_p == null) {
                        return true;
                    }

                    World adminWorld = null;
                    try {
                        adminWorld = CJFM_DonatorWorld.getInstance().getWorld();
                    } catch (Exception ex) {
                    }

                    if (adminWorld == null || sender_p.getWorld() == adminWorld) {
                        playerMsg("Going to the main world.");
                        sender_p.teleport(server.getWorlds().get(0).getSpawnLocation());
                    } else {
                        if (CJFM_DonatorWorld.getInstance().canAccessWorld(sender_p)) {
                            playerMsg("Going to the Donator World.");
                            CJFM_DonatorWorld.getInstance().sendToWorld(sender_p);
                        } else {
                            playerMsg("You don't have permission to access the Doantor World.");
                        }
                    }

                    break;
                }
                case GUEST: {
                    if (args.length == 2) {
                        if ("list".equalsIgnoreCase(args[1])) {
                            playerMsg("Donator World guest list: " + CJFM_DonatorWorld.getInstance().guestListToString());
                        } else if ("purge".equalsIgnoreCase(args[1])) {
                            assertCommandPerms(sender, sender_p);
                            CJFM_DonatorWorld.getInstance().purgeGuestList();
                            TFM_Util.adminAction(sender.getName(), "Donator World guest list purged.", false);
                        } else {
                            return false;
                        }
                    } else if (args.length == 3) {
                        assertCommandPerms(sender, sender_p);

                        if ("add".equalsIgnoreCase(args[1])) {
                            final Player player = getPlayer(args[2]);

                            if (player == null) {
                                sender.sendMessage(TFM_Command.PLAYER_NOT_FOUND);
                                return true;
                            }

                            if (CJFM_DonatorWorld.getInstance().addGuest(player, sender_p)) {
                                TFM_Util.adminAction(sender.getName(), "Donator World guest added: " + player.getName(), false);
                            } else {
                                playerMsg("Could not add player to guest list.");
                            }
                        } else if ("remove".equals(args[1])) {
                            final Player player = CJFM_DonatorWorld.getInstance().removeGuest(args[2]);
                            if (player != null) {
                                TFM_Util.adminAction(sender.getName(), "Doantor World guest removed: " + player.getName(), false);
                            } else {
                                playerMsg("Can't find guest entry for: " + args[2]);
                            }
                        } else {
                            return false;
                        }
                    }

                    break;
                }
                case TIME: {
                    assertCommandPerms(sender, sender_p);

                    if (args.length == 2) {
                        CJFM_DonatorWorld.TimeOfDay timeOfDay = CJFM_DonatorWorld.TimeOfDay.getByAlias(args[1]);
                        if (timeOfDay != null) {
                            CJFM_DonatorWorld.getInstance().setTimeOfDay(timeOfDay);
                            playerMsg("Donator World time set to: " + timeOfDay.name());
                        } else {
                            playerMsg("Invalid time of day. Can be: sunrise, noon, sunset, midnight");
                        }
                    } else {
                        return false;
                    }

                    break;
                }
                case WEATHER: {
                    assertCommandPerms(sender, sender_p);

                    if (args.length == 2) {
                        CJFM_DonatorWorld.WeatherMode weatherMode = CJFM_DonatorWorld.WeatherMode.getByAlias(args[1]);
                        if (weatherMode != null) {
                            CJFM_DonatorWorld.getInstance().setWeatherMode(weatherMode);
                            playerMsg("Donator World weather set to: " + weatherMode.name());
                        } else {
                            playerMsg("Invalid weather mode. Can be: off, rain, storm");
                        }
                    } else {
                        return false;
                    }

                    break;
                }
                default: {
                    return false;
                }
            }
        } catch (PermissionDeniedException ex) {
            sender.sendMessage(ex.getMessage());
        }

        return true;
    }

    private void assertCommandPerms(CommandSender sender, Player sender_p) throws PermissionDeniedException {
        if (!(sender instanceof Player) || sender_p == null || !CJFM_DonatorList.isSuperDonor(sender)) {
            throw new PermissionDeniedException(TFM_Command.MSG_NO_PERMS);
        }
    }

    private class PermissionDeniedException extends Exception {

        public PermissionDeniedException(String string) {
            super(string);
        }
    }
}
