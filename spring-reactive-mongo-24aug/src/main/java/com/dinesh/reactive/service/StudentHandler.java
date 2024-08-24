package com.dinesh.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.dinesh.reactive.repository.StudentRepository;
import com.dinesh.reactive.model.Student;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
public class StudentHandler {

    @Autowired
    private StudentRepository studentRepository;

    // Add a new student
    public Mono<ServerResponse> addStudent(ServerRequest request) {
        return request.bodyToMono(Student.class).flatMap(data -> 
            ServerResponse.ok().body(studentRepository.save(data), Student.class)
        );
    }

    // Update a student by ID
    public Mono<ServerResponse> updateStudentById(ServerRequest request) {
        return request.bodyToMono(Student.class).flatMap(data -> 
            studentRepository.findById(data.getId()).flatMap(existingStudent -> {
                existingStudent.setName(data.getName());
                existingStudent.setAge(data.getAge());
                return ServerResponse.ok().body(studentRepository.save(existingStudent), Student.class);
            }).switchIfEmpty(ServerResponse.notFound().build())
        );
    }

    // Delete a student by ID
    public Mono<ServerResponse> deleteStudentById(ServerRequest request) {
        return request.bodyToMono(Student.class).flatMap(data -> 
            studentRepository.findById(data.getId()).flatMap(existingStudent -> 
                ServerResponse.ok().body(studentRepository.deleteById(data.getId()), Void.class)
            ).switchIfEmpty(ServerResponse.notFound().build())
        );
    }

    // Get all students
    public Mono<ServerResponse> getAllStudents(ServerRequest request) {
        return ServerResponse.ok().body(studentRepository.findAll(), Student.class);
    }
}
