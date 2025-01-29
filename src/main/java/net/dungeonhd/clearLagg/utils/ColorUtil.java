package net.dungeonhd.clearLagg.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtil {

    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");

    public static String translate(String text) {
        Matcher matcher = HEX_PATTERN.matcher(text);
        while (matcher.find()) {
            String hexCode = matcher.group(1);
            text = text.replace("&#" + hexCode, ChatColor.of("#" + hexCode).toString());
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
