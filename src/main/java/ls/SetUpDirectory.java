package ls;

import java.io.File;
import java.util.Objects;

public class SetUpDirectory {
    String inputFile;

    public SetUpDirectory(String inputFile) {
        if (inputFile != null) {
            this.inputFile = inputFile;
        } else {
            throw new IllegalArgumentException("");
        }
    }

    public File[] getFilesArray() {
        File file = new File(inputFile);

        if (!file.isDirectory()) {
            return new File[] {file};
        }

        File[] filesArray = file.listFiles();
        if (filesArray == null) {
            throw new IllegalArgumentException("");
        }

        return filesArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetUpDirectory that = (SetUpDirectory) o;
        return inputFile.equals(that.inputFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputFile);
    }
}
