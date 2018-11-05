package ru.digitaluniversity.contollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.digitaluniversity.dto.SubjectDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.services.interfaces.SubjectService;

import java.util.List;

/**
 * The type Subject controller.
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService dataService;

    @GetMapping("/all")
    public List<SubjectDto> findAll() {
        return dataService.findAll();
    }

    /**
     * Find subject by id.
     *
     * @param id the id
     * @return the SubjectDto
     * @throws ConvertException the convert exception
     */
    @GetMapping("/{id}")
    public SubjectDto findSubjectById(@PathVariable("id") Integer id) throws ConvertException, NotFoundException {
        return dataService.findById(id);
    }

    @PostMapping
    public SubjectDto createSubject(@RequestBody SubjectDto subjectDto) {
        return dataService.create(subjectDto);
    }
}
