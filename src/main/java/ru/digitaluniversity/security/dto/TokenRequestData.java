package ru.digitaluniversity.security.dto;

/**
 * The type Token request data from form
 */
public class TokenRequestData{
    private String username;
    private String password;

    public TokenRequestData() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
