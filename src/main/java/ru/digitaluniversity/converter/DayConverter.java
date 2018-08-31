package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.DayDto;
import ru.digitaluniversity.entity.Day;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class DayConverter implements Converter<Day, DayDto> {
    @Override
    public DayDto convertToDto(Day obj) {
        DayDto dayDto = new DayDto();
        dayDto.setId(obj.getId().toString());
        dayDto.setTitle(obj.getDay());
        return dayDto;
    }

    @Override
    public Day convertToEntity(DayDto obj) {
        Day day = new Day();
        if (obj.getId() != null) {
            day.setId(Integer.parseInt(obj.getId()));
        }
        if (obj.getTitle() != null) {
            day.setDay(obj.getTitle());
        }
        return day;
    }
}
