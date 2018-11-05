package ru.digitaluniversity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.DayDto;
import ru.digitaluniversity.entity.Day;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.NotImplementedMethodException;
import ru.digitaluniversity.repository.DayRepository;
import ru.digitaluniversity.services.interfaces.DayService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DayServiceImpl implements DayService {
    @Autowired
    private DayRepository dayRepository;

    @Override
    public List<DayDto> findAll() {
        return dayRepository.findAll().stream().map(DayDto::new).collect(Collectors.toList());
    }

    @Override
    public DayDto findById(Integer id) throws NotFoundException {
        Day day = dayRepository.findById(id).get();
        if (day != null) {
            return new DayDto(day);
        } else {
            throw new NotFoundException("Day not found");
        }
    }

    @Override
    public DayDto create(DayDto obj) {
        throw new NotImplementedMethodException("Метод не поддерживается!");
    }
}
