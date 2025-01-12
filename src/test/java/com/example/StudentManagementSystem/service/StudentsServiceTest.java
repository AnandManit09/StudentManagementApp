package com.example.StudentManagementSystem.service;

import com.example.StudentManagementSystem.model.Student;
import com.example.StudentManagementSystem.repository.StudentsDal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StudentsServiceTest {

    StudentsDal studentsDal= Mockito.mock(StudentsDal.class);
    StudentsService studentsService;

    //@BeforeEach will run the method before each test case is executed.
    @BeforeEach
    public void setUp(){
        studentsDal= Mockito.mock(StudentsDal.class);
        studentsService=new StudentsService(studentsDal);
    }
    public void setup(){
        studentsService=new StudentsService(studentsDal);
    }

    //Test cases for StudentsService
    @Test
    @DisplayName("Should increase value of param by 1")
    public void testIncrementService(){
        //Arrange

        int num=5;

        //Act
        int result=studentsService.increementService(num);


        Assertions.assertEquals(result,num+1);

    }

    @Test
    @DisplayName("Should return all the students in DB")
    public void shouldTestGetAllStudents(){
        List<Student> expectedList=new ArrayList<Student>();
        expectedList.add(
                new Student(1,"Junit Test",
                       19,
                        "JUNIT Test",
                        "Junit Address",
                        "junit@junit.com",
                        "29300394949")
        );
        Mockito.when(studentsDal.findAll()).thenReturn(expectedList);
        // This will return the expected list which we created when the findAll function of studentsDal is called.
        // It will not call the original function but mock the behaviour of that function by returning our list

        List<Student> actual=studentsService.getStudents();
        Assertions.assertEquals(actual.size(), expectedList.size());
        Assertions.assertEquals(actual.get(0).getName(),expectedList.get(0).getName());
        Assertions.assertEquals(actual.get(0).getAge(),expectedList.get(0).getAge());

    }

    @Test
    public void shouldTestDeleteById(){
        int sid=1;
        Mockito.when(studentsDal.existsById(sid)).thenReturn(true); // We are mocking the behaviour
        Mockito.doNothing().when(studentsDal).deleteById(sid); // we are mocking the results to not call the actual method to delete real sid

        List<Student> expectedList=new ArrayList<Student>();
        expectedList.add(
                new Student(1,"Ramesh Test",
                        19,
                        "JUNIT Test",
                        "Junit Address",
                        "junit@junit.com",
                        "29300394949")
        );
        expectedList.add(
                new Student(2,"Rakesh Test",
                        21,
                        "Spring Test",
                        "Junit Address",
                        "junit@junit.com",
                        "29300394949")
        );

        Boolean result=studentsService.deleteStudent(sid);

        Assertions.assertEquals(result,true);



    }
}
