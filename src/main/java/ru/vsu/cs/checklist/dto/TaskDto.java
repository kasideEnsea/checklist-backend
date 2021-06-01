package ru.vsu.cs.checklist.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
public class TaskDto {
    private int id;
    @NotEmpty
    private String description;
    private Integer parentId;
    private Integer userId;
    private boolean done;
    private Date deadline;
    private boolean deleted;
    private List<TaskDto> tasks;
    private String comment;
}
