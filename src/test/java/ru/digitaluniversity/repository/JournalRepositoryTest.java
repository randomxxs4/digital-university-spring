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
        Journal journal = journalRepository.findById(211).get();
        assertEquals("Малявский", journal.getJournalStudent().getUser().getSurname());
    }

    @Test
    public void testSave() {
        Student student = studentRepository.findById(191).get();
        Rating rating = ratingRepostitory.findById(145).get();
        Timetable timetable = timetableRepository.findById(201).get();
        Subject subject = subjectRepository.findById(171).get();
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