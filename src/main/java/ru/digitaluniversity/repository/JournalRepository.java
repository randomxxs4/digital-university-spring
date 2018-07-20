package ru.digitaluniversity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Journal;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.entity.Timetable;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer> {
    Page<Journal> findByJournalStudent(Student student, Pageable pageable);
    Page<Journal> findByJournalTimetable(Timetable timetable, Pageable pageable);
    Journal findByJournalTimetable(Timetable timetable);
}
