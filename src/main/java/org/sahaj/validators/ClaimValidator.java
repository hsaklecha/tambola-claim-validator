package org.sahaj.validators;

import org.sahaj.AnnouncedNumbers;
import org.sahaj.GameType;

public class ClaimValidator {
    private GameType gameType;
    private GameValidator gameValidator;
    private AnnouncedNumbers announcedNumbers;

    public ClaimValidator(GameType gameType, GameValidator gameValidator, AnnouncedNumbers announcedNumbers) {
        this.gameType = gameType;
        this.gameValidator = gameValidator;
        this.announcedNumbers = announcedNumbers;
    }

    public String validateClaim() {
        // Check if the claim is valid based on the game type and last announced number
        if (gameValidator.isWinningClaim() && gameValidator.isValidLastNumber(announcedNumbers.getLastAnnouncedNumber())) {
            return "Accepted";
        }
        return "Rejected";
    }
}

