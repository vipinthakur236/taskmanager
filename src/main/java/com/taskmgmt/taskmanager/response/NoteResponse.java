package com.taskmgmt.taskmanager.response;

import com.taskmgmt.taskmanager.entities.NotesEntity;
import lombok.Data;

@Data
public class NoteResponse {
    int taskId;
    NotesEntity note;
}