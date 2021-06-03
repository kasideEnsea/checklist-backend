package ru.vsu.cs.checklist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.checklist.converter.TaskMapper;
import ru.vsu.cs.checklist.dto.TaskDto;
import ru.vsu.cs.checklist.entity.Task;
import ru.vsu.cs.checklist.exception.WebException;
import ru.vsu.cs.checklist.service.CurrentUserLogService;
import ru.vsu.cs.checklist.service.TasksService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TasksService tasksService;
    private final CurrentUserLogService logService;

    @GetMapping("/")
    public List<TaskDto> getMyTasks(@RequestParam(required = false) Integer userId) {
        if (userId == null) {
            return tasksService.myTasks2forest();
        } else {
            return tasksService.tasks2forest(userId);
        }
    }

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable int id) {
        Task task = tasksService.getTaskById(id)
                .orElseThrow(() -> new WebException("Task not found", HttpStatus.NOT_FOUND));
        return TaskMapper.INSTANCE.toDto(task);
    }

    @PostMapping("/")
    public TaskDto addNewMyTask(@RequestBody TaskDto taskDto) {
        Task task = TaskMapper.INSTANCE.toEntity(taskDto);
        tasksService.addTaskForMe(task);
        TaskDto res = TaskMapper.INSTANCE.toDto(task);
        boolean isChecklist = taskDto.getParentId() == null;
        logService.logCreatedTask(task.getId(), task.getDescription(), taskDto.getComment(), isChecklist);
        return res;
    }

    @PutMapping("/{id}")
    public TaskDto editMyTask(@PathVariable int id, @RequestBody TaskDto taskDto) {
        if (id != taskDto.getId()) {
            throw new WebException("Wrong id", HttpStatus.BAD_REQUEST);
        }
        TaskDto oldTask = getTaskById(id);
        Task task = TaskMapper.INSTANCE.toEntity(taskDto);
        tasksService.editMyTask(task);
        TaskDto res = TaskMapper.INSTANCE.toDto(task);
        logChanges(taskDto, oldTask);
        return res;
    }

    private void logChanges(TaskDto taskDto, TaskDto oldTask) {

        boolean isChecklist = taskDto.getParentId() == null;
        if (!oldTask.getDescription().equals(taskDto.getDescription())) {
            logService.logModifiedTask(taskDto.getId(), oldTask.getDescription(),
                    taskDto.getDescription(), taskDto.getComment(), isChecklist);
        }
        if (oldTask.getDeadline()!=null && taskDto.getDeadline()!= null && !Objects.toString(oldTask.getDeadline().toInstant())
                .equals(Objects.toString(taskDto.getDeadline().toInstant()))) {
            String newDate = taskDto.getDeadline() != null ? taskDto.getDeadline().toInstant().toString() : null;
            logService.logModifiedTaskDeadline(taskDto.getId(), taskDto.getDescription(),
                    newDate, taskDto.getComment(), isChecklist);
        }
        if (!oldTask.isDone() && taskDto.isDone()) {
            if (Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()).after(taskDto.getDeadline())){
                taskDto.setRate(1);
            }
            logService.logDoneTask(taskDto.getId(), taskDto.getDescription(), taskDto.getComment(), isChecklist);
        }
        if (oldTask.isDone() && !taskDto.isDone()) {
            logService.logUndoneTask(taskDto.getId(), taskDto.getDescription(), taskDto.getComment(), isChecklist);
        }
        if (!oldTask.isDeleted() && taskDto.isDeleted()) {
            logService.logDeletedTask(taskDto.getId(), taskDto.getDescription(), taskDto.getComment(), isChecklist);
        }
    }
}
