package ru.digitaluniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
