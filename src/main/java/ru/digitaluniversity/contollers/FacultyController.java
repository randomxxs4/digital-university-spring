package ru.digitaluniversity.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.digitaluniversity.dto.FacultyDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.services.interfaces.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    private FacultyService dataService;

    @GetMapping
    public List<FacultyDto> getAll() {
        return dataService.findAll();
    }

    @GetMapping("/{id}")
    public FacultyDto getOne(@PathVariable Integer id) throws ConvertException, NotFoundException {
        return dataService.findById(id);
    }

}
