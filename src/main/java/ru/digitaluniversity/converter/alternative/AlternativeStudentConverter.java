package ru.digitaluniversity.converter.alternative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.SpecialityDto;
import ru.digitaluniversity.dto.alternative.AlternativeStudentDto;
import ru.digitaluniversity.entity.Speciality;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class AlternativeStudentConverter implements Converter<Student, AlternativeStudentDto> {

    @Autowired
    private Converter<Speciality, SpecialityDto> specialityConverter;

    @Override
    public AlternativeStudentDto convertToDto(Student obj) {
        AlternativeStudentDto alternativeStudentDto = new AlternativeStudentDto();
        alternativeStudentDto.setId(obj.getId().toString());
        alternativeStudentDto.setMiddlename(obj.getUser().getMiddlename());
        alternativeStudentDto.setName(obj.getUser().getName());
        alternativeStudentDto.setSurname(obj.getUser().getSurname());
        alternativeStudentDto.setStudentSpeciality(specialityConverter.convertToDto(obj.getStudentSpeciality()));
        return alternativeStudentDto;

    }

    @Override
    public Student convertToEntity(AlternativeStudentDto obj) {
        return null;
    }
}
