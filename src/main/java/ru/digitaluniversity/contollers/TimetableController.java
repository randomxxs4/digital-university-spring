package ru.digitaluniversity.contollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.digitaluniversity.dto.TimetableDto;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.NotLogInException;
import ru.digitaluniversity.exception.UnsupportedRoleException;
import ru.digitaluniversity.security.dto.Message;
import ru.digitaluniversity.services.interfaces.TimetableService;

import java.util.List;

/**
 * The type Timetable controller.
 */
@RestController
@RequestMapping("/timetable")
public class TimetableController {

    @Autowired
    private TimetableService dataService;

    @GetMapping("/all")
    public List<TimetableDto> findAll() {
        return dataService.findAll();
    }

    /**
     * Find timetable by role.
     *
     * @return the list
     */
    @GetMapping
    public List<TimetableDto> findByRole() {
        return dataService.findTimetableByRole();
    }

    /**
     * Find timetable by id.
     *
     * @param id the id
     * @return the timetable dto
     * @throws Exception the exception
     */
    @GetMapping("/{id}")
    public TimetableDto findTimetableById(@PathVariable("id") Integer id) throws Exception {
        return dataService.findById(id);
    }

    @PostMapping
    public TimetableDto createTimetable(@RequestBody TimetableDto timetableDto) {
        return dataService.create(timetableDto);
    }

    /**
     * Not found exception handler.
     *
     * @return the message
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Message notFound() {
        return new Message("Timetable not found");
    }

    /**
     * Not login exception handler.
     *
     * @return the message
     */
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotLogInException.class)
    public Message notLogin() {
        return new Message("Timetable not found");
    }

    /**
     * UnsupportedRole exception handler.
     *
     * @return the message
     */
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnsupportedRoleException.class)
    public Message notSupportRole() {
        return new Message("UnsupportedRoleException");
    }
}
