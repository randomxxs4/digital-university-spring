package ru.digitaluniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.entity.User;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByUser(User user);

}
