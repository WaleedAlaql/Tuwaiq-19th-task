package com.waleed.task19.Service;

import com.waleed.task19.Api.APIException;
import com.waleed.task19.DTO.AddressDTO;
import com.waleed.task19.DTO.Out.TeacherOutDTO;
import com.waleed.task19.DTO.TeacherDTO;
import com.waleed.task19.Entity.Address;
import com.waleed.task19.Entity.Teacher;
import com.waleed.task19.Repository.AddressRepository;
import com.waleed.task19.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;

    public List<TeacherOutDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherOutDTO> teacherOutDTOs = new ArrayList<>();
        for (Teacher t : teachers) {
            String area = (t.getAddress() != null) ? t.getAddress().getArea() : "No Address";
            teacherOutDTOs.add(new TeacherOutDTO(t.getName(), t.getAge(), t.getEmail(), t.getSalary(), area));
        }
        return teacherOutDTOs;
    }

    public void addTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher(null, teacherDTO.getName(), teacherDTO.getAge(), teacherDTO.getEmail(), teacherDTO.getSalary(), null, null);
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Long id, TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null) throw new APIException("Teacher not found");

        teacher.setName(teacherDTO.getName());
        teacher.setAge(teacherDTO.getAge());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setSalary(teacherDTO.getSalary());
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null) throw new APIException("Teacher not found");
        teacherRepository.delete(teacher);
    }

    public void addTeacherAddress(Long teacherId, AddressDTO addressDTO) {
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        if (teacher == null) throw new APIException("Teacher not found");

        Address address = new Address(null, addressDTO.getArea(), addressDTO.getStreet(), addressDTO.getBuildingNumber(), teacher);
        addressRepository.save(address);
    }

    public void updateTeacherAddress(Long teacherId, AddressDTO addressDTO) {
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        if (teacher == null) throw new APIException("Teacher not found");

        Address address = teacher.getAddress();
        if (address == null) throw new APIException("Address not found for this teacher");

        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(addressDTO.getBuildingNumber());
        addressRepository.save(address);
    }

    public void deleteTeacherAddress(Long teacherId) {
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        if (teacher == null) throw new APIException("Teacher not found");

        Address address = teacher.getAddress();
        if (address == null) throw new APIException("Address not found");

        teacher.setAddress(null);
        addressRepository.delete(address);
    }

    public TeacherOutDTO getTeacherDetails(Long id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null) throw new APIException("Teacher not found");
        String area = (teacher.getAddress() != null) ? teacher.getAddress().getArea() : "No Address";
        return new TeacherOutDTO(teacher.getName(), teacher.getAge(), teacher.getEmail(), teacher.getSalary(), area);
    }
}