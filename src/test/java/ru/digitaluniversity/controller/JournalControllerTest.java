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
import ru.digitaluniversity.SpringUniversityApplicationTests;
import ru.digitaluniversity.contollers.JournalController;
import ru.digitaluniversity.dto.JournalDto;
import ru.digitaluniversity.dto.RatingRequestData;
import ru.digitaluniversity.entity.Journal;
import ru.digitaluniversity.repository.JournalRepository;
import ru.digitaluniversity.service.JournalService;

import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class JournalControllerTest extends SpringUniversityApplicationTests {

    private static final String BASE_URL = "/journal";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JournalService journalService;

    @InjectMocks
    private JournalController journalController;

    @Autowired
    private JournalRepository journalRepository;


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
                .header("Authorization", "Bearer TOKEN12231"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdateByTeacher() throws Exception {
        Integer id = 1;

        Journal journalBeforeUpdate = journalRepository.findById(id).get();

        RatingRequestData ratingRequestData = new RatingRequestData();
        ratingRequestData.setId(id.toString());
        ratingRequestData.setRating("4");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(ratingRequestData);

        String contentAsString = mvc.perform(post(BASE_URL)
                .header("Authorization", "Bearer TOKEN1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JournalDto journalDtoAfterUpadate = objectMapper.readValue(contentAsString, JournalDto.class);
        assertNotEquals(journalBeforeUpdate.getJournalRating().getRating(), journalDtoAfterUpadate.getRating());
    }

    @Test
    public void testUpdateByStudent() throws Exception {
        Integer id = 1;

        RatingRequestData ratingRequestData = new RatingRequestData();
        ratingRequestData.setId(id.toString());
        ratingRequestData.setRating("4");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(ratingRequestData);

        mvc.perform(post(BASE_URL)
                .header("Authorization", "Bearer TOKEN2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testFindJournalByRoleAndTimetable() throws Exception {
        Integer correctId = 1;
        Integer incorrectId = 100;

        String correctRequestPath = BASE_URL + "/find?size=5&page=0&timetableId=" + correctId;
        String incorrectRequestPath = BASE_URL + "/find?size=5&page=0&timetableId=" + incorrectId;

        mvc.perform(get(correctRequestPath)
                .header("Authorization", "Bearer TOKEN1"))
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(get(correctRequestPath)
                .header("Authorization", "Bearer TOKEN2"))
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(get(incorrectRequestPath)
                .header("Authorization", "Bearer TOKEN2"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
