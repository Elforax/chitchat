package me.elforax.chitchat;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;


public class DataManager {
    private Plugin plugin;
    private static HashMap<String, String> PlayerList = new HashMap<String, String>();

    public DataManager(JavaPlugin plugin){
        this.plugin = plugin;
    }


}
