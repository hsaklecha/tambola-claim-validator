package org.sahaj.validators;

import org.sahaj.AnnouncedNumbers;
import org.sahaj.Ticket;

public class EarlyFiveValidator implements GameValidator {
    private AnnouncedNumbers announcedNumbers;
    private Ticket ticket;

    public EarlyFiveValidator(AnnouncedNumbers announcedNumbers, Ticket ticket) {
        this.announcedNumbers = announcedNumbers;
        this.ticket = ticket;
    }

    @Override
    public boolean isWinningClaim() {
        int crossedNumbers = 0;
        for (int[] row : ticket.getNumbers()) {
            for (int number : row) {
                if (number != 0 && announcedNumbers.isNumberAnnounced(number)) {
                    crossedNumbers++;
                    if (crossedNumbers >= 5) {
                        return true;
                    }
                }
            }
        }
        return crossedNumbers >= 5;
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

