package ru.digitaluniversity.services.interfaces;

import ru.digitaluniversity.dto.PositionDto;

import java.util.List;

public interface PositionService extends DataService<PositionDto> {
    List<PositionDto> findAll();
}
