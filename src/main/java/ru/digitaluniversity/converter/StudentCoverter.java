package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.StudentDto;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class StudentCoverter implements Converter<Student, StudentDto> {
    @Override
    public StudentDto convert(Student obj) throws ConvertException, ConvertException {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(obj.getId().toString());
        studentDto.setMiddleName(obj.getMiddlename());
        studentDto.setName(obj.getName());
        studentDto.setSurname(obj.getSurname());
        studentDto.setStudentGroup(obj.getStudentGroup().getTitle());
        studentDto.setStudentSpeciality(obj.getStudentSpeciality().getTitle());
        return studentDto;
    }
}
