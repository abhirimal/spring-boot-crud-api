package com.example.crud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddStudentRequestDto {

    private int studentRoll;

    private String studentName;

    private String studentPhone;

    private int departmentId;
}
