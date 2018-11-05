package ru.digitaluniversity.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.digitaluniversity.dto.StudentDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.services.interfaces.StudentService;

import java.util.List;
import java.util.Optional;

/**
 * The type Student controller.
 */
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService dataService;

    /**
     * Search for all Student entity data.
     *
     * @param page page number
     * @param size page size
     * @return page
     */
    @GetMapping("/page/all")
    public Page<StudentDto> findAll(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size
    ) {
        return dataService.findAll(page, size);
    }

    @GetMapping("/all")
    public List<StudentDto> findAll() {
        return dataService.findAll();
    }

    /**
     * Find student by id.
     *
     * @param id the id
     * @return the StudentDto
     * @throws ConvertException the convert exception
     */
    @GetMapping("/{id}")
    public StudentDto findStudentById(@PathVariable("id") Integer id) throws ConvertException, NotFoundException {
        return dataService.findById(id);
    }

    @PostMapping
    public StudentDto createStudent(@RequestBody StudentDto studentDto){
        return dataService.create(studentDto);
    }
}
