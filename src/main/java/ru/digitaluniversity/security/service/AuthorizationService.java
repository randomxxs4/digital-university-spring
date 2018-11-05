package ru.digitaluniversity.security.service;

import org.springframework.security.core.Authentication;
import ru.digitaluniversity.exception.TokenNotFoundException;
import ru.digitaluniversity.exception.UnsupportedRoleException;
import ru.digitaluniversity.security.dto.TokenDto;
import ru.digitaluniversity.security.dto.TokenRequestData;

import javax.servlet.http.HttpSession;

/**
 * The interface Authorization services.
 */
public interface AuthorizationService {

    /**
     * The constant TEACHER_ROLE.
     */
    String TEACHER_ROLE = "TEACHER";
    /**
     * The constant STUDENT_ROLE.
     */
    String STUDENT_ROLE = "STUDENT";

    /**
     * The constant ADMIN_ROLE.
     */
    String ADMIN_ROLE = "ADMIN";

    /**
     * Generate token from TokenRequestData
     *
     * @param requestData the request data from form
     * @param session     the session
     * @return the TokenDto
     * @throws UnsupportedRoleException the unsupported role exception
     */
    TokenDto generateToken(TokenRequestData requestData, HttpSession session) throws UnsupportedRoleException;

    /**
     * Cheack token id db.
     *
     * @param tokenString the token string
     * @return the boolean
     * @throws TokenNotFoundException the token not found exception
     */
    boolean checkToken(String tokenString) throws TokenNotFoundException;

    /**
     * Create login and session binding
     *
     * @param tokenString the token string
     * @return the login string
     */
    void createLogin(String tokenString);

    /**
     * Checks for the user's required role
     *
     * @param role the role
     * @return the boolean
     * @throws Exception the exception
     */
    boolean hasRole(String role) throws Exception;

    /**
     * Gets user role.
     *
     * @param userId the user id
     * @return the user role
     * @throws UnsupportedRoleException the unsupported role exception
     */
    String getUserRole(Integer userId) throws UnsupportedRoleException;

    /**
     * Gets authentication.
     *
     * @param tokenString the token string
     * @return the authentication
     * @throws TokenNotFoundException the token not found exception
     */
    Authentication getAuthentication(String tokenString) throws TokenNotFoundException;

    /**
     * Is teacher boolean.
     *
     * @return the boolean
     */
    boolean isTeacher();

    /**
     * Is student boolean.
     *
     * @return the boolean
     */
    boolean isStudent();

    /**
     * Is admin boolean.
     *
     * @return the boolean
     */
    boolean isAdmin();

    TokenDto getAuthorizedInfo();
}
