package ls.fileparameters;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FilesList {

    public static File[] getFilesList(File inputFile) {
        if (!inputFile.isDirectory()) {
            return new File[] {inputFile};
        }

        File[] filesList = inputFile.listFiles();
        if (filesList == null) {
            throw new IllegalArgumentException("");
        }

        return sortFilesList(filesList);
    }

    private static File[] sortFilesList(File[] filesList) {
        Arrays.sort(filesList, Comparator.comparing(File::isFile).thenComparing(File::getName));
        return filesList;
    }
}
