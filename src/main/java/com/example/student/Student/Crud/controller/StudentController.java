package com.example.student.Student.Crud.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.Student.Crud.payload.ApiResponse;
import com.example.student.Student.Crud.payload.StudentDtos;
import com.example.student.Student.Crud.service.StudentService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/Student/")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/addStudent")
		public ResponseEntity<StudentDtos> createStudent(@Valid @RequestBody StudentDtos studentDtos){
		StudentDtos createStudentDtos= this.studentService.createStudent(studentDtos);
		return new ResponseEntity<StudentDtos>(createStudentDtos,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateStudent/{studentId}")
	public ResponseEntity<StudentDtos>updateStudent(@Valid @RequestBody StudentDtos studentDtos,
		@PathVariable("studentId") Integer pid ){
		StudentDtos upadteStudent= this.studentService.updateStudent(studentDtos, pid);
		return new ResponseEntity<StudentDtos>(upadteStudent,HttpStatus.OK);
	}
	
	@GetMapping("/getStudent/{studentId}")
	public ResponseEntity<StudentDtos> getStudentById(@PathVariable("studentId") Integer pid){
		StudentDtos studentById = this.studentService.getStudentById(pid);
		return new ResponseEntity<StudentDtos>(studentById,HttpStatus.OK);
	}
	@GetMapping("/getAllStudent")
	public ResponseEntity<List<StudentDtos>> getAllPatient(){
		return ResponseEntity.ok(this.studentService.getAllStudent());
	}
	@DeleteMapping("/{studentId}")
	public ResponseEntity<com.example.student.Student.Crud.payload.ApiResponse> deleteUser(@PathVariable("studentId") Integer pid){
		this.studentService.deleteStudent(pid);
		//return new ResponseEntity<>(Map.of("message", "User Deleted Successfully"), HttpStatus.OK);
	     return new ResponseEntity<ApiResponse>(new ApiResponse("Student Deleted Successfully",false),HttpStatus.OK);
    }
	@GetMapping("/currentUser")
	public String getloggedInUser(Principal principal)
	{
		return principal.getName();
	}

}
