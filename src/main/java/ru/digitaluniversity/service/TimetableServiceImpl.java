package ru.digitaluniversity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.JournalDto;
import ru.digitaluniversity.dto.TeacherDto;
import ru.digitaluniversity.dto.TimetableDto;
import ru.digitaluniversity.entity.*;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.NotLogInException;
import ru.digitaluniversity.exception.StreamConvertException;
import ru.digitaluniversity.repository.StudentRepository;
import ru.digitaluniversity.repository.TeacherRepository;
import ru.digitaluniversity.repository.TimetableRepository;
import ru.digitaluniversity.security.component.AuthenticationToken;
import ru.digitaluniversity.security.service.AuthorizationService;

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

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public Page<TimetableDto> findTimetableByRole(Optional<Integer> page, Optional<Integer> size) throws Exception {
        PageRequest pageRequest = PageRequest.of(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        String userRole = authorizationService.getUserRole();
        User user = ((AuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getUser();
        if (user != null) {
            if (AuthorizationService.STUDENT_ROLE.equals(userRole)) {
                Student student = studentRepository.findByUser(user);
                if (student != null) {
                    Page<Timetable> timetablePage = timetableRepository.findByTimetableGroup(student.getStudentGroup(), pageRequest);
                    List<TimetableDto> timetableDtoList = getStreamConvert(timetablePage);
                    Page<TimetableDto> result = new PageImpl<>(timetableDtoList, pageRequest, timetablePage.getTotalElements());
                    return result;
                }
            }
            if (AuthorizationService.TEACHER_ROLE.equals(userRole)) {
                Teacher teacher = teacherRepository.findByUser(user);
                if (teacher != null) {
                    Page<Timetable> timetablePage = timetableRepository.findByTimetableTeacher(teacher, pageRequest);
                    List<TimetableDto> timetableDtoList = getStreamConvert(timetablePage);
                    Page<TimetableDto> result = new PageImpl<>(timetableDtoList, pageRequest, timetablePage.getTotalElements());
                    return result;
                }
            }
        }
        throw new NotLogInException();
    }

    @Override
    public Page<TimetableDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        PageRequest pageRequest = PageRequest.of(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        Page<Timetable> allPages = timetableRepository.findAll(pageRequest);
        List<TimetableDto> timetableDtoList = getStreamConvert(allPages);
        Page<TimetableDto> result = new PageImpl<>(timetableDtoList, pageRequest, allPages.getTotalElements());
        return result;
    }

    private List<TimetableDto> getStreamConvert(Page<Timetable> allPages) {
        return allPages.getContent().stream()
                .map(timetable -> {
                    try {
                        return converter.convert(timetable);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new StreamConvertException("Не удалось преобразовать Timetable в Dto");
                    }
                }).collect(Collectors.toList());
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

