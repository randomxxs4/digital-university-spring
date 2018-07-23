package ru.digitaluniversity.security.component;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import ru.digitaluniversity.entity.User;

/**
 * The type Authentication token. Used in the authentication process
 */
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
        super(user.getRoles());

        this.token = token;
        this.user = user;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return getUser().getPassword();
    }

    @Override
    public Object getPrincipal() {
        return getUser().getUsername();
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
