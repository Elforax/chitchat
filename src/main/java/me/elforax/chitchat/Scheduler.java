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
    private static final Plugin plugin = Chitchat.getPlugin(); //!< Create a reference to the main class for use with JavaPlugin extensions
    private static BukkitScheduler scheduler = plugin.getServer().getScheduler();

    /**
     * Sets up the schedule workers
     */
    public static void initialization(){
        scheduler.scheduleSyncRepeatingTask(plugin, new addChatListCount(),0L, ConfigData.period);
        scheduler.scheduleSyncRepeatingTask(plugin, new timeoutChatList(),0L, ConfigData.period);
        scheduler.scheduleSyncRepeatingTask(plugin, new ChatEffects(),0L, ConfigData.effect_period);
    }
}

/**
 * Schedule worker adds to the counter of players in the chattingPlayers hashtable
 */
class addChatListCount implements Runnable{
    @Override
    public void run() {
        Set<String> keys = ChatDatabase.getKeys();
        for(String key : keys){
            ChatDatabase.addCount(key);
        }
    }
}

/**
 * Schedule worker removes players who are not chatting
 */
class timeoutChatList implements Runnable{
    private static final Plugin plugin = Chitchat.getPlugin();

    //!< Calculates the repeat times based on the scheduler period and the idle time from config { 200 idle and 20 period = 10 repeats }
    private static final int idle_repeat = (int)Math.ceil((double)ConfigData.idle_time/(double)ConfigData.period);

    @Override
    public void run() {
        Set<String> keys = ChatDatabase.getKeys();
        for(String key : keys){
            int count = ChatDatabase.getCount(key);
            if(count >= idle_repeat){
                ChatDatabase.removePlayer(key);
            }
        }
    }
}

/**
 * Schedule worker that adds effects to a player who it in the chattingPlayer hashtable
 */
class ChatEffects implements Runnable{
    private static final Plugin plugin = Chitchat.getPlugin();

    private static String getChatEffect(String particle){
        if (particle != null) {
            particle = particle.toUpperCase();
            for (Particle p : Particle.values()) {
                if (p.name().equals(particle));
                return p.name();
            }
        }
        plugin.getLogger().warning("config: chat effect value not valid using default instead");
        return "END_ROD";
    }

    @Override
    public void run() {
        Set<String> keys = ChatDatabase.getKeys();
        for(String key : keys) {
            Player player = plugin.getServer().getPlayer(key);
            if (player != null) {
                World world = player.getWorld();
                Location loc = player.getLocation();

                loc.add(0,ConfigData.particleOffsetY,0);

                world.spawnParticle(Particle.END_ROD , loc, 1, 0, 0 ,0 , 0);
            }
        }
    }
}
