package com.retterners.kata;

import com.retterners.kata.util.CardComparator;
import com.retterners.kata.util.Rank;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PokerHand {

    private static final Map<Character, Integer> VALUE_RANK = new HashMap<>() {{
        put('2', 2);
        put('3', 3);
        put('4', 4);
        put('5', 5);
        put('6', 6);
        put('7', 7);
        put('8', 8);
        put('9', 9);
        put('T', 10);
        put('J', 11);
        put('Q', 12);
        put('K', 13);
        put('A', 14);
    }};



    public String play(String input) {
        String[] cards = input.split(" ");

        Rank whiteRank = rankHand(Arrays.copyOfRange(cards, 0, 5));
        Rank blackRank = rankHand(Arrays.copyOfRange(cards, 5, 10));

        System.out.println("blackRank : " + blackRank.getValue());
        System.out.println("whiteRank : " + whiteRank.getValue());

        if (whiteRank.getValue() > blackRank.getValue()) {
            System.out.println("White wins. - with " + whiteRank.getRank());
             return "White wins. - with ";
        } else if (blackRank.getValue() > whiteRank.getValue() ) {
            System.out.println("Black wins. - with " + blackRank.getRank());
             return "Black wins. - with ";
        } else {
            System.out.println("Tie.");
            return "Tie.";
        }

    }


    public int findHighCard(String[] hand) {
        // Convert cards to their numerical values
        int[] values = new int[5];
        for (int i = 0; i < hand.length; i++) {
            String card = hand[i];
            char value = card.charAt(0);
            switch (value) {
                case 'A':
                    values[i] = 14;
                    break;
                case 'K':
                    values[i] = 13;
                    break;
                case 'Q':
                    values[i] = 12;
                    break;
                case 'J':
                    values[i] = 11;
                    break;
                case 'T':
                    values[i] = 10;
                    break;
                default:
                    values[i] = Character.getNumericValue(value);
                    break;
            }
        }

        // Sort the values in descending order
        Arrays.sort(values);
        int[] sortedValues = new int[5];
        for (int i = 0; i < values.length; i++) {
            sortedValues[i] = values[values.length - i - 1];
        }

        // Return the highest value
        return sortedValues[0];
    }

    public Rank isFlush(String[] hand) {
        char suit = hand[0].charAt(1);  // Get the suit of the first card
        for (int i = 1; i < hand.length; i++) {
            if (hand[i].charAt(1) != suit) {
                return  Rank.NO_MATCH;  // If any other card has a different suit, it's not a flush
            }
        }
        return  Rank.FLUSH;  // All cards have the same suit, it's a flush
    }

    public Rank isStraightFlush(String[] hand) {
        // Sort hand by card rank
        Arrays.sort(hand);

        if (isFlush(hand) == Rank.NO_MATCH) {
            return  Rank.NO_MATCH;
        }

        // Check if the card ranks form a consecutive sequence
        for (int i = 1; i < hand.length; i++) {
            if (VALUE_RANK.get(hand[i].charAt(0)) != VALUE_RANK.get(hand[i - 1].charAt(0)) + 1) {
                return  Rank.NO_MATCH;
            }
        }

        // If all conditions are met, it is a straight flush
        return  Rank.STRAIGHT_FLUSH;
    }

    public Rank isFourOfAKind(String[] hand) {
        // Sort the cards in the hand
        Arrays.sort(hand, new CardComparator());
        // Check if the first four cards or last four cards have the same rank

        char lastChar = hand[0].charAt(hand[0].length() - 1);
        if (Arrays.stream(hand).allMatch(s -> s.charAt(s.length() - 1) == lastChar)) {
            return  Rank.FOUR_OF_A_KIND;
        }
        return  Rank.NO_MATCH;
    }

    public Rank isFullHouse(String[] hand) {
        // Sort the cards in the hand by rank
        Arrays.sort(hand, new CardComparator());

        // Check if the hand is a full house
        if ((hand[0].charAt(1) == hand[1].charAt(1) && hand[1].charAt(1) == hand[2].charAt(1) && hand[3].charAt(1) == hand[4].charAt(1)) ||
                (hand[0].charAt(1) == hand[1].charAt(1) && hand[2].charAt(1) == hand[3].charAt(1) && hand[3].charAt(1) == hand[4].charAt(1))) {
            return  Rank.FULL_HOUSE;
        }
        return  Rank.NO_MATCH;
    }

    public Rank isStraight(String[] hand) {
        Arrays.sort(hand, new CardComparator());
        int lowestRank = hand[0].charAt(1);
        int highestRank = hand[4].charAt(1);
        if (highestRank - lowestRank == 4) {
            return  Rank.STRAIGHT;
        }
        return  Rank.NO_MATCH;
    }


    public Rank isThreeOfAKind(String[] hand) {
        Map<Character, Integer> rankCount = new HashMap<>();
        for (String card : hand) {
            rankCount.put(card.charAt(0), rankCount.getOrDefault(card.charAt(0), 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() == 3) {
                return Rank.THREE_OF_A_KIND;
            }
        }
        return Rank.NO_MATCH;
    }

    public Rank isTwoPairs(String[] hand) {
        Arrays.sort(hand, new CardComparator()); // Step 1

        // Step 2
        if (hand[0].charAt(0) == hand[1].charAt(0) && hand[3].charAt(0) == hand[4].charAt(0)) {
            // Step 3
            if (hand[1].charAt(0) != hand[2].charAt(0) && hand[2].charAt(0) != hand[3].charAt(0)) {
                return Rank.TWO_PAIR;
            }
        }

        return Rank.NO_MATCH;
    }

    public Rank isPair(String[] hand) {
        Arrays.sort(hand, new CardComparator()); // sort the hand by rank
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i].charAt(0) == hand[i + 1].charAt(0)) {
                return Rank.ONE_PAIR; // found a pair
            }
        }
        return Rank.NO_MATCH; // no pair found
    }


    private Rank rankHand(String[] cards) {
        if (isStraightFlush(cards) == Rank.STRAIGHT_FLUSH) {
            return Rank.STRAIGHT_FLUSH;
        }
        if (isFourOfAKind(cards) == Rank.FOUR_OF_A_KIND) {
            return Rank.FOUR_OF_A_KIND;
        }
        if (isFullHouse(cards) == Rank.FULL_HOUSE) {
            return Rank.FULL_HOUSE;
        }
        if (isFlush(cards) == Rank.FLUSH) {
            return Rank.FLUSH;
        }
        if (isStraight(cards) == Rank.STRAIGHT) {
            return Rank.STRAIGHT;
        }
        if (isThreeOfAKind(cards) == Rank.THREE_OF_A_KIND) {
            return Rank.THREE_OF_A_KIND;
        }
        if (isTwoPairs(cards) == Rank.TWO_PAIR) {
            return Rank.TWO_PAIR;
        }
        if (isPair(cards) == Rank.ONE_PAIR) {
            return Rank.ONE_PAIR;
        }
        return Rank.HIGH_CARD;
    }

}
/*

Sort the hand of cards in ascending order of their values.

Check if the hand is a Straight Flush:
a. Check if all cards have the same suit.
b. Check if the values of the cards are consecutive.

Check if the hand is a Four of a Kind:
a. Check if any four cards have the same value.
b. Rank hands by the value of the four cards.

Check if the hand is a Full House:
a. Check if there are three cards with the same value and two cards with another value.
b. Rank hands by the value of the three cards.

Check if the hand is a Flush:
a. Check if all cards have the same suit.
b. Rank hands using the rules for High Card.

Check if the hand is a Straight:
a. Check if the values of the cards are consecutive.
b. Rank hands by the highest card.

Check if the hand is a Three of a Kind:
a. Check if any three cards have the same value.
b. Rank hands by the value of the three cards.

Check if the hand is Two Pairs:
a. Check if there are two pairs of cards with different values.
b. Rank hands by the highest pair, then the second-highest pair, then the remaining card.
---------------------------------------------------
Check if the hand is a Pair:
a. Check if any two cards have the same value.
b. Rank hands by the value of the pair, then the remaining cards in decreasing order.

If the hand does not fit any of the above categories, rank hands using the rules for High Card.





 */