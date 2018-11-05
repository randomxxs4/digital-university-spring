package ru.digitaluniversity.services.interfaces;

import ru.digitaluniversity.dto.StudentDto;

import java.util.List;

public interface StudentService extends DataService<StudentDto> {
    List<StudentDto> findAll();
}
