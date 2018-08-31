package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.RatingDto;
import ru.digitaluniversity.entity.Rating;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class RatingConverter implements Converter<Rating, RatingDto> {
    @Override
    public RatingDto convertToDto(Rating obj) {
        RatingDto ratingDto = new RatingDto();
        if (obj.getId() != null) {
            ratingDto.setId(obj.getId().toString());
        }
        if (obj.getRating() != null) {
            ratingDto.setRating(obj.getRating());
        }
        return ratingDto;
    }

    @Override
    public Rating convertToEntity(RatingDto obj) {
        Rating rating = new Rating();
        if (obj.getId() != null) {
            rating.setId(Integer.parseInt(obj.getId()));
        }
        if (obj.getRating() != null) {
            rating.setRating(obj.getRating());
        }
        return rating;
    }
}
