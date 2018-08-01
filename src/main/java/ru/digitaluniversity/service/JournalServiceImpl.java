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
        User user = ((AuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getUser();
        if (user != null) {
            String userRole = authorizationService.getUserRole(user.getId());
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
                    return findByTeacher(teacher, pageRequest);
                }
            }
        }
        throw new NotLogInException();
    }

    @Override
    public Page<JournalDto> findByRoleAndTimetable(Optional<Integer> page, Optional<Integer> size, Integer timetableId) throws Exception {
        PageRequest pageRequest = PageRequest.of(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        User user = ((AuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getUser();
        if (user != null) {
            Timetable timetable = timetableRepository.findById(timetableId).orElseThrow(NotFoundException :: new);
                String userRole = authorizationService.getUserRole(user.getId());
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
    public JournalDto updateRating(Integer id, String rating) throws ForbiddenException, NotFoundException, ConvertException, UnsupportedRoleException {
        User user = ((AuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getUser();
        if (user != null) {
            String userRole = authorizationService.getUserRole(user.getId());
            if (AuthorizationService.TEACHER_ROLE.equals(userRole)) {
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
                }
            } else {
                throw new UnsupportedRoleException();
            }
        }
        throw new NotLogInException("Journal not found");
    }

    @Override
    public Page<JournalDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        PageRequest pageRequest = PageRequest.of(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
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

    public Page<JournalDto> findByTeacher(Teacher teacher, Pageable pageable) {
        Page<Timetable> byTimetableTeacher = timetableRepository.findByTimetableTeacher(teacher, pageable);
        List<Timetable> content = byTimetableTeacher.getContent();
        List<JournalDto> journalDtoList = new ArrayList<>();
        for (int i = 0; i < content.size(); i++) {
            List<Journal> timetableJournals = content.get(i).getTimetableJournal();
            journalDtoList.addAll(getJournalDtos(timetableJournals));
        }
        return new PageImpl<>(journalDtoList, pageable, byTimetableTeacher.getTotalElements());
    }

    private List<JournalDto> getJournalDtos(List<Journal> journalList) {
        return journalList.stream()
                .map(journal -> {
                    try {
                        return converter.convert(journal);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new StreamConvertException("Could not convert Journal to Dto");
                    }
                }).collect(Collectors.toList());
    }
}
