package ru.digitaluniversity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.TeacherDto;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.StreamConvertException;
import ru.digitaluniversity.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private Converter<Teacher, TeacherDto> converter;

    @Override
    public Page<TeacherDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        PageRequest pageRequest = new PageRequest(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        Page<Teacher> allPages = teacherRepository.findAll(pageRequest);
        List<TeacherDto> teacherDtoList = allPages.getContent().stream()
                .map(teacher -> {
                    try {
                        return converter.convert(teacher);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new StreamConvertException("Could not convert Teacher to Dto");
                    }
                }).collect(Collectors.toList());
        Page<TeacherDto> result = new PageImpl<>(teacherDtoList, pageRequest, allPages.getTotalElements());
        return result;
    }

    @Override
    public TeacherDto findById(Integer id) throws ConvertException, NotFoundException {
        Teacher teacher = teacherRepository.findById(id).get();
        if (teacher != null) {
            TeacherDto teacherDto = converter.convert(teacher);
            return teacherDto;
        } else {
            throw new NotFoundException("Teacher not found");
        }
    }
}
