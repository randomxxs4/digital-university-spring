package ru.digitaluniversity.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.digitaluniversity.SpringUniversityApplicationTests;
import ru.digitaluniversity.entity.User;
import ru.digitaluniversity.security.entity.Token;
import ru.digitaluniversity.security.repository.TokenRepository;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TokenRepositoryTest extends SpringUniversityApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Test
    public void testGet() {
        String tokenString = "TOKEN1";
        Token token = tokenRepository.findByTokenString(tokenString);
        Optional<Token> byId = tokenRepository.findById(333);

        assertEquals(token.getTokenString(), tokenString);
        assertEquals(tokenString, byId.get().getTokenString());
    }

    @Test
    public void testSave() {
        User user = userRepository.findById(1).get();
        Token token = new Token();
        token.setUser(user);
        token.setExperationDate(new Date(new Date().getTime() + 30000L));
        token.setTokenString("saved_token");

        Token savedToken = tokenRepository.save(token);
        Token byTokenString = tokenRepository.findByTokenString(savedToken.getTokenString());
        assertEquals(savedToken.getTokenString(), byTokenString.getTokenString());
    }
}
