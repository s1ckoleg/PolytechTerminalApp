package ls;

import ls.fileparameters.FilesList;
import ls.fileparameters.HumanParameters;
import ls.fileparameters.LongParameters;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MapOfParameters {
    private final boolean reverseOutput, longOutput, humanOutput;
    private final File inputFile;

    public MapOfParameters(boolean reverseOutput, boolean longOutput, boolean humanOutput, File inputFile) {
        this.reverseOutput = reverseOutput;
        this.longOutput = longOutput;
        this.humanOutput = humanOutput;
        this.inputFile = inputFile;
    }

    public Map<String, List<String>> getMap() throws IOException {
        File[] filesArray = FilesList.getFilesList(inputFile);
        Map<String, List<String>> results = new LinkedHashMap<>();

        if (reverseOutput) {
            for (int i = filesArray.length - 1; i >= 0; i--) {
                results.put(filesArray[i].getName(), getParameters(filesArray[i]));
            }
        } else {
            for (int i = 0; i <= filesArray.length - 1; i++) {
                results.put(filesArray[i].getName(), getParameters(filesArray[i]));
            }
        }

        return results;
    }

    private List<String> getParameters(File file) throws IOException {
        if (longOutput) {
            return LongParameters.getLongParameters(file);
        } else if (humanOutput) {
            return HumanParameters.getHumanParameters(file);
        } else {
            List<String> parametres = new ArrayList<>();
            parametres.add(file.getName());
            return parametres;
        }
    }
}
