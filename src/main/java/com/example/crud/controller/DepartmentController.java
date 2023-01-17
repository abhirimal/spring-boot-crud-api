package com.example.crud.controller;

import com.example.crud.dto.AddStudentRequestDto;
import com.example.crud.model.Department;
import com.example.crud.model.Student;
import com.example.crud.service.DepartmentService;
import com.example.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/addDepartment") //database ma add garne
    public Department addDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/getDepartment/{id}")
    public Department showDepartment(@PathVariable int id){
        return departmentService.getDepartment(id);
    }

}
