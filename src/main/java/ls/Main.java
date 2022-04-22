package ls;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.parse(args);

        Map<String, Boolean> arguments = parser.getMapOfArguments();
        Map<String, File> files = parser.getMapOfFiles();

        MapOfParameters mapOfParameters = new MapOfParameters(arguments.get("reverseOutput"), arguments.get("longOutput"),
                arguments.get("humanOutput"), files.get("inputFile"));
        Map<String, List<String>> results = mapOfParameters.getMap();

        Output output = new Output(arguments.get("fileOutput"), files.get("outputFile"), results);
        output.buildOutput();
    }
}
