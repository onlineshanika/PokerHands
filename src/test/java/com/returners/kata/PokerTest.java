package com.returners.kata;

import com.retterners.kata.Poker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerTest {


    @Test
    public void flushPokerHand() {
        Poker hand = new Poker();
        assertEquals(Boolean.TRUE, hand.isFlush(new String[]{"2H", "3H", "5H", "9H", "KH"}));

    }

    @Test
    public void notflushPokerHand() {
        Poker hand = new Poker();
        assertEquals(Boolean.FALSE, hand.isFlush(new String[]{"2H", "3D", "5H", "9H", "KH"}));

    }


    @Test
    public void isStraightFlush() {
        Poker hand = new Poker();
        assertEquals(Boolean.TRUE, hand.isStraightFlush(new String[]{"2H", "3H", "4H", "5H", "6H"}));

    }

    @Test
    public void isNotStraightFlush() {
        Poker hand = new Poker();
        assertEquals(Boolean.FALSE, hand.isStraightFlush(new String[]{"8H", "3H", "4H", "5H", "6H"}));

    }

    @Test
    public void isFourOfAKind() {
        Poker hand = new Poker();
        assertEquals(Boolean.TRUE, hand.isFourOfAKind(createInputMap(new String[]{"AH", "AC", "AS", "AD", "2H"})));

    }

    @Test
    public void isNotFourOfAKind() {
        Poker hand = new Poker();
        assertEquals(Boolean.FALSE, hand.isFourOfAKind(createInputMap(new String[]{"8H", "3H", "4D", "5S", "6H"})));

    }

    @Test
    public void isFullHouse() {
        Poker hand = new Poker();
        assertEquals(Boolean.TRUE, hand.isFullHouse(createInputMap(new String[]{"AH", "AC", "AS", "2D", "2H"})));

    }

    @Test
    public void isNotFullHouse() {
        Poker hand = new Poker();
        assertEquals(Boolean.FALSE, hand.isFullHouse(createInputMap(new String[]{"8H", "3D", "4H", "5H", "6H"})));

    }

    @Test
    public void isThreeOfAKind() {
        Poker hand = new Poker();
        assertEquals(Boolean.TRUE, hand.isThreeOfAKind(createInputMap(new String[]{"3H", "3D", "3S", "5H", "6C"})));

    }

    @Test
    public void isNotThreeOfAKind() {
        Poker hand = new Poker();
        assertEquals(Boolean.FALSE, hand.isThreeOfAKind(createInputMap(new String[]{"3H", "4D", "3S", "5H", "4C"})));

    }


    @Test
    public void isStraight() {
        Poker hand = new Poker();
        assertEquals(Boolean.TRUE, hand.isStraight(new String[]{"3H", "4D", "5S", "6H", "7C"}));

    }


    @Test
    public void isNotStraight() {
        Poker hand = new Poker();
        assertEquals(Boolean.FALSE, hand.isStraight(new String[]{"3H", "KD", "5S", "6H", "7C"}));

    }

    @Test
    public void isTwoPairs() {
        Poker hand = new Poker();
        assertEquals(Boolean.TRUE, hand.isTwoPairs(createInputMap(new String[]{"3H", "3D", "5S", "6H", "6C"})));
    }

    @Test
    public void isNotTwoPairs() {
        Poker hand = new Poker();
        assertEquals(Boolean.FALSE, hand.isTwoPairs(createInputMap(new String[]{"3H", "KD", "5S", "6H", "7C"})));

    }

    @Test
    public void isPair() {
        Poker hand = new Poker();
        assertEquals(Boolean.TRUE, hand.isPair(createInputMap(new String[]{"AH", "AS", "5C", "8D", "QH"})));

    }

    @Test
    public void isNotPair() {
        Poker hand = new Poker();
        assertEquals(Boolean.FALSE, hand.isPair(createInputMap(new String[]{"3H", "KD", "5S", "6H", "7C"})));

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv")
    public void bulkTest(String input, String output) {
        Poker hand = new Poker();
        assertEquals(output, hand.play(input, hand));
    }


    private Map<String, Integer> createInputMap(String[] hands) {
        Map<String, Integer> freq = new HashMap<>();

        // Count the frequency of each card
        for (String card : hands) {
            String rank = card.substring(0, card.length() - 1);
            freq.put(rank, freq.getOrDefault(rank, 0) + 1);
        }
        return freq;
    }


}
/*
Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH        -- > White wins. - with high card: Ace
Black: 2H 4S 4C 2D 4H  White: 2S 8S AS QS 3S        -- > Black wins. - with full house: 4 over 2
Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH        -- > Black wins. - with high card: 9
Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH        -- > Tie.
 */




