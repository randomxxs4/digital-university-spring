package ru.digitaluniversity.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.digitaluniversity.SpringUniversityApplicationTests;
import ru.digitaluniversity.security.entity.Token;
import ru.digitaluniversity.security.repository.TokenRepository;

public class TokenRepositoryTest extends SpringUniversityApplicationTests{

    @Autowired
    private TokenRepository tokenRepository;

    @Test
    public void testGet() {
        Token token11532961371638 = tokenRepository.findByTokenString("TOKEN11532961371638");

    }
}
