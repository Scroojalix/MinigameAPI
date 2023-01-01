package io.github.scroojalix.minigameapi;

public abstract class Minigame {

    private boolean running = false;

    public void start() {
        handleStart();
        this.running = true;
    }

    public void end() {
        this.running = false;
        handleEnd();
    }

    public boolean isRunning() {
        return this.running;
    }
    
    public abstract void handleStart();

    public abstract void handleEnd();
    
}
