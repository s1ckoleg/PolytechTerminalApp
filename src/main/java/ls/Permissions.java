package ls;

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

    public Set<PosixFilePermission> getPermissionsSet(File file) throws IOException {
        Path path = Paths.get(file.toString());
        PosixFileAttributeView posixView = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        PosixFileAttributes attribs = posixView.readAttributes(); // get file attributes
        return attribs.permissions();
    }

    public String getPermissionsBitmask(File file) throws IOException {
        Set<PosixFilePermission> permissionsSet = getPermissionsSet(file);
        return PosixFilePermissions.toString(permissionsSet);
    }

    public String getPermissionsRWX(File file) throws IOException {
        Set<PosixFilePermission> permissionsSet = getPermissionsSet(file);
        StringBuilder string = new StringBuilder();

        if (permissionsSet.contains(PosixFilePermission.OWNER_READ)) {
            string.append("r");
        }
        if (permissionsSet.contains(PosixFilePermission.OWNER_WRITE)) {
            string.append("w");
        }
        if (permissionsSet.contains(PosixFilePermission.OWNER_EXECUTE)) {
            string.append("x");
        }

        if (string.isEmpty()) {
            return "-";
        } else {
            return string.toString();
        }
    }
}
