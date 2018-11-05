//package ru.digitaluniversity.repository;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import ru.digitaluniversity.SpringUniversityApplicationTests;
//import ru.digitaluniversity.entity.Day;
//
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//@Transactional
//public class DayRepositoryTest extends SpringUniversityApplicationTests {
//
//    @Autowired
//    private DayRepository dayRepository;
//
//    @Test
//    public void testA() {
//        Day day = new Day();
//        day.setId(1);
//        day.setDay("Восьмойденьнедели");
//        Day savedDay = dayRepository.save(day);
//
//        Day dayFromRepos = dayRepository.findById(savedDay.getId()).get();
//        assertEquals(savedDay, dayFromRepos);
//    }
//
//    @Test
//    public void testB() {
//        Integer id = 1;
//        Optional<Day> dayById = dayRepository.findById(id);
//        assertNotNull(dayById.get());
//    }
//}