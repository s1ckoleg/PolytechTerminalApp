package ls;

public enum Errors {
    INCORRECT_INPUT_ERROR("Incorrect CMD input was given"),
    FILE_WRITING_ERROR("Something went wrong during the writing");

    private final String message;

    Errors(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

}
