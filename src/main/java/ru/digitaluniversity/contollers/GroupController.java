package ru.digitaluniversity.contollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.digitaluniversity.dto.GroupDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.services.interfaces.GroupService;

import java.util.List;

/**
 * The type Group controller.
 */
@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService dataService;

    /**
     * Search for all Group entity data
     *
     * @return page
     */
    @GetMapping("/all")
    public List<GroupDto> findAll() {
        return dataService.findAll();
    }

    /**
     * Find group by id.
     *
     * @param id the id
     * @return the GroupDto obj
     * @throws ConvertException the convert exception
     */
    @GetMapping("/{id}")
    public GroupDto findGroupById(@PathVariable("id") Integer id) throws ConvertException, NotFoundException {
        return dataService.findById(id);
    }

    @PostMapping
    public GroupDto createGroup(@RequestBody GroupDto groupDto) {
        return dataService.create(groupDto);
    }
}
