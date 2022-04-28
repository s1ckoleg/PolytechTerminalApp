import ls.Main;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LSTests {

    private static final String PATH_TO_TESTDIR =
            "src/test/resources/TestDir";

    public String getOutput(String argument) throws IOException {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Main.main(argument.split(" "));
        return String.valueOf(outputStreamCaptor.toString());
    }

    public Path createTempPaths() throws IOException {
        return Files.createTempFile("TestFile1", ".txt");
    }

    public boolean compareContent(Path path1, Path path2) throws IOException {
        InputStream inputStream1 = new FileInputStream(path1.toFile());
        InputStream inputStream2 = new FileInputStream(path2.toFile());

        return IOUtils.contentEquals(inputStream1, inputStream2);
    }

    @Test
    public void simpleOutputTest() throws IOException {
        String stringToCompare = """
                Dir1\s
                Dir2\s
                Dir3\s
                textfile1.txt\s
                textfile2.txt\s
                textfile3.txt\s
                """;
        assertEquals(stringToCompare, getOutput(PATH_TO_TESTDIR));
    }

    @Test
    public void simpleOutputReversedTest() throws IOException {
        String argument = "-r " + PATH_TO_TESTDIR;
        String stringToCompare = """
                textfile3.txt\s
                textfile2.txt\s
                textfile1.txt\s
                Dir3\s
                Dir2\s
                Dir1\s
                """;
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void simpleOutputOneFileTest() throws IOException {
        String argument = PATH_TO_TESTDIR + "/textfile2.txt";
        String stringToCompare = "textfile2.txt " + System.lineSeparator();
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void simpleOutputToFileTest() throws IOException {
        Path path1 = createTempPaths();
        Main.main(
                ("-o " + path1.toString() +
                        " " + PATH_TO_TESTDIR).split(" "));
        Path path2 = Path.of("src/test/resources/ResultsFiles/SimpleOutput");
        Assertions.assertTrue(compareContent(path1, path2));
    }

    @Test
    public void defaultDirectory() throws IOException {
        String argument = " ";
        String stringToCompare = """
                .git\s
                .idea\s
                out\s
                src\s
                target\s
                .DS_Store\s
                .gitignore\s
                pom.xml\s
                testfile.txt\s
                """;
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void longTest() throws IOException {
        String argument = "-l " + PATH_TO_TESTDIR;
        String stringToCompare = """
                755 2022-04-09T16:55:50.636828929Z 64 Dir1\s
                755 2022-04-09T16:55:52.886645145Z 64 Dir2\s
                755 2022-04-09T16:55:56.647419967Z 64 Dir3\s
                644 2022-04-09T16:56:04.337665024Z 0 textfile1.txt\s
                644 2022-04-09T16:56:11.851892291Z 0 textfile2.txt\s
                644 2022-04-09T16:56:30.697382495Z 43 textfile3.txt\s
                """;
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void longReversedTest() throws IOException {
        String argument = "-l -r " + PATH_TO_TESTDIR;
        String stringToCompare = """
                644 2022-04-09T16:56:30.697382495Z 43 textfile3.txt\s
                644 2022-04-09T16:56:11.851892291Z 0 textfile2.txt\s
                644 2022-04-09T16:56:04.337665024Z 0 textfile1.txt\s
                755 2022-04-09T16:55:56.647419967Z 64 Dir3\s
                755 2022-04-09T16:55:52.886645145Z 64 Dir2\s
                755 2022-04-09T16:55:50.636828929Z 64 Dir1\s
                """;
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void longOneFileTest() throws IOException {
        String argument = "-l " + PATH_TO_TESTDIR + "/textfile3.txt";
        String stringToCompare = "644 2022-04-09T16:56:30.697382495Z 43 textfile3.txt " + System.lineSeparator();
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void longToFileTest() throws IOException {
        Path path1 = createTempPaths();
        Main.main(
                ("-l -o " + path1.toString() +
                        " " + PATH_TO_TESTDIR).split(" "));
        Path path2 = Path.of("src/test/resources/ResultsFiles/LongOutput");
        Assertions.assertTrue(compareContent(path1, path2));
    }

    @Test
    public void humanTest() throws IOException {
        String argument = "-h " + PATH_TO_TESTDIR;
        String stringToCompare = """
                rwx 64 bytes Dir1\s
                rwx 64 bytes Dir2\s
                rwx 64 bytes Dir3\s
                rw- 0 bytes textfile1.txt\s
                rw- 0 bytes textfile2.txt\s
                rw- 43 bytes textfile3.txt\s
                """;
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void humanReversedTest() throws IOException {
        String argument = "-h -r " + PATH_TO_TESTDIR;
        String stringToCompare = """
                rw- 43 bytes textfile3.txt\s
                rw- 0 bytes textfile2.txt\s
                rw- 0 bytes textfile1.txt\s
                rwx 64 bytes Dir3\s
                rwx 64 bytes Dir2\s
                rwx 64 bytes Dir1\s
                """;
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void humanOneFileTest() throws IOException {
        String argument = "-h " + PATH_TO_TESTDIR + "/textfile2.txt";
        String stringToCompare = "rw- 0 bytes textfile2.txt " + System.lineSeparator();
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void humanToFileTest() throws IOException {
        Path path1 = createTempPaths();
        Main.main(
                ("-h -o " + path1.toString() +
                        " " + PATH_TO_TESTDIR).split(" "));
        Path path2 = Path.of("src/test/resources/ResultsFiles/HumanOutput");
        Assertions.assertTrue(compareContent(path1, path2));
    }

    @Test
    public void emptyDir() throws IOException {
        String argument = "src/test/resources/EmptyDir";
        String stringToCompare = "";
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void errors() {
        assertThrows(IllegalArgumentException.class, () -> Main.main(("-l -h " + PATH_TO_TESTDIR).split(" ")));
        assertThrows(NoSuchFileException.class, () -> Main.main("-l not/existing/directory".split(" ")));
    }
}
