package com.example.crud.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue
    private int studentId;

    private int studentRoll;

    private String studentName;

    private String studentPhone;

    @ManyToOne
    @JoinColumn(name = "department_id",
            referencedColumnName = "id")
    private Department department;

}
