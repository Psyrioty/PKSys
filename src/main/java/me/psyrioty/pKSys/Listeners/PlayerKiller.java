package me.psyrioty.pKSys.Listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.psyrioty.pKSys.PKSys;
import org.bukkit.Bukkit;
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
            int rep = 0;
            int repPlayerDeath = 0;

            FileConfiguration defaultConfig = PKSys.getPlugin().defaultConfig;
            boolean onWhiteList = false;
            int i = 0;
            while(true){
                try{
                    int minX = defaultConfig.getInt("RgWhiteList." + i + ".minPos.x");
                    int minY = defaultConfig.getInt("RgWhiteList." + i + ".minPos.y");
                    int minZ = defaultConfig.getInt("RgWhiteList." + i + ".minPos.z");

                    int maxX = defaultConfig.getInt("RgWhiteList." + i + ".maxPos.x");
                    int maxY = defaultConfig.getInt("RgWhiteList." + i + ".maxPos.y");
                    int maxZ = defaultConfig.getInt("RgWhiteList." + i + ".maxPos.z");

                    if (minX == 0 && maxX == 0 && minY == 0 && maxY == 0 && minZ == 0 && maxZ == 0) {
                        break;
                    }

                    int playerX = playerKiller.getLocation().getBlockX();
                    int playerY = playerKiller.getLocation().getBlockY();
                    int playerZ = playerKiller.getLocation().getBlockZ();

                    if((minX <= playerX && minY <= playerY && minZ <= playerZ) && (playerX <= maxX && playerY <= maxY && playerZ <= maxZ)) {
                        onWhiteList = true;
                        //break;
                    }
                }catch (Exception exception){
                    break;
                }

                i++;
            }



            int oldRep = config.getInt(playerKiller.getName());
            repPlayerDeath = config.getInt(player.getName());
            if(!onWhiteList){
                if(repPlayerDeath >= 0) {
                    rep = oldRep - 10;
                }else
                    rep = oldRep + 10;
                config.set(playerKiller.getName(), rep);
                PKSys.getPlugin().saveCustomConfig();
                playerKiller.sendMessage(prefix("#B50778[PK]: #DDA0DDУ Вас изменилась репутация, за убийство "+ playerPlace + "#DDA0DD, до #B50778" + rep + "#DDA0DD."));
                if (oldRep >=0 && rep < 0) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "luckperms user " + playerKiller.getName() + " meta addsuffix 99 \" &4☠&f \"");
                } else if(oldRep < 0 && rep >= 0){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "luckperms user " + playerKiller.getName() + " meta removesuffix 99");
                }
            }
        }else {
            String playerPlace = prefix("%luckperms_prefix%%player_name%");
            playerPlace = PlaceholderAPI.setPlaceholders(player, playerPlace);
            e.setDeathMessage(prefix(playerPlace + "&#DDA0DD отлетел в мир иной!"));
        }
    }
}
