package ru.digitaluniversity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.StudentDto;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.entity.User;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.StreamConvertException;
import ru.digitaluniversity.repository.StudentRepository;
import ru.digitaluniversity.repository.UserRepository;
import ru.digitaluniversity.services.interfaces.StudentService;

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

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<StudentDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        PageRequest pageRequest = new PageRequest(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        Page<Student> allPages = studentRepository.findAll(pageRequest);
        List<StudentDto> studentDtoList = allPages.getContent().stream()
                .map(student -> {
                    try {
                        return converter.convertToDto(student);
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
        User user = userRepository.findById(id).get();
        Student student = studentRepository.findByUser(user);
        if (student != null) {
            StudentDto studentDto = converter.convertToDto(student);
            return studentDto;
        } else {
            throw new NotFoundException("Student not found");
        }
    }

    @Override
    public StudentDto create(StudentDto obj) {
        Student student = converter.convertToEntity(obj);
        userRepository.save(student.getUser());
        return converter.convertToDto(student);
    }

    @Override
    public List<StudentDto> findAll() {
        List<Student> all = studentRepository.findAll();
        return all.stream()
                .map(student -> converter.convertToDto(student))
                .collect(Collectors.toList());
    }
}
