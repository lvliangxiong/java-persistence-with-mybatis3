package com.mybatis3.mapper;

import com.mybatis3.domain.Student;

import java.util.List;
import java.util.Map;


/**
 * @author Siva
 */
public interface StudentMapper {

    List<Student> findAllStudents();

    Student findStudentById(Integer id);

    Student selectStudentWithAddress(int id);

    void insertStudent(Student student);

    void insertStudentWithMap(Map<String, Object> map);

    void updateStudent(Student student);

    int deleteStudent(int id);

}
