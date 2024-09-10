package com.example.student.Student.Crud.service;

import java.util.List;

import com.example.student.Student.Crud.payload.StudentDtos;

public interface StudentService {
	
	StudentDtos createStudent( StudentDtos student);
	StudentDtos updateStudent(StudentDtos studentDtos, Integer studentId);
	StudentDtos getStudentById(Integer studentId);
	
	List<StudentDtos> getAllStudent();
	
	void deleteStudent (Integer StudentId);

}
