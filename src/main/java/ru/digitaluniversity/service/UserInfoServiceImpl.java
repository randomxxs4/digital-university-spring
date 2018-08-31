package ru.digitaluniversity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.BasicInfoDto;
import ru.digitaluniversity.dto.alternative.AlternativeGroupDto;
import ru.digitaluniversity.dto.alternative.AlternativeStudentDto;
import ru.digitaluniversity.dto.alternative.AlternativeTeacherDto;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.entity.User;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.UnsupportedRoleException;
import ru.digitaluniversity.repository.StudentRepository;
import ru.digitaluniversity.repository.TeacherRepository;
import ru.digitaluniversity.security.component.AuthenticationToken;
import ru.digitaluniversity.security.service.AuthorizationService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private Converter<Teacher, AlternativeTeacherDto> teacherDtoConverter;

    @Autowired
    private Converter<Student, AlternativeStudentDto> studentDtoConverter;

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public BasicInfoDto generateInfoByRole() throws NotFoundException, ConvertException, UnsupportedRoleException {
            User user = ((AuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getUser();
            if (user != null) {
                String userRole = authorizationService.getUserRole(user.getId());
                if (AuthorizationService.STUDENT_ROLE.equals(userRole)) {
                    Student student = studentRepository.findByUser(user);
                    if (student != null) {
                        return generateStudentInfo(student.getId());
                    }
                }
                else if (AuthorizationService.TEACHER_ROLE.equals(userRole)) {
                    Teacher teacher = teacherRepository.findByUser(user);
                    if (teacher != null) {
                        return generateTeacherInfo(teacher.getId());
                    }
                } else {
                    throw new UnsupportedRoleException();
                }
            }
        throw new NotFoundException();
    }


    public AlternativeStudentDto generateStudentInfo(Integer id) throws ConvertException {
        Student student = studentRepository.findById(id).get();
        if (student != null) {
            AlternativeStudentDto studentDto = studentDtoConverter.convertToDto(student);
            AlternativeGroupDto alternativeGroupDto = new AlternativeGroupDto();
            alternativeGroupDto.setId(student.getStudentGroup().getId().toString());
            alternativeGroupDto.setTitle(student.getStudentGroup().getTitle());
            alternativeGroupDto.setCountStundent(studentRepository.countByStudentGroup(student.getStudentGroup()));
            studentDto.setStudentGroup(alternativeGroupDto);
            return studentDto;
        }
        throw new ConvertException();
    }


    public AlternativeTeacherDto generateTeacherInfo(Integer id) throws ConvertException {
        Teacher teacher = teacherRepository.findById(id).get();
        if (teacher != null) {
            return teacherDtoConverter.convertToDto(teacher);
        }
        throw new ConvertException();
    }
}
