package com.example.finalTrello.services;

import com.example.finalTrello.entities.Folders;

import java.util.List;

public interface FoldersService {
    void addFolder(String name);
   void saveFolder(Folders folder);
    List<Folders> getFolders();
    void deleteFolder(Long id);
    Folders getFolderById(Long id);
    Folders assignCategory(Long cat_id, Long fold_id);
    Folders unassignCategory(Long cat_id, Long fold_id);

}
