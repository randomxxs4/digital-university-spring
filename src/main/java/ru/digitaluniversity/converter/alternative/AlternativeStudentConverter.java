package ru.digitaluniversity.converter.alternative;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.alternative.AlternativeGroupDto;
import ru.digitaluniversity.dto.alternative.AlternativeStudentDto;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class AlternativeStudentConverter implements Converter<Student, AlternativeStudentDto> {
    @Override
    public AlternativeStudentDto convert(Student obj) throws ConvertException, ConvertException {
        AlternativeStudentDto alternativeStudentDto = new AlternativeStudentDto();
        if (obj != null) {
            alternativeStudentDto.setId(obj.getId().toString());
            alternativeStudentDto.setMiddlename(obj.getUser().getMiddlename());
            alternativeStudentDto.setName(obj.getUser().getName());
            alternativeStudentDto.setSurname(obj.getUser().getSurname());
            alternativeStudentDto.setStudentSpeciality(obj.getStudentSpeciality().getTitle());
            return alternativeStudentDto;
        }
        throw new ConvertException();
    }
}
