package ru.digitaluniversity.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.digitaluniversity.contollers.DayController;
import ru.digitaluniversity.entity.Day;
import ru.digitaluniversity.repository.DayRepository;
import ru.digitaluniversity.security.entity.Token;
import ru.digitaluniversity.security.repository.TokenRepository;
import ru.digitaluniversity.service.DayService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DayController.class)
public class DayControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DayService dayService;

    @InjectMocks
    private DayController dayController;

    @Test
    public void testFindById() throws Exception {

        MockHttpServletResponse response = mvc.perform(get("/day/101")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer TOKEN11532961371638")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        String contentAsString = response.getContentAsString();

    }
}
