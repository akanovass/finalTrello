package com.example.finalTrello.impl;

import com.example.finalTrello.entities.Folders;
import com.example.finalTrello.entities.Tasks;
import com.example.finalTrello.repositories.CommentsRepository;
import com.example.finalTrello.repositories.FoldersRepository;
import com.example.finalTrello.repositories.TasksRepository;
import com.example.finalTrello.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TasksServiceImpl implements TasksService {
    @Autowired
    private FoldersRepository foldersRepository;
    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    CommentsRepository commentsRepository;

    @Override
    public Tasks addTask(String name, String description, Long folder_id) {
        Folders folder = foldersRepository.findById(folder_id).orElse(null);
        if(folder!=null){
            Tasks task = new Tasks();
            task.setTitle(name);
            task.setDescription(description);
            task.setFolder(folder);
            task.setStatus(0);

            return tasksRepository.save(task);
        }
        return null;
    }

    @Override
    public void deleteTask(Long folder_id, Long id) {
        Tasks task = tasksRepository.findById(id).orElse(null);
        if(task!=null){
            commentsRepository.deleteAllByTask(task);
            tasksRepository.deleteById(id);
        }
    }

    @Override
    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    @Override
    public Tasks getTask(Long id) {
        return tasksRepository.findById(id).orElse(null);
    }

    @Override
    public Tasks updateTask(String name, String description, Long task_id, int status, Long folder_id) {
        Tasks task = tasksRepository.findById(task_id).orElse(null);
        if(task!=null){
            task.setTitle(name);
            task.setDescription(description);
            task.setStatus(status);

            return tasksRepository.save(task);
        }
        return null;
    }

    @Override
    public List<Tasks> getAllTasksInAFolder(Long folder_id) {
        List<Tasks> tasks = new ArrayList<>();
        for(Tasks task: tasksRepository.findAll() ){
            if(task.getFolder().getId()==folder_id) tasks.add(task);
        }
        return tasks;
    }
}
