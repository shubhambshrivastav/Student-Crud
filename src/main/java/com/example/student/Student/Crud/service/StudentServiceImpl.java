package com.example.student.Student.Crud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.Student.Crud.entity.Student;
import com.example.student.Student.Crud.payload.StudentDtos;
import com.example.student.Student.Crud.repository.StudentRepo;
import com.example.student.Student.Crud.exception.ResourceNotFoundException;

@Service
public abstract class StudentServiceImpl implements StudentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public StudentDtos createStudent(StudentDtos student) {
        Student s = this.modelMapper.map(student, Student.class);
        Student addStudent = this.studentRepo.save(s);
        return this.modelMapper.map(addStudent, StudentDtos.class);
    }

    @Override
    public StudentDtos updateStudent(StudentDtos studentDtos, Integer studentId) {
        Student student = this.studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", studentId));
        student.setName(studentDtos.getName());
        student.setContactDetails(studentDtos.getContactDetails());
        student.setAddress(studentDtos.getAddress());
        student.setPincode(studentDtos.getPincode());

        Student saveStudent = this.studentRepo.save(student);
        return this.modelMapper.map(saveStudent, StudentDtos.class);
    }

    @Override
    public StudentDtos getStudentById(Integer studentId) {
        Student student = this.studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", studentId));
        return this.modelMapper.map(student, StudentDtos.class);
    }

    @Override
    public List<StudentDtos> getAllStudent() {
        List<Student> students = this.studentRepo.findAll();
        return students.stream()
                .map(student -> this.modelMapper.map(student, StudentDtos.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteStudent(Integer studentId) {
        Student student = this.studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", studentId));
        this.studentRepo.delete(student);
    }
}
