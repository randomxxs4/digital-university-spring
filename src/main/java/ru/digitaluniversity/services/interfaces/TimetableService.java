package ru.digitaluniversity.services.interfaces;

import ru.digitaluniversity.dto.TimetableDto;

import java.util.List;

public interface TimetableService extends DataService<TimetableDto> {
    List<TimetableDto> findTimetableByRole();

    List<TimetableDto> findAll();
}
