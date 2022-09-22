package com.example.finalTrello.repositories;

import com.example.finalTrello.entities.Folders;
import com.example.finalTrello.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TasksRepository extends JpaRepository<Tasks, Long> {
    void deleteAllByFolderEquals(Folders folder);

    List<Tasks> findAllByFolder(Folders folder);



}
