package ru.digitaluniversity.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.digitaluniversity.exception.NotLogInException;
import ru.digitaluniversity.exception.TokenNotFoundException;
import ru.digitaluniversity.exception.UnsupportedRoleException;
import ru.digitaluniversity.security.dto.Message;
import ru.digitaluniversity.security.dto.TokenDto;
import ru.digitaluniversity.security.dto.TokenRequestData;
import ru.digitaluniversity.security.service.AuthorizationService;

/**
 * The type Token controller.
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private AuthorizationService authorizationService;

    /**
     * Generate token
     *
     * @param requestData the request data
     * @return the TokenDto
     */
    @PostMapping
    public TokenDto generateToken(@RequestBody TokenRequestData requestData) throws UnsupportedRoleException {
        return authorizationService.generateToken(requestData, null);
    }

    @GetMapping
    public TokenDto getAuthorizedInfo() {
        return authorizationService.getAuthorizedInfo();
    }

    /**
     * Handler for NotLogInException send HTTP 401 code
     *
     * @return the message
     */
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotLogInException.class)
    public Message invalidLogin() {
        return new Message("Invalid login/password");
    }

    /**
     * Handler for TokenNotFoundException send HTTP 401 code
     *
     * @return the message
     */
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenNotFoundException.class)
    public Message invalidToken() {
        return new Message("Token not found");
    }

    /**
     * UnsupportedRole Exception handler.
     *
     * @return the message
     */
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnsupportedRoleException.class)
    public Message invalidRole() {
        return new Message("Unsuported role");
    }
}
