package ru.digitaluniversity.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.digitaluniversity.dto.ProfileInfoDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.UnsupportedRoleException;
import ru.digitaluniversity.security.dto.Message;
import ru.digitaluniversity.services.interfaces.UserInfoService;


/**
 * The type User info controller.
 */
@RestController
@RequestMapping("/info")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * Get user info.
     *
     * @return the user info
     * @throws NotFoundException the not found exception
     */
    @GetMapping
    public ProfileInfoDto getUserInfo() throws Exception {
        return userInfoService.generateInfoByRole();
    }

    /**
     * NotFound exception handler.
     *
     * @return the message
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Message notFound() {
        return new Message("NotFoundException");
    }

    /**
     * Convert exception handler.
     *
     * @return the message
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ConvertException.class)
    public Message notConvert() {
        return new Message("Convert error");
    }

    /**
     * UnsupportedRole exception handler.
     *
     * @return the message
     */
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnsupportedRoleException.class)
    public Message notSupportRole() {
        return new Message("UnsupportedRoleException");
    }

}
