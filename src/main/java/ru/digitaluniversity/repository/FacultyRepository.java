package ru.digitaluniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
}
