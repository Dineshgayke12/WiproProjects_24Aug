package com.dinesh.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class StudentRouter {
	
    @Autowired
    private StudentHandler studentHandler;

    @Bean
     RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions
                .route(POST("/api/student/add"), studentHandler::addStudent)
                .andRoute(POST("/api/student/update"), studentHandler::updateStudentById)
                .andRoute(POST("/api/student/delete"), studentHandler::deleteStudentById)
                .andRoute(GET("/api/student/getall"), studentHandler::getAllStudents);
    }

}
