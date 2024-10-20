package com.taskmgmt.taskmanager.controller;

import com.taskmgmt.taskmanager.dao.CreateTaskDAO;
import com.taskmgmt.taskmanager.dao.UpdateTaskDAO;
import com.taskmgmt.taskmanager.dao.ErrorResponseDAO;
import com.taskmgmt.taskmanager.entities.NotesEntity;
import com.taskmgmt.taskmanager.entities.TaskEntity;
import com.taskmgmt.taskmanager.response.TaskResponse;
import com.taskmgmt.taskmanager.service.NotesService;
import com.taskmgmt.taskmanager.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;
    private NotesService notesService;
    private ModelMapper mapper;

    public TaskController(TaskService taskService, NotesService notesService) {
        this.taskService = taskService;
        this.notesService = notesService;
        mapper = new ModelMapper();
    }

    @GetMapping("")
    public ResponseEntity<List<TaskResponse>> getTasks() {
        var tasks = taskService.getTasks();
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (TaskEntity task : tasks) {
            TaskResponse taskResponse = mapper.map(task, TaskResponse.class);
            List<NotesEntity> notes = notesService.getNotes(task.getId());
            taskResponse.setNotes(notes);
            taskResponses.add(taskResponse);
        }
        return ResponseEntity.ok(taskResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("id") Integer id) {
        TaskEntity task = taskService.getTaskIdById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        TaskResponse response = mapper.map(task, TaskResponse.class);
        List<NotesEntity> notes = notesService.getNotes(id);
        response.setNotes(notes);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDAO request) throws ParseException {
        var task = taskService.addTask(request.getTitle(), request.getDescription(), request.getDeadline());

        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDAO
            request) throws ParseException {
        var task = taskService.updateTask(id, request.getDescription(), request.getDeadline(),
                request.getCompleted());
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Integer id) {
        boolean isDeleted = taskService.deleteTask(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDAO> handleErrors(Exception e) {
        if (e instanceof ParseException) {
            return ResponseEntity.badRequest().body(new ErrorResponseDAO("Invalid Date Format"));
        }
        return ResponseEntity.internalServerError().body(new ErrorResponseDAO("Internal Server Error"));
    }
}