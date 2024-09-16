package org.sahaj.game.validators;

import org.sahaj.game.entities.AnnouncedNumbers;
import org.sahaj.game.entities.GameValidator;
import org.sahaj.game.entities.Ticket;

public class BottomRowValidator implements GameValidator {
    private AnnouncedNumbers announcedNumbers;
    private Ticket ticket;

    public BottomRowValidator(AnnouncedNumbers announcedNumbers, Ticket ticket) {
        this.announcedNumbers = announcedNumbers;
        this.ticket = ticket;
    }

    @Override
    public boolean isWinningClaim() {
        int[] bottomRow = ticket.getRow(2);
        for (int number : bottomRow) {
            if (number != 0 && !announcedNumbers.isNumberAnnounced(number)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isValidLastNumber(int lastAnnouncedNumber) {
        int[] bottomRow = ticket.getRow(2);
        for (int number : bottomRow) {
            if (number == lastAnnouncedNumber) {
                return true;
            }
        }
        return false;
    }
}

