package com.example.finalTrello.impl;

import com.example.finalTrello.entities.Folders;
import com.example.finalTrello.entities.TaskCategories;
import com.example.finalTrello.entities.Tasks;
import com.example.finalTrello.repositories.CommentsRepository;
import com.example.finalTrello.repositories.FoldersRepository;
import com.example.finalTrello.repositories.TaskCategoriesRepository;
import com.example.finalTrello.repositories.TasksRepository;
import com.example.finalTrello.services.FoldersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FoldersServiceImpl implements FoldersService {
    @Autowired
    private FoldersRepository foldersRepository;
    @Autowired
    private TaskCategoriesRepository taskCategoriesRepository;
    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    CommentsRepository commentsRepository;
    @Override
    public List<Folders> getFolders() {
        return foldersRepository.findAll();
    }

    @Override
    public void addFolder(String name) {
        Folders folder = new Folders();
        folder.setName(name);
        foldersRepository.save(folder);
    }

    @Override
    public void saveFolder(Folders folder) {
        foldersRepository.save(folder);
    }


    @Override
    public void deleteFolder(Long id) {
        Folders folder = foldersRepository.findById(id).orElse(null);

        if(folder!=null){
            List<Tasks> tasks =  tasksRepository.findAllByFolder(folder);
            for(Tasks task:tasks){
                commentsRepository.deleteAllByTask(task);
            }
            List<TaskCategories> categories = folder.getCategories();
            List<TaskCategories> categories2 = categories;

            categories.removeAll(categories2);

            folder.setCategories(categories);
            tasksRepository.deleteAllByFolderEquals(folder);
            foldersRepository.save(folder);


            foldersRepository.delete(folder);

        }

    }

    @Override
    public Folders getFolderById(Long id) {
        return foldersRepository.findById(id).orElse(null);
    }

    @Override
    public Folders assignCategory(Long cat_id, Long fold_id) {
        Folders folder = foldersRepository.findById(fold_id).orElse(null);
        if(folder!=null){
            TaskCategories category = taskCategoriesRepository.findById(cat_id).orElse(null);
            if(category!=null){
                List<TaskCategories> categories = folder.getCategories();
                if(categories==null) categories = new ArrayList<>();
                categories.add(category);
                folder.setCategories(categories);

            }
            return foldersRepository.save(folder);

        }

        return null;
    }

    @Override
    public Folders unassignCategory(Long cat_id, Long fold_id) {
        Folders folder = foldersRepository.findById(fold_id).orElse(null);
        if(folder!=null){
            TaskCategories category = taskCategoriesRepository.findById(cat_id).orElse(null);
            if(category!=null){
                List<TaskCategories> categories = folder.getCategories();
                if(categories==null) categories = new ArrayList<>();
                categories.remove(category);
                folder.setCategories(categories);

            }
            return foldersRepository.save(folder);

        }

        return null;
    }
}
