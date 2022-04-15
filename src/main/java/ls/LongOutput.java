package ls;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.attribute.BasicFileAttributes;

public class LongOutput {

    public String getLastModifiedTime(File file) throws IOException {
        Path path = Paths.get(file.toString());
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        return attr.lastModifiedTime().toString();
    }

    public String getFileSizeBytes(File file) {
        return Long.toString(file.length());
    }

    @NotNull
    public List<String> getLongParametres(File file) throws IOException {
        List<String> parametres = new ArrayList<>();

        parametres.add(new Permissions().getPermissionsBitmask(file));
        parametres.add(getLastModifiedTime(file));
        parametres.add(getFileSizeBytes(file));
        parametres.add(file.getName());

        return parametres;
    }
}
