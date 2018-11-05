package ru.digitaluniversity.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.digitaluniversity.dto.PairDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.services.interfaces.PairService;

import java.util.List;
import java.util.Optional;

/**
 * The type Pair controller.
 */
@RestController
@RequestMapping("/pair")
public class PairController {

    @Autowired
    private PairService dataService;

    /**
     * Search for all Pair entity data
     *
     * @return page
     */
    @GetMapping("/all")
    public List<PairDto> findAll() {
        return dataService.findAll();
    }

    /**
     * Find pair by id.
     *
     * @param id the id
     * @return the PairDto
     * @throws ConvertException the convert exception
     */
    @GetMapping("/{id}")
    public PairDto findPairById(@PathVariable("id") Integer id) throws ConvertException, NotFoundException {
        return dataService.findById(id);
    }
}
