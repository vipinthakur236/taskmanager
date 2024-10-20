package com.taskmgmt.taskmanager.dao;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTaskDAO {

    String description;
    String deadline;
    Boolean completed;
}