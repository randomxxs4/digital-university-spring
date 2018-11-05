package ru.digitaluniversity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.BasicInfoDto;
import ru.digitaluniversity.dto.GroupDto;
import ru.digitaluniversity.dto.SpecialityDto;
import ru.digitaluniversity.dto.StudentDto;
import ru.digitaluniversity.entity.Group;
import ru.digitaluniversity.entity.Speciality;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.entity.User;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.repository.UserRepository;
import ru.digitaluniversity.security.dto.UserDto;

@Service
public class StudentConverter implements Converter<Student, StudentDto> {

    @Autowired
    private Converter<Group, GroupDto> groupConverter;

    @Autowired
    private Converter<Speciality, SpecialityDto> specialityConverter;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public StudentDto convertToDto(Student obj) {
        StudentDto studentDto = new StudentDto();
        if (obj.getId() != null) {
            studentDto.setId(obj.getId().toString());
        }
        if (obj.getUser() != null) {
            BasicInfoDto basicInfoDto = new BasicInfoDto();
            basicInfoDto.setId(obj.getUser().getId().toString());
            basicInfoDto.setSurname(obj.getUser().getSurname());
            basicInfoDto.setMiddlename(obj.getUser().getMiddlename());
            basicInfoDto.setName(obj.getUser().getName());
            studentDto.setBasicInfo(basicInfoDto);}
        if (obj.getStudentGroup() != null) {
            studentDto.setStudentGroup(groupConverter.convertToDto(obj.getStudentGroup()));
        }
        if (obj.getStudentSpeciality() != null) {
            studentDto.setStudentSpeciality(specialityConverter.convertToDto(obj.getStudentSpeciality()));
        }
        return studentDto;
    }

    @Override
    public Student convertToEntity(StudentDto obj) {
        Student student = new Student();
        if (obj.getId() != null) {
            student.setId(Integer.parseInt(obj.getId()));
        }
        if (obj.getStudentGroup() != null) {
            student.setStudentGroup(groupConverter.convertToEntity(obj.getStudentGroup()));
        }
        if (obj.getStudentSpeciality() != null) {
            student.setStudentSpeciality(specialityConverter.convertToEntity(obj.getStudentSpeciality()));
        }
        if (obj.getBasicInfo() != null){
            User bySurname = userRepository.findBySurname(obj.getBasicInfo().getSurname());
            if (bySurname != null){
                student.setUser(bySurname);
            }
        }
        return student;
    }
}
