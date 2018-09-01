package ru.digitaluniversity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.BasicInfoDto;
import ru.digitaluniversity.dto.PositionDto;
import ru.digitaluniversity.dto.TeacherDto;
import ru.digitaluniversity.entity.Position;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.entity.User;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherConverter implements ManyToManyConverter<Teacher, TeacherDto> {

    @Autowired
    private ManyToManyConverter<Position, PositionDto> positionConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TeacherDto convertToDto(Teacher obj) {
        TeacherDto teacherDto = new TeacherDto();
        if (obj.getId() != null){
            teacherDto.setId(obj.getId().toString());
        }
        if (obj.getPositions() != null && !obj.getPositions().isEmpty()){
            List<Position> positions = obj.getPositions();
            List<PositionDto> positionDtos = positions.stream().map((position -> positionConverter.convertManyToManyLink(position))).collect(Collectors.toList());
            teacherDto.setPositions(positionDtos);
        }
        if (obj.getUser() != null){
            BasicInfoDto basicInfoDto = new BasicInfoDto();
            basicInfoDto.setId(obj.getUser().getId().toString());
            basicInfoDto.setSurname(obj.getUser().getSurname());
            basicInfoDto.setMiddlename(obj.getUser().getMiddlename());
            basicInfoDto.setName(obj.getUser().getName());
            teacherDto.setBasicInfo(basicInfoDto);
        }
        return teacherDto;
    }

    @Override
    public Teacher convertToEntity(TeacherDto obj) {
        Teacher teacher = new Teacher();
        if (obj.getId() != null) {
            teacher.setId(Integer.parseInt(obj.getId()));
        }
        if (obj.getPositions() != null && !obj.getPositions().isEmpty()){
            List<PositionDto> positionDtos = obj.getPositions();
            List<Position> positions = positionDtos.stream().map(positionDto -> positionConverter.convertToEntity(positionDto)).collect(Collectors.toList());
            teacher.setPositions(positions);
        }
        if (obj.getBasicInfo() != null) {
            User bySurname = userRepository.findBySurname(obj.getBasicInfo().getSurname());
            if (bySurname != null) {
                teacher.setUser(bySurname);
            }
        }
        return teacher;
    }

    @Override
    public TeacherDto convertManyToManyLink(Teacher obj) {
        return null;
    }
}
