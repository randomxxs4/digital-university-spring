package ru.digitaluniversity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.FacultyDto;
import ru.digitaluniversity.entity.Faculty;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.repository.FacultyRepository;
import ru.digitaluniversity.services.interfaces.FacultyService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public List<FacultyDto> findAll() {
        return facultyRepository.findAll().stream().map(FacultyDto::new).collect(Collectors.toList());
    }

    @Override
    public FacultyDto findById(Integer id) throws NotFoundException {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            return new FacultyDto(faculty.get());
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public FacultyDto create(FacultyDto obj) {
        return null;
    }
}
