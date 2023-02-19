package com.example.crud.controller;

import com.example.crud.dto.AddStudentRequestDto;
import com.example.crud.model.Student;
import com.example.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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

    @GetMapping("/getAllStudent")
    public Page<Student> viewAllStudent(@RequestParam Integer pageNumber,
                                        @RequestParam Integer size){
        return studentService.getAllStudents(pageNumber, size);
    }

    @PutMapping("/updateStudent")
    public Student updateStudentData(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable int id){
        return studentService.deleteStudent(id);
    }

    @GetMapping("/export/excel")
    public void exportExcel() throws IOException {
        studentService.createStudentExcel();
    }
}
