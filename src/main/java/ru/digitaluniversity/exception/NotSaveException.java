package ru.digitaluniversity.exception;

/**
 * An exception that occurs when an entity is not stored in a database.
 */
public class NotSaveException extends RuntimeException {
    public NotSaveException() {
        super();
    }

    public NotSaveException(String message) {
        super(message);
    }

    public NotSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSaveException(Throwable cause) {
        super(cause);
    }
}
