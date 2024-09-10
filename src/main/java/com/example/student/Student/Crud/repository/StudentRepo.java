package com.example.student.Student.Crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student.Student.Crud.entity.Student;

@Repository
public interface StudentRepo  extends JpaRepository<Student, Integer>{

}
