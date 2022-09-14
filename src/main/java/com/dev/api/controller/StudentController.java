package com.dev.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.api.exception.StudentNotFoundException;
import com.dev.api.model.Student;
import com.dev.api.repository.StudentRepository;
import com.dev.api.service.StudentService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService service;
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	
	@GetMapping("/list")
	public List<Student> list(){
		return service.listar();
	}
	
	@PostMapping("/")
	public ResponseEntity<String> salvar(@RequestBody Student student) {
		service.salvar(student);
		
		return ResponseEntity.status(HttpStatus.OK).body("Registado com sucesso");
	}
	/*
	@PutMapping("/")
	public ResponseEntity<String> alterar(@RequestBody Student student) {
		service.actualizar(student);
		
		return ResponseEntity.status(HttpStatus.OK).body("Actualizado com sucesso");
	}*/
	@PutMapping("/{id}")
	public Student alterar(@RequestBody Student student, @PathVariable Long id) {
		
		//service.actualizar(student);
		//return ResponseEntity.status(HttpStatus.OK).body("Actualizado com sucesso");
		
		return studentRepository.findById(id)
				.map(studente->{
					studente.setName(student.getName());
					studente.setAddress(student.getAddress());
					return service.salvar(student);
				}).orElseThrow(()->new StudentNotFoundException(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable("id") Long id){
		
		if(!studentRepository.existsById(id)) {
			throw new StudentNotFoundException(id);
		}
		//Chamar o method no service
		service.excluir(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso o estudante: "+id);
	}
	
	@GetMapping("/{id}")
	public Student buscarStudentPorId(@PathVariable Long id) {
		
		return studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException(id));
		
	}
}
