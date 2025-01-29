package net.dungeonhd.clearLagg.utils;

import net.dungeonhd.clearLagg.managers.ConfigManager;

public class TimeFormatter {

    private final ConfigManager configManager;

    public TimeFormatter(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public String formatTime(int seconds) {
        int minutes = seconds / 60;
        int hours = minutes / 60;
        int days = hours / 24;
        int weeks = days / 7;
        int months = weeks / 4;
        int years = months / 12;

        if (years > 1) return years + " " + configManager.getConfig().getString("timeFormat.Years");
        if (years == 1) return years + " " + configManager.getConfig().getString("timeFormat.Year");
        if (months > 1) return months + " " + configManager.getConfig().getString("timeFormat.Months");
        if (months == 1) return months + " " + configManager.getConfig().getString("timeFormat.Month");
        if (weeks > 1) return weeks + " " + configManager.getConfig().getString("timeFormat.Weeks");
        if (weeks == 1) return weeks + " " + configManager.getConfig().getString("timeFormat.Week");
        if (days > 1) return days + " " + configManager.getConfig().getString("timeFormat.Days");
        if (days == 1) return days + " " + configManager.getConfig().getString("timeFormat.Day");
        if (hours > 1) return hours + " " + configManager.getConfig().getString("timeFormat.Hours");
        if (hours == 1) return hours + " " + configManager.getConfig().getString("timeFormat.Hour");
        if (minutes > 1) return minutes + " " + configManager.getConfig().getString("timeFormat.Minutes");
        if (minutes == 1) return minutes + " " + configManager.getConfig().getString("timeFormat.Minute");
        if (seconds > 1) return seconds + " " + configManager.getConfig().getString("timeFormat.Seconds");
        return seconds + " " + configManager.getConfig().getString("timeFormat.Second");
    }
}
