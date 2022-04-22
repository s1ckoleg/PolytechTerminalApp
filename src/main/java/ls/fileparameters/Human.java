package ls.fileparameters;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Human {

    private String getHumanReadableSize(File file) {
        long byteLength = file.length();
        return FileUtils.byteCountToDisplaySize(byteLength);
    }

    @NotNull
    public List<String> getHumanParameters(File file) throws IOException {
        List<String> parametres = new ArrayList<>();
        parametres.add(Permissions.getPermissionsRWX(file));
        parametres.add(getHumanReadableSize(file));
        parametres.add(file.getName());
        return parametres;
    }
}
