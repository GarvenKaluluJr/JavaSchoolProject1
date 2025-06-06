package com.java.taskapi.service.impl;

import com.java.taskapi.model.Task;
import com.java.taskapi.repository.TaskRepository;
import com.java.taskapi.service.TaskService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Profile({"postgres"})
public class TaskServiceDbImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceDbImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks(UUID userId) {
        return taskRepository.findByUserIdAndDeletedFalse(userId);
    }

    @Override
    public List<Task> getPendingTasks(UUID userId) {
        return taskRepository.findByUserIdAndCompletedFalseAndDeletedFalse(userId);
    }

    @Override
    public Task addTask(Task task) {
        task.setDeleted(false);
        task.setCompleted(false);
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.findById(taskId).ifPresent(task -> {
            task.setDeleted(true);
            taskRepository.save(task);
        });
    }
}
