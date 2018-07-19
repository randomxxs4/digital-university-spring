package ru.digitaluniversity.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.digitaluniversity.interceptor.LoginFilter;
import ru.digitaluniversity.security.service.AuthProvider;
import ru.digitaluniversity.security.service.AuthorizationService;
import ru.digitaluniversity.security.service.AuthorizationServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProvider authProvider;
    @Autowired
    private AuthorizationService authorizationService;


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);


        http
                .csrf().disable()
                .authenticationProvider(authProvider)
                .authorizeRequests()
//                .antMatchers("/**").permitAll();
                .antMatchers("/api/token/**").permitAll()
                .antMatchers("/token").permitAll()
                .antMatchers("/**").authenticated();
    }

    public LoginFilter loginFilter() {
        return new LoginFilter(authorizationService);
    }
}