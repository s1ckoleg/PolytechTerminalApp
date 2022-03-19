package ls;

import org.kohsuke.args4j.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

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

    public static void main(String[] args) throws IOException {
        Main instance = new Main();
        instance.parse(args);
    }

    public void parse(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if ((fileOutput && arguments.size() != 2)) {
                throw new IllegalArgumentException("");
            }
        } catch (CmdLineException e) {
            throw new IllegalArgumentException("");
        }
        new OutputBuilder(longOutput, humanOutput, reverseOutput, fileOutput, arguments).buildOutput();
    }
}
