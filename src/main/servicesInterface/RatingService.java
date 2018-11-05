package ru.digitaluniversity.services;

import ru.digitaluniversity.dto.RatingDto;

import java.util.List;

public interface RatingService extends DataService<RatingDto> {
    List<RatingDto> findAllDay();
}
