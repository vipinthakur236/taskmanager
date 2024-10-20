package com.taskmgmt.taskmanager.service;

import com.taskmgmt.taskmanager.entities.TaskEntity;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class TaskService {
    @Getter
    ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;
    private final DateFormat deadlineFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public TaskEntity addTask(String title, String description, String deadline) throws ParseException {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskId);
        taskEntity.setTitle(title);
        taskEntity.setDescription(description);
        taskEntity.setDeadline(deadlineFormatter.parse(deadline));
        taskEntity.setCompleted(false);
        tasks.add(taskEntity);
        taskId++;
        return taskEntity;
    }

    public TaskEntity getTaskIdById(Integer taskId) {

        return tasks.stream().filter(t -> t.getId() == taskId).findFirst().orElse(null);
    }

    public TaskEntity updateTask(int id, String description, String deadline, Boolean completed) throws ParseException {
        TaskEntity task = getTaskIdById(id);
        if (task == null) {
            return null;
        }
        if (description != null) {
            task.setDescription(description);
        }
        if (deadline != null) {
            task.setDeadline(deadlineFormatter.parse(deadline));
        }
        if (completed != null) {
            task.setCompleted(completed);
        }
        return task;
    }

    public boolean deleteTask(int id) {
        TaskEntity task = getTaskIdById(id);
        if (task != null) {
            tasks.remove(task);
            return true; // Task successfully deleted
        }
        return false; // Task not found
    }
}