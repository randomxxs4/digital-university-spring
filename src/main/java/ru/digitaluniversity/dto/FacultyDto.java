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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SpecialityDto> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<SpecialityDto> specialities) {
        this.specialities = specialities;
    }
}
