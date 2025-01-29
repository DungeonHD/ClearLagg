package net.dungeonhd.clearLagg.managers;

import net.dungeonhd.clearLagg.ClearLagg;
import net.dungeonhd.clearLagg.utils.ColorUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class MessageManager {

    private final ClearLagg plugin;
    private File file;
    private FileConfiguration messages;

    public MessageManager(ClearLagg plugin) {
        this.plugin = plugin;
        setup();
    }

    /**
     * Sets up the messages.yml configuration file.
     */
    private void setup() {
        file = new File(plugin.getDataFolder(), "messages.yml");
        if (!file.exists()) {
            plugin.saveResource("messages.yml", false);
        }
        messages = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Fetches a message from the messages.yml file and processes placeholders.
     *
     * @param path the path to the message in the configuration file
     * @return the translated message with placeholders replaced
     */
    public String getMessage(String path) {
        if (messages.contains(path)) {
            String message = messages.getString(path);
            if (message == null) return "§cMessage not found: " + path;

            // Replace placeholders
            message = replacePlaceholders(message);
            return ColorUtil.translate(message);
        }
        return "§cMessage not found: " + path;
    }

    /**
     * Fetches a list of messages and processes placeholders.
     *
     * @param path the path to the message list in the configuration file
     * @return a list of translated messages with placeholders replaced
     */
    public List<String> getMessageList(String path) {
        if (messages.contains(path)) {
            return messages.getStringList(path).stream()
                    .map(this::replacePlaceholders)
                    .map(ColorUtil::translate)
                    .collect(Collectors.toList());
        }
        return List.of("§cList not found: " + path);
    }

    /**
     * Replaces placeholders such as %prefix% and %error_prefix%.
     *
     * @param message the raw message string
     * @return the message with placeholders replaced
     */
    private String replacePlaceholders(String message) {
        String prefix = messages.getString("Messages.GLOBAL.Prefix", "&7[&#ffc500ClearLagg&7]&#009bff ");
        String pluginversion = plugin.getDescription().getVersion();
        return message.replace("%prefix%", prefix).replace("%plugin_version%", pluginversion);
    }

    /**
     * Reloads the messages.yml configuration.
     */
    public void reloadMessages() {
        messages = YamlConfiguration.loadConfiguration(file);
    }
}
