package ru.digitaluniversity.services;

import ru.digitaluniversity.dto.SubjectDto;

import java.util.List;

public interface SubjectService extends DataService<SubjectDto> {
    List<SubjectDto> findAll();
}
