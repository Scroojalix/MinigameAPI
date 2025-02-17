package io.github.scroojalix.minigameapi.countdown;

import org.bukkit.ChatColor;
import org.bukkit.Sound;

public class CountdownStyleBuilder {

    private final CountdownStyle style;

    public CountdownStyleBuilder() {
        this.style = new CountdownStyle();
    }

    public CountdownStyleBuilder(CountdownStyle style) {
        this.style = style;
    }

    public static CountdownStyleBuilder getDefaults() {
        return new CountdownStyleBuilder(new CountdownStyle(ChatColor.GOLD + "Countdown ends in", ChatColor.WHITE + "%count%", 0, 20, 0));
    }

    public CountdownStyle create() {
        return style;
    }

    public CountdownStyleBuilder setTitleFormat(String titleFormat) {
        style.setTitleFormat(titleFormat);
        return this;
    }

    public CountdownStyleBuilder setSubtitleFormat(String subtitleFormat) {
        style.setSubtitleFormat(subtitleFormat);
        return this;
    }
    
    public CountdownStyleBuilder setFadein(int fadeIn) {
        style.setFadeInLength(fadeIn);
        return this;
    }
    
    public CountdownStyleBuilder setStay(int stay) {
        style.setStayLength(stay);
        return this;
    }
    
    public CountdownStyleBuilder setFadeout(int fadeout) {
        style.setFadeOutLength(fadeout);
        return this;
    }

    public CountdownStyleBuilder setWait(int wait) {
        style.setWaitLength(wait);
        return this;
    }

    public CountdownStyleBuilder setSoundInfo(Sound sound, float volume, float pitch) {
        style.setSoundInfo(sound, volume, pitch);
        return this;
    }

    public CountdownStyleBuilder setEarlyCancelStyle(CountdownStyle earlyCancelStyle) {
        style.setEarlyCancelStyle(earlyCancelStyle);
        return this;
    }

    public CountdownStyleBuilder setFinalTickStyle(CountdownStyle finalTickStyle) {
        style.setFinalTickStyle(finalTickStyle);
        return this;
    }
    
}
