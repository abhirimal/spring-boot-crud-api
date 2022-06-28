package com.example.crud.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue
    private int studentId;

    private int studentRoll;

    private String studentName;

    private String studentFaculty;

    private String studentPhone;

}
