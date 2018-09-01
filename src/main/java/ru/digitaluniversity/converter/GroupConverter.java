package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.GroupDto;
import ru.digitaluniversity.entity.Group;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class GroupConverter implements Converter<Group, GroupDto> {
    @Override
    public GroupDto convertToDto(Group obj) {
        GroupDto groupDto = new GroupDto();
        if (obj.getId() != null) {
            groupDto.setId(obj.getId().toString());
        }
        if (obj.getTitle() != null) {
            groupDto.setTitle(obj.getTitle());
        }
        return groupDto;
    }

    @Override
    public Group convertToEntity(GroupDto obj) {
        Group group = new Group();
        if (obj.getId() != null) {
            group.setId(Integer.parseInt(obj.getId()));
        }
        if (obj.getTitle() != null) {
            group.setTitle(obj.getTitle());
        }
        return group;
    }
}
