package org.sahaj.validators;

public interface GameValidator {
    boolean isWinningClaim();
    boolean isValidLastNumber(int lastAnnouncedNumber);
}

