package ru.digitaluniversity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.SubjectDto;
import ru.digitaluniversity.entity.Subject;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.repository.SubjectRepository;
import ru.digitaluniversity.services.interfaces.SubjectService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public SubjectDto findById(Integer id) throws NotFoundException {
        Subject subject = subjectRepository.findById(id).get();
        if (subject != null) {
            return new SubjectDto(subject);
        } else {
            throw new NotFoundException("Subject not found");
        }
    }

    @Override
    public SubjectDto create(SubjectDto obj) {
        return null;
    }

    @Override
    public List<SubjectDto> findAll() {
        List<Subject> all = subjectRepository.findAll();
        return all.stream()
                .map(SubjectDto::new)
                .collect(Collectors.toList());
    }
}
