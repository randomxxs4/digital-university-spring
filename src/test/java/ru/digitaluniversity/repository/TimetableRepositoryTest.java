package ru.digitaluniversity.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.digitaluniversity.SpringUniversityApplicationTests;
import ru.digitaluniversity.entity.*;

import static org.junit.Assert.assertEquals;

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
        Timetable timetable = timetableRepository.findById(201).get();
        assertEquals("Иванов", timetable.getTimetableTeacher().getUser().getSurname());
    }

    @Test
    public void testSave() {
        Teacher teacher = teacherRepository.findById(181).get();
        Day day = dayRepository.findById(101).get();
        Group group = groupRepository.findById(111).get();
        Pair pair = pairRepository.findById(121).get();
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

