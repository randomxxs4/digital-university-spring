package ru.digitaluniversity.dto;

import ru.digitaluniversity.entity.Faculty;

import java.util.List;

public class FacultyDto {
    private String id;
    private String name;
    private String description;
    private List<SpecialityDto> specialities;

    public FacultyDto() {
    }

    public FacultyDto(String id, String name, String description, List<SpecialityDto> specialities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.specialities = specialities;
    }

    public FacultyDto(Faculty faculty) {
    }
}
