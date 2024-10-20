package com.taskmgmt.taskmanager.controller;

import com.taskmgmt.taskmanager.dao.CreateNoteDAO;
import com.taskmgmt.taskmanager.dao.ErrorResponseDAO;
import com.taskmgmt.taskmanager.entities.NotesEntity;
import com.taskmgmt.taskmanager.entities.TaskEntity;
import com.taskmgmt.taskmanager.response.NoteResponse;
import com.taskmgmt.taskmanager.service.NotesService;
import com.taskmgmt.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.InstanceNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NoteController {

    private NotesService notesService;
    private TaskService taskService;

    public NoteController(TaskService taskService, NotesService notesService) {
        this.taskService = taskService;
        this.notesService = notesService;
    }

    @GetMapping("")
    ResponseEntity<List<NotesEntity>> getNotesByTaskId(@PathVariable("taskId") int taskId) {
        var notes = notesService.getNotes(taskId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    ResponseEntity<NoteResponse> createNote(@RequestBody CreateNoteDAO request,
                                           @PathVariable("taskId") int taskId) throws InstanceNotFoundException {
        TaskEntity task = taskService.getTaskIdById(taskId);
        if (task == null) {
            throw new InstanceNotFoundException("Task doesn't exist");
        }
        NotesEntity note = notesService.createNote(taskId, request.getTitle(), request.getBody());
        NoteResponse response = new NoteResponse();
        response.setTaskId(taskId);
        response.setNote(note);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDAO> handleErrors(Exception e) {
        if (e instanceof InstanceNotFoundException) {
            return ResponseEntity.badRequest().body(new ErrorResponseDAO(e.getMessage()));
        }
        return ResponseEntity.internalServerError().body(new ErrorResponseDAO("Internal Server Error"));
    }
}