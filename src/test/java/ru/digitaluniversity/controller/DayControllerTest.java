package ru.digitaluniversity.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.digitaluniversity.dto.DayDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.service.DayService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class DayControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DayService service;

    public void findById() throws ConvertException, NotFoundException {
        Integer id = 1;
        DayDto byId = service.findById(id);
    }
}
