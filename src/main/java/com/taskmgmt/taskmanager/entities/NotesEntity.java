package com.taskmgmt.taskmanager.entities;

import lombok.Data;

@Data
public class NotesEntity {
    private int id;
    private String title;
    private String description;
}