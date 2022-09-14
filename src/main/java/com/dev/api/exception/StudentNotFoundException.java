package com.dev.api.exception;

@SuppressWarnings("serial")
public class StudentNotFoundException extends RuntimeException{

	public StudentNotFoundException(Long id) {
		super("Could not found the student ID: " + id);
	}

}
