package ls;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser instance = new Parser();
        instance.parse(args);

        Map<String, Boolean> arguments = instance.getMapOfArguments();
        Map<String, File> files = instance.getMapOfFiles();

        Map<String, List<String>> results =
                new MapOfParameters(arguments.get("reverseOutput"), arguments.get("longOutput"),
                        arguments.get("humanOutput"), files.get("inputFile")).getMap();

        new Output(arguments.get("fileOutput"), files.get("outputFile"), results).buildOutput();
    }
}
