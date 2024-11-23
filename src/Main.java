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

    public static String directoryShortCut = "C:\\Users\\qazia\\Desktop\\CS12\\Cheater Checker Assignmnt\\src\\";

    // Colours from https://www.mymiller.name/wordpress/java/ansi-colors/
    public static final String BRIGHT_RED = "\u001B[31;1m";

    public static ArrayList<String> comments = new ArrayList<>();

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

                comments = new ArrayList<>(Comparison.processComments(comments.toArray(new String[0])));
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
        comments.clear();
    }

    // Print statement because no one likes Java
    public static void print(String msg) {
        System.out.println(msg);
    }

    public static void CSVMaker(String fileName, Comparison fileA, Comparison fileB, double finalPercent) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("Metric,FileA,FileB,Deviation (%)\n");
            writer.append("Line Count,").append(String.valueOf(fileA.getLineCount())).append(",")
                    .append(String.valueOf(fileB.getLineCount())).append(",")
                    .append(String.format("%.2f", fileA.getLineCountDiv())).append("\n");
            writer.append("if Count,").append(String.valueOf(fileA.getIfCount())).append(",")
                    .append(String.valueOf(fileB.getIfCount())).append(",")
                    .append(String.format("%.2f", fileA.getIfCountDiv())).append("\n");
            writer.append("Curly Bracket Count,").append(String.valueOf(fileA.getCurlyBracketCount())).append(",")
                    .append(String.valueOf(fileB.getCurlyBracketCount())).append(",")
                    .append(String.format("%.2f", fileA.getCurlyBracketCountDiv())).append("\n");
            writer.append("Regular Bracket Count,").append(String.valueOf(fileA.getRegularBracketCount())).append(",")
                    .append(String.valueOf(fileB.getRegularBracketCount())).append(",")
                    .append(String.format("%.2f", fileA.getRegularBracketCountDiv())).append("\n");
            writer.append("Cyclomatic Complexity,").append(String.valueOf(fileA.getNumRegions())).append(",")
                    .append(String.valueOf(fileB.getNumRegions())).append(",")
                    .append(String.format("%.2f", fileA.getNumRegionsDiv())).append("\n");
            writer.append("Overall Similarity (%), , ,").append(String.format("%.2f", finalPercent)).append("\n");

            System.out.println("Results saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        processData(directoryShortCut + "SumOfMultiples1.java");
        Comparison fileA = new Comparison(lineCount, ifCount, curlyBracketCount, regularBracketCount, forCount, caseCount,
                whileCount, doWhileCount, elseIfCount, numRegions, new HashSet<>(comments));
        valueReset();
        print(String.valueOf(fileA));
        print("-----------------------------------------------");
        processData(directoryShortCut + "SumOfMultiples2.java");
        Comparison fileB = new Comparison(lineCount, ifCount, curlyBracketCount, regularBracketCount, forCount, caseCount,
                whileCount, doWhileCount, elseIfCount, numRegions, new HashSet<>(comments));
        valueReset();
        print(String.valueOf(fileB));
        print("-----------------------------------------------");
        processData(directoryShortCut + "SumOfMultiples3.java");
        Comparison fileC = new Comparison(lineCount, ifCount, curlyBracketCount, regularBracketCount, forCount, caseCount,
                whileCount, doWhileCount, elseIfCount, numRegions, new HashSet<>(comments));
        valueReset();
        print(String.valueOf(fileC));
        print("-----------------------------------------------");
        processData(directoryShortCut + "Spotit.java");
        Comparison fileD = new Comparison(lineCount, ifCount, curlyBracketCount, regularBracketCount, forCount, caseCount,
                whileCount, doWhileCount, elseIfCount, numRegions, new HashSet<>(comments));
        valueReset();
        print(String.valueOf(fileD));
        print("-----------------------------------------------");
        processData(directoryShortCut + "pspotit.java");
        Comparison fileE = new Comparison(lineCount, ifCount, curlyBracketCount, regularBracketCount, forCount, caseCount,
                whileCount, doWhileCount, elseIfCount, numRegions, new HashSet<>(comments));
        valueReset();
        print(String.valueOf(fileE));
        print("-----------------------------------------------");
        processData(directoryShortCut + "uspotit.java");
        Comparison fileF = new Comparison(lineCount, ifCount, curlyBracketCount, regularBracketCount, forCount, caseCount,
                whileCount, doWhileCount, elseIfCount, numRegions, new HashSet<>(comments));
        valueReset();
        print(String.valueOf(fileF));
        print("-----------------------------------------------");
        processData(directoryShortCut + "sspotit.java");
        Comparison fileG = new Comparison(lineCount, ifCount, curlyBracketCount, regularBracketCount, forCount, caseCount,
                whileCount, doWhileCount, elseIfCount, numRegions, new HashSet<>(comments));
        valueReset();
        print(String.valueOf(fileG));

        double finalPercent = fileB.deviation(fileA);


        CSVMaker("comparison_results.csv", fileA, fileB, finalPercent);

    }
}