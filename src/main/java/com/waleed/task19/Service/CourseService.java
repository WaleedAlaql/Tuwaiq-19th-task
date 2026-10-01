package com.waleed.task19.Service;

import com.waleed.task19.Api.APIException;
import com.waleed.task19.DTO.CourseDTO;
import com.waleed.task19.DTO.Out.CourseOutDTO;
import com.waleed.task19.DTO.Out.StudentOutDTO;
import com.waleed.task19.Entity.Course;
import com.waleed.task19.Entity.Student;
import com.waleed.task19.Entity.Teacher;
import com.waleed.task19.Repository.CourseRepository;
import com.waleed.task19.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<CourseOutDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseOutDTO> courseOutDTOs = new ArrayList<>();
        for (Course c : courses) {
            String tName = (c.getTeacher() != null) ? c.getTeacher().getName() : "No Teacher";
            courseOutDTOs.add(new CourseOutDTO(c.getName(), tName));
        }
        return courseOutDTOs;
    }

    public void addCourse(Long teacherId, CourseDTO courseDTO) {
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        if (teacher == null) throw new APIException("Teacher not found");

        Course course = new Course(null, courseDTO.getName(), teacher, null);
        courseRepository.save(course);
    }

    public void updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findCourseById(id);
        if (course == null) throw new APIException("Course not found");

        course.setName(courseDTO.getName());
        courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findCourseById(id);
        if (course == null) throw new APIException("Course not found");
        courseRepository.delete(course);
    }

    public String getTeacherNameByCourseId(Long courseId) {
        Course course = courseRepository.findCourseById(courseId);
        if (course == null) throw new APIException("Course not found");
        if (course.getTeacher() == null) throw new APIException("This course has no teacher assigned");

        return course.getTeacher().getName();
    }

    public List<StudentOutDTO> getStudentsByCourseId(Long courseId) {
        Course course = courseRepository.findCourseById(courseId);
        if (course == null) throw new APIException("Course not found");

        Set<Student> students = course.getStudents();
        List<StudentOutDTO> studentOutDTOs = new ArrayList<>();
        for ( Student s : students) {
            studentOutDTOs.add(new StudentOutDTO(s.getName(), s.getAge(), s.getMajor()));
        }
        return studentOutDTOs;
    }
}