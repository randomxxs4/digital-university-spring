package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.TimetableDto;
import ru.digitaluniversity.entity.Timetable;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class TimetableConverter implements Converter<Timetable, TimetableDto> {
    @Override
    public TimetableDto convert(Timetable obj) throws ConvertException, ConvertException {
        TimetableDto timetableDto = new TimetableDto();
        timetableDto.setId(obj.getId().toString());
        timetableDto.setDay(obj.getTimetableDay().getDay());
        timetableDto.setGroup(obj.getTimetableGroup().getTitle());
        timetableDto.setPair(obj.getTimetablePair().getNumber().toString());
        timetableDto.setSubject(obj.getTimetableSubject().getTitle());
        timetableDto.setTeacher(obj.getTimetableTeacher().getSurname());
        return timetableDto;
    }
}
