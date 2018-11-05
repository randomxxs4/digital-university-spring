package ru.digitaluniversity.exception;

/**
 * The exception that occurs when a token is not found in the database
 */
public class TokenNotFoundException extends Exception {
    public TokenNotFoundException() {
        super();
    }

    public TokenNotFoundException(String message) {
        super(message);
    }

    public TokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenNotFoundException(Throwable cause) {
        super(cause);
    }
}
