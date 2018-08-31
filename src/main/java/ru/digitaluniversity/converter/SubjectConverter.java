package ru.digitaluniversity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.PositionDto;
import ru.digitaluniversity.dto.SubjectDto;
import ru.digitaluniversity.entity.Position;
import ru.digitaluniversity.entity.Subject;
import ru.digitaluniversity.exception.ConvertException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectConverter implements Converter<Subject, SubjectDto> {

    @Autowired
    private Converter<Position, PositionDto> positionConverter;

    @Override
    public SubjectDto convertToDto(Subject obj) {
        SubjectDto subjectDto = new SubjectDto();
        if (obj.getId() != null) {
            subjectDto.setId(obj.getId().toString());
        }
        if (obj.getTitle() != null) {
            subjectDto.setTitle(obj.getTitle());
        }
        if (obj.getPositions() != null && !obj.getPositions().isEmpty()) {
            List<Position> positions = obj.getPositions();
            List<PositionDto> positionDtos = positions.stream().map((position -> positionConverter.convertToDto(position))).collect(Collectors.toList());
            subjectDto.setPositions(positionDtos);
        }
        return subjectDto;
    }

    @Override
    public Subject convertToEntity(SubjectDto obj) {
        Subject subject = new Subject();
        if (obj.getId() != null) {
            subject.setId(Integer.parseInt(obj.getId()));
        }
        if (obj.getTitle() != null) {
            subject.setTitle(obj.getTitle());
        }
        if (obj.getPositions() != null && !obj.getPositions().isEmpty()) {
            List<PositionDto> positionDtos = obj.getPositions();
            List<Position> positions = positionDtos.stream().map(position -> positionConverter.convertToEntity(position)).collect(Collectors.toList());
            subject.setPositions(positions);
        }
        return subject;
    }
}
