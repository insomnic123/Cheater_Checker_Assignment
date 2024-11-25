import java.util.*;

public class Comparison {

    // Count for all metrics
    private int ifCount = 0;
    private int curlyBracketCount = 0;
    private int regularBracketCount = 0;
    private int lineCount = 0;
    private int numRegions = 0; // Cyclomatic Complexity, as per: https://www.youtube.com/watch?v=edjOiohPPw8&ab_channel=TahiaTabassum
    private int elseIfCount = 0;
    private int whileCount = 0;
    private int doWhileCount = 0;
    private int caseCount = 0;
    private int forCount = 0;

    private ArrayList<String> comments = new ArrayList<>(); // Arraylist for comments

    // Variables to store the deviation of factors between two files
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

    private double commentSimilartiy = 0;

    // Constructor
    public Comparison(int lineCount, int ifCount, int curlyBracketCount, int regularBracketCount, int forCount,
                      int caseCount, int whileCount, int doWhileCount, int elseIfCount, int numRegions, Set<String> comments) {
        this.lineCount = lineCount;
        this.ifCount = ifCount;
        this.curlyBracketCount = curlyBracketCount * 2;
        this.regularBracketCount = regularBracketCount * 2;
        this.forCount = forCount;
        this.caseCount = caseCount;
        this.whileCount = whileCount;
        this.doWhileCount = doWhileCount;
        this.elseIfCount = elseIfCount;
        this.numRegions = numRegions;
        this.comments = new ArrayList<>(comments); // Convert Set back to List for compatibility
    }

    // Returns Deviation between Two Factors
    public double getPercentDiff(int valueA, int valueB) {
        if (valueA + valueB > 0) {
            return (Math.abs(valueA - valueB) / ((valueA + valueB) / 2.0)) * 100; // Ensures deviation is the same both ways
        }
        return 43110;
    }

    // To String Methods
    public String toString() {
        return ("Num Lines: " + this.lineCount + " | if Count: " + this.ifCount + " | curlyBracketCount: " + this.curlyBracketCount
        + " | regBracketCount: " + this.regularBracketCount + " | Comments " + this.comments);
    }

    // Using Jaccard Similarity to Calculate Comment Similarity, as per: https://www.youtube.com/watch?v=YotbvhndSf4&ab_channel=KimberlyFessel
    public static double calculateJaccardSimilarity(Set<String> set1, Set<String> set2) {
        if (set1.isEmpty() && set2.isEmpty()) {
            return 100.0; // Return 100 if there are no comments
        }

        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        return (double) intersection.size() / union.size() * 100;
    }

    // Deviation Method calculates teh final score -- Output taken from Main to ensure all outputs go to the same file
    public double deviation(Comparison x, Output output /* Recommended by ChatGPT */, String file1name, String file2name) {
        double totalWeight = 0.0; // Total Weight of Applicable Factors
        double weightedSum = 0.0; // Sum of All Valid Weights

        // Assign weights to each metric
        double lineCountWeight = 1.0;
        double ifCountWeight = 1.5;
        double elseIfCountWeight = 1.5;
        double curlyBracketCountWeight = 0.5;
        double regularBracketCountWeight = 0.5;
        double numRegionsWeight = 1.5;
        double forCountWeight = 1.4;
        double whileCountWeight = 1.4;
        double doWhileCountWeight = 1.2;
        double caseCountWeight = 1.2;
        double commentSimilarityWeight = 0.8;

        // Converts comments to Sets and Processes them
        Set<String> thisCommentSet = processComments(this.comments.toArray(new String[0]));
        Set<String> xCommentSet = processComments(x.comments.toArray(new String[0]));
        double commentSimilarity = calculateJaccardSimilarity(thisCommentSet, xCommentSet);

        weightedSum += commentSimilarity * commentSimilarityWeight;
        totalWeight += commentSimilarityWeight;

        // Calculates deviation for things which are for certain in the code
        lineCountDiv = getPercentDiff(this.lineCount, x.getLineCount());
        weightedSum += lineCountDiv * lineCountWeight;
        totalWeight += lineCountWeight;

        curlyBracketCountDiv = getPercentDiff(this.curlyBracketCount, x.getCurlyBracketCount());
        weightedSum += curlyBracketCountDiv * curlyBracketCountWeight;
        totalWeight += curlyBracketCountWeight;

        regularBracketCountDiv = getPercentDiff(this.regularBracketCount, x.getRegularBracketCount());
        weightedSum += regularBracketCountDiv * regularBracketCountWeight;
        totalWeight += regularBracketCountWeight;

        numRegionsDiv = getPercentDiff(this.numRegions + 1, x.getNumRegions() + 1);
        weightedSum += numRegionsDiv * numRegionsWeight;
        totalWeight += numRegionsWeight;

        // Calculates deviation for things which may be in the code
        if (this.ifCount > 0 || x.getIfCount() > 0) {
            ifCountDiv = getPercentDiff(this.ifCount, x.getIfCount());
            weightedSum += ifCountDiv * ifCountWeight;
            totalWeight += ifCountWeight;
        }

        if (this.elseIfCount > 0 || x.getElseIfCount() > 0) {
            elseIfCountDiv = getPercentDiff(this.elseIfCount, x.getElseIfCount());
            weightedSum += elseIfCountDiv * elseIfCountWeight;
            totalWeight += elseIfCountWeight;
        }


        if (this.forCount > 0 || x.getForCount() > 0) {
            forCountDiv = getPercentDiff(this.forCount, x.getForCount());
            weightedSum += forCountDiv * forCountWeight;
            totalWeight += forCountWeight;
        }

        if (this.whileCount > 0 || x.getWhileCount() > 0) {
            whileCountDiv = getPercentDiff(this.whileCount, x.getWhileCount());
            weightedSum += whileCountDiv * whileCountWeight;
            totalWeight += whileCountWeight;
        }

        if (this.doWhileCount > 0 || x.getDoWhileCount() > 0) {
            doWhileCountDiv = getPercentDiff(this.doWhileCount, x.getDoWhileCount());
            weightedSum += doWhileCountDiv * doWhileCountWeight;
            totalWeight += doWhileCountWeight;
        }

        if (this.caseCount > 0 || x.getCaseCount() > 0) {
            caseCountDiv = getPercentDiff(this.caseCount, x.getCaseCount());
            weightedSum += caseCountDiv * caseCountWeight;
            totalWeight += caseCountWeight;
        }

        double weightedAverage = weightedSum / totalWeight; // Finds the average deviation between two files after considering all factors

        double confidenceScore = 100 - weightedAverage; // Incredibly complex calculation to find confidence

        // Makes sure negative confidence scores aren't presented
        if (confidenceScore < 0) {
            confidenceScore = 0;
        }

        // Outputs and prints the details from the calculations
        output.logAndPrint(String.format("Files: %s and %s", file1name, file2name));
        output.logAndPrint(String.format("The weighted average deviation is: %.2f%%", weightedAverage));
        output.logAndPrint(String.format("Confidence score of cheating likelihood: %.2f%%", confidenceScore));

        output.writeToFile(); // Writes everything to the file

        return confidenceScore;
    }

    // Processes comments by spliting up the comments into words
    public static Set<String> processComments(String[] comments) {
        Set<String> wordSet = new HashSet<>();
        for (String comment : comments) {
            if (comment != null && !comment.isBlank()) {
                String[] words = comment.toLowerCase().split("\\W+");
                wordSet.addAll(Arrays.asList(words));
            }
        }
        return wordSet;
    }

    // getters setters
    public double getNumRegionsDiv() {return numRegionsDiv;}

    public double getElseIfCountDiv() {return elseIfCountDiv;}

    public double getWhileCountDiv() {return whileCountDiv;}

    public double getDoWhileCountDiv() {return doWhileCountDiv;}

    public double getCaseCountDiv() {return caseCountDiv;}

    public double getForCountDiv() {return forCountDiv;}

    public int getForCount() {return forCount;}

    public int getCaseCount() {return caseCount;}

    public int getWhileCount() {return whileCount;}

    public int getNumRegions() {return numRegions;}

    public int getElseIfCount() {return elseIfCount;}

    public int getDoWhileCount() {return doWhileCount;}

    public int getIfCount() {return ifCount;}

    public int getCurlyBracketCount() {return curlyBracketCount;}

    public int getRegularBracketCount() {return regularBracketCount;}

    public int getLineCount() {return lineCount;}

    public double getIfCountDiv() {return ifCountDiv;}

    public double getCurlyBracketCountDiv() {return curlyBracketCountDiv;}

    public double getLineCountDiv() {return lineCountDiv;}

    public double getRegularBracketCountDiv() {return regularBracketCountDiv;}

}
