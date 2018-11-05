package ru.digitaluniversity.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.digitaluniversity.dto.TeacherDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.services.interfaces.TeacherService;

import java.util.Optional;

/**
 * The type Teacher controller.
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService dataService;

    /**
     * Search for all Teacher entity data.
     *
     * @param page page number
     * @param size page size
     * @return page
     */
    @GetMapping("/all")
    public Page<TeacherDto> findAll(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size
    ) {
        return dataService.findAll(page, size);
    }

    /**
     * Find teacher by id.
     *
     * @param id the id
     * @return the TeacherDto
     * @throws ConvertException the convert exception
     */
    @GetMapping("/{id}")
    public TeacherDto findTeacherById(@PathVariable("id") Integer id) throws ConvertException, NotFoundException {
        return dataService.findById(id);
    }

    @PostMapping
    public TeacherDto createTeacher(@RequestBody TeacherDto teacherDto){
        return dataService.create(teacherDto);
    }
}
