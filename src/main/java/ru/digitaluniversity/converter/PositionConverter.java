package ru.digitaluniversity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.PositionDto;
import ru.digitaluniversity.dto.SubjectDto;
import ru.digitaluniversity.entity.Position;
import ru.digitaluniversity.entity.Subject;
import ru.digitaluniversity.exception.ConvertException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionConverter implements ManyToManyConverter<Position, PositionDto> {

    @Autowired
    private Converter<Subject, SubjectDto> subjectConverter;

    @Override
    public PositionDto convertToDto(Position obj) {
        PositionDto positionDto = new PositionDto();
        if (obj.getId() != null) {
            positionDto.setId(obj.getId().toString());
        }
        if (obj.getTitle() != null) {
            positionDto.setTitle(obj.getTitle());
        }
        if (obj.getSubjects() != null && !obj.getSubjects().isEmpty()) {
            List<Subject> subjects = obj.getSubjects();
            List<SubjectDto> subjectDtos = new ArrayList<>();
            for (int i = 0; i < subjects.size(); i++) {
                Subject subject = subjects.get(i);
                subjectDtos.add(subjectConverter.convertToDto(subject));
            }
            positionDto.setSubjects(subjectDtos);
        }
        return positionDto;
    }

    @Override
    public Position convertToEntity(PositionDto obj) {
        Position position = new Position();
        if (obj.getId() != null) {
            position.setId(Integer.parseInt(obj.getId()));
        }
        if (obj.getTitle() != null) {
            position.setTitle(obj.getTitle());
        }
//        if (obj.getSubjects() != null && !obj.getSubjects().isEmpty()) {
//            List<SubjectDto> subjectDtos = obj.getSubjects();
//            List<Subject> subjects = subjectDtos.stream().map((subjectDto -> subjectConverter.convertToEntity(subjectDto))).collect(Collectors.toList());
//            position.setSubjects(subjects);
//        }
        return position;
    }

    @Override
    public PositionDto convertManyToManyLink(Position obj) {
        PositionDto positionDto = new PositionDto();
        if (obj.getId() != null) {
            positionDto.setId(obj.getId().toString());
        }
        if (obj.getTitle() != null) {
            positionDto.setTitle(obj.getTitle());
        }
        if (obj.getSubjects() != null && !obj.getSubjects().isEmpty()) {
            List<Subject> subjects = obj.getSubjects();
            ArrayList<SubjectDto> subjectDtos = new ArrayList<>();
            for (int i = 0; i < subjects.size(); i++) {
                Subject subject = subjects.get(i);
                SubjectDto subjectDto = new SubjectDto();
                if (subject.getId() != null) {
                    subjectDto.setId(subject.getId().toString());
                }
                if (subject.getTitle() != null) {
                    subjectDto.setTitle(subject.getTitle());
                }
                subjectDtos.add(subjectDto);
            }
            positionDto.setSubjects(subjectDtos);
        }
        return positionDto;
    }
}
