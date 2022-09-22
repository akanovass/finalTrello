package com.example.finalTrello.repositories;

import com.example.finalTrello.entities.TaskCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TaskCategoriesRepository extends JpaRepository<TaskCategories, Long> {


}
