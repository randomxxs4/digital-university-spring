package ru.digitaluniversity.exception;

/**
 * An exception that occurs when you try to perform an action without the necessary rights
 */
public class ForbiddenException extends Exception {
    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }
}
