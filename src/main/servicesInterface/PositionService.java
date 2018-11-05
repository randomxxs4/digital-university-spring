package ru.digitaluniversity.services;

import ru.digitaluniversity.dto.PositionDto;

import java.util.List;

public interface PositionService extends DataService<PositionDto> {
    List<PositionDto> findAll();
}
