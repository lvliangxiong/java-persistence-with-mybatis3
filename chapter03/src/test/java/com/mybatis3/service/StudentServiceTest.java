package com.mybatis3.service;

import com.mybatis3.domain.PhoneNumber;
import com.mybatis3.domain.Student;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mybatis3.util.DataInitialization.initDatabase;
import static org.junit.jupiter.api.Assertions.*;


public class StudentServiceTest {
    private static StudentService studentService;

    @BeforeAll
    public static void beforeAll() {
        studentService = new StudentService();
        initDatabase();
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
        System.out.println(student);
    }

    @Test
    public void testFindStudentWithAddressById() {
        Student student = studentService.findStudentWithAddressById(1);
        assertNotNull(student);
        System.out.println(student);
    }

    @Test
    public void testCreateStudent() {
        Student stud = new Student();
        long ts = System.currentTimeMillis();
        stud.setName("stud_" + ts);
        stud.setEmail("stud_" + ts + "@gmail.com");
        stud.setPhone(new PhoneNumber("123-456-7890"));
        Student student = studentService.createStudent(stud);
        assertNotNull(student);
        assertEquals("stud_" + ts, student.getName());
        assertEquals("stud_" + ts + "@gmail.com", student.getEmail());
        System.out.println("Created Student:" + student);

    }

    @Test
    public void testCreateStudentWithMap() {
        Map<String, Object> studMap = new HashMap<String, Object>();
        long ts = System.currentTimeMillis();
        studMap.put("name", "stud_" + ts);
        studMap.put("email", "stud_" + ts + "@gmail.com");
        studMap.put("phone", null);
        System.out.println(studMap);
        studentService.createStudentWithMap(studMap);
        // 插入数据后，Map 中多出了 key 为 phone 和 studId 的 key-value pair
        System.out.println(studMap);
    }

    @Test
    public void testUpdateStudent() {
        Student stud = new Student();
        long ts = System.currentTimeMillis();
        stud.setStudId(2);
        stud.setName("stud_" + ts);
        stud.setEmail("stud_" + ts + "@gmail.com");
        Student updatedStudent = studentService.updateStudent(stud);
        assertNotNull(updatedStudent);
        assertEquals("stud_" + ts, updatedStudent.getName());
        assertEquals("stud_" + ts + "@gmail.com", updatedStudent.getEmail());
    }

    @Test
    public void testDeleteStudent() {
        boolean deleteStudent = studentService.deleteStudent(3);
        assertTrue(deleteStudent);
        System.out.println("deleteStudent:" + deleteStudent);
    }
}
