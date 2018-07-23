package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.JournalDto;
import ru.digitaluniversity.entity.Journal;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class JournalConverter implements Converter<Journal, JournalDto>{
    @Override
    public JournalDto convert(Journal obj) throws ConvertException, ConvertException {
        JournalDto journalDto = new JournalDto();
        journalDto.setId(obj.getId().toString());
        journalDto.setDate(obj.getJournalDate());
        journalDto.setRating(obj.getJournalRating().getRating());
        journalDto.setStudent(obj.getJournalStudent().getUser().getSurname());
        journalDto.setSubject(obj.getJournalSubject().getTitle());
        journalDto.setTimetableTeacher(obj.getJournalTimetable().getTimetableTeacher().getUser().getSurname());
        return journalDto;
    }
}
