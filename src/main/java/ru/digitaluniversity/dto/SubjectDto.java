package ru.digitaluniversity.dto;

/**
 * Класс, описывающий DTO сущности предмет.
 */
public class SubjectDto {
    private String id;
    private String title;
    private PositionDto position;

    public SubjectDto() {
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

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }
}
