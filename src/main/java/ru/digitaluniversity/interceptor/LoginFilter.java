package ru.digitaluniversity.interceptor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import ru.digitaluniversity.exception.TokenNotFoundException;
import ru.digitaluniversity.security.service.AuthorizationService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginFilter extends GenericFilterBean {

    private AuthorizationService authorizationService;

    public LoginFilter(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            String authorizationHeader = ((HttpServletRequest) servletRequest).getHeader("Authorization");
            if (authorizationHeader != null) {
                String tokenString = authorizationHeader.substring("Bearer ".length());
                if (StringUtils.hasText(tokenString)) {
                    if (this.authorizationService.checkToken(tokenString)) {
                        Authentication authentication = this.authorizationService.getAuthentication(tokenString);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }



            }
        } catch (TokenNotFoundException e) {
            e.printStackTrace();
        }
        filterChain.doFilter(servletRequest, servletResponse);
        this.resetAuthenticationAfterRequest();
    }

    private void resetAuthenticationAfterRequest() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
