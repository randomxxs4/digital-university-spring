package ru.digitaluniversity.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.digitaluniversity.dto.PositionDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.services.interfaces.PositionService;

import java.util.List;

/**
 * The type Position controller.
 */
@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionService dataService;

    /**
     * Search for all Position entity data
     *
     * @return page
     */
    @GetMapping("/all")
    public List<PositionDto> findAll() {
        return dataService.findAll();
    }

    /**
     * Find pair by id.
     *
     * @param id the id
     * @return the PositionDto
     */
    @GetMapping("/{id}")
    public PositionDto findPairById(@PathVariable("id") Integer id) throws ConvertException, NotFoundException {
        return dataService.findById(id);
    }

    @PostMapping
    public PositionDto createPosition(@RequestBody PositionDto positionDto) {
        return dataService.create(positionDto);
    }
}
