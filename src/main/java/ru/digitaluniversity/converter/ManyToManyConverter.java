package ru.digitaluniversity.converter;

public interface ManyToManyConverter<T, S> extends Converter<T, S>  {
    S convertManyToManyLink(T obj);
}
