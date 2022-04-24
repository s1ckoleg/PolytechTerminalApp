package ls.fileparameters;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.attribute.BasicFileAttributes;

public class LongParameters {

    private static String getLastModifiedTime(File file) throws IOException {
        Path path = Paths.get(file.toString());
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        return attr.lastModifiedTime().toString();
    }

    private static String getFileSizeBytes(File file) {
        return java.lang.Long.toString(file.length());
    }

    @NotNull
    public static List<String> getLongParameters(File file) throws IOException {
        List<String> parametres = new ArrayList<>();

        parametres.add(Permissions.getPermissionsBitmask(file));
        parametres.add(getLastModifiedTime(file));
        parametres.add(getFileSizeBytes(file));
        parametres.add(file.getName());

        return parametres;
    }
}
