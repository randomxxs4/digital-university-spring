package ru.digitaluniversity.exception;


/**
 * The exception that occurs when authorization is not passed.
 */
public class NotLogInException extends RuntimeException {
    public NotLogInException() {
        super();
    }

    public NotLogInException(String message) {
        super(message);
    }

    public NotLogInException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotLogInException(Throwable cause) {
        super(cause);
    }
}
