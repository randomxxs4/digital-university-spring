package ru.digitaluniversity.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.digitaluniversity.entity.Journal;
import ru.digitaluniversity.entity.Rating;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.entity.Subject;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JournalRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JournalRepository journalRepository;

    @Test
    public void testGet() {
//        Subject subject = new Subject();
//        subject.setId(10);
//        subject.setTitle("Matem");
//
//        Rating rating = new Rating();
//        rating.setId(10);
//        rating.setRating("10");
//
//        Student student = new Student();
//        student.setId(10);
//        student.setStudentGroup();
//
//        Journal journal = new Journal();
//        journal.setId(10);
//        journal.setJournalSubject(subject);
//        journal.setJournalStudent();
//        journal.setJournalDate(new Date());
//        journal.setJournalRating();
//        journal.setJournalTimetable();
//
//        assertEquals("Малявский", journal.getJournalStudent().getUser().getSurname());
    }

}