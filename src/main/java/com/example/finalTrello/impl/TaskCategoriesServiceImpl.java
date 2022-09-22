package com.example.finalTrello.impl;

import com.example.finalTrello.entities.TaskCategories;
import com.example.finalTrello.repositories.TaskCategoriesRepository;
import com.example.finalTrello.services.TaskCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCategoriesServiceImpl implements TaskCategoriesService {
    @Autowired
    private TaskCategoriesRepository taskCategoriesRepository;

    @Override

    public TaskCategories saveTaskCategories(String name) {
        return null;
    }

    private boolean checkForDeletion = false;

    @Override
    public boolean isCheckForDeletion() {
        return checkForDeletion;
    }

    @Override
    public void deleteTaskCategories(Long id) {
        try{
            taskCategoriesRepository.deleteById(id);//orElse null?
        }catch (Exception e){
            TaskCategories taskCategory = taskCategoriesRepository.findById(id).orElse(null);
            if(taskCategory!=null){
                checkForDeletion=true;
            }
           
        }


    }


    @Override
    public TaskCategories addTaskCategories(String name) {
            TaskCategories category = new TaskCategories();
            category.setName(name);

        return taskCategoriesRepository.save(category);
    }

    @Override
    public List<TaskCategories> allTaskCategories() {
        return taskCategoriesRepository.findAll();
    }

    @Override
    public TaskCategories updateCategoryName(Long category_id, String name) {
        TaskCategories taskCategory = taskCategoriesRepository.findById(category_id).orElse(null);
        if(taskCategory!=null){
            taskCategory.setName(name);

            return taskCategoriesRepository.save(taskCategory);
        }

        return null;
    }

    @Override
    public TaskCategories findTaskCategoriesById(Long id) {
        return taskCategoriesRepository.findById(id).orElse(null);
    }
}
