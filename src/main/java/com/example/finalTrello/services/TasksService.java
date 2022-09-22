package com.example.finalTrello.services;

import com.example.finalTrello.entities.Tasks;

import java.util.List;

public interface TasksService {
    Tasks addTask(String name, String description, Long folder_id);
    void deleteTask(Long folder_id,Long id);
    List<Tasks> getAllTasks();
    Tasks getTask(Long id);
    Tasks updateTask(String name, String description, Long task_id, int status, Long folder_id);
    List<Tasks> getAllTasksInAFolder(Long folder_id);
}
