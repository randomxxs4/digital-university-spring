package ru.digitaluniversity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Journal;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.entity.Timetable;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer> {
    Page<Journal> findByJournalStudent(Student student, Pageable pageable);

    List<Journal> findByJournalStudent(Student student);

    @Query(nativeQuery = true, value = "SELECT j.id, j.date, j.rating, j.student, j.subject, j.timetable " +
            " FROM journals j " +
            " JOIN timetables tt ON j.timetable = tt.id " +
            " JOIN teachers t ON tt.timetable_teacher_id = t.id " +
            " WHERE t.id = ?1")
    List<Journal> findByTeacher(Integer teacherId);

    Page<Journal> findByJournalTimetable(Timetable timetable, Pageable pageable);
}
