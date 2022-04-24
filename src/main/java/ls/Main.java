package ls;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.parse(args);

        MapOfParameters mapOfParameters = new MapOfParameters(parser.isReverseOutput(), parser.isLongOutput(),
                parser.isHumanOutput(), parser.getInputFile());
        Map<String, List<String>> results = mapOfParameters.getMap();

        Output output = new Output(parser.getOutputFile(), results);
        output.buildOutput();
    }
}
