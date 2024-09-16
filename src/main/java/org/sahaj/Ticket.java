package org.sahaj;

public class Ticket {
    private int[][] numbers;

    public Ticket(int[][] numbers) {
        this.numbers = numbers;
    }

    public int[] getRow(int rowIndex) {
        return numbers[rowIndex];
    }

    public int[][] getNumbers() {
        return numbers;
    }
}

