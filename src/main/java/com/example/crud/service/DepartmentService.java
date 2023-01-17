package com.example.crud.service;

import com.example.crud.model.Department;
import com.example.crud.model.Student;
import com.example.crud.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    public Department saveDepartment(Department department) {
        return departmentRepo.save(department);
    }

    public Department getDepartment(int id) {
        return departmentRepo.findById(id).orElse(null);
    }
}
