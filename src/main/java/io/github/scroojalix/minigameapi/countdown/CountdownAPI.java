package io.github.scroojalix.minigameapi.countdown;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.Plugin;

public final class CountdownAPI {

    static Map<Integer, CountdownHandler> runningCountdowns = new HashMap<>();

    // TODO find better place for this
    public static boolean PLACEHOLDER_API_ENABLED = true;

    public static int startCountdown(Plugin plugin, int length) {
        return startCountdown(plugin, length, CountdownStyleBuilder.getDefaults().create(), () -> {});
    }

    public static int startCountdown(Plugin plugin, int length, CountdownStyle style) {
        return startCountdown(plugin, length, style, () -> {});
    }

    public static int startCountdown(Plugin plugin, int length, CountdownStyle style, CountdownInterfacer interfacer) {
        CountdownHandler task = new CountdownHandler(length, style, interfacer);
        return task.start(plugin);
    }

    public static void cancelCountdown(int id) {
        if (runningCountdowns.containsKey(id))
            runningCountdowns.get(id).cancel();
        }
        
    public static void cancelAllCountdowns() {
        for (int id : runningCountdowns.keySet()) {
            runningCountdowns.get(id).cancel();
            
        }
    }
    
}
