package ru.digitaluniversity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.digitaluniversity.SpringUniversityApplicationTests;
import ru.digitaluniversity.security.controller.TokenController;
import ru.digitaluniversity.security.dto.TokenDto;
import ru.digitaluniversity.security.dto.TokenRequestData;
import ru.digitaluniversity.security.entity.Token;
import ru.digitaluniversity.security.repository.TokenRepository;
import ru.digitaluniversity.security.service.AuthorizationService;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
public class TokenControllerTest extends SpringUniversityApplicationTests {

    private static final String BASE_URL = "/token";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AuthorizationService authorizationService;

    @InjectMocks
    private TokenController tokenController;

    @Autowired
    private TokenRepository tokenRepository;

    @Test
    public void testGenerateTokenToTeacher() throws Exception {

        TokenRequestData tokenRequestData = new TokenRequestData();
        tokenRequestData.setUsername("teacher");
        tokenRequestData.setPassword("123");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(tokenRequestData);

        String contentAsString = mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(tokenRequestData.getUsername())))
                .andReturn().getResponse().getContentAsString();

        TokenDto tokenDto = objectMapper.readValue(contentAsString, TokenDto.class);
        Token byTokenString = tokenRepository.findByTokenString(tokenDto.getTokenString());
        assertNotNull(byTokenString);
    }

    @Test
    public void testGenerateTokenToStudent() throws Exception {

        TokenRequestData tokenRequestData = new TokenRequestData();
        tokenRequestData.setUsername("student");
        tokenRequestData.setPassword("456");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(tokenRequestData);

        String contentAsString = mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(tokenRequestData.getUsername())))
                .andReturn().getResponse().getContentAsString();

        TokenDto tokenDto = objectMapper.readValue(contentAsString, TokenDto.class);
        Token byTokenString = tokenRepository.findByTokenString(tokenDto.getTokenString());
        assertNotNull(byTokenString);
    }

    @Test
    public void testGenerateTokenToUser() throws Exception {

        TokenRequestData tokenRequestData = new TokenRequestData();
        tokenRequestData.setUsername("user");
        tokenRequestData.setPassword("111");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(tokenRequestData);

        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testGenerateTokenToIncorrectUser() throws Exception {

        TokenRequestData tokenRequestData = new TokenRequestData();
        tokenRequestData.setUsername("1323211");
        tokenRequestData.setPassword("22323");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(tokenRequestData);

        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

}
