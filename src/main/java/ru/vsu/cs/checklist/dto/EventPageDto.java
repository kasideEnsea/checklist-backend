package ru.vsu.cs.checklist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EventPageDto {
    int page;
    int totalPages;
    List<EventDto> data;
}
