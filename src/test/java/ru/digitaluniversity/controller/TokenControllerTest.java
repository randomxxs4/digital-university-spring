package ru.digitaluniversity.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.digitaluniversity.SpringUniversityApplication;
import ru.digitaluniversity.configuration.DispatcherServletConfig;
import ru.digitaluniversity.configuration.WebMvcConfig;
import ru.digitaluniversity.security.configuration.SecurityConfig;
import ru.digitaluniversity.security.controller.TokenController;
import ru.digitaluniversity.security.dto.TokenRequestData;
import ru.digitaluniversity.security.service.AuthorizationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.junit.Assert.*;

//@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest(TokenController.class)
//@ContextConfiguration(classes = SpringUniversityApplication.class)
//@ContextConfiguration(classes = {DispatcherServletConfig.class, WebMvcConfig.class, SecurityConfig.class})
public class TokenControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorizationService authorizationService;

    @InjectMocks
    private TokenController tokenController;

    @Test
    public void testGenerateToken() throws Exception {

        TokenRequestData tokenRequestData = new TokenRequestData();
        tokenRequestData.setUsername("teacher");
        tokenRequestData.setPassword("123");

        MockHttpServletResponse requestData = mvc.perform(post("http://localhost:8081/api/token")
                .accept(MediaType.APPLICATION_JSON)
                .requestAttr("requestData", tokenRequestData))
                .andReturn().getResponse();

        String contentAsString = requestData.getContentAsString();
    }
}
