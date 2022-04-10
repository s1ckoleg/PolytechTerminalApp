package ls;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Objects;

public class HumanOutput {
    private final String inputFile;

    public HumanOutput(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getPermissionsRWX(File file) {
        StringBuilder permissions = new StringBuilder();
        if (file.canRead()) {
            permissions.append("r");
        }
        if (file.canWrite()) {
            permissions.append("w");
        }
        if (file.canExecute()) {
            permissions.append("x");
        }

        if (permissions.isEmpty()) {
            return "-";
        } else {
            return permissions.toString();
        }
     }

    public String getHumanReadableSize(File file) {
        long byteLength = file.length();
        return FileUtils.byteCountToDisplaySize(byteLength);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanOutput that = (HumanOutput) o;
        return Objects.equals(inputFile, that.inputFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputFile);
    }
}
