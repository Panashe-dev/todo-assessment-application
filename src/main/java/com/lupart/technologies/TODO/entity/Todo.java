package com.lupart.technologies.TODO.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "todo")
@Table(name = "Todo")
public class Todo  extends AbstractAuditingEntity {

    private static final long serialVersionUID= -6234057529255411648L;

    @Column(name = "task_name",
            nullable = false
    )
    private  String taskName;

    @Column(
            name = "description",
            nullable = false
    )
    private String description;


    @Column(
            name = "target_date",
            nullable = false
    )
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm"
    )
    private LocalDateTime targetDate;

}
