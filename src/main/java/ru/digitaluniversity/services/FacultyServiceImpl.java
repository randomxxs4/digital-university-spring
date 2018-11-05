package ru.digitaluniversity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import ru.digitaluniversity.dto.FacultyDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.repository.FacultyRepository;
import ru.digitaluniversity.services.interfaces.FacultyService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public List<FacultyDto> findAll() {
        return facultyRepository.findAll().stream().map(FacultyDto::new).collect(Collectors.toList());
    }

    @Override
    public Page<FacultyDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        return null;
    }

    @Override
    public FacultyDto findById(Integer id) throws ConvertException, NotFoundException {
        return null;
    }

    @Override
    public FacultyDto create(FacultyDto obj) {
        return null;
    }
}
