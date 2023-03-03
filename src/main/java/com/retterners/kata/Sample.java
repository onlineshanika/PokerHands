package com.retterners.kata;

import java.util.Arrays;
import java.util.Collections;

public class Sample {

    public static void main(String[] args) {
        String[] arr = {"8H","3H","4D","5S","6H"};
//        Arrays.sort(arr); // Sort the array in descending order
//        for (String s : arr) {
//            System.out.println(s);
//        }

//        char lastChar = arr[0].charAt(arr[0].length() - 1);
//        boolean allStartWithA = Arrays.stream(arr).allMatch(s -> s.charAt(s.length() - 1) == lastChar);
//        System.out.println(allStartWithA);
             String[] hand1 = {"4H", "6D", "8S", "9C", "KS"}; // Example hand 1
            String[] hand2 = {"2H", "3D", "5S", "JC", "AD"}; // Example hand 2

            int rank1 = rankHand(hand1); // Rank hand 1
            int rank2 = rankHand(hand2); // Rank hand 2

            // Compare the ranks of the hands
            if (rank1 > rank2) {
                System.out.println("Hand 1 wins with a high card rank of " + rank1);
            } else if (rank2 > rank1) {
                System.out.println("Hand 2 wins with a high card rank of " + rank2);
            } else {
                System.out.println("It's a tie with a high card rank of " + rank1);
            }
        }

        public static int rankHand(String[] hand) {
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
    }