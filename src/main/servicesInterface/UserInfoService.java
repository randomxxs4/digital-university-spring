package ru.digitaluniversity.services;

import ru.digitaluniversity.dto.BasicInfoDto;
import ru.digitaluniversity.dto.ProfileInfoDto;
import ru.digitaluniversity.dto.alternative.AlternativeStudentDto;
import ru.digitaluniversity.dto.alternative.AlternativeTeacherDto;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.UnsupportedRoleException;

public interface UserInfoService {
    ProfileInfoDto generateInfoByRole() throws NotFoundException, NotFoundException, ConvertException, UnsupportedRoleException;
}
