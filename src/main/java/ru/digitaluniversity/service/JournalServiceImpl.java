package ru.digitaluniversity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.JournalDto;
import ru.digitaluniversity.entity.*;
import ru.digitaluniversity.exception.*;
import ru.digitaluniversity.repository.*;
import ru.digitaluniversity.security.component.AuthenticationToken;
import ru.digitaluniversity.security.service.AuthorizationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JournalServiceImpl implements JournalService {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private Converter<Journal, JournalDto> converter;

    @Autowired
    private RatingRepostitory ratingRepostitory;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TimetableRepository timetableRepository;

    @Override
    public Page<JournalDto> findByRole(Optional<Integer> page, Optional<Integer> size) throws Exception {
        PageRequest pageRequest = PageRequest.of(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        String userRole = authorizationService.getUserRole();
        User user = ((AuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getUser();
        if (user != null) {
            if (AuthorizationService.STUDENT_ROLE.equals(userRole)) {
                Student student = studentRepository.findByUser(user);
                if (student != null) {
                    Page<Journal> journalPage = journalRepository.findByJournalStudent(student, pageRequest);
                    List<JournalDto> journalDtoList = getStreamConvert(journalPage);
                    Page<JournalDto> result = new PageImpl<>(journalDtoList, pageRequest, journalPage.getTotalElements());
                    return result;
                }
            }
            if (AuthorizationService.TEACHER_ROLE.equals(userRole)) {
                Teacher teacher = teacherRepository.findByUser(user);
                if (teacher != null) {
                    return findByTimetableTeacher(teacher, pageRequest);
                }
            }
        }
        throw new NotLogInException();
    }

    @Override
    public Page<JournalDto> findByRoleAndTimetable(Optional<Integer> page, Optional<Integer> size, Integer timetableId) throws Exception {
        PageRequest pageRequest = PageRequest.of(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        String userRole = authorizationService.getUserRole();
        User user = ((AuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getUser();
        Timetable timetable = timetableRepository.findById(timetableId).get();
        if (user != null && timetable != null) {
            if (AuthorizationService.STUDENT_ROLE.equals(userRole) || AuthorizationService.TEACHER_ROLE.equals(userRole)) {
                Page<Journal> journalPage = journalRepository.findByJournalTimetable(timetable, pageRequest);
                List<JournalDto> journalDtoList = getStreamConvert(journalPage);
                Page<JournalDto> result = new PageImpl<>(journalDtoList, pageRequest, journalPage.getTotalElements());
                return result;
            }
        }
        throw new NotLogInException();
    }

    @Override
    public JournalDto updateRating(Integer id, String rating) throws ForbiddenException, NotFoundException, ConvertException {
        Journal journal = journalRepository.findById(id).get();
        if (journal != null) {
            Rating ratingObj = ratingRepostitory.findByRating(rating);
            if (ratingObj != null) {
                journal.setJournalRating(ratingObj);
                Journal savedJournal = journalRepository.save(journal);
                return converter.convert(savedJournal);
            } else {
                throw new NotFoundException("Rating not found");
            }
        } else {
            throw new NotFoundException("Journal not found");
        }
    }

    @Override
    public Page<JournalDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        PageRequest pageRequest = new PageRequest(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        Page<Journal> allPages = journalRepository.findAll(pageRequest);
        List<JournalDto> journalDtoList = getStreamConvert(allPages);
        Page<JournalDto> result = new PageImpl<>(journalDtoList, pageRequest, allPages.getTotalElements());
        return result;
    }

    @Override
    public JournalDto findById(Integer id) throws ConvertException, NotFoundException {
        Journal journal = journalRepository.findById(id).get();
        if (journal != null) {
            JournalDto journalDto = converter.convert(journal);
            return journalDto;
        } else {
            throw new NotFoundException("Journal not found");
        }
    }

    private List<JournalDto> getStreamConvert(Page<Journal> journalPage) {
        return journalPage.getContent().stream()
                .map(journal -> {
                    try {
                        return converter.convert(journal);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new StreamConvertException("Could not convert Journal to Dto");
                    }
                }).collect(Collectors.toList());
    }

    private Page<JournalDto> findByTimetableTeacher(Teacher teacher, Pageable pageable) {
        List<Timetable> content = timetableRepository.findByTimetableTeacher(teacher, pageable).getContent();
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < content.size(); i++) {
            Journal byJournalTimetable = journalRepository.findByJournalTimetable(content.get(i));
            if (byJournalTimetable != null) {
                journalList.add(byJournalTimetable);
            }
        }
        List<JournalDto> journalDtoList = journalList.stream()
                .map(journal -> {
                    try {
                        return converter.convert(journal);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new StreamConvertException("Could not convert Journal to Dto");
                    }
                }).collect(Collectors.toList());
        Page<JournalDto> result = new PageImpl<>(journalDtoList, pageable, journalDtoList.size());
        return result;


    }
}
