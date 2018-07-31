package ru.digitaluniversity.controller;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ru.digitaluniversity.SpringUniversityApplicationTests;
import ru.digitaluniversity.contollers.UserInfoController;
import ru.digitaluniversity.service.UserInfoService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class UserInfoControllerTest extends SpringUniversityApplicationTests {
    private static final String BASE_URL = "/info";

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private UserInfoController userInfoController;

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testGenerateInfoByRole() throws Exception {
        mvc.perform(get(BASE_URL)
                .header("Authorization", "Bearer TOKEN1"))
                .andDo(print())
                .andExpect(status().isOk());


        mvc.perform(get(BASE_URL)
                .header("Authorization", "Bearer TOKEN2"))
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(get(BASE_URL)
                .header("Authorization", "Bearer TOKEN3"))
                .andDo(print())
                .andExpect(status().isUnauthorized());

        mvc.perform(get(BASE_URL)
                .header("Authorization", "Bearer TOKEN_null"))
                .andDo(print())
                .andExpect(status().isNotFound());

        mvc.perform(get(BASE_URL)
                .header("Authorization", "Bearer TOKEN_user"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}
