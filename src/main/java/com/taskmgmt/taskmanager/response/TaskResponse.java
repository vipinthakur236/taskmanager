package com.taskmgmt.taskmanager.response;

import com.taskmgmt.taskmanager.entities.NotesEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskResponse {
    private int id;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;
    private List<NotesEntity> notes;
}