package ru.digitaluniversity.security.dto;

/**
 * The type Message. Use in Exception Handling
 */
public class Message {
    private String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
