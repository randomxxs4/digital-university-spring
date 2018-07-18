package ru.digitaluniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
