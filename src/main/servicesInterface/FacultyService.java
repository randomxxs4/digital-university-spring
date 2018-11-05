package ru.digitaluniversity.services;

import ru.digitaluniversity.dto.FacultyDto;

import java.util.List;

public interface FacultyService extends DataService<FacultyDto> {
    List<FacultyDto> findAll();
}
