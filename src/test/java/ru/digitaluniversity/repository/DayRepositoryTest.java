package ru.digitaluniversity.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.digitaluniversity.SpringUniversityApplicationTests;
import ru.digitaluniversity.entity.Day;

import static org.junit.Assert.assertEquals;

@Transactional
public class DayRepositoryTest extends SpringUniversityApplicationTests {

    @Autowired
    private DayRepository dayRepository;

    @Test
    public void testGet() {
        Day dayById = dayRepository.findById(101).get();
        assertEquals("Понедельник", dayById.getDay());
    }

    @Test

    public void testSave(){
        Day day = new Day();
        day.setId(new Integer(108));
        day.setDay("Восьмойденьнедели");
        Day savedDay = dayRepository.save(day);

        Day dayFromRepos = dayRepository.findById(savedDay.getId()).get();
        assertEquals(savedDay, dayFromRepos);

    }
}