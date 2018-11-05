package ru.digitaluniversity.services;

import org.springframework.data.domain.Page;
import ru.digitaluniversity.dto.TimetableDto;

import java.util.List;
import java.util.Optional;

public interface TimetableService extends DataService<TimetableDto> {

    /**
     * Find timetable by user role.
     *
     * @param page the page
     * @param size the size
     * @return the page
     * @throws Exception the exception
     */
    Page<TimetableDto> findTimetableByRole(Optional<Integer> page, Optional<Integer> size) throws Exception;

    List<TimetableDto> findTimetableByRole();

    List<TimetableDto> findAll();

}
