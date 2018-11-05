package ru.digitaluniversity.dto;

import ru.digitaluniversity.entity.Position;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, описывающий DTO сущности должность преподавателя.
 */
public class PositionDto {
    private String id;
    private String title;
    private List<SubjectDto> subjects;

    public PositionDto() {
    }

    public PositionDto(Position position) {
        this.id = position.getId().toString();
        this.title = position.getTitle();
        this.subjects = position.getSubjects().stream().map(SubjectDto::new).collect(Collectors.toList());
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
