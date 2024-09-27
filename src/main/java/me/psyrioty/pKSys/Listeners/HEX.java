package me.psyrioty.pKSys.Listeners;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HEX {
    public static String prefix(String from) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(from);
        while (matcher.find()) {
            String hexCode = from.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');
            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder("");
            for (char c : ch)
                builder.append("&" + c);
            from = from.replace(hexCode, builder.toString());
            matcher = pattern.matcher(from);
        }
        String pref = ChatColor.translateAlternateColorCodes('&', from);
        StringBuilder builder = new StringBuilder("");
        char[] ch = pref.toCharArray();
        for(char c : ch){
            if(c != '&'){
                builder.append(c);
            }
        }

        return builder.toString();
    }
}
