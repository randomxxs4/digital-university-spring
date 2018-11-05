package ru.digitaluniversity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.SpecialityDto;
import ru.digitaluniversity.entity.Speciality;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.repository.SpecialityRepository;
import ru.digitaluniversity.services.interfaces.SpecialityService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialityServiceImpl implements SpecialityService {
    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public SpecialityDto findById(Integer id) throws ConvertException, NotFoundException {
        Speciality speciality = specialityRepository.findById(id).get();
        if (speciality != null) {
            return new SpecialityDto(speciality);
        } else {
            throw new NotFoundException("Speciality not found");
        }
    }

    @Override
    public SpecialityDto create(SpecialityDto obj) {
        return null;
    }

    @Override
    public List<SpecialityDto> findAll() {
        List<Speciality> all = specialityRepository.findAll();
        List<SpecialityDto> result = all.stream()
                .map(SpecialityDto::new)
                .collect(Collectors.toList());
        return result;
    }
}
