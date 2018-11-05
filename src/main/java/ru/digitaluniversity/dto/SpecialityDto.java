package ru.digitaluniversity.dto;

/**
 * Класс, описывающий DTO сущности специальность.
 */
public class SpecialityDto {
    private String id;
    private String title;

    public SpecialityDto() {
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
