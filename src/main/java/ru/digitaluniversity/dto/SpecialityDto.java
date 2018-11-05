package ru.digitaluniversity.dto;

import ru.digitaluniversity.entity.Speciality;

/**
 * Класс, описывающий DTO сущности специальность.
 */
public class SpecialityDto {
    private String id;
    private String title;

    public SpecialityDto() {
    }

    public SpecialityDto(Speciality speciality) {
        this.id = speciality.getId().toString();
        this.title = speciality.getTitle();
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
