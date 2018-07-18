package ru.digitaluniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Day;

@Repository
public interface DayRepository extends JpaRepository<Day, Integer> {
}
