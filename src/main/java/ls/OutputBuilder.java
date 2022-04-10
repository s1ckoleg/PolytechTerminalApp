package ls;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputBuilder {
    private final boolean fileOutput;
    private final String outputFile;
    private final HashMap<String, List<String>> results;

    public OutputBuilder(boolean fileOutput, String outputFile, HashMap<String, List<String>> results) {
        this.fileOutput = fileOutput;
        this.outputFile = outputFile;
        this.results = results;
    }


    public void buildOutput() throws IOException {
        if (fileOutput) {
            textFileOutput();
        } else {
            consoleOutput();
        }
    }

    private StringBuilder buildString(Map.Entry<String, List<String>> entry) {
        StringBuilder string = new StringBuilder();

        for (int j = 0; j < entry.getValue().size(); j++) {
            string.append(entry.getValue().get(j)).append(" ");
        }

        return string;
    }

    public void consoleOutput() {

        for (Map.Entry<String, List<String>> entry : results.entrySet()) {

            System.out.println(buildString(entry));
        }
    }

    public void textFileOutput() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        for (Map.Entry<String, List<String>> entry : results.entrySet()) {
            writer.write(buildString(entry).append(System.lineSeparator()).toString());
        }

        writer.close();
    }


}
