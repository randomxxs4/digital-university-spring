package ru.digitaluniversity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.StudentDto;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.StreamConvertException;
import ru.digitaluniversity.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private Converter<Student, StudentDto> converter;

    @Override
    public Page<StudentDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        PageRequest pageRequest = new PageRequest(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        Page<Student> allPages = studentRepository.findAll(pageRequest);
        List<StudentDto> studentDtoList = allPages.getContent().stream()
                .map(student -> {
                    try {
                        return converter.convert(student);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new StreamConvertException("Could not convert Student to Dto");
                    }
                }).collect(Collectors.toList());
        Page<StudentDto> result = new PageImpl<>(studentDtoList, pageRequest, allPages.getTotalElements());
        return result;
    }

    @Override
    public StudentDto findById(Integer id) throws ConvertException, NotFoundException {
        Student student = studentRepository.findById(id).get();
        if (student != null) {
            StudentDto studentDto = converter.convert(student);
            return studentDto;
        } else {
            throw new NotFoundException("Student not found");
        }
    }
}
