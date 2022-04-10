package ls;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BuildMap {
    private final boolean reverseOutput, longOutput, humanOutput;
    private final String inputFile;

    public BuildMap(boolean reverseOutput, boolean longOutput, boolean humanOutput, String inputFile) {
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
            return getLongParametres(file);
        } else if (humanOutput) {
            return getHumanParametres(file);
        } else {
            return getSimpleParametres(file);
        }
    }

    @NotNull
    private List<String> getLongParametres(File file) throws IOException {
        LongOutput instance = new LongOutput(inputFile);
        List<String> parametres = new ArrayList<>();
        parametres.add(instance.getPermissionsBitmask(file));
        parametres.add(instance.getLastModifiedTime(file));
        parametres.add(instance.getFileSizeBytes(file));
        parametres.add(file.getName());
        return parametres;
    }

    @NotNull
    private List<String> getHumanParametres(File file) {
        HumanOutput instance = new HumanOutput(inputFile);
        List<String> parametres = new ArrayList<>();
        parametres.add(instance.getPermissionsRWX(file));
        parametres.add(instance.getHumanReadableSize(file));
        parametres.add(file.getName());
        return parametres;
    }

    @NotNull
    private List<String> getSimpleParametres(File file){
        List<String> parametres = new ArrayList<>();
        parametres.add(file.getName());
        return parametres;
    }
}
