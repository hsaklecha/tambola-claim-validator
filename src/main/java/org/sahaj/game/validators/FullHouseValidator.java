package org.sahaj.game.validators;

import org.sahaj.game.entities.AnnouncedNumbers;
import org.sahaj.game.entities.GameValidator;
import org.sahaj.game.entities.Ticket;

public class FullHouseValidator implements GameValidator {
    private AnnouncedNumbers announcedNumbers;
    private Ticket ticket;

    public FullHouseValidator(AnnouncedNumbers announcedNumbers, Ticket ticket) {
        this.announcedNumbers = announcedNumbers;
        this.ticket = ticket;
    }

    @Override
    public boolean isWinningClaim() {
        for (int[] row : ticket.getNumbers()) {
            for (int number : row) {
                if (number != 0 && !announcedNumbers.isNumberAnnounced(number)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isValidLastNumber(int lastAnnouncedNumber) {
        for (int[] row : ticket.getNumbers()) {
            for (int number : row) {
                if (number == lastAnnouncedNumber) {
                    return true;
                }
            }
        }
        return false;
    }
}

