package com.dinesh.reactive;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.dinesh.reactive.model.Student;
import com.dinesh.reactive.service.StudentHandler;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebClient
class SpringReactiveMongo24augApplicationTests {

    @Autowired
    private WebTestClient webTestClient;
    
    @MockBean
    private StudentHandler studentHandler;

    @Test
    void testAddStudent() {
        Student student = new Student();
        student.setName("Dinesh");
        student.setAge(26);

        when(studentHandler.addStudent(any())).thenReturn(ServerResponse.ok().bodyValue(student));

        webTestClient.post()
            .uri("/api/student/add")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(student)
            .equals(student);

    }
    
    @Test
    void testUploadStudentById() {
    	
    	Student student= new Student();
    	 student.setName("ganesh");
         student.setAge(24);
         
         when(studentHandler.updateStudentById(any())).thenReturn(ServerResponse.ok().bodyValue(student));
         
         webTestClient.post().uri("/api/student/update").contentType(MediaType.APPLICATION_JSON)
                                                         .bodyValue(student)
                                                         .equals(student);
    }
}
