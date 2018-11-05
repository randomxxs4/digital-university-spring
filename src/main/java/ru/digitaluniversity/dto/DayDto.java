package ru.digitaluniversity.dto;

import ru.digitaluniversity.entity.Day;

/**
 * Класс, описывающий DTO сущности день.
 */
public class DayDto {
    private String id;
    private String title;

    public DayDto() {
    }

    public DayDto(Day day) {
        this.id = day.getId().toString();
        this.title = day.getDay();
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
