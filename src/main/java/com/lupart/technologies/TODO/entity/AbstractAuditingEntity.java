package com.lupart.technologies.TODO.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @CreatedBy
    @Column(
            name = "created_by",
            nullable = false,
            length = 50,
            updatable = false
    )
    @JsonIgnore
    private String createdBy;

    @CreatedDate
    @Column(
            name = "created_date",
            nullable = false,
            updatable = false)
    @JsonFormat(
            shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss"
    )
    @JsonIgnore
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedBy
    @Column(
            name = "last_modified_by",
            length = 50
    )
    @JsonIgnore
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(
            name = "last_modified_date"
    )
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss"
    )
    @JsonIgnore
    private LocalDateTime lastModifiedDate =LocalDateTime.now();

    @Column(name = "isDeleted")
    @JsonIgnore
    private Boolean deleted=false;

}

