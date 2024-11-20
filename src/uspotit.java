import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class uspotit {

    static String guessPic(String[] pic1, String[] pic2) {


        String result = null;
        for (String img1 : pic1) {
            for (String img2 : pic2) {
                if (img1.equals(img2)) {
                    result = img2;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {

        // ANSI Escape Codes for Text Color
        String redText = "\u001B[31m";
        String blueText = "\u001B[34m";
        String cyanText = "\u001B[36m";
        String greenText = "\u001B[32m";
        String whiteText = "\u001B[37m";
        String yellowText = "\u001B[33m";
        String magentaText = "\u001B[35m";
        String blackText = "\u001B[30m";

        // ANSI Escape Codes for Background Color
        String redBG = "\u001B[41m";
        String blueBG = "\u001B[44m";
        String cyanBG = "\u001B[46m";
        String blackBG = "\u001B[40m";
        String greenBG = "\u001B[42m";
        String whiteBG = "\u001B[47m";
        String yellowBG = "\u001B[43m";
        String magentaBG = "\u001B[45m";

        String[] food = {"tiramisu", "chocolate", "gummies","marshmallows", "cake","icecream", "brownies", "cookies", "cupcakes", "donuts", "candy", "popcorn","chips"};


        String[][] cardDeck = {
                {food[0], food[1], food[2], food[3]},
                {food[0], food[4], food[5], food[6]},
                {food[0], food[7], food[8], food[9]},
                {food[0], food[10], food[11], food[12]},
                {food[1], food[4], food[7], food[10]},
                {food[1], food[5], food[8], food[11]},
                {food[1], food[6], food[9], food[12]},
                {food[2], food[4], food[9], food[11]},
                {food[2], food[5], food[7], food[12]},
                {food[2], food[6], food[8], food[10]},
                {food[3], food[4], food[7], food[11]},
                {food[3], food[5], food[9], food[10]},
                {food[3], food[6], food[7], food[12]},
        };




        // control variables
        boolean startGame = false;
        boolean playAgain = false;
        int numRounds = 0;
        boolean menu = true;

        int score1=0;
        int score2=0;

        Scanner scanner = new Scanner(System.in);
        //intro prompt

        System.out.println(cyanText + blackBG + "Welcome to our SPOT IT game, food edition deluxe!!! Press 1 to start the game, or press 2 for instructions.");

        // intro prompt
        while (menu) {

            int menuResponse = scanner.nextInt();
            scanner.nextLine();

            switch (menuResponse) {

                case 1:
                    // Getting number of rounds to play
                    System.out.println(magentaText + blackBG + "How many rounds do you want to play?");
                    numRounds = scanner.nextInt(); // choosing the number of rounds
                    scanner.nextLine();

                    // Starting games
                    System.out.println(yellowText + blackBG + "Game started!!");
                    startGame = true;
                    menu = false;
                    break;


                case 2:
                    System.out.println("Both players will be presented with two cards that share only one image. The goal is for both players to guess the matching image before the other player. The options for players will be based on the first card provided. Player one will select options using numbers 1-4, whilst player two will use options from 5-8 (5 as 1, 6 as 2, 7 as 3, and 8 as 4). For example, if the matching image from both cards is the 3rd image on card 1, the correct answer for player one will be 3 and the correct answer for player two would be 7. Please don't leave any spaces between numbers and don't cheat! Press run to start again.");
                    menu = false;
                    break;

                default:
                    System.out.println(redText + blackBG + "Inavlid choice. Try again.");
                    menu=false;
                    break;

            } // switch case
        }// menu while loop


        while (startGame) {
            /// call on function here
            for (int i = 1; i <= numRounds; i++) {

                int cardIndex1 = (int) (Math.random() * (12 + 1));
                String[] card1 = cardDeck[cardIndex1]; // card 1

                System.out.print(greenText + "Card One:");
                System.out.print(greenText + Arrays.toString(card1));// show card 1 images

                int cardIndex2 = (int) (Math.random() * (12 + 1));
                while (cardIndex2 == cardIndex1) {
                    cardIndex2 = (int) (Math.random() * (12 + 1));
                } // card 2 index changes if it is the same as card 1
                String[] card2 = cardDeck[cardIndex2]; //card 2

                System.out.print(blueText + " Card Two:");
                System.out.println(blueText + Arrays.toString(card2));// show card 1 images

                // shuffling card deck
                Collections.shuffle(Arrays.asList(cardDeck[1]));


                // finding similar image between both cards
                String result = guessPic(card1, card2);
                int resultIndex = Arrays.asList(card1).indexOf(result); //image index in card 1


                // allow players to guess, and the first player to guess corectly will earn a point
                System.out.print(whiteText + "Both players input guesses: ");
                int guesses = scanner.nextInt(); // two guessers!
                scanner.nextLine();
                int firstGuess = guesses/10; // first digit which is first guess
                int secondGuess = guesses %10; // second digit which is second guess

                //checks each digit to see who guessed and if it's correct

                if (firstGuess >=1 && firstGuess <=4) {

                    if (firstGuess == resultIndex+1) {
                        System.out.println(blackText + greenBG + "Player one wins!");
                        score1 += 1; // if player one first guest is correct, player one wins
                    } else if (secondGuess >=5 && secondGuess <=8){
                        if (secondGuess == resultIndex+5) {
                            System.out.println(blackText + blueBG + "Player two wins!");
                            score2 += 1; // if player 1 first guess is wrong, if player 2 first guess is right, player 2 wins
                        }
                    } else {
                        System.out.println(blackText + redBG + "No one wins"); // No one gets points if neither player guesses correctly
                    }
                }

                if (firstGuess >=5 && firstGuess <=8) {
                    if (firstGuess == resultIndex+5) {
                        System.out.println(blueText + "Player two wins!");
                        score2 += 1; //if player two first guest is correct, player one wins
                    } else if (secondGuess >=1 && secondGuess <=4) {
                        if (secondGuess == resultIndex+1) {
                            System.out.println(greenText + "Player one wins!");
                            score1 += 1;
                        } // if player 2 first guess is wrong, if player 2 first guess is right, player 1 wins
                    } else {
                        System.out.println(redText + blackBG + "No one wins"); // No one gets points if neither player guesses correctly
                    }
                }


                if (i != numRounds) {
                    System.out.println(magentaText + blackBG + "\n--Next Round--"); // adds "next round" statement for following rounds if there is one
                }

                // restart game
                if (i == numRounds) {
                    System.out.println(magentaBG + blackText + "\n---scores---");
                    System.out.printf(redText + blackBG + "Player One Score: %d   Player Two Score: %d", score1, score2);

                    if (score1 > score2) {
                        System.out.println(blackText + greenBG + "\n\nPlayer One Wins!");
                    } else if (score2 > score1) {
                        System.out.println(blackText + blueBG + "\n\nPlayer Two Wins!");
                    } else {
                        System.out.println(blackBG + redText + "\n\nTie!");
                    }

                    playAgain = true;
                } // restart

            } // numRounds for-loop

            if (playAgain == true) {
                System.out.println(redText + blackBG + "\n\nDo you want to play again? Yes or no");
                String restart = scanner.nextLine();

                if (restart.toLowerCase().equals("no")) {
                    startGame = false;
                    System.out.println(yellowText + blackBG + "Thanks for playing!");
                } else {
                    score1=0; //reset score
                    score2=0; // reset score
                    menu = true;
                    playAgain = false;
                }// restarting the game
            } // ending game or restarting based on response

        } //game loop

    }

} // class Main


