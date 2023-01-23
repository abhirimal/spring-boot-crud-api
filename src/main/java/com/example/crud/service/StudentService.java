package com.example.crud.service;

import com.example.crud.dto.AddStudentRequestDto;
import com.example.crud.model.Department;
import com.example.crud.model.Student;
import com.example.crud.repo.DepartmentRepo;
import com.example.crud.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    public Student saveStudent(AddStudentRequestDto student){
        Department department = departmentRepo.findById(student.getDepartmentId()).orElse(null);
        Student newStudent = new Student();
        newStudent.setStudentName(student.getStudentName());
        newStudent.setStudentRoll(student.getStudentRoll());
        newStudent.setStudentPhone(student.getStudentPhone());
        newStudent.setDepartment(department);
        return studentRepo.save(newStudent);
    }

    public Student getStudent(int id){
        return studentRepo.findById(id).orElse(null);
    }

    public List<Student> getAllStudents(List<Student> getStudents){
        return studentRepo.findAll();
    }

    public String deleteStudent(int id){
        studentRepo.deleteById(id);
        return "Successfully deleted";
    }

    public Student updateStudent(Student student) {
        Student existingStudent = studentRepo.findById(student.getStudentId()).orElse(null);
        existingStudent.setStudentName(student.getStudentName());
        existingStudent.setStudentPhone(student.getStudentPhone());
        existingStudent.setStudentRoll(student.getStudentRoll());
        return studentRepo.save(existingStudent);
    }

}
