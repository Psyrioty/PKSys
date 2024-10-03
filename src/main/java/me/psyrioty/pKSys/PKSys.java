package me.psyrioty.pKSys;

import me.psyrioty.pKSys.Listeners.AutoMessage;
import me.psyrioty.pKSys.Listeners.JoinQuitPlayer;
import me.psyrioty.pKSys.Listeners.PlayerKiller;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;


import org.bukkit.plugin.java.JavaPlugin;



import java.io.File;


public final class PKSys extends JavaPlugin {
    static PKSys plugin;

    File customFile;
    File defaultFile;
    public FileConfiguration customConfig;
    public FileConfiguration defaultConfig;

    @Override
    public void onEnable() {
        plugin = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        defaultFile = new File(getDataFolder(), "config.yml");
        defaultConfig = YamlConfiguration.loadConfiguration(defaultFile);

        customFile = new File(getDataFolder(), "rep.yml");
        customConfig = YamlConfiguration.loadConfiguration(customFile);

        saveCustomConfig();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerKiller(), this);
        if(defaultConfig.getBoolean("CustomMessageLoginLogout")){
            pm.registerEvents(new JoinQuitPlayer(), this);
        }

        if(defaultConfig.getBoolean("AutoMessageEnable")){
            int AutoMessageTime = defaultConfig.getInt("time");
            Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin)this, (Runnable)new AutoMessage(), 20L, 20L * AutoMessageTime);
        }

        new Expansion(this).register();
    }

    public void saveCustomConfig(){
        try{
            customConfig.save(customFile);
        }catch (Exception e){}
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static PKSys getPlugin() {
        return plugin;
    }
}
