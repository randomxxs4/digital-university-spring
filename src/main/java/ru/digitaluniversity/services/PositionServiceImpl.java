package ru.digitaluniversity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.PositionDto;
import ru.digitaluniversity.entity.Position;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.repository.PositionRepository;
import ru.digitaluniversity.services.interfaces.PositionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public PositionDto findById(Integer id) throws NotFoundException {
        Position position = positionRepository.findById(id).get();
        if (position != null) {
            return new PositionDto(position);
        } else {
            throw new NotFoundException("Position not found");
        }
    }

    @Override
    public PositionDto create(PositionDto obj) {
        return null;
    }

    @Override
    public List<PositionDto> findAll() {
        List<Position> all = positionRepository.findAll();
        List<PositionDto> result = all.stream()
                .map(PositionDto::new)
                .collect(Collectors.toList());
        return result;
    }
}
