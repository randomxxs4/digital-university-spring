package ru.digitaluniversity.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.digitaluniversity.entity.Day;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DayRepositoryTest {

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testGet(){
        Day day = new Day();
        day.setId(10);
        day.setDay("Десятый");

        Day persist = entityManager.persist(day);

        Day dayById = dayRepository.findById(1).get();
        assertEquals(persist.getDay(), dayById.getDay());

    }
}