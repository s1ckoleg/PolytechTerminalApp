package ls;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static ls.Errors.FILE_WRITING_ERROR;

public class Output {
    private final File outputFile;
    private final Map<String, List<String>> results;

    public Output(File outputFile, Map<String, List<String>> results) {
        this.outputFile = outputFile;
        this.results = results;
    }


    public void buildOutput() throws IOException {
        if (outputFile != null) {
            textFileOutput();
        } else {
            consoleOutput();
        }
    }

    private StringBuilder getString(Map.Entry<String, List<String>> entry) {
        StringBuilder string = new StringBuilder();

        for (int j = 0; j < entry.getValue().size(); j++) {
            string.append(entry.getValue().get(j)).append(" ");
        }

        return string;
    }

    public void consoleOutput() {

        for (Map.Entry<String, List<String>> entry : results.entrySet()) {
            System.out.println(getString(entry));
        }
    }

    public void textFileOutput() throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            for (Map.Entry<String, List<String>> entry : results.entrySet()) {
                writer.write(getString(entry).append(System.lineSeparator()).toString());
            }

        } catch (IOException e) {
            throw new IOException(FILE_WRITING_ERROR.getMessage());
        }
    }


}
