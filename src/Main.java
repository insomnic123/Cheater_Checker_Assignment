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

    // Colours from https://www.mymiller.name/wordpress/java/ansi-colors/
    public static final String RESET = "\u001B[0m";
    public static final String BRIGHT_RED = "\u001B[31;1m";
    public static final String BRIGHT_BLUE = "\u001B[34;1m";
    public static final String BRIGHT_MAGENTA = "\u001B[35;1m";
    public static final String BRIGHT_WHITE = "\u001B[37;1m";

    public static void processData(String filePath) {
        try {
            File myFile = new File(filePath);
            Scanner scnr = new Scanner(myFile);

            // Checks if file exists and ends the program if it doesn't
            if (myFile.exists()) {
                print("File name: " + myFile.getName());
                print("File size in bytes: " + myFile.length());
                // if the first line is a header print it out:
                print("Header: " + scnr.nextLine());
                print("---------------------------------------");
            } else {
                print(BRIGHT_RED + "The file does not exist.");
                System.exit(43110); //  Get it cuz 43110 looks like hello :D
            }
            if (!scnr.hasNext()) {
                print(BRIGHT_RED + "Error: File is empty");
                System.exit(43110);
            }

            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                String[] values = line.split(" ");

                if (line.contains("if")) {
                    ifCount++;
                }

                if (line.contains("{")) {
                    curlyBracketCount++;
                }

                if (line.contains("(")) {
                    regularBracketCount++;
                }

                lineCount++;
            }
        } catch (FileNotFoundException e) {
            print(BRIGHT_RED + "File not found: " + e.getMessage()); // Catches errors
        } catch (NumberFormatException e) {
            print(BRIGHT_RED + "Error parsing year: " + e.getMessage());
        }
    }

    // Print statement because no one likes Java
    public static void print(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        processData("C:\\Users\\qazia\\Desktop\\CS12\\Cheater Checker Assignmnt\\src\\SumOfMultiples1.java");
        Comparison fileA = new Comparison(lineCount, ifCount, curlyBracketCount, regularBracketCount);

    }
}