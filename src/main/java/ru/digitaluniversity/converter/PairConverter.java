package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.PairDto;
import ru.digitaluniversity.entity.Pair;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class PairConverter implements Converter<Pair, PairDto> {
    @Override
    public PairDto convert(Pair obj) throws ConvertException, ConvertException {
        PairDto pairDto = new PairDto();
        pairDto.setId(obj.getId().toString());
        pairDto.setNumber(obj.getNumber().toString());
        return pairDto;
    }
}
