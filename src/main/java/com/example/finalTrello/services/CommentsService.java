package com.example.finalTrello.services;

import com.example.finalTrello.entities.Comments;
import com.example.finalTrello.entities.Tasks;

import java.util.List;

public interface CommentsService {
    Comments addComment(Long folder_id, Long task_id, String comment);
    List<Comments> getAllComments();
    List<Comments> getAllCommentsInATask(Tasks task);
}
