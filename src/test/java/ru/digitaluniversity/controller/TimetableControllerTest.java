package ru.digitaluniversity.controller;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ru.digitaluniversity.SpringUniversityApplicationTests;
import ru.digitaluniversity.contollers.JournalController;
import ru.digitaluniversity.contollers.TimetableController;
import ru.digitaluniversity.repository.JournalRepository;
import ru.digitaluniversity.repository.TimetableRepository;
import ru.digitaluniversity.service.JournalService;
import ru.digitaluniversity.service.TimetableService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class TimetableControllerTest extends SpringUniversityApplicationTests {
    private static final String BASE_URL = "/timetable";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TimetableService timetableService;

    @InjectMocks
    private TimetableController timetableController;

    @Autowired
    private TimetableRepository timetableRepository;

    @Test
    public void testFindByTeacherRole() throws Exception {
        mvc.perform(get(BASE_URL)
                .header("Authorization", "Bearer TOKEN1"))
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(get(BASE_URL)
                .header("Authorization", "Bearer TOKEN121"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testFindByStudentRole() throws Exception {
        mvc.perform(get(BASE_URL)
                .header("Authorization", "Bearer TOKEN2"))
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(get(BASE_URL)
                .header("Authorization", "Bearer TOKEN3"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testFindById() throws Exception {
        Integer correctId = 1;
        Integer incorrectId = 100;

        mvc.perform(get(BASE_URL + "/" + correctId)
                .header("Authorization", "Bearer TOKEN1"))
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(get(BASE_URL + "/" + incorrectId)
                .header("Authorization", "Bearer TOKEN2"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
