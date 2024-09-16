package org.sahaj;

import java.util.List;

public class AnnouncedNumbers {
    private List<Integer> announcedNumbers;

    public AnnouncedNumbers(List<Integer> announcedNumbers) {
        this.announcedNumbers = announcedNumbers;
    }

    public boolean isNumberAnnounced(int number) {
        return announcedNumbers.contains(number);
    }

    public int getLastAnnouncedNumber() {
        return announcedNumbers.get(announcedNumbers.size() - 1);
    }
}

