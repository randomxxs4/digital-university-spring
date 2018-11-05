package ru.digitaluniversity.exception;

/**
 * An exception that occurs when a non-redefined / unsupported method is invoked
 */
public class NotImplementedMethodException extends RuntimeException {
    public NotImplementedMethodException() {
    }

    public NotImplementedMethodException(String message) {
        super(message);
    }

    public NotImplementedMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotImplementedMethodException(Throwable cause) {
        super(cause);
    }
}
