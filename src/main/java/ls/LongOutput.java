package ls;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.*;
import java.nio.file.attribute.BasicFileAttributes;

public class LongOutput {
    String inputFile;

    public LongOutput(String inputFile) {
        this.inputFile = inputFile;
    }

    String getPermissionsBitmask(File file) throws IOException {
        Path path = Paths.get(file.toString());
        PosixFileAttributeView posixView = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        PosixFileAttributes attribs = posixView.readAttributes(); // get file attributes
        Set<PosixFilePermission> permissions = attribs.permissions(); // get permissions from attributes (list like)
        return PosixFilePermissions.toString(permissions);
    }

    String getLastModifiedTime(File file) throws IOException {
        Path path = Paths.get(file.toString());
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        return attr.lastModifiedTime().toString();
    }

    String getFileSizeBytes(File file) {
        return Long.toString(file.length());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongOutput that = (LongOutput) o;
        return Objects.equals(inputFile, that.inputFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputFile);
    }
}
