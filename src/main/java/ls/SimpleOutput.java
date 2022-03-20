package ls;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleOutput {
    boolean reverseOutput, fileOutput;
    String inputFile, outputFile;

    public SimpleOutput(boolean reverseOutput, boolean fileOutput, String inputFile, String outputFile) {
        this.reverseOutput = reverseOutput;
        this.fileOutput = fileOutput;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public void output() throws IOException {
        if (fileOutput) {
            textFileOutput();
        } else {
            consoleOutput();
        }
    }

    private void consoleOutput() {
        File[] filesArray = new SetUpDirectory(inputFile).getFilesArray();

        if (reverseOutput) {
            for (int i = filesArray.length - 1; i >= 0; i--) {
                System.out.println(filesArray[i].getName());
            }
        } else {
            for (int i = 0; i <= filesArray.length - 1; i++) {
                System.out.println(filesArray[i].getName());
            }
        }
    }

    private void textFileOutput() throws IOException {
        File[] filesArray = new SetUpDirectory(inputFile).getFilesArray();

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        if (reverseOutput) {
            for (int i = filesArray.length - 1; i >= 0; i--) {
                writer.write(filesArray[i].getName() + "\n");
            }
        } else {
            for (int i = 0; i <= filesArray.length - 1; i++) {
                writer.write(filesArray[i].getName() + "\n");
            }
        }

        writer.close();
    }
}
