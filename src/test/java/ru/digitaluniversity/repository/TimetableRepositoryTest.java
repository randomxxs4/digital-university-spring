package ru.digitaluniversity.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.digitaluniversity.SpringUniversityApplicationTests;
import ru.digitaluniversity.entity.*;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Transactional
public class TimetableRepositoryTest extends SpringUniversityApplicationTests {

    @Autowired
    private TimetableRepository timetableRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PairRepository pairRepository;

    @Test
    public void testGet() {
        Integer id = 1;
        Optional<Timetable> timetable = timetableRepository.findById(id);
        assertNotNull(timetable.get());
    }

    @Test
    public void testSave() {
        Integer id = 1;
        Teacher teacher = teacherRepository.findById(id).get();
        Day day = dayRepository.findById(id).get();
        Group group = groupRepository.findById(id).get();
        Pair pair = pairRepository.findById(id).get();
        Timetable timetable = new Timetable();
        timetable.setTimetableDay(day);
        timetable.setTimetableGroup(group);
        timetable.setTimetablePair(pair);
        timetable.setTimetableTeacher(teacher);
        Timetable savedTimetable = timetableRepository.save(timetable);
        Timetable timetableFromRepos = timetableRepository.findById(savedTimetable.getId()).get();
        assertEquals(savedTimetable, timetableFromRepos);
    }
}

