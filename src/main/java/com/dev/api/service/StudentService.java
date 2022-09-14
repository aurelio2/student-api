package com.dev.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.api.model.Student;
import com.dev.api.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
		
	public List<Student> listar(){
		return studentRepository.findAll();
	}
	
	public Student salvar(Student student) {		
		return  studentRepository.saveAndFlush(student);
	}
	
	public Student actualizar(Student student) {		
		return  studentRepository.saveAndFlush(student);
	}

	
	public void excluir(Long id) {
		//pegar o id 
		Student idStudent = studentRepository.findById(id).get();
		
		studentRepository.delete(idStudent);
	}
	
	public Student buscarPorId(Long id) {
		return studentRepository.findById(id).get();
		
	}
	
	
}
