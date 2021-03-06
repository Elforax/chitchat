package me.elforax.chitchat;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Main Class of the ChitChat Plugin
 */
public final class Chitchat extends JavaPlugin {

    private static Plugin plugin; //!< Main plugin instance needed for other classes to interact with main

    /**
     * Runs the starting conditions of the plugin on server startup/restart/reload
     */
    @Override
    public void onEnable() {
        plugin = this; //!< Make a object of the plugin to reference externally form the main pointing to main class
        Messenger.consoleMsg(MessageType.START);

        Messenger.consoleMsg(MessageType.CONFIG_INIT);
        ConfigData.init();
        ConfigData.readConfig();

        // Register More Event listeners to this if required\
        Messenger.consoleMsg(MessageType.EVENTS);
        registerEvents(this, new Events());

        Messenger.consoleMsg(MessageType.SCHEDULER);
        Scheduler.initialization();

    }

    /**
     * Runs the stopping conditions of the plugin on server shutdown/restart/reload
     */
    @Override
    public void onDisable() {
        Messenger.consoleMsg(MessageType.STOP);

        // plugin -> null always last!
        plugin = null; //!< Releases memory held by plugin on shutdown
    }

    /**
     * Register EventHandlers form other classes to the main EventHandler register
     * @param plugin instance of the main plugin class
     * @param listeners List of listeners to add to the EventRegister
     */
    public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners){
        for(Listener listener : listeners){
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    /**
     * retrieves the main plugins object instance
     * @return main plugin class object
     */
    public static Plugin getPlugin(){
        return plugin;
    }
}
