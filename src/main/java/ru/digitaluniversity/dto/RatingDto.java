package ru.digitaluniversity.dto;

/**
 * Класс, описывающий DTO сущности оценки в журнале.
 */
public class RatingDto {
    private String id;
    private String rating;

    public RatingDto() {
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
