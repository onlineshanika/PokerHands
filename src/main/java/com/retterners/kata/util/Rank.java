package com.retterners.kata.util;

public enum Rank {



    STRAIGHT_FLUSH ("STRAIGHT FLUSH" ,8),
    FOUR_OF_A_KIND ("FOUR_OF_A_KIND" ,7),
    FULL_HOUSE ("FULL_HOUSE" ,6),
    FLUSH ("  FLUSH" ,5),
    STRAIGHT ("STRAIGHT FLUSH" ,4),
    THREE_OF_A_KIND ("STRAIGHT" ,3),
    TWO_PAIR ("TWO_PAIR" ,2),
    ONE_PAIR ("ONE_PAIR" ,1),
    HIGH_CARD ("STRAIGHT FLUSH" ,0),

    NO_MATCH ("HIGH CARD" ,-1);

    String rank ;
    int value;
    Rank(String rank, int constant) {
        this.rank=rank;
        this.value=constant;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }
}
