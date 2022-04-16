package ls.fileparameters;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class FilesList {
    private final File inputFile;

    public FilesList(File inputFile) {
        if (inputFile != null) {
            this.inputFile = inputFile;
        } else {
            throw new IllegalArgumentException("");
        }
    }

    public File[] getFilesList() {
        if (!inputFile.isDirectory()) {
            return new File[] {inputFile};
        }

        File[] filesList = inputFile.listFiles();
        if (filesList == null) {
            throw new IllegalArgumentException("");
        }

        return sortFilesList(filesList);
    }

    private File[] sortFilesList(File[] filesList) {
        Arrays.sort(filesList, (a, b) -> Boolean.compare(b.isDirectory(), a.isDirectory())); // sort directories first
        Arrays.sort(filesList); // sort by file name
        return filesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilesList that = (FilesList) o;
        return inputFile.equals(that.inputFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputFile);
    }
}
