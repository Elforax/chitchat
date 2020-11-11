package me.elforax.chitchat;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Set;

/**
 * Scheduler class used to setup the Schedule workers and there tasks
 */
public class Scheduler {
    private static Plugin plugin = Chitchat.getPlugin(); //!< Create a reference to the main class for use with JavaPlugin extensions

    /**
     * Sets up the schedule workers
     */
    public static void initialization(){
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new addChatListCount(),0l, 20l); //TODO make fix period time a variable retrieved from a config file
        scheduler.scheduleSyncRepeatingTask(plugin, new timeoutChatList(),0l, 25l); //TODO make fix period time a variable retrieved from a config file
        scheduler.scheduleSyncRepeatingTask(plugin, new ChatEffects(),0l, 5l); //TODO make fix period time a variable retrieved from a config file
    }
}

/**
 * Schedule worker adds to the counter of players in the chattingPlayers hashtable
 */
class addChatListCount implements Runnable{
    @Override
    public void run() {
        Set<String> keys = Database.getKeys();
        for(String key : keys){
            Database.addCount(key);
        }
    }
}

/**
 * Schedule worker removes players who are not chatting
 */
class timeoutChatList implements Runnable{
    private static final Plugin plugin = Chitchat.getPlugin();
    @Override
    public void run() {
        Set<String> keys = Database.getKeys();
        for(String key : keys){
            int count = Database.getCount(key);
            if(count >= 10){ //TODO make fix int a variable retrieved from a config file
                Database.removePlayer(key);
            }
        }
    }
}

/**
 * Schedule worker that adds effects to a player who it in the chattingPlayer hashtable
 */
class ChatEffects implements Runnable{
    private static final Plugin plugin = Chitchat.getPlugin();
    @Override
    public void run() {
        Set<String> keys = Database.getKeys();
        for(String key : keys) {
            Player player = plugin.getServer().getPlayer(key);
            if (player != null) {
                //player.sendMessage("Spam ;)");
                World world = player.getWorld();
                Location loc = player.getLocation();
                loc.add(0,3.0,0);
                world.spawnParticle(Particle.END_ROD, loc, 1, 0, 0 ,0 , 0);
            }
        }
    }
}
