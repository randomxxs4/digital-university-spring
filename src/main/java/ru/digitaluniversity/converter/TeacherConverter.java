package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.TeacherDto;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class TeacherConverter implements Converter<Teacher, TeacherDto> {
    @Override
    public TeacherDto convert(Teacher obj) throws ConvertException, ConvertException {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(obj.getId().toString());
        teacherDto.setMiddleName(obj.getMiddlename());
        teacherDto.setName(obj.getName());
        teacherDto.setSurname(obj.getSurname());
        teacherDto.setPosition(obj.getTeacherPosition().getTitle());
        return teacherDto;
    }
}
