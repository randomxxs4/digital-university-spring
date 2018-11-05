package ru.digitaluniversity.services;

import ru.digitaluniversity.dto.SpecialityDto;

import java.util.List;

public interface SpecialityService extends DataService<SpecialityDto> {
    List<SpecialityDto> findAll();
}
