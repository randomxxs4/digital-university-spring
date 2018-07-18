package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.SubjectDto;
import ru.digitaluniversity.entity.Subject;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class SubjectConverter implements Converter<Subject, SubjectDto> {
    @Override
    public SubjectDto convert(Subject obj) throws ConvertException, ConvertException {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(obj.getId().toString());
        subjectDto.setTitle(obj.getTitle());
        // TODO: 17.07.2018 добавить должность
        return subjectDto;
    }
}
