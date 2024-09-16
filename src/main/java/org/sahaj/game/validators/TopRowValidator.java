package org.sahaj.game.validators;

import org.sahaj.game.entities.AnnouncedNumbers;
import org.sahaj.game.entities.GameValidator;
import org.sahaj.game.entities.Ticket;

public class TopRowValidator implements GameValidator {
    private AnnouncedNumbers announcedNumbers;
    private Ticket ticket;

    public TopRowValidator(AnnouncedNumbers announcedNumbers, Ticket ticket) {
        this.announcedNumbers = announcedNumbers;
        this.ticket = ticket;
    }

    @Override
    public boolean isWinningClaim() {
        int[] topRow = ticket.getRow(0);
        for (int number : topRow) {
            if (number != 0 && !announcedNumbers.isNumberAnnounced(number)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isValidLastNumber(int lastAnnouncedNumber) {
        int[] topRow = ticket.getRow(0);
        for (int number : topRow) {
            if (number == lastAnnouncedNumber) {
                return true;
            }
        }
        return false;
    }
}

