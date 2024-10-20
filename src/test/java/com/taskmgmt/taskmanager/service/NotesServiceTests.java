package com.taskmgmt.taskmanager.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
public class NotesServiceTests {

    @Autowired
    private TaskService taskService;

    @Autowired
    private NotesService notesService;

    @Test
    void testAddNote() throws ParseException {
        var task = taskService.addTask("task 1", "Read a book", "2024-10-30T12:08:56.235-0700");
        int taskId = task.getId();
        notesService.createNote(taskId, "Resume reading", "Page 50");
        var taskInfo = taskService.getTaskIdById(taskId);
        Assertions.assertEquals("task 1", taskInfo.getTitle());
        Assertions.assertEquals("Read a book", taskInfo.getDescription());
        var notes = notesService.getNotes(taskId);
        Assertions.assertEquals("Resume reading", notes.get(0).getTitle());
        Assertions.assertEquals("Page 50", notes.get(0).getDescription());
    }

    @Test
    void testGetNotes() throws ParseException {
        var task = taskService.addTask("task 1", "Read a book", "2024-10-30T12:08:56.235-0700");
        int taskId = task.getId();
        notesService.createNote(taskId, "Resume reading", "Page 50");
        var notes = notesService.getNotes(taskId);
        Assertions.assertEquals(1, notes.size());
    }
}