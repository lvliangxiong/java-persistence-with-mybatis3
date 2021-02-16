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
            System.out.println(student);
        }
    }

    @Test
    public void testFindStudentById() {
        Student student = studentService.findStudentById(1);
        System.out.println(student);
        System.out.println(student.getAddress().getAddrId() + ":" + student.getAddress().getCity());
    }

    @Test
    public void testFindStudentWithAddressById() {
        Student student = studentService.findStudentWithAddressById(2);
        assertNotNull(student);
        System.out.println(student.getAddress().getAddrId() + ":" + student.getAddress().getCity());
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
        System.out.println("CreatedStudent: " + student);
    }

    @Test
    public void testCreateStudentWithMap() {
        Map<String, Object> studMap = new HashMap<String, Object>();
        long ts = System.currentTimeMillis();
        studMap.put("name", "stud_" + ts);
        studMap.put("email", "stud_" + ts + "@gmail.com");
        studMap.put("phone", null);
        studentService.createStudentWithMap(studMap);
    }

    @Test
    public void testUpdateStudent() {
        Student stud = new Student();
        long ts = System.currentTimeMillis();
        stud.setStudId(2);
        stud.setName("studddd_" + ts);
        stud.setEmail("studddd_" + ts + "@gmail.com");
        Student student = studentService.updateStudent(stud);
        assertNotNull(student);
        assertEquals("studddd_" + ts, student.getName());
        assertEquals("studddd_" + ts + "@gmail.com", student.getEmail());
        assertEquals(new Integer(2), student.getStudId());

        System.out.println("UpdatedStudent: " + student);
    }

    @Test
    public void testDeleteStudent() {
        boolean deleted = studentService.deleteStudent(3);
        assertTrue(deleted);
        System.out.println("deleteStudent:" + deleted);
    }

    @Test
    public void testFindStudentMapById() {
        Map<String, Object> studentMap = studentService.findStudentMapById(1);
        System.out.println(studentMap);
    }

    @Test
    public void testFindAllStudentsMap() {
        List<Map<String, Object>> studentMapList = studentService.findAllStudentsMap();
        for (Map<String, Object> studentMap : studentMapList) {
            System.out.println("studId :" + studentMap.get("studId"));
            System.out.println("name :" + studentMap.get("name"));
            System.out.println("email :" + studentMap.get("email"));
            System.out.println("phone :" + studentMap.get("phone"));
        }
    }

}
