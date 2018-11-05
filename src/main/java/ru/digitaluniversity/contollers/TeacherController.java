package ru.digitaluniversity.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.digitaluniversity.dto.TeacherDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.services.interfaces.TeacherService;

import java.util.List;

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
     * @return page
     */
    @GetMapping("/all")
    public List<TeacherDto> findAll() {
        return dataService.findAll();
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
    public TeacherDto createTeacher(@RequestBody TeacherDto teacherDto) {
        return dataService.create(teacherDto);
    }
}
