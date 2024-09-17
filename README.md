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
