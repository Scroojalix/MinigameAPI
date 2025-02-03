package io.github.scroojalix.minigameapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.scroojalix.minigameapi.countdown.CountdownAPI;

public class Main extends JavaPlugin {

    @Override
    public void onLoad() {
        MinigameAPI.instance = this;
    }

    @Override
    public void onEnable() {
        try {
            Class.forName("me.clip.placeholderapi.PlaceholderAPI");
            CountdownAPI.PLACEHOLDER_API_ENABLED = true;
            this.getLogger().info("PlaceholderAPI located.");
        } catch (ClassNotFoundException e) {
            CountdownAPI.PLACEHOLDER_API_ENABLED = false;
            this.getLogger().warning("PlaceholderAPI not installed");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getLabel().equalsIgnoreCase("listminigames")) {
            String[] minigameNames = MinigameAPI.getMinigameNames();
            sender.sendMessage(minigameNames);
        }
        else if (command.getLabel().equalsIgnoreCase("startminigame")) {
            if (args.length > 0) {
                RegisteredMinigame registeredMinigame = MinigameAPI.getMinigame(args[0]);
                if (registeredMinigame != null) {
                    if (!registeredMinigame.isRunning()) {
                        registeredMinigame.start();
                    } else {
                        registeredMinigame.end();
                    }
                } else {
                    sender.sendMessage(ChatColor.RED+"That minigame does not exist.");
                }
            }
            
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("startminigame")) {
            if (args.length == 1) {
                return Arrays.asList(MinigameAPI.getMinigameNames());
            }
        }
        return new ArrayList<>();
    }
}
