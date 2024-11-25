import java.io.FileWriter;
import java.io.IOException;

public class Output {
    private StringBuilder log;
    public String fileName = "";

    public Output() {
        log = new StringBuilder();
    }

    public void logAndPrint(String message) {
        log.append(message).append(System.lineSeparator());
        System.out.println(message);
    }

    public void writeToFile() {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(log.toString());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void setFileName(String gfileName){
        fileName = gfileName;
    }

    public void clearLog() {
        log.setLength(0);
    }

}
