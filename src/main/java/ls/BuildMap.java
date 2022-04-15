package ls;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuildMap {
    private final boolean reverseOutput, longOutput, humanOutput;
    private final File inputFile;

    public BuildMap(boolean reverseOutput, boolean longOutput, boolean humanOutput, File inputFile) {
        this.reverseOutput = reverseOutput;
        this.longOutput = longOutput;
        this.humanOutput = humanOutput;
        this.inputFile = inputFile;
    }

    public HashMap<String, List<String>> buildMap() throws IOException {
        File[] filesArray = new GetFilesList(inputFile).getFilesList();
        HashMap<String, List<String>> results = new HashMap<>();

        if (reverseOutput) {
            for (int i = filesArray.length - 1; i >= 0; i--) {
                results.put(filesArray[i].getName(), getParametres(filesArray[i]));
            }
        } else {
            for (int i = 0; i <= filesArray.length - 1; i++) {
                results.put(filesArray[i].getName(), getParametres(filesArray[i]));
            }
        }

        return results;
    }

    private List<String> getParametres(File file) throws IOException {
        if (longOutput) {
            return new LongOutput().getLongParametres(file);
        } else if (humanOutput) {
            return new HumanOutput().getHumanParametres(file);
        } else {
            List<String> parametres = new ArrayList<>();
            parametres.add(file.getName());
            return parametres;
        }
    }
}
