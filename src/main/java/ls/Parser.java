package ls;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ls.Errors.INCORRECT_INPUT_ERROR;
import static ls.Errors.OUTPUT_FILE_ERROR;

public class Parser {

    @Option(name = "-l", aliases = "long", usage = "Long format output.", forbids = "-h")
    public boolean longOutput;

    @Option(name = "-h", aliases = "human-readable", usage = "Human readable format output.", forbids = "-l")
    public boolean humanOutput;

    @Option(name = "-r", aliases = "reverse", usage = "Reversed format output.")
    private boolean reverseOutput;

    @Option(name = "-o", aliases = "output", usage = "File name to write output.")
    private boolean fileOutput;

    @Argument
    public List<String> arguments = new ArrayList<>();

    private File inputFile;
    private File outputFile;


    public void parse(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if ((fileOutput && arguments.size() != 2)) {
                throw new IllegalArgumentException(OUTPUT_FILE_ERROR.getMessage());
            }
        } catch (CmdLineException e) {
            throw new IllegalArgumentException(INCORRECT_INPUT_ERROR.getMessage());
        }
        buildMapWithResults();
    }

    private void setFilesFromArguments() {
        if (arguments.isEmpty()) {
            File file = new File("");
            inputFile = new File(file.getAbsolutePath());
        } else if (fileOutput) {
            inputFile = new File(arguments.get(1));
            outputFile = new File(arguments.get(0));
        } else {
            inputFile = new File(arguments.get(0));
            outputFile = null;
        }
    }

    private void buildMapWithResults() throws IOException {
        setFilesFromArguments();
        HashMap<String, List<String>> results = new BuildMap(reverseOutput, longOutput, humanOutput, inputFile).buildMap();
        buildOutput(results);
    }

    public void buildOutput(HashMap<String, List<String>> results) throws IOException {
        new OutputBuilder(fileOutput, outputFile, results).buildOutput();
    }

}
