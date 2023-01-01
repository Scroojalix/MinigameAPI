package io.github.scroojalix.minigameapi;

import org.bukkit.plugin.Plugin;

public class RegisteredMinigame {
    
    private final String name;

    private final Minigame minigame;

    private final Plugin plugin;

    public RegisteredMinigame(String name, Minigame minigame, Plugin plugin) {
        this.name = name;
        this.minigame = minigame;
        this.plugin = plugin;
    }

    public String getName() {
        return this.name;
    }

    public Minigame getMinigame() {
        return this.minigame;
    }

    public Plugin getPlugin() {
        return this.plugin;
    }
}
