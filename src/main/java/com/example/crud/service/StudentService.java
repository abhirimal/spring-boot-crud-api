package com.example.crud.service;

import com.example.crud.dto.AddStudentRequestDto;
import com.example.crud.model.Department;
import com.example.crud.model.Student;
import com.example.crud.repo.DepartmentRepo;
import com.example.crud.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;

    private final DepartmentRepo departmentRepo;

    /*
         output
            = (C:\Users\abhir + \ + desktop)
            = C:\Users\abhir\desktop
     */
    private String baseLocation = System.getProperty("user.home") + File.separator + "desktop";

    public Student saveStudent(AddStudentRequestDto student) {
        Department department = departmentRepo.findById(student.getDepartmentId()).orElse(null);
        Student newStudent = new Student();
        newStudent.setStudentName(student.getStudentName());
        newStudent.setStudentRoll(student.getStudentRoll());
        newStudent.setStudentPhone(student.getStudentPhone());
        newStudent.setDepartment(department);
        return studentRepo.save(newStudent);
    }

    public Student getStudent(int id) {
        return studentRepo.findById(id).orElse(null);
    }

    public Page<Student> getAllStudents(Integer pageNumber, Integer size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return studentRepo.findAll(pageable);
    }

    public String deleteStudent(int id) {
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

    public boolean createStudentExcel() throws IOException {
        List<Student> students = studentRepo.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 4000);
        sheet.setColumnWidth(4, 4000);

        Row row = sheet.createRow(0);

        List<String> headers = Arrays.asList("Student Id", "Student Name", "Student Phone", "Roll Number", "Department Id");

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // creating headers for excel
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headers.get(i));
            cell.setCellStyle(headerStyle);

        }

        // populating data in excel

        for (int i = 0; i < students.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);

            int cellCount = 0;
            Cell cell1 = dataRow.createCell(cellCount);
            cell1.setCellValue(students.get(i).getStudentId());
            Cell cell2 = dataRow.createCell(cellCount + 1);
            cell2.setCellValue(students.get(i).getStudentName());
            Cell cell3 = dataRow.createCell(cellCount + 2);
            cell3.setCellValue(students.get(i).getStudentPhone());
            Cell cell4 = dataRow.createCell(cellCount + 3);
            cell4.setCellValue(students.get(i).getStudentRoll());
            Cell cell5 = dataRow.createCell(cellCount + 4);
            cell5.setCellValue(
                    students.get(i).getDepartment() == null
                            ? -2
                            : students.get(i).getDepartment().getId()
            );

        }

        String fileName = "students.xlsx";
        String fileLocation = baseLocation + File.separator + fileName;
        File file = new File(fileLocation);
        workbook.write(new FileOutputStream(file));
        workbook.close();
        return true;

    }

}
