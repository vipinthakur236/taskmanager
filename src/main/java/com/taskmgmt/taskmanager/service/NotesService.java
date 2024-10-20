package com.taskmgmt.taskmanager.service;

import com.taskmgmt.taskmanager.entities.NotesEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class NotesService {

    private int noteId = 1;
    Map<Integer, TaskNoteHolder> taskNoteHolderMap = new HashMap<>();

    class TaskNoteHolder {
        List<NotesEntity> notesEntities = new ArrayList<>();

        public List<NotesEntity> getNotesEntities() {
            return notesEntities;
        }

        public void setNotesEntities(List<NotesEntity> notesEntities) {
            this.notesEntities = notesEntities;
        }
    }

    public List<NotesEntity> getNotes(int taskId) {
        TaskNoteHolder taskNoteHolder = taskNoteHolderMap.get(taskId);
        return taskNoteHolder == null ? Collections.emptyList() : taskNoteHolder.getNotesEntities();
    }

    public NotesEntity createNote(int taskId, String title, String description) {
        NotesEntity noteEntity = new NotesEntity();
        noteEntity.setId(noteId++);
        noteEntity.setTitle(title);
        noteEntity.setDescription(description);
        TaskNoteHolder noteHolder = taskNoteHolderMap.get(taskId);
        if (noteHolder == null) {
            noteHolder = new TaskNoteHolder();
        }
        List<NotesEntity> notes = noteHolder.getNotesEntities();
        notes.add(noteEntity);
        taskNoteHolderMap.put(taskId, noteHolder);
        return noteEntity;
    }
}