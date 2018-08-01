package ru.digitaluniversity.controller;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.digitaluniversity.SpringUniversityApplicationTests;
import ru.digitaluniversity.constant.TokenConst;
import ru.digitaluniversity.contollers.JournalController;
import ru.digitaluniversity.dto.JournalDto;
import ru.digitaluniversity.dto.RatingRequestData;
import ru.digitaluniversity.entity.Journal;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.entity.Timetable;
import ru.digitaluniversity.repository.JournalRepository;
import ru.digitaluniversity.repository.StudentRepository;
import ru.digitaluniversity.repository.TeacherRepository;
import ru.digitaluniversity.repository.TimetableRepository;
import ru.digitaluniversity.serializer.JsonSerializer;
import ru.digitaluniversity.service.JournalService;
import ru.digitaluniversity.service.JournalServiceImpl;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@AutoConfigureMockMvc
public class JournalControllerTest extends SpringUniversityApplicationTests {

    private static final String BASE_URL = "/journal";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JournalServiceImpl journalService;

    @InjectMocks
    private JournalController journalController;

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private TimetableRepository timetableRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void testFindByStudentRole() throws Exception {
        Integer idTimetable = 1;
        Integer idStudent = 1;
        PageRequest pageRequest = PageRequest.of(0, 5);
        int positionInList = 0;

        Integer teacherId = 1;
        Teacher teacher = teacherRepository.findById(teacherId).get();
        List<JournalDto> listTeacherJournals = journalService.findByTeacher(teacher, pageRequest).getContent();

        mvc.perform(get(BASE_URL)
                .header("Authorization", TokenConst.TEACHER_TOKEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[" + positionInList + "].subject", is(listTeacherJournals.get(positionInList).getSubject())))
                .andExpect(jsonPath("$.content[" + positionInList + "].student", is(listTeacherJournals.get(positionInList).getStudent())))
                .andExpect(jsonPath("$.content[" + positionInList + "].rating", is(listTeacherJournals.get(positionInList).getRating())))
                .andExpect(jsonPath("$.content[" + positionInList + "].timetableTeacher", is(listTeacherJournals.get(positionInList).getTimetableTeacher())));

        Student student = studentRepository.findById(idStudent).get();
        List<Journal> listStudentJournal = journalRepository.findByJournalStudent(student, pageRequest).getContent();

        mvc.perform(get(BASE_URL)
                .header("Authorization", TokenConst.STUDENT_TOKEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[" + positionInList + "].subject", is(listStudentJournal.get(positionInList).getJournalSubject().getTitle())))
                .andExpect(jsonPath("$.content[" + positionInList + "].student", is(listStudentJournal.get(positionInList).getJournalStudent().getUser().getSurname())))
                .andExpect(jsonPath("$.content[" + positionInList + "].rating", is(listStudentJournal.get(positionInList).getJournalRating().getRating())))
                .andExpect(jsonPath("$.content[" + positionInList + "].timetableTeacher", is(listStudentJournal.get(positionInList).getJournalTimetable().getTimetableTeacher().getUser().getSurname())));

        mvc.perform(get(BASE_URL)
                .header("Authorization", TokenConst.INCORRECT_TOKEN))
                .andDo(print())
                .andExpect(status().isUnauthorized());

        mvc.perform(get(BASE_URL)
                .header("Authorization", TokenConst.RANDOM_USER_TOKEN))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdateByTeacher() throws Exception {
        Integer id = 1;

        Journal journalBeforeUpdate = journalRepository.findById(id).get();

        RatingRequestData ratingRequestData = new RatingRequestData();
        ratingRequestData.setId(id.toString());
        ratingRequestData.setRating("4");

        String contentAsString = mvc.perform(post(BASE_URL)
                .header("Authorization", TokenConst.TEACHER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonSerializer.toJSON(ratingRequestData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating", is(ratingRequestData.getRating())))
                .andReturn().getResponse().getContentAsString();

        JournalDto journalDto = (JournalDto) JsonSerializer.fromJSON(contentAsString, JournalDto.class);
        assertNotEquals(journalBeforeUpdate.getJournalRating().getRating(), journalDto.getRating());
    }

    @Test
    public void testUpdateByStudent() throws Exception {
        Integer id = 1;

        RatingRequestData ratingRequestData = new RatingRequestData();
        ratingRequestData.setId(id.toString());
        ratingRequestData.setRating("4");

        mvc.perform(post(BASE_URL)
                .header("Authorization", TokenConst.STUDENT_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonSerializer.toJSON(ratingRequestData)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdateByUser() throws Exception {
        Integer id = 1;

        RatingRequestData ratingRequestData = new RatingRequestData();
        ratingRequestData.setId(id.toString());
        ratingRequestData.setRating("4");

        mvc.perform(post(BASE_URL)
                .header("Authorization", TokenConst.RANDOM_USER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonSerializer.toJSON(ratingRequestData)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testFindJournalByRoleAndTimetable() throws Exception {
        Integer correctId = 1;
        Integer incorrectId = 100;
        PageRequest pageRequest = PageRequest.of(0, 5);
        int positionInList = 0;


        String correctRequestPath = BASE_URL + "/find?size=" + pageRequest.getPageSize() + "&page=" + pageRequest.getPageNumber() + "&timetableId=" + correctId;
        String incorrectRequestPath = BASE_URL + "/find?size=" + pageRequest.getPageSize() + "&page=" + pageRequest.getPageNumber() + "&timetableId=" + incorrectId;

        Timetable timetable = timetableRepository.findById(correctId).get();
        List<Journal> listJournalsByTimetable = journalRepository.findByJournalTimetable(timetable, pageRequest).getContent();
        mvc.perform(get(correctRequestPath)
                .header("Authorization", TokenConst.TEACHER_TOKEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[" + positionInList + "].subject", is(listJournalsByTimetable.get(positionInList).getJournalSubject().getTitle())))
                .andExpect(jsonPath("$.content[" + positionInList + "].student", is(listJournalsByTimetable.get(positionInList).getJournalStudent().getUser().getSurname())))
                .andExpect(jsonPath("$.content[" + positionInList + "].rating", is(listJournalsByTimetable.get(positionInList).getJournalRating().getRating())))
                .andExpect(jsonPath("$.content[" + positionInList + "].timetableTeacher", is(listJournalsByTimetable.get(positionInList).getJournalTimetable().getTimetableTeacher().getUser().getSurname())));

        mvc.perform(get(correctRequestPath)
                .header("Authorization", TokenConst.STUDENT_TOKEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[" + positionInList + "].subject", is(listJournalsByTimetable.get(positionInList).getJournalSubject().getTitle())))
                .andExpect(jsonPath("$.content[" + positionInList + "].student", is(listJournalsByTimetable.get(positionInList).getJournalStudent().getUser().getSurname())))
                .andExpect(jsonPath("$.content[" + positionInList + "].rating", is(listJournalsByTimetable.get(positionInList).getJournalRating().getRating())))
                .andExpect(jsonPath("$.content[" + positionInList + "].timetableTeacher", is(listJournalsByTimetable.get(positionInList).getJournalTimetable().getTimetableTeacher().getUser().getSurname())));

        mvc.perform(get(incorrectRequestPath)
                .header("Authorization", TokenConst.STUDENT_TOKEN))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
