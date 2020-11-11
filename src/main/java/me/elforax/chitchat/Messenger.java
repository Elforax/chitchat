package me.elforax.chitchat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
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
    public static void consoleMsg(MessageType msgType){
        switch (msgType){
            case START:
                header();
                plugin.getLogger().info(ChatColor.GREEN + "Initializing Main...");
                break;
            case CONFIG_INIT:
                plugin.getLogger().info(ChatColor.GREEN + "Reading Config...");
                break;
            case CONFIG_VALUES:
                plugin.getLogger().info(ChatColor.AQUA + "config: debug mode = " + ConfigData.debug);
                plugin.getLogger().info(ChatColor.AQUA + "config: disable on move = " + ConfigData.disableOnMove);

                plugin.getLogger().info(ChatColor.AQUA + "config: effect Y offset = " + ConfigData.particleOffsetY + " Blocks");
                plugin.getLogger().info(ChatColor.AQUA + "config: effect period = " + ConfigData.effect_period + " Ticks");

                plugin.getLogger().info(ChatColor.AQUA + "config: worker period = " + ConfigData.period + " Ticks");
                plugin.getLogger().info(ChatColor.AQUA + "config: idle time = " + ConfigData.idle_time + " Ticks");

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
     * Sends a preset message to the console with information about a given player
     * @param msgType
     * @param playerName
     */
    public static  void consoleMsg(MessageType msgType, String playerName){
        switch (msgType){
            case PLAYER_NOT_FOUND:
                plugin.getLogger().info(ChatColor.GOLD + playerName + " Was not in hashtable");
                break;
            case PLAYER_FOUND:
                plugin.getLogger().info(ChatColor.GOLD + playerName + " Was already in hashtable");
                break;
            case PLAYER_ADDED:
                plugin.getLogger().info(ChatColor.GOLD + playerName + " Added to hashtable");
                break;
            case PLAYER_REMOVED:
                plugin.getLogger().info(ChatColor.GOLD + playerName + " Removed from hashtable");
                break;
            default:
                plugin.getLogger().info("msgType not recognized used default instead!");
                break;
        }
    }

    /**
     * Sends a preset message to a given player
     * @param msgType message type to be send
     * @param playerName receiving players name
     */
    public static void playerMsg(MessageType msgType, String playerName){
        Player player = plugin.getServer().getPlayer(playerName);
        if (player != null) {
            switch (msgType) {
                case STARTCHAT:
                    player.sendMessage(ChatColor.GOLD + "ChatMode: " + ChatColor.GREEN + "True");
                    break;
                case ENDCHAT:
                    player.sendMessage(ChatColor.GOLD + "ChatMode: " + ChatColor.RED + "False");
                    break;
                default:
                    plugin.getLogger().info("msgType not recognized used default instead!");
                    break;
            }
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

