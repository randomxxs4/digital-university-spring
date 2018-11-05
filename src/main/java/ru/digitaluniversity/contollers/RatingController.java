package ru.digitaluniversity.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.digitaluniversity.dto.RatingDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.services.interfaces.RatingService;

import java.util.List;

/**
 * The type Rating controller.
 */
@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService dataService;

    /**
     * Search for all Rating entity data
     *
     * @return list
     */
    @GetMapping
    public List<RatingDto> findAll() {
        return dataService.findAllDay();
    }

    /**
     * Find teacher by id.
     *
     * @param id the id
     * @return the RatingDto
     * @throws ConvertException the convert exception
     */
    @GetMapping("/{id}")
    public RatingDto findRatingById(@PathVariable("id") Integer id) throws ConvertException, NotFoundException {
        return dataService.findById(id);
    }
}
