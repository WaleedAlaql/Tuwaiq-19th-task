package com.waleed.task19.Controller;

import com.waleed.task19.DTO.AddressDTO;
import com.waleed.task19.DTO.Out.TeacherOutDTO;
import com.waleed.task19.DTO.TeacherDTO;
import com.waleed.task19.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/get")
    public ResponseEntity<List<TeacherOutDTO>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTeacher(@RequestBody @Valid TeacherDTO teacherDTO) {
        teacherService.addTeacher(teacherDTO);
        return ResponseEntity.status(201).body("Teacher added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTeacher(@PathVariable Long id, @RequestBody @Valid TeacherDTO teacherDTO) {
        teacherService.updateTeacher(id, teacherDTO);
        return ResponseEntity.ok("Teacher updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted successfully");
    }

    @PostMapping("/address/add/{teacherId}")
    public ResponseEntity<String> addTeacherAddress(@PathVariable Long teacherId, @RequestBody @Valid AddressDTO addressDTO) {
        teacherService.addTeacherAddress(teacherId, addressDTO);
        return ResponseEntity.status(201).body("Address added successfully");
    }

    @PutMapping("/address/update/{teacherId}")
    public ResponseEntity<String> updateTeacherAddress(@PathVariable Long teacherId, @RequestBody @Valid AddressDTO addressDTO) {
        teacherService.updateTeacherAddress(teacherId, addressDTO);
        return ResponseEntity.ok("Address updated successfully");
    }

    @DeleteMapping("/address/delete/{teacherId}")
    public ResponseEntity<String> deleteTeacherAddress(@PathVariable Long teacherId) {
        teacherService.deleteTeacherAddress(teacherId);
        return ResponseEntity.ok("Address deleted successfully");
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<TeacherOutDTO> getTeacherDetails(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacherDetails(id));
    }
}