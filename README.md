Tambola is a game that is widely played across the world. To participate in a round, players are given tickets (shown below) which have numbers on them. A round has multiple games in it. A round is complete when all games are complete. As numbers are announced by a dealer at random, players match the numbers with those on the ticket and cross them. If they have crossed all numbers needed to win a game, they can claim to be the winner of the game.

Problem statement: Claim validator

**Input**: Numbers announced so far, a valid ticket and claim for a specific game

**Output**: Accepted/Rejected

**Games**
Each round has multiple games. Each game has a winning pattern.
● Top line: The ticket with all the numbers of the top row crossed fastest
● Middle line: The ticket with all the numbers of the middle row crossed fastest
● Bottom line: The ticket with the numbers of the bottom row crossed fastest
● Full house: The ticket with all the 15 numbers crossed first
● Early five: The fastest ticket to have 5 numbers crossed

**Rules**

1. System only has to return whether a claim is accepted or rejected
2. A player's claim to victory is only valid if it is made immediately following the announcement of the number that completes their winning sequence.

**Solution**:

To implement the Tambola Claim Validator we will modularize the code by breaking it down into different classes.

**Key Components:**
ClaimValidator: The main service to validate claims.
Ticket: A class representing the player's ticket.
GameType: Enum to represent the different game types.
GameValidator: Abstract class that various game types (Top Row, Middle Row, etc.) will extend.
AnnouncedNumbers: A class to hold the list of numbers announced so far.

Ticket Input:

The user enters the ticket's 3 rows as comma-separated values, where 0 represents an empty cell.
Announced Numbers Input:

The user enters the announced numbers as a comma-separated list.
Game Type Input:

The user specifies the game type by entering one of the following values: TOP_ROW, MIDDLE_ROW, BOTTOM_ROW, FULL_HOUSE, or EARLY_FIVE.
Claim Validation:

Based on the selected game type, a specific GameValidator is created.
The ClaimValidator then validates the claim based on the provided ticket, announced numbers, and game type.
Result:

The output is either "Accepted" or "Rejected" depending on the validation logic.
Example Input/Output:

Input:
Enter your Tambola ticket (3 rows with 9 numbers, use 0 for empty cells):
Row 1: 4, 16, 0, 0, 48, 0, 63, 76, 0
Row 2: 7, 0, 23, 38, 0, 52, 0, 0, 80
Row 3: 9, 0, 25, 0, 0, 56, 64, 0, 83

Enter the announced numbers separated by commas:
90, 4, 46, 63, 89, 16, 48, 76

Enter the game type (TOP_ROW, MIDDLE_ROW, BOTTOM_ROW, FULL_HOUSE, EARLY_FIVE):
TOP_ROW

Output:
Claim result: Accepted


Input:
Enter your Tambola ticket (3 rows with 9 numbers, use 0 for empty cells):
Row 1: 4, 16, 0, 0, 48, 0, 63, 76, 0
Row 2: 7, 0, 23, 38, 0, 52, 0, 0, 80
Row 3: 9, 0, 25, 0, 0, 56, 64, 0, 83

Enter the announced numbers separated by commas:
90, 4, 46, 63, 89, 16, 48, 76, 12

Enter the game type (TOP_ROW, MIDDLE_ROW, BOTTOM_ROW, FULL_HOUSE, EARLY_FIVE):
TOP_ROW

Output:
Claim result: Rejected
