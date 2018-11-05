package ru.digitaluniversity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.JournalDto;
import ru.digitaluniversity.entity.*;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.NotLogInException;
import ru.digitaluniversity.exception.UnsupportedRoleException;
import ru.digitaluniversity.repository.*;
import ru.digitaluniversity.security.component.AuthenticationToken;
import ru.digitaluniversity.security.service.AuthorizationService;
import ru.digitaluniversity.services.interfaces.JournalService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<JournalDto> findByRole() {
        User user = ((AuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getUser();
        if (user != null) {
            if (authorizationService.isStudent()) {
                Student student = studentRepository.findByUser(user);
                if (student != null) {
                    return journalRepository.findByJournalStudent(student)
                            .stream().sorted(Comparator.comparingLong(o -> o.getJournalDate().getTime()))
                            .map(JournalDto::new).collect(Collectors.toList());
                }
            }
            if (authorizationService.isTeacher()) {
                Teacher teacher = teacherRepository.findByUser(user);
                if (teacher != null) {
                    return journalRepository.findByTeacher(teacher.getId())
                            .stream().sorted(Comparator.comparingLong(o -> o.getJournalDate().getTime()))
                            .map(JournalDto::new).collect(Collectors.toList());
                }
            }
        }
        throw new NotLogInException();
    }

    @Override
    public JournalDto updateRating(Integer id, String rating) throws NotFoundException, UnsupportedRoleException {
        if (authorizationService.isTeacher() || authorizationService.isAdmin()) {
            Journal journal = journalRepository.findById(id).get();
            if (journal != null) {
                Rating ratingObj = ratingRepository.findByRating(rating);
                if (ratingObj != null) {
                    journal.setJournalRating(ratingObj);
                    return new JournalDto(journalRepository.save(journal));

                } else {
                    throw new NotFoundException("Rating not found");
                }
            } else {
                throw new NotFoundException("Journal not found");
            }
        } else {
            throw new UnsupportedRoleException();
        }
    }

    @Override
    public List<JournalDto> findAll() {
        return journalRepository.findAll().stream().map(JournalDto::new).collect(Collectors.toList());
    }

    @Override
    public JournalDto findById(Integer id) throws NotFoundException {
        Journal journal = journalRepository.findById(id).get();
        if (journal != null) {
            return new JournalDto(journal);
        } else {
            throw new NotFoundException("Journal not found");
        }
    }

    @Override
    public JournalDto create(JournalDto obj) {
        return null;
    }
}
