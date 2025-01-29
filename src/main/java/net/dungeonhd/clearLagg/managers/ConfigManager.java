package net.dungeonhd.clearLagg.managers;

import net.dungeonhd.clearLagg.ClearLagg;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private final ClearLagg plugin;

    public ConfigManager(ClearLagg plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig(); // Erstellt die config.yml falls nicht vorhanden
    }

    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    public void reloadConfig() {
        plugin.reloadConfig();
    }
}
