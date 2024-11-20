import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Comparison {

    // Print statement because no one likes Java
    public static void print(String msg) {
        System.out.println(msg);
    }

    private int ifCount = 0;
    private int curlyBracketCount = 0;
    private int regularBracketCount = 0;
    private int lineCount = 0;
    private int numRegions = 0; // https://www.youtube.com/watch?v=edjOiohPPw8&ab_channel=TahiaTabassum
    private int elseIfCount = 0;
    private int whileCount = 0;
    private int doWhileCount = 0;
    private int caseCount = 0;
    private int forCount = 0;

    private ArrayList<String> comments = new ArrayList<>();

    private int validCount = 0;

    private double ifCountDiv = 0;
    private double curlyBracketCountDiv = 0;
    private double regularBracketCountDiv = 0;
    private double lineCountDiv = 0;
    private double numRegionsDiv = 0;
    private double elseIfCountDiv = 0;
    private double whileCountDiv = 0;
    private double doWhileCountDiv = 0;
    private double caseCountDiv = 0;
    private double forCountDiv = 0;

    public Comparison(int lineCount, int ifCount, int curlyBracketCount, int regularBracketCount, int forCount, int
                      caseCount, int whileCount, int doWhileCount, int elseIfCount, int numRegions, ArrayList<String> comments) {
        this.lineCount = lineCount;
        this.ifCount = ifCount;
        this.curlyBracketCount = curlyBracketCount*2;
        this.regularBracketCount = regularBracketCount*2;
        this.forCount = forCount;
        this.caseCount = caseCount;
        this.whileCount = whileCount;
        this.doWhileCount = doWhileCount;
        this.elseIfCount = elseIfCount;
        this.numRegions = numRegions;
        this.comments = comments;
    }

    public double getPercentDiff(int valueA, int valueB) {
        if (valueA > 0) {
            return (Math.abs(valueA - valueB) / (double) valueA) * 100;
        }
        else if (valueB > 0) {
            return (Math.abs(valueA-valueB) / (double) valueB) * 100;
        }
        return 43110;
    }

    public int getIfCount() {
        return ifCount;
    }

    public int getCurlyBracketCount() {
        return curlyBracketCount;
    }

    public int getRegularBracketCount() {
        return regularBracketCount;
    }

    public int getLineCount() {
        return lineCount;
    }

    public double getIfCountDiv() {
        return ifCountDiv;
    }

    public double getCurlyBracketCountDiv() {
        return curlyBracketCountDiv;
    }

    public double getLineCountDiv() {
        return lineCountDiv;
    }

    public double getRegularBracketCountDiv() {
        return regularBracketCountDiv;
    }

    public String toString() {
        return ("Num Lines: " + this.lineCount + " | if Count: " + this.ifCount + " | curlyBracketCount: " + this.curlyBracketCount
        + " | regBracketCount: " + this.regularBracketCount + " | Comments " + this.comments);
    }

    public double deviation(Comparison x) {
        double totalWeight = 0.0;
        double weightedSum = 0.0;

        // Assign weights to each metric
        double lineCountWeight = 1.2;
        double ifCountWeight = 1.5;
        double elseIfCountWeight = 1.3;
        double curlyBracketCountWeight = 0.7;
        double regularBracketCountWeight = 0.6;
        double numRegionsWeight = 1.0;
        double forCountWeight = 1.4;
        double whileCountWeight = 1.4;
        double doWhileCountWeight = 1.1;
        double caseCountWeight = 1.2;

        // Calculate and add each weighted contribution if applicable
        lineCountDiv = getPercentDiff(this.lineCount, x.getLineCount());
        weightedSum += lineCountDiv * lineCountWeight;
        totalWeight += lineCountWeight;

        if (this.ifCount > 0 || x.getIfCount() > 0) {
            validCount++;
            ifCountDiv = getPercentDiff(this.ifCount, x.getIfCount());
            weightedSum += ifCountDiv * ifCountWeight;
            totalWeight += ifCountWeight;
        }

        if (this.elseIfCount > 0 || x.getElseIfCount() > 0) {
            validCount++;
            elseIfCountDiv = getPercentDiff(this.elseIfCount, x.getElseIfCount());
            weightedSum += elseIfCountDiv * elseIfCountWeight;
            totalWeight += elseIfCountWeight;
        }

        curlyBracketCountDiv = getPercentDiff(this.curlyBracketCount, x.getCurlyBracketCount());
        weightedSum += curlyBracketCountDiv * curlyBracketCountWeight;
        totalWeight += curlyBracketCountWeight;

        regularBracketCountDiv = getPercentDiff(this.regularBracketCount, x.getRegularBracketCount());
        weightedSum += regularBracketCountDiv * regularBracketCountWeight;
        totalWeight += regularBracketCountWeight;

        numRegionsDiv = getPercentDiff(this.numRegions + 1, x.getNumRegions() + 1);
        weightedSum += numRegionsDiv * numRegionsWeight;
        totalWeight += numRegionsWeight;

        if (this.forCount > 0 || x.getForCount() > 0) {
            validCount++;
            forCountDiv = getPercentDiff(this.forCount, x.getForCount());
            weightedSum += forCountDiv * forCountWeight;
            totalWeight += forCountWeight;
        }

        if (this.whileCount > 0 || x.getWhileCount() > 0) {
            validCount++;
            whileCountDiv = getPercentDiff(this.whileCount, x.getWhileCount());
            weightedSum += whileCountDiv * whileCountWeight;
            totalWeight += whileCountWeight;
        }

        if (this.doWhileCount > 0 || x.getDoWhileCount() > 0) {
            validCount++;
            doWhileCountDiv = getPercentDiff(this.doWhileCount, x.getDoWhileCount());
            weightedSum += doWhileCountDiv * doWhileCountWeight;
            totalWeight += doWhileCountWeight;
        }

        if (this.caseCount > 0 || x.getCaseCount() > 0) {
            validCount++;
            caseCountDiv = getPercentDiff(this.caseCount, x.getCaseCount());
            weightedSum += caseCountDiv * caseCountWeight;
            totalWeight += caseCountWeight;
        }

        double weightedAverage = weightedSum / totalWeight;

        double confidenceScore = 100 - weightedAverage;

        if (confidenceScore < 0) {
            confidenceScore = 0;
        }

        System.out.printf("The weighted average deviation is: %.2f%%%n", weightedAverage);
        System.out.printf("Confidence score of cheating likelihood: %.2f%%%n", confidenceScore);

        return weightedAverage;
    }

    public double getNumRegionsDiv() {
        return numRegionsDiv;
    }

    public double getElseIfCountDiv() {
        return elseIfCountDiv;
    }


    public double getWhileCountDiv() {
        return whileCountDiv;
    }


    public double getDoWhileCountDiv() {
        return doWhileCountDiv;
    }

    public double getCaseCountDiv() {return caseCountDiv;}

    public double getForCountDiv() {
        return forCountDiv;
    }

    public int getForCount() {
        return forCount;
    }

    public int getCaseCount() {
        return caseCount;
    }

    public int getWhileCount() {
        return whileCount;
    }

    public int getNumRegions() {
        return numRegions;
    }

    public int getElseIfCount() {
        return elseIfCount;
    }

    public int getDoWhileCount() {
        return doWhileCount;
    }


}
