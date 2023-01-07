package de.mischok.academy.skiller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {

    private String title;
    private String description;
    private Boolean done;
    private LocalDateTime dueDate;
}
