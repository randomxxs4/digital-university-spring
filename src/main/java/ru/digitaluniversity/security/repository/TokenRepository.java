package ru.digitaluniversity.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.security.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findByTokenString(String tokenString);
}
