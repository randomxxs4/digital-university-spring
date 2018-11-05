package ru.digitaluniversity.services.interfaces;

import ru.digitaluniversity.dto.ProfileInfoDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.UnsupportedRoleException;

public interface UserInfoService {
    ProfileInfoDto generateInfoByRole() throws NotFoundException, NotFoundException, ConvertException, UnsupportedRoleException;
}
