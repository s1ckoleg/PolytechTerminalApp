package ls;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Objects;
import java.util.Set;
import java.nio.file.attribute.BasicFileAttributes;

public class LongOutput {
    boolean reverseOutput, fileOutput;
    String inputFile, outputFile;

    public LongOutput(boolean reverseOutput, boolean fileOutput, String inputFile, String outputFile) {
        this.reverseOutput = reverseOutput;
        this.fileOutput = fileOutput;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public void output() throws IOException {
        if (fileOutput) {
            textFileOutput();
        } else {
            consoleOutput();
        }
    }

    private String getPermissionsBitmask(File file) throws IOException {
        Path pathRaw = Paths.get(file.toString());
//        Path path = Files.isSymbolicLink(pathRaw) ? Paths.get(inputFile + Files.readSymbolicLink(pathRaw).toString())
//                : Paths.get(file.toString());

        PosixFileAttributeView posixView = Files.getFileAttributeView(pathRaw, PosixFileAttributeView.class);
        PosixFileAttributes attribs = posixView.readAttributes(); // get file attributes
        Set<PosixFilePermission> permissions = attribs.permissions(); // get permissions from attributes (list like)
        return PosixFilePermissions.toString(permissions);
    }

    private String getLastModifiedTime(File file) throws IOException {
        Path path = Paths.get(file.toString());
        BasicFileAttributes attr =
                Files.readAttributes(path, BasicFileAttributes.class);
        return attr.lastModifiedTime().toString();
    }

    private String getFileSizeBytes(File file) {
        return Long.toString(file.length());
    }

    private void consoleOutput() throws IOException {
        File[] filesArray = new SetUpDirectory(inputFile).getFilesArray();

        if (reverseOutput) {
            for (int i = filesArray.length - 1; i >= 0; i--) {
                System.out.println(getPermissionsBitmask(filesArray[i]) + " " + getLastModifiedTime(filesArray[i]) + " "
                        + getFileSizeBytes(filesArray[i]) + " " + filesArray[i].getName());
            }
        } else {
            for (int i = 0; i <= filesArray.length - 1; i++) {
                System.out.println(getPermissionsBitmask(filesArray[i]) + " " + getLastModifiedTime(filesArray[i]) + " "
                        + getFileSizeBytes(filesArray[i]) + " " + filesArray[i].getName());
            }
        }
    }

    private void textFileOutput() throws IOException {
        File[] filesArray = new SetUpDirectory(inputFile).getFilesArray();

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        if (reverseOutput) {
            for (int i = filesArray.length - 1; i >= 0; i--) {
                writer.write(getPermissionsBitmask(filesArray[i]) + " " + getLastModifiedTime(filesArray[i]) + " "
                        + getFileSizeBytes(filesArray[i]) + " " + filesArray[i].getName() + "\n");
            }
        } else {
            for (int i = 0; i <= filesArray.length - 1; i++) {
                writer.write(getPermissionsBitmask(filesArray[i]) + " " + getLastModifiedTime(filesArray[i]) + " "
                        + getFileSizeBytes(filesArray[i]) + " " + filesArray[i].getName() + "\n");
            }
        }

        writer.close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongOutput that = (LongOutput) o;
        return reverseOutput == that.reverseOutput && fileOutput == that.fileOutput && inputFile.equals(that.inputFile)
                && Objects.equals(outputFile, that.outputFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reverseOutput, fileOutput, inputFile, outputFile);
    }
}
