package ru.vsu.cs.checklist.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.checklist.dto.TaskDto;
import ru.vsu.cs.checklist.entity.Task;

import java.util.List;

@Mapper
public interface TaskMapper { //NOSONAR
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto toDto(Task entity);

    Task toEntity(TaskDto dto);

    List<TaskDto> toDtoList(List<Task> entities);

    List<Task> toEntityList(List<TaskDto> dtos);
}

