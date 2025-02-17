package io.github.scroojalix.minigameapi.countdown;

import org.bukkit.Sound;

public class CountdownStyle {
    // TODO add extra effects such as sfx, action bar, xp bar etc.

    private String titleFormat;
    private String subtitleFormat;
    
    int fadeIn, stay, fadeOut, wait;
    
    private SoundInfo soundInfo;
    private CountdownStyle finalTickStyle;
    private CountdownStyle earlyCancelStyle;

    public CountdownStyle() {
        this(null, null, 0, 0, 0, 0, null, null);
    }

    public CountdownStyle(String titleFormat, String subtitleFormat, int fadeIn, int stay, int fadeOut) {
        this(titleFormat, subtitleFormat, fadeIn, stay, fadeOut, 0, null, null);
    }

    public CountdownStyle(String titleFormat, String subtitleFormat, int fadeIn, int stay, int fadeOut, int wait) {
        this(titleFormat, subtitleFormat, fadeIn, stay, fadeOut, 0, null, null);
    }

    public CountdownStyle(String titleFormat, String subtitleFormat, int fadeIn, int stay, int fadeOut, CountdownStyle earlyCancelStyle) {
        this(titleFormat, subtitleFormat, fadeIn, stay, fadeOut, 0, earlyCancelStyle, null);
    }

    public CountdownStyle(String titleFormat, String subtitleFormat, int fadeIn, int stay, int fadeOut, int wait, CountdownStyle earlyCancelStyle, CountdownStyle finalTickStyle) {
        this.titleFormat = titleFormat;
        this.subtitleFormat = subtitleFormat;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
        this.wait = wait;
        this.earlyCancelStyle = earlyCancelStyle;
        this.finalTickStyle = finalTickStyle;
    }

    public String getTitleFormat() {
        return titleFormat;
    }

    public void setTitleFormat(String titleFormat) {
        this.titleFormat = titleFormat;
    }

    public String getSubtitleFormat() {
        return subtitleFormat;
    }

    public void setSubtitleFormat(String subtitleFormat) {
        this.subtitleFormat = subtitleFormat;
    }

    public void setFadeInLength(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    public void setStayLength(int stay) {
        this.stay = stay;
    }

    public void setFadeOutLength(int fadeOut) {
        this.fadeOut = fadeOut;
    }

    public void setWaitLength(int wait) {
        this.wait = wait;
    }

    public int getWaitLength() {
        return this.wait;
    }

    public int getTotalTickLength() {
        return fadeIn + stay + fadeOut;
    }

    public SoundInfo getSoundInfo() {
        return this.soundInfo;
    }

    public void setSoundInfo(Sound sound, float volume, float pitch) {
        if (sound != null)
            this.soundInfo = new SoundInfo(sound, volume, pitch);
    }

    public CountdownStyle getEarlyCancelStyle() {
        return this.earlyCancelStyle;
    }

    public void setEarlyCancelStyle(CountdownStyle earlyCancelStyle) {
        this.earlyCancelStyle = earlyCancelStyle;
    }

    public CountdownStyle getFinalTickStyle() {
        return this.finalTickStyle;
    }

    public void setFinalTickStyle(CountdownStyle finalTickStyle) {
        this.finalTickStyle = finalTickStyle;
    }

    public class SoundInfo {
        public final Sound sound;
        public final float volume, pitch;

        public SoundInfo(Sound sound, float volume, float pitch) {
            this.sound = sound;
            this.volume = volume;
            this.pitch = pitch;
        }
    }
}
