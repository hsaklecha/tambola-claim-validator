package org.sahaj.game;

import org.sahaj.game.entities.*;
import org.sahaj.game.validators.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Tambola {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Tambola ticket (3 rows with 9 numbers, use 0 for empty cells):");
        int[][] ticketNumbers = new int[3][9];

        for (int i = 1; i <= 3; i++) {
            System.out.println("Enter row " + i + " numbers separated by commas (use 0 for empty cells):");
            ticketNumbers[i] = Arrays.stream(scanner.nextLine().split(","))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        System.out.println("Enter the announced numbers separated by commas:");
        List<Integer> announcedNumbersList = Arrays.stream(scanner.nextLine().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println("Enter the game type (TOP_ROW, MIDDLE_ROW, BOTTOM_ROW, FULL_HOUSE, EARLY_FIVE):");
        String gameTypeInput = scanner.nextLine().trim().toUpperCase();

        GameType gameType;
        try {
            gameType = GameType.valueOf(gameTypeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid game type entered.");
            return;
        }

        Ticket ticket = new Ticket(ticketNumbers);
        AnnouncedNumbers announcedNumbers = new AnnouncedNumbers(announcedNumbersList);

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

        ClaimValidator claimValidator = new ClaimValidator(gameType, gameValidator, announcedNumbers);
        String result = claimValidator.validateClaim();
        System.out.println("Claim result: " + result);
    }
}
