package ru.digitaluniversity.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.digitaluniversity.dto.DayDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.services.interfaces.DayService;

import java.util.List;

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
     * @return page
     */
    @GetMapping("/all")
    public List<DayDto> findAll() {
        return dataService.findAll();
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
