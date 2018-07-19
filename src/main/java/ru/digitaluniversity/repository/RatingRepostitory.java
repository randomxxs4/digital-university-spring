package ru.digitaluniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitaluniversity.entity.Rating;

@Repository
public interface RatingRepostitory extends JpaRepository<Rating, Integer> {
    Rating findByRating(String rating);
}
