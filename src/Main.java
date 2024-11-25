/*
Author: Qazi Ahmed Ayan
Teacher: M. Lal
Course: CS12
Program Name: Cheater Checker
Program Description: An incredibly simple programming cheater checker which takes several file types into consideration and
generates a confidence score for how likely it is that two programs have been copied off of one another.
 */
import java.io.*;
import java.util.*;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    // Declaring all the factors considered
    public static int ifCount = 0;
    public static int curlyBracketCount = 0;
    public static int regularBracketCount = 0;
    public static int lineCount = 0;
    public static int numRegions = 0; // Used to calculate Cyclomatic Complexity, as per: https://www.youtube.com/watch?v=edjOiohPPw8&ab_channel=TahiaTabassum
    public static int elseIfCount = 0;
    public static int whileCount = 0;
    public static int doWhileCount = 0;
    public static int caseCount = 0;
    public static int forCount = 0;

    public static Output output = new Output();

    // Declarations to ensure the user has to input minimal information
    public static String directoryShortCut = "";
    public static String extension = "";

    // Colours from https://www.mymiller.name/wordpress/java/ansi-colors/
    public static final String BRIGHT_RED = "\u001B[31;1m";
    public static final String RESET = "\u001B[0m";

    public static ArrayList<String> comments = new ArrayList<>();

    // Method for processing the data
    public static Comparison processData(String filePath) {
        try {
            File myFile = new File(filePath);
            Scanner scnr = new Scanner(myFile);

            // Checks if file exists
            if (!myFile.exists()) {
                print(BRIGHT_RED + "The file does not exist.");
                return null;
            }
            if (!scnr.hasNext()) {
                print(BRIGHT_RED + "Error: File is empty");
                return null;
            }
            // Reset values again, in case
            valueReset();
            boolean skib = false;

            // Counts the instances of certain attributes
            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                String[] values = line.split(" ");


                // Checks if we're in a block comment
                if (skib) {
                    comments.add(line);
                    if (line.contains("*/")) {
                        skib = false;
                    }
                } else if (line.contains("//")) {
                    comments.add(line.substring(line.indexOf("//")));
                } else if (line.contains("/*")) {
                    skib = true;
                    comments.add(line.substring(line.indexOf("/*")));
                    if (line.contains("*/")) {
                        skib = false;
                    }
                }

                if (line.contains("if")) {
                    ifCount++;
                    numRegions++;
                }
                if (line.contains("else if")) {
                    elseIfCount++;
                    numRegions++;
                }
                if (line.contains("while")) {
                    whileCount++;
                    numRegions++;
                }
                if (line.contains("do")) {
                    doWhileCount++;
                    numRegions++;
                }
                if (line.contains("for")) {
                    forCount++;
                    numRegions++;
                }
                if (line.contains("case")) {
                    caseCount++;
                    numRegions++;
                }
                if (line.contains("{")) {
                    curlyBracketCount++;
                }
                if (line.contains("(")) {
                    regularBracketCount++;
                }

                lineCount++;
            }

            return new Comparison(lineCount, ifCount, curlyBracketCount, regularBracketCount, forCount, caseCount,
                    whileCount, doWhileCount, elseIfCount, numRegions, new HashSet<>(comments)); // Returns Comparison Type and instantiates a Comparison object with all the data of the file; info per https://www.w3schools.com/java/java_hashset.asp
        } catch (FileNotFoundException e) {
            print(BRIGHT_RED + "File not found: " + e.getMessage() + RESET);
            return null;
        }
    }

    // Resets all values
    public static void valueReset() {
        lineCount = 0;
        ifCount = 0;
        curlyBracketCount = 0;
        regularBracketCount = 0;
        forCount = 0;
        caseCount = 0;
        whileCount = 0;
        doWhileCount = 0;
        elseIfCount = 0;
        numRegions = 0;
        comments.clear();
    }

    // Print statement because no one likes Java
    public static void print(String msg) {
        System.out.println(msg);
    }

    // Start menu
    public static int startMenu() {
        print("Please select an option from one of the following:");
        print("--------------------------------------------------");
        print("[1]  |        Add a file for Comparison          |");
        print("[2]  |           Compare two files               |");
        print("[4]  |                 Quit                      |");

        int input;

        // Ensuring the inputted values are compatible with the code
        while (true) {
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                while (input < 1 || input > 3) {
                    print("That's an invalid input! Please put an integer between 1 and 3");
                    input = scanner.nextInt();
                }
                break;

            } catch (InputMismatchException e) {
                print("That's an invalid input! Please put an integer between 1 and 3");
                scanner.nextLine();
            }
        }
        return (input);
    }

    public static void main(String[] args) {
        Map<String, Comparison> files = new HashMap<>(); // Hashmap knowledge from: https://www.w3schools.com/java/java_hashmap.asp
        boolean skib = true;

        print("Please input the type of files you wish to compare today! (ex. txt, java, py - it's case sensitive!");
        extension = "." + scanner.nextLine();

        print("Please enter the directory of the file in which you have added all the files you wish to compare: ");
        directoryShortCut = scanner.nextLine() + "\\";

        print("Please enter the name of the file you wish to output the results to:");
        String outputFile = directoryShortCut + scanner.nextLine() + ".txt";
        output.createFile(outputFile);
        print(outputFile);

        while (skib) {
            int input = startMenu();

            switch (input) {
                case 1:
                    print("Enter the name you want to assign to this file:");
                    String name = scanner.nextLine();
                    print("Enter the actual name of the file (be EXACT- it's case sensitive :)):");
                    String filePath = scanner.nextLine();

                    Comparison comp = processData(directoryShortCut + filePath + extension);
                    if (comp != null) {
                        files.put(name.toLowerCase(), comp);
                        print("File added successfully !");
                    } else {
                        print("Failed to process the file.");
                    }
                    break;

                case 2:
                    if (files.size() < 2) {
                        print("Not enough files to compare. Add at least two files.");
                        break;
                    }

                    // Gets information for comparisons
                    print("Available files: " + files.keySet());
                    print("Enter the name of the first file:");
                    String file1 = scanner.nextLine().toLowerCase();
                    print("Enter the name of the second file:");
                    String file2 = scanner.nextLine().toLowerCase();

                    // Compares two given files and returns the adequate information, as well as saves it to a TXT file
                    if (files.containsKey(file1) && files.containsKey(file2)) {
                        print("Please enter the confidence/threshold to determine cheating (50% - 70% recommended for larger files, " +
                                "80% - 90% recommended for smaller files)");

                        int guess = scanner.nextInt();

                        double confidence = files.get(file1).deviation(files.get(file2), output, file1, file2);

                        // Outputs if cheating is likely or not
                        if (confidence >= guess) {
                            output.logAndPrint(String.format("Based on the threshold, it's likely that the individuals cheated.%n"));
                        }
                        else {
                            output.logAndPrint(String.format("Based on the threshold, it's unlikely that the individuals cheated.%n"));
                        }

                        output.writeToFile(); // Writes output to file
                    } else {
                        print("One or both files not found.");
                    }
                    break;

                case 3:
                    print("k.");
                    skib = false;
                    break;

                default:
                    print("Invalid choice. Please try again.");
                    break;

            }
        }
    }

}