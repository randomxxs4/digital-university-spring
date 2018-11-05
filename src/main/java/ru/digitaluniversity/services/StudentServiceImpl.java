package ru.digitaluniversity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.StudentDto;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.entity.User;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.repository.StudentRepository;
import ru.digitaluniversity.repository.UserRepository;
import ru.digitaluniversity.services.interfaces.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public StudentDto findById(Integer id) throws NotFoundException {
        User user = userRepository.findById(id).get();
        Student student = studentRepository.findByUser(user);
        if (student != null) {
            return new StudentDto(student);
        } else {
            throw new NotFoundException("Student not found");
        }
    }

    @Override
    public StudentDto create(StudentDto obj) {
        return null;
    }

    @Override
    public List<StudentDto> findAll() {
        List<Student> all = studentRepository.findAll();
        return all.stream()
                .map(StudentDto::new)
                .collect(Collectors.toList());
    }
}
