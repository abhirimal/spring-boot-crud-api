package com.example.crud.controller;

import com.example.crud.dto.AddStudentRequestDto;
import com.example.crud.model.Student;
import com.example.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent") //database ma add garne
    public Student addStudent(@RequestBody AddStudentRequestDto student){
        return studentService.saveStudent(student);
    }

    @GetMapping("/getStudent/{id}")
    public Student showStudent(@PathVariable int id){
        return studentService.getStudent(id);
    }

    @PutMapping("/updateStudent")
    public Student updateStudentData(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable int id){
        return studentService.deleteStudent(id);
    }
}
