package ru.digitaluniversity.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.entity.User;
import ru.digitaluniversity.exception.NotLogInException;
import ru.digitaluniversity.exception.TokenNotFoundException;
import ru.digitaluniversity.exception.UnsupportedRoleException;
import ru.digitaluniversity.repository.UserRepository;
import ru.digitaluniversity.security.dto.TokenDto;
import ru.digitaluniversity.security.dto.TokenRequestData;
import ru.digitaluniversity.security.entity.AuthenticationToken;
import ru.digitaluniversity.security.entity.Token;
import ru.digitaluniversity.security.repository.TokenRepository;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class AuthoriationServiceImpl implements AuthorizationService{

    private static final Long TIME_5_MIN = 300000L;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TokenDto generateToken(TokenRequestData requestData, HttpSession session) {
        User user = userRepository.findByUsername(requestData.getUsername());
        if (user != null){
            if (user.getPassword().equals(requestData.getPassword())){
                long time = new Date().getTime();
                Long expirationDate = time + TIME_5_MIN;
                String tokenString = "TOKEN" + user.getId() + expirationDate;

                AuthenticationToken authenticationToken = new AuthenticationToken(tokenString, user);

                Token token = new Token();
                token.setTokenString(tokenString);
                token.setExperationDate(new Date(expirationDate));
                token.setUser(user);
                tokenRepository.save(token);

                TokenDto tokenDto = new TokenDto();
                tokenDto.setTokenString(tokenString);
                tokenDto.setExpirationDate(expirationDate);
                tokenDto.setUserId(user.getUsername());
                tokenDto.setUserRole(user.getRoles().get(0).getRole()); // TODO: 18.07.2018 исправить на нормальное получение роли

                return tokenDto;
            }
        }
        throw new NotLogInException();
    }

    @Override
    public boolean checkToken(String tokenString) throws TokenNotFoundException {
        Token token = tokenRepository.findByTokenString(tokenString);
        if (token != null) {
            long currentTime = new Date().getTime();
            return currentTime < token.getExperationDate().getTime();
        } else {
            throw new TokenNotFoundException("Token not found!");
        }
    }

    @Override
    public void createLogin(String tokenString) {

    }

    @Override
    public boolean hasRole(String role) throws Exception {
        return false;
    }

    @Override
    public String getUserRole() throws UnsupportedRoleException {
        return null;
    }
}
