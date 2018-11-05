package ru.digitaluniversity.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.digitaluniversity.dto.SpecialityDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.services.interfaces.SpecialityService;

import java.util.List;

/**
 * The type Speciality controller.
 */
@RestController
@RequestMapping("/speciality")
public class SpecialityController {

    @Autowired
    private SpecialityService dataService;

    /**
     * Search for all Speciality entity data
     *
     * @return page
     */
    @GetMapping("/all")
    public List<SpecialityDto> findAll() {
        return dataService.findAll();
    }

    /**
     * Find teacher by id.
     *
     * @param id the id
     * @return the SpecialityDto
     * @throws ConvertException the convert exception
     */
    @GetMapping("/{id}")
    public SpecialityDto findSpecialityById(@PathVariable("id") Integer id) throws ConvertException, NotFoundException {
        return dataService.findById(id);
    }

    @PostMapping
    public SpecialityDto createSpeciality(@RequestBody SpecialityDto specialityDto) {
        return dataService.create(specialityDto);
    }
}
