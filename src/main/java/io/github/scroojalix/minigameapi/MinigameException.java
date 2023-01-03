package io.github.scroojalix.minigameapi;

/**
 * Thrown to indicate that something has gone wrong involving minigames in MinigameAPI.
 */
public class MinigameException extends RuntimeException {

    public MinigameException() {
        super();
    }

    public MinigameException(String s) {
        super(s);
    }
}
