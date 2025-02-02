package io.github.scroojalix.minigameapi;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.Plugin;

public class MinigameAPI {

    protected static Main instance;

    private static final Set<RegisteredMinigame> registeredMinigames = new HashSet<>();

    public static Set<RegisteredMinigame> getRegisteredMinigames() {
        return registeredMinigames;
    }

    public static void registerMinigame(String name, Minigame minigame, Plugin plugin) {
        if (!plugin.isEnabled())
            throw new IllegalPluginAccessException(
                    plugin.getName() + " attempted to register minigame " + name + " whilst not enabled");
        if (name.contains(" ")) {
            name = name.replace(" ", "");
            instance.getLogger().log(Level.WARNING, "Minigame names may not contain spaces. Defaulted to {0}", name);
        }
        if (getMinigame(name) != null)
            throw new MinigameException(name + " is already a registered minigame name.");

        registeredMinigames.add(new RegisteredMinigame(name, minigame, plugin));
    }

    public static void initMinigame(String registeredName) {
        RegisteredMinigame minigame = getMinigame(registeredName);
        if (minigame == null)
            throw new MinigameException(registeredName + " is not a valid minigame.");

        minigame.init();
    }

    public static void startMinigame(String registeredName) {
        RegisteredMinigame minigame = getMinigame(registeredName);
        if (minigame == null)
            throw new MinigameException(registeredName + " is not a valid minigame.");

        minigame.start();
    }

    public static String[] getMinigameNames() {
        return registeredMinigames.stream().map(RegisteredMinigame::getName).toArray(String[]::new);
    }

    public static @Nullable RegisteredMinigame getMinigame(@Nonnull String name) {
        Optional<RegisteredMinigame> matchingObject = registeredMinigames.stream().filter(m -> m.getName().equals(name))
                .findFirst();
        return matchingObject.orElse(null);
    }
}
