package com.retterners.kata;

import com.retterners.kata.util.CardComparator;

import java.util.*;

public class Poker {
    public String play(String input, Poker poker) {

        String[] cards = input.split(" ");
        String[] blackHand = Arrays.copyOfRange(cards, 0, 5);
        String[] whiteHand = Arrays.copyOfRange(cards, 5, 10);

        HandRank blackResult = poker.getHandResult(blackHand);
        HandRank whiteResult = poker.getHandResult(whiteHand);

        if (blackResult.getValue() > whiteResult.getValue()) {
            return String.format("Black wins. - with %s", blackResult.getRank());
        } else if (blackResult.getValue() < whiteResult.getValue()) {
            return String.format("White wins. - with %s", whiteResult.getRank());
        } else {
            for (int i = 4; i >= 0; i--) {
                int blackValue = CardValue.charValueBy(blackHand[i].charAt(0)).cardValue;
                int whiteValue = CardValue.charValueBy(whiteHand[i].charAt(0)).cardValue;

                if (blackValue > whiteValue) {
                    return String.format("Black wins. - with %s: %s", blackResult.getRank(), CardValue.cardValueBy(blackValue).card);
                } else if (whiteValue > blackValue) {
                    return String.format("White wins. - with %s: %s", whiteResult.getRank(), CardValue.cardValueBy(whiteValue).card);
                }
            }
            // all cards are the same, it's a tie
            return "Tie.";
        }
    }

    public HandRank getHandResult(String[] hand) {
        // Map to store the frequency of each card
        Map<String, Integer> freq = new HashMap<>();

        // Count the frequency of each card
        for (String card : hand) {
            String rank = card.substring(0, card.length() - 1);
            freq.put(rank, freq.getOrDefault(rank, 0) + 1);
        }

        if (isStraightFlush(hand)) {
            return HandRank.STRAIGHT_FLUSH;
        }
        // Check for four of a kind
        if (isFourOfAKind(freq)) {
            return HandRank.FOUR_OF_A_KIND;
        }

        // Check for full house
        if (isFullHouse(freq)) {
            return HandRank.FULL_HOUSE;
        }
        // Check for flush
        if (isFlush(hand)) {
            return HandRank.FLUSH;
        }

        // Check for three of a kind
        if (isThreeOfAKind(freq)) {
            return HandRank.THREE_OF_A_KIND;
        }

        // Check for two pairs
        if (isTwoPairs(freq)) {
            return HandRank.TWO_PAIRS;
        }

        // Check for a pair
        if (isPair(freq)) {
            return HandRank.PAIR;
        }

        // Check for a straight
        if (isStraight(hand)) {
            return HandRank.STRAIGHT;
        }
        // Default case: high card
        return HandRank.HIGH_CARD;
    }


    public Boolean isFlush(String[] hand) {
        char suit = hand[0].charAt(1);  // Get the suit of the first card
        for (int i = 1; i < hand.length; i++) {
            if (hand[i].charAt(1) != suit) {
                return false;  // If any other card has a different suit, it's not a flush
            }
        }
        return true;  // All cards have the same suit, it's a flush
    }

    public Boolean isStraightFlush(String[] hand) {
        // Sort hand by card rank
        Arrays.sort(hand, new CardComparator());

        if (!isFlush(hand)) {
            return false;
        }

        // Check if the card ranks form a consecutive sequence
        for (int i = 1; i < hand.length; i++) {
            if (CardValue.charValueBy(hand[i].charAt(0)).cardValue != CardValue.charValueBy(hand[i - 1].charAt(0)).cardValue + 1) {
                return false;
            }
        }

        // If all conditions are met, it is a straight flush
        return true;
    }

    public Boolean isFourOfAKind(Map<String, Integer> freq) {
        for (String rank : freq.keySet()) {
            if (freq.get(rank) == 4) {
                return true;
            }
        }
        return false;
    }

    public Boolean isFullHouse(Map<String, Integer> freq) {
        boolean hasThreeOfKind = false;
        boolean hasPair = false;
        for (String rank : freq.keySet()) {
            if (freq.get(rank) == 3) {
                hasThreeOfKind = true;
            } else if (freq.get(rank) == 2) {
                hasPair = true;
            }
        }
        return (hasThreeOfKind && hasPair);
    }

    public Boolean isStraight(String[] hand) {
        Set<Integer> ranks = new HashSet<>();
        for (String card : hand) {
            String rank = card.substring(0, card.length() - 1);
            ranks.add(CardValue.charValueBy(rank.charAt(0)).cardValue);
        }
        return (ranks.size() == 5 && Collections.max(ranks) - Collections.min(ranks) == 4);
    }


    public Boolean isThreeOfAKind(Map<String, Integer> freq) {
        for (String rank : freq.keySet()) {
            if (freq.get(rank) == 3) {
                return true;
            }
        }
        return false;
    }

    public Boolean isTwoPairs(Map<String, Integer> freq) {
        int numPairs = 0;
        for (String rank : freq.keySet()) {
            if (freq.get(rank) == 2) {
                numPairs++;
            }
        }
        return (numPairs == 2);
    }

    public Boolean isPair(Map<String, Integer> freq) {
        for (String rank : freq.keySet()) {
            if (freq.get(rank) == 2) {
                return true;
            }
        }
        return false;
    }

}
