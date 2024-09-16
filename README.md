# Tambola Claim Validator

**Tambola** is a widely played game across the world, where players are given tickets with numbers on them. The goal of the game is to cross off numbers as they are announced and to claim victory by matching certain winning patterns. This system validates whether a player's claim for a specific game is **Accepted** or **Rejected** based on the numbers announced so far.

## Problem Statement: Claim Validator

- **Input**: Numbers announced so far, a valid ticket, and a claim for a specific game.
- **Output**: Whether the claim is **Accepted** or **Rejected**.

## Games

Each round of Tambola consists of multiple games. Each game has a unique winning pattern:

1. **Top Line**: The first ticket to cross all numbers on the top row.
2. **Middle Line**: The first ticket to cross all numbers on the middle row.
3. **Bottom Line**: The first ticket to cross all numbers on the bottom row.
4. **Full House**: The first ticket to cross all 15 numbers on the ticket.
5. **Early Five**: The first ticket to cross any 5 numbers.

## Rules

1. The system must only return whether the claim is **Accepted** or **Rejected**.
2. A claim is only valid if it is made immediately following the announcement of the number that completes the winning sequence for the player.

---

## Solution Design

To implement the Tambola Claim Validator, we break the problem into different components, ensuring the code is modular, adheres to **SOLID** principles, and avoids redundancy.

### Key Components:

1. **ClaimValidator**: The main class that handles validating claims based on the game type.
2. **Ticket**: A class representing the player's 3x9 grid of numbers.
3. **GameType**: An enumeration defining the types of games (e.g., Top Row, Middle Row).
4. **GameValidator**: An abstract class that various game types (Top Row, Middle Row, etc.) extend, implementing specific validation logic.
5. **AnnouncedNumbers**: A class to hold the announced numbers and check if a specific number is announced.

---

## Input Structure

1. **Ticket**: A 3x9 grid where each row contains numbers. Empty cells are represented by `0`.
   - Example Ticket:
     ```plaintext
     4, 16, 0, 0, 48, 0, 63, 76, 0
     7,  0, 23, 38, 0, 52, 0,  0, 80
     9,  0, 25, 0, 0, 56, 64, 0, 83
     ```

2. **Announced Numbers**: A list of comma-separated numbers that have been announced so far.
   - Example: `90, 4, 46, 63, 89, 16, 48, 76`

3. **Game Type**: A string representing the type of game being claimed.
   - Valid values: `TOP_ROW`, `MIDDLE_ROW`, `BOTTOM_ROW`, `FULL_HOUSE`, `EARLY_FIVE`

---

## Claim Validation Process

1. **Input Validation**:
   - The user provides the ticket, the announced numbers, and the game type.

2. **Game Selection**:
   - Based on the provided game type, a corresponding `GameValidator` (e.g., `TopRowValidator`) is instantiated.

3. **Claim Check**:
   - The `ClaimValidator` class checks if the last announced number completes the winning pattern.
   - If the claim is valid and made at the right time, the system outputs `Accepted`.
   - Otherwise, the claim is rejected.

---

## Example Input/Output

### Example 1: Top Row Win (Accepted)

**Input**:
```plaintext
Ticket:
4, 16, 0, 0, 48, 0, 63, 76, 0
7,  0, 23, 38, 0, 52, 0,  0, 80
9,  0, 25, 0, 0, 56, 64, 0, 83

Announced Numbers: 90, 4, 46, 63, 89, 16, 48, 76

Game Type: TOP_ROW
```
**Output:**
```plaintext
Claim result: Accepted
```

### Example 2: Late Top Row Claim (Rejected)
**Input**:

```plaintext
Ticket:
4, 16, 0, 0, 48, 0, 63, 76, 0
7,  0, 23, 38, 0, 52, 0,  0, 80
9,  0, 25, 0, 0, 56, 64, 0, 83

Announced Numbers: 90, 4, 46, 63, 89, 16, 48, 76, 12

Game Type: TOP_ROW
```
**Output:**
```plaintext
Claim result: Rejected
```
