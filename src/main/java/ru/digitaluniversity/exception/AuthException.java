package ru.digitaluniversity.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * The exception that occurs when authentication fails
 */
public class AuthException extends AuthenticationException {

    public AuthException(String msg) {
        super(msg);
    }
}
