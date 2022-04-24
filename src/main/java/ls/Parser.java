package ls;

import lombok.Getter;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;

import static ls.Errors.INCORRECT_INPUT_ERROR;

public class Parser {

    @Option(name = "-l", aliases = "long", usage = "Long format output.", forbids = "-h")
    @Getter
    private boolean longOutput;

    @Option(name = "-h", aliases = "human-readable", usage = "Human readable format output.", forbids = "-l")
    @Getter
    private boolean humanOutput;

    @Option(name = "-r", aliases = "reverse", usage = "Reversed format output.")
    @Getter
    private boolean reverseOutput;

    @Option(name = "-o", aliases = "output", usage = "File name to write output.")
    @Getter
    private File outputFile;

    @Argument
    @Getter
    private File inputFile;


    public void parse(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            throw new IllegalArgumentException(INCORRECT_INPUT_ERROR.getMessage());
        }

        if (inputFile == null) {
            File file = new File("");
            inputFile = new File(file.getAbsolutePath());
        }
    }
}
