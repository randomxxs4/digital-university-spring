package ru.digitaluniversity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.RatingDto;
import ru.digitaluniversity.entity.Rating;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.NotImplementedMethodException;
import ru.digitaluniversity.repository.RatingRepository;
import ru.digitaluniversity.services.interfaces.RatingService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public RatingDto findById(Integer id) throws NotFoundException {
        Rating rating = ratingRepository.findById(id).get();
        if (rating != null) {
            return new RatingDto(rating);
        } else {
            throw new NotFoundException("Rating not found");
        }
    }

    @Override
    public RatingDto create(RatingDto obj) {
        throw new NotImplementedMethodException();
    }

    @Override
    public List<RatingDto> findAll(){
        return ratingRepository.findAll().stream()
                .map(RatingDto::new)
                .collect(Collectors.toList());
    }
}
