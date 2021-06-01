package ru.vsu.cs.checklist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.vsu.cs.checklist.converter.TaskMapper;
import ru.vsu.cs.checklist.dao.TaskDao;
import ru.vsu.cs.checklist.dto.TaskDto;
import ru.vsu.cs.checklist.entity.Task;
import ru.vsu.cs.checklist.exception.WebException;
import ru.vsu.cs.checklist.security.CurrentUserService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final TaskDao taskDao;

    public List<TaskDto> myTasks2forest() {
        return tasks2forest(CurrentUserService.getUserId());
    }

    public List<TaskDto> tasks2forest(int userId) {
        List<Task> data = taskDao.findAllByUserIdAndDeletedOrderByDeadline(userId, false);
        return data2tree(data, null);
    }

    private List<TaskDto> data2tree(List<Task> data, Integer parentId) {
        List<TaskDto> tree = data.stream()
                .filter(task -> Objects.equals(task.getParentId(), parentId))
                .map(TaskMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
        tree.forEach(taskDto -> taskDto.setTasks(data2tree(data, taskDto.getId())));
        return tree;
    }

    public Optional<Task> getTaskById(int id) {
        return taskDao.findById(id);
    }

    public void addTaskForMe(Task task) {
        task.setId(null);
        task.setUserId(CurrentUserService.getUserId());
        taskDao.save(task);
    }

    public void editMyTask(Task task) {
        if (task.getId() == null) {
            throw new WebException("Task id not specified", HttpStatus.BAD_REQUEST);
        }
        if (task.getUserId() != CurrentUserService.getUserId()) {
            throw new WebException("Task's owner cannot be changed", HttpStatus.BAD_REQUEST);
        }
        if (!taskDao.existsByIdAndUserId(task.getId(), task.getUserId())) {
            throw new WebException("Task doesn't exist", HttpStatus.NOT_FOUND);
        }
        taskDao.save(task);
    }

    public void deleteMyTask(int id) {
        Task task = taskDao.findByIdAndUserId(id, CurrentUserService.getUserId())
                .orElseThrow(() -> new WebException("Task not found", HttpStatus.NOT_FOUND));
        taskDao.delete(task);
    }
}
