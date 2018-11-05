package ru.digitaluniversity.dto;

import ru.digitaluniversity.entity.Subject;

import java.util.Optional;

/**
 * Класс, описывающий DTO сущности предмет.
 */
public class SubjectDto {
    private String id;
    private String title;


    public SubjectDto() {
    }

    public SubjectDto(Subject subject) {
        this.id = subject.getId().toString();
        this.title = subject.getTitle();
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
