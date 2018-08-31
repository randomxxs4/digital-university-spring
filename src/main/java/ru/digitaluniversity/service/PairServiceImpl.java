package ru.digitaluniversity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.PairDto;
import ru.digitaluniversity.entity.Pair;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.StreamConvertException;
import ru.digitaluniversity.repository.PairRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PairServiceImpl implements PairService {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 8;

    @Autowired
    private PairRepository pairRepository;

    @Autowired
    private Converter<Pair, PairDto> converter;

    @Override
    public Page<PairDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        PageRequest pageRequest = new PageRequest(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        Page<Pair> allPages = pairRepository.findAll(pageRequest);
        List<PairDto> journalDtoList = allPages.getContent().stream()
                .map(pair -> {
                    try {
                        return converter.convertToDto(pair);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new StreamConvertException("Could not convert Pair to Dto");
                    }
                }).collect(Collectors.toList());
        Page<PairDto> result = new PageImpl<>(journalDtoList, pageRequest, allPages.getTotalElements());
        return result;
    }

    @Override
    public PairDto findById(Integer id) throws ConvertException, NotFoundException {
        Pair pair = pairRepository.findById(id).get();
        if (pair != null) {
            PairDto pairDto = converter.convertToDto(pair);
            return pairDto;
        } else {
            throw new NotFoundException("Pair not found");
        }
    }

    @Override
    public PairDto create(PairDto obj) {
        return null;
    }
}
