package io.github.scroojalix.minigameapi;

import org.bukkit.plugin.Plugin;

public class RegisteredMinigame {
    
    private final String name;
    private final Minigame minigame;
    private final Plugin plugin;

    private boolean initialised = false;
    private boolean running = false;

    public RegisteredMinigame(String name, Minigame minigame, Plugin plugin) {
        this.name = name;
        this.minigame = minigame;
        this.plugin = plugin;
    }

    public void init() {
        if (initialised) throw new RuntimeException("Attempted to initialise a minigame that is already initialised.");
        minigame.init();
        initialised = true;
    }
    
    public void start() {
        if (!initialised) throw new RuntimeException("Attempted to start a minigame that has not been initialised.");
        this.running = true;
        minigame.start();
    }
    
    public void end() {
        if (!running) throw new RuntimeException("Attempted to end a minigame that is not running.");
        this.running = false;
        minigame.end();
    }

    public boolean isRunning() {
        return this.running;
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
