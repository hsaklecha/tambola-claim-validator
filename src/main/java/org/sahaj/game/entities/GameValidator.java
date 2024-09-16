package org.sahaj.game.entities;

public interface GameValidator {
    boolean isWinningClaim();
    boolean isValidLastNumber(int lastAnnouncedNumber);
}

