package io.github.scroojalix.minigameapi.utils;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;

public final class PAPILinker {

    public static String setPlaceholders(Player p, String text) {
        return PlaceholderAPI.setPlaceholders(p, text);
    }
}
