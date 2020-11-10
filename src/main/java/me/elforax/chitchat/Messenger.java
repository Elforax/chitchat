package me.elforax.chitchat;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

/**
 * Massager Class is used to place messages in a central place
 */
public class Messenger {
    private static final Plugin plugin = Chitchat.getPlugin(); //!< Create a reference to the main class for use with JavaPlugin extensions

    /**
     * Console Message Controller
     * Adds pre set messages to console for quick and simple message controls in other classes
     * @param msgType
     */
    public static void consoleMsg(MassageType msgType){
        switch (msgType){
            case START:
                header();
                plugin.getLogger().info(ChatColor.GREEN + "Initializing Main...");
                break;
            case SCHEDULER:
                plugin.getLogger().info(ChatColor.GREEN + "Initializing Scheduler...");
                break;
            case EVENTS:
                plugin.getLogger().info(ChatColor.GREEN + "Registering Events...");
                break;
            case STOP:
                header();
                plugin.getLogger().info(ChatColor.GREEN + "Shutting down...");
                break;
            default:
                plugin.getLogger().info("msgType not recognized used default instead!");
                break;
        }
    }

    /**
     * Header for use in console to make finding the plugins messages easier
     */
    private static void header(){
        plugin.getLogger().info(ChatColor.GREEN + "-------------------------");
        plugin.getLogger().info(ChatColor.GREEN + "|     ChitChat  v0.2    |");
        plugin.getLogger().info(ChatColor.GREEN + "-------------------------");
    }
}

