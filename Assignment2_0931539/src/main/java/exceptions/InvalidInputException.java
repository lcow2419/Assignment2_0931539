package exceptions;

/**
 * Custom exception to handle invalid input cases.
 */
public class InvalidInputException extends RuntimeException {

    /**
     * Constructor to initialize the exception with a custom message.
     *
     * @param message The error message describing the invalid input.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
