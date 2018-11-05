package ru.digitaluniversity.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.digitaluniversity.dto.StudentDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.services.interfaces.StudentService;

import java.util.List;

/**
 * The type Student controller.
 */
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService dataService;

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
    public StudentDto createStudent(@RequestBody StudentDto studentDto) {
        return dataService.create(studentDto);
    }
}
