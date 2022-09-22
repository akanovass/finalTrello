package com.example.finalTrello.repositories;

import com.example.finalTrello.entities.Folders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FoldersRepository extends JpaRepository<Folders, Long> {


}
