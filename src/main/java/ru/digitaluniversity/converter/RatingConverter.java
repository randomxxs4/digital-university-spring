package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.RatingDto;
import ru.digitaluniversity.entity.Rating;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class RatingConverter implements Converter<Rating, RatingDto> {
    @Override
    public RatingDto convert(Rating obj) throws ConvertException, ConvertException {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setId(obj.getId().toString());
        ratingDto.setRating(obj.getRating());
        return ratingDto;
    }
}
