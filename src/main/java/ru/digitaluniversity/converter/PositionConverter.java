package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.PositionDto;
import ru.digitaluniversity.entity.Position;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class PositionConverter implements Converter<Position, PositionDto> {
    @Override
    public PositionDto convert(Position obj) throws ConvertException, ConvertException {
        PositionDto positionDto = new PositionDto();
        positionDto.setId(obj.getId().toString());
        positionDto.setTitle(obj.getTitle());
        // TODO: 17.07.2018 добавить преобразование предметов
        return positionDto;
    }
}
