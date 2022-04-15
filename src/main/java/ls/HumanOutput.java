package ls;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HumanOutput {

    private String getPermissionsRWX(File file) {
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

    private String getHumanReadableSize(File file) {
        long byteLength = file.length();
        return FileUtils.byteCountToDisplaySize(byteLength);
    }

    @NotNull
    public List<String> getHumanParametres(File file) throws IOException {
        List<String> parametres = new ArrayList<>();
        parametres.add(new Permissions().getPermissionsRWX(file));
        parametres.add(getHumanReadableSize(file));
        parametres.add(file.getName());
        return parametres;
    }
}
