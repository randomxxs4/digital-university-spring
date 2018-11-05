package ru.digitaluniversity.services;

import org.springframework.data.domain.Page;
import ru.digitaluniversity.dto.JournalDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.ForbiddenException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.UnsupportedRoleException;

import java.util.List;
import java.util.Optional;

public interface JournalService extends DataService<JournalDto> {
    Page<JournalDto> findByRole(Optional<Integer> page, Optional<Integer> size) throws Exception;

    List<JournalDto> findByRole();


    Page<JournalDto> findByRoleAndTimetable(Optional<Integer> page, Optional<Integer> size, Integer timetableId) throws Exception;

    JournalDto updateRating(Integer id, String rating) throws ForbiddenException, ForbiddenException, NotFoundException, ConvertException, UnsupportedRoleException;

    List<JournalDto> findAll();
}
