package com.retterners.kata;

import java.util.HashMap;
import java.util.Map;

public enum CardValue {

    TWO('2', 2, "2"),
    THREE('3', 3, "3"),
    FOUR('4', 4, "4"),
    FIVE('5', 5, "5"),
    SIX('6', 6, "6"),
    SEVEN('7', 7, "7"),
    EIGHT('8', 8, "8"),
    NINE('9', 9, "9"),
    TEN('T', 10, "10"),
    JACk('J', 11, "Jack"),
    QUEEN('Q', 12, "Queen"),
    KING('K', 13, "King"),
    ACE('A', 14, "Ace");;


    final char charValue;
    final int cardValue;
    final String card;

    private static final Map<Integer, CardValue> BY_VALUE = new HashMap<>();
    private static final Map<Character, CardValue> BY_CHAR = new HashMap<>();

    static {
        for (CardValue e : values()) {
            BY_VALUE.put(e.getCardValue(), e);
            BY_CHAR.put(e.getCharValue(), e);
        }
    }

    CardValue(char charValue, int cardValue, String card) {
        this.charValue = charValue;
        this.cardValue = cardValue;
        this.card = card;
    }

    public char getCharValue() {
        return charValue;
    }

    public int getCardValue() {
        return cardValue;
    }

    public String getCard() {
        return card;
    }


    public static CardValue cardValueBy(int value) {
        return BY_VALUE.get(value);
    }

    public static CardValue charValueBy(char character) {
        return BY_CHAR.get(character);
    }
}
