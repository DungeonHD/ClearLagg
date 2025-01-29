package net.dungeonhd.clearLagg.tasks;

import net.dungeonhd.clearLagg.ClearLagg;
import net.dungeonhd.clearLagg.managers.MessageManager;
import net.dungeonhd.clearLagg.utils.TimeFormatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class ClearTask extends BukkitRunnable {

    private final ClearLagg plugin;
    private final MessageManager messageManager;
    private final TimeFormatter timeFormatter;
    private final int clearInterval;
    private int timeLeft;

    public ClearTask(ClearLagg plugin) {
        this.plugin = plugin;
        this.messageManager = plugin.getMessageManager();
        this.timeFormatter = new TimeFormatter(plugin.getConfigManager());
        this.clearInterval = plugin.getConfig().getInt("clearInterval", 600);
        this.timeLeft = clearInterval;
    }

    @Override
    public void run() {
        if (timeLeft <= 0) {
            int removedItems = clearGroundItems();
            Bukkit.broadcastMessage(messageManager.getMessage("Messages.CHAT.ClearLaggCleared")
                    .replace("%items%", String.valueOf(removedItems)));
            timeLeft = clearInterval;
        } else {
            sendWarningMessage(timeLeft);
        }
        timeLeft--;
    }

    private int clearGroundItems() {
        int count = 0;
        for (World world : Bukkit.getWorlds()) {
            for (Item item : world.getEntitiesByClass(Item.class)) {
                item.remove();
                count++;
            }
        }
        return count;
    }

    private void sendWarningMessage(int seconds) {
        List<Integer> warningTimes = List.of(300, 60, 30, 10, 5, 4, 3, 2, 1);
        if (warningTimes.contains(seconds)) {
            String timeString = timeFormatter.formatTime(seconds);
            Bukkit.broadcastMessage(messageManager.getMessage("Messages.CHAT.ClearLaggWarning")
                    .replace("%time%", timeString));
        }
    }
}
