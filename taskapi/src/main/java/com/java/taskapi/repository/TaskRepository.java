package com.java.taskapi.repository;

import java.time.LocalDateTime;
import com.java.taskapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByUserIdAndDeletedFalse(UUID userId);
    List<Task> findByUserIdAndCompletedFalseAndDeletedFalse(UUID userId);
    List<Task> findByCompletedFalseAndDeletedFalseAndDueDateBefore(LocalDateTime dateTime);
}