package com.dev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.api.model.Student;

public interface StudentRepository  extends JpaRepository<Student, Long>{

}
