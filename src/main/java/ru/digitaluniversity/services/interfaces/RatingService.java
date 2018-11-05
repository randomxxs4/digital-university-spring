package ru.digitaluniversity.services.interfaces;

import ru.digitaluniversity.dto.RatingDto;

import java.util.List;

public interface RatingService extends DataService<RatingDto> {
    List<RatingDto> findAllDay();
}
