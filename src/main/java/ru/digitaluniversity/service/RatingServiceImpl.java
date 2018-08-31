package ru.digitaluniversity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.RatingDto;
import ru.digitaluniversity.entity.Rating;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.StreamConvertException;
import ru.digitaluniversity.repository.RatingRepostitory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private RatingRepostitory ratingRepostitory;

    @Autowired
    private Converter<Rating, RatingDto> converter;

    @Override
    public Page<RatingDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        PageRequest pageRequest = new PageRequest(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        Page<Rating> allPages = ratingRepostitory.findAll(pageRequest);
        List<RatingDto> ratingDtoList = allPages.getContent().stream()
                .map(rating -> {
                    try {
                        return converter.convertToDto(rating);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new StreamConvertException("Could not convert Rating to Dto");
                    }
                }).collect(Collectors.toList());
        Page<RatingDto> result = new PageImpl<>(ratingDtoList, pageRequest, allPages.getTotalElements());
        return result;
    }

    @Override
    public RatingDto findById(Integer id) throws ConvertException, NotFoundException {
        Rating rating = ratingRepostitory.findById(id).get();
        if (rating != null) {
            RatingDto ratingDto = converter.convertToDto(rating);
            return ratingDto;
        } else {
            throw new NotFoundException("Rating not found");
        }
    }

    @Override
    public RatingDto create(RatingDto obj) {
        return null;
    }
}
