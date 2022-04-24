package ls.fileparameters;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

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
        Arrays.sort(filesList, (a, b) -> Boolean.compare(b.isDirectory(), a.isDirectory())); // sort directories first
        Arrays.sort(filesList); // sort by file name
        return filesList;
    }
}
