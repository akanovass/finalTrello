package com.example.finalTrello.repositories;

import com.example.finalTrello.entities.Comments;
import com.example.finalTrello.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    void deleteAllByTask(Tasks task);

}
