package ru.digitaluniversity.services.interfaces;

import ru.digitaluniversity.dto.SpecialityDto;

import java.util.List;

public interface SpecialityService extends DataService<SpecialityDto> {
    List<SpecialityDto> findAll();
}
