package me.RyanWild.CJFreedomMod;

import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;

public class CJFM_Addon {

    protected final TotalFreedomMod plugin;
    protected final CJFM_Util util;

    public CJFM_Addon(TotalFreedomMod plugin) {
        this.plugin = plugin;
        this.util = plugin.util;
    }
}
