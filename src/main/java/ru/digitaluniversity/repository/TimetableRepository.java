package ru.digitaluniversity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Group;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.entity.Timetable;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Integer> {
    Page<Timetable> findByTimetableGroup(Group group, Pageable pageable);

    Page<Timetable> findByTimetableTeacher(Teacher teacher, Pageable pageable);
}
