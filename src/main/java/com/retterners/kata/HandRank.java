package com.retterners.kata;

public enum HandRank {
    HIGH_CARD("High Card", 0),
    PAIR("Pair", 1),
    TWO_PAIRS("Two Pairs", 2),
    THREE_OF_A_KIND("Three of a Kind", 3),
    STRAIGHT("Straight", 4),

    FLUSH("Flush", 5),
    FULL_HOUSE("Full House", 6),
    FOUR_OF_A_KIND("Four of a Kind", 7),
    STRAIGHT_FLUSH("Straight Flush", 8);

    String rank;
    int value;

    HandRank(String rank, int value) {
        this.rank = rank;
        this.value = value;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }
}
