package ls;

import java.io.File;
import java.util.Objects;

public class GetFilesList {
    private final File inputFile;

    public GetFilesList(File inputFile) {
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

        return filesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetFilesList that = (GetFilesList) o;
        return inputFile.equals(that.inputFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputFile);
    }
}
