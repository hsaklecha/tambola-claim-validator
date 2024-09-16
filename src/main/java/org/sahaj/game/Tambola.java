package org.sahaj.game;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import org.sahaj.game.entities.*;
import org.sahaj.game.validators.*;
import org.sahaj.validators.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Tambola {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Take input for ticket (3 rows of numbers)
        System.out.println("Enter your Tambola ticket (3 rows with 9 numbers, use 0 for empty cells):");
        int[][] ticketNumbers = new int[3][9];

        for (int i = 0; i < 3; i++) {
            System.out.println("Enter row " + (i + 1) + " numbers separated by commas (use 0 for blank spaces):");
            ticketNumbers[i] = Arrays.stream(scanner.nextLine().split(","))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        // Step 2: Take input for the announced numbers
        System.out.println("Enter the announced numbers separated by commas:");
        List<Integer> announcedNumbersList = Arrays.stream(scanner.nextLine().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // Step 3: Take input for the game type (Top Row, Middle Row, Bottom Row, Full House, Early Five)
        System.out.println("Enter the game type (TOP_ROW, MIDDLE_ROW, BOTTOM_ROW, FULL_HOUSE, EARLY_FIVE):");
        String gameTypeInput = scanner.nextLine().trim().toUpperCase();

        GameType gameType;
        try {
            gameType = GameType.valueOf(gameTypeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid game type entered.");
            return;
        }

        // Step 4: Create objects for Ticket and AnnouncedNumbers
        Ticket ticket = new Ticket(ticketNumbers);
        AnnouncedNumbers announcedNumbers = new AnnouncedNumbers(announcedNumbersList);

        // Step 5: Create the appropriate GameValidator based on the game type
        GameValidator gameValidator;
        switch (gameType) {
            case TOP_ROW:
                gameValidator = new TopRowValidator(announcedNumbers, ticket);
                break;
            case MIDDLE_ROW:
                gameValidator = new MiddleRowValidator(announcedNumbers, ticket);
                break;
            case BOTTOM_ROW:
                gameValidator = new BottomRowValidator(announcedNumbers, ticket);
                break;
            case FULL_HOUSE:
                gameValidator = new FullHouseValidator(announcedNumbers, ticket);
                break;
            case EARLY_FIVE:
                gameValidator = new EarlyFiveValidator(announcedNumbers, ticket);
                break;
            default:
                System.out.println("Invalid game type.");
                return;
        }

        // Step 6: Validate the claim and output the result
        ClaimValidator claimValidator = new ClaimValidator(gameType, gameValidator, announcedNumbers);
        String result = claimValidator.validateClaim();
        System.out.println("Claim result: " + result);
    }
}
