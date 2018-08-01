package ru.digitaluniversity.controller;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ru.digitaluniversity.SpringUniversityApplicationTests;
import ru.digitaluniversity.constant.TokenConst;
import ru.digitaluniversity.contollers.UserInfoController;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.repository.StudentRepository;
import ru.digitaluniversity.repository.TeacherRepository;
import ru.digitaluniversity.service.UserInfoService;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testGenerateInfoByRole() throws Exception {
        Integer teacherId = 1;
        Integer studentId = 1;

        Teacher teacher = teacherRepository.findById(teacherId).get();
        Student student = studentRepository.findById(studentId).get();

        mvc.perform(get(BASE_URL)
                .header("Authorization", TokenConst.TEACHER_TOKEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.surname", is(teacher.getUser().getSurname())))
                .andExpect(jsonPath("$.name", is(teacher.getUser().getName())))
                .andExpect(jsonPath("$.middlename", is(teacher.getUser().getMiddlename())))
                .andExpect(jsonPath("$.position", is(teacher.getTeacherPosition().getTitle())));

        mvc.perform(get(BASE_URL)
                .header("Authorization", TokenConst.STUDENT_TOKEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.surname", is(student.getUser().getSurname())))
                .andExpect(jsonPath("$.name", is(student.getUser().getName())))
                .andExpect(jsonPath("$.middlename", is(student.getUser().getMiddlename())))
                .andExpect(jsonPath("$.studentSpeciality", is(student.getStudentSpeciality().getTitle())))
                .andExpect(jsonPath("$.studentGroup.title", is(student.getStudentGroup().getTitle())))
                .andExpect(jsonPath("$.studentGroup.countStundent", is(((int) studentRepository.countByStudentGroup(student.getStudentGroup())))));

        mvc.perform(get(BASE_URL)
                .header("Authorization", TokenConst.INCORRECT_TOKEN))
                .andDo(print())
                .andExpect(status().isUnauthorized());

        mvc.perform(get(BASE_URL)
                .header("Authorization", TokenConst.NULL_FIELD_TOKEN))
                .andDo(print())
                .andExpect(status().isNotFound());

        mvc.perform(get(BASE_URL)
                .header("Authorization", TokenConst.RANDOM_USER_TOKEN))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}
