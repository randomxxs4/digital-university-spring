package ru.digitaluniversity.services;

import ru.digitaluniversity.dto.StudentDto;

import java.util.List;

public interface StudentService extends DataService<StudentDto> {
    List<StudentDto> findAll();
}
