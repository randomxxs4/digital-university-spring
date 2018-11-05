package ru.digitaluniversity.services.interfaces;

import ru.digitaluniversity.dto.SubjectDto;

import java.util.List;

public interface SubjectService extends DataService<SubjectDto> {
    List<SubjectDto> findAll();
}
