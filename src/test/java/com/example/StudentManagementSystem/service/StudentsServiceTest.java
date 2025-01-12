package com.example.StudentManagementSystem.service;

import com.example.StudentManagementSystem.repository.StudentsDal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StudentsServiceTest {

    StudentsDal studentsDal= Mockito.mock(StudentsDal.class);

    //Test cases for StudentsService
    @Test
    public void testIncrementService(){
        //Arrange
        StudentsService studentsService= new StudentsService(studentsDal);
        int num=5;

        //Act
        int result=studentsService.increementService(num);


        Assertions.assertEquals(result,num+1);

    }
}
