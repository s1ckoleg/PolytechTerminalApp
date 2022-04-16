package ls;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ls.Errors.INCORRECT_INPUT_ERROR;
import static ls.Errors.OUTPUT_FILE_ERROR;

public class Parser {

    @Option(name = "-l", aliases = "long", usage = "Long format output.", forbids = "-h")
    private boolean longOutput;

    @Option(name = "-h", aliases = "human-readable", usage = "Human readable format output.", forbids = "-l")
    private boolean humanOutput;

    @Option(name = "-r", aliases = "reverse", usage = "Reversed format output.")
    private boolean reverseOutput;

    @Option(name = "-o", aliases = "output", usage = "File name to write output.")
    private boolean fileOutput;

    @Argument
    private List<File> fileArguments = new ArrayList<>();

    private File inputFile;
    private File outputFile;


    public void parse(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if ((fileOutput && fileArguments.size() != 2)) {
                throw new IllegalArgumentException(OUTPUT_FILE_ERROR.getMessage());
            }
        } catch (CmdLineException e) {
            throw new IllegalArgumentException(INCORRECT_INPUT_ERROR.getMessage());
        }
        setFilesFromArguments();
    }

    public Map<String, Boolean> getMapOfArguments() {
        Map<String, Boolean> arguments = new HashMap<>();
        arguments.put("reverseOutput", reverseOutput);
        arguments.put("longOutput", longOutput);
        arguments.put("humanOutput", humanOutput);
        arguments.put("fileOutput", fileOutput);
        return arguments;
    }

    public Map<String, File> getMapOfFiles() {
        Map<String, File> files = new HashMap<>();
        files.put("inputFile", inputFile);
        files.put("outputFile", outputFile);
        return files;
    }

    private void setFilesFromArguments() {
        if (fileArguments.isEmpty()) {
            File file = new File("");
            inputFile = new File(file.getAbsolutePath());
            outputFile = null;
        } else if (fileOutput) {
            inputFile = fileArguments.get(1);
            outputFile = fileArguments.get(0);
        } else {
            inputFile = fileArguments.get(0);
            outputFile = null;
        }
    }
}
