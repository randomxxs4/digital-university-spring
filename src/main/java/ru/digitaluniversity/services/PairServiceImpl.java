package ru.digitaluniversity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.PairDto;
import ru.digitaluniversity.entity.Pair;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.NotImplementedMethodException;
import ru.digitaluniversity.repository.PairRepository;
import ru.digitaluniversity.services.interfaces.PairService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PairServiceImpl implements PairService {

    @Autowired
    private PairRepository pairRepository;

    @Override
    public List<PairDto> findAll() {
        return pairRepository.findAll().stream().map(PairDto::new).collect(Collectors.toList());
    }

    @Override
    public PairDto findById(Integer id) throws NotFoundException {
        Pair pair = pairRepository.findById(id).get();
        if (pair != null) {
            return new PairDto(pair);
        } else {
            throw new NotFoundException("Pair not found");
        }
    }

    @Override
    public PairDto create(PairDto obj) {
        throw new NotImplementedMethodException();
    }
}
