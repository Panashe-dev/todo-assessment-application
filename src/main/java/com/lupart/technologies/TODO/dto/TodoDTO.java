package com.lupart.technologies.TODO.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private Integer id;
    private  String taskName;
    private String description;
    private LocalDateTime targetDate;
    private String createdBy;
    private String lastModifiedBy;
}
