package ru.digitaluniversity.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.digitaluniversity.SpringUniversityApplicationTests;
import ru.digitaluniversity.entity.*;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        Integer id = 2;
        Optional<Journal> journal = journalRepository.findById(id);
        assertNotNull(journal.get());
    }

    @Test
    public void testSave() {
        Integer id = 1;

        Journal journal = new Journal();
        Journal savedJournal = journalRepository.save(journal);

        Journal journalFromRepos = journalRepository.findById(savedJournal.getId()).get();
        assertEquals(savedJournal, journalFromRepos);
    }

}