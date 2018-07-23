package ru.digitaluniversity.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.digitaluniversity.entity.User;
import ru.digitaluniversity.exception.AuthException;
import ru.digitaluniversity.repository.UserRepository;
import ru.digitaluniversity.security.component.AuthenticationToken;
import ru.digitaluniversity.security.entity.Token;
import ru.digitaluniversity.security.repository.TokenRepository;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthenticationToken authenticationToken = (AuthenticationToken) authentication;
        User user = userRepository.findByUsername(authenticationToken.getUser().getUsername());
        if (user != null) {
            Token token = tokenRepository.findByTokenString(authenticationToken.getToken());
            if (token != null) {
                if (token.getUser().getId() == user.getId()){
                    return new AuthenticationToken(token.getTokenString(), user);
                }
            }
        }
        throw new AuthException("Authentication error!");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
