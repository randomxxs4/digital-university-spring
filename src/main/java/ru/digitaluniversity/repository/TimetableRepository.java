package ru.digitaluniversity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Group;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.entity.Timetable;

import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Integer> {
    Page<Timetable> findByTimetableGroup(Group group, Pageable pageable);

    List<Timetable> findByTimetableGroup(Group group);

    Page<Timetable> findByTimetableTeacher(Teacher teacher, Pageable pageable);

    List<Timetable> findByTimetableTeacher(Teacher teacher);
}
