package me.elforax.chitchat;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

/**
 * Massager Class is used to place messages in a central place
 */
public class Massager {
    private static Plugin plugin = Chitchat.getPlugin();

    public static void consoleMsg(MassageType msgType){
        switch (msgType){
            case START:
                plugin.getLogger().info(ChatColor.GREEN + "ChitChat initializing...");
                break;
            case STOP:
                plugin.getLogger().info(ChatColor.GREEN + "ChitChat Shutting down...");
                break;
            default:
                plugin.getLogger().warning("msgType not recognized used default instead!");
                break;
        }
    }
}

