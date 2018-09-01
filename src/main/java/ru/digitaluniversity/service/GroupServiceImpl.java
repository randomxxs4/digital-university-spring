package ru.digitaluniversity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.GroupDto;
import ru.digitaluniversity.entity.Group;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.StreamConvertException;
import ru.digitaluniversity.repository.GroupRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private Converter<Group, GroupDto> converter;

    @Override
    public Page<GroupDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        PageRequest pageRequest = new PageRequest(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        Page<Group> allPages = groupRepository.findAll(pageRequest);
        List<GroupDto> dayDtoList = allPages.getContent().stream()
                .map(group -> {
                    try {
                        return converter.convertToDto(group);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new StreamConvertException("Could not convert Group to Dto");
                    }
                }).collect(Collectors.toList());
        Page<GroupDto> result = new PageImpl<>(dayDtoList, pageRequest, allPages.getTotalElements());
        return result;
    }

    @Override
    public GroupDto findById(Integer id) throws ConvertException, NotFoundException {
        Group group = groupRepository.findById(id).get();
        if (group != null) {
            GroupDto groupDto = converter.convertToDto(group);
            return groupDto;
        } else {
            throw new NotFoundException("Group not found");
        }
    }

    public GroupDto create(GroupDto groupDto) {
        return converter.convertToDto(groupRepository.save(converter.convertToEntity(groupDto)));
    }
}

