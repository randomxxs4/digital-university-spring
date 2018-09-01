package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.SpecialityDto;
import ru.digitaluniversity.entity.Speciality;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class SpecialityConverter implements Converter<Speciality, SpecialityDto> {
    @Override
    public SpecialityDto convertToDto(Speciality obj) {
        SpecialityDto specialityDto = new SpecialityDto();
        if (obj.getId() != null) {
            specialityDto.setId(obj.getId().toString());
        }
        if (obj.getTitle() != null) {
            specialityDto.setTitle(obj.getTitle());
        }
        return specialityDto;
    }

    @Override
    public Speciality convertToEntity(SpecialityDto obj) {
        Speciality speciality = new Speciality();
        if (obj.getId() != null) {
            speciality.setId(Integer.parseInt(obj.getId()));
        }
        if (obj.getTitle() != null) {
            speciality.setTitle(obj.getTitle());
        }
        return speciality;
    }
}
