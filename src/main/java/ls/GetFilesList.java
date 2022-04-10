package ls;

import java.io.File;
import java.util.Objects;

public class GetFilesList {
    String inputFile;

    public GetFilesList(String inputFile) {
        if (inputFile != null) {
            this.inputFile = inputFile;
        } else {
            throw new IllegalArgumentException("");
        }
    }

    public File[] getFilesList() {
        File file = new File(inputFile);

        if (!file.isDirectory()) {
            return new File[] {file};
        }

        File[] filesList = file.listFiles();
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
