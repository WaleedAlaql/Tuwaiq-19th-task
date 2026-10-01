package com.waleed.task19.Service;

import com.waleed.task19.Api.APIException;
import com.waleed.task19.DTO.Out.StudentOutDTO;
import com.waleed.task19.DTO.StudentDTO;
import com.waleed.task19.Entity.Student;
import com.waleed.task19.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<StudentOutDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentOutDTO> studentOutDTOs = new ArrayList<>();
        for (Student s : students) {
            studentOutDTOs.add(new StudentOutDTO(s.getName(), s.getAge(), s.getMajor()));
        }
        return studentOutDTOs;
    }

    public void addStudent(StudentDTO studentDTO) {
        Student student = new Student(null, studentDTO.getName(), studentDTO.getAge(), studentDTO.getMajor(), null);
        studentRepository.save(student);
    }

    public void updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) throw new APIException("Student not found");

        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setMajor(studentDTO.getMajor());
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) throw new APIException("Student not found");
        studentRepository.delete(student);
    }

    public void updateStudentMajor(Long studentId, String newMajor) {
        Student student = studentRepository.findStudentById(studentId);
        if (student == null) throw new APIException("Student not found");

        student.setMajor(newMajor);
        if (student.getCourses() != null) {
            student.getCourses().clear();
        }
        studentRepository.save(student);
    }
}