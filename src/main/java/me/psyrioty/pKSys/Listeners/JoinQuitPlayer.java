package me.psyrioty.pKSys.Listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.psyrioty.pKSys.PKSys;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.psyrioty.pKSys.Listeners.HEX.prefix;

public class JoinQuitPlayer implements Listener {
    @EventHandler
    private void JoinServer(PlayerJoinEvent e){
        FileConfiguration defaultConfig = PKSys.getPlugin().defaultConfig;

        Player player = e.getPlayer();
        String playerPlace = "%luckperms_prefix%%player_name%";
        playerPlace = PlaceholderAPI.setPlaceholders(player, playerPlace);
        e.setJoinMessage(prefix(playerPlace + defaultConfig.getString("MessageLogin")));
    }

    @EventHandler
    private void QuitServer(PlayerQuitEvent e){
        FileConfiguration defaultConfig = PKSys.getPlugin().defaultConfig;

        Player player = e.getPlayer();
        String playerPlace = "%luckperms_prefix%%player_name%";
        playerPlace = PlaceholderAPI.setPlaceholders(player, playerPlace);
        e.setQuitMessage(prefix(playerPlace + defaultConfig.getString("MessageLogout")));
    }
}
