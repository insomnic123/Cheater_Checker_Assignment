import java.io.*;
import java.io.IOException;

// File creating and writing info as per: https://www.w3schools.com/java/java_files_create.asp
public class Output {
    private StringBuilder log;
    public String fileName = "";

    // Creates file
    public void createFile(String dirAndName) {
        File file = new File(dirAndName);
        fileName = file.getName();
    }

    // Constructor
    public Output() {
        log = new StringBuilder();
    }

    // Prints information and puts it into the TXT file
    public void logAndPrint(String message) {
        log.append(message).append(System.lineSeparator());
        System.out.println(message);
    }

    // Writes information to the file
    public void writeToFile() {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(log.toString());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}
