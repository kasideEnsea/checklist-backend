package ru.vsu.cs.checklist.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.checklist.dto.EventDto;
import ru.vsu.cs.checklist.entity.Event;

import java.util.List;

@Mapper
public interface EventMapper { //NOSONAR
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    EventDto toDto(Event entity);

    Event toEntity(EventDto dto);

    List<EventDto> toDtoList(List<Event> entities);

    List<Event> toEntityList(List<EventDto> dtos);
}

