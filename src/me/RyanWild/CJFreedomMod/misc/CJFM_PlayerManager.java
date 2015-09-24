package me.RyanWild.CJFreedomMod.misc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.RyanWild.CJFreedomMod.CJFM_Addon;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.player.PlayerJoinEvent;

public class CJFM_PlayerManager extends CJFM_Addon {

    public Map<String, PlayerInfo> infoMap = new HashMap<String, PlayerInfo>();

    public CJFM_PlayerManager(TotalFreedomMod plugin) {
        super(plugin);
    }

    public void onUncancelledPlayerJoin(PlayerJoinEvent event) {

        final Date date = new Date();

        final PlayerInfo info = new PlayerInfo(plugin, event.getPlayer().getName());

    }

    public final PlayerInfo getInfo(OfflinePlayer player) {
        if (player == null) {
            return null;
        }

        PlayerInfo info = infoMap.get(player.getName());

        if (info == null) {
            info = new PlayerInfo(plugin, player.getName());

            if (player.isOnline()) {
                infoMap.put(player.getName(), info);
            }
        }

        return info;
    }

    public static class PlayerInfo {

        private TotalFreedomMod plugin;
        //
        // Saved items 
        private String name;
        private String firstIp;
        private String lastIp;
        private List<String> ips = new ArrayList<String>();
        private int logins;
        private Date firstLogin;
        private Date lastLogin;
        private int votes;
        private Date lastVote;
        //
        // Unsaved items
        private boolean inAdminChat = false;
        private boolean muted = false;
        private boolean busy = false;

        public PlayerInfo(TotalFreedomMod plugin, String name) {
            this.name = name;
            this.plugin = plugin;
        }

        public PlayerInfo(TotalFreedomMod plugin) {
            this.plugin = plugin;
        }

        // ----- ITEMS -----
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirstIp() {
            return this.firstIp;
        }

        public void setFirstIp(String ip) {
            this.firstIp = ip;
        }

        public String getLastIp() {
            return this.lastIp;
        }

        public void setLastIp(String lastIp) {
            this.lastIp = lastIp;
        }

        public List<String> getIps() {
            return this.ips;
        }

        public void setIps(List<String> ips) {
            this.ips = ips;
        }

        public void addIp(String ip) {
            if (!this.ips.contains(ip)) {
                this.ips.add(ip);
            }
        }

        public int getLogins() {
            return logins;
        }

        public void setLogins(int logins) {
            this.logins = logins;
        }

        public int addLogin() {
            return ++this.logins;
        }

        public Date getFirstLogin() {
            return firstLogin;
        }

        public void setFirstLogin(Date login) {
            this.firstLogin = login;
        }

        public Date getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(Date login) {
            this.lastLogin = login;
        }

        public boolean isMuted() {
            return muted;
        }

        public void setMuted(boolean muted) {
            this.muted = muted;
        }

        public boolean isInAdminChat() {
            return inAdminChat;
        }

        public void setInAdminChat(boolean inAdminChat) {
            this.inAdminChat = inAdminChat;
        }

        public boolean isBusy() {
            return busy;
        }

        public void setBusy(boolean busy) {
            this.busy = busy;
        }

        public int getVotes() {
            return votes;
        }

        public void setVotes(int votes) {
            this.votes = votes;
        }

        public Date getLastVote() {
            return lastVote;
        }

        public void setLastVote(Date lastVote) {
            this.lastVote = lastVote;
        }
    }
}
