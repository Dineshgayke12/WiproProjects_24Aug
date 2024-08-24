package com.dinesh.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.stereotype.Repository;

import com.dinesh.reactive.model.Student;


@Repository
@EnableReactiveMongoRepositories
public interface StudentRepository extends ReactiveMongoRepository<Student,  String> {

}
