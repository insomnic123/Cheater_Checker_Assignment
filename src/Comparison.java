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

    private int ifCountDiv = 0;
    private int curlyBracketCountDiv = 0;
    private int regularBracketCountDiv = 0;
    private int lineCountDiv = 0;

    public Comparison(int lineCount, int ifCount, int curlyBracketCount, int regularBracketCount) {
        this.lineCount = lineCount;
        this.ifCount = ifCount;
        this.curlyBracketCount = curlyBracketCount;
        this.regularBracketCount = regularBracketCount;
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

    public int getIfCountDiv() {
        return ifCountDiv;
    }

    public void setIfCountDiv(int ifCountDiv) {
        this.ifCountDiv = ifCountDiv;
    }

    public int getCurlyBracketCountDiv() {
        return curlyBracketCountDiv;
    }

    public void setCurlyBracketCountDiv(int curlyBracketCountDiv) {
        this.curlyBracketCountDiv = curlyBracketCountDiv;
    }

    public int getLineCountDiv() {
        return lineCountDiv;
    }

    public void setLineCountDiv(int lineCountDiv) {
        this.lineCountDiv = lineCountDiv;
    }

    public int getRegularBracketCountDiv() {
        return regularBracketCountDiv;
    }

    public void setRegularBracketCountDiv(int regularBracketCountDiv) {
        this.regularBracketCountDiv = regularBracketCountDiv;
    }
}
