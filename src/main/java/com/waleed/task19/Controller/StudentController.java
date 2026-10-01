package com.waleed.task19.Controller;

import com.waleed.task19.DTO.Out.StudentOutDTO;
import com.waleed.task19.DTO.StudentDTO;
import com.waleed.task19.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<List<StudentOutDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody @Valid StudentDTO studentDTO) {
        studentService.addStudent(studentDTO);
        return ResponseEntity.status(201).body("Student added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody @Valid StudentDTO studentDTO) {
        studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok("Student updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }

    @PutMapping("/change-major/{studentId}/{newMajor}")
    public ResponseEntity<String> updateStudentMajor(@PathVariable Long studentId, @PathVariable String newMajor) {
        studentService.updateStudentMajor(studentId, newMajor);
        return ResponseEntity.ok("Student major updated and enrolled courses dropped successfully");
    }
}