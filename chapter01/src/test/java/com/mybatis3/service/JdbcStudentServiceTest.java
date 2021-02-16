package com.mybatis3.service;

import com.mybatis3.domain.Student;
import com.mybatis3.util.DataInitialization;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class JdbcStudentServiceTest {
    private static JdbcStudentService service;


    @BeforeAll
    static void beforeAll() {
        service = new JdbcStudentService();
        DataInitialization.initDatabase();
    }

    @AfterAll
    static void afterAll() {
        service = null;
    }

    @Test
    public void testFindStudentById() {
        Student existingStudent = service.findStudentById(1);
        System.out.println(existingStudent);
    }

    @Test
    public void testCreateStudent() {
        // For creating unique student names
        long ts = System.currentTimeMillis();
        Student newStudent = new Student(0, "student_" + ts, "student_" + ts + "@gmail.com", new Date());
        service.createStudent(newStudent);
        System.out.println(newStudent);
    }

    @Test
    public void testUpdateStudent() {
        int updateStudId = 1;
        Student updateStudent = service.findStudentById(updateStudId);
        long ts = System.currentTimeMillis();
        updateStudent.setEmail("student_" + ts + "@gmail.com");
        service.updateStudent(updateStudent);
    }
}