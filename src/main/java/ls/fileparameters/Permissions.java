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

    private static Set<PosixFilePermission> permissionsSet(File file) throws IOException {
        Path path = Paths.get(file.toString());
        PosixFileAttributeView posixView = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        PosixFileAttributes attribs = posixView.readAttributes();
        return attribs.permissions();
    }


    private static String countPermissionBitmask(PosixFilePermission read, PosixFilePermission write, PosixFilePermission execute, File file) throws IOException {
        // r == 4
        // w == 2
        // x == 1
        int bitmask = 0;
        Set<PosixFilePermission> permissionsSet = permissionsSet(file);

        if (permissionsSet.contains(read)) {
            bitmask += 4;
        }

        if (permissionsSet.contains(write)) {
            bitmask += 2;
        }

        if (permissionsSet.contains(execute)) {
            bitmask += 1;
        }

        return Integer.toString(bitmask);
    }

    public static String getPermissionsBitmask(File file) throws IOException {
        return countPermissionBitmask(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE, file) +
                countPermissionBitmask(PosixFilePermission.GROUP_READ, PosixFilePermission.GROUP_WRITE, PosixFilePermission.GROUP_EXECUTE, file) +
                countPermissionBitmask(PosixFilePermission.OTHERS_READ, PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_EXECUTE, file);
    }

    public static String getPermissionsRWX(File file) throws IOException {
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
