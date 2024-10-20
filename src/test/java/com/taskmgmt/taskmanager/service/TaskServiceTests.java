package com.taskmgmt.taskmanager.service;

import com.taskmgmt.taskmanager.entities.TaskEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;

@SpringBootTest
public class TaskServiceTests {

    @Autowired
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService.getTasks().clear();
    }

    @Test
    void testAddTask() throws ParseException {
        var task = taskService.addTask("task 1", "Read a book", "2024-10-30T12:08:56.235-0700");
        int taskId = task.getId();
        var taskInfo = taskService.getTaskIdById(taskId);
        Assertions.assertEquals("task 1", taskInfo.getTitle());
        Assertions.assertEquals("Read a book", taskInfo.getDescription());
    }

    @Test
    void testGetTasks() throws ParseException {
        var task = taskService.addTask("task 1", "Read a book", "2024-10-30T12:08:56.235-0700");
        List<TaskEntity> tasks = taskService.getTasks();
        Assertions.assertEquals(1, tasks.size());
        taskService.deleteTask(task.getId());
        Assertions.assertEquals(0, tasks.size());
    }

    @Test
    void testUpdateTask() throws ParseException {
        var task = taskService.addTask("task 1", "Read a book", "2024-10-30T12:08:56.235-0700");
        int taskId = task.getId();
        taskService.updateTask(taskId, "Finished reading", "2024-10-30T12:08:56.235-0700", true);
        var taskInfo = taskService.getTaskIdById(taskId);
        Assertions.assertEquals("Finished reading", taskInfo.getDescription());
        Assertions.assertTrue(taskInfo.isCompleted());
    }
}