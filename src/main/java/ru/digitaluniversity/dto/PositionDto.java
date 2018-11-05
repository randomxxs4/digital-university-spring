package ru.digitaluniversity.dto;

import java.util.List;

/**
 * Класс, описывающий DTO сущности должность преподавателя.
 */
public class PositionDto {
    private String id;
    private String title;
    private List<SubjectDto> subjects;

    public PositionDto() {
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

    public List<SubjectDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDto> subjects) {
        this.subjects = subjects;
    }
}
