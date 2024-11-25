/*
TODO : Add info here
 */
import java.io.*;
import java.util.*;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static int ifCount = 0;
    public static int curlyBracketCount = 0;
    public static int regularBracketCount = 0;
    public static int lineCount = 0;
    public static int numRegions = 0; // https://www.youtube.com/watch?v=edjOiohPPw8&ab_channel=TahiaTabassum
    public static int elseIfCount = 0;
    public static int whileCount = 0;
    public static int doWhileCount = 0;
    public static int caseCount = 0;
    public static int forCount = 0;

    public static Output output = new Output();


    public static String directoryShortCut = "";
    public static String extension = "";

    // Colours from https://www.mymiller.name/wordpress/java/ansi-colors/
    public static final String BRIGHT_RED = "\u001B[31;1m";
    public static final String RESET = "\u001B[0m";

    public static ArrayList<String> comments = new ArrayList<>();

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

            // Reset values
            valueReset();
            boolean skib = false;

            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                String[] values = line.split(" ");

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
                    whileCount, doWhileCount, elseIfCount, numRegions, new HashSet<>(comments));
        } catch (FileNotFoundException e) {
            print(BRIGHT_RED + "File not found: " + e.getMessage() + RESET);
            return null;
        }
    }

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

    public static void createFiles(String directoryName) {
        processData(directoryShortCut + directoryName);
        Comparison file1 = new Comparison(lineCount, ifCount, curlyBracketCount, regularBracketCount, forCount, caseCount,
                whileCount, doWhileCount, elseIfCount, numRegions, new HashSet<>(comments));
        valueReset();
    }

    public static int startMenu() {
        print("Please select an option from one of the following:");
        print("--------------------------------------------------");
        print("[1]  |        Add a file for Comparison          |");
        print("[2]  |           Compare two files               |");
        print("[3]  |             Clear Outputs                 |");
        print("[4]  |                 Quit                      |");

        int input;

        // Ensuring the inputted values are compatible with the code
        while (true) {
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                while (input < 1 || input > 4) {
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
        Map<String, Comparison> files = new HashMap<>();
        boolean skib = true;

        print("Please input the type of files you wish to compare today! (ex. txt, java, py - it's case sensitive!");
        extension = "." + scanner.nextLine();

        print("Please enter the directory of the file in which you have added all the files you wish to compare: ");
        directoryShortCut = scanner.nextLine() + "\\";

        print("Please enter the name of the *txt* file you wish to output the results to:");
        String outputFile = directoryShortCut + scanner.nextLine() + ".txt";
        output.setFileName(outputFile);
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

                    print("Available files: " + files.keySet());
                    print("Enter the name of the first file:");
                    String file1 = scanner.nextLine().toLowerCase();
                    print("Enter the name of the second file:");
                    String file2 = scanner.nextLine().toLowerCase();

                    if (files.containsKey(file1) && files.containsKey(file2)) {

                        print("Please enter the confidence/threshold to determine cheating (50% - 70% recommended for larger files, " +
                                "80% - 90% recommended for smaller files)");
                        int guess = scanner.nextInt();
                        double similarity = files.get(file1).deviation(files.get(file2), output, file1, file2);

                        if (similarity >= guess) {
                            output.logAndPrint(String.format("Based on the threshold, it's likely that the individuals cheated.%n"));
                        }
                        else {
                            output.logAndPrint(String.format("Based on the threshold, it's unlikely that the individuals cheated.%n"));
                        }

                        output.writeToFile();


                    } else {
                        print("One or both files not found.");
                    }
                    break;

                case 3:
                    output.clearLog();
                    print("Output has been cleared!");

                case 4:
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