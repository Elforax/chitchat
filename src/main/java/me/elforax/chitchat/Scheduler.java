package me.elforax.chitchat;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Set;

public class Scheduler {
    private static Plugin plugin = Chitchat.getPlugin(); //!< Create a reference to the main class for use with JavaPlugin extensions

    public static void initialization(){
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new addChitListCount(),0l, 20l); //TODO make fix period time a variable retrieved from a config file
        scheduler.scheduleSyncRepeatingTask(plugin, new sayHello(),0l, 25l); //TODO make fix period time a variable retrieved from a config file
    }
}

class addChitListCount implements Runnable{
    @Override
    public void run() {
        Set<String> keys = Database.getKeys();
        for(String key : keys){
            Database.addCount(key);
        }
    }
}

class sayHello implements Runnable{
    private static Plugin plugin = Chitchat.getPlugin();
    @Override
    public void run() {
        Set<String> keys = Database.getKeys();
        for(String key : keys){
            int count = Database.getCount(key);
            if(count >= 10){ //TODO make fix int a variable retrieved from a config file
                Database.removePlayer(key);
            }
        }
        plugin.getLogger().info(ChatColor.LIGHT_PURPLE + "Hello :)");
    }
}
