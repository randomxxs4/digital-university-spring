package ru.digitaluniversity.dto;

import ru.digitaluniversity.entity.Speciality;

/**
 * Класс, описывающий DTO сущности специальность.
 */
public class SpecialityDto {
    private String id;
    private String title;
    private String description;

    public SpecialityDto() {
    }

    public SpecialityDto(Speciality speciality) {
        this.id = speciality.getId().toString();
        this.title = speciality.getTitle();
        this.description = speciality.getDescription();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
