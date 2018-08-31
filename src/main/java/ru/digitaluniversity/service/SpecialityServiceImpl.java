package ru.digitaluniversity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.RatingDto;
import ru.digitaluniversity.dto.SpecialityDto;
import ru.digitaluniversity.entity.Rating;
import ru.digitaluniversity.entity.Speciality;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.StreamConvertException;
import ru.digitaluniversity.repository.SpecialityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private SpecialityRepository specialityRepository;

    @Autowired
    private Converter<Speciality, SpecialityDto> converter;

    @Override
    public Page<SpecialityDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        PageRequest pageRequest = new PageRequest(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        Page<Speciality> allPages = specialityRepository.findAll(pageRequest);
        List<SpecialityDto> specialityDtoList = allPages.getContent().stream()
                .map(speciality -> {
                    try {
                        return converter.convertToDto(speciality);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new StreamConvertException("Could not convert Speciality to Dto");
                    }
                }).collect(Collectors.toList());
        Page<SpecialityDto> result = new PageImpl<>(specialityDtoList, pageRequest, allPages.getTotalElements());
        return result;
    }

    @Override
    public SpecialityDto findById(Integer id) throws ConvertException, NotFoundException {
        Speciality speciality = specialityRepository.findById(id).get();
        if (speciality != null) {
            SpecialityDto specialityDto = converter.convertToDto(speciality);
            return specialityDto;
        } else {
            throw new NotFoundException("Speciality not found");
        }
    }

    @Override
    public SpecialityDto create(SpecialityDto obj) {
        return null;
    }
}
