package net.dungeonhd.clearLagg.commands;

import net.dungeonhd.clearLagg.ClearLagg;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class ClearLaggCommand implements CommandExecutor, TabCompleter {

    private ClearLagg plugin;

    public ClearLaggCommand(ClearLagg plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("clearlagg.command.clearlagg") || sender.isOp()) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    List<String> PluginhelpMessages = plugin.getMessageManager().getMessageList("Messages.CHAT.PluginHelp");
                    for (String line : PluginhelpMessages) {
                        sender.sendMessage(line);
                    }

                    // Wenn der Command von einem Spieler ausgeführt wurde, wird bei dem Spieler ein Sound abgespielt.
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 2.0f);
                    } else {
                        // Gibt nichts zurück, wenn der sender kein Spieler ist.
                    }
                } else if (args[0].equalsIgnoreCase("reload") && (sender.hasPermission("clearlagg.command.reload") || sender.isOp())) {
                    plugin.getConfigManager().reloadConfig();
                    plugin.getMessageManager().reloadMessages();
                    sender.sendMessage(plugin.getMessageManager().getMessage("Messages.GLOBAL.Reload"));

                    // Wenn der Command von einem Spieler ausgeführt wurde, wird bei dem Spieler ein Sound abgespielt.
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 2.0f);
                    } else {
                        // Gibt nichts zurück, wenn der sender kein Spieler ist.
                    }
                } else {
                    sender.sendMessage(plugin.getMessageManager().getMessage("Messages.GLOBAL.Syntax"));
                }
            } else {
                sender.sendMessage(plugin.getMessageManager().getMessage("Messages.GLOBAL.Syntax"));
            }
        } else {
            sender.sendMessage(plugin.getMessageManager().getMessage("Messages.GLOBAL.NoPermissions"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return List.of("help", "reload");
    }
}
