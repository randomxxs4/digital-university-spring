package ru.digitaluniversity.dto;

import ru.digitaluniversity.entity.Rating;

/**
 * Класс, описывающий DTO сущности оценки в журнале.
 */
public class RatingDto {
    private String id;
    private String rating;

    public RatingDto() {
    }

    public RatingDto(Rating rating) {
        this.id = rating.getId().toString();
        this.rating = rating.getRating();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
