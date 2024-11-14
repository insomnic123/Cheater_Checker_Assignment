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
                      caseCount, int whileCount, int doWhileCount, int elseIfCount, int numRegions) {
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
    }

    public double getPercentDiff(int valueA, int valueB) {
        return (Math.abs(valueA - valueB) / (double) valueA)*100;
    }

    public int getIfCount() {
        return ifCount;
    }

    public void setIfCount(int ifCount) {
        this.ifCount = ifCount;
    }

    public int getCurlyBracketCount() {
        return curlyBracketCount;
    }

    public void setCurlyBracketCount(int curlyBracketCount) {
        this.curlyBracketCount = curlyBracketCount;
    }

    public int getRegularBracketCount() {
        return regularBracketCount;
    }

    public void setRegularBracketCount(int regularBracketCount) {
        this.regularBracketCount = regularBracketCount;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public double getIfCountDiv() {
        return ifCountDiv;
    }

    public void setIfCountDiv(int ifCountDiv) {
        this.ifCountDiv = ifCountDiv;
    }

    public double getCurlyBracketCountDiv() {
        return curlyBracketCountDiv;
    }

    public void setCurlyBracketCountDiv(int curlyBracketCountDiv) {
        this.curlyBracketCountDiv = curlyBracketCountDiv;
    }

    public double getLineCountDiv() {
        return lineCountDiv;
    }

    public void setLineCountDiv(int lineCountDiv) {
        this.lineCountDiv = lineCountDiv;
    }

    public double getRegularBracketCountDiv() {
        return regularBracketCountDiv;
    }

    public void setRegularBracketCountDiv(int regularBracketCountDiv) {
        this.regularBracketCountDiv = regularBracketCountDiv;
    }

    public String toString() {
        return ("Num Lines: " + lineCount + " | if Count: " + ifCount + " | curlyBracketCount: " + curlyBracketCount
        + " | regBracketCount: " + regularBracketCount);
    }

    public void deviation(Comparison x) {
        lineCountDiv = getPercentDiff(this.lineCount, x.getLineCount());
        System.out.printf("The line count differs by %.2f%%%n", lineCountDiv);

        ifCountDiv = getPercentDiff(this.ifCount, x.getIfCount());
        System.out.printf("The if count differs by %.2f%%$n", ifCountDiv);

        curlyBracketCountDiv = getPercentDiff(this.curlyBracketCount, x.getCurlyBracketCount());
        System.out.printf("The number of curly brackets differ by %.2f%%%n", curlyBracketCountDiv);

        regularBracketCountDiv = getPercentDiff(this.regularBracketCount, x.getRegularBracketCount());
        System.out.printf("The number of regular brackets differ by %.2f%%%n", regularBracketCountDiv);

        numRegionsDiv = getPercentDiff(this.numRegions + 1, x.getNumRegions() + 1);
        System.out.printf("The Cyclomatic Complexity differs by %.2f%%%n", numRegionsDiv);

        double average = (lineCountDiv + ifCountDiv + curlyBracketCountDiv + regularBracketCountDiv) / 4;

        System.out.printf("The average deviation is: %.2f%%%n", average);

    }

    public double getNumRegionsDiv() {
        return numRegionsDiv;
    }

    public void setNumRegionsDiv(double numRegionsDiv) {
        this.numRegionsDiv = numRegionsDiv;
    }

    public double getElseIfCountDiv() {
        return elseIfCountDiv;
    }

    public void setElseIfCountDiv(double elseIfCountDiv) {
        this.elseIfCountDiv = elseIfCountDiv;
    }

    public double getWhileCountDiv() {
        return whileCountDiv;
    }

    public void setWhileCountDiv(double whileCountDiv) {
        this.whileCountDiv = whileCountDiv;
    }

    public double getDoWhileCountDiv() {
        return doWhileCountDiv;
    }

    public void setDoWhileCountDiv(double doWhileCountDiv) {
        this.doWhileCountDiv = doWhileCountDiv;
    }

    public double getCaseCountDiv() {
        return caseCountDiv;
    }

    public void setCaseCountDiv(double caseCountDiv) {
        this.caseCountDiv = caseCountDiv;
    }

    public double getForCountDiv() {
        return forCountDiv;
    }

    public void setForCountDiv(double forCountDiv) {
        this.forCountDiv = forCountDiv;
    }

    public int getForCount() {
        return forCount;
    }

    public void setForCount(int forCount) {
        this.forCount = forCount;
    }

    public int getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(int caseCount) {
        this.caseCount = caseCount;
    }

    public int getWhileCount() {
        return whileCount;
    }

    public void setWhileCount(int whileCount) {
        this.whileCount = whileCount;
    }

    public int getNumRegions() {
        return numRegions;
    }

    public void setNumRegions(int numRegions) {
        this.numRegions = numRegions;
    }

    public int getElseIfCount() {
        return elseIfCount;
    }

    public void setElseIfCount(int elseIfCount) {
        this.elseIfCount = elseIfCount;
    }

    public int getDoWhileCount() {
        return doWhileCount;
    }

    public void setDoWhileCount(int doWhileCount) {
        this.doWhileCount = doWhileCount;
    }
}
