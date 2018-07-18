package ru.digitaluniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
