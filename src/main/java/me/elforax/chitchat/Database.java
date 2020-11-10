package me.elforax.chitchat;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.util.Date;
import java.util.Hashtable;
import java.util.Set;


public class Database {
    private static final Plugin plugin = Chitchat.getPlugin(); //!< Create a reference to the main class for use with JavaPlugin extensions
    private static Hashtable<String, String> chattingPlayers = new Hashtable<String, String>(); //!< List of Player who are active in chat

    public static void addPlayer(String playerName){
        if(!(chattingPlayers.containsKey(playerName))) {
            chattingPlayers.put(playerName, "0");
            plugin.getLogger().info(ChatColor.GOLD + playerName + " Added to hashtable");

            Messenger.playerMsg(MassageType.STARTCHAT, playerName);
        }else{
            plugin.getLogger().info(ChatColor.GOLD + playerName + " Was already in hashtable");
        }
    }

    public static void removePlayer(String playerName){
        if(chattingPlayers.containsKey(playerName)) {
            chattingPlayers.remove(playerName);
            plugin.getLogger().info(ChatColor.GOLD + playerName + " Removed from hashtable");

            Messenger.playerMsg(MassageType.ENDCHAT, playerName);
        }else{
            plugin.getLogger().info(ChatColor.GOLD + playerName + " Was not in hashtable");
        }
    }

    public static void addCount(String playerName){
        if(chattingPlayers.containsKey(playerName)) {
            String valueStr = chattingPlayers.get(playerName);
            int value = Integer.parseInt(valueStr) + 1;
            String result = Integer.toString(value);
            chattingPlayers.put(playerName, result);
            plugin.getLogger().info(ChatColor.GOLD + playerName + " counter updated to " + result);
        }else {
            plugin.getLogger().info(ChatColor.GOLD + playerName + " Was not in hashtable");
        }
    }

    public static int getCount(String playerName){
        if(chattingPlayers.containsKey(playerName)) {
            String valueStr = chattingPlayers.get(playerName);
            return Integer.parseInt(valueStr);
        }else {
            plugin.getLogger().info(ChatColor.GOLD + playerName + " Was not in hashtable");
            return 0;
        }
    }

    public static Set<String> getKeys(){
        return chattingPlayers.keySet();
    }

    public static boolean inList(String playerName){
        return chattingPlayers.containsKey(playerName);
    }

}