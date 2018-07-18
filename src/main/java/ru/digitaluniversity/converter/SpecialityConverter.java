package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.SpecialityDto;
import ru.digitaluniversity.entity.Speciality;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class SpecialityConverter implements Converter<Speciality, SpecialityDto> {
    @Override
    public SpecialityDto convert(Speciality obj) throws ConvertException, ConvertException {
        SpecialityDto specialityDto = new SpecialityDto();
        specialityDto.setId(obj.getId().toString());
        specialityDto.setTitle(obj.getTitle());
        return specialityDto;
    }
}
