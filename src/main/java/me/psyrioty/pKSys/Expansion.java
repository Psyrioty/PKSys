package me.psyrioty.pKSys;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import static me.psyrioty.pKSys.Listeners.HEX.prefix;

public class Expansion extends PlaceholderExpansion {

    private final PKSys plugin; //

    public Expansion(PKSys plugin) {
        this.plugin = plugin;
    }

    @Override
    @NotNull
    public String getAuthor() {
        return String.join(", ", plugin.getDescription().getAuthors()); //
    }

    @Override
    @NotNull
    public String getIdentifier() {
        return "pksys";
    }

    @Override
    @NotNull
    public String getVersion() {
        return plugin.getDescription().getVersion(); //
    }

    @Override
    public boolean persist() {
        return true; //
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equalsIgnoreCase("rep")) {
            int rep = plugin.customConfig.getInt(player.getName());
            if (rep < 0){
                return prefix("&#8B0000" + rep);
            }else{
                return prefix("&#2D00FF" + rep);
            }
        }

        return null; //
    }
}
