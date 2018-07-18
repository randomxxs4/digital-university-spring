package ru.digitaluniversity.security.entity;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import ru.digitaluniversity.entity.User;

public class AuthenticationToken extends AbstractAuthenticationToken {

    private String token;
    private User user;

    public AuthenticationToken(String token) {
        super(null);

        this.token = token;
        this.user = null;
        setAuthenticated(false);
    }

    public AuthenticationToken(String token, User user) {
        //note that the constructor needs a collection of GrantedAuthority
        //but our User have a collection of our UserRole's
        super(user.getRoles());

        this.token = token;
        this.user = user;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return getToken();
    }

    @Override
    public Object getPrincipal() {
        return getUser();
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
