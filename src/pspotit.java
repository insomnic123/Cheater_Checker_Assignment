/*
SPOT IT! JAVA EDITION
Course: ICS4U1-1
Date created: October 6/2024
Student/Creator: Prithviraj Banerjee
Teacher: Ms. Lal
Ethics Declaration: This code is my own work

-------

This program is a re-creation of the Spot It! card game, programmed in Java.
It prompts the user to select the common "image", or the common pair of characters, between two cards.
The user is able to play through various game modes, and select the number of images on each card.
*/


//Imports the required classes from java.util.
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

//Defines the Main class.
public class pspotit {

    //Defines the main method.
    public static void main(String[] args) {

        //Outputs the title and starts the game.
        outputScreen("title");
        startGame();

    }

    //Shows the starting menu page.
    public static void startGame() {

        //Outputs the text box and input prompt.
        outputScreen("begin", "MAIN MENU");
        outputScreen("input");

        //Defines the scanner and scans for input.
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        //Runs the appropriate method depending on the input.
        switch (input) {
            case ("P"):
                getImagesPerCard(scan);
                break;
            case ("H"):
                showInstructions(scan);
            case ("E"):
                System.exit(0);
                break;
            default:
                outputScreen("invalid");
                startGame();

        }
    }

    //Shows the instruction menu.
    public static void showInstructions(Scanner scan) {

        //Outputs the text box and input prompt.
        outputScreen("help", "INSTRUCTIONS");
        outputScreen("input");

        //Scans for input.
        String input = scan.nextLine();

        //Runs the method again if the input is invalid - otherwise, it returns to the menu.
        if (!input.equals("M")) {
            outputScreen("invalid");
            showInstructions(scan);

        }

        else startGame();

    }

    //Requests the number of images per card from the user.
    public static void getImagesPerCard(Scanner scan) {

        //Outputs the text box and input prompt.
        outputScreen("menu1", "MAIN MENU");
        outputScreen("input");

        //Scans for input and initializes the accepted inputs.
        String input = scan.nextLine();
        String[] accepted_values = {"3", "4", "6", "8"};

        //Runs the method again if the input is invalid - otherwise, it asks for the next input.
        if (!Arrays.asList(accepted_values).contains(input)) {
            outputScreen("invalid");
            getImagesPerCard(scan);

        }

        else getGameMode(scan, Integer.parseInt(input));

    }

    //Requests the game mode from the user.
    public static void getGameMode(Scanner scan, int images_per_card) {

        //Outputs the text box and input prompt.
        outputScreen("menu2", "MAIN MENU");
        outputScreen("input");

        //Scans for input and initializes the accepted inputs.
        String input = scan.nextLine();
        String[] accepted_values = {"A", "B", "C"};
        int target_rounds = -1;

        //Runs the method again if the input is invalid - otherwise, it asks for the next input.
        if (!Arrays.asList(accepted_values).contains(input)) {
            outputScreen("invalid");
            getGameMode(scan, images_per_card);

        }

        //If the game mode is either B or C, it requests for the number of rounds that the user would like to play.
        if (!input.equals("A")) {

            while (true) {

                //Outputs the text box and input prompt.
                outputScreen("menu3", "MAIN MENU");
                outputScreen("input");

                //Attempts to validate the input as an integer greater than 0 - if it succeeds, the input loop is exited.
                try {

                    target_rounds = Integer.parseInt(scan.nextLine());
                    if (target_rounds > 0) break;

                }

                catch (Exception _) {}

                //If the conditions for exiting the loop are not met, the input is considered invalid.
                outputScreen("invalid");

            }

        }

        //The game is started.
        playGame(scan, images_per_card, input, target_rounds);

    }

    //Outputs information to the screen, given certain information.
    public static void outputScreen(String display, String header, double... args) {

        //Declares and initializes several preset text options.

        String title = """
                \u001b[1m
                \u001b[38;5;11m .----------------.  .----------------.  .----------------.  .----------------.    .----------------.  .----------------.  .----------------.
                | .--------------. || .--------------. || .--------------. || .--------------. |  | .--------------. || .--------------. || .--------------. |
                | |    _______   | || |   ______     | || |     ____     | || |  _________   | |  | |     _____    | || |  _________   | || |              | |
                | |   /  ___  |  | || |  |_   __ \\   | || |   .'    `.   | || | |  _   _  |  | |  | |    |_   _|   | || | |  _   _  |  | || |      _       | |
                | |  |  (__ \\_|  | || |    | |__) |  | || |  /  .--.  \\  | || | |_/ | | \\_|  | |  | |      | |     | || | |_/ | | \\_|  | || |     | |      | |
                | |   '.___`-.   | || |    |  ___/   | || |  | |    | |  | || |     | |      | |  | |      | |     | || |     | |      | || |     | |      | |
                | |  |`\\____) |  | || |   _| |_      | || |  \\  `--'  /  | || |    _| |_     | |  | |     _| |_    | || |    _| |_     | || |     | |      | |
                | |  |_______.'  | || |  |_____|     | || |   `.____.'   | || |   |_____|    | |  | |    |_____|   | || |   |_____|    | || |     |_|      | |
                | |              | || |              | || |              | || |              | |  | |              | || |              | || |     (_)      | |
                | '--------------' || '--------------' || '--------------' || '--------------' |  | '--------------' || '--------------' || '--------------' |
                 '----------------'  '----------------'  '----------------'  '----------------'    '----------------'  '----------------'  '----------------' \u001b[0m
                ╔───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╗
                │ \u001b[38;5;40m ____    _  _ ____ ____ ____ _ ____ _  _    ____ ____    ___ _  _ ____    ___  ____ ___  _  _ _    ____ ____    ____ ____ _  _ ____ \u001b[0m      │
                │ \u001b[38;5;40m |__|    |  | |___ |__/ [__  | |  | |\\ |    |  | |___     |  |__| |___    |__] |  | |__] |  | |    |__| |__/    | __ |__| |\\/| |___    __ \u001b[0m│
                │ \u001b[38;5;40m |  |     \\/  |___ |  \\ ___] | |__| | \\|    |__| |        |  |  | |___    |    |__| |    |__| |___ |  | |  \\    |__] |  | |  | |___ \u001b[0m      │
                │                                                                                                                                           │
                │ \u001b[38;5;166m _  _ ____ ___  ____    _ _  _     _ ____ _  _ ____ \u001b[0m                                                                                      │
                │ \u001b[38;5;166m |\\/| |__| |  \\ |___    | |\\ |     | |__| |  | |__| \u001b[0m                                                                                      │
                │ \u001b[38;5;166m |  | |  | |__/ |___    | | \\|    _| |  |  \\/  |  |  \u001b[0m                                                                                     │
                ╚───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╝\u001b[0m
                """;

        String begin = "\u001b[38;5;40m Enter \"P\" to play. \u001b[0m\n" +
                "\u001b[38;5;11m Enter \"H\" to learn how to play. \u001b[0m\n" +
                "\u001b[38;5;166m Enter \"E\" to exit. \u001b[0m";

        String help = "\u001b[38;5;250m Spot It! is a matching game. \u001b[0m\n" +
                "\u001b[38;5;250m Every round, you will be given a set of two cards. \u001b[0m\n" +
                "\u001b[38;5;250m Each card will have a set of images. \u001b[0m\n" +
                "\u001b[38;5;250m You must identify the image that is common to both cards. \u001b[0m\n\n" +
                "\u001b[38;5;250m In this version of Spot It!, each image is represented by a pair of characters. \u001b[0m\n" +
                "\u001b[38;5;250m You earn points each time you correctly identify the common characters. \u001b[0m\n" +
                "\u001b[38;5;250m Remember that inputs are case-sensitive. \u001b[0m\n" +
                "\u001b[38;5;250m Have fun! \u001b[0m\n\n" +
                "\u001b[38;5;166m Enter \"M\" to exit to main menu. \u001b[0m";

        String endA = "\u001b[38;5;11m The game ended. \u001b[0m\n" +
                "\u001b[38;5;250m Your final score is %s. \u001b[0m\n" +
                "\u001b[38;5;250m You played for %.3f seconds. \u001b[0m\n";

        String end = "\u001b[38;5;11m The game ended. \u001b[0m\n" +
                "\u001b[38;5;250m Your final score is %s. \u001b[0m\n" +
                "\u001b[38;5;250m It took you %.3f seconds to finish all %s rounds. \u001b[0m\n";

        String menu1 = "\u001b[38;5;40m Enter the number of images per card. \u001b[0m\n" +
                "\u001b[38;5;250m This can be either 3, 4, 6, or 8. \u001b[0m";

        String menu2 = "\u001b[38;5;40m Enter your preferred game mode. \u001b[0m\n" +
                "\u001b[38;5;250m This can be either A, B, or C. \u001b[0m\n\n"+
                "\u001b[38;5;11m A ⇒ Infinite Mode: Play endlessly, for as long as you wish. \u001b[0m\n"+
                "\u001b[38;5;11m B ⇒ Timed Mode: Complete a certain amount of rounds as fast as you can. \u001b[0m\n"+
                "\u001b[38;5;11m C ⇒ Rapid Mode: Gain points based on the speed of your answer. \u001b[0m";

        String menu3 = "\u001b[38;5;40m Enter the number of rounds you would like to play. \u001b[0m\n" +
                "\u001b[38;5;250m This must be a value greater than 0. \u001b[0m";

        String find = "\u001b[38;5;11m Enter the pair of characters that match, or enter \"M\" to stop the game. \u001b[0m";

        String correctA = "\u001b[38;5;40m Correct! Your score is %s. \u001b[0m\n";

        String incorrectA = "\u001b[38;5;166m Incorrect! Your score is %s. \u001b[0m\n";

        String time_spent = "\u001b[38;5;250m You took %.3f seconds to answer. \u001b[0m\n";

        String earned = "\u001b[38;5;250m You earned %s points. \u001b[0m\n";

        String invalid = "\u001b[38;5;160m└─┤ 〈〈〈 INVALID INPUT 〉〉〉 ├─⛝ \u001b[0m\n\n";

        String input =  "└─➜ ";

        String output;

        //Outputs the text option that corresponds to the requested display, and formats the text with the method parameters if required.
        switch (display) {

            case ("title"): System.out.println(title); return;

            case ("begin"): output = begin; break;

            case ("help"): output = help; break;

            case ("endA"): output = String.format(endA, args[0], args[1]); break;

            case ("end"): output = String.format(end, args[0], args[1], (int)args[2]); break;

            case ("menu1"): output = menu1; break;

            case ("menu2"): output = menu2; break;

            case ("menu3"): output = menu3; break;

            case ("find"): output = find; break;

            case ("correctA"): output = String.format(correctA, args[0]); break;

            case ("incorrectA"): output = String.format(incorrectA, args[0]); break;

            case ("correctB"): output = String.format(correctA+time_spent, args[0], args[1]); break;

            case ("incorrectB"), ("incorrectC"): output = String.format(incorrectA+time_spent, args[0], args[1]); break;

            case ("correctC"): output = String.format(correctA+time_spent+earned, args[0], args[1], args[2]); break;

            case ("invalid"): output = invalid; break;

            case ("input"): output = input; break;

            default: output = display;

        }

        //Splits the output into an array of lines.
        String[] output_list = output.split("\n");

        //If the header parameter is not blank, a header will be printed first.
        if (!(header).isEmpty()) {

            System.out.println("┌───────────────┐");
            System.out.printf("│ \u001b[38;5;75m%-14s\u001b[0m│\n", header);

        }

        //If the requested display is an input arrow or an "invalid" message, there is no need to print a text box - the strings are simply printed.
        if (display.equals("input")) System.out.print(input);
        else if (display.equals("invalid")) System.out.print(invalid);

        //Otherwise, a text box is printed around the text.
        else {

            //If the requested display is a card, then no upper border is drawn.
            //To determine if the display is a card, it is checked if it contains a card border.
            if (!display.contains("┌───")) System.out.println("╔══════════════════════════════════════════════════════════════════════════════════╗");

            //Loops through each line in the array.
            for (String line : output_list) {

                //If the line is empty, an empty line is printed with a border surrounding each side.
                if (line.isEmpty()) System.out.println("║                                                                                  ║");

                //Otherwise, a formatted line is printed.
                else {

                    //Identifies the position of the leading and trailing ANSI codes in each line.
                    //In the preset values above, the ANSI codes on each line are separated from the main text by a space.
                    //The indices of these spaces are identified to determine the position of the ANSI codes.
                    int first_ansi_code_pos = line.indexOf(" ");
                    int last_ansi_code_pos = line.lastIndexOf(" ");

                    //ANSI codes contribute to the length of a line, but don't show up in the actual output.
                    //So, the substrings containing the ANSI codes are added separately - it ensures that the formatting for each line is correct.
                    System.out.printf("║" + line.substring(0, first_ansi_code_pos) + " %-80s" + line.substring(last_ansi_code_pos) + "║\n", line.substring(1 + first_ansi_code_pos, last_ansi_code_pos));

                }
            }

            //If the requested display is the user prompt during each game round, then a connecting line is drawn as the lower border/
            //Otherwise, a normal lower border is drawn.
            if (display.equals("find")) System.out.println("╠══════════════════════════════════════════════════════════════════════════════════╣");
            else System.out.println("╚══════════════════════════════════════════════════════════════════════════════════╝");

        }

    }

    //Handles outputScreen() method calls when only the "display" parameter is given.
    public static void outputScreen(String display) {

        outputScreen(display, "");

    }

    //Handles outputScreen() method calls when only the "display" and "args" parameters are given.
    public static void outputScreen(String display, double... args) {

        outputScreen(display, "", args);

    }

    //Formats the card strings.
    public static String formatCards(String[] card) {

        //Declares and initializes a frame for the card.
        String card_frame =
                  "\u001b[38;5;75m ┌"+ ("───").repeat(card.length) +"─┐ \u001b[0m\n" +
                 "\u001b[38;5;75m │ %s │ \u001b[0m\n" +
                  "\u001b[38;5;75m └"+ ("───").repeat(card.length) +"─┘ \u001b[0m";

        //Turns the list of images into a string, formats it into the card frame, and returns it.
        String card_string = String.join(" ", card);
        return String.format(card_frame, card_string);

    }

    //Runs the core Spot It! game.
    public static void playGame(Scanner scan, int images_per_card, String game_mode, int target_rounds) {

        //Declares and initializes a Random object.
        Random random = new Random();

        //Creates the deck of cards.
        String[][] card_collection = initializeCards(images_per_card);

        //Creates variables for each card and the player input.
        String[] cardA;
        String[] cardB;
        String player_input;

        //Creates variables fo keep track of the round, score, and time.
        int round = 0;
        double score = 0;
        double earned_score = 0;
        double last_recorded_time = System.currentTimeMillis();
        double total_time = 0;
        double delta_time;

        //Runs each round without restrictions if the game mode is A (infinite mode).
        //If the game mode is not A, the loops runs for the specified number of rounds.
        while (game_mode.equals("A") || round < target_rounds) {

            //Selects two random cards from the deck, ensuring they are not the same card.
            do {

                cardA = card_collection[random.nextInt(card_collection.length)];
                cardB = card_collection[random.nextInt(card_collection.length)];

            } while (Arrays.equals(cardA, cardB));

            //Outputs the textbox, cards, and input arrow, before taking the player input.
            outputScreen("find", "ROUND "+(round+1));
            outputScreen(formatCards(randomizeImages(cardA)) + "\n" + formatCards(randomizeImages(cardB)));
            outputScreen("input");
            player_input = scan.nextLine();

            //Determines the time taken to receive the input, and adds it to the total elapsed time.
            delta_time = (System.currentTimeMillis() - last_recorded_time)/1000;
            total_time += delta_time;

            //If both cards contain the player input, points are awarded.
            if (Arrays.asList(cardA).contains(player_input) && Arrays.asList(cardB).contains(player_input)) {

                //If the game mode is not C (rapid mode), 10 points are awarded.
                //If the game mode is C, then points are awarded depending on the speed of the answer and the number of images per card.
                //A longer input time results in a higher score penalty.
                //More images per card decreases this penalty, since it naturally takes the player longer to analyze cards with more images.
                earned_score = 10.0 - (game_mode.equals("C") ? 1:0)*(Math.min(Math.floor(delta_time/(images_per_card-0.5)), 5.0));
                score += earned_score;

                //The player is notified of their correct answer.
                outputScreen("correct"+game_mode, score, delta_time, earned_score);

            }

            //Determines if the player forcefully terminated the game.
            else if (player_input.equals("M")) {

                //If so, an ending screen is shown and the game is started from the beginning.
                outputScreen("endA", score, total_time);
                startGame();
                return;

            }

            //Otherwise, the answer is deemed incorrect and the player is notified of their incorrect answer.
            else {

                outputScreen("incorrect"+game_mode, score, delta_time, earned_score);

            }

            //The reference time and the round number if updated.
            last_recorded_time = System.currentTimeMillis();
            round += 1;

        }

        //After the loop ends, an ending screen is shown and the game is started from the menu.
        outputScreen("end", score, total_time, target_rounds);
        startGame();

    }

    //Creates the deck of cards.
    public static String[][] initializeCards(int images_per_card) {

        //Determines the total number of cards - this is equal to the total number of images.
        int total_cards = images_per_card * (images_per_card - 1) + 1;

        //Builds an array of images and randomizes them.
        String[] all_images = buildImageCollection(total_cards);
        String[] image_collection = randomizeImages(all_images);

        //Creates a matrix of 1s and 0s to represent the deck.
        int[][] image_matrix = buildMatrix(images_per_card);

        //Initializes a two-dimensional array to represent the deck.
        //Each card is represented by a row, which has all the card's images.
        String[][] card_collection = new String[image_matrix.length][images_per_card];

        //Loops through the image matrix and determines which image corresponds to which card.
        //It then adds these images to the corresponding card, or row, in the two-dimensional array.
        for (int row = 0; row < total_cards; row += 1) {

            int column = 0;
            for (int image = 0; image < image_matrix.length; image += 1) {

                if (image_matrix[image][row] == 1) {

                    card_collection[row][column] = image_collection[image];
                    column += 1;

                }
            }
        }

        return card_collection;

    }

    //Randomizes an array of images.
    public static String[] randomizeImages(String[] all_images) {

        //Declares and initializes the Random object, a new array, and an integer to store the random integer.
        Random random_num = new Random();
        String[] randomized_images = new String[all_images.length];
        int random_index;

        //Loops through the indices of the array.
        for (int i = 0; i < randomized_images.length; i += 1) {

            //Picks a random index, ensuring that there is not already an image at that index.
            do {

                random_index = random_num.nextInt(randomized_images.length);

            } while (randomized_images[random_index] != null);

            //Inserts the image at the random index.
            randomized_images[random_index] = all_images[i];

        }

        return randomized_images;

    }

    //Creates an array of 1s and 0s to represent a card.
    public static int[][] buildMatrix(int images_per_card) {

        //Determines the prime number associated with the given images per card, and calculates the total number of images.
        int n = images_per_card - 1;
        int total_images = n*n + n + 1;

        //Initializes a new matrix and sets the top-left corner of the matrix to "1".
        //Each column in the matrix represents a card, while each row represents an image.
        //A "1" means that the corresponding image belongs to the corresponding card.
        int[][] matrix = new int[total_images][total_images];
        matrix[0][0] = 1;

        //Loops through each column and row in the matrix to assign values of "1".
        for (int row = 0; row < images_per_card; row+=1) {

            for (int column = 0; column < n; column+=1) {

                matrix[row][column+1+row*n] = 1;
                matrix[column+1+row*n][row] = 1;

            }

        }

        //Loops through each column and row in the matrix to assign values of "1".
        for (int a = 1; a < images_per_card; a += 1) {

            for (int b = 1; b < images_per_card; b += 1) {

                for (int c = 0; c < n; c += 1) {

                    matrix[n*b+1+c][n*a+1+((a-1)*(b-1)+c)%n] = 1;

                }

            }

        }

        return matrix;

    }

    //Creates a collection of images.
    public static String[] buildImageCollection(int total_images) {

        //Initializes an array and calculates the number of variations for the second letter, given the first letter.
        //For example, if one image is "AA" and the other is "AB", then the letter "A" has two variations.
        String[] all_images = new String[total_images];
        int variations_per_letter = (int)Math.ceil(total_images/26.0);

        //Loops through the total number of images, creating individual images and adding them to the array.
        for (int i = 0; i < total_images; i+= 1) {

            //Adds the two characters together - a value of 65 is added to each character ID since 65 is the value of "A" on an ASCII table.
            all_images[i] =  String.valueOf((char)(65+i/variations_per_letter)) + String.valueOf((char)(65+i%variations_per_letter+i/variations_per_letter));

        }

        return all_images;

    }

}