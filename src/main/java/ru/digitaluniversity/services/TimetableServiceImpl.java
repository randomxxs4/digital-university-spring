package ru.digitaluniversity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.TimetableDto;
import ru.digitaluniversity.entity.Student;
import ru.digitaluniversity.entity.Teacher;
import ru.digitaluniversity.entity.Timetable;
import ru.digitaluniversity.entity.User;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.NotLogInException;
import ru.digitaluniversity.repository.StudentRepository;
import ru.digitaluniversity.repository.TeacherRepository;
import ru.digitaluniversity.repository.TimetableRepository;
import ru.digitaluniversity.security.component.AuthenticationToken;
import ru.digitaluniversity.security.service.AuthorizationService;
import ru.digitaluniversity.services.interfaces.TimetableService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimetableServiceImpl implements TimetableService {
    @Autowired
    private TimetableRepository timetableRepository;
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<TimetableDto> findTimetableByRole() {
        User user = ((AuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getUser();
        if (user != null) {
            if (authorizationService.isStudent()) {
                Student student = studentRepository.findByUser(user);
                if (student != null) {
                    return timetableRepository.findByTimetableGroup(student.getStudentGroup())
                            .stream().sorted(Comparator.comparingInt(o -> o.getTimetableDay().getId()))
                            .map(TimetableDto::new).collect(Collectors.toList());
                }
            }
            if (authorizationService.isTeacher()) {
                Teacher teacher = teacherRepository.findByUser(user);
                if (teacher != null) {
                    return timetableRepository.findByTimetableTeacher(teacher)
                            .stream().sorted(Comparator.comparingInt(o -> o.getTimetableDay().getId()))
                            .map(TimetableDto::new)
                            .collect(Collectors.toList());
                }
            }
        }
        throw new NotLogInException();
    }

    @Override
    public List<TimetableDto> findAll() {
        return timetableRepository.findAll().stream()
                .map(TimetableDto::new).collect(Collectors.toList());
    }

    @Override
    public TimetableDto findById(Integer id) throws ConvertException, NotFoundException {
        Timetable timetable = timetableRepository.findById(id).orElseThrow(NotFoundException::new);
        return new TimetableDto(timetable);
    }

    @Override
    public TimetableDto create(TimetableDto obj) {
        return null;
    }
}