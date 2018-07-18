package ru.digitaluniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Pair;

@Repository
public interface PairRepository extends JpaRepository<Pair, Integer> {
}
