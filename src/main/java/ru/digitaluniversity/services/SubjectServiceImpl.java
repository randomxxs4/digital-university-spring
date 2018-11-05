package ru.digitaluniversity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.ManyToManyConverter;
import ru.digitaluniversity.dto.SubjectDto;
import ru.digitaluniversity.entity.Subject;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.repository.SubjectRepository;
import ru.digitaluniversity.services.interfaces.SubjectService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ManyToManyConverter<Subject, SubjectDto> converter;

    @Override
    public Page<SubjectDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        PageRequest pageRequest = PageRequest.of(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        Page<Subject> allPages = subjectRepository.findAll(pageRequest);
        List<SubjectDto> subjectDtoList = allPages.getContent().stream()
                .map(subject -> converter.convertManyToManyLink(subject))
                .collect(Collectors.toList());
        Page<SubjectDto> result = new PageImpl<>(subjectDtoList, pageRequest, allPages.getTotalElements());
        return result;
    }

    @Override
    public SubjectDto findById(Integer id) throws NotFoundException {
        Subject subject = subjectRepository.findById(id).get();
        if (subject != null) {
            SubjectDto subjectDto = converter.convertManyToManyLink(subject);
            return subjectDto;
        } else {
            throw new NotFoundException("Subject not found");
        }
    }

    @Override
    public SubjectDto create(SubjectDto obj) {
        return converter.convertManyToManyLink(subjectRepository.save(converter.convertToEntity(obj)));
    }

    @Override
    public List<SubjectDto> findAll() {
        List<Subject> all = subjectRepository.findAll();
        return all.stream()
                .map(subject -> converter.convertManyToManyLink(subject))
                .collect(Collectors.toList());
    }
}
