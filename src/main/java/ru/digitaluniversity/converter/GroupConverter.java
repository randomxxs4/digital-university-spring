package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.GroupDto;
import ru.digitaluniversity.entity.Group;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class GroupConverter implements Converter<Group, GroupDto> {
    @Override
    public GroupDto convert(Group obj) throws ConvertException, ConvertException {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(obj.getId().toString());
        groupDto.setTitle(obj.getTitle());
        return groupDto;
    }
}
