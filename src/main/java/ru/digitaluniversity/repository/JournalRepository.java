package ru.digitaluniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Journal;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer> {
}
