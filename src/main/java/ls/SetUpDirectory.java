package ls;

import java.io.File;

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
}
