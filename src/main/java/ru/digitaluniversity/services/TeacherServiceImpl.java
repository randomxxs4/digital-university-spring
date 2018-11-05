package ru.digitaluniversity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.TeacherDto;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.entity.User;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.repository.TeacherRepository;
import ru.digitaluniversity.repository.UserRepository;
import ru.digitaluniversity.services.interfaces.TeacherService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<TeacherDto> findAll() {
        return teacherRepository.findAll().stream().map(TeacherDto::new).collect(Collectors.toList());
    }

    @Override
    public TeacherDto findById(Integer id) throws NotFoundException {
        User user = userRepository.findById(id).get();
        Teacher teacher = teacherRepository.findByUser(user);
        if (teacher != null) {
            return new TeacherDto(teacher);
        } else {
            throw new NotFoundException("Teacher not found");
        }
    }

    @Override
    public TeacherDto create(TeacherDto obj) {
        return null;
    }
}
