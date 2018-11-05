package ru.digitaluniversity.converter.alternative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.PositionDto;
import ru.digitaluniversity.dto.alternative.AlternativeTeacherDto;
import ru.digitaluniversity.entity.Position;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.exception.ConvertException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlternativeTeacherConverter implements Converter<Teacher, AlternativeTeacherDto> {

    @Autowired
    private Converter<Position, PositionDto> positionConverter;

    @Override
    public AlternativeTeacherDto convertToDto(Teacher obj) {
        AlternativeTeacherDto alternativeTeacherDto = new AlternativeTeacherDto();
        alternativeTeacherDto.setId(obj.getId().toString());
        alternativeTeacherDto.setName(obj.getUser().getName());
        alternativeTeacherDto.setMiddlename(obj.getUser().getMiddlename());
        alternativeTeacherDto.setSurname(obj.getUser().getSurname());

        alternativeTeacherDto.setPosition(positionConverter.convertToDto(obj.getPosition()));
        return alternativeTeacherDto;

    }

    @Override
    public Teacher convertToEntity(AlternativeTeacherDto obj) {
        return null;
    }
}
