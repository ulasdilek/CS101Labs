package lab5;

import java.util.Random;
import java.util.Scanner;

/**
 * This is a simplified version of blackjack
 * 
 * @author Ulas Dilek
 */

public class lab5_Q2 {
    public static void main(String[] args) {

        Random random = new Random();
        boolean gameOn = true;
        boolean playerTurn = true;
        String deck = "";
        String player = "";
        String dealer = "";
        int playerScore;
        int dealerScore;
        Scanner in = new Scanner(System.in);
        /*
         * Create deck Draw cards
         * Remove drawn cards from the deck
         */
        for (byte cardNum = 1; cardNum < 10; cardNum++) {
            for (byte counter = 0; counter < 4; counter++) {
                deck += String.valueOf(cardNum);
            }
        }
        System.out.println("Starting the game with the following deck:");
        System.out.println(deck);
        for (byte counter = 0; counter < 2; counter++) {
            int cardIndex = random.nextInt(deck.length());
            player += String.valueOf(deck.charAt(cardIndex));
            deck = deck.replaceFirst(Character.toString(deck.charAt(cardIndex)), "");
        }
        for (byte counter = 0; counter < 2; counter++) {
            int cardIndex = random.nextInt(deck.length());
            dealer += String.valueOf(deck.charAt(cardIndex));
            deck = deck.replaceFirst(Character.toString(deck.charAt(cardIndex)), "");
        }
        System.out.println("Dealer is now dealing cards...");
        System.out.println("Player's hand:");
        System.out.println(player);
        System.out.println("Dealer's hand:");
        System.out.println(dealer.charAt(0) + "?");//revise
        

        /*
         * Player hit or stand
         * Make decisions and possibly end the game
         */
        do {
            
            playerScore = 0;
            System.out.println("Please enter your choice:");
            System.out.println("1) Hit");
            System.out.println("2) Stand");
            byte hitOrStand = in.nextByte();

            if (hitOrStand == 1) {

                System.out.println("Hit! Dealer is giving the player a card...");
                int cardIndex = random.nextInt(deck.length());
                player += String.valueOf(deck.charAt(cardIndex));
                deck = deck.replaceFirst(Character.toString(deck.charAt(cardIndex)), "");
                System.out.println("Player's hand:");
                System.out.println(player);

                for (byte index = 0; index < player.length(); index++){
                    playerScore += Integer.parseInt(Character.toString(player.charAt(index)));
                }

                if (playerScore == 21) {
                    System.out.println("\nPlayer Scored 21. Player wins!");
                    gameOn = false;
                    playerTurn = false;
                }
                else if (playerScore > 21) {
                    System.out.println("\n Player scored over 21. Player lost!");
                    gameOn = false;
                    playerTurn = false;
                }

            }
            else {

                for (byte index = 0; index < player.length(); index++){
                    playerScore += Integer.parseInt(Character.toString(player.charAt(index)));
                }
                System.out.println("Stand! Player's turn is now over. Player's hand sums to " + playerScore);
                playerTurn = false;

            }
        } while (playerTurn && gameOn);
        in.close();

        /*
         * Dealer hit or stand
         * Make decisions and end the game
         */
        for (; gameOn;){

            System.out.println("Dealer is drawing cards...");
            do {
                
                dealerScore = 0;
                int cardIndex = random.nextInt(deck.length());
                dealer += String.valueOf(deck.charAt(cardIndex));
                deck = deck.replaceFirst(Character.toString(deck.charAt(cardIndex)), "");
                System.out.println("Dealer's hand:");
                System.out.println(dealer);

                for (byte index = 0; index < dealer.length(); index++){
                    dealerScore += Integer.parseInt(Character.toString(dealer.charAt(index)));
                }

                if (dealerScore == 21) {
                    System.out.println("\nDealer scored 21. Dealer wins!");
                    gameOn = false;
                }
                else if (dealerScore > 21) {
                    System.out.println("\nDealer scored more than 21. Dealer lost!");
                    gameOn = false;
                }
                else if ( dealerScore > playerScore){
                    System.out.println("\nDealer scored more than player. Dealer wins!");
                    gameOn = false;
                }
                
            } while (gameOn);
            
        }

        System.out.println("\nEnding the game with the following deck:");
        System.out.println(deck);

    }
}
