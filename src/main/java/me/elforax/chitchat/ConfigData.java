package me.elforax.chitchat;

import org.bukkit.Particle;
import org.bukkit.plugin.Plugin;

/**
 * Runs all config variables and stores them for use
 */
public class ConfigData {
    private static final Plugin plugin = Chitchat.getPlugin(); //!< Create a reference to the main class for use with JavaPlugin extensions

    // public variable list ---
    public static Double particleOffsetY = 3.0;
    public static Long effect_period = 20L;

    public static Long period = 20L;
    public static int idle_time = 200;
    public static boolean disableOnMove = true;
    public static boolean debug = false;
    // end variable list ------

    public static void init(){
        plugin.saveDefaultConfig(); //!< Saves a default config stored in the Jar file
    }

    public static void readConfig(){
        period = plugin.getConfig().getLong("workers.period");
        effect_period = plugin.getConfig().getLong("workers.effect_period");
        idle_time = plugin.getConfig().getInt("workers.idle_time");
        particleOffsetY = plugin.getConfig().getDouble("workers.effect.offset_y");
        disableOnMove = plugin.getConfig().getBoolean("disableOnMove");
        debug = plugin.getConfig().getBoolean("debug");

        Messenger.consoleMsg(MessageType.CONFIG_VALUES);
    }
}
