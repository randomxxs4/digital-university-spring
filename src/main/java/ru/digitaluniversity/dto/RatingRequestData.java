package ru.digitaluniversity.dto;

/**
 * The type Rating request data from form.
 */
public class RatingRequestData {
    private String id;
    private String rating;

    public RatingRequestData() {
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
