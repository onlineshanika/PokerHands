package com.returners.kata;

import com.retterners.kata.PokerHand;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerTest {


    @Test
    public void flushPokerHand() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.TRUE, hand.isFlush(new String[]{"2H", "3H", "5H", "9H", "KH"}));

    }

    @Test
    public void notflushPokerHand() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.FALSE, hand.isFlush(new String[]{"2H", "3D", "5H", "9H", "KH"}));

    }


    @Test
    public void isStraightFlush() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.TRUE, hand.isStraightFlush(new String[]{"2H", "3H", "4H", "5H", "6H"}));

    }

    @Test
    public void isNotStraightFlush() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.FALSE, hand.isStraightFlush(new String[]{"8H", "3H", "4H", "5H", "6H"}));

    }

    @Test
    public void isFourOfAKind() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.TRUE, hand.isFourOfAKind(new String[]{"8H", "3H", "4H", "5H", "6D"}));

    }

    @Test
    public void isNotFourOfAKind() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.TRUE, hand.isFourOfAKind(new String[]{"8H", "3H", "4D", "5S", "6H"}));

    }

    @Test
    public void isFullHouse() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.TRUE, hand.isFullHouse(new String[]{"8H", "3H", "4H", "5H", "6H"}));

    }

    @Test
    public void isNotFullHouse() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.FALSE, hand.isFullHouse(new String[]{"8H", "3D", "4H", "5H", "6H"}));

    }

    @Test
    public void isThreeOfAKind() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.TRUE, hand.isThreeOfAKind(new String[]{"3H", "3D", "3S", "5H", "6C"}));

    }

    @Test
    public void isNotThreeOfAKind() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.FALSE, hand.isThreeOfAKind(new String[]{"3H", "4D", "3S", "5H", "4C"}));

    }


    @Test
    public void isStraight() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.FALSE, hand.isStraight(new String[]{"3H", "4D", "5S", "6H", "7C"}));

    }


    @Test
    public void isNotStraight() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.FALSE, hand.isStraight(new String[]{"3H", "KD", "5S", "6H", "7C"}));

    }

    @Test
    public void isTwoPairs() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.TRUE, hand.isTwoPairs(new String[]{"3H", "3D", "5S", "6H", "6C"}));

    }

    @Test
    public void isNotTwoPairs() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.FALSE, hand.isStraight(new String[]{"3H", "KD", "5S", "6H", "7C"}));

    }

    @Test
    public void isPair() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.TRUE, hand.isPair(new String[]{"AH", "AS", "5C", "8D", "QH"}));

    }

    @Test
    public void isNotPair() {
        PokerHand hand = new PokerHand();
        assertEquals(Boolean.FALSE, hand.isPair(new String[]{"3H", "KD", "5S", "6H", "7C"}));

    }


    @Test
    public void play() {
        PokerHand hand = new PokerHand();
//        assertEquals("White wins. - with ", hand.play("2H 3D 5S 9C KD 2C 3H 4S 8C AH"));
        assertEquals("Black wins. - with ", hand.play("2H 4S 4C 2D 4H 2S 8S AS QS 3S"));
//        assertEquals("Black wins. - with ", hand.play("2H 3D 5S 9C KD 2C 3H 4S 8C KH "));
//        assertEquals("Black wins. - with ", hand.play("2H 3D 5S 9C KD 2D 3H 5C 9S KH"));
    }


}
/*
Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH        -- > White wins. - with high card: Ace
Black: 2H 4S 4C 2D 4H  White: 2S 8S AS QS 3S        -- > Black wins. - with full house: 4 over 2
Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH        -- > Black wins. - with high card: 9
Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH        -- > Tie.
 */




