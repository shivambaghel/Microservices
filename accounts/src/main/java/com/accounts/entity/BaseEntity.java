package com.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass  // it annotates that , this class going to be parent class for other entity class
@EntityListeners(AuditingEntityListener.class) // it will automatically set the created and updated date and time {audit package)
@Getter
@Setter
@ToString
public class BaseEntity {

    // @Column(name = "created_at")  // if you want to change the column name associated with DB column
    // @Column(updatable = false)  // it will not allow to update the value of this column / customer cannot update this column
    // @Column(insertable = false)   // it will not allow to insert the value of this column / customer cannot insert this column

    @CreatedDate // it will automatically set the current date and time when the record is created
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy // it will automatically set the user name who created the record
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate // it will automatically set the current date and time when the record is updated
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy // it will automatically set the user name who updated the record
    @Column(insertable = false)
    private String updatedBy;
}