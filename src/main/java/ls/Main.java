package ls;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser instance = new Parser();
        instance.parse(args);
    }
}
