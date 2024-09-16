package org.sahaj.validators;

import org.sahaj.AnnouncedNumbers;
import org.sahaj.Ticket;

public class MiddleRowValidator implements GameValidator {
    private AnnouncedNumbers announcedNumbers;
    private Ticket ticket;

    public MiddleRowValidator(AnnouncedNumbers announcedNumbers, Ticket ticket) {
        this.announcedNumbers = announcedNumbers;
        this.ticket = ticket;
    }

    @Override
    public boolean isWinningClaim() {
        int[] middleRow = ticket.getRow(1);
        for (int number : middleRow) {
            if (number != 0 && !announcedNumbers.isNumberAnnounced(number)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isValidLastNumber(int lastAnnouncedNumber) {
        int[] middleRow = ticket.getRow(1);
        for (int number : middleRow) {
            if (number == lastAnnouncedNumber) {
                return true;
            }
        }
        return false;
    }
}

