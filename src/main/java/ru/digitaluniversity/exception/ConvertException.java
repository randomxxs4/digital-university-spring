package ru.digitaluniversity.exception;

/**
 * An exception that occurs when an error converts data from an entity class to a DTO class
 */
public class ConvertException extends Exception {
    public ConvertException() {
    }

    public ConvertException(String message) {
        super(message);
    }

    public ConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConvertException(Throwable cause) {
        super(cause);
    }
}
