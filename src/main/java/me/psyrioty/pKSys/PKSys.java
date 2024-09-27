package me.psyrioty.pKSys;

import me.psyrioty.pKSys.Listeners.PlayerKiller;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;

import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;


public final class PKSys extends JavaPlugin {
    static PKSys plugin;

    File customFile;
    public FileConfiguration customConfig;

    @Override
    public void onEnable() {
        plugin = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        customFile = new File(getDataFolder(), "rep.yml");
        customConfig = YamlConfiguration.loadConfiguration(customFile);

        saveCustomConfig();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerKiller(), this);

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
