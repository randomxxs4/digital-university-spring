package ru.digitaluniversity.controller;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import ru.digitaluniversity.SpringUniversityApplicationTests;
import ru.digitaluniversity.constant.TokenConst;
import ru.digitaluniversity.contollers.TimetableController;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.entity.Timetable;
import ru.digitaluniversity.repository.StudentRepository;
import ru.digitaluniversity.repository.TeacherRepository;
import ru.digitaluniversity.repository.TimetableRepository;
import ru.digitaluniversity.service.TimetableService;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void testFindByRole() throws Exception {
        Integer teacherId = 1;
        Integer studentId = 1;
        PageRequest pageRequest = PageRequest.of(0, 5);
        int positionInList = 0;

        Student student = studentRepository.findById(studentId).get();
        Teacher teacher = teacherRepository.findById(teacherId).get();
        List<Timetable> teacherTimetables = timetableRepository.findByTimetableTeacher(teacher, pageRequest).getContent();
        List<Timetable> studentTimetables = timetableRepository.findByTimetableGroup(student.getStudentGroup(), pageRequest).getContent();

        mvc.perform(get(BASE_URL)
                .header("Authorization", TokenConst.TEACHER_TOKEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[" + positionInList + "].teacher", is(teacherTimetables.get(positionInList).getTimetableTeacher().getUser().getSurname())))
                .andExpect(jsonPath("$.content[" + positionInList + "].group", is(teacherTimetables.get(positionInList).getTimetableGroup().getTitle())))
                .andExpect(jsonPath("$.content[" + positionInList + "].subject", is(teacherTimetables.get(positionInList).getTimetableSubject().getTitle())));

        mvc.perform(get(BASE_URL)
                .header("Authorization", TokenConst.STUDENT_TOKEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[" + positionInList + "].teacher", is(studentTimetables.get(positionInList).getTimetableTeacher().getUser().getSurname())))
                .andExpect(jsonPath("$.content[" + positionInList + "].group", is(studentTimetables.get(positionInList).getTimetableGroup().getTitle())))
                .andExpect(jsonPath("$.content[" + positionInList + "].subject", is(studentTimetables.get(positionInList).getTimetableSubject().getTitle())));

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
    public void testFindById() throws Exception {
        Integer correctId = 1;
        Integer incorrectId = 100;

        Timetable timetable = timetableRepository.findById(correctId).get();

        mvc.perform(get(BASE_URL + "/" + correctId)
                .header("Authorization", TokenConst.TEACHER_TOKEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teacher", is(timetable.getTimetableTeacher().getUser().getSurname())))
                .andExpect(jsonPath("$.group", is(timetable.getTimetableGroup().getTitle())))
                .andExpect(jsonPath("$.subject", is(timetable.getTimetableSubject().getTitle())));

        mvc.perform(get(BASE_URL + "/" + incorrectId)
                .header("Authorization", TokenConst.STUDENT_TOKEN))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}