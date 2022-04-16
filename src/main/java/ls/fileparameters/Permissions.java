package ls.fileparameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class Permissions {

    static private Set<PosixFilePermission> permissionsSet(File file) throws IOException {
        Path path = Paths.get(file.toString());
        PosixFileAttributeView posixView = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        PosixFileAttributes attribs = posixView.readAttributes();
        return attribs.permissions();
    }

    public String getPermissionsBitmask(File file) throws IOException {
        Set<PosixFilePermission> permissionsSet = permissionsSet(file);
        return PosixFilePermissions.toString(permissionsSet);
    }

    public String getPermissionsRWX(File file) throws IOException {
        Set<PosixFilePermission> permissionsSet = permissionsSet(file);
        StringBuilder string = new StringBuilder();

        if (permissionsSet.contains(PosixFilePermission.OWNER_READ)) {
            string.append("r");
        } else {
            string.append("-");
        }

        if (permissionsSet.contains(PosixFilePermission.OWNER_WRITE)) {
            string.append("w");
        } else {
            string.append("-");
        }

        if (permissionsSet.contains(PosixFilePermission.OWNER_EXECUTE)) {
            string.append("x");
        } else {
            string.append("-");
        }

        return string.toString();
    }
}
