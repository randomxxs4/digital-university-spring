package ru.digitaluniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Group;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.entity.User;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByUser(User user);
    long countByStudentGroup(Group group);
}
