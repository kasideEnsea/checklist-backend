package ru.vsu.cs.checklist.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class EventDto {
    private int id;
    private int userId;
    private int taskId;
    private String type;
    private String comment;
    private String taskName;
    private boolean isChecklist;
    private String newValue;
    private boolean deleted;
    private ZonedDateTime created;
}
