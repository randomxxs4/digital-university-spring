package ru.digitaluniversity.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.digitaluniversity.dto.DayDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.services.interfaces.DayService;

import java.util.List;
import java.util.Optional;

/**
 * The type Day controller.
 */
@RestController
@RequestMapping("/day")
public class DayController {

    @Autowired
    private DayService dataService;

    /**
     * Search for all Day entity data
     *
     * @param page page number
     * @param size page size
     * @return page
     */
    @GetMapping("/all")
    public List<DayDto> findAll(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        return dataService.findAll(page, size).getContent();
    }

    /**
     * Find day by id
     *
     * @param id the id
     * @return the DayDto obj
     * @throws ConvertException the convert exception
     */
    @GetMapping("/{id}")
    public DayDto findDayById(@PathVariable("id") Integer id) throws ConvertException, NotFoundException {
        return dataService.findById(id);
    }
}
