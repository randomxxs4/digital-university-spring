package ru.digitaluniversity.dto;

/**
 * Класс, описывающий DTO сущности день.
 */
public class DayDto {
    private String id;
    private String title;

    public DayDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
