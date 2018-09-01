package ru.digitaluniversity.converter;

import org.springframework.stereotype.Service;
import ru.digitaluniversity.dto.PairDto;
import ru.digitaluniversity.entity.Pair;
import ru.digitaluniversity.exception.ConvertException;

@Service
public class PairConverter implements Converter<Pair, PairDto> {
    @Override
    public PairDto convertToDto(Pair obj) {
        PairDto pairDto = new PairDto();
        if (obj.getId() != null) {
            pairDto.setId(obj.getId().toString());
        }
        if (obj.getNumber() != null) {
            pairDto.setNumber(obj.getNumber().toString());
        }
        return pairDto;
    }

    @Override
    public Pair convertToEntity(PairDto obj) {
        Pair pair = new Pair();
        if (obj.getId() != null) {
            pair.setId(Integer.parseInt(obj.getId()));
        }
        if (obj.getNumber() != null) {
            pair.setNumber(Integer.parseInt(obj.getNumber()));
        }
        return pair;
    }
}
