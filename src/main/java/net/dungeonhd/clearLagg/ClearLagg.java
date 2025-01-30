package net.dungeonhd.clearLagg;

import net.dungeonhd.clearLagg.commands.ClearLaggCommand;
import net.dungeonhd.clearLagg.managers.ConfigManager;
import net.dungeonhd.clearLagg.managers.MessageManager;
import net.dungeonhd.clearLagg.tasks.ClearTask;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClearLagg extends JavaPlugin {

    private static ClearLagg instance;
    private ConfigManager configManager;
    private MessageManager messageManager;

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager(this);
        messageManager = new MessageManager(this);

        new ClearTask(this).runTaskTimer(this, 20, 20);

        getCommand("clearlagg").setExecutor(new ClearLaggCommand(this));
        getCommand("clearlagg").setTabCompleter(new ClearLaggCommand(this));
    }

    @Override
    public void onDisable() {
    }

    public static ClearLagg getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}
