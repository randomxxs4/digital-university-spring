package ru.digitaluniversity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.DayDto;
import ru.digitaluniversity.dto.PositionDto;
import ru.digitaluniversity.entity.Day;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.StreamConvertException;
import ru.digitaluniversity.repository.DayRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DayServiceImpl implements DayService {
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 7;

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private Converter<Day, DayDto> converter;

    @Override
    public Page<DayDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        PageRequest pageRequest = new PageRequest(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        Page<Day> allPages = dayRepository.findAll(pageRequest);
        List<DayDto> dayDtoList = allPages.getContent().stream()
                .map(day -> {
                    try {
                        return converter.convert(day);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new StreamConvertException("Не удалось преобразовать Day в Dto");
                    }
                }).collect(Collectors.toList());
        Page<DayDto> result = new PageImpl<>(dayDtoList, pageRequest, allPages.getTotalElements());
        return result;
    }

    @Override
    public DayDto findById(Integer id) throws ConvertException, NotFoundException {
        Day day = dayRepository.findById(id).get();
        if (day != null) {
            DayDto dayDto = converter.convert(day);
            return dayDto;
        }
        else{
            throw new NotFoundException("Day not found");
        }
    }
}
