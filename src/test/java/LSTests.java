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
            "/Users/olegkalasnikov/IdeaProjects/PolytechTerminalApp/src/test/java/resources/TestDir";

    public String getOutput(String argument) throws IOException {
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Main.main(argument.split(" "));
        return String.valueOf(outputStreamCaptor.toString()).trim();
    }

    public Path[] createTempPaths() throws IOException {
        Path path1 = Files.createTempFile("TestFile1", ".txt");
        Path path2 = Files.createTempFile("TestFile2", ".txt");
        return new Path[] {path1, path2};
    }

    public boolean compareContent(Path path1, Path path2) throws IOException {
        InputStream inputStream1 = new FileInputStream(path1.toFile());
        InputStream inputStream2 = new FileInputStream(path2.toFile());

        return IOUtils.contentEquals(inputStream1, inputStream2);
    }

    @Test
    public void simpleTest() throws IOException {
        String argument = PATH_TO_TESTDIR;
        String stringToCompare = getOutput(argument);
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void simpleReversedTest() throws IOException {
        String argument = "-r " + PATH_TO_TESTDIR;
        String stringToCompare = getOutput(argument);
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void simpleOneFileTest() throws IOException {
        String argument = "-r " + PATH_TO_TESTDIR + "/textfile2.txt";
        String stringToCompare = getOutput(argument);
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void simpleToFileTest() throws IOException {
        Path[] paths = createTempPaths();
        Path path1 = paths[0];
        Path path2 = paths[1];
        Main.main(
                ("-o " + path1.toString() +
                        " " + PATH_TO_TESTDIR).split(" "));
        Main.main(
                ("-o " + path2.toString() +
                        " " + PATH_TO_TESTDIR).split(" "));
        Assertions.assertTrue(compareContent(path1, path2));
    }

    @Test
    public void defaultDirectory() throws IOException {
        String argument = " ";
        String stringToCompare = getOutput(argument);
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void longTest() throws IOException {
        String argument = "-l " + PATH_TO_TESTDIR;
        String stringToCompare = getOutput(argument);
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void longReversedTest() throws IOException {
        String argument = "-l -r " + PATH_TO_TESTDIR;
        String stringToCompare = getOutput(argument);
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void longOneFileTest() throws IOException {
        String argument = "-l " + PATH_TO_TESTDIR + "/textfile3.txt";
        String stringToCompare = getOutput(argument);
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void longToFileTest() throws IOException {
        Path[] paths = createTempPaths();
        Path path1 = paths[0];
        Path path2 = paths[1];
        Main.main(
                ("-l -o " + path1.toString() +
                        " " + PATH_TO_TESTDIR).split(" "));
        Main.main(
                ("-l -o " + path2.toString() +
                        " " + PATH_TO_TESTDIR).split(" "));
        Assertions.assertTrue(compareContent(path1, path2));
    }

    @Test
    public void humanTest() throws IOException {
        String argument = "-h " + PATH_TO_TESTDIR;
        String stringToCompare = getOutput(argument);
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void humanReversedTest() throws IOException {
        String argument = "-h -r " + PATH_TO_TESTDIR;
        String stringToCompare = getOutput(argument);
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void humanOneFileTest() throws IOException {
        String argument = "-h " + PATH_TO_TESTDIR + "/textfile2.txt";
        String stringToCompare = getOutput(argument);
        assertEquals(stringToCompare,
                getOutput(argument));
    }

    @Test
    public void humanToFileTest() throws IOException {
        Path[] paths = createTempPaths();
        Path path1 = paths[0];
        Path path2 = paths[1];
        Main.main(
                ("-h -o " + path1.toString() +
                        " " + PATH_TO_TESTDIR).split(" "));
        Main.main(
                ("-h -o " + path2.toString() +
                        " " + PATH_TO_TESTDIR).split(" "));
        Assertions.assertTrue(compareContent(path1, path2));
    }

    @Test
    public void errors() {
        assertThrows(IllegalArgumentException.class, () -> Main.main(("-l -h " + PATH_TO_TESTDIR).split(" ")));
        assertThrows(IllegalArgumentException.class, () -> Main.main(("-l -o " + PATH_TO_TESTDIR).split(" ")));
        assertThrows(NoSuchFileException.class, () -> Main.main("-l not/existing/directory".split(" ")));
    }
}
