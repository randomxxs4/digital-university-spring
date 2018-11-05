package ru.digitaluniversity.security.dto;


import ru.digitaluniversity.security.entity.Token;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Token dto.
 */
public class TokenDto {
    private String token;
    private Long expirationDate;
    private UserDto user;
    private List<String> roles;
    private boolean authenticated;

    public TokenDto() {
    }

    public TokenDto(Token token, boolean authenticated) {
        this.token = token.getTokenString();
        this.expirationDate = token.getExperationDate().getTime();
        this.user = Optional.ofNullable(token.getUser()).map(UserDto::new).orElse(null);
        this.authenticated = authenticated;
        this.roles = token.getUser().getRoles().stream()
                .map(item -> item.getRole()).collect(Collectors.toList());
    }

    public TokenDto(Token token) {
        this.token = token.getTokenString();
        this.expirationDate = token.getExperationDate().getTime();
        this.user = Optional.ofNullable(token.getUser()).map(UserDto::new).orElse(null);
        this.roles = token.getUser().getRoles().stream()
                .map(item -> item.getRole()).collect(Collectors.toList());
    }

    public String getToken() {
        return token;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
