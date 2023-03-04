package com.retterners.kata.util;

import java.util.Comparator;

public class CardComparator implements Comparator<String> {

    @Override
    public int compare(String card1, String card2) {
        int value1 = getValue(card1);
        int value2 = getValue(card2);
        int suit1 = getSuit(card1);
        int suit2 = getSuit(card2);
        if (value1 != value2) {
            return Integer.compare(value1, value2);
        } else {
            return Integer.compare(suit1, suit2);
        }
    }

    private int getValue(String card) {
        String valueString = card.substring(0, card.length() - 1);
        return switch (valueString) {
            case "T" -> 10;
            case "J" -> 11;
            case "Q" -> 12;
            case "K" -> 13;
            case "A" -> 14;
            default -> Integer.parseInt(valueString);
        };
    }

    private int getSuit(String card) {
        char suitChar = card.charAt(card.length() - 1);
        return switch (suitChar) {
            case 'H' -> 0;
            case 'D' -> 1;
            case 'S' -> 2;
            case 'C' -> 3;
            default -> throw new IllegalArgumentException("Invalid suit: " + suitChar);
        };
    }
}


