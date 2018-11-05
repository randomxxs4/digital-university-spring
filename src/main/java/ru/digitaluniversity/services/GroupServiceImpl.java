package ru.digitaluniversity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.GroupDto;
import ru.digitaluniversity.entity.Group;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.repository.GroupRepository;
import ru.digitaluniversity.services.interfaces.GroupService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<GroupDto> findAll() {
        return groupRepository.findAll().stream().map(GroupDto::new).collect(Collectors.toList());
    }

    @Override
    public GroupDto findById(Integer id) throws ConvertException, NotFoundException {
        Group group = groupRepository.findById(id).get();
        if (group != null) {
            return new GroupDto(group);
        } else {
            throw new NotFoundException("Group not found");
        }
    }

    public GroupDto create(GroupDto groupDto) {
        return null;
    }
}

