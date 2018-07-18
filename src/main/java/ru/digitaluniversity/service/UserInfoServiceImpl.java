package ru.digitaluniversity.service;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.BasicInfoDto;
import ru.digitaluniversity.dto.alternative.AlternativeStudentDto;
import ru.digitaluniversity.dto.alternative.AlternativeTeacherDto;
import ru.digitaluniversity.exception.NotFoundException;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public BasicInfoDto generateInfoByRole() throws NotFoundException {
        return null;
    }

    @Override
    public AlternativeStudentDto generateStudentInfo(Integer id) throws Exception {
        return null;
    }

    @Override
    public AlternativeTeacherDto generateTeacherInfo(Integer id) throws Exception {
        return null;
    }
}
