package com.taskmgmt.taskmanager.dao;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
public class CreateTaskDAO {
    String title;
    String description;
    String deadline;
}
