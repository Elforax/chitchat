package me.elforax.chitchat;

import org.bukkit.ChatColor;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;
import java.util.logging.Level;


public class ChatDatabase {
    private static final Plugin plugin = Chitchat.getPlugin(); //!< Create a reference to the main class for use with JavaPlugin extensions
    private static Hashtable<String, String> chattingPlayers = new Hashtable<String, String>(); //!< List of Player who are active in chat

    /**
     * Adds a player to the hashtable of chattingPlayers
     * @param playerName
     */
    public static void addPlayer(String playerName){
        if(!(inList(playerName))) {
            chattingPlayers.put(playerName, "0");

            if(ConfigData.debug) {
                Messenger.consoleMsg(MessageType.PLAYER_ADDED, playerName);

                Messenger.playerMsg(MessageType.STARTCHAT, playerName);
            }
        }else{
            if(ConfigData.debug) {
                Messenger.consoleMsg(MessageType.PLAYER_FOUND, playerName);
            }
        }
    }

    /**
     * Removes a player from the hashtable of chattingPlayers
     * @param playerName
     */
    public static void removePlayer(String playerName){
        if(inList(playerName)) {
            chattingPlayers.remove(playerName);
            if(ConfigData.debug) {
                Messenger.consoleMsg(MessageType.PLAYER_REMOVED, playerName);

                Messenger.playerMsg(MessageType.ENDCHAT, playerName);
            }
        }else{
            Messenger.consoleMsg(MessageType.PLAYER_NOT_FOUND, playerName);
        }
    }

    /**
     * Adds 1 to the counter of a given player
     * @param playerName
     */
    public static void addCount(String playerName){
        if(inList(playerName)) {
            String valueStr = chattingPlayers.get(playerName);
            int value = Integer.parseInt(valueStr) + 1;
            String result = Integer.toString(value);
            chattingPlayers.put(playerName, result);

            //Debug logger
            if(ConfigData.debug) {
                plugin.getLogger().info(ChatColor.GOLD + playerName + " counter updated to " + result);
            }
        }else {
            Messenger.consoleMsg(MessageType.PLAYER_NOT_FOUND, playerName);
        }
    }

    /**
     * resets a given players chat counter
     * @param playerName
     */
    public static void resetCount(String playerName){
        if(inList(playerName)) {
            chattingPlayers.put(playerName, "0");

            //Debug logger
            if(ConfigData.debug) {
                plugin.getLogger().info(ChatColor.GOLD + playerName + " counter reset to 0");
            }
        }else {
            Messenger.consoleMsg(MessageType.PLAYER_NOT_FOUND, playerName);
        }
    }


    /**
     * Gets the current chatmode counter value for a given player
     * @param playerName
     * @return Integer value of the counter form a given player
     */
    public static int getCount(String playerName){
        if(inList(playerName)) {
            String valueStr = chattingPlayers.get(playerName);
            return Integer.parseInt(valueStr);
        }else {
            Messenger.consoleMsg(MessageType.PLAYER_NOT_FOUND, playerName);
            return 0;
        }
    }


    /**
     * returns the list op chatting players in the hashtable
     * @return Set of keys (list of playerNames form the hashtable)
     */
    public static Set<String> getKeys(){
        return chattingPlayers.keySet();
    }

    /**
     * checks if a player is in the hashtable for chattingPlayers
     * @param playerName requires the players name
     * @return boolean value if the player is in the list true else false
     */
    public static boolean inList(String playerName){
        return chattingPlayers.containsKey(playerName);
    }

}

