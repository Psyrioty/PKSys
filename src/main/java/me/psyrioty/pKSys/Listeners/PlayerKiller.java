package me.psyrioty.pKSys.Listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.psyrioty.pKSys.PKSys;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static me.psyrioty.pKSys.Listeners.HEX.prefix;

public class PlayerKiller implements Listener {
    @EventHandler
    private void kill(PlayerDeathEvent e){
        Player player = e.getEntity().getPlayer();
        Player playerKiller = e.getEntity().getKiller();
        if (playerKiller!=null) {
            String playerPlace = prefix("%luckperms_prefix%%player_name%");
            playerPlace = PlaceholderAPI.setPlaceholders(player, playerPlace);

            String killerPlace = prefix("%luckperms_prefix%%player_name%");
            killerPlace = PlaceholderAPI.setPlaceholders(playerKiller, killerPlace);
            e.setDeathMessage(prefix(playerPlace + "&#DDA0DD отлетел в мир иной, от руки " + killerPlace + "&#DDA0DD!"));

            FileConfiguration config = PKSys.getPlugin().customConfig;
            var rep = 0;
            var repPlayerDeath = 0;

            rep = config.getInt(playerKiller.getName());
            repPlayerDeath = config.getInt(player.getName());
            if(repPlayerDeath >= 0) {
                rep -= 10;
            }else
                rep += 10;
            config.set(playerKiller.getName(), rep);
            PKSys.getPlugin().saveCustomConfig();

            playerKiller.sendMessage(prefix("&#B50778У Вас изменилась репутация, за убийство "+ playerPlace + "&#B50778, до " + rep + "."));
        }else {
            String playerPlace = prefix("%luckperms_prefix%%player_name%");
            playerPlace = PlaceholderAPI.setPlaceholders(player, playerPlace);
            e.setDeathMessage(prefix(playerPlace + "&#DDA0DD отлетел в мир иной!"));
        }
    }
}
