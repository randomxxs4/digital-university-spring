package ru.digitaluniversity.converter.alternative;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.alternative.AlternativeTeacherDto;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class AlternativeTeacherConverter implements Converter<Teacher, AlternativeTeacherDto> {
    @Override
    public AlternativeTeacherDto convert(Teacher obj) throws ConvertException, ConvertException {
        if (obj != null){
            AlternativeTeacherDto alternativeTeacherDto = new AlternativeTeacherDto();
            alternativeTeacherDto.setId(obj.getId().toString());
            alternativeTeacherDto.setName(obj.getUser().getName());
            alternativeTeacherDto.setMiddlename(obj.getUser().getMiddlename());
            alternativeTeacherDto.setSurname(obj.getUser().getSurname());
            alternativeTeacherDto.setPosition(obj.getTeacherPosition().getTitle());
            return alternativeTeacherDto;
        }
        throw new ConvertException();
    }
}
