package ru.digitaluniversity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.converter.ManyToManyConverter;
import ru.digitaluniversity.dto.JournalDto;
import ru.digitaluniversity.dto.PositionDto;
import ru.digitaluniversity.entity.Journal;
import ru.digitaluniversity.entity.Position;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.StreamConvertException;
import ru.digitaluniversity.repository.PositionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ManyToManyConverter<Position, PositionDto> converter;

    @Override
    public Page<PositionDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        PageRequest pageRequest = PageRequest.of(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        Page<Position> allPages = positionRepository.findAll(pageRequest);
        List<PositionDto> positionDtoList = allPages.getContent().stream()
                .map(position -> {
                    try {
                        return converter.convertManyToManyLink(position);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new StreamConvertException("Could not convert Position to Dto");
                    }
                }).collect(Collectors.toList());
        Page<PositionDto> result = new PageImpl<>(positionDtoList, pageRequest, allPages.getTotalElements());
        return result;
    }

    @Override
    public PositionDto findById(Integer id) throws NotFoundException {
        Position position = positionRepository.findById(id).get();
        if (position != null) {
            PositionDto positionDto = converter.convertManyToManyLink(position);
            return positionDto;
        } else {
            throw new NotFoundException("Position not found");
        }
    }

    @Override
    public PositionDto create(PositionDto obj) {
        return converter.convertManyToManyLink(positionRepository.save(converter.convertToEntity(obj)));
    }

    @Override
    public List<PositionDto> findAll() {
        List<Position> all = positionRepository.findAll();
        List<PositionDto> result = all.stream()
                .map(position ->
                        converter.convertManyToManyLink(position))
                .collect(Collectors.toList());
        return result;
    }
}
