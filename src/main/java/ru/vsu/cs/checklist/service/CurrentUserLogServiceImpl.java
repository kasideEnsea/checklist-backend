package ru.vsu.cs.checklist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.cs.checklist.dao.EventDao;
import ru.vsu.cs.checklist.entity.Event;
import ru.vsu.cs.checklist.security.CurrentUserService;

@Service
@RequiredArgsConstructor
public class CurrentUserLogServiceImpl implements CurrentUserLogService {
    private final EventDao eventDao;

    @Override
    public void logRegistered(int userId) {
        Event event = new Event();
        event.setUserId(userId);
        event.setType("registered");
        eventDao.save(event);
    }

    @Override
    public void logCreatedTask(int taskId, String name, String comment, boolean isChecklist) {
        Event event = new Event();
        event.setUserId(CurrentUserService.getUserId());
        event.setType("created");
        event.setTaskId(taskId);
        event.setTaskName(name);
        event.setComment(comment);
        event.setChecklist(isChecklist);
        eventDao.save(event);
    }

    @Override
    public void logModifiedTask(int taskId, String oldName, String newName, String comment, boolean isChecklist) {
        Event event = new Event();
        event.setUserId(CurrentUserService.getUserId());
        event.setType("modified");
        event.setTaskId(taskId);
        event.setNewValue(oldName);
        event.setNewValue(newName);
        event.setComment(comment);
        event.setChecklist(isChecklist);
        eventDao.save(event);
    }

    @Override
    public void logModifiedTaskDeadline(int taskId, String taskName, String newDate, String comment, boolean isChecklist) {
        Event event = new Event();
        event.setUserId(CurrentUserService.getUserId());
        event.setType("modified-deadline");
        event.setTaskId(taskId);
        event.setTaskName(taskName);
        event.setNewValue(newDate);
        event.setComment(comment);
        event.setChecklist(isChecklist);
        eventDao.save(event);
    }

    @Override
    public void logDeletedTask(int taskId, String name, String comment, boolean isChecklist) {
        Event event = new Event();
        event.setUserId(CurrentUserService.getUserId());
        event.setType("deleted");
        event.setTaskId(taskId);
        event.setTaskName(name);
        event.setComment(comment);
        event.setChecklist(isChecklist);
        eventDao.save(event);
    }

    @Override
    public void logDoneTask(int taskId, String name, String comment, boolean isChecklist) {
        Event event = new Event();
        event.setUserId(CurrentUserService.getUserId());
        event.setType("done");
        event.setTaskId(taskId);
        event.setTaskName(name);
        event.setComment(comment);
        event.setChecklist(isChecklist);
        eventDao.save(event);
    }

    @Override
    public void logUndoneTask(int taskId, String name, String comment, boolean isChecklist) {
        Event event = new Event();
        event.setUserId(CurrentUserService.getUserId());
        event.setType("undone");
        event.setTaskId(taskId);
        event.setTaskName(name);
        event.setComment(comment);
        event.setChecklist(isChecklist);
        eventDao.save(event);
    }


}
