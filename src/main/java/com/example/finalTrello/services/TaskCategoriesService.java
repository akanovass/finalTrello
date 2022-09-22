package com.example.finalTrello.services;

import com.example.finalTrello.entities.TaskCategories;

import java.util.List;

public interface TaskCategoriesService {
    TaskCategories saveTaskCategories(String name);
    void deleteTaskCategories(Long id);
    TaskCategories addTaskCategories(String name);
    List<TaskCategories> allTaskCategories();
    TaskCategories updateCategoryName(Long category_id, String name);
    TaskCategories findTaskCategoriesById(Long id);

    boolean isCheckForDeletion();
}
