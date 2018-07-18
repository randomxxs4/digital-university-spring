package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.DayDto;
import ru.digitaluniversity.entity.Day;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class DayConverter implements Converter<Day, DayDto> {
    @Override
    public DayDto convert(Day obj) throws ConvertException, ConvertException {
        DayDto dayDto = new DayDto();
        dayDto.setId(obj.getId().toString());
        dayDto.setTitle(obj.getDay());
        return dayDto;
    }
}
