package com.java.taskapi.service;

import com.java.taskapi.model.Task;
import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> getAllTasks(UUID userId);
    List<Task> getPendingTasks(UUID userId);
    Task addTask(Task task);
    void deleteTask(UUID taskId); // Marks as deleted
}
