package ru.digitaluniversity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.*;
import ru.digitaluniversity.dto.alternative.AlternativeGroupDto;
import ru.digitaluniversity.dto.alternative.AlternativeStudentDto;
import ru.digitaluniversity.dto.alternative.AlternativeTeacherDto;
import ru.digitaluniversity.entity.*;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.UnsupportedRoleException;
import ru.digitaluniversity.repository.StudentRepository;
import ru.digitaluniversity.repository.TeacherRepository;
import ru.digitaluniversity.security.component.AuthenticationToken;
import ru.digitaluniversity.security.service.AuthorizationService;

import java.util.ArrayList;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private Converter<Position, PositionDto> positionDtoConverter;

    @Autowired
    private Converter<Group, GroupDto> groupDtoConverter;

    @Autowired
    private Converter<Speciality, SpecialityDto> specialityDtoConverter;

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public ProfileInfoDto generateInfoByRole() throws NotFoundException, ConvertException, UnsupportedRoleException {
        User user = ((AuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getUser();
        if (user != null) {
            ProfileInfoDto profileInfoDto = new ProfileInfoDto();
            BasicInfoDto basicInfoDto = new BasicInfoDto();
            basicInfoDto.setId(user.getId().toString());
            basicInfoDto.setSurname(user.getSurname());
            basicInfoDto.setMiddlename(user.getMiddlename());
            basicInfoDto.setName(user.getName());
            profileInfoDto.setBasicInfo(basicInfoDto);
            if (user.getStudent() != null) {
                Student student = user.getStudent();
                PositionDto positionDto = new PositionDto();
                positionDto.setSubjects(new ArrayList<>());
                profileInfoDto.setPosition(positionDto);

                profileInfoDto.setStudentSpeciality(
                        specialityDtoConverter.convertToDto(student.getStudentSpeciality()));
                profileInfoDto.setStudentGroup(groupDtoConverter.convertToDto(student.getStudentGroup()));
                return profileInfoDto;
            } else if (user.getTeacher() != null) {
                Teacher teacher = user.getTeacher();
                profileInfoDto.setPosition(positionDtoConverter.convertToDto(teacher.getPosition()));
                return profileInfoDto;
            }
        }
        throw new NotFoundException();
    }
}
