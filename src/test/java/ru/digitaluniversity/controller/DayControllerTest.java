//package ru.digitaluniversity.controller;
//
//
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.test.web.servlet.MockMvc;
//import ru.digitaluniversity.SpringUniversityApplicationTests;
//import ru.digitaluniversity.constant.TokenConst;
//import ru.digitaluniversity.contollers.DayController;
//import ru.digitaluniversity.entity.Day;
//import ru.digitaluniversity.repository.DayRepository;
//import ru.digitaluniversity.services.interfaces.DayService;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//public class DayControllerTest extends SpringUniversityApplicationTests {
//
//    private static final String BASE_URL = "/day";
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private DayService dayService;
//
//    @InjectMocks
//    private DayController dayController;
//
//    @Autowired
//    private DayRepository dayRepository;
//
//    @Test
//    public void testFindById() throws Exception {
//        Integer id = 1;
//
//        Day day = dayRepository.findById(id).get();
//
//        mvc.perform(get(BASE_URL + "/" + id)
//                .header("Authorization", TokenConst.TEACHER_TOKEN))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString(day.getDay())))
//                .andExpect(jsonPath("$.title", is(day.getDay())));
//    }
//}
