package ru.digitaluniversity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.TeacherDto;
import ru.digitaluniversity.dto.TimetableDto;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.entity.Timetable;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.StreamConvertException;
import ru.digitaluniversity.repository.TimetableRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimetableServiceImpl implements TimetableService {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private TimetableRepository timetableRepository;

    @Autowired
    private Converter<Timetable, TimetableDto> converter;

    @Override
    public Page<TimetableDto> findTimetableByRole(Optional<Integer> page, Optional<Integer> size) throws Exception {
        return null;
    }

    @Override
    public Page<TimetableDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        PageRequest pageRequest = new PageRequest(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        Page<Timetable> allPages = timetableRepository.findAll(pageRequest);
        List<TimetableDto> timetableDtoList = allPages.getContent().stream()
                .map(timetable -> {
                    try {
                        return converter.convert(timetable);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new StreamConvertException("Не удалось преобразовать Timetable в Dto");
                    }
                }).collect(Collectors.toList());
        Page<TimetableDto> result = new PageImpl<>(timetableDtoList, pageRequest, allPages.getTotalElements());
        return result;
    }

    @Override
    public TimetableDto findById(Integer id) throws ConvertException, NotFoundException {
        Timetable timetable = timetableRepository.findById(id).get();
        if (timetable != null) {
            TimetableDto timetableDto = converter.convert(timetable);
            return timetableDto;
        } else {
            throw new NotFoundException("Timetable not found");
        }
    }
}
