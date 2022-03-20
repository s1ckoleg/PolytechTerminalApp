package ls;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class HumanOutput {
    boolean reverseOutput, fileOutput;
    String inputFile, outputFile;

    public HumanOutput(boolean reverseOutput, boolean fileOutput, String inputFile, String outputFile) {
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

    public String getPermissions(File file) {
        StringBuilder permissions = new StringBuilder();
        if (file.canRead()) {
            permissions.append("r");
        }
        if (file.canWrite()) {
            permissions.append("w");
        }
        if (file.canExecute()) {
            permissions.append("x");
        }

        if (permissions.isEmpty()) {
            return "-";
        } else {
            return permissions.toString();
        }
     }

    public String getHumanReadableSize(File file) {
        long byteLength = file.length();
        return FileUtils.byteCountToDisplaySize(byteLength);
    }

    private void consoleOutput() {
        File[] filesArray = new SetUpDirectory(inputFile).getFilesArray();

        if (reverseOutput) {
            for (int i = filesArray.length - 1; i >= 0; i--) {
                System.out.println(getPermissions(filesArray[i]) + " " + getHumanReadableSize(filesArray[i]) + " "
                        + filesArray[i].getName());
            }
        } else {
            for (int i = 0; i <= filesArray.length - 1; i++) {
                System.out.println(getPermissions(filesArray[i]) + " " + getHumanReadableSize(filesArray[i]) + " "
                        + filesArray[i].getName());
            }
        }
    }

    private void textFileOutput() throws IOException {
        File[] filesArray = new SetUpDirectory(inputFile).getFilesArray();

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        if (reverseOutput) {
            for (int i = filesArray.length - 1; i >= 0; i--) {
                writer.write(getPermissions(filesArray[i]) + " " + getHumanReadableSize(filesArray[i]) + " "
                        + filesArray[i].getName() + "\n");
            }
        } else {
            for (int i = 0; i <= filesArray.length - 1; i++) {
                writer.write(getPermissions(filesArray[i]) + " " + getHumanReadableSize(filesArray[i]) + " "
                        + filesArray[i].getName() + "\n");
            }
        }

        writer.close();
    }
}
