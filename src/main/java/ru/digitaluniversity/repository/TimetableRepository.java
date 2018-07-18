package ru.digitaluniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Timetable;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Integer> {
}
