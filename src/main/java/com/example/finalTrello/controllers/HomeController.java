package com.example.finalTrello.controllers;

import com.example.finalTrello.entities.Folders;
import com.example.finalTrello.entities.TaskCategories;
import com.example.finalTrello.entities.Tasks;
import com.example.finalTrello.services.CommentsService;
import com.example.finalTrello.services.FoldersService;
import com.example.finalTrello.services.TaskCategoriesService;
import com.example.finalTrello.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CommentsService commentsService;

    @Autowired
    private FoldersService foldersService;

    @Autowired
    private TaskCategoriesService taskCategoriesService;

    @Autowired
    private TasksService tasksService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("folders", foldersService.getFolders());
        return "index";
    }

    @PostMapping("/addfolder")
    public String addfolder(
            @RequestParam(name = "name") String name){
        foldersService.addFolder(name);
        return "redirect:/";
    }

    @PostMapping("/addcategory")
    public String addcategory(
            @RequestParam(name = "name") String name){
        taskCategoriesService.addTaskCategories(name);
        return "redirect:/categories";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable(name = "id") Long id, Model model){
        Folders folder = foldersService.getFolderById(id);
        if(folder!=null){
            List<TaskCategories> categories = folder.getCategories();
            List<TaskCategories> allcategories =taskCategoriesService.allTaskCategories() ;
            allcategories.removeAll(categories);
            if(categories==null) categories = new ArrayList<>();
            model.addAttribute("folderCategories", categories);
                model.addAttribute("folder", folder);
                model.addAttribute("allCategories", allcategories);
                model.addAttribute("tasks", tasksService.getAllTasksInAFolder(id));
        }
        return "details";
    }

    @PostMapping("/assigncategory")
    public String assigncategory(
            @RequestParam(name = "category_id") Long category_id,
            @RequestParam( name = "folder_id") Long folder_id){
        foldersService.assignCategory(category_id, folder_id);
        return "redirect:/details/"+folder_id;
    }

    @PostMapping("/unassigncategory")
    public String unassigncategory(
            @RequestParam(name = "categories_id") Long category_id,
            @RequestParam( name = "folders_id") Long folder_id){
        foldersService.unassignCategory(category_id, folder_id);
        return "redirect:/details/"+folder_id;
    }

    @GetMapping("/categories")
    public String categories(Model model){
        model.addAttribute("allCategories", taskCategoriesService.allTaskCategories());
        return "categoriespage";
    }

    @GetMapping("/deletecategory/{id}")
    public String deletecategory(@PathVariable(name = "id") Long id, Model model){
        taskCategoriesService.deleteTaskCategories(id);
System.out.println(taskCategoriesService.isCheckForDeletion() + " QARAAAAAA");
        String redirect = "redirect:/categories";
        if(taskCategoriesService.isCheckForDeletion()){

            redirect = "redirect:/categorydetails/" + id;
            model.addAttribute("delete", true);
        }

        return  redirect;
    }

    @PostMapping("/updatecategoryname")
    public String updatecategoryname(@RequestParam(name = "category_id") Long id,
                                     @RequestParam(name = "name") String name){
        taskCategoriesService.updateCategoryName(id, name);
        return "redirect:/categories";
    }

    @PostMapping("/addtask")
    public String addtask(@RequestParam(name = "name") String name,
                          @RequestParam(name = "description") String description,
                          @RequestParam(name = "folder_id") Long folder_id){
        tasksService.addTask(name,description,folder_id);
        return "redirect:/details/" + folder_id;
    }

    @GetMapping("/taskdetails/{folderid}/{id}")
    public String taskdetails(@PathVariable(name = "folderid") Long folder_id,
                              @PathVariable(name = "id") Long id,
                              Model model){
        Tasks task = tasksService.getTask(id);
        if(task!=null){
            model.addAttribute("task", task);
            model.addAttribute("folder_id", folder_id);
            model.addAttribute("comments", commentsService.getAllCommentsInATask(task));
        }
        return "taskdetails";
    }

    @PostMapping("/edittask")
    public String edittask(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "task_id") Long task_id,
            @RequestParam(name = "status") int status,
            @RequestParam(name = "folder_id") Long folder_id){
        tasksService.updateTask(name,description, task_id, status, folder_id);
        return "redirect:/details/" + folder_id;
    }

    @GetMapping("/deletetask/{folderid}/{id}")
    public String deletetask(
            @PathVariable(name = "folderid") Long folder_id,
            @PathVariable(name = "id") Long id){
        tasksService.deleteTask(folder_id,id);
        return "redirect:/details/" + folder_id;
    }

    @GetMapping("/categorydetails/{id}")
    public String categorydetails(@PathVariable(name = "id") Long id, Model model){
        TaskCategories category = taskCategoriesService.findTaskCategoriesById(id);
        if(category!=null){
            model.addAttribute("category", category);
        }
        return "categorydetails";
    }

    @GetMapping("/deletefolder/{id}")
    public String deletefolder(
            @PathVariable(name = "id") Long folder_id){
        foldersService.deleteFolder(folder_id);
        return "redirect:/";
    }

    @PostMapping("/addcomment")
    public String addcomment(@RequestParam(name = "folder_id") Long folder_id,
                             @RequestParam(name = "task_id") Long task_id,
                             @RequestParam( name = "comment") String commenttext){
        commentsService.addComment(folder_id,task_id,commenttext);
        return "redirect:/taskdetails/" + folder_id + "/" + task_id;
    }
}
