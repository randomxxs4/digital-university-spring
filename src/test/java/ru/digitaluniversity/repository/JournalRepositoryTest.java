package ru.digitaluniversity.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.digitaluniversity.SpringUniversityApplicationTests;
import ru.digitaluniversity.entity.*;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@Transactional
public class JournalRepositoryTest extends SpringUniversityApplicationTests {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private RatingRepostitory ratingRepostitory;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TimetableRepository timetableRepository;

    @Test
    public void testGet() {
        Integer id = 1;
        Journal journal = journalRepository.findById(id).get();
        assertEquals("Малявский", journal.getJournalStudent().getUser().getSurname());
    }

    @Test
    public void testSave() {
        Integer id = 1;
        Student student = studentRepository.findById(id).get();
        Rating rating = ratingRepostitory.findById(id).get();
        Timetable timetable = timetableRepository.findById(id).get();
        Subject subject = subjectRepository.findById(id).get();
        Journal journal = new Journal();
        journal.setJournalTimetable(timetable);
        journal.setJournalRating(rating);
        journal.setJournalDate(new Date());
        journal.setJournalStudent(student);
        journal.setJournalSubject(subject);

        Journal savedJournal = journalRepository.save(journal);

        Journal journalFromRepos = journalRepository.findById(savedJournal.getId()).get();
        assertEquals(savedJournal, journalFromRepos);
    }

}