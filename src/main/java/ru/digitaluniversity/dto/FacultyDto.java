package ru.digitaluniversity.dto;

import ru.digitaluniversity.entity.Faculty;

import java.util.List;
import java.util.stream.Collectors;

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
        this.id = faculty.getId().toString();
        this.name = faculty.getName();
        this.description = faculty.getDescription();
        this.specialities = faculty.getSpecialities()
                .stream().map(SpecialityDto::new).collect(Collectors.toList());
    }
}
