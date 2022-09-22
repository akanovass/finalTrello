package com.example.finalTrello.impl;

import com.example.finalTrello.entities.Comments;
import com.example.finalTrello.entities.Tasks;
import com.example.finalTrello.repositories.CommentsRepository;
import com.example.finalTrello.repositories.TasksRepository;
import com.example.finalTrello.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    CommentsRepository commentsRepository;
    @Override
    public Comments addComment(Long folder_id, Long task_id, String commenttext) {
        Tasks task = tasksRepository.findById(task_id).orElse(null);
        if(task!=null){
            Comments comment = new Comments();
            comment.setComment(commenttext);
            comment.setTask(task);
           return commentsRepository.save(comment);

        }

        return null;
    }

    @Override
    public List<Comments> getAllComments() {
        return null;
    }

    @Override
    public List<Comments> getAllCommentsInATask(Tasks task) {
        List<Comments> comments = new ArrayList<>();
        for(Comments com:commentsRepository.findAll() ){
            if(com.getTask().equals(task)) comments.add(com);
        }

        return comments;
    }
}
