package ru.digitaluniversity.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.digitaluniversity.dto.JournalDto;
import ru.digitaluniversity.dto.RatingRequestData;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.ForbiddenException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.UnsupportedRoleException;
import ru.digitaluniversity.security.dto.Message;
import ru.digitaluniversity.services.interfaces.JournalService;

import java.util.List;

/**
 * The type Journal controller.
 */
@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService dataService;

    @GetMapping("/all")
    public List<JournalDto> findAll() {
        return dataService.findAll();
    }

    /**
     * Find journal by id.
     *
     * @param id the id
     * @return the JournalDto
     * @throws ConvertException the convert exception
     */
    @GetMapping("/{id}")
    public JournalDto findJournalById(@PathVariable("id") Integer id) throws ConvertException, NotFoundException {
        return dataService.findById(id);
    }

    /**
     * Find journal by user role.
     *
     * @return the page
     * @throws NotFoundException the not found exception
     */
    @GetMapping
    public List<JournalDto> findJournalsByRole() throws Exception {
        return dataService.findByRole();
    }

    /**
     * Update rating in journal.
     *
     * @param ratingRequestData the rating request data
     * @return the journal dto
     * @throws Exception the exception
     */
    @PostMapping
    public JournalDto updateRating(@RequestBody RatingRequestData ratingRequestData) throws Exception {
        return dataService.updateRating(Integer.parseInt(ratingRequestData.getId()), ratingRequestData.getRating());
    }

    /**
     * Not found exception handler.
     *
     * @return the message
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Message notFound() {
        return new Message("Journal not found");
    }

    /**
     * Forbidden exception handler.
     *
     * @return the message
     */
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public Message noAuthority() {
        return new Message("Can't perform this action");
    }

    /**
     * UnsupportedRole exception handler.
     *
     * @return the message
     */
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnsupportedRoleException.class)
    public Message unsuportedROle() {
        return new Message("Can't perform this action");
    }
}
