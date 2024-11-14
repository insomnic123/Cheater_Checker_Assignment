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
        } catch (FileNotFoundException e) {
            print(BRIGHT_RED + "File not found: " + e.getMessage()); // Catches errors
        } catch (NumberFormatException e) {
            print(BRIGHT_RED + "Error parsing year: " + e.getMessage());
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
    }

    // Print statement because no one likes Java
    public static void print(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        processData("C:\\Users\\qazia\\Desktop\\CS12\\Cheater Checker Assignmnt\\src\\SumOfMultiples1.java");
        Comparison fileA = new Comparison(lineCount, ifCount, curlyBracketCount, regularBracketCount, forCount, caseCount,
                whileCount, doWhileCount, elseIfCount, numRegions);
        valueReset();
        print(String.valueOf(fileA));
        print("-----------------------------------------------");
        processData("C:\\Users\\qazia\\Desktop\\CS12\\Cheater Checker Assignmnt\\src\\SumOfMultiples2.java");
        Comparison fileB = new Comparison(lineCount, ifCount, curlyBracketCount, regularBracketCount, forCount, caseCount,
                whileCount, doWhileCount, elseIfCount, numRegions);
        valueReset();
        print(String.valueOf(fileB));
        print("-----------------------------------------------");
        processData("C:\\Users\\qazia\\Desktop\\CS12\\Cheater Checker Assignmnt\\src\\SumOfMultiples3.java");
        Comparison fileC = new Comparison(lineCount, ifCount, curlyBracketCount, regularBracketCount, forCount, caseCount,
                whileCount, doWhileCount, elseIfCount, numRegions);
        valueReset();
        print(String.valueOf(fileC));

        fileA.deviation(fileC);

    }
}