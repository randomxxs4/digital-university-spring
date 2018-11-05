package ru.digitaluniversity.exception;

/**
 * An exception that occurs when you try to access a resource with an unsupported role
 */
public class UnsupportedRoleException extends Exception {
    public UnsupportedRoleException() {
        super();
    }

    public UnsupportedRoleException(String message) {
        super(message);
    }

    public UnsupportedRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedRoleException(Throwable cause) {
        super(cause);
    }
}
