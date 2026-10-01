package com.waleed.task19.Controller;

import com.waleed.task19.DTO.CourseDTO;
import com.waleed.task19.DTO.Out.CourseOutDTO;
import com.waleed.task19.DTO.Out.StudentOutDTO;
import com.waleed.task19.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<List<CourseOutDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping("/add/{teacherId}")
    public ResponseEntity<String> addCourse(@PathVariable Long teacherId, @RequestBody @Valid CourseDTO courseDTO) {
        courseService.addCourse(teacherId, courseDTO);
        return ResponseEntity.status(201).body("Course added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @RequestBody @Valid CourseDTO courseDTO) {
        courseService.updateCourse(id, courseDTO);
        return ResponseEntity.ok("Course updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }

    @GetMapping("/teacher-name/{courseId}")
    public ResponseEntity<String> getTeacherNameByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getTeacherNameByCourseId(courseId));
    }

    @GetMapping("/students/{courseId}")
    public ResponseEntity<List<StudentOutDTO>> getStudentsByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getStudentsByCourseId(courseId));
    }
}