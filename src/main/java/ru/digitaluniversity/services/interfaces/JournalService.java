package ru.digitaluniversity.services.interfaces;

import ru.digitaluniversity.dto.JournalDto;
import ru.digitaluniversity.exception.ForbiddenException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.UnsupportedRoleException;

import java.util.List;

public interface JournalService extends DataService<JournalDto> {
    List<JournalDto> findByRole();

    JournalDto updateRating(Integer id, String rating) throws ForbiddenException, NotFoundException, UnsupportedRoleException;
}
