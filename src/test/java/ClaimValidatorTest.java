import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sahaj.game.entities.*;
import org.sahaj.game.validators.*;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ClaimValidatorTest {

    private Ticket validTicket;
    private Ticket invalidTicket;
    private List<Integer> announcedNumbers;

    @BeforeEach
    void setUp() {
        // Set up a valid ticket
        int[][] ticketNumbers = {
                {4, 16, 0, 0, 48, 0, 63, 76, 0},
                {7, 0, 23, 38, 0, 52, 0, 0, 80},
                {9, 0, 25, 0, 0, 56, 64, 0, 83}
        };
        validTicket = new Ticket(ticketNumbers);

        // Set up an invalid ticket (empty numbers)
        int[][] invalidTicketNumbers = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        invalidTicket = new Ticket(invalidTicketNumbers);

        // Set up announced numbers
        announcedNumbers = List.of(90, 4, 46, 63, 89, 16, 48, 76);
    }

    @Test
    void testTopRowWinAccepted() {
        // Create the necessary objects
        AnnouncedNumbers announced = new AnnouncedNumbers(announcedNumbers);
        GameValidator topRowValidator = new TopRowValidator(announced, validTicket);
        ClaimValidator claimValidator = new ClaimValidator(GameType.TOP_ROW, topRowValidator, announced);

        // Test that the top row claim is accepted
        String result = claimValidator.validateClaim();
        assertThat(result).isEqualTo("Accepted");
    }

    @Test
    void testTopRowWinRejectedDueToInvalidLastNumber() {
        // Add an extra number to the announced numbers (too late for top row)
        List<Integer> lateAnnouncedNumbers = List.of(90, 4, 46, 63, 89, 16, 48, 76, 12);
        AnnouncedNumbers announced = new AnnouncedNumbers(lateAnnouncedNumbers);
        GameValidator topRowValidator = new TopRowValidator(announced, validTicket);
        ClaimValidator claimValidator = new ClaimValidator(GameType.TOP_ROW, topRowValidator, announced);

        // Test that the top row claim is rejected
        String result = claimValidator.validateClaim();
        assertThat(result).isEqualTo("Rejected");
    }

    @Test
    void testMiddleRowWinAccepted() {
        // Announce additional numbers to cover the middle row
        List<Integer> extendedAnnouncedNumbers = List.of(90, 4, 46, 63, 89, 16, 48, 76, 7, 23, 38, 52, 80);
        AnnouncedNumbers announced = new AnnouncedNumbers(extendedAnnouncedNumbers);
        GameValidator middleRowValidator = new MiddleRowValidator(announced, validTicket);
        ClaimValidator claimValidator = new ClaimValidator(GameType.MIDDLE_ROW, middleRowValidator, announced);

        // Test that the middle row claim is accepted
        String result = claimValidator.validateClaim();
        assertThat(result).isEqualTo("Accepted");
    }

    @Test
    void testBottomRowWinRejected() {
        // Announced numbers are insufficient to complete the bottom row
        AnnouncedNumbers announced = new AnnouncedNumbers(announcedNumbers);
        GameValidator bottomRowValidator = new BottomRowValidator(announced, validTicket);
        ClaimValidator claimValidator = new ClaimValidator(GameType.BOTTOM_ROW, bottomRowValidator, announced);

        // Test that the bottom row claim is rejected
        String result = claimValidator.validateClaim();
        assertThat(result).isEqualTo("Rejected");
    }

    @Test
    void testFullHouseWinAccepted() {
        // Announce all the numbers to complete the ticket (Full House)
        List<Integer> fullHouseNumbers = List.of(90, 4, 46, 63, 89, 16, 48, 76, 7, 23, 38, 52, 80, 9, 25, 56, 64, 83);
        AnnouncedNumbers announced = new AnnouncedNumbers(fullHouseNumbers);
        GameValidator fullHouseValidator = new FullHouseValidator(announced, validTicket);
        ClaimValidator claimValidator = new ClaimValidator(GameType.FULL_HOUSE, fullHouseValidator, announced);

        // Test that the full house claim is accepted
        String result = claimValidator.validateClaim();
        assertThat(result).isEqualTo("Accepted");
    }

    @Test
    void testEarlyFiveWinAccepted() {
        // Use the original announced numbers (more than 5 have been announced)
        AnnouncedNumbers announced = new AnnouncedNumbers(announcedNumbers);
        GameValidator earlyFiveValidator = new EarlyFiveValidator(announced, validTicket);
        ClaimValidator claimValidator = new ClaimValidator(GameType.EARLY_FIVE, earlyFiveValidator, announced);

        // Test that the early five claim is accepted
        String result = claimValidator.validateClaim();
        assertThat(result).isEqualTo("Accepted");
    }

    @Test
    void testEarlyFiveRejectedForInvalidTicket() {
        // Use an invalid ticket (no numbers crossed)
        AnnouncedNumbers announced = new AnnouncedNumbers(announcedNumbers);
        GameValidator earlyFiveValidator = new EarlyFiveValidator(announced, invalidTicket);
        ClaimValidator claimValidator = new ClaimValidator(GameType.EARLY_FIVE, earlyFiveValidator, announced);

        // Test that the early five claim is rejected
        String result = claimValidator.validateClaim();
        assertThat(result).isEqualTo("Rejected");
    }
}

