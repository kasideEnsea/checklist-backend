package ru.vsu.cs.checklist.service;

public interface CurrentUserLogService {
    void logRegistered(int userId);

    void logCreatedTask(int taskId, String name, String comment, boolean isChecklist);

    void logModifiedTask(int taskId, String oldName, String newName, String comment, boolean isChecklist);

    void logModifiedTaskDeadline(int taskId, String oldDate, String newDate, String comment, boolean isChecklist);

    void logDeletedTask(int taskId, String name, String comment, boolean isChecklist);

    void logDoneTask(int taskId, String name, String comment, boolean isChecklist);

    void logUndoneTask(int taskId, String name, String comment, boolean isChecklist);
}
