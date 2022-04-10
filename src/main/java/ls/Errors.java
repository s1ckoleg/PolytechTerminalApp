package ls;

public enum Errors {
    OUTPUT_FILE_ERROR("-o argument was used, but path to file was never given"),
    INCORRECT_INPUT_ERROR("Incorrect CMD input was given");

    private final String message;

    Errors(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

}
