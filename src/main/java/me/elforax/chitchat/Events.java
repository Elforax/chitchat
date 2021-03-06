package me.elforax.chitchat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

/**
 * Events class handles Server events that effect the ChitChat plugin
 */
public class Events implements Listener {
    private static final Plugin plugin = Chitchat.getPlugin(); //!< Create a reference to the main class for use with JavaPlugin extensions

    /**
     * everytime a chat message is send and it doesnt start with '/' the plugin will
     * add the player who send the message to the ChattingPlayers list to indicate they are speaking
     * @param event
     */
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){     //TODO Add reset time when chat is send
        char[] msg = event.getMessage().toCharArray();

        if(!(msg[0] == '/' )){
            Player player = event.getPlayer();
            ChatDatabase.addPlayer(player.getName());
        }
    }

    /**
     * When a player leaves the server they will be removed from the ChattingPlayers list
     * if they are on the list
     * @param event
     */
    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        ChatDatabase.removePlayer(player.getName());
    }

    /**
     * When a player moves and they are in the hashtable remove them from the table
     * @param event
     */
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(ChatDatabase.inList(player.getName()) && ConfigData.disableOnMove) {

            double dX = Math.abs(event.getFrom().getX() - event.getTo().getX());
            double dY = Math.abs(event.getFrom().getY() - event.getTo().getY());
            double dZ = Math.abs(event.getFrom().getZ() - event.getTo().getZ());
            double exitChat = 0.1;

            // debug console msg
            //plugin.getLogger().info(ChatColor.RED + player.getName() + "-> dX:" + dX + " dY:" + dY + " dZ:" + dZ);

            if (dX >= exitChat || dY >= exitChat || dZ >= exitChat) {
                ChatDatabase.removePlayer(player.getName());
            }
        }
    }
}
