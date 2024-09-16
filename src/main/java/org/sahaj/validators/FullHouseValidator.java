package org.sahaj.validators;

import org.sahaj.AnnouncedNumbers;
import org.sahaj.Ticket;

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

