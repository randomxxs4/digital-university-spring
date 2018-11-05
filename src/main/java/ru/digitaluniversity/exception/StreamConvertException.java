package ru.digitaluniversity.exception;


/**
 * An exception that occurs when a type conversion error occurs using the stream API
 */
public class StreamConvertException extends RuntimeException {
    public StreamConvertException() {
        super();
    }

    public StreamConvertException(String message) {
        super(message);
    }

    public StreamConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public StreamConvertException(Throwable cause) {
        super(cause);
    }
}
