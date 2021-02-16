package com.mybatis3.service;

import com.mybatis3.domain.Student;
import com.mybatis3.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static com.mybatis3.util.DataInitialization.initDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudentServiceTest {
    private static StudentService studentService;

    @BeforeAll
    public static void beforeAll() {
        initDatabase();

        SqlSessionFactory sqlSessionFactory = null;
        // Use this if you want XML based configuration
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactoryUsingXML();

        // Use this if you want to use Java API configuration
        // sqlSessionFactory = MyBatisUtil.getSqlSessionFactoryUsingJavaAPI();

        studentService = new StudentService(sqlSessionFactory);
    }

    @AfterAll
    public static void afterAll() {
        studentService = null;
    }

    @Test
    public void testFindAllStudents() {
        List<Student> students = studentService.findAllStudents();
        assertNotNull(students);
        for (Student student : students) {
            assertNotNull(student);
            System.out.println(student);
        }
    }

    @Test
    public void testFindStudentById() {
        Student student = studentService.findStudentById(1);
        assertNotNull(student);
    }

    @Test
    public void testCreateUStudent() {
        Student student = new Student();
        int id = 4;
        student.setStudId(id);
        student.setName("student_" + id);
        student.setEmail("student_" + id + "gmail.com");
        student.setDob(new Date());
        Student newStudent = studentService.createStudent(student);
        assertNotNull(newStudent);
        assertEquals("student_" + id, newStudent.getName());
        assertEquals("student_" + id + "gmail.com", newStudent.getEmail());
    }

    @Test
    public void testUpdateStudent() {
        int id = 2;
        Student student = studentService.findStudentById(id);
        student.setStudId(id);
        student.setName("student_" + id);
        student.setEmail("student_" + id + "gmail.com");
        Date now = new Date();
        student.setDob(now);
        studentService.updateStudent(student);
        Student updatedStudent = studentService.findStudentById(id);
        assertNotNull(updatedStudent);
        assertEquals("student_" + id, updatedStudent.getName());
        assertEquals("student_" + id + "gmail.com", updatedStudent.getEmail());
    }
}
