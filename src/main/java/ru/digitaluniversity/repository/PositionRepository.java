package ru.digitaluniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
}
