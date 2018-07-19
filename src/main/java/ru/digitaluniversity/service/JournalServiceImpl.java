package ru.digitaluniversity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.digitaluniversity.converter.Converter;
import ru.digitaluniversity.dto.GroupDto;
import ru.digitaluniversity.dto.JournalDto;
import ru.digitaluniversity.entity.Group;
import ru.digitaluniversity.entity.Journal;
import ru.digitaluniversity.entity.Rating;
import ru.digitaluniversity.exception.ConvertException;
import ru.digitaluniversity.exception.ForbiddenException;
import ru.digitaluniversity.exception.NotFoundException;
import ru.digitaluniversity.exception.StreamConvertException;
import ru.digitaluniversity.repository.JournalRepository;
import ru.digitaluniversity.repository.RatingRepostitory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JournalServiceImpl implements JournalService {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private Converter<Journal, JournalDto> converter;

    @Autowired
    private RatingRepostitory ratingRepostitory;

    @Override
    public Page<JournalDto> findByRole(Optional<Integer> page, Optional<Integer> size) throws Exception {
        return null;
    }

    @Override
    public Page<JournalDto> findByRoleAndTimetable(Optional<Integer> page, Optional<Integer> size, Integer timetableId) throws Exception {
        return null;
    }

    @Override
    public JournalDto updateRating(Integer id, String rating) throws ForbiddenException, NotFoundException, ConvertException {
        Journal journal = journalRepository.findById(id).get();
        if (journal != null){
            Rating ratingObj = ratingRepostitory.findByRating(rating);
            if (ratingObj != null){
                journal.setJournalRating(ratingObj);
                Journal savedJournal = journalRepository.save(journal);
                return converter.convert(savedJournal);
            } else {
                throw new NotFoundException("Rating not found");
            }
        }else{
            throw new NotFoundException("Journal not found");
        }
    }

    @Override
    public Page<JournalDto> findAll(Optional<Integer> page, Optional<Integer> size) {
        PageRequest pageRequest = new PageRequest(page.orElse(DEFAULT_PAGE_NUMBER), size.orElse(DEFAULT_PAGE_SIZE));
        Page<Journal> allPages = journalRepository.findAll(pageRequest);
        List<JournalDto> journalDtoList = allPages.getContent().stream()
                .map(journal -> {
                    try {
                        return converter.convert(journal);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new StreamConvertException("Не удалось преобразовать Journal в Dto");
                    }
                }).collect(Collectors.toList());
        Page<JournalDto> result = new PageImpl<>(journalDtoList, pageRequest, allPages.getTotalElements());
        return result;
    }

    @Override
    public JournalDto findById(Integer id) throws ConvertException, NotFoundException {
        Journal journal = journalRepository.findById(id).get();
        if (journal != null) {
            JournalDto journalDto = converter.convert(journal);
            return journalDto;
        } else {
            throw new NotFoundException("Journal not found");
        }
    }
}
