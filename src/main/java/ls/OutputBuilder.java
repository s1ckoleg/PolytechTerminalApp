package ls;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OutputBuilder {
    boolean longOutput, humanOutput, reverseOutput, fileOutput;
    String inputFile, outputFile;

    public OutputBuilder(boolean longOutput, boolean humanOutput, boolean reverseOutput, boolean fileOutput, List<String> arguments) {
        this.longOutput = longOutput;
        this.humanOutput = humanOutput;
        this.reverseOutput = reverseOutput;
        this.fileOutput = fileOutput;


        if (arguments.isEmpty()) {
            File file = new File("");
            this.inputFile = file.getAbsolutePath();
        } else if (fileOutput) {
            this.inputFile = arguments.get(1);
            this.outputFile = arguments.get(0);
        } else {
            this.inputFile = arguments.get(0);
            this.outputFile = null;
        }
    }

    public void buildOutput() throws IOException {
        if (longOutput) {
            // long output class + reverse + file
            System.out.println("hi");
        } else if (humanOutput) {
            // human output class + reverse + file
            System.out.println("hi");
        } else {
            new SimpleOutput(reverseOutput, fileOutput, inputFile, outputFile).output();
        }
    }
}
