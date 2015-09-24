package me.RyanWild.CJFreedomMod.Config;

import java.util.List;

public enum CJFM_ConfigEntry {

    HOSTNAME(String.class, "Hostname"),
    PORT(String.class, "Port"),
    DATABASE(String.class, "Database"),
    USER(String.class, "Username"),
    PASSWORD(String.class, "Password"),
    PANEL_URL(String.class, "panel_url"),
    PANEL_API_KEY(String.class, "panel_api_key"),
    HEAD(String.class, "head"),
    EMERGANCY_MODE(Boolean.class, "emergancy_mode"),
    EMERGANCY_MODE_OPEN(Boolean.class, "emergancy_mode_open"),
    DEVELOPMENT_MODE(Boolean.class, "dev_mode"),
    DHAMMER_MODE(Boolean.class, "dhammer_mode"),
    ENABLE_ADMINWORLD(Boolean.class, "adminworld");
    //
    private final Class<?> type;
    private final String configName;

    private CJFM_ConfigEntry(Class<?> type, String configName) {
        this.type = type;
        this.configName = configName;
    }

    public Class<?> getType() {
        return type;
    }

    public String getConfigName() {
        return configName;
    }

    public String getString() {
        return CJFM_MainConfig.getInstance().getString(this);
    }

    public String setString(String value) {
        CJFM_MainConfig.getInstance().setString(this, value);
        return value;
    }

    public Double getDouble() {
        return CJFM_MainConfig.getInstance().getDouble(this);
    }

    public Double setDouble(Double value) {
        CJFM_MainConfig.getInstance().setDouble(this, value);
        return value;
    }

    public Boolean getBoolean() {
        return CJFM_MainConfig.getInstance().getBoolean(this);
    }

    public Boolean setBoolean(Boolean value) {
        CJFM_MainConfig.getInstance().setBoolean(this, value);
        return value;
    }

    public Integer getInteger() {
        return CJFM_MainConfig.getInstance().getInteger(this);
    }

    public Integer setInteger(Integer value) {
        CJFM_MainConfig.getInstance().setInteger(this, value);
        return value;
    }

    public List<?> getList() {
        return CJFM_MainConfig.getInstance().getList(this);
    }

    public static CJFM_ConfigEntry findConfigEntry(String name) {
        name = name.toLowerCase().replace("_", "");
        for (CJFM_ConfigEntry entry : values()) {
            if (entry.toString().toLowerCase().replace("_", "").equals(name)) {
                return entry;
            }
        }
        return null;
    }
}