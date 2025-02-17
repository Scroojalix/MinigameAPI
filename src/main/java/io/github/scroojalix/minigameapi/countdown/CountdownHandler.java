package io.github.scroojalix.minigameapi.countdown;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import io.github.scroojalix.minigameapi.utils.PAPILinker;

public class CountdownHandler implements Runnable {

    private int taskId;
    private int count;
    private CountdownStyle style;
    private CountdownInterfacer interfacer;
    
    public CountdownHandler(int countdownLength, CountdownInterfacer interfacer) {
        this(countdownLength, CountdownStyleBuilder.getDefaults().create(), interfacer);
    }
    
    public CountdownHandler(int countdownLength, CountdownStyle style, CountdownInterfacer interfacer) {
        this.count = countdownLength;
        this.style = style;
        this.interfacer = interfacer;
    }
    
    /** Called each second of the countdown */
    void tick(CountdownStyle style) {
        if (style.getTotalTickLength() <= 0) {
            Bukkit.getLogger().warning("A countdown with no tick length attempted to be called. Consider adjusting the countdowns fadeIn, stay and fadeOut values");
        }
        interfacer.tick();

        CountdownStyle.SoundInfo soundInfo = style.getSoundInfo();
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(formatCountdown(p, style.getTitleFormat()), formatCountdown(p, style.getSubtitleFormat()), style.fadeIn, style.stay, style.fadeOut);
            
            if (soundInfo != null)
                p.playSound(p.getLocation(), soundInfo.sound, soundInfo.volume, soundInfo.pitch);
        }
    }
    
    int start(Plugin plugin) {
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0, style.getTotalTickLength() + style.getWaitLength());
        CountdownAPI.runningCountdowns.put(taskId, this);
        return taskId;
    }
    
    @Override
    public void run() {
        if (count != 0) {
            tick(style);
        } else {
            if (style.getFinalTickStyle() != null) {
                tick(style.getFinalTickStyle());
            }
            removeCountdownTask();
            interfacer.callback();
        }
        count--;
    }

    /** Call to cancel the countdown */
    public void cancel() {
        if (style.getEarlyCancelStyle() != null) {
            tick(style.getEarlyCancelStyle());
        }
        removeCountdownTask();
    }

    private void removeCountdownTask() {

        Bukkit.getScheduler().cancelTask(taskId);
        CountdownAPI.runningCountdowns.remove(taskId);
    }

    /** Get current count */
    public int getCount() {
        return count;
    }

    private String formatCountdown(Player reciever, String in) {
        if (in == null)
            return null;
        String out = in.replace("%count%", getCount()+"");
        
        if (CountdownAPI.PLACEHOLDER_API_ENABLED) {
            out = PAPILinker.setPlaceholders(reciever, out);
        }

        return ChatColor.translateAlternateColorCodes('&', out);
    }
}