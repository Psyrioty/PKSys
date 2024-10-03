package me.psyrioty.pKSys.Listeners;

import me.psyrioty.pKSys.PKSys;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static me.psyrioty.pKSys.Listeners.HEX.prefix;

public class AutoMessage implements Runnable {
    int i = 0;
    public void run() {
        FileConfiguration defaultConfig = PKSys.getPlugin().defaultConfig;
        String messageText = defaultConfig.getString("AutoMessages." + i + ".text");
        i++;

        for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (messageText != null) {
                p.sendMessage(prefix(messageText));
            }else{
                i = 0;
                break;
            }
        }
    }
}
