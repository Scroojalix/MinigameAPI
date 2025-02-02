package io.github.scroojalix.minigameapi.countdown;

public interface CountdownInterfacer {

    /**
     * Called each tick of countdown
     */
    public default void tick() {}

    /**
     * Called after countdown is complete
     */
    public void callback();
    
}
