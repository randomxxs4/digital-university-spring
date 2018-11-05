package ru.digitaluniversity.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.security.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findByTokenString(String tokenString);

    @Query(nativeQuery = true, value = "SELECT tt.id, tt.experation_date, tt.token_string, tt.user_id" +
            " FROM tokens tt WHERE experation_date = (SELECT max(t.experation_date)\n" +
            " FROM public.tokens t \n" +
            " JOIN users u ON t.user_id = u.id\n" +
            " WHERE u.id = 1 )\n" +
            " select tt.id, tt.experation_date, tt.token_string, tt.user_id\n" +
            " from tokens tt where experation_date = (SELECT max(t.experation_date)\n" +
            " FROM public.tokens t \n" +
            " join users u on t.user_id = u.id\n" +
            " where u.id = ?1)")
    Token findByUser(Integer userId);
}
