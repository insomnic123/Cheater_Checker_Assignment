/*
Name: Imisi and Sadman
Program Name: SPOT IT JR JR!!
Program Description:
Play a game of 6 rounds of SPOT IT JR JR
Find the matching letter between 2 cards with 4 images each
Creation Date: 2024-09-24
Last Edit Date: 2024-10-06
Ethics Declaration: This code is my own work
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class sspotit {

    static Scanner input = new Scanner(System.in);

    /// Shuffles Deck
    public static void shuffle_deck(String[][] deck){

        //Shuffles each element in a card
        for (String[] image : deck) {
            Collections.shuffle(Arrays.asList(image));
        }
        // Shuffles the cards in the deck
        Collections.shuffle(Arrays.asList(deck));

    }

    public static void main(String[] args) {


        // 2d Deck of Cards
        String[][] deck = {

                {"a", "b", "c", "d"},      // Card 1
                {"a", "e", "f", "g"},      // Card 2
                {"a", "h", "i", "j"},      // Card 3
                {"a", "k", "l", "m"},      // Card 4

                {"b", "e", "h", "k"},      // Card 5
                {"b", "f", "i", "l"},      // Card 6
                {"b", "g", "j", "m"},      // Card 7

                {"c", "e", "i", "m"},      // Card 8
                {"c", "f", "j", "k"},      // Card 9
                {"c", "g", "h", "l"},      // Card 10

                {"d", "e", "j", "l"},      // Card 11
                {"d", "f", "h", "m"},      // Card 12
                {"d", "g", "i", "k"},      // Card 13

        }; // Closes List

        // Var Declaration
        // Player Guess
        String guess;

        // Player continue_game
        String continue_game;

        // Game Loop
        boolean game_state = false;

        // Game Loop index
        int index = 0;

        // Highscore
        double highScore = 0;

        // Start game
        String start_game;

        // ANSI escape code constants for text colors
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String BLUE = "\u001B[34m";

        // Menu
        System.out.println("SPOT IT JR JR");

        do {
            System.out.println("Press \"P\" to Play");
            start_game = input.nextLine();
            if (start_game.equalsIgnoreCase("p")){
                game_state = true;
                System.out.println("----------------------------");
            }
        } while (!(start_game.equalsIgnoreCase("p")));


        // Shuffles Deck at start
        shuffle_deck(deck);

        // Game loop (iterates through game)
        while (game_state) {

            // Checks time at beginning
            long start = System.currentTimeMillis();
            for (;index < 12; index += 2) {

                String matchingImage = "placeholder";

                // Finds the matching letter between two cards
                for (String image : deck[index]) {
                    for (String image2 : deck[index + 1]) {
                        //Checks between the images of both cards
                        if (image.equals(image2)) {
                            matchingImage = image;
                            break;
                        }
                    }
                }

                // Prints the cards in the round
                System.out.println(BLUE + (String.join(", ", deck[index])) + RESET);
                System.out.println(BLUE + (String.join(", ", deck[index + 1])) + RESET);

                // User Input for matching img
                do {
                    System.out.print("Whats the matching letter? ");
                    guess = input.nextLine();
                } while (!(guess.equals(matchingImage)));
                System.out.println("----------------------------");

            } // closes for loop

            // Print GAME OVER after finishing 6 rounds
            System.out.println("GAME OVER");

            // Finds time for game
            long end = System.currentTimeMillis();
            long elapsedTime = end - start;
            double elapsedSeconds = elapsedTime / 1000.0;

            // Checks for and prints score and highScore
            if (highScore == 0) {
                highScore = elapsedSeconds;
                System.out.println("Current HIGHSCORE is " + elapsedSeconds + " seconds");
            } else if (highScore > elapsedSeconds) {
                highScore = elapsedSeconds;
                System.out.printf("NEW HIGHSCORE: %.3f seconds%n", highScore);
            } else {
                System.out.println("Current TIME is " + elapsedSeconds);
                System.out.printf("Current HIGHSCORE: %.3f seconds%n", highScore);
            }

            //New game player input
            System.out.println("Play new game?");

            //Checks and filters answer
            do {
                System.out.println(GREEN + "(Y)es " + RESET + "or " + RED + "(N)o" + RESET);
                continue_game = input.nextLine();
                if (continue_game.equalsIgnoreCase("n")){
                    game_state = false;
                    break;
                }
            } while (!(continue_game.equalsIgnoreCase("y")));

            // Restarts game
            if (continue_game.equalsIgnoreCase("y")){
                System.out.println("----------------------------");
                shuffle_deck(deck);
                index = 0;
            }

        }// Closes game loop
    } //Closes public static...
} // Closes main