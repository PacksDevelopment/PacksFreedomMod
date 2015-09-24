package me.RyanWild.CJFreedomMod.Player;

import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import me.RyanWild.CJFreedomMod.CJFreedomMod;
import me.StevenLawson.TotalFreedomMod.Config.TFM_Config;
import me.StevenLawson.TotalFreedomMod.TFM_Log;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import com.google.common.collect.Sets;
import me.StevenLawson.TotalFreedomMod.TFM_DepreciationAggregator;
import me.StevenLawson.TotalFreedomMod.World.CJFM_DonatorWorld;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.util.FileUtil;

public class CJFM_DonatorList {

    private static final Map<UUID, CJFM_Donator> donorList;
    private static final Set<UUID> donorUUIDs;
    private static final Set<UUID> seniorUUIDs;
    private static final Set<String> donorIps;

    static {
        donorList = new HashMap<UUID, CJFM_Donator>();
        donorUUIDs = new HashSet<UUID>();
        seniorUUIDs = new HashSet<UUID>();
        donorIps = new HashSet<String>();
    }

    private CJFM_DonatorList() {
        throw new AssertionError();
    }

    public static Set<UUID> getDonatorUUIDs() {
        return Collections.unmodifiableSet(donorUUIDs);
    }

    public static Set<UUID> getSeniorUUIDs() {
        return Collections.unmodifiableSet(seniorUUIDs);
    }

    public static Set<String> getDonatorIps() {
        return Collections.unmodifiableSet(donorIps);
    }

    public static Set<String> getDonatorNames() {
        final Set<String> names = new HashSet<String>();

        for (CJFM_Donator admin : donorList.values()) {
            if (!admin.isActivated()) {
                continue;
            }

            names.add(admin.getLastLoginName());
        }

        return Collections.unmodifiableSet(names);
    }

    public static Set<String> getLowerSuperNames() {
        final Set<String> names = new HashSet<String>();

        for (CJFM_Donator admin : donorList.values()) {
            if (!admin.isActivated()) {
                continue;
            }

            names.add(admin.getLastLoginName().toLowerCase());
        }

        return Collections.unmodifiableSet(names);
    }

    public static Set<CJFM_Donator> getAllAdmins() {
        return Sets.newHashSet(donorList.values());
    }

    public static void load() {
        donorList.clear();

        final TFM_Config config = new TFM_Config(TotalFreedomMod.plugin, CJFreedomMod.DONATOR_FILE, true);
        config.load();

        // Parse old donors
        if (config.isConfigurationSection("donators")) {
            parseOldConfig(config);
        }

        if (!config.isConfigurationSection("Donors")) {
            TFM_Log.warning("Missing donors section in donor.yml.");
            return;
        }

        final ConfigurationSection section = config.getConfigurationSection("Donors");

        for (String uuidString : section.getKeys(false)) {
            if (!TFM_Util.isUniqueId(uuidString)) {
                TFM_Log.warning("Invalid Unique ID: " + uuidString + " in donor.yml, ignoring");
                continue;
            }

            final UUID uuid = UUID.fromString(uuidString);

            final CJFM_Donator donor = new CJFM_Donator(uuid, section.getConfigurationSection(uuidString));
            donorList.put(uuid, donor);
        }

        updateIndexLists();

        TFM_Log.info("Loaded " + donorList.size() + " donors (" + donorUUIDs.size() + " active) and " + donorIps.size() + " IPs.");
    }

    public static void createBackup() {
        final File oldYaml = new File(TotalFreedomMod.plugin.getDataFolder(), CJFreedomMod.DONATOR_FILE);
        final File newYaml = new File(TotalFreedomMod.plugin.getDataFolder(), CJFreedomMod.DONATOR_FILE + ".bak");
        FileUtil.copy(oldYaml, newYaml);
    }

    public static void updateIndexLists() {
        donorUUIDs.clear();
        seniorUUIDs.clear();
        donorIps.clear();

        for (CJFM_Donator admin : donorList.values()) {
            if (!admin.isActivated()) {
                continue;
            }

            final UUID uuid = admin.getUniqueId();

            donorUUIDs.add(uuid);

            for (String ip : admin.getIps()) {
                donorIps.add(ip);
            }

            if (admin.isSeniorDonor()) {
                seniorUUIDs.add(uuid);
            }
        }

        CJFM_DonatorWorld.getInstance().wipeAccessCache();
    }

    private static void parseOldConfig(TFM_Config config) {
        TFM_Log.info("Old donor configuration found, parsing...");

        final ConfigurationSection section = config.getConfigurationSection("donors");

        int counter = 0;
        int errors = 0;

        for (String admin : config.getConfigurationSection("donors").getKeys(false)) {
            final OfflinePlayer player = TFM_DepreciationAggregator.getOfflinePlayer(admin);

            if (player == null || player.getUniqueId() == null) {
                errors++;
                TFM_Log.warning("Could not convert admin " + admin + ", UUID could not be found!");
                continue;
            }

            final String uuid = player.getUniqueId().toString();

            config.set("donors." + uuid + ".last_login_name", player.getName());
            config.set("donors." + uuid + ".is_activated", section.getBoolean(admin + ".is_activated"));
            config.set("donors." + uuid + ".is_senior_donor", section.getBoolean(admin + ".is_senior_donor"));
            config.set("donors." + uuid + ".last_login", section.getString(admin + ".last_login"));
            config.set("donors." + uuid + ".custom_login_message", section.getString(admin + ".custom_login_message"));
            config.set("donors." + uuid + ".ips", section.getStringList(admin + ".ips"));

            counter++;
        }

        config.set("donors", null);
        config.save();

        TFM_Log.info("Done! " + counter + " donors parsed, " + errors + " errors");
    }

    public static void save() {
        final TFM_Config config = new TFM_Config(TotalFreedomMod.plugin, CJFreedomMod.DONATOR_FILE, true);
        config.load();

        Iterator<Entry<UUID, CJFM_Donator>> it = donorList.entrySet().iterator();
        while (it.hasNext()) {
            Entry<UUID, CJFM_Donator> pair = it.next();

            UUID uuid = pair.getKey();
            CJFM_Donator donor = pair.getValue();

            config.set("donors." + uuid + ".last_login_name", donor.getLastLoginName());
            config.set("donors." + uuid + ".is_activated", donor.isActivated());
            config.set("donors." + uuid + ".is_senior_donor", donor.isSeniorDonor());
            config.set("donors." + uuid + ".last_login", TFM_Util.dateToString(donor.getLastLogin()));
            config.set("donors." + uuid + ".custom_login_message", donor.getCustomLoginMessage());
            config.set("donors." + uuid + ".ips", TFM_Util.removeDuplicates(donor.getIps()));
        }

        config.save();
    }

    public static CJFM_Donator getEntry(Player player) {
        final UUID uuid = player.getUniqueId();

        if (Bukkit.getOnlineMode()) {
            if (donorList.containsKey(uuid)) {
                return donorList.get(uuid);
            }
        }

        return getEntryByIp(TFM_Util.getIp(player));
    }

    public static CJFM_Donator getEntry(UUID uuid) {
        return donorList.get(uuid);
    }

    public static CJFM_Donator getEntry(String name) {
        for (UUID uuid : donorList.keySet()) {
            if (donorList.get(uuid).getLastLoginName().equalsIgnoreCase(name)) {
                return donorList.get(uuid);
            }
        }
        return null;
    }

    public static CJFM_Donator getEntryByIp(String ip) {
        return getEntryByIp(ip, false);
    }

    public static CJFM_Donator getEntryByIp(String needleIp, boolean fuzzy) {
        Iterator<Entry<UUID, CJFM_Donator>> it = donorList.entrySet().iterator();
        while (it.hasNext()) {
            final Entry<UUID, CJFM_Donator> pair = it.next();
            final CJFM_Donator donor = pair.getValue();

            if (fuzzy) {
                for (String haystackIp : donor.getIps()) {
                    if (TFM_Util.fuzzyIpMatch(needleIp, haystackIp, 3)) {
                        return donor;
                    }
                }
            } else {
                if (donor.getIps().contains(needleIp)) {
                    return donor;
                }
            }
        }
        return null;
    }

    public static void updateLastLogin(Player player) {
        final CJFM_Donator admin = getEntry(player);
        if (admin == null) {
            return;
        }
        admin.setLastLogin(new Date());
        admin.setLastLoginName(player.getName());
        save();
    }

    public static boolean isSeniorDonor(CommandSender sender) {
        return isSeniorDonor(sender, false);
    }

    public static boolean isSeniorDonor(CommandSender sender, boolean verifySuperadmin) {
        if (verifySuperadmin) {
            if (!isSuperDonor(sender)) {
                return false;
            }
        }

        final CJFM_Donator entry = getEntry((Player) sender);
        if (entry != null) {
            return entry.isSeniorDonor();
        }

        return false;
    }

    public static boolean isSuperDonor(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }

        if (Bukkit.getOnlineMode() && donorUUIDs.contains(((Player) sender).getUniqueId())) {
            return true;
        }

        if (donorIps.contains(TFM_Util.getIp((Player) sender))) {
            return true;
        }

        return false;
    }

    public static boolean isIdentityMatched(Player player) {
        if (!isSuperDonor(player)) {
            return false;
        }

        if (Bukkit.getOnlineMode()) {
            return true;
        }

        final CJFM_Donator entry = getEntry(player);
        if (entry == null) {
            return false;
        }

        return entry.getUniqueId().equals(player.getUniqueId());
    }

    @Deprecated
    public static boolean checkPartialSuperadminIp(String ip, String name) {
        ip = ip.trim();

        if (donorIps.contains(ip)) {
            return true;
        }

        try {
            String matchIp = null;
            for (String testIp : donorIps) {
                if (TFM_Util.fuzzyIpMatch(ip, testIp, 3)) {
                    matchIp = testIp;
                    break;
                }
            }

            if (matchIp != null) {
                final CJFM_Donator entry = getEntryByIp(matchIp);

                if (entry == null) {
                    return true;
                }

                if (entry.getLastLoginName().equalsIgnoreCase(name)) {
                    if (!entry.getIps().contains(ip)) {
                        entry.addIp(ip);
                    }
                    save();
                }
                return true;

            }
        } catch (Exception ex) {
            TFM_Log.severe(ex);
        }

        return false;
    }

    public static boolean isAdminImpostor(Player player) {
        if (donorUUIDs.contains(player.getUniqueId())) {
            return !isSuperDonor(player);
        }

        return false;
    }

    public static void addSuperadmin(OfflinePlayer player) {
        final UUID uuid = player.getUniqueId();
        final String ip = TFM_Util.getIp(player);

        if (donorList.containsKey(uuid)) {
            final CJFM_Donator donor = donorList.get(uuid);
            donor.setActivated(true);

            if (player instanceof Player) {
                donor.setLastLogin(new Date());
                donor.addIp(ip);
            }
            save();
            updateIndexLists();
            return;
        }

        if (ip == null) {
            TFM_Log.severe("Cannot add donor: " + TFM_Util.formatPlayer(player));
            TFM_Log.severe("Could not retrieve IP!");
            return;
        }

        final CJFM_Donator donor = new CJFM_Donator(
                uuid,
                player.getName(),
                new Date(),
                "",
                false,
                false,
                true);
        donor.addIp(ip);

        donorList.put(uuid, donor);

        save();
        updateIndexLists();
    }

    public static void removeSuperadmin(OfflinePlayer player) {
        final UUID uuid = player.getUniqueId();

        if (!donorList.containsKey(uuid)) {
            TFM_Log.warning("Could not remove admin: " + TFM_Util.formatPlayer(player));
            TFM_Log.warning("Player is not an admin!");
            return;
        }

        final CJFM_Donator donor = donorList.get(uuid);
        donor.setActivated(false);

        save();
        updateIndexLists();
    }

    public static void cleanSuperdonorList(boolean verbose) {
        Iterator<Entry<UUID, CJFM_Donator>> it = donorList.entrySet().iterator();
        while (it.hasNext()) {
            final Entry<UUID, CJFM_Donator> pair = it.next();
            final CJFM_Donator donor = pair.getValue();

            if (!donor.isActivated() || donor.isSeniorDonor()) {
                continue;
            }

            final Date lastLogin = donor.getLastLogin();
            final long lastLoginHours = TimeUnit.HOURS.convert(new Date().getTime() - lastLogin.getTime(), TimeUnit.MILLISECONDS);

        }

        save();
        updateIndexLists();
    }
}